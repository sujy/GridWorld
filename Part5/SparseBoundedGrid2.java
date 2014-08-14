import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A <code>SparseBoundedGrid2</code> is a rectangular grid with a finite number
 * of rows and columns. <br />
 * This is implement by hash map
 */
public class SparseBoundedGrid2<E> extends AbstractGrid<E> {

    // occupantMap
    private Map<Location, E> occupantMap;
    // number of columns
    private int numcols;
    // number of rows
    private int numrows;

    /**
     * Constructs an empty SparseBoundGrid2.
     */
    public SparseBoundedGrid2(int rows, int cols) {
        if (rows <= 0) {
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0) {
            throw new IllegalArgumentException("cols <= 0");
        }
        numcols = cols;
        numrows = rows;
        occupantMap = new HashMap<Location, E>();
    }

    // get the number of rows
    public int getNumRows() {
        return numrows;
    }

    // get the number of columns
    public int getNumCols() {
        return numcols;
    }

    // is valid location
    public boolean isValid(Location loc) {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    // get occupied location
    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> a = new ArrayList<Location>();
        for (Location loc : occupantMap.keySet()) {
            a.add(loc);
        }
        return a;
    }

    // get object by location
    public E get(Location loc) {
        if (loc == null) {
            throw new NullPointerException("loc == null");
        }
        return occupantMap.get(loc);
    }

    // put object by location
    public E put(Location loc, E obj) {
        if (loc == null) {
            throw new NullPointerException("loc == null");
        }
        if (obj == null) {
            throw new NullPointerException("obj == null");
        }
        return occupantMap.put(loc, obj);
    }

    // remove the object by location
    public E remove(Location loc) {
        if (loc == null) {
            throw new NullPointerException("loc == null");
        }
        return occupantMap.remove(loc);
    }

}