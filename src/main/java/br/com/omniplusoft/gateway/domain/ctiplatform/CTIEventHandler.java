package br.com.omniplusoft.gateway.domain.ctiplatform;

import br.com.omniplusoft.gateway.domain.ctiplatform.event.CTIEvent;
import br.com.omniplusoft.gateway.domain.ctiplatform.exceptions.CTIMethodNotImplementedException;
import br.com.omniplusoft.gateway.infrastructure.ctiplatform.CTIEvents;
import br.com.omniplusoft.gateway.infrastructure.ctiplatform.annotation.EventHandler;
import br.com.omniplusoft.gateway.infrastructure.ctiplatform.annotation.Handle;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by hermeswaldemarin on 14/12/15.
 */
@Service
public class CTIEventHandler {

    private static Map<CTIEvents, EventDispatcher> cache = new ConcurrentHashMap<>();

    private ApplicationContext context;

    @Autowired
    public CTIEventHandler(ApplicationContext context){
        this.context = context;
        loadDispatchers();
    }

    private void loadDispatchers(){
        Reflections reflections = new Reflections("br.com.omniplusoft");
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(EventHandler.class);

        for (Class clazz: annotated){

            for(Method method: clazz.getDeclaredMethods()){
                Handle handle = null;
                if(( handle = method.getAnnotation(Handle.class)) != null){

                    cache.put(handle.value(), new EventDispatcher(method, context.getBean(clazz)));

                }
            }


        }

    }

    public void dispatch(CTIEvent event){

        try{
            cache.get(CTIEvents.getByClass(event.getClass())).invoke(event);
        }catch (NullPointerException e){
            throw new CTIMethodNotImplementedException("There is no method annotated with event " + CTIEvents.getByClass(event.getClass()), e);
        }
    }

}
