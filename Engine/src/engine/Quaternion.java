package engine;

public class Quaternion 
{
	public float x;
	public float y;
	public float z;
	public float w;
	
	/**
	 * 0 degrees about the y axis
	 */
	public static Quaternion none = new Quaternion();
	
	/**
	 * Value of default quaternion is 0 degrees about the y axis
	 */
	public Quaternion()
	{
		this(0,1,0,0f);
	}
	
	/**
	 * Creates a new Quaternion with the axis and angle parameters. The values stored in the quaternion will be different than these.
	 * @param axis
	 * @param angle
	 */
	public Quaternion(Vector3 axis, float angle)
	{
		float s = (float)Math.sin(angle / Mathf.RAD2DEG/2);
		x = axis.x * s;
		y = axis.y * s;
		z = axis.z * s;
		w = (float)Math.cos(angle / Mathf.RAD2DEG /2);

	}
	
	/**
	 * Creates a new Quaternion with the axis and angle parameters. The values stored in the quaternion will be different than these.
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	public Quaternion(float x, float y, float z, float w)
	{

		float s = (float)Math.sin(w / Mathf.RAD2DEG/2);
		this.x = x * s;
		this.y = y * s;
		this.z = z * s;
		//System.out.println(Math.cos(w / Mathf.RAD2DEG /2));
		this.w = (float)Math.cos(w / Mathf.RAD2DEG /2);

	}
	
	/**
	 * Adds two quaternions
	 * @param q the other quaternion to add
	 * @return the resulting quaternion
	 */
	public Quaternion plus(Quaternion q)
	{
		return new Quaternion(x + q.x, y + q.y, z +q.z, w + q.w).normalize();
	}
	
	/**
	 * Sets the axis and angle parameters of the quaternion. The values stored in the quaternion will be different than these.
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	public void set(float x, float y, float z, float w)
	{
		System.out.println("INPUT: "  + x + ", " + y + ", " + z + ", " + w);
		float s = (float)Math.sin(w / Mathf.RAD2DEG/2);
		this.x = x * s;
		this.y = y * s;
		this.z = z * s;
		//System.out.println(Math.cos(w / Mathf.RAD2DEG /2));
		this.w = (float)Math.cos(w / Mathf.RAD2DEG /2);
		normalize();
	}
	
	/**
	 * Directly sets the value of the quaternion. Only use if you know what you're doing
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 * @return the new value of the quaternion
	 */
	public Quaternion setRaw(float x, float y, float z, float w)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
		return this;
	}
	
	/**
	 * adds the components of two quaternions. Modifies this quaternion
	 * @param q the other quaternion to add
	 */
	public void add(Quaternion q)
	{
		x += q.x;
		y += q.y;
		z += q.z;
		w += q.w;
		normalize();
	}
	
	public Quaternion times(Quaternion q)
	{
		return new Quaternion(
				w * q.x + x * q.w - y * q.z + z * q.y,
				w * q.y + x*q.z + y * q.w - z * q.x,
				w * q.z - x * q.z + y * q.x + z * q.w,
				w * q.w - x * q.x - y * q.y - z *q.z);	}
	
	/**
	 * Multiplies two quaternions using FOIL
	 * @param q the other quaternion to multiply by
	 * @return the resulting quaternion
	 */
	public Quaternion multiplyBy(Quaternion q)
	{
		
		setRaw(
				w * q.x + x * q.w + y * q.z - z * q.y,
				w * q.y + y*q.w + z * q.x - x * q.z,
				w * q.z + z * q.w + x * q.y - y * q.x,
				w * q.w - x * q.x - y * q.y - z *q.z);
		normalize();
		return this;
	}
	
	/**
	 * 
	 * @return the conjugate of the quaternion
	 */
	public Quaternion inverse()
	{
		return new Quaternion(-x, -y ,-z ,w).normalize();
	}
	
	/**
	 * Rotates a vector using a quaternion
	 * @param vect the vector to rotate
	 * @param rot the rotation quaternion
	 * @return the rotated vector
	 */
	public static Vector3 rotateVector(Vector3 vect, Quaternion rot)
	{
		float mag = vect.magnitude();
		vect.normalize();
		Quaternion q = new Quaternion().setRaw(vect.x, vect.y, vect.z, 0);
		return rot.times(rot.inverse()).times(q).axis().times(mag);
	}
	
	/**
	 * @return the Quaternion in the format "(w, y, z, w)"
	 */
	public String toString()
	{
		return "" + x + ", " + y + ", " + z + ", " + w + "";
	}
	
	/**
	 * Does not actually return a quaternion but uses it as a means to represent an angle axis. Angle w about Axis x,y,z 
	 * @return the angle-axis representation of the quaternion
	 */
	public Quaternion angleAxis()
	{
		if(w > 1)
			normalize();
		float newAngle =2* (float)Math.acos(w);
		float s = Mathf.sqrt(1-w *w);
		//.out.println(newAngle);
		///System.out.println(newAngle  * Mathf.RAD2DEG);
		//System.out.println();
		if(s < 0.001)
		{
			return new Quaternion().setRaw(x, y, z, newAngle * Mathf.RAD2DEG);
		}
		return new Quaternion().setRaw(x, y, z, newAngle * Mathf.RAD2DEG);
	}
	
	/**
	 * 
	 * @return the first 3 components of the Quaternion as a Vector
	 */
	public Vector3 axis()
	{
		if(w > 1)
			normalize();

		//.out.println(newAngle);
		///System.out.println(newAngle  * Mathf.RAD2DEG);
		//System.out.println();
		return new Vector3(x, y, z);
	}
	

	
	public float magnitude()
	{
		return Mathf.sqrt(w*w + x*x + y*y + z*z);
	}
	
	public Quaternion normalize()
	{
		float norm = magnitude();
		w/= norm;
		x /= norm;
		y /= norm;
		z /= norm;
		return this;
	}
}
