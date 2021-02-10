package engine;

public class Mathf 
{
	
	public static float PI = 3.1415927410125732421875f;
	public static float SQRT_2 = 1.41421353816986083984375f;
	public static float SQRT_2_OVER_2 = 0.707106781187f;
	public static float RAD2DEG = 57.2957795131f;
	public static long INFINITY = Long.MAX_VALUE;
	public static long NEGATIVE_INFINITY = Long.MIN_VALUE;
	
	/**
	 * Returns the square root. Only supports real numbers
	 * @param num a float greater than or equal to 0
	 * @return a the real square root of the input
	 */
	public static float sqrt(float num)
	{
		return(float)Math.sqrt(num);
		
	}
	
	/**
	 *  Returns the square root. Supports complex numbers/ the input of negative values. To derive the real component of the result, add <code>.r</code> to the result. Example: <code>float ex = Mathf.sqrtc(4).r;</code>
	 * @param num any floating-point value
	 * @return A the complex square root of the input. 
	 */
	public static ComplexNumber Sqrtc(float num)
	{
		if(num <= 0)
		{
			float abs = (float)Math.sqrt(Math.abs(num));
			return new ComplexNumber(0, abs);
		}
		return new ComplexNumber((float)Math.sqrt(num),0);
	}

}
