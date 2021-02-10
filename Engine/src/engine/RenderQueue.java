package engine;

import java.util.ArrayList;
import java.util.List;

public class RenderQueue 
{
	public List<Renderable> renderQueue = new ArrayList<>();
	
	/**
	 * Sets the renderable to the last value in the render queue and removes it from its original index. Things close to the end of the render queue get rendered in front
	 * @param r
	 */
	public void setLastInQueue(Renderable r)
	{
		renderQueue.add(renderQueue.remove(r.renderQueueIndex));
	}
	
	/**
	 * Sets the renderable to the first value in the render queue and removes it from its original index. Things close to the end of the render queue get rendered in front
	 * @param r
	 */
	public void setFirstInQueue(Renderable r)
	{
		renderQueue.add(0, renderQueue.remove(r.renderQueueIndex));
	}
	
	/**
	 * Inserts the renderable into the render queue at the new index and removes it from its original index. Things close to the end of the render queue get rendered in front
	 * @param r
	 * @param i the index to insert at
	 */
	public void insertInQueue(Renderable r, int i) throws IndexOutOfBoundsException
	{
		renderQueue.add(i, renderQueue.remove(r.renderQueueIndex));
	}
	
	/**
	 * Inserts the renderable into the render queue before another renderable and removes it from its original index. Things close to the end of the render queue get rendered in front
	 * @param r
	 * @param other Renderable to insert before
	 */
	public void insertBeforeInQueue(Renderable r, Renderable other) throws IndexOutOfBoundsException
	{
		renderQueue.add(other.renderQueueIndex, renderQueue.remove(r.renderQueueIndex));
	}
	
	/**
	 * Inserts the renderable into the render queue after another renderable and removes it from its original index. Things close to the end of the render queue get rendered in front
	 * @param r
	 * @param other Renderable to insert after
	 */
	public void insertAfterInQueue(Renderable r, Renderable other) throws IndexOutOfBoundsException
	{
		renderQueue.add(other.renderQueueIndex + 1, renderQueue.remove(r.renderQueueIndex));
	}
	
	/**
	 * swaps the indeces of two Renderables
	 * @param r1
	 * @param r2
	 */
	public void swap(Renderable r1, Renderable r2)
	{
		renderQueue.remove(r1.renderQueueIndex);
		renderQueue.remove(r2.renderQueueIndex);
		renderQueue.add(r1.renderQueueIndex, r2);
		renderQueue.add(r2.renderQueueIndex, r1);
	}
}
