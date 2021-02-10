package engine;



public class Main
{
	
	static Text t;
	static DummyExtender de;

	public static void main(String[] args) 
	{
		de = (DummyExtender)Engine.createEngineObject(new DummyExtender());

		//new Timer(4f, tc, "print");
		
		//new Invoker(up, "print", new Class<?>[] {float.class}, new Object[] {1f}, 1f);
		
		beforeStart();

		de.masterStart();

		afterStart();
		
		loop();

	}
	
	public static void beforeStart()
	{
		GameObject go = new GameObject(new Transform(new Vector3(0,0,-8), new Quaternion(0,1,0,0)));
		 
		go.addProperty(TestClass.class);
		
		Material.add("C:\\Users\\jam\\Downloads\\cube\\monkeyMatHD.mtl");
		//Material.add("C:\\Users\\jam\\Downloads\\octane-rocket-league\\source\\Octane\\Octane.mtl");
		
		GameObject g1 = new GameObject(new Transform(new Vector3(0,-2,-8), new Quaternion(0,1,0,-90)));
		
		
		OglObject object3d = g1.addProperty(OglObject.class);
		object3d.set("C:\\Users\\jam\\Downloads\\cube\\backdrop.obj");
		g1.addProperty(TextMod.class);
	}
	
	public static void afterStart()
	{
		
		
		ModelRenderer renderer = ModelRenderer.run();
	}
	
	public static void loop()
	{
		try 
		{
	        while (true) 
	        {
	        	Engine.currentFrameTime = System.nanoTime();

	        	Engine.secondsSinceLaunch += Engine.previousFrameTime/1000000000d;

	        	//renderer.rquad = (float)Math.sin(f)/3;
	            //System.out.println(new Date());
	        	de.masterFixedUpdate();
	            java.lang.Thread.sleep(1);
	        	Engine.previousFrameTime = System.nanoTime() - Engine.currentFrameTime;
	        	
	        }
	    } 
		catch (Exception e) 
		{
	        e.printStackTrace();
	    }
	}
}

