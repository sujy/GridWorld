import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.maze.MazeBug;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class RandomMazeBug extends MazeBug {

    private Location next = null;
    private Location last = null;
    private boolean isEnd = false;
    private Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
    private Integer stepCount = 0;
    // final message has been shown
    private boolean hasShown = false;
    private Set<Location> visSet = new HashSet<Location>();
    private boolean invisited;

    /**
     * Constructs a maze bug
     * 
     * @param none
     * 
     */
    public RandomMazeBug() {
        setColor(Color.BLUE);
        last = null;
    }

    /**
     * Moves to the next location of the square.
     */
    public void act() {
        boolean willMove = canMove();
        if (isEnd) {
            // to show step count when reach the goal
            if (!hasShown) {
                String msg = stepCount.toString() + " steps";
                JOptionPane.showMessageDialog(null, msg);
                hasShown = true;
            }
        } else if (willMove) {
            move();
            // increase step count when move
            stepCount++;
        }
    }

    /**
     * Find all positions that can be move to.
     * 
     * @param loc
     *            the location to detect.
     * @return List of positions.
     */
    public ArrayList<Location> getValid(Location loc) {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return null;
        }
        ArrayList<Location> valid = new ArrayList<Location>();
        ArrayList<Actor> neighbors = gr.getNeighbors(getLocation());
        // get the neighbors
        getNeigbors(neighbors, valid);
        // get empty adjacent location
        for (Location temp : gr.getEmptyAdjacentLocations(loc)) {
            int temprow = temp.getRow();
            int tempcol = temp.getCol();
            if (temprow == getLocation().getRow()
                    || tempcol == getLocation().getCol()) {
                valid.add(temp);
            }
        }
        return valid;
    }

    private void getNeigbors(ArrayList<Actor> neighbors,
            ArrayList<Location> valid) {
        for (Actor temp : neighbors) {
            int temprow = temp.getLocation().getRow();
            int tempcol = temp.getLocation().getCol();
            // a bug can move to a flows
            if (temp instanceof Flower) {
                if (temprow == getLocation().getRow()
                        || tempcol == getLocation().getCol()) {
                    valid.add(temp.getLocation());
                }
            }
            // if find a rock finished
            if (temprow == getLocation().getRow()
                    || tempcol == getLocation().getCol()) {
                if ((temp instanceof Rock) && temp.getColor().equals(Color.RED)) {
                    isEnd = true;
                }
            }

        }

    }

    /**
     * Tests whether this bug can move forward into a location that is empty or
     * contains a flower.
     * 
     * @return if this bug can move.
     */
    public boolean canMove() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return false;
        }
        Location loc = getLocation();
        ArrayList<Location> allArrayList = getValid(loc);
        ArrayList<Location> validList = new ArrayList<Location>();
        invisited = false;

        // delete the location that visited
        validList = deleteLocationVisited(allArrayList);

        if (validList.size() > 0) {

            // get the next location
            next = validList.get((int)(Math.random() * 10) % validList.size());
            // put it in the end to get the location when it back
            validList.add(getLocation());
            crossLocation.push(validList);

        } else {
            // bad end return
            validList = crossLocation.pop();
            next = validList.get(validList.size() - 1);
        }
        return true;
    }

    private ArrayList<Location> deleteLocationVisited(
            ArrayList<Location> allArrayList) {
        ArrayList<Location> validList = new ArrayList<Location>();
        if (visSet.size() != 0) {
            for (int i = 0; i < allArrayList.size(); i++) {
                for (Location temp : visSet) {
                    if (temp.getRow() == allArrayList.get(i).getRow()
                            && temp.getCol() == allArrayList.get(i).getCol()) {
                        // key here
                        invisited = true;
                    }
                }
                if (!invisited) {
                    validList.add(allArrayList.get(i));
                }
                invisited = false;
            }
        } else {
            // there is no visited location just put it in to validList
            validList = allArrayList;
        }
        return validList;

    }

   

    /**
     * Moves the bug forward, putting a flower into the location it previously
     * occupied.
     */
    public void move() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        Location loc = getLocation();
        if (gr.isValid(next)) {
            setDirection(getLocation().getDirectionToward(next));
            last = getLocation();
            visSet.add(last);
            moveTo(next);
        } else {
            removeSelfFromGrid();
        }
        // leave folower behind
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
    }
}