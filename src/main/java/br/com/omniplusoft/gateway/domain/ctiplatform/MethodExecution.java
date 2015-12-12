package br.com.omniplusoft.gateway.domain.ctiplatform;

import java.lang.reflect.Method;
import java.util.Map;

public abstract class MethodExecution<T> {
	
	private Method method;
	private Class<?> type;

	public MethodExecution(Method method, Class<?> type){
		this.method = method;
		this.type = type;
		
	}
	
	public abstract void execute(Map map, OmniPlusoftCTI target);
	
	protected Method getMethod() {
		return method;
	}

	protected Class<?> getType() {
		return type;
	}

}
