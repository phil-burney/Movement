package Motion;

import processing.core.PVector;

public class KinematicMotion extends Motion {
	

	public KinematicMotion(PVector position, float orientation, PVector velocity, float rotationSpeed) {
		super(position, orientation, velocity, rotationSpeed);
		
		
	}
	
	@Override
	public void kinematicUpdate() {
		super.setPosition(super.getPosition().add(super.getVelocity()));
		if (super.velocity.mag() > 0) {
			//orientationMethod1();
			// orientationMethod2();
			super.orientationMethod3();

		}
	}
}