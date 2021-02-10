package engine;

import java.io.Console;

public class Vector3
{
	public float x = 0;
	public float y = 0;
	public float z = 0;
	
	/**
	 * 0,0,-1
	 */
	public static final Vector3 back = new Vector3(0,0,-1);
	
	/**
	 * 0,0,1
	 */
	public static final Vector3 forward = new Vector3(0,0,1);
	
	/**
	 * 0, -1, 0
	 */
	public static final Vector3 down = new Vector3(0,-1,0);
	
	/**
	 * -1, 0, 0
	 */
	public static final Vector3 left = new Vector3(-1,0,0);
	
	/**
	 * 1, 1, 1
	 */
	public static final Vector3 one = new Vector3(1,1,1);
	
	/**
	 * 1, 0, 0
	 */
	public static final Vector3 right = new Vector3(1,0,0);
	
	/**
	 * 0, 1, 0
	 */
	public static final Vector3 up = new Vector3(0,1,0);
	
	/**
	 * 0, 0, 0
	 */
	public static final Vector3 zero = new Vector3(0,0,0);
	
	public Vector3()
	{
		
	}
	
	public Vector3(float mag)
	{
		this.x = mag;
		this.y = mag;
		this.z = mag;
	}
	
	public Vector3(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3(String s)
	{
		String[] sarr = s.split(" ");
		try 
		{
			x = Float.parseFloat(sarr[1]); 
			y = Float.parseFloat(sarr[2]); 
			z = Float.parseFloat(sarr[3]); 
		} 
		catch (Exception e) 
		{
			System.out.println("Could not parse normal " + s);
		}
	}
	
	
	
	/**
	 * @return The magnitude of the vector
	 */
	public float magnitude()
	{
		return (float)Math.sqrt(x*x + y*y + z*z);
	}
	
	/**
	 * Assigns the vector with the appropriate x y and z values to conform to the desired magnitude while keeping the same unit vector
	 * @param newMagnitude The new magnitude to clamp the vector to
	 * @return The new value of the vector
	 */
	public Vector3 clamp(float newMagnitude)
	{
		set(this.dividedBy(magnitude()).times(newMagnitude));
		return this;
	}
	
	/**
	 * Derives the unit vector. Equivalent to calling defineMagnitude(1f)
	 * @return The new value of the vector
	 */
	public Vector3 normalize()
	{
		this.dividedBy(magnitude());
		return this;
	}
	
	/**
	 * Returns the result of rotating the instance Vector around <code>axis</code> by an angle
	 * @param axis the axis to rotate about
	 * @param angle the angle to rotate
	 * @return the resulting vector
	 */
	public Vector3 rotatedAround(Vector3 axis, float angle)
	{
		angle/= Mathf.RAD2DEG;
		return this.times
				(
						(float)Math.cos
						(
								angle
						)
				)
				.plus
				(
						axis.cross
						(
								this
						)
						.times
						(
								(float)Math.sin
								(
										angle
								)
						)
				)
				.plus
				(
						axis.times
						(
								axis.dot
								(
										this
								)
						)
						.times
						(
								1 - (float)Math.cos
												(
														angle
												)
						)
				)
				.clamp
				(
						this.magnitude()
				);
	}
	

	
	/**
	 * Adds value to all components of a Vector and returns a new vector
	 * @param f value to add
	 * @return the resulting Vector
	 */
	public Vector3 plus(float f)
	{
		return new Vector3(x + f, y + f, z + f);
	}
	
	/**
	 * Adds other Vector to this Vector
	 * @param other Vector to add
	 * @return the new value of this Vector
	 */
	public Vector3 add(Vector3 other)
	{
		x += other.x;
		y += other.y;
		z += other.z;
		return this;
	}
	
	/**
	 * Adds a Vector to another
	 * @param other vector to add
	 * @return the resulting Vector
	 */
	public Vector3 plus(Vector3 other)
	{
		return new Vector3(x + other.x, y + other.y, z + other.z);
	}
	
	
	
	/**
	 * Subtracts value from all components of a Vector
	 * @param f value to subtract
	 * @return the resulting Vector
	 */
	public Vector3 minus(float f)
	{
		return new Vector3(x - f, y - f, z - f);
	}
	
	/**
	 * Subtracts Vector from another
	 * @param other Vector to subtract
	 * @return the resulting Vector
	 */
	public Vector3 minus(Vector3 other)
	{
		return new Vector3(x - other.x, y - other.y, z - other.z);
	}
	
	/**
	 * Divides all components of a Vector by the value
	 * @param f value to divide by
	 * @return the resulting Vector
	 */
	public Vector3 dividedBy(float f)
	{
		return new Vector3(x / f, y / f, z / f);
	}
	
	/**
	 * Multiplies all components of a Vector by the value
	 * @param f value to multiply by
	 * @return the resulting Vector
	 */
	public Vector3 times(float f)
	{
		return new Vector3(x * f, y * f, z * f);
	}
	
	/**
	 * Gets the dot product of the instance Vector and another
	 * @param other the second vector to find the dot product
	 * @return the resulting dot product
	 */
	public float dot(Vector3 other)
	{
		return x * other.x + y * other.y + z * other.z;
	}
	
	/**
	 * Gets the cross product of the instance Vector and another
	 * @param other the second vector to find the cross product
	 * @return the resulting cross product
	 */
	public Vector3 cross(Vector3 other)
	{
		return new Vector3(this.y * other.z - other.y * this.z, -1 * (this.x * other.z - other.x * this.z), this.x * other.y - other.x * this.y);

	}
	
	/**
	 * Checks for equivalence
	 * @param other the Vector to check against
	 * @return the equivalence of the two vectors
	 */
	public boolean equals(Vector3 other)
	{
		return x == other.x && y == other.y && z == other.z;
	}
	
	/**
	 * Sets the values of a Vector
	 * @param x the new X value
	 * @param y the new Y value
	 * @param z the new Z value
	 * @return the new value of the vector
	 */
	public Vector3 set(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}
	
	/**
	 * Sets the values of a Vector
	 * @param other the new value of the vector
	 * @return the new value of the vector
	 */
	public Vector3 set(Vector3 other)
	{
		x = other.x;
		y = other.y;
		z = other.z;
		return this;
	}
	
	/**
	 * Returns a String in the format "(x, y, z)"
	 */
	public String toString()
	{
		return "(" + x + ", " + y + ", " + z + ")";
	}
	
	/**
	 * Finds the angle in degrees between two vectors
	 * @param v1 the first Vector
	 * @param v2 the second Vector
	 * @return the angle in degrees between the two Vectors
	 */
	public static float angle(Vector3 v1, Vector3 v2)
	{
		return (float)Math.acos(v1.dot(v2)/(v1.magnitude() * v2.magnitude())) * Mathf.RAD2DEG;
	}
	
	/**
	 * Finds the distance between two vectors
	 * @param v1 the first Vector
	 * @param v2 the second Vector
	 * @return the distance between the two vectors
	 */
	public static float distance(Vector3 v1, Vector3 v2)
	{
		return v1.minus(v2).magnitude();
	}
	
	/**
	 * Creates a vector of magnitude <code>maxDist</code> pointing from <code>start</code> to <code>target</code> unless <code>maxDist</code> is greater than the distance, in which case the distance is the new Vector's magnitude
	 * @param start the coordinate to originate from
	 * @param target the coordinate to point to
	 * @param maxDist the maximum magnitude of the returned vector
	 * @return a vector pointing from <code>start</code> to <code>target</code> clamped to <code>maxDist</code>
	 */
	public static Vector3 pointAt(Vector3 start, Vector3 target, float maxDist)
	{
		Vector3 vect = target.minus(start);
		float mag = vect.magnitude();
		if(mag > maxDist)
			vect.clamp(maxDist);
		return vect;
	}
	
	public void print(float f)
	{
		System.out.println(f);
	}
	
}
