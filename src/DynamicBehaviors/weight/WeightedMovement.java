package DynamicBehaviors.weight;

import processing.core.PVector;

public class WeightedMovement {
	private PVector linearAcceleration;
	private float rotationalAcceleration;
	
	
	public WeightedMovement(PVector linAccel, float rotAccel) {
		linearAcceleration = linAccel;
		rotationalAcceleration = rotAccel;
	}
	
	public PVector getLinAccel() {
		return linearAcceleration;
	}
	
	public float getRotAccel() {
		return rotationalAcceleration;
	}
	public void applyWeight(float x) {
		this.linearAcceleration.mult(x);
		this.rotationalAcceleration *= x;
	}

}
