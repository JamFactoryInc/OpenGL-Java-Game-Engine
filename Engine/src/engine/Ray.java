package engine;

public class Ray extends Renderable
{
	public static float[] mat_emission = {1,1,1,1};
	
	Vector3 start;
	Vector3 end;
	Color color = Color.RED;
	public Ray()
	{
		
	}
	
	public void set(Vector3 start, Vector3 end)
	{
		this.start = start;
		this.end = end;
	}
	
	public void set(Vector3 start, Vector3 end, Color color)
	{
		this.start = start;
		this.end = end;
		this.color = color;
	}
}
