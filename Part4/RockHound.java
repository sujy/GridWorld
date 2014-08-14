import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;

import java.util.ArrayList;

/**
 * A <code>RockHound</code> is an actor that moves through its world, processing
 * other actors and rocks in some way and then moving to a new location. 
 */

public class RockHound extends Critter {
    public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a : actors)
        {
            //when we found a rock, remove it
            if ((a instanceof Rock)) {
                a.removeSelfFromGrid();
            }
        }
    }
    
}