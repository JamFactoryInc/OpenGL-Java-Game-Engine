package engine;

public class Camera extends GameObject
{
	public float fov;
	public Vector3 target;
	public Camera(Transform t, float fov)
	{
		transform = t;
		this.fov = fov;
		
		//target = Vector3.pointAt(start, target, maxDist)
	}
}
