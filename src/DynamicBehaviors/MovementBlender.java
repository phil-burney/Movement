package DynamicBehaviors;

import java.util.ArrayList;

import DynamicBehaviors.weight.WeightedMovement;
import Motion.DynamicMotion;
import Motion.Motion;
import processing.core.PVector;

public class MovementBlender {
	ArrayList<WeightedMovement> movements;
	
	public MovementBlender() {
		movements = new ArrayList<WeightedMovement>();
	}
	
	public void addWeightedMovementToList(float weight, WeightedMovement motion) {
		motion.applyWeight(weight);
		movements.add(motion);
	}
	
	public WeightedMovement getBlendedMovement() {
		
		PVector linAccel = new PVector(0,0);
		float angAccel = 0f;
		
		
		for(WeightedMovement movement : movements) {
			linAccel.add(movement.getLinAccel());
			angAccel += movement.getRotAccel();
		}
		movements.clear();
		return new WeightedMovement (linAccel, angAccel);
	}
}
