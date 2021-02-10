package engine;

public class Text extends Renderable
{
	
	public String text;
	public int xOffset;
	public int yOffset;
	public Color color = new Color();
	
	public Text()
	{
		super();
		//Engine.renderQueue.renderQueue.add(this);
		text = "Sample text";
		xOffset = 0;
		yOffset = 0;
	}
	
	public Text set(String s)
	{
		text = s;
		return this;
	}
	
	public Text set(int x, int y)
	{
		this.xOffset = x;
		this.yOffset = y;
		return this;
	}
	
}
