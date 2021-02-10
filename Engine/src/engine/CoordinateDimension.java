package engine;

public class CoordinateDimension 
{
	public float x;
	public float y;
	public float z;
	public float w = Float.MIN_VALUE;
	
	/**
	 * internal use of Vector3. Wavefront OBJ vertex passed in
	 * @param line
	 */
		public CoordinateDimension(String line)
		{
			String[] split = line.split("\\s+");
			
			x = Float.parseFloat(split[1]);
			y = Float.parseFloat(split[2]);
			z = Float.parseFloat(split[3]);
			if(split.length > 4)
				w = Float.parseFloat(split[4]);
		}
}
