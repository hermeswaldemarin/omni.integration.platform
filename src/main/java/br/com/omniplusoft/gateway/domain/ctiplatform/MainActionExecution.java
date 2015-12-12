package br.com.omniplusoft.gateway.domain.ctiplatform;

import br.com.omniplusoft.gateway.domain.ctiplatform.exceptions.DispatchExecutionException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class MainActionExecution extends MethodExecution{

	public MainActionExecution(Method method, Class<?> type){
		super(method, type);

	}

	@Override
	public void execute(Map map, OmniPlusoftCTI target){

		try {

            getMethod().invoke(target, map);
			
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new DispatchExecutionException("Failed to execute informed method.", e);
		}
	}
	
}
