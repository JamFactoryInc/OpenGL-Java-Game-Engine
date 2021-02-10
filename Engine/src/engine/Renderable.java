package engine;

public class Renderable extends Property
{
	public int renderQueueIndex = -1;
	
	public Renderable()
	{
		renderQueueIndex = Engine.renderQueue.renderQueue.size();
		Engine.renderQueue.renderQueue.add(this);
	}
}
