package br.com.omniplusoft.gateway.domain.ctiplatform;

import br.com.omniplusoft.gateway.domain.ctiplatform.event.CTIEvent;
import br.com.omniplusoft.gateway.domain.ctiplatform.exceptions.CTIMethodArgumentMismatchException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by hermeswaldemarin on 14/12/15.
 */
public class EventDispatcher {

    private Method method;
    private Object instance;

    public EventDispatcher(Method method, Object instance) {
        this.method = method;
        this.instance = instance;
    }

    public void invoke(CTIEvent event){
        try {

            method.invoke(instance, event);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e){
            throw new CTIMethodArgumentMismatchException("Method "
                    + this.method.getName()
                    + " from "
                    + this.instance.getClass().getName()
                    + " has no valid argument type."
                    , e);
        }
    }
}
