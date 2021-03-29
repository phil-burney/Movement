package DynamicBehaviors;

import DynamicBehaviors.weight.WeightedMovement;
import Motion.DynamicMotion;
import Motion.Motion;
import MovingObject.MovingObject;
import processing.core.PApplet;
import processing.core.PVector;
import Display.Display;

public class DynamicArrive extends DynamicBehavior {

	public static final float SAT_RAD = 10;
	public static final float APPROACH_RAD = 100;
	public static final float VELOCITY_MIN = 20;
	// PVector dest = obj.getMotion().getPosition();
	PVector dest = new PVector(Display.SCREEN_SIZE / 2, Display.SCREEN_SIZE / 2);

	public DynamicArrive(PApplet app) {
		super(app);

	}

	public WeightedMovement behavior1(Motion motion) {
		PVector linear = new PVector(0, 0);
		if (motion.getVectorToDest(dest).mag() < SAT_RAD) {
			motion.setVelocityToZero();
			return new WeightedMovement(linear, 0);
		}
		// Update vector to follow. If the object is in the approach radius, slow it
		// down, otherwise, set the vector to the
		// Desired speed.
		float distance = motion.getVectorToDest(dest).mag();

		linear = motion.getVectorToDest(dest).normalize().mult(DynamicMotion.ACCEL_LIM);
		float v = motion.getVelocity().mag();
		motion.setVelocity(motion.getVectorToDest(dest).normalize().mult(v));

		return new WeightedMovement(linear, 0);

	}

	/**
	 * This method implements a more advanced arrive algorithm. For this arrive
	 * algorithm, the method will detect when the object is in a satisfaction
	 * radius, and slow that object down by a pre-determined factor until it reaches
	 * a minimum velocity. At that point, an acceleration vector of 0 is returned
	 */
	@Override
	public WeightedMovement behavior(Motion motion) {
		PVector linear = new PVector(0, 0);
		if (motion.getVectorToDest(dest).mag() < SAT_RAD) {
			motion.setVelocityToZero();
			return new WeightedMovement(linear, 0);
		}
		// Update vector to follow. If the object is in the approach radius, slow it
		// down, otherwise, set the vector to the
		// Desired speed.
		float distance = motion.getVectorToDest(dest).mag();
		
		if(PApplet.abs(PApplet.degrees(motion.getDirectionofMovement()) - PApplet.degrees(motion.getVectorToDest(dest).heading())) 
				> 20 && motion.getVelocity().mag() != 0) {
			linear = new PVector(motion.getVelocity().x, motion.getVelocity().y).mult(-1);
			return new WeightedMovement(linear, 0);
		}
		// If object is in the approach radius, apply a braking force
		if (distance < motion.getVelocity().mag() * 2) {
			if (motion.getVelocity().mag() < VELOCITY_MIN) {
				return new WeightedMovement(linear, 0);
			} else {
				// Cut the velocity in half
				linear = motion.getVectorToDest(dest).normalize().mult(-motion.getVelocity().mag() * .5f);
			}

		} else {
			linear = motion.getVectorToDest(dest).normalize().mult(DynamicMotion.ACCEL_LIM);

		}
		app.ellipse(dest.x, dest.y, 20,20);
		return new WeightedMovement(linear, 0);

	}

	@Override
	public WeightedMovement secondaryBehavior(Motion motion) {
		PVector linear = new PVector(0, 0);
		if (motion.getVectorToDest(dest).mag() < SAT_RAD) {
			motion.setVelocityToZero();
			return new WeightedMovement(linear, 0);
		}
		// Update vector to follow. If the object is in the approach radius, slow it
		// down, otherwise, set the vector to the
		// Desired speed.
		float distance = motion.getVectorToDest(dest).mag();
		// Ensure object gets picked up
		if (distance < motion.getVelocity().mag() * 2) {
			if (motion.getVelocity().mag() < VELOCITY_MIN) {
				return new WeightedMovement(linear, 0);
			} else {
				// Cut the velocity in half
				linear = motion.getVectorToDest(dest).normalize().mult(-motion.getVelocity().mag() * .5f);
			}

		} else {
			linear = motion.getVectorToDest(dest).normalize().mult(DynamicMotion.ACCEL_LIM);

		}
		app.ellipse(dest.x, dest.y, 20,20);
		return new WeightedMovement(linear, 0);
		
	}

	/**
	 * The action that occurs on mouse press. In this case, the location is selected
	 */
	@Override
	public void onMousePress(int x, int y) {
		dest = new PVector(x, y);

		System.out.println("Clicked for Dynamic Arrive");
	}

	public PVector getDestination() {
		return dest;
	}

}
