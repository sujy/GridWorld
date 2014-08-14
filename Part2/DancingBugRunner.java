import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

public class BugRunner
{
    public static void main(String[] args)
   {
        int arr[] ={2,3,1,3,3,3,6,4};
        ActorWorld world = new ActorWorld();
        DancingBug alice = new DancingBug(arr);
        alice.setColor(Color.GREEN);
        world.add(new Location(5, 5), alice);
        world.show();
    }
}
