import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

/**
 * This class runs a world with additional grid choices.
 */
public final class GridRunner {
    private GridRunner() {
        
    }
    public static void main(String[] args) {
        // add a new actor world
        ActorWorld world = new ActorWorld();
        // test for grid written by my self
        world.addGridClass("SparseBoundedGrid");
        world.addGridClass("SparseBoundedGrid2");
        world.addGridClass("SparseBoundedGrid3");
        world.addGridClass("UnboundedGrid2");
        world.add(new Location(2, 2), new Critter());
        world.show();
    }
}