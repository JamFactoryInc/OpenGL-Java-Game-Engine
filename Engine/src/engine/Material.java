package engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Material 
{
	public static List<Material> materials = new ArrayList<>();
	
	public String name;
	public int id;
	public float[] mat_ambient = {0,0,0,0};
	public float[] mat_ambient_color = {0,0,0,0};
	public float[] mat_diffuse = {1,1,1,1};
	public float[] mat_specular = {0,0,0,0};
	public float[] mat_emission = {0,0,0,0};
	public float shininess;
	public static Material defaultMaterial = new Material("default");
	static
	{
		defaultMaterial.mat_ambient = new float[] {0,0,0,0};
		defaultMaterial.mat_ambient_color = new float[] {0,0,0,0};
		defaultMaterial.mat_diffuse = new float[] {1,1,1,1};
		defaultMaterial.mat_specular = new float[] {0,0,0,0};
		defaultMaterial.mat_emission = new float[] {0,0,0,0};
	}
	
	public Material(String name)
	{
		id = materials.size();
		this.name = name;
		materials.add(this);
	}
	
	/**
	 * Finds the index of a material given its name
	 * @param name the name to search
	 * @return the index of the material. If none is found, returns -1
	 */
	public static int indexOf(String name)
	{
		for(int i = 0; i < materials.size(); i++)
			if(materials.get(i).name.equals(name))
				return i;
		return -1;
	}
	
	/**
	 * Takes in an mtl file
	 * @param filePath complete filepath to the desired mtl. Be sure to add the .mtl extension
	 * @return the number of materials added
	 */
	public static int add(String filePath)
	{
		File file = new File(filePath); 
		
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
		String content;
		String begWith;
		int currentMatIndex = 0;
		Material currentMaterial = materials.get(0);
		int matsAdded = 0;
		try 
		{
			while ((st = br.readLine()) != null) 
			{
				if(st.indexOf(' ') == -1 || st.length() == 0)
					continue;
				content = st.substring(st.indexOf(' '));
				begWith = st.substring(0,2);
				if(begWith.equals("ne"))
				{
					int index = indexOf(content);
					if(index == -1)
					{
						//System.out.println("Created mat: " + content);
						currentMaterial = new Material(content.substring(1));
						matsAdded++;
					}
					else 
					{
						currentMaterial = materials.get(index);
					}
				}
				else if(begWith.equals("Ns"))
				{
					//System.out.println("Shininess: " + content);
					currentMaterial.shininess = Float.parseFloat(content);
				}	
				
				else if(begWith.equals("Kd"))	
				{
					String[] split = content.split(" ");
					currentMaterial.mat_diffuse[1] = Float.parseFloat( split[1]);
					currentMaterial.mat_diffuse[2] = Float.parseFloat( split[2]);
					currentMaterial.mat_diffuse[3] = Float.parseFloat( split[3]);
					currentMaterial.mat_diffuse[0] = 1;
					//currentMaterial.mat_diffuse = new float[] {1,1,1,1};

				}
				else if(begWith.equals("Ka"))
				{
					
					String[] split = content.split(" ");
					//System.out.println("st: " + st);
					//System.out.println("split0: " + split[1]);
					//currentMaterial.mat_ambient_color[1] = Float.parseFloat( split[1]);
					//currentMaterial.mat_ambient_color[2] = Float.parseFloat( split[2]);
					//currentMaterial.mat_ambient_color[3] = Float.parseFloat( split[3]);
					currentMaterial.mat_ambient_color[0] = .1f;
					
				}
				else if(begWith.equals("Ks"))
				{

					String[] split = content.split(" ");
					currentMaterial.mat_specular[1] = Float.parseFloat( split[1])/(float)Math.log(currentMaterial.shininess);
					currentMaterial.mat_specular[2] = Float.parseFloat( split[2])/(float)Math.log(currentMaterial.shininess);
					currentMaterial.mat_specular[3] = Float.parseFloat( split[3])/(float)Math.log(currentMaterial.shininess);
					System.out.println(currentMaterial.mat_specular[1]);
					System.out.println(currentMaterial.mat_specular[2]);
					System.out.println(currentMaterial.mat_specular[3]);
					//currentMaterial.mat_specular[3] = -.0f;
				}
				else if(begWith.equals("Ke"))
				{
					String[] split = content.split(" ");
					//currentMaterial.mat_emission[1] = Float.parseFloat( split[1]);
					//currentMaterial.mat_emission[2] = Float.parseFloat( split[2]);
					//currentMaterial.mat_emission[3] = Float.parseFloat( split[3]);
					//currentMaterial.mat_emission[0] = 0;
				}
				else if(begWith.equals("Ni"))
				{
					
				}
				else if(begWith.equals("d "))
				{
					
				}
				else if(begWith.equals("il"))
				{
					
				}
				//System.out.println(currentMaterial.mat_specular[0] + ", "+currentMaterial.mat_specular[1] + ", "+currentMaterial.mat_specular[2] + ", "+currentMaterial.mat_specular[3]);

			}
			
			
			return matsAdded;
		} 
		catch (IOException e) 
		{
			
			e.printStackTrace();
			return matsAdded;
		} 
	}
}
