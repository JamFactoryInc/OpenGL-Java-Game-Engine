package engine;

public class Color 
{
	public static final Color RED = new Color(1,0,0);
	public static final Color GREEN = new Color(0,1,0);
	public static final Color BLUE = new Color(0,0,1);
	public static final Color WHITE = new Color(1,1,1);
	public static final Color BLACK = new Color(0,0,0);
	public float r = 1;
	public float g = 1;
	public float b = 1;
	public float a = 1;
	
	public Color()
	{
		
	}
	
	/**
	 * Creates a grayscale value 0-1
	 * @param brightness grayscale value
	 */
	public Color(float brightness)
	{
		if(brightness > 1)
			brightness = 1;
		else if(brightness < 0)
			brightness = 0;
		
		r = brightness;
		g = brightness;
		b = brightness;
	}
	
	/**
	 * Creates a new color with rgb values 0-1
	 * @param r red
	 * @param g green
	 * @param b blue
	 */
	public Color(float r, float g, float b)
	{
		
		if(r > 1)
			r = 1;
		else if(r < 0)
			r = 0;
		
		if(g > 1)
			g = 1;
		else if(g < 0)
			g = 0;
		
		if(g > 1)
			g = 1;
		else if(g < 0)
			g = 0;
		
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	
	/**
	 * Creates a new color with rgba values 0-1
	 * @param r red
	 * @param g green
	 * @param b blue
	 * @param a alpha
	 */
	public Color(float r, float g, float b, float a)
	{
		this(r,g,b);
		if(a > 1)
			a = 1;
		else if(a < 0)
			a = 0;
		this.a = a;
	}
	
	/**
	 * Sets the values of the color
	 * @param r red
	 * @param g green
	 * @param b blue
	 * @return the modified color
	 */
	public Color set(float r, float g, float b)
	{
		if(r > 1)
			r = 1;
		else if(r < 0)
			r = 0;
		
		if(g > 1)
			g = 1;
		else if(g < 0)
			g = 0;
		
		if(g > 1)
			g = 1;
		else if(g < 0)
			g = 0;
		
		this.r = r;
		this.g = g;
		this.b = b;
		
		return this;
	}
	
	/**
	 * Sets the values of the color
	 * @param r red
	 * @param g green
	 * @param b blue
	 * @param a alpha
	 * @return the modified color
	 */
	public Color set (float r, float g, float b, float a)
	{
		this.a = a;
		set(r,g,b);
		return this;
	}
	
}
