import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

public class BugRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        CircleBug alice = new CircleBug(4);
        alice.setColor(Color.BLUE);
        CircleBug bob = new CircleBug(3);
        world.add(new Location(7, 8), alice);
        world.show();
    }
}
