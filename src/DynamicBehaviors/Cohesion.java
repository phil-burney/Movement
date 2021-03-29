package DynamicBehaviors;

import DynamicBehaviors.weight.WeightedMovement;
import Motion.DynamicMotion;
import MovingObject.MovingObject;
import processing.core.PApplet;
import processing.core.PVector;

public class Cohesion extends FlockingBehavior {

	boolean hasLeader;

	public Cohesion(PApplet app, boolean hasLeader) {
		super(app);
		this.hasLeader = hasLeader;
	}

	public WeightedMovement behavior(MovingObject[] followers, MovingObject follower, MovingObject leader) {

		PVector linear = new PVector(0, 0);

		PVector dest = follower.getMotion().getVectorToDest(centerOfMass(followers, follower, leader));

		linear.add(dest.normalize());
		return new WeightedMovement(linear, 0);

	}

	private PVector centerOfMass(MovingObject[] followers, MovingObject follower, MovingObject leader) {
		float avgX = 0;
		float avgY = 0;
		int groupScore = 0;
		for (MovingObject object : followers) {
			float distance = follower.getMotion().getVectorToDest(object.getMotion().getPosition()).mag();
			if (!object.equals(follower) && distance < VISION_RADIUS) {
				avgX = object.getMotion().getPosition().x + avgX;
				avgY = object.getMotion().getPosition().y + avgY;
				groupScore++;
			}
			if (hasLeader) {
				float leaderDistance = follower.getMotion().getVectorToDest(leader.getMotion().getPosition()).mag();
				if (leaderDistance < VISION_RADIUS) {
					avgX = (object.getMotion().getPosition().x + avgX) * LEADER_ATTRACTION;
					avgY = (object.getMotion().getPosition().y + avgY) * LEADER_ATTRACTION;
					groupScore++;
				}
			}
		}
		if (groupScore != 0) {
			avgX /= (groupScore);
			avgY /= (groupScore);
		} else {
			avgX = 0;
			avgY = 0;
		}
		return new PVector(avgX, avgY);
	}

}
