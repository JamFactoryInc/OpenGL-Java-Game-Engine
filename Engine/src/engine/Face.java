package engine;

public class Face
{
	public int v1;
	public int v2;
	public int v3;
	public int v4 = Integer.MIN_VALUE;
	
	public int vt1;
	public int vt2;
	public int vt3;
	public int vt4;
	
	public int vn1;
	public int vn2;
	public int vn3;
	public int vn4;
	
	public int materialIndex;
	
	/**
	 * internal use to store OBJ face values. Wavefront OBJ line and material index passed in
	 * @param line
	 * @param matIndex
	 */
		public Face(String line, int matIndex)
		{
			this.materialIndex = matIndex;
			
			String[] split = line.split(" ");
			
			String[] split1 = split[1].split("/");
			v1 = Integer.parseInt(split1[0]);
			if(split1[1].length() > 0)
				vt1 = Integer.parseInt(split1[1]);
			vn1 = Integer.parseInt(split1[2]);
			
			String[] split2 = split[2].split("/");
			v2 = Integer.parseInt(split2[0]);
			if(split2[1].length() > 0)
				vt2 = Integer.parseInt(split2[1]);
			vn2 = Integer.parseInt(split2[2]);
			
			String[] split3 = split[3].split("/");
			v3 = Integer.parseInt(split3[0]);
			if(split3[1].length() > 0)
				vt3 = Integer.parseInt(split3[1]);
			vn3 = Integer.parseInt(split3[2]);
					
			//v1 = Integer.parseInt(split[1].substring(0, split[1].indexOf('/')));
			//v2 = Integer.parseInt(split[2].substring(0, split[2].indexOf('/')));
			//v3 = Integer.parseInt(split[3].substring(0, split[3].indexOf('/')));
			if(split.length > 4)
			{
				String[] split4 = split[4].split("/");
				v4 = Integer.parseInt(split4[0]);
				if(split4[1].length() > 0)
					vt4 = Integer.parseInt(split4[1]);
				vn4 = Integer.parseInt(split4[2]);
				//v4 = Integer.parseInt(split[4].substring(0, split[4].indexOf('/')));
			}
		}
		
}
