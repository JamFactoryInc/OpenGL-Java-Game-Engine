package engine;

public class ComplexNumber
{
	   //for real and imaginary parts of complex numbers
	   float r, i;
		
	   /**
	    * constructor to initialize the complex number
	    * @param r the real number
	    * @param i the imaginary coefficient
	    */
	   ComplexNumber(float r, float i)
	   {
		this.r = r;
		this.i = i;
	   }
		
	   /**
	    * adds two complex numbers
	    * @param c the other complex number to add
	    * @return a new instance as the result of the addition
	    */
	   public ComplexNumber plus(ComplexNumber c)
	   {
	        return new ComplexNumber(r + c.r, i + c.i);
	   }
	   
	   /**
	    * subtracts two complex numbers
	    * @param c the other complex number to subtract
	    * @return a new instance as the result of the subtraction
	    */
	   public ComplexNumber minus(ComplexNumber c)
	   {
	        return new ComplexNumber(r - c.r, i - c.i);
	   }
	   
	   /**
	    * multiplies two complex numbers
	    * @param c the other complex number to multiply
	    * @return a new instance as the result of the multiplication
	    */
	   public ComplexNumber times(ComplexNumber c)
	   {
		   return new ComplexNumber(r * c.r - i * c.i, i * c.r + r * c.i);
	   }
	   
	   /**
	    * multiplies by a number
	    * @param f the floating-point number to multiply by
	    * @return a new instance as the result of the multiplication
	    */
	   public ComplexNumber times(float f)
	   {
		   return new ComplexNumber(r * f, i * f);
	   }
	   
	   /**
	    * divides two complex numbers
	    * @param c the other complex number to divide by
	    * @return a new instance as the result of the division
	    */
	   public ComplexNumber dividedBy(ComplexNumber c)
	   {
		   return new ComplexNumber((r*c.r + i * c.i)/(c.r * c.r + c.i * c.i), (c.r * i + r * c.i)/(c.r * c.r + c.i * c.i));
	   }
	   
	   /**
	    * Squares the complex number
	    * @return a new instance as the square of this number
	    */
	   public ComplexNumber squared()
	   {
		   return times(this);
	   }
	   
	   /**
	    * Makes the real and imaginary components positive
	    * @return a new instance as the result of the operation
	    */
	   public ComplexNumber abs()
	   {
		   return new ComplexNumber(Math.abs(r), Math.abs(i));
	   }
	   
	   /**
	    * Finds the square root of the complex number
	    * @return a new instance as the square root of this number
	    */
	   public ComplexNumber sqrt()
	   {
		  return new ComplexNumber(Mathf.sqrt(0.5f * (r + Mathf.sqrt(r * r + i * i))),
				  Mathf.SQRT_2_OVER_2 * (i/Math.abs(i)) * Mathf.sqrt((Mathf.sqrt(r * r + i * i) - r)));
	   }
	   
	   /**
	    * Treats a complex number as a coordinate on the real-imaginary plane and rotates it about the angle theta
	    * @param theta the angle in degrees to rotate around
	    * @return the new complex number representing the rotated coordinate
	    */
	   public ComplexNumber rotate(float theta)
	   {
		   return this.times(new ComplexNumber((float)Math.cos(theta / Mathf.RAD2DEG), (float)Math.sin(theta / Mathf.RAD2DEG)));
	   }
	   
	   /**
	    * @return the complex number in the form "(r, i)" where r is the real number and i is the imaginary coefficient
	    */
	   public String toString()
	   {
		   if(i != 0)
			   return "(" + r + ", " + i + "i)";
		   return "" + r;
	   }
	   
	   
}