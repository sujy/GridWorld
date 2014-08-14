import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A <code>SparseBoundedGrid</code> is a rectangular grid with a finite number
 * of rows and columns. <br />
 */
public class SparseBoundedGrid<E> implements Grid<E> {

    // class of public class OccupantInCol
    class OccupantInCol {
        private Object occupant;
        private int col;

        OccupantInCol(int numbercol, Object object) {
            occupant = object;
            col = numbercol;
        }

        public Object getOccupant() {
            return occupant;
        }

        public int getCol() {
            return col;
        }
    }

    // the array storing the grid elements
    private ArrayList<LinkedList<OccupantInCol>> sparseArrayList;
    private int numcols;
    private int numrows;

    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * 
     * @param rows
     *            number of rows in BoundedGrid
     * @param cols
     *            number of columns in BoundedGrid
     */
    public SparseBoundedGrid(int rows, int cols) {
        if (rows <= 0) {
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0) {
            throw new IllegalArgumentException("cols <= 0");
        }
        // get the number of columns and rows
        numcols = cols;
        numrows = rows;
        sparseArrayList = new ArrayList<LinkedList<OccupantInCol>>();
        for (int i = 0; i < rows; i++) {
            sparseArrayList.add(new LinkedList<OccupantInCol>());
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        for (OccupantInCol tmp : sparseArrayList.get(loc.getRow())) {
            if (tmp.getCol() == loc.getCol()) {
                return (E) tmp.getOccupant();
            }
        }
        // unavoidable warning
        return (E) null;
    }

    @Override
    public ArrayList<Location> getEmptyAdjacentLocations(Location loc) {
        ArrayList<Location> locs = new ArrayList<Location>();
        for (Location neighborLoc : getValidAdjacentLocations(loc)) {
            if (get(neighborLoc) == null) {
                locs.add(neighborLoc);
            }
        }
        return locs;
    }

    @Override
    public ArrayList<E> getNeighbors(Location loc) {
        ArrayList<E> neighbors = new ArrayList<E>();
        for (Location neighborLoc : getOccupiedAdjacentLocations(loc)) {
            neighbors.add(get(neighborLoc));
        }
        return neighbors;
    }

    @Override
    // get the number of columns
    public int getNumCols() {
        return numcols;
    }

    @Override
    // get the number of rows
    public int getNumRows() {
        return numrows;
    }

    @Override
    // get occupied adjacent locations
    public ArrayList<Location> getOccupiedAdjacentLocations(Location loc) {
        ArrayList<Location> locs = new ArrayList<Location>();
        for (Location neighborLoc : getValidAdjacentLocations(loc)) {
            if (get(neighborLoc) != null) {
                locs.add(neighborLoc);
            }
        }
        return locs;
    }

    @Override
    // get occupied locations
    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> theLocations = new ArrayList<Location>();
        // Look at all grid locations.
        for (int r = 0; r < getNumRows(); r++) {
            for (int c = 0; c < getNumCols(); c++) {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null) {
                    theLocations.add(loc);
                }
            }
        }
        return theLocations;
    }

    @Override
    // get valid adjacent locations
    public ArrayList<Location> getValidAdjacentLocations(Location loc) {
        ArrayList<Location> locs = new ArrayList<Location>();
        int d = Location.NORTH;
        for (int i = 0; i < Location.FULL_CIRCLE / Location.HALF_RIGHT; i++) {
            Location neighborLoc = loc.getAdjacentLocation(d);
            if (isValid(neighborLoc)) {
                locs.add(neighborLoc);
            }
            d = d + Location.HALF_RIGHT;
        }
        return locs;
    }

    @Override
    // isValid
    public boolean isValid(Location loc) {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    @Override
    public E put(Location loc, E obj) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        if (obj == null) {
            throw new NullPointerException("obj == null");
        }

        // Add the object to the grid.
        E oldOccupant = get(loc);
        sparseArrayList.get(loc.getRow()).add(
                new OccupantInCol(loc.getCol(), obj));
        return oldOccupant;
    }

    @Override
    public E remove(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }

        // Remove the object from the grid.
        int removepos = 0;
        E r = get(loc);
        for (OccupantInCol tmp : sparseArrayList.get(loc.getRow())) {
            if (tmp.getCol() == loc.getCol()) {
                break;
            }
            removepos++;
        }
        sparseArrayList.get(loc.getRow()).remove(removepos);
        return r;
    }

}
