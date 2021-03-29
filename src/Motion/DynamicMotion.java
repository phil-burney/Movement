package Motion;

import DynamicBehaviors.weight.WeightedMovement;
import processing.core.PApplet;
import processing.core.PVector;

public class DynamicMotion extends Motion {
	public static final float ACCEL_LIM = 2;

	public DynamicMotion(PVector position, float orientation, PVector velocity, float rotationSpeed, PVector linAccel,
			float angAccel) {
		super(position, orientation, velocity, rotationSpeed, linAccel, angAccel);
	}

	@Override
	public void dynamicUpdate() {
		if (super.velocity.mag() > 0) {
			
			//Different orientation methods; ensure that one is commented out at a time
			
			// Dynamic update method 
			dynamicOrientation();
			
			// Smoothing method
			//orientationMethod1();
			
		}
		super.setRotationSpeed(super.getRotationSpeed() + super.getAngAccel());
		super.getVelocity().add(super.getLinAccel());
		super.setPosition(super.getPosition().add(super.getVelocity()));
		super.setLinAccel(new PVector(0, 0));
		
		
	}

	private void dynamicOrientation() {
		// Get target vector
		float target = PApplet.atan2(super.getVelocity().y, super.getVelocity().x);
		// Get the angle difference and adjust accordingly
		float angleDiff = (target - super.orientation);
		if (PApplet.PI < angleDiff) {
			angleDiff -= PApplet.TWO_PI;
		} else if (-PApplet.PI > angleDiff) {
			angleDiff += PApplet.TWO_PI;
		}
		// Determine the direction that the object should rotate
		float mult = angleDiff > 0 ? 1 : -1;
		// Determine absolute value of angle difference
		angleDiff = angleDiff < 0 ? angleDiff * -1 : angleDiff;
		// If the rotation speed is greater than the angle difference,
		// adjust by the angle difference
		this.setRotationSpeed(this.getRotationSpeed() + this.getAngAccel());
		if (angleDiff < this.getRotationSpeed()) {
			super.orientation += mult * angleDiff;
			this.setRotationSpeed(0);
			return;
		}
		// Otherwise adjust by the rotation speed
		super.orientation += mult * this.getRotationSpeed();
		// Ensure that orientation is normalized
		if (PApplet.PI < super.orientation) {
			super.orientation -= PApplet.TWO_PI;
		} else if (-PApplet.PI > super.orientation) {
			super.orientation += PApplet.TWO_PI;
		}

	}
	public void addBlendedBehaviors(WeightedMovement movement) {
		super.linAccel.add(movement.getLinAccel());
		super.angAccel += movement.getRotAccel();
	}
	
	private void orientationMethod1() {
		float target = PApplet.atan2(this.velocity.y, this.velocity.x);

		float angleDiff = (target - this.orientation);
		if (PApplet.PI < angleDiff) {
			angleDiff -= PApplet.TWO_PI;
		} else if (-PApplet.PI > angleDiff) {
			angleDiff += PApplet.TWO_PI;
		}

		this.orientation += angleDiff * 0.5f;

		if (PApplet.PI < this.orientation) {
			this.orientation -= PApplet.TWO_PI;
		} else if (-PApplet.PI > this.orientation) {
			this.orientation += PApplet.TWO_PI;
		}
	}
}
