package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.Point;
import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;

/**
 * Herbivore is an actor. 
 * @author Armin T - A00942927
 * @version Feb 18, 2016 - v1.0
 *
 */
public class Herbivore extends Actor implements Movable, 
    CarnivoresCaneEat, OmnivoresCanEat {


    /**The position of the cell this actor resides in. */
    private Point position;
    /**The current life of the herbie. */
    private int life;
    
    /**
     * creates an herbivore and places it in a cell.
     * @param cell the cell this actor resides in.
     */
    public Herbivore(Cell cell) {
        super(cell);
        mustEatBy = 10;
        color = color.YELLOW;
    }
    
    /**
     * sets the color of the cell this actor resides 
     * in to yellow.
     */
    public void init() {
        pos.setBackground(color);
    }

    /**
     * gets the life of this actor.
     */
    public int getLife() {
        return life;
    }
    
    /**
     * Moves the actor to another cell.
     */
    public void move() {
        if (moved) {
            return;
        }
            
        moved = true;
        Cell[] adj = pos.getAdjacentCells();
        
        if (adj.length <= 0) {
            return;
        }
        
        int rand = RandomGenerator.nextNumber(adj.length);
        
        if (adj[rand] == null) {
            return;
        }
        
        Actor act = adj[rand].getActor();
        eat(act); //eat if appropriate actor to eat is available.
        updateEaten();
        Cell curPos = getPos();
        //the check style here is problematic, we discussed in class, 
        // i can't move this any lower because it is temporarily 
        ///holding the position of the actor and i can't lose it.
        setPos(adj[rand]);
        adj[rand].setActor(this);
        adj[rand].setBackground(color);
        curPos.setActor(null);
        curPos.setBackground(Color.WHITE);
    }
    
    /**
     * resets the last time an herbivore has eaten.
     */
    public void eat(Actor act) {
        if (act instanceof HerbivoresCanEat) {
            lastEaten = 0;
        }
    }
    
    /**
     * creates a new Herbivore if 2 food cells adjacent, 1 empty 
     * adjacent cells, and one other herbivore adjacent.
     */
    public void procreate() {
        if (reproduced) {
            return;
        }
            
        reproduced = true;
        Cell[] adj = pos.getAdjacentCells();
        
        if (adj.length <= 0) {
            return;
        }
            
        int herbies = 0;
        int empty = 0;
        int foods = 0;
        
        for (int i = 0; i < adj.length; i++) {
            if (adj[i].getActor() instanceof HerbivoresCanEat) {
                foods++;
            } else if (adj[i].getActor() instanceof Herbivore) {
                herbies++;
            } else if (adj[i].getActor() == null) {
                empty++;
            }
        }
        
        if (foods >= 2 && empty >= 1 && herbies >= 1) {
            int rand = RandomGenerator.nextNumber(adj.length);
            
            if (adj[rand] == null) {
                return;
            }
                
            Actor babyHerb = new Herbivore(adj[rand]);
            adj[rand].setActor(babyHerb);
            adj[rand].setBackground(color);
        }
    }
}












