package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.Point;

/**
 * Represents a carnivore.
 * @author Armin T - A00942927 
 * @version March 17, 2016  v1.0
 *
 */
public class Carnivore extends Actor implements Movable, OmnivoresCanEat {
    
    /**
     * creates a carnivore object, sets the position, mustEatBy var, 
     * and color.
     * @param cell - cell it resides in.
     */
    public Carnivore(Cell cell) {
        super(cell);
        mustEatBy = 3;
        color = Color.MAGENTA;
    }
    
    /**
     * sets the back ground color of the cell it resides in.
     */
    public void init() {
        pos.setBackground(color);
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
        if (act instanceof CarnivoresCaneEat) {
            lastEaten = 0;
        }
    }
    
    /**
     * creates a new Carnivore if there are 3 adjacent food cells, and 
     * 3 adjacent empty cells and one adjacent carnivore.
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
            
        int carnies = 0;
        int emptys = 0;
        int foods = 0;
        
        for (int i = 0; i < adj.length; i++) {
            if (adj[i].getActor() instanceof CarnivoresCaneEat) {
                foods++;
            } else if (adj[i].getActor() instanceof Carnivore) {
                carnies++;
            } else if (adj[i].getActor() == null) {
                emptys++;
            }
        }
        
        if (foods >= 3 && emptys >= 3 && carnies >= 1) {
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
