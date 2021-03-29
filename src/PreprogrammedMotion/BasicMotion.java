package PreprogrammedMotion;

import Motion.KinematicMotion;
import MovingObject.MovingObject;
import processing.core.PApplet;
import processing.core.PVector;
import Display.Breadcrumbs;
import Display.Display;
import KinematicBehaviors.KinematicArrive;

public class BasicMotion {
	PApplet app;
	MovingObject obj;
	KinematicArrive behavior;
	
	float turnCornerMax;
	
	float turnCornerMin;
	
	public BasicMotion(PApplet app, MovingObject obj ) {
		this.obj = obj;
		this.app = app;
		PVector start = new PVector(obj.getSize()  / 2, Display.SCREEN_SIZE - obj.getSize()  / 2);
		PVector velocity = new PVector(0f, MovingObject.MAX_SPEED * -1);
		
		obj.setMotion( new KinematicMotion(start, -90f, velocity, PApplet.radians(15)));
		
		
		
		turnCornerMax = Display.SCREEN_SIZE - obj.getSize()  / 2;
		turnCornerMin = obj.getSize()  / 2;
		behavior = new KinematicArrive();
		behavior.setDest(turnCornerMin, turnCornerMin);

	}
	/**
	 * This method does the basic motion laid out in the project instructions.  For each frame, 
	 * boundary checks are conducted, and the velocity is changed as needed. 
	 */
	public void basicMotion() {
		PVector v = behavior.behavior(obj.getMotion());
		obj.getMotion().setVelocity(v);
		
		obj.getMotion().kinematicUpdate();
		
		
		
		float x = obj.getMotion().getPosition().x;
		float y = obj.getMotion().getPosition().y;
		if(y <= turnCornerMin ) {
			behavior.setDest(turnCornerMax,turnCornerMin);
				
		}
		if(x >= turnCornerMax) {
			behavior.setDest(turnCornerMax, turnCornerMax);
			
		}
		
		if(y >= turnCornerMax) {
			behavior.setDest(turnCornerMin, turnCornerMax);
		}
		
		if(x < turnCornerMin) {
			return;
			
		}
			
		obj.updateObjKinematics(x, y, obj.getMotion().getOrientation());
	}

	
}

