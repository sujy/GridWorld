/* 
 * Created by Group 58 on 08-06
 * Copyright(c) 2014
 * 
 * This code is distributed in order to finishi our project...
 * depends on 'BoxBug.java'
 *
 * methods in this class:  Jumper(), move(), canMove();
 *
 * classes required:
 * Actor, Bug, Flower, Rock, Location, Grid
 *
 * sonar: 95.3%
 * good luck
 */

import info.gridworld.actor.*;
import info.gridworld.grid.*;

import java.awt.Color;

/*
 * A <code>Bug</code> is an actor that can move and turn. It drops flowers as
 * it moves. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class Jumper extends Bug
{

    /**
     * Constructs a jump bug.
     */
    public Jumper()
    {
        setColor(Color.RED);
    }

    /**
     * Constructs a jumper of a given color.
     * @param bugColor the color for this bug
     */
    public Jumper(Color jumperColor)
    {
        setColor(jumperColor);
    }

    /**
     * Moves the bug forward, putting a flower into the location it previously
     * occupied.
     * line 64 to 69 is new.
     * delete the last two lines.
     */
    public void move()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
        {
            return;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (gr.isValid(next)) 
        {
            next = next.getAdjacentLocation(getDirection());
            if (gr.isValid(next)) {
                moveTo(next);
            } else {
                removeSelfFromGrid();
            }
        }
        else
        {
            removeSelfFromGrid();
        }
    }

    /**
     * Tests whether this bug can move forward into a location that is empty or
     * contains a flower.
     * @return true if this bug can move.
     * line 97 is new.
     */
    public boolean canMove()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null) 
        {
            return false;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        next = next.getAdjacentLocation(getDirection()); 
        if (!gr.isValid(next))
        {
            return false;
        }
        Actor neighbor = gr.get(next);
        
        return (neighbor == null);
        // ok to move into empty location
        // not ok to move onto any other actor
    }
}


