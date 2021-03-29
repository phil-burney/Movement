package Motion;

import processing.core.PApplet;
import processing.core.PVector;

public class Motion {
	/** Position vector */
	private PVector position;
	/** Orientation in radians */
	protected float orientation;
	/** Velocity vector */
	protected PVector velocity;
	/** Rotation speed in radians */
	private float rotationSpeed;
	/** Acceleration vector */
	protected PVector linAccel;
	/** Angular acceleration in radians */
	protected float angAccel;
	/**
	 * Constructor class for Dynamic Motion 
	 * 
	 * @param position      the position of the object
	 * @param orientation   the orientation of the object, in radians
	 * @param velocity      the velocity of the object, in vector form
	 * @param rotationSpeed the rotation speed of the object, in radians per second
	 * @param linAccel		linear acceleration of the object
	 * @param angAccel		angular acceleration of the object
	 */	
	public Motion(PVector position, float orientation, PVector velocity, float rotationSpeed, PVector linAccel,
			float angAccel) {
		this.position = position;
		this.orientation = orientation;
		this.velocity = velocity;
		this.rotationSpeed = rotationSpeed;
		this.linAccel = linAccel;
		this.angAccel = angAccel;
	}
	
	/**
	 * Constructor class for Kinematic Motion 
	 * 
	 * @param position      the position of the object
	 * @param orientation   the orientation of the object, in radians
	 * @param velocity      the velocity of the object, in vector form
	 * @param rotationSpeed the rotation speed of the object, in radians per second
	 */
	public Motion(PVector position, float orientation, PVector velocity, float rotationSpeed) {
		this.position = position;
		this.orientation = orientation;
		this.velocity = velocity;
		this.rotationSpeed = rotationSpeed;
	}

	/**
	 * Returns position
	 * @return position
	 */
	public PVector getPosition() {
		return this.position;
	}
	/**
	 * Sets new positon
	 * @param position to set
	 */
	public void setPosition(PVector position) {
		this.position = position;
	}

	public float getOrientation() {
		return this.orientation;
	}

	public PVector getVelocity() {
		return this.velocity;
	}

	public void setVelocity(PVector velocity) {
		this.velocity = velocity;
	}
	
	public float getDirectionofMovement() {
		return this.velocity.heading();
	}

	public float getRotationSpeed() {
		return this.rotationSpeed;
	}

	public void setRotationSpeed(float f) {
		this.rotationSpeed = f;
	}

	public PVector getLinAccel() {
		return linAccel;
	}

	public void setLinAccel(PVector v) {
		this.linAccel = v;
	}

	public float getAngAccel() {
		return angAccel;
	}

	public void setAngAccel(float f) {
		this.angAccel = f;
	}

	public PVector getVectorToDest(PVector dest) {
		float x = dest.x - this.position.x;
		float y = dest.y - this.position.y;
		return new PVector(x, y);
	}
	
	public PVector getVectorFromDest(PVector dest) {
		float x = this.position.x - dest.x;
		float y = this.position.y - dest.y;
		return new PVector(x, y);
	}

	public void setVelocityToZero() {
		this.velocity = this.getVelocity().setMag(0);
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

	private void orientationMethod2() {
		float target = PApplet.atan2(this.velocity.y, this.velocity.x);

		float angleDiff = target - this.orientation;

		if (PApplet.PI < angleDiff) {
			angleDiff -= PApplet.TWO_PI;
		} else if (-PApplet.PI > angleDiff) {
			angleDiff -= PApplet.TWO_PI;
		}

		this.orientation += angleDiff;

		if (PApplet.PI < this.orientation) {
			this.orientation -= PApplet.TWO_PI;
		} else if (-PApplet.PI > this.orientation) {
			this.orientation += PApplet.TWO_PI;
		}

	}

	protected void orientationMethod3() {
		//Get target vector
		float target = PApplet.atan2(this.velocity.y, this.velocity.x);
		//Get the angle difference and adjust accordingly
		float angleDiff = (target - this.orientation);
		if (PApplet.PI < angleDiff) {
			angleDiff -= PApplet.TWO_PI;
		} else if (-PApplet.PI > angleDiff) {
			angleDiff += PApplet.TWO_PI;
		}
		//Determine the direction that the object should rotate
		float mult = angleDiff > 0 ? 1 : -1;
		//Determine absolute value of angle difference
		angleDiff = angleDiff < 0 ? angleDiff * -1 : angleDiff;
		//If the rotation speed is greater than the angle difference,
		//adjust by the angle difference
		if(angleDiff < this.rotationSpeed) {
			this.orientation += mult * angleDiff;
			return;
		}
		//Otherwise adjust by the rotation speed
		this.orientation += mult * this.rotationSpeed;
		//Ensure that orientation is normalized
		if (PApplet.PI < this.orientation) {
			this.orientation -= PApplet.TWO_PI;
		} else if (-PApplet.PI > this.orientation) {
			this.orientation += PApplet.TWO_PI;
		}
		
	}



	public void kinematicUpdate() {
		// TODO Auto-generated method stub
		
	}

	public void dynamicUpdate() {
		// TODO Auto-generated method stub
		
	}

}
