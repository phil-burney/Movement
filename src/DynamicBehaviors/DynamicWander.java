package DynamicBehaviors;

import Display.Display;
import DynamicBehaviors.weight.WeightedMovement;
import Motion.DynamicMotion;
import Motion.Motion;
import processing.core.PApplet;
import processing.core.PVector;

public class DynamicWander extends DynamicBehavior {

	public static final float VELOCITY_MAX = 20;
	public static final float TURN_FACTOR = 3f;
	public static final float BOUNDARY_BUFFER = 50f;
	public DynamicWander(PApplet app) {
		super(app);
		

	}

	@Override
	public WeightedMovement behavior(Motion motion) {

		// Update vector to follow. If the object is in the approach radius, slow it
		// down, otherwise, set the vector to the
		// Desired speed.
		PVector turn;
		if (motion.getPosition().x < BOUNDARY_BUFFER) {
			turn = new PVector(2,0).mult(TURN_FACTOR);
		}
		else if (motion.getPosition().x > Display.SCREEN_SIZE - BOUNDARY_BUFFER) {
			turn = new PVector(-2,0).mult(TURN_FACTOR);
		}
		else if (motion.getPosition().y < BOUNDARY_BUFFER) {
			turn = new PVector(0,2).mult(TURN_FACTOR);
		}
		else if ( motion.getPosition().y > Display.SCREEN_SIZE - BOUNDARY_BUFFER) {
			turn = new PVector(0,-2).mult(TURN_FACTOR);
		}
		else {
			float accelX = app.random(-100, 100);
			float accelY = app.random(-100, 100);
			turn = new PVector(accelX, accelY).normalize().mult(DynamicMotion.ACCEL_LIM);
		}
		
		if(motion.getVelocity().mag() > VELOCITY_MAX) {
			turn = motion.getVelocity().normalize().mult(-DynamicMotion.ACCEL_LIM);
		}
		return new WeightedMovement(turn, 0); 

	}

}
