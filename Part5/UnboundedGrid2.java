import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * An <code>UnboundedGrid2</code> is a rectangular grid with an unbounded number
 * of rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */

public class UnboundedGrid2<E> extends AbstractGrid<E> {
    // the array storing the grid elements
    private Object[][] occupantArray;
    private int sidelength = 16;

    /**
     * Constructs an empty unbounded grid.
     */
    public UnboundedGrid2() {
        occupantArray = new Object[sidelength][sidelength];
    }

    public int getNumRows() {
        return -1;
    }

    public int getNumCols() {
        return -1;
    }

    public boolean isValid(Location loc) {
        return (loc.getCol() >= 0 && loc.getRow() >= 0);
    }

    private void changeTheGrid(Location loc) {
        int doublelength = sidelength + sidelength;
        // double if the the doublelength is lager than required
        while (doublelength <= loc.getCol() || doublelength <= loc.getRow()) {
            doublelength = doublelength + doublelength;
        }
        Object[][] tmp = new Object[doublelength][doublelength];

        // put the old grid to new one
        for (int i = 0; i < sidelength; i++) {
            for (int j = 0; j < sidelength; j++) {
                tmp[i][j] = occupantArray[i][j];
            }
        }

        // refresh the variable
        sidelength = doublelength;
        occupantArray = tmp;

    }

    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < sidelength; r++) {
            for (int c = 0; c < sidelength; c++) {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null){
                    theLocations.add(loc);
                }
            }
        }

        return theLocations;
    }

    @SuppressWarnings("unchecked")
    public E get(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        // return null if a location is valid, but not in the array
        if (loc.getRow() >= sidelength || loc.getCol() >= sidelength) {
            return null;
        }
        return (E) occupantArray[loc.getRow()][loc.getCol()];
    }

    public E put(Location loc, E obj) {

        if (loc == null) {
            throw new NullPointerException("loc == null");
        }

        if (loc.getCol() >= sidelength || loc.getRow() >= sidelength) {
            changeTheGrid(loc);
        }

        // Add the object to the grid.
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    public E remove(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        // if location is valid but not in array we should return null
        if (loc.getRow() >= sidelength || loc.getCol() >= sidelength) {
            return null;
        }
        // Remove the object
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }
}
