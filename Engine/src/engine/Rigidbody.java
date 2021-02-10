package engine;

public class Rigidbody extends Engine
{

	public Vector3 velocity;
	public float mass = 1;
	public Vector3 origin;
	public Vector3 acceleration;
	
	public Rigidbody()
	{
		Engine.physicsObjects.add(this);
		velocity = new Vector3(0);
		origin = new Vector3(0);
		acceleration = new Vector3(0);
	}
	
	public void set(Vector3 velocity, Vector3 acceleration)
	{
		this.velocity = velocity;
		this.acceleration = acceleration;
	}
	
	public void tick()
	{
		velocity.add(acceleration.times(.01f));
		parent.transform.position.add(velocity.times(.01f));
		//System.out.println(parent.transform.position.toString());
	}

	@Override
	protected void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void fixedUpdate() {
		// TODO Auto-generated method stub
		tick();
	}

	
}
