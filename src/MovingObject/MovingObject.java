// Java program to demonstrate 
// how to create an applet 
package MovingObject;

import Motion.DynamicMotion;
import Motion.KinematicMotion;
import Motion.Motion;
import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

// Creating a class which extends 
// the PApplet class 
public class MovingObject {
	/** The PApplet object to be used */
	PApplet app;
	/** The shape of the object to be used */
	PShape s;
	/** The Kinematic motion of the MovingObject */
	Motion motion;
	/** The size of the MovingObject */
	float size;
	/** The maximum speed that the object can move */
	public static final float MAX_SPEED = 20f;
	

	
	/**
	 * Constructor method for MovingObject that includes the applet and the 
	 * @param app the applet to be used 
	 */
	public MovingObject(PApplet app) {
		this.app = app;
	}
	/**
	 * Constructor method for MovingObject that includes the applet and the 
	 * data structure for the dynamic motion 
	 * @param app the applet to be used 
	 * @param dynMotion the DynamicMotion object to be used
	 */
	public MovingObject(PApplet app, Motion motion, float size) {
		this.app = app;
		this.motion = motion;
		this.size = size;
	}
	
	/**
	 * Draws the moving object
	 */
	public void updateObjKinematics(float x, float y, float angle) {
		// Push the matrix
		app.pushMatrix();
		// Translate and rotate the matrix to where the shape is located
		app.translate(x,y);
		app.rotate(angle);
		
		
		// Create the shape
		s = app.createShape();
		// Begin the shape
		s.beginShape();
		// Fill the shape with the best color
		app.fill(255, 255, 0);
		app.strokeWeight(1);
		// Create the triangle
		app.triangle(0,(size / 2) * -1, 0, size / 2, size, 0);
		// Create the circle 
		app.ellipse(0, 0 , size, size);
		// Shape is created
		
		s.endShape();
		
		app.popMatrix();

	}
	/**
	 * Returns the kinematic motion of the object
	 */
	public Motion getMotion() {
		return this.motion;
	}
	/** Sets the kinematic motion of the moving object
	 * 
	 * @param dynamicMotion the motion to set the object to
	 */
	public void setMotion(Motion motion) {
		this.motion = motion;
	}
	
	public float getSize() {
		return size;
	}
	
	public float setSize(float size) {
		return size;
	}
	


}