package DynamicBehaviors;

import DynamicBehaviors.weight.WeightedMovement;
import Motion.DynamicMotion;
import MovingObject.MovingObject;
import processing.core.PApplet;
import processing.core.PVector;

public class FlockingBehavior extends DynamicBehavior{
	PApplet app;
	public static final float VISION_RADIUS = 70;
	public static final float LEADER_ATTRACTION = 1;
	public FlockingBehavior(PApplet app) {
		super(app);
	}



	public WeightedMovement behavior(MovingObject[] followers, MovingObject follower) {
		// TODO Auto-generated method stub
		return null;
	}
}
