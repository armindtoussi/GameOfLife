package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.Point;

/**
 * abstract representation of an Actor.
 * @author Armin T - A00942927
 * @version Feb 18, 2016 - v1.0
 *
 */
public abstract class Actor {

    /**Cell location of the actor. */
    protected Cell pos;
    /**Tracks if an actor has moved this turn. */
    protected boolean moved = false;
    /**When the last time the actor ate. */
    protected int lastEaten = 0;
    /**How long the actor has to eat before it dies. */
    protected int mustEatBy = 0; 
    /**Is actor alive. */
    private boolean alive = true; 
    /**Has actor reproduced this turn. */
    protected boolean reproduced = false;
    /**Color of the actor. */
    protected Color color;
    
    /**
     * Constructs the actor with a cell location.
     * @param cell cell in which the actor resides.
     */
    public Actor(Cell cell) {
        pos = cell;
    }
    
    /**
     * causes actor to eat.
     * @param act takes an actor 
     */
    public void eat(Actor act) {
        
    }
   
    /**
     *  allows the actor to give birth. 
     */
    public void procreate() {
        
    }
    
    /**
     * passed the value of lastEaten.
     * @return lastEaten
     */
    public int getLastEaten() {
        return lastEaten;
    }
    
    /**
     * allows the actor to move.
     */
    public void move() {
        
    }
    
    /**
     * gets the color of the actor.
     * @return color
     */
    public Color getColor() {
        return color;
    }
    
    /**
     * updates the lastEaten variable and changes alive to false if 
     * necessary.
     */
    public void updateEaten() {
        if (++lastEaten > mustEatBy) {
            alive = false;
        }
    }
    
    /**
     * returns alive boolean.
     * @return alive
     */
    public boolean isAlive() {
        return alive;
    }
    
    /**
     * sets the status of the actor.
     */
    public void setStatus(boolean living) {
        alive = living;
    }
    
/*    *//**
     * kills the actor.
     *//*
    public void kill() {
        alive = false;
    }*/
    
    /**
     * resets the moved boolean.
     */
    public void resetMoved() {
        moved = false;
    }
    
    /**
     * resets the reproduced variable.
     */
    public void resetReproduced() {
        reproduced = false;
    }

    /**
     * sets the position of the actor on the board.
     * @param cell the actor resides in.
     */
    public void setPos(Cell cell) {
        pos = cell;
    }
    
    /**
     * gets the position of the actor on the board.
     * @return pos 
     */
    public Cell getPos() {
        return pos;
    }
    
    
}
