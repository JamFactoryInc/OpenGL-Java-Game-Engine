package engine;

public class Transform 
{

	public Vector3 position;
	public Vector3 scale;
	public Quaternion rotation;
	public Transform parent;
	
	public Transform()
	{
		position = Vector3.zero;
		scale = Vector3.one;
		rotation = Quaternion.none;
		
	}
	
	public Transform(Vector3 position)
	{
		this.position = position;
		scale = Vector3.one;
		rotation = Quaternion.none;
		
	}
	
	public Transform(Vector3 position, Quaternion rotation) 
	{
		scale = Vector3.one;
		this.position = position;
		this.rotation = rotation;
	}
	
	public Transform(Vector3 position, Quaternion rotation, Vector3 scale) 
	{
		this.scale = scale;
		this.position = position;
		this.rotation = rotation;
	}
	
}
