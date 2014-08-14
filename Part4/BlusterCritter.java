import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

/*
 * A BlusterCritter looks at all of the neighbors within two steps of its 
 * current location. 
 * (For a BlusterCritter not near an edge, this includes 24 locations). 
 * It counts the number of critters in those locations. 
 * If there are fewer than c critters, the BlusterCritter's 
 * color gets brighter (color values increase). 
 * If there are c or more critters, the BlusterCritter's 
 * color darken (color values decrease). 
 * Here, c is a value that indicates the courage of the critter. 
 * It should be set in the constructor. 
 */

public class BlusterCritter extends Critter {
    private int courage;
    

    public BlusterCritter(int c) {
        super();
        courage = c;
    }

    public int getCourage() {
        return courage;
    }

    // search the grid
    public ArrayList<Actor> getActors() {
        ArrayList<Actor> result = new ArrayList<Actor>();
        Location myloc = getLocation();
        for (int i = myloc.getRow() - 2; i <= myloc.getRow() + 2; i++) {
            for (int j = myloc.getCol() - 2; j <= myloc.getCol() + 2; j++) {
                Location tempLoc = new Location(i, j);
                if (getGrid().isValid(tempLoc)) {
                    Actor a = getGrid().get(tempLoc);
                    if (a != null && a != this) {
                        result.add(a);
                    }
                }
            }
        }
        return result;
    }

    public void processActors(ArrayList<Actor> actors) {
        int number = 0;
        final int colorMax = 255;
        final int darkenFactor = 3;
        final double lightenFactor = 0.2;
        
        for (Actor a : actors) {
            if ((a instanceof Critter)
                    && !(a.getLocation().equals(this.getLocation()))) {
                number++;
            }
        }
        if (number > courage) {
            Color c = getColor();
            // darken
            int red = (int) (c.getRed() * (1 - lightenFactor));
            int green = (int) (c.getGreen() * (1 - lightenFactor));
            int blue = (int) (c.getBlue() * (1 - lightenFactor));
            setColor(new Color(red, green, blue));
        } else if (number < courage) {
            Color c = getColor();
            // brighter
            int red = (int) (c.getRed() * (darkenFactor + lightenFactor));
            int green = (int) (c.getGreen() * (darkenFactor + lightenFactor));
            int blue = (int) (c.getBlue() * (darkenFactor + lightenFactor));
            // avoid out of range
            if (red > colorMax) {
                red = colorMax;
            }
            if (green > colorMax) {
                green = colorMax;
            }
            if (blue > colorMax) {
                blue = colorMax;
            }
            setColor(new Color(red, green, blue));
        }
    }
}