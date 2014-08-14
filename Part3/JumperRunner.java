/* 
 * Created by Gemini on 08-06
 * Copyright(c) 2014
 * 
 * This code is distributed in order to finishi our project...
 * depends on 'BugRunner.java'
 *
 * good luck
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.grid.*;
/**
 * This class runs a world that contains a bug and a rock, added at random
 * locations. Click on empty locations to add additional actors. Click on
 * populated locations to invoke methods on their occupants. <br />
 * To build your own worlds, define your own actors and a runner class. See the
 * BoxBugRunner (in the boxBug folder) for an example. <br />
 * This class is not tested on the AP CS A and AB exams.
 */

public class JumperRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Location(4, 4), new Jumper());
        world.add(new Rock());
        world.add(new Flower());        
        world.show();
    }
}

