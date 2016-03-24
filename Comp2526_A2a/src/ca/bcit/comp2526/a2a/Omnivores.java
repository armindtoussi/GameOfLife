package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.Point;

/**
 * Represents an omnivore animal.
 * @author Armin T - A00942927
 * @version March 18, 2016 - v1.0
 *
 */
public class Omnivores extends Actor implements Movable {

    /**
     * Creates an omnivore with 2 life and blue color.
     * @param cell the cell the actor is in.
     */
    public Omnivores(Cell cell) {
        super(cell);
        mustEatBy = 2;
        color = Color.BLUE;
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
        eat(act);
        updateEaten();
        Cell curPos = getPos();
        //the check style here is problematic, we discussed in class, 
        // i can't move this any lower because it is temporarily 
        ///holding the position of the actor and i can't lose it.
        setPos(adj[rand]);
        adj[rand].setActor(this);
        adj[rand].setBackground(color);
        //Cell curPos = getPos();
        curPos.setActor(null);
        curPos.setBackground(Color.white);
            
    }
    
    /**
     * Allows the actor to eat and updates lastEaten.
     */
    public void eat(Actor act) {
        if (act instanceof OmnivoresCanEat) {
            lastEaten = 0;
            
        }
    }
    
    /**
     * creates a baby omnivore when 3 food, 3 empty, and one omnivore 
     * is adjacent.
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
            
        int omnies = 0;
        int emptys = 0;
        int foods = 0;
        
        for (int i = 0; i < adj.length; i++) {
            if (adj[i].getActor() instanceof OmnivoresCanEat) {
                foods++;
            } else if (adj[i].getActor() instanceof Omnivores) {
                omnies++;
            } else if (adj[i].getActor() == null) {
                emptys++;
            }
        }
        
        if (foods >= 3 && emptys >= 3 && omnies >= 1) {
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










