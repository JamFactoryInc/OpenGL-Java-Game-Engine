package engine;

import java.util.ArrayList;
import java.util.List;
import com.jogamp.newt.event.KeyEvent;

public abstract class Engine extends Property
{
	public static List<Engine> engineObjects = new ArrayList<Engine>();
	public static List<Engine> physicsObjects = new ArrayList<>();
	public static List<Timer> activeTimers = new ArrayList<Timer>();
	public static RenderQueue renderQueue = new RenderQueue();
	
	public static double secondsSinceLaunch = 0;
	public static Input input = new Input();
	public static int maxFrameRate = 200;
	public static int windowWidth = 800;
	public static int windowHeight = 500;
	public static long previousFrameTime = 0;
	public static long currentFrameTime = 0;
	public static int maxThreads = 8;
	public static final List<Multithreaded> threads = new ArrayList<>();
	static 
	{
		for(int i = 0; i < maxThreads; i++)
		{
			threads.add(new Multithreaded(i));
		}
	}
	
	public static List<GameObject> gameObjects = new ArrayList<>();
	
	/**
	 * Internal use. Adds object to Engine.engineObjects list to be iterated on
	 * @param obj
	 * @return
	 */
	public static Engine createEngineObject(Object obj)
	{
		
		((Engine)obj).propertyId = 1;

		engineObjects.add((Engine)obj);
		//System.out.println(engineObjects.get(engineObjects.size()-1).toString());
		return (Engine)obj;
	}
	
	
	/**
	 * Internal use
	 */
	public void masterFixedUpdate()
	{
		physicsTick();
		updateTimers();
		for(Engine engine : engineObjects)
		{
			engine.fixedUpdate();
		}
	}
	
	/**
	 * Internal use
	 */
	public void masterStart()
	{
		for(int i = 0; i < engineObjects.size(); i++)
		{
			engineObjects.get(i).start();
		}
		
	}

	/**
	 * Called when the Object is initialized
	 */
	protected abstract void start();
	/**
	 * Called once per frame
	 */
	protected abstract void fixedUpdate();
	
	/**
	 * Internal use
	 */
	private void physicsTick()
	{
		for(Engine e : physicsObjects)
		{
			
		}
			
	}
	
	/**
	 * Internal use. Updates all timers in Engine.activeTimers
	 */
	private void updateTimers()
	{
		for(int i = 0; i < activeTimers.size(); i++)
		{
			//System.out.println(i);
			Timer t = activeTimers.get(i);
			t.ms += Engine.previousFrameTime;
			
			if(t.ms >= t.duration)
			{
				//System.out.println("this");
				t.expire();
				//System.out.println(activeTimers.size());
				activeTimers.remove(i);
				//System.out.println(activeTimers.size());
				i--;
			}
		}
	}
	
}
