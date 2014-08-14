import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/*
 * a class KingCrab that extends CrabCritter. A KingCrab gets the actors to be
 * processed in the same way a CrabCritter does. A KingCrab causes each actor
 * that it processes to move one location further away from the KingCrab.
 * If the actor cannot move away, the KingCrab removes it from the grid.
 * When the KingCrab has completed processing the actors, it moves like a CrabCritter.
 */

public class KingCrab extends CrabCritter {

    public void forceToMove(Actor a, int ac, int ar, int kr, int kc) {
        // targetdir to set the direction to move
        // target is the location to move
        int targetdir;
        Location target;
        Grid<?> gr = getGrid();

        if (ar < kr) {
            // northwest and northeast position
            if (ac < kc) {
                targetdir = Location.NORTHWEST;
            } else {
                targetdir = Location.NORTHEAST;
            }
        } else {
            // northwest and northeast position
            if (ac < kc) {
                targetdir = Location.SOUTHWEST;
            } else {
                targetdir = Location.SOUTHEAST;
            }
        }

        // move if is valid
        target = a.getLocation().getAdjacentLocation(targetdir);
        if (gr.isValid(target)) {
            a.moveTo(target);
        } else {
            a.removeSelfFromGrid();
        }
    }

    public void processActors(ArrayList<Actor> actors) {
        // kr king row
        // kc king column
        int kr = getLocation().getRow();
        int kc = getLocation().getCol();
        Grid<?> gr = getGrid();

        for (Actor a : actors) {
            // variable ar actor row
            // variable ac actor column
            int ar = a.getLocation().getRow();
            int ac = a.getLocation().getCol();
            // row distance
            int rd = ar > kr ? (ar - kr) : (kr - ar);
            // column distance
            int cd = ac > kc ? (ac - kc) : (kc - ac);

            // move away
            if (rd == 1 && cd == 1) {
                forceToMove(a, ac, ar, kr, kc);
            } else {
                // move in front of the king
                if (gr.isValid(a.getLocation().getAdjacentLocation(
                        getDirection()))) {
                    a.moveTo(a.getLocation()
                            .getAdjacentLocation(getDirection()));
                } else {
                    a.removeSelfFromGrid();
                }
            }
        }
    }
}
