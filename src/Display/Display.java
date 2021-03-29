package Display;


import DynamicBehaviors.DynamicArrive;
import DynamicBehaviors.DynamicBehavior;
import DynamicBehaviors.DynamicWander;
import DynamicBehaviors.MovementBlender;
import DynamicBehaviors.weight.WeightedMovement;
import Flocking.Flock;
import KinematicBehaviors.KinematicArrive;
import KinematicBehaviors.KinematicBehavior;
import KinematicBehaviors.KinematicWander;
import Motion.DynamicMotion;
import Motion.KinematicMotion;
import Motion.Motion;
import MovingObject.MovingObject;
import PreprogrammedMotion.BasicMotion;
import processing.core.*;

public class Display extends PApplet {
	/** The frame rate per second of the window */
	public static final int FRAME_RATE = 30;
	/** The size of the window, in pixels */
	public static final int SCREEN_SIZE = 700;
	
	/** MovingObject that is to be used */
	MovingObject dynamicMovingObject;
	/** MovingObject that is to be used */
	MovingObject kinematicMovingObject;
	/** Movement program that is to be used */
	BasicMotion bMotion;
	/** Program for kinematic wander */
	DynamicWander dWander;
	/** Program for kinematic arrive */
	KinematicBehavior kArrive;
	
	Breadcrumbs crumbs;

	
	DynamicBehavior dBehavior;
	/** Program for flocking behavior */
	Flock flockingLeader;
	Flock noLeader;
	int mouseXD = 0;
	int mouseYD = 0;
	/**
	 * Designates the location of the main class 
	 * @param args String arguments 
	 */
	public static void main(String[] args) {
		PApplet.main("Display.Display");

	}
	/**
	 * Settings for the screen size
	 */
	public void settings() {
		size(SCREEN_SIZE, SCREEN_SIZE);
		smooth(2);

	}
	/**
	 * Sets the frame rate, initalizes a moving object and initalizes each behavior that is being 
	 * graded  
	 */
	public void setup() {
		frameRate(FRAME_RATE);
		
		PVector start = new PVector(Display.SCREEN_SIZE / 2, Display.SCREEN_SIZE / 2);
		PVector velocity = new PVector(0f, 0f);
		PVector accel = new PVector(0f, 0f);
		DynamicMotion dynmotion = new DynamicMotion(start, 90f, velocity, PApplet.radians(1), accel, PApplet.radians(1));
		kinematicMovingObject = new MovingObject(this, null, 100);
		dynamicMovingObject = new MovingObject(this, dynmotion, 50);
		
		
		
		// Initializer for part one (5pts)
		bMotion = new BasicMotion(this, kinematicMovingObject);
		crumbs = new Breadcrumbs(this, kinematicMovingObject);
		
		
		dBehavior = new DynamicArrive(this);
		
		dWander = new DynamicWander(this);
		
		// To adjust flock numbers, adjust the middle variable (right now the members are set to 20)
		flockingLeader = new Flock(this, 20, true);
		
		noLeader = new Flock(this, 20, false);
		
	}

	public void draw() {
		
		background(100, 200, 200);
		
		
		
		
		//kinematicMotionDemo();
		//dynamicArriveDemo(dynamicMovingObject);
		//dynamicArriveDemoTwo(dynamicMovingObject);
		//dynamicWanderDemo(dynamicMovingObject);
		//flockingWithLeader();
		//flockingNoLeader();
		
	}
	private void flockingNoLeader() {
		noLeader.flocking();
		
	}
	private void flockingWithLeader() {
		flockingLeader.flocking();
	}
	private void kinematicMotionDemo() {
		bMotion.basicMotion();
		crumbs.render();
		
	}
	private void dynamicWanderDemo(MovingObject obj) {
		DynamicMotion dynMotion = (DynamicMotion) obj.getMotion();
		dynMotion.addBlendedBehaviors(dWander.behavior(dynMotion));
		obj.getMotion().dynamicUpdate();
		obj.updateObjKinematics(obj.getMotion().getPosition().x, obj.getMotion().getPosition().y, obj.getMotion().getOrientation());
		
	}
	private void dynamicArriveDemo(MovingObject obj) {
		DynamicMotion dynMotion = (DynamicMotion) obj.getMotion();
		dynMotion.addBlendedBehaviors(dBehavior.behavior(dynMotion));
		obj.getMotion().dynamicUpdate();
		obj.updateObjKinematics(obj.getMotion().getPosition().x, obj.getMotion().getPosition().y, obj.getMotion().getOrientation());
		
	}
	private void dynamicArriveDemoTwo(MovingObject obj) {
		DynamicMotion dynMotion = (DynamicMotion) obj.getMotion();
		dynMotion.addBlendedBehaviors(dBehavior.secondaryBehavior(dynMotion));
		obj.getMotion().dynamicUpdate();
		obj.updateObjKinematics(obj.getMotion().getPosition().x, obj.getMotion().getPosition().y, obj.getMotion().getOrientation());
		
	}
	public void mousePressed() {
		//kArrive.onMousePress(mouseX, mouseY);

		dBehavior.onMousePress(mouseX, mouseY);
		
		noLeader.onMousePress(mouseX, mouseY);
		
		flockingLeader.onMousePress(mouseX, mouseY);
		
		ellipse(mouseX, mouseY, 10, 10);
		
	}

}
