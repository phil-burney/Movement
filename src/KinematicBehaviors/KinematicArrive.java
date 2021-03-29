package KinematicBehaviors;

import Display.Display;
import DynamicBehaviors.weight.WeightedMovement;
import Motion.DynamicMotion;
import Motion.KinematicMotion;
import Motion.Motion;
import MovingObject.MovingObject;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * This class implements the kinematic arrive behavior
 * 
 * @author Phil Burney
 *
 */
public class KinematicArrive extends KinematicBehavior {
	public static final float SAT_RAD = 2;
	public static final float APPROACH_RAD = 50;
	PVector dest = new PVector(0, 0);
	

	public KinematicArrive() {
		super();
	}
	/**
	 * This arrive algorithm uses one vector, and never updates that vector along the path.  Stops when the 
	 * satisfaction radius is approached
	 */
	@Override
	public PVector behavior(Motion motion) {
		PVector linear = new PVector(0, 0);
		if (motion.getVectorToDest(dest).mag() < SAT_RAD) {
			motion.setVelocityToZero();
			return linear;
		}
		// Update vector to follow. If the object is in the approach radius, slow it
		// down, otherwise, set the vector to the
		// Desired speed.
		float distance = motion.getVectorToDest(dest).mag();

		if ( distance < APPROACH_RAD) {
			linear = motion.getVectorToDest(dest).normalize().mult(MovingObject.MAX_SPEED / 2);
			

		} else {
			linear = motion.getVectorToDest(dest).normalize().mult(MovingObject.MAX_SPEED);
		}

		return linear;

	}
	/**
	 * The action that occurs on mouse press.  In this case, the location is selected
	 */
	@Override
	public void onMousePress(float x, float y) {
		dest = new PVector(x, y);

		obj.getMotion().setVelocity(obj.getMotion().getVectorToDest(dest).setMag(MovingObject.MAX_SPEED));

	}
	public void setDest(float x, float y) {
		dest = new PVector(x, y);
	}


}
