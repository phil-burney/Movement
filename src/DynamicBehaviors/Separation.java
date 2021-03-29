package DynamicBehaviors;

import DynamicBehaviors.weight.WeightedMovement;
import Motion.DynamicMotion;
import MovingObject.MovingObject;
import processing.core.PApplet;
import processing.core.PVector;

public class Separation extends FlockingBehavior {

	private static final float COLLISION_RADIUS = 50f;

	private static final float SEPARATION = 1f;
	boolean hasLeader;

	public Separation(PApplet app, boolean hasLeader) {
		super(app);
		this.hasLeader = hasLeader;
		// TODO Auto-generated constructor stub
	}

	public WeightedMovement behavior(MovingObject[] followers, MovingObject follower, MovingObject leader) {
		PVector linear = new PVector(0, 0);
		for (MovingObject object : followers) {
			// Separate followers
			PVector direction = follower.getMotion().getVectorFromDest(object.getMotion().getPosition());
			float distance = direction.mag();
			if (distance <= COLLISION_RADIUS && !object.equals(follower)) {
				if (distance != 0) {
					linear.add(direction.mult(SEPARATION / distance));
				} else {
					linear.add(direction.mult(SEPARATION));
				}
			}

			// Separate followers from leader
			if (hasLeader) {
				float leaderDistance = follower.getMotion().getVectorToDest(leader.getMotion().getPosition()).mag();
				PVector leaderDirection = follower.getMotion().getVectorFromDest(object.getMotion().getPosition());
				if (leaderDistance <= leader.getSize() * 1.5f) {
					if (leaderDistance != 0) {
						linear.add(leaderDirection.mult(0.25f * SEPARATION / leaderDistance));
					} else {
						linear.add(leaderDirection.mult(SEPARATION));
					}
				}
			}
		}
		return new WeightedMovement(linear, 0);
	}

}
