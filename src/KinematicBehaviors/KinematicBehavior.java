package KinematicBehaviors;

import Display.Display;
import Motion.KinematicMotion;
import Motion.Motion;
import MovingObject.MovingObject;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * Serves as a superclass for all kinematic behaviors
 * @author Phil Burney
 *
 */
public class KinematicBehavior {
	/** The moving object impacted by the kinematic behavior */
	protected MovingObject obj;
	/** The applet to be used */
	protected PApplet app;
	/**
	 * Constructor class for a Kinematic Behavior.  Also initializes object motion if there is none
	 * @param obj the object that is to be acted upon
	 * @param app the applet to be used 
	 */
	public KinematicBehavior(MovingObject obj, PApplet app) {
		
		this.obj = obj;
		this.app = app;
		PVector start = new PVector(Display.SCREEN_SIZE / 2, Display.SCREEN_SIZE / 2);
		PVector velocity = new PVector(0f, 0f);
		
		if(obj.getMotion() == null) {
			obj.setMotion( new KinematicMotion(start, 90f, velocity, PApplet.radians((700)) / (float)Display.FRAME_RATE));
			System.out.println("motion generated");
		}

		
	}
	public KinematicBehavior() {
	
	}


	/**
	 * The action that occurs on mouse press.  In this case, the location is selected
	 */
	public void onMousePress(float x, float y) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * This arrive algorithm uses one vector, and never updates that vector along the path.  Stops when the 
	 * satisfaction radius is approached
	 */
	public PVector behavior(Motion motion) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Method that imposes wandering behavior on the object
	 */
	public void behavior() {
		// TODO Auto-generated method stub
		
	}
	

}
