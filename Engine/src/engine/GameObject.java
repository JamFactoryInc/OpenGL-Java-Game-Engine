package engine;
import java.util.ArrayList;
import java.util.List;

public class GameObject 
{
	
	public boolean isActive;
	public List<Property> properties = new ArrayList<>();
	public Transform transform;
	
	/**
	 * Creates new gameobject and adds it to Engine.gameObjects
	 */
	public GameObject()
	{
		Engine.gameObjects.add(this);
	}
	
	/**
	 * Creates new gameobject and adds it to Engine.gameObjects
	 * @param t the transform of the new gameobject
	 */
	public GameObject(Transform t)
	{
		transform = t;
		Engine.gameObjects.add(this);
	}
	
	/**
	 * Adds a property to a gameObject instance. Usage :<p> <code> Rigidbody rb = sampleGameObject.addProperty(Rigidbody.class);</code><p> Parameters information cannot be added in this method. Store the returned value in a variable and use Property.set();
	 * @param propertyClass
	 * @return the property added.  
	 */
	public <T extends Property> T addProperty(Class<T> propertyClass)
	{
		try 
		{
			return addPropertySafe(propertyClass);
			
			//new Timer(4f, tc, "print");
		} 
		catch (IllegalAccessException e) 
		{
			e.printStackTrace();
		} 
		catch (InstantiationException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	private <T extends Property> T addPropertySafe(Class<T> propertyClass) throws IllegalAccessException, InstantiationException 
	{
	    if(propertyClass.getSuperclass() == Engine.class)
	    {
	    	Engine temp = Engine.createEngineObject(propertyClass.newInstance());
	    	temp.propertyId = properties.size();
	    	properties.add(temp);
	    	
	    	temp.parent = this;
	    		return (T)temp;
	    }
	    T temp = propertyClass.newInstance();
	    temp.propertyId = properties.size();
	    properties.add(temp);
	    temp.parent = this;
	    return (T)temp;

	}
	
	/**
	 * Destroys the gameobject
	 */
	public void destroy()
	{
		Engine.gameObjects.remove(this);
	}
	
	/**
	 * Destroys the gameobject after time t
	 * @param t time in seconds
	 */
	public void destroy(float t)
	{
		Engine.gameObjects.remove(this);
	}

	
	
	

}
