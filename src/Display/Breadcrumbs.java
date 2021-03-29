package Display;
import java.util.ArrayList;

import MovingObject.MovingObject;
import processing.core.*;

public class Breadcrumbs {
	ArrayList<PVector> breadcrumbs = new ArrayList<PVector>();
	  
	  MovingObject obj;
	  PApplet app;
	  PVector lastPosition;
	  float distance;
	  
	  float interval = 15f;
	  float size = 4f;
	  
	  public Breadcrumbs(PApplet app, MovingObject obj) {
	    this.obj = obj;
	    this.app = app;
	    lastPosition = obj.getMotion().getPosition();
	    distance = 0.0f;
	  }
	  
	  public void render() {
	    lastPosition = new PVector(obj.getMotion().getPosition().x, obj.getMotion().getPosition().y);
	    breadcrumbs.add(lastPosition);
	   
	    app.fill(165, 42, 42);
	    app.stroke(165, 42, 42);
	    for(PVector crumb : breadcrumbs)
	      app.circle(crumb.x, crumb.y, size);
	  }

}
