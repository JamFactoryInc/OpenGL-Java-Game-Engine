package engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OglObject extends Renderable
{
	public static List<OglObject> openGlObjects = new ArrayList<>();
	
	public List<CoordinateDimension> vertices = new ArrayList<>();
	
	public List<Vector3> normals = new ArrayList<>();
	
	public List<Face> faces = new ArrayList<>();

	
	public OglObject()
	{
		super();
	}
	
	/**
	 * Takes in an obj file
	 * @param filePath complete filepath to the desired obj. Be sure to add the .obj extension
	 * @return if the object was imported successfully 
	 */
	public boolean set(String filePath)
	{
		File file = new File(filePath); 
		  
		openGlObjects.add(this);
		
		BufferedReader br = null;
		try 
		{
			br = new BufferedReader(new FileReader(file));
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		  
		String st; 
		String begWith;
		int currentMatIndex = 0;
		try 
		{
			while ((st = br.readLine()) != null) 
			{
				if(st.length() == 0)
					continue;
				begWith = st.substring(0,2);
				if(st.startsWith("f"))
				{
					//System.out.println(st);
					openGlObjects.get(openGlObjects.size()-1).faces.add(new Face(st, currentMatIndex));
				}
				else if(begWith.equals("v "))
					openGlObjects.get(openGlObjects.size()-1).vertices.add(new CoordinateDimension(st));
				else if(begWith.equals("vn"))
					openGlObjects.get(openGlObjects.size()-1).normals.add(new Vector3(st));
				else if(begWith.equals("us"))
				{
					//System.out.println("new material");
					System.out.println(st.substring(st.indexOf(' ') + 1));
					System.out.println(Material.indexOf(st.substring(st.indexOf(' ') + 1)));
					String matName = st.substring(st.indexOf(' ') + 1);
					int index = Material.indexOf(matName);
					if(index != -1)
						currentMatIndex = index;
					else 
					{
						currentMatIndex = new Material(matName).id;
						System.out.println("I: " + currentMatIndex);
					}
					
				}
			}
			return true;
		} 
		catch (IOException e) 
		{
			
			e.printStackTrace();
			return false;
		} 
	}
	
}
