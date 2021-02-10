package engine;


import java.lang.reflect.InvocationTargetException;



public class Invoker 
{
	public Object callerObject;
	public String methodName;
	public Class<?>[] paramTypes;
	public Object[] params;
	
	/**
	 * Creates a new Invoker. Used to call methods by class instance and method name. Methods called after a set time
	 * @param obj object instance that contains the method
	 * @param methodName method name to call. Case sensitive
	 * @param time time in seconds to wait before method call
	 */
	public Invoker(Object obj, String methodName, float time)
	{
		this.callerObject = obj;
		this.methodName = methodName;
		//System.out.println("this");
		new Timer(time, this, "timedInvoke");
		
	}

	/**
	 * Creates a new Invoker. Used to call methods by class instance and method name while passing in values. Methods called after a set time
	 * @param obj object instance that contains the method
	 * @param methodName method name to call. Case sensitive
	 * @param paramTypes method parameter types in the format <code>new Class<?>[] {T1.class, T2.class...}</code>
	 * @param params values to pass into the method in the format <code>new Object[] {1f, "example"}</code>
	 * @param time time in seconds to wait before method call
	 */
	public Invoker(Object obj, String methodName, Class<?>[] paramTypes, Object[] params, float time)
	{
		this.callerObject = obj;
		this.methodName = methodName;
		this.paramTypes = paramTypes;
		this.params = params;
		//System.out.println("this2");
		new Timer(time, this, "timedInvoke");
	}
	/**
	 * Creates a new Invoker. Used to call methods by class instance and method name. Methods called after a set time
	 * @param obj object instance that contains the method
	 * @param methodName method name to call. Case sensitive
	 */
	public Invoker(Object obj, String methodName)
	{
		java.lang.reflect.Method method;
		try 
		{
		  method = obj.getClass().getMethod(methodName);
		  
		  method.invoke(obj);
		} 
		catch (SecurityException e) 
		{ 
			System.out.println(e.getMessage()); 
		}  
		catch (NoSuchMethodException e) 
		{ 
			System.out.println(e.getMessage()); 
		}
		catch (IllegalArgumentException e) 
		{  
			System.out.println(e.getMessage()); 
		}
		catch (IllegalAccessException e) 
		{  
			System.out.println(e.getMessage()); 
		}
		catch (InvocationTargetException e) 
		{  
			System.out.println(e.getMessage()); 
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage()); 
		}

	}
	
	/**
	 * Creates a new Invoker. Used to call methods by class instance and method name while passing in values. Methods called after a set time
	 * @param obj object instance that contains the method
	 * @param methodName method name to call. Case sensitive
	 * @param paramTypes method parameter types in the format <code>new Class<?>[] {T1.class, T2.class...}</code>
	 * @param params values to pass into the method in the format <code>new Object[] {1f, "example"}</code>
	 */
	public Invoker(Object obj, String methodName, Class<?>[] paramTypes, Object[] params)
	{
		java.lang.reflect.Method method;
		try 
		{
		  method = obj.getClass().getMethod(methodName, paramTypes);
		  
		  method.invoke(obj, params);
		} 
		catch (SecurityException e) 
		{ 
			System.out.println("Security exception " + e.getMessage()); 
		}  
		catch (NoSuchMethodException e) 
		{ 
			System.out.println("No such method " + e.getMessage()); 
		}
		catch (IllegalArgumentException e) 
		{  
			System.out.println("Illegal argument " + e.getMessage()); 
		}
		catch (IllegalAccessException e) 
		{  
			System.out.println("Illegal access " + e.getMessage()); 
		}
		catch (InvocationTargetException e) 
		{  
			System.out.println("Invocation target exeption " + e.getMessage()); 
		}
		catch (Exception e) 
		{
			System.out.println("Unknown exception " + e.getMessage()); 
		}

	}
	
	/**
	 * Called when timer runs out, or can be called prematurely. Runs its associated method before removing itself from the list of active timers
	 */
	public void expire()
	{
		
		java.lang.reflect.Method method;
		try 
		{
			method = callerObject.getClass().getMethod(methodName, paramTypes);
			if(params != null)
			{
				
				method.invoke(callerObject, params);
			}
			else
			{
				method.invoke(callerObject);
			}
		} 
		catch (SecurityException e) 
		{ 
			System.out.println("Security exception " + e.getMessage()); 
		}  
		catch (NoSuchMethodException e) 
		{ 
			System.out.println("No such method " + e.getMessage()); 
		}
		catch (IllegalArgumentException e) 
		{  
			System.out.println("Illegal argument " + e.getMessage()); 
		}
		catch (IllegalAccessException e) 
		{  
			System.out.println("Illegal access " + e.getMessage()); 
		}
		catch (InvocationTargetException e) 
		{  
			System.out.println("Invocation target exeption " + e.getMessage()); 
		}
		catch (Exception e) 
		{
			System.out.println("Unknown exception " + e.getMessage()); 
		}

	}
	
	public static Invoker invokeRepeating(Object obj, String methodName, float time)
	{
		return new Invoker(obj, methodName, time);
	}
}
