To compile and run the code, I will be assuming that the user has Eclipse installed and knows how to use Eclipse.

To compile the code:
1.  Go to File->Import.  A list of Import Types will show up
2.  Select General->Existing Projects Into Workspace. A pop-up menu will appear
3.  Select the "Select Archive File" radio button
4.  Find the .zip file on the hard drive and ensure that "MovementProject" is selected as the project
5.  Select "Finish" at the bottom right corner.  The project should now be imported and ready to run.

To use the code:
1.  Go to src.Display.Display
2.  Under the Display.draw() method, there will be 6 different methods that are commented out.  Uncomment one at a time
while using the code, similar to what was displayed in lecture.

*kinematicMotionDemo() is the part one deliverable (5pts).

*dynamicArriveDemo() and dynamicArriveDemoTwo() are the deliverables for part two; each implement different arrival algorithms (15 pts)

*dynamicWanderDemo() is the implementation for part three.   
To change the orientation method, do the following
    1. Navigate to src.Motion.DynamicMotion class
    2. Under the dynamicUpdate() method, there are two method calls: dynamicOrientation() which uses a dynamic orientation method and orientationMethod1() which uses
    a smoothing orientation method.
    3. Ensure that one of those method calls is commented out when evaluating the orientation algorithms. 
    
*flockingWithLeader() and flockingNoLeader() are the implementations of flocking with a leader and flocking with no leader.  The titles are self-explanatory.  To adjust the members of the flock, change the integers where the 
flockingLeader and the flockingNoLeader variables are initialized.  The variables are initialized above the draw() method. (25pts) 

Thats it! 

