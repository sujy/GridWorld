import info.gridworld.actor.Actor;

import java.util.ArrayList;

public class ChameleonKid extends ChameleonCritter {

    /**
     * selects the front or behind actor and changes this critter's color to be
     * the same as that neighbor's. If there are no neighbors, no action is
     * taken.
     */
    public ArrayList<Actor> getActors() {
        final int behind = 180;
        /**
         * selects the front of the chameleon kid
         */
        ArrayList<Actor> neighbors = new ArrayList<Actor>();
        if (getGrid()
                .isValid(getLocation().getAdjacentLocation(getDirection()))) {
            Actor target = getGrid().get(
                    getLocation().getAdjacentLocation(getDirection()));
            if (target != null) {
                neighbors.add(target);
            }
        }
        /**
         * selects the behind of the chameleon kid
         */
        if (getGrid().isValid(
                getLocation().getAdjacentLocation(getDirection() + behind))) {
            Actor target = getGrid().get(
                    getLocation().getAdjacentLocation(getDirection() + behind));
            if (target != null) {
                neighbors.add(target);
            }
        }
        return neighbors;
    }

}