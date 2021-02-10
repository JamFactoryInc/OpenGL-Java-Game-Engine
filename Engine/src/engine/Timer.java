package engine;


public class Timer
{
	public long ms;
	public long duration;
	private String expirationMethod;
	private Object callerInstance;
	
	public Timer()
	{
		Engine.activeTimers.add(this);

	}
	public Timer(float time)
	{

		Engine.activeTimers.add(this);
		duration = (long)(time * 500000000);
	}
	public Timer(float time, Object callerInstance, String expirationMethod)
	{
		
		Engine.activeTimers.add(this);
		this.callerInstance = callerInstance;
		duration = (long)(time * 500000000);
		this.expirationMethod = expirationMethod;
	}

	public void expire()
	{
		
		if(callerInstance instanceof Invoker)
		{
			
			((Invoker)callerInstance).expire();
		}
		else 
			new Invoker(callerInstance, expirationMethod);
	}
	
	
	
	
}
