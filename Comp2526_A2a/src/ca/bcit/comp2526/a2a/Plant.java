package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.Point;

/**
 * An actor, represents a plant. herbivores can eat this.
 * @author  Armin T - A00942927
 * @version Feb 18, 2016 - v1.0
 *
 */
public class Plant extends Actor implements HerbivoresCanEat, 
        OmnivoresCanEat{

    /**
     * creates a plant and places it in a cell.
     * @param cell cell this plant lives in.
     */
    public Plant(Cell cell) {
        super(cell);
        mustEatBy = 10;
        color = Color.GREEN;
    }
    
    /**
     * Creates a seedling if 2 adjacent cells are empty and 3 adjacent
     * cells are plants.
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
        
        int empty = 0;
        int plants = 0;
        
        for (int i = 0; i < adj.length; i++) {
            if (adj[i].getActor() instanceof Plant) {
                plants++;
            } else if (adj[i].getActor() == null) {
                empty++;
            }
        }
        
        if (empty >= 2 && plants >= 3) {
            int rand = RandomGenerator.nextNumber(adj.length);
            
            if (adj[rand] == null) {
                return;
            }
                
            Actor seedling = new Plant(adj[rand]);
            adj[rand].setActor(seedling);
            adj[rand].setBackground(color);
            
        }
        
    }
    
    /**
     * pains the cell green.
     */
    void init() {
        pos.setBackground(color);
    }
}
