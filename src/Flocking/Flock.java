package Flocking;

import Display.Display;
import DynamicBehaviors.Cohesion;
import DynamicBehaviors.DynamicArrive;
import DynamicBehaviors.DynamicBehavior;
import DynamicBehaviors.DynamicWander;
import DynamicBehaviors.FlockingBehavior;
import DynamicBehaviors.MovementBlender;
import DynamicBehaviors.Separation;
import DynamicBehaviors.VelocityMatching;
import DynamicBehaviors.weight.WeightedMovement;
import Motion.DynamicMotion;
import MovingObject.MovingObject;
import processing.core.PApplet;
import processing.core.PVector;

public class Flock {
	/** Number of followers that the leader will have */
	private int followerNum;
	/** The leader of the flock */
	private MovingObject leader;
	/** The leader of the flock's behavior */
	private DynamicBehavior leaderBehavior;

	/** The list of moving objects following the leader */
	private MovingObject[] followers;
	/** List of behaviors for the followers */
	private FlockingBehavior followerBehavior;

	/** The applet to be used */
	private PApplet app;
	/** Follower in each row */

	MovementBlender blender;

	Separation separation;

	VelocityMatching vMatching;

	Cohesion cohesion;

	DynamicArrive dArrive;

	DynamicWander dWander;

	private static final float FOLLOWER_SIZE = 10;

	/**
	 * Constructor method for the Flocking object
	 * 
	 * @param app
	 * @param followers
	 */
	boolean hasLeader;

	public Flock(PApplet app, int followerNum, boolean hasLeader) {
		this.hasLeader = hasLeader;
		blender = new MovementBlender(); 
		
		separation = new Separation(app, hasLeader);
		
		vMatching = new VelocityMatching(app, hasLeader);
		
		cohesion = new Cohesion(app, hasLeader);
		
		dArrive = new DynamicArrive(app);
		
		dWander = new DynamicWander(app);

		this.app = app;
		this.followerNum = followerNum;
		// Starting positon and velocity vectors
		followers = new MovingObject[this.followerNum];
		if(hasLeader) {
		createLeader();
		}
		createFollowers();

	}

	private void createLeader() {
		PVector start = new PVector(Display.SCREEN_SIZE / 2, Display.SCREEN_SIZE / 2);
		PVector velocity = new PVector(0f, 0f);
		PVector accel = new PVector(0f, 0f);
		// Start up the leader and his behavior

		leader = new MovingObject(app,
				new DynamicMotion(start, 90f, velocity, PApplet.radians(2), accel, PApplet.radians(30)), 30);

		// Behavior for Flock leader
		leaderBehavior = dArrive;

	}

	private void createFollowers() {

		for (int i = 0; i < followerNum; i++) {

			// Starting positon and velocity vectors
			PVector velocity = new PVector(0f, 0f);
			PVector accel = new PVector(0f, 0f);
			float x = app.random(0, 600);
			float y = app.random(0, 600);
			PVector start = new PVector(x, y);

			// Start up the followers
			followers[i] = new MovingObject(app,
					new DynamicMotion(start, 90f, velocity, PApplet.radians(2), accel, PApplet.radians(20)),
					FOLLOWER_SIZE);
		}
	}

	public void flocking() {
		updateFollowers();
		if (hasLeader) {
			updateLeader();
		}

	}

	public void updateLeader() {
		DynamicMotion dynMotion = (DynamicMotion) leader.getMotion();
		dynMotion.addBlendedBehaviors(leaderBehavior.behavior(dynMotion));
		leader.getMotion().dynamicUpdate();
		leader.updateObjKinematics(leader.getMotion().getPosition().x, leader.getMotion().getPosition().y,
				leader.getMotion().getOrientation());
	}

	private void updateFollowers() {
		// For arrival
		// followerBehaviorBlender(.75f, .5f, 1f, 0.75f);
		// For wander without leader
		//followerBehaviorBlender(.5f, .5f, 0.5f, 0.6f);
		// For wander with leader
		// followerBehaviorBlender(1f, 0.1f, 1f, 1f);
		// Cohesion and velocity matching turned off
		followerBehaviorBlender(1f, 0f, 0f, 1f);
		
		

	}

	private void followerBehaviorBlender(float steeringCof, float cohesionCof, float vmCof, float separationCof) {

		for (MovingObject follower : followers) {

			WeightedMovement arrivalM = dArrive.behavior(follower.getMotion());
			WeightedMovement wanderM = dWander.behavior(follower.getMotion());
			WeightedMovement cohesionM = cohesion.behavior(followers, follower, leader);
			WeightedMovement vMatchM = vMatching.behavior(followers, follower, leader);
			WeightedMovement separationM = separation.behavior(followers, follower, leader);

			blender.addWeightedMovementToList(steeringCof, wanderM);
			blender.addWeightedMovementToList(cohesionCof, cohesionM);
			blender.addWeightedMovementToList(vmCof, vMatchM);
			blender.addWeightedMovementToList(separationCof, separationM);

			WeightedMovement motion = blender.getBlendedMovement();

			DynamicMotion dynMotion = (DynamicMotion) follower.getMotion();
			dynMotion.addBlendedBehaviors(motion);
			follower.getMotion().dynamicUpdate();
			follower.updateObjKinematics(dynMotion.getPosition().x, dynMotion.getPosition().y,
					dynMotion.getOrientation());
		}

	}

	public void onMousePress(int mouseX, int mouseY) {
		dArrive.onMousePress(mouseX, mouseY);

	}

}
