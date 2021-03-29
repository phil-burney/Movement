package DynamicBehaviors;

import Display.Display;
import DynamicBehaviors.weight.WeightedMovement;
import Motion.DynamicMotion;
import Motion.Motion;
import MovingObject.MovingObject;
import processing.core.PApplet;
import processing.core.PVector;

public class DynamicBehavior {
	protected PApplet app;

	public DynamicBehavior(PApplet app) {

		this.app = app;

	}

	public Motion behavior() {
		return null;
		// TODO Auto-generated method stub
		
	}

	public void onMousePress(int mouseX, int mouseY) {
		System.out.println(mouseX);
		
	}



	/**
	 * The action that occurs on mouse press. In this case, the location is selected
	 */
	public void onMousePress(int x, int y, Motion motion) {
		// TODO Auto-generated method stub
		
	}




	public WeightedMovement behavior(Motion motion) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * This method implements a more advanced arrive algorithm. For this arrive
	 * algorithm, the method will detect when the object is in a satisfaction
	 * radius, and slow that object down by a pre-determined factor until it reaches
	 * a minimum velocity. At that point, an acceleration vector of 0 is returned
	 */
	public WeightedMovement secondaryBehavior(Motion motion) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
