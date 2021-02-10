package engine;

public class Vector3Int 
{
	public int x;
	public int y;
	public int z;
	
	public Vector3Int()
	{
		
	}
	
	public Vector3Int(int mag)
	{
		this.x = mag;
		this.y = mag;
		this.z = mag;
	}
	
	public Vector3Int(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	//internal use of Vector3. Wavefront OBJ vertex passed in
	public Vector3Int(String line)
	{
		String[] split = line.split(" ");
		
		x = Integer.parseInt(split[1].substring(0,1));
		y = Integer.parseInt(split[2].substring(0,1));
		z = Integer.parseInt(split[3].substring(0,1));
	}
	
	/**
	 * Returns a String in the format "(x, y, z)"
	 */
	public String toString()
	{
		return "(" + x + ", " + y + ", " + z + ")";
	}

}
