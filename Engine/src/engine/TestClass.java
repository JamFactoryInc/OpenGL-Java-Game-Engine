package engine;

import com.jogamp.newt.event.KeyEvent;

public class TestClass extends Engine
{
	Rigidbody rb;
	//Ray ray;
	@Override
	protected void start() 
	{
		
		OglObject object3d = parent.addProperty(OglObject.class);
		object3d.set("C:\\Users\\jam\\Downloads\\cube\\monkeyMatHD.obj");
		System.out.println(object3d.faces.size());
		
		
		//Material.materials.get(Material.materials.size()-1).mat_diffuse = new float[] {1,0,0,1f};

		//Material.materials.get(Material.materials.size()-1).mat_ambient = new float[] {0,0,0,0f};
		//Material.materials.get(Material.materials.size()-1).mat_ambient_color = new float[] {0,.1f,.1f,.1f};
		//Material.materials.get(Material.materials.size()-1).mat_specular = new float[] {1,1,1,.5f};
		
		//ray = parent.addProperty(Ray.class);
		
		//System.out.println("call");
		rb = parent.addProperty(Rigidbody.class);
		//System.out.println("FinishedTC");
		
		Engine.renderQueue.setLastInQueue(object3d);
		parent.transform.rotation = new Quaternion(new Vector3(0,1,0).normalize(), 10);
		
		//
		//parent.transform.rotation.add(new Quaternion(1,0,0,45));
		parent.transform.rotation.normalize();
		System.out.println(parent.transform.rotation.toString());

		
		
		//Engine.renderQueue.setLastInQueue(ray);
	}

	@Override
	protected void fixedUpdate() 
	{
		
		
		
		if(input.getKey(KeyEvent.VK_A))
		{
			//System.out.println(parent.transform.rotation.toString());
			//System.out.println(new Quaternion(0,1,0,1).toString());
			//parent.transform.rotation.w -= .01f;
			//System.out.println(parent.transform.rotation.toString());
			//System.out.println();
			//parent.transform.rotation.w -= .01f;
			//System.out.println(parent.transform.rotation.yawAxis.toString());
			//rb.velocity.x = -3;
			
			//1,0,0 -> 0,1,-1
			//0,1,0 -> 0,1,0
			//0,0,1 -> 1,0,0
			
			Quaternion aa = parent.transform.rotation.angleAxis();
			Vector3 axis = new Vector3(0,1,0).normalize();
			parent.transform.rotation.multiplyBy(new Quaternion(axis,-1));
		}
		 if(input.getKey(KeyEvent.VK_D))
		{
			
			
			 Quaternion aa = parent.transform.rotation.angleAxis();
				Vector3 axis = new Vector3(0,1,0).normalize();
				parent.transform.rotation.multiplyBy(new Quaternion(axis,1));
			//rb.velocity.x = 3;
		}
		else 
			;
			//rb.velocity.x = 0;
		
		if(input.getKey(KeyEvent.VK_W))
		{
			parent.transform.rotation.add(new Quaternion(0,1,0,45f));
			//parent.transform.rotation.rotateAround(parent.transform.rotation.rollAxis, 1);
			//rb.velocity.y= 3;
		}
		else if(input.getKey(KeyEvent.VK_S))
		{
			parent.transform.rotation.add(new Quaternion(0,1,0,-45f));
			//parent.transform.rotation.rotateAround(parent.transform.rotation.rollAxis, -1);
			//rb.velocity.y = -3;
		}
		else 
			;
			//rb.velocity.y = 0;
		
		if(input.getKey(KeyEvent.VK_Q))
		{
			rb.velocity.y = -1;
			//parent.transform.rotation.rotateAround(Vector3.forward, 1);
			//System.out.println(parent.transform.rotation.rollAxis.toString());
		}
		else if(input.getKey(KeyEvent.VK_E))
		{
			rb.velocity.y = 1;
			//parent.transform.rotation.rotateAround(Vector3.forward, -1);
		}
		else 
			rb.velocity.y = 0;

		Quaternion q = parent.transform.rotation.angleAxis();
		//ray.set(parent.transform.position, parent.transform.position.plus( q.times(q.inverse()).axis().clamp(3)));
		//System.out.println("loop");
		
			
	}
	
	public void print()
	{
		

	}
}
