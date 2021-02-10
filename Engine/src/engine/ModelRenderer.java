package engine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.newt.event.KeyListener;
import com.jogamp.newt.event.WindowEvent;

import com.jogamp.newt.event.WindowUpdateEvent;
import com.jogamp.newt.event.KeyEvent;
import java.awt.event.WindowListener;
import java.nio.FloatBuffer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;

public class ModelRenderer implements GLEventListener
{

   public static DisplayMode dm, dm_old;
   private GLU glu = new GLU();
   public float rquad = 0;
   private static WindowListener wl;
   private static TextRenderer trenderer = new TextRenderer(new Font("SansSerif", Font.BOLD,36));
   private static JFrame frame;
      
   @Override
   public void display( GLAutoDrawable drawable ) 
   {
	   
	   
	   //KeyEvent p = createKey
	   //System.out.println(Engine.previousFrameTime);
	   
	   //long before = System.nanoTime();
	   
	   final GL2 gl = drawable.getGL().getGL2();
	   gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT );
	   gl.glLoadIdentity();
	   
	   
	   
	   // Rotate The Cube On X, Y & Z
	    
 
	   //giving different colors to different sides
       // Start Drawing The Cube
	   
	   for(Renderable renderObject : Engine.renderQueue.renderQueue)
	   {
		   if (renderObject instanceof Ray)
		   {
			   //System.out.println("render text");
			   drawRay(drawable, gl, (Ray)renderObject);
		   }
		   else if(renderObject instanceof OglObject)
		   {
			   //System.out.println("render 3d");
			   //System.out.println(renderObject.parent.transform.rotation.yaw + ", " + renderObject.parent.transform.rotation.yawAxis.toString());
			   //gl.glRotatef(renderObject.parent.transform.rotation.yaw, renderObject.parent.transform.rotation.yawAxis.x, renderObject.parent.transform.rotation.yawAxis.y, renderObject.parent.transform.rotation.yawAxis.z);
			   
			   gl.glEnable(GL2.GL_DEPTH_TEST);
			   gl.glEnable(GL2.GL_LIGHTING);
			   gl.glEnable(GL2.GL_LIGHT0);
			   //gl.glLightModeli(GL2.GL_LIGHT_MODEL_LOCAL_VIEWER, 2);
			   float[] position = { 3,3,3,0 };
			   gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, position, 0);

			   
			   gl.glEnd();
			   draw3DObjects(drawable, gl, (OglObject)renderObject);
			   
		   }
		   else if (renderObject instanceof Text)
		   {
			   //System.out.println("render text");
			   drawText(drawable, gl, (Text)renderObject);
		   }
		   
	   }
	   
	   
	   //draw2DObjects(drawable, gl);
	   

      gl.glEnd(); // Done Drawing The Quad
      gl.glFlush();
		
      //rquad -= 0.15f;
      
      //long after = System.nanoTime();
      //System.out.println("frametime: " + (after-before)/1000000f + "ms");
      //System.out.println("Theoretical FPS: " + 1000/((after-before)/1000000f));
   }
   
   public void drawRay(GLAutoDrawable drawable, GL2 gl, Ray ray)
   {
	   	
	   	gl.glPushMatrix();
	   	gl.glBegin (GL2.GL_LINES);
	   	gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_EMISSION, Ray.mat_emission, 1);
	   	gl.glColor3f(ray.color.r, ray.color.g, ray.color.b);
	   	//System.out.println(ray.start.toString());
	   	//System.out.println(ray.end.toString());
	   	//System.out.println();
	    gl.glVertex3f(ray.start.x,ray.start.y, ray.start.z);
	    gl.glVertex3f(ray.end.x,ray.end.y, ray.end.z);
	    gl.glPopMatrix();
	    gl.glEnd();
   }
   
   public void drawText(GLAutoDrawable drawable, GL2 gl, Text text)
   {
	   trenderer.beginRendering(drawable.getSurfaceWidth(), drawable.getSurfaceHeight());
	   trenderer.setColor(text.color.r, text.color.g, text.color.b, text.color.a);
	   trenderer.draw(text.text, (int)(text.parent.transform.position.x + text.xOffset), (int)(text.parent.transform.position.y + text.yOffset));
	   trenderer.endRendering();
   }
   
   public void draw2DObjects(GLAutoDrawable drawable, GL2 gl)
   {

	   gl.glBindTexture (GL2.GL_TEXTURE_2D, 13);
	   gl.glBegin ( GL2.GL_QUADS);
	   gl.glTexCoord2f (0.0f, 0.0f);
	   gl.glVertex3f (0.0f, 0.0f, 0.0f);
	   gl.glTexCoord2f (1.0f, 0.0f);
	   gl.glVertex3f (10.0f, 0.0f, 0.0f);
	   gl.glTexCoord2f (1.0f, 1.0f);
	   gl.glVertex3f (10.0f, 10.0f, 0.0f);
	   gl.glTexCoord2f (0.0f, 1.0f);
	   gl.glVertex3f (0.0f, 10.0f, 0.0f);
	   gl.glEnd ();
   }
   
   public void draw3DObjects(GLAutoDrawable drawable, GL2 gl, OglObject oglo)
   {
	   
	   
	   
	   float size = 0;
	   Face face;
	   Vector3 norm;
	   Material mat;
	   

	   
	   //for(OglObject oglo : OglObject.openGlObjects)
	   //{
		   gl.glLoadIdentity();
		   gl.glTranslatef( oglo.parent.transform.position.x,oglo.parent.transform.position.y,oglo.parent.transform.position.z ); 
		   
		   //System.out.println(oglo.parent.transform.rotation.w);
		   Quaternion q = oglo.parent.transform.rotation.angleAxis();
		   //q.w *=  Mathf.RAD2DEG;
		   //System.out.println("FINAL: " + q.toString());
		   gl.glRotatef( q.w,
				   q.x,
				   q.y,
				   q.z);
		   
		   /*
		   Vector3 cross = oglo.parent.transform.rotation.pitchAxis.cross(Vector3.right);
		   gl.glRotatef( Vector3.angle(oglo.parent.transform.rotation.pitchAxis, Vector3.right),
				   cross.x,
				   cross.y,
				   cross.z);
		   //System.out.println(oglo.parent.transform.rotation.pitchAxis.toString());
		   //System.out.println(Vector3.angle(oglo.parent.transform.rotation.pitchAxis, Vector3.right));
		   cross = oglo.parent.transform.rotation.rollAxis.cross(Vector3.forward);
		   gl.glRotatef( Vector3.angle(oglo.parent.transform.rotation.rollAxis, Vector3.forward),
				   cross.x,
				   cross.y,
				   cross.z);
		   

		   
		   cross = oglo.parent.transform.rotation.yawAxis.cross(Vector3.up);
		   gl.glRotatef( Vector3.angle(oglo.parent.transform.rotation.yawAxis, Vector3.up),
				   cross.x,
				   cross.y,
				   cross.z);
		   */

		   
		   //gl.glRotatef(90, 0, 1, 0);
		   //gl.glRotatef(45, 1, 0, 0);
	      // Rotate The Cube On X, Y & Z
		   //gl.glRotatef(oglo.parent.transform.rotation.angle, oglo.parent.transform.rotation.axis.x,oglo.parent.transform.rotation.axis.y,oglo.parent.transform.rotation.axis.z); 
		   

		   
		   for(int i = 0; i < oglo.faces.size(); i++)
		   {
    		  	try 
    		  	{
    		  		mat = Material.materials.get( oglo.faces.get(i).materialIndex);
    		  		
    		  		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT, mat.mat_ambient_color, 1);
    		        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_DIFFUSE, mat.mat_diffuse, 1);
    		        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, mat.mat_specular, 1);
    		        gl.glMaterialf(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, mat.shininess);
    		        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_EMISSION, mat.mat_emission, 1);
    		        
    		        //System.out.println(mat.name);
    		  		if(oglo.faces.get(i).v4 == Integer.MIN_VALUE)
    		  		{
    		  			size = i/(float)oglo.faces.size();
    		  			gl.glBegin(GL2.GL_TRIANGLES);
    		  			face = oglo.faces.get(i);
    		  			
    		  			CoordinateDimension v1 = oglo.vertices.get(face.v1 -1);
        		  		CoordinateDimension v2 = oglo.vertices.get(face.v2 -1);
        		  		CoordinateDimension v3 = oglo.vertices.get(face.v3 -1);
        		  		
        		  		norm = oglo.normals.get(face.vn1-1);
    		  			gl.glNormal3d(norm.x, norm.y, norm.z);
        		  		gl.glVertex3f(v1.x, v1.y, v1.z); // Top Right Of The Quad (Top)
        		  		
        		  		norm = oglo.normals.get(face.vn2-1);
    		  			gl.glNormal3d(norm.x, norm.y, norm.z);
        		  		gl.glVertex3f(v2.x, v2.y, v2.z); // Top Left Of The Quad (Top)
        		  		
        		  		norm = oglo.normals.get(face.vn3-1);
    		  			gl.glNormal3d(norm.x, norm.y, norm.z);
        		  		gl.glVertex3f( v3.x, v3.y, v3.z ); // Bottom Left Of The Quad (Top)
        		  		gl.glColor3f(.5f,.5f,.5f);
        		  		gl.glEnd();
    		  		}
    		  		else 
    		  		{
    		  			gl.glBegin(GL2.GL_QUADS);
    		  			face = oglo.faces.get(i);
    		  			CoordinateDimension v1 = oglo.vertices.get(face.v1 -1);
        		  		CoordinateDimension v2 = oglo.vertices.get(face.v2 -1);
        		  		CoordinateDimension v3 = oglo.vertices.get(face.v3 -1);
        		  		CoordinateDimension v4 = oglo.vertices.get(face.v4 -1);
        		  		
        		  		norm = oglo.normals.get(face.vn1-1);
    		  			gl.glNormal3d(norm.x, norm.y, norm.z);
        		  		gl.glVertex3f(v1.x, v1.y, v1.z); // Top Right Of The Quad (Top)
        		  		
        		  		norm = oglo.normals.get(face.vn2-1);
    		  			gl.glNormal3d(norm.x, norm.y, norm.z);
        		  		gl.glVertex3f(v2.x, v2.y, v2.z); // Top Left Of The Quad (Top)
        		  		
        		  		norm = oglo.normals.get(face.vn3-1);
    		  			gl.glNormal3d(norm.x, norm.y, norm.z);
        		  		gl.glVertex3f( v3.x, v3.y, v3.z ); // Bottom Left Of The Quad (Top)
        		  		
        		  		norm = oglo.normals.get(face.vn4-1);
    		  			gl.glNormal3d(norm.x, norm.y, norm.z);
        		  		gl.glVertex3f( v4.x, v4.y, v4.z );
        		  		gl.glColor3f(size, size, size);
        		  		gl.glEnd();
					}
    		  		
    		  		
    		  		
    		  	} 
    		  	catch (Exception e) 
    		  	{
    		  		System.out.println("Could not load face " + oglo.faces.get(i).toString() + " due to " + e.getMessage());
    		  	}
    	  }
		   
		  
		   
		   
      //}
   }
   

   public void dispose( GLAutoDrawable drawable ) 
   {
	   
      
   }
   
   @Override
   public void init( GLAutoDrawable drawable ) 
   {
      final GL2 gl = drawable.getGL().getGL2();
      gl.glShadeModel( GL2.GL_SMOOTH );
      gl.glClearColor( 0f, 0f, 0f, 0f );
      gl.glClearDepth( 1.0f );
      gl.glEnable( GL2.GL_DEPTH_TEST );
      gl.glDepthFunc( GL2.GL_LEQUAL );
      gl.glHint( GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST );
   }
      
   @Override
   public void reshape( GLAutoDrawable drawable, int x, int y, int width, int height ) 
   {
	
      // TODO Auto-generated method stub
      final GL2 gl = drawable.getGL().getGL2();
      if(height == 0) 
    	  height = 1;
			
      final float h = ( float ) width / ( float ) height;
      gl.glViewport( 0, 0, width, height );
      gl.glMatrixMode( GL2.GL_PROJECTION );
      gl.glLoadIdentity();
		
      glu.gluPerspective( 70, h, 1.0, 100.0 );
      glu.gluLookAt(0,0,0,0,0,-8,0,1,0);
      gl.glMatrixMode( GL2.GL_MODELVIEW );
      gl.glLoadIdentity();
   }
   

      
   public static ModelRenderer run() 
   {
	
	   wl = new WindowListener() {
		
		@Override
		public void windowOpened(java.awt.event.WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowIconified(java.awt.event.WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowDeiconified(java.awt.event.WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowDeactivated(java.awt.event.WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowClosing(java.awt.event.WindowEvent e) {
			// TODO Auto-generated method stub
			System.exit(1);
		}
		
		@Override
		public void windowClosed(java.awt.event.WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowActivated(java.awt.event.WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
	};
	   
      final GLProfile profile = GLProfile.get( GLProfile.GL2 );
      GLCapabilities capabilities = new GLCapabilities( profile );
      
      // The canvas
      final GLCanvas glcanvas = new GLCanvas( capabilities );
      glcanvas.addKeyListener(Engine.input);
      ModelRenderer renderer = new ModelRenderer();
		
      glcanvas.addGLEventListener(  renderer);
      glcanvas.setSize( Engine.windowWidth, Engine.windowHeight );
		
      frame = new JFrame ( "Render" );
      frame.getContentPane().add( glcanvas );
      frame.setSize( frame.getContentPane().getPreferredSize() );
      frame.setVisible( true );
      final FPSAnimator animator = new FPSAnimator(glcanvas, Engine.maxFrameRate,true);
      animator.start();

      
      
      
      /////////////////////////////
      Border raisedBorder = new EtchedBorder(EtchedBorder.RAISED);
      LineBorder lineBorder = new LineBorder(Color.red);
      TitledBorder titleBorder = new TitledBorder("Demo Title");
      Border border = BorderFactory.createCompoundBorder(lineBorder, titleBorder);
      JButton raisedButton = new JButton("Raised Border");
      raisedButton.setBorder(raisedBorder);
      JButton button = new JButton("Button with two borders");
      button.setBorder(border);
      Cursor cursor = button.getCursor();
      button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
      Container contentPane = frame.getContentPane();
      contentPane.add(raisedButton,BorderLayout.WEST);
      contentPane.add(button,BorderLayout.EAST);
      //frame1.setSize(600, 300);
      
      //frame.add(new Text());
      frame.setVisible(true);
      ///////////////////////////////////////
      
      
      
      frame.addWindowListener(wl);
      
      return renderer;
   }


	
}