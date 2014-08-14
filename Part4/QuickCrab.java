import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/*
 *  A QuickCrab processes actors the same way a CrabCritter does. 
 *  A QuickCrab moves to one of the two locations, 
 *  randomly selected, that are two spaces to its right or left, 
 *  if that location and the intervening location are both empty. 
 *  Otherwise, a QuickCrab moves like a CrabCritter.
 */
public class QuickCrab extends CrabCritter {
    /**
     * @return list of empty locations immediately to the right and to the left
     */
    public ArrayList<Location> getMoveLocations() {
        final int turn = 90;
        ArrayList<Location> locs = new ArrayList<Location>();
        canGoDirectionTwice(locs, getDirection() + turn);
        canGoDirectionTwice(locs, getDirection() - turn);
        if (locs.size() > 0) {
            return locs;
        } else {
            return super.getMoveLocations();
        }
    }

    /**
     * If the crab critter can move twice in on direction
     */
    public void canGoDirectionTwice(ArrayList<Location> locs, int direction) {
        Grid<?> gr = getGrid();
        Location loc = getLocation();
        Location target = loc.getAdjacentLocation(direction);
        if (gr.isValid(target) && (gr.get(target) == null)) {
            loc = target;
            target = loc.getAdjacentLocation(direction);
            if (gr.isValid(target) && (gr.get(target) == null)) {
                locs.add(target);
            }

        }
    }

    /**
     * If the crab critter doesn't move, it randomly turns left or right.
     */
    public void makeMove(Location loc) {
        final double factor = 0.5;
        if (loc.equals(getLocation())) {
            double r = Math.random();
            int angle;
            if (r < factor) {
                angle = Location.LEFT;
            } else {
                angle = Location.RIGHT;
            }
            setDirection(getDirection() + angle);
        } else {
            super.makeMove(loc);
        }
    }

}