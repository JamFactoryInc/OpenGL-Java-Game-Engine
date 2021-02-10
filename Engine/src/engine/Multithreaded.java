package engine;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class Multithreaded implements Runnable 
{
	private int thread;
	private boolean doStop = false;
	Map<Method, Object> methodObjectMap = new HashMap<>();
	
	public Multithreaded(int thread) 
	{
		this.thread = thread;
	}
	
	@Override
	public void run() 
	{
		startNewThread(object);
		method.invoke(object);
		while(!doStop)
		{
			method.invoke(object);
		}
		
	}
	
	public void stop()
	{
		doStop = true;
	}
	
	private void startNewThread(Object object) throws Exception {
	    Class<?> clazz = object.getClass();
	    for (Method method : clazz.getDeclaredMethods()) {
	        if (method.isAnnotationPresent(Thread.class)) {
	            method.setAccessible(true);
	            methodObjectMap.put(method, object);
	            
	        }
	    }
	 }

}
