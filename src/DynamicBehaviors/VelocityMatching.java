package DynamicBehaviors;

import DynamicBehaviors.weight.WeightedMovement;
import Motion.DynamicMotion;
import MovingObject.MovingObject;
import processing.core.PApplet;
import processing.core.PVector;

public class VelocityMatching extends FlockingBehavior {
	boolean hasLeader;

	public VelocityMatching(PApplet app, boolean hasLeader) {
		super(app);
		this.hasLeader = hasLeader;
	}

	public WeightedMovement behavior(MovingObject[] followers, MovingObject follower, MovingObject leader) {

		PVector mean = new PVector(0, 0);
		int groupScore = 0;
		for (MovingObject object : followers) {
			float distance = follower.getMotion().getVectorToDest(object.getMotion().getPosition()).mag();
			if (!object.equals(follower) && distance < VISION_RADIUS) {
				mean.add(object.getMotion().getVelocity());
				groupScore++;
			}
			if (hasLeader) {
				float leaderDistance = follower.getMotion().getVectorToDest(leader.getMotion().getPosition()).mag();
				if (leaderDistance < VISION_RADIUS) {
					PVector mult = new PVector(leader.getMotion().getVelocity().x, leader.getMotion().getVelocity().y);
					mean.add(mult.mult(LEADER_ATTRACTION));
					groupScore++;
				}
			}

		}
		if (groupScore != 0) {
			mean.div(groupScore);
			return new WeightedMovement(PVector.sub(mean, follower.getMotion().getVelocity()), 0);
		} else {
			mean = new PVector(0, 0);
			return new WeightedMovement(mean, 0);
		}

	}

}
