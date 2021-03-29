package KinematicBehaviors;

import MovingObject.MovingObject;
import Display.Display;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * This class implements the kinematic wander behavior
 * @author Phil Burney
 *
 */
public class KinematicWander extends KinematicBehavior {
	public static final float SAT_RAD = 2;
	public static final float APPROACH_RAD = 100;
	PVector dest = obj.getMotion().getPosition();
	/**
	 * Constructor for KinematicWander
	 * @param obj object to exhibit the behavior
	 * @param app applet to be used
	 */
	public KinematicWander(MovingObject obj, PApplet app) {
		super(obj, app);
	}
	/**
	 * Method that imposes wandering behavior on the object
	 */
	@Override
	public void behavior() {
		
		
		if (obj.getMotion().getVectorToDest(dest).mag() < SAT_RAD) {
			dest = getNewLocation();
		}
		float x = obj.getMotion().getPosition().x;
		float y = obj.getMotion().getPosition().y;
		
		obj.getMotion().kinematicUpdate();
		obj.updateObjKinematics(x, y, obj.getMotion().getOrientation());
		
		if (obj.getMotion().getVectorToDest(dest).mag() < APPROACH_RAD) {
			obj.getMotion().setVelocity(obj.getMotion().getVectorToDest(dest).mult(0.5f).limit(MovingObject.MAX_SPEED)); 
			
		} else {
			obj.getMotion().setVelocity(obj.getMotion().getVectorToDest(dest).setMag(MovingObject.MAX_SPEED));
		}
	}
	/**
	 * This method is a helper method for the behavior method. Generates a random location for the object to go to 
	 * @return location that is within the bounds of the screen
	 */
	private PVector getNewLocation() {
		float x = app.random(obj.getSize() / 2,  Display.SCREEN_SIZE - obj.getSize()  / 2);
		float y = app.random(obj.getSize()  / 2,  Display.SCREEN_SIZE - obj.getSize()  / 2);
		return new PVector(x, y);
		
	}


}
