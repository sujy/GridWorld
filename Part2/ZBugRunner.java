import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

public class BugRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        ZBug alice = new ZBug(4);
        ZBug bob = new ZBug(4);
        alice.setColor(Color.BLACK);
        world.add(new Location(5, 4), alice);
        world.add(new Location(6, 5), bob);
        world.show();
    }
}
