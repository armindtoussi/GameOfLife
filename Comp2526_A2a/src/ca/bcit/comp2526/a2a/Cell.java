package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * <p>represents a cell in the world, can be a plant, animal or 
 *    possibly other.</p>
 * @author Armin T - A00942927  
 * @version Feb 18, 2016 - v1.0
 *
 */
public class Cell extends JPanel{

    
    /**the cell's position. */
    protected Point position;
    /**world obj for communication. */
    protected World world;
    
    /**close by cells. */
    private ArrayList<Point> adjacents;
    /**all the cells in the world. */
    protected Cell[][] cells;
    
    /**if a cell has a movable actor. */
    private boolean occupied;
    /**Actor that occupies this cell. */
    private Actor actor;
    
    /**
     * builds a cell sets it's border and world and paints it.
     * @param world passed in for communication.
     */
    public Cell(World world) {
        this.world = world;
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.init();
    }
    
    /**
     * sets the color.
     */
    public void init() {
        setBackground(Color.WHITE);
    }
    
    /**
     * sets the actor.
     * @param actor the actor that lives in the cell.
     */
    public void setActor(Actor actor) {
        this.actor = actor;
    }
    
    /**
     * gets the actor in the cell.
     * @return the actor that resides here.
     */
    public Actor getActor() {
        return actor;
    }
    
    /**
     * removes the actor from the cell. and paints it white.
     */
    public void removeActor() {
        this.actor = null;
        //this.init();
    }
    
    /**
     * sets if a cell is occupied by an animal.
     * @param occupied boolean true or false.
     */
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
    
    /**
     * gets occupied boolean.
     * @return occupied boolean
     */
    public boolean getOccupied() {
        return occupied;
    }
    
    /**
     * gets the location.
     * @return the position.
     */
    public Point getLocation() {
        return position;
    }
    
    /**
     * tells the cell where it is located.
     * @param row location.
     * @param col location.
     */
    public void setCell(int row, int col) {
        if (row > -1 && col > -1) {
            position = new Point(row, col);
        }
    }
    
    /**
     * gets the 2d cell array from world.
     */
    public void getCellsArray() {
        cells = world.getCells();
    }
    
    /**
     * passes cells array to Herbivore.
     * @return a 2d array of cells.
     */
    public Cell[][] passCellsArray() {
        return cells;
    }
    
    /**
     * <p>Gets adjacent cells relative to this cell.</p>
     * @return an array of adjacent cells
     */
    public Cell[] getAdjacentCells() {
        ArrayList<Cell> adj = new ArrayList<Cell>();
                
        int xpos = position.x;
        int ypos = position.y;
        int tempX = 0;
        int tempY = 0;
        
        Cell tempCell = null; 
        
        for (int p = -1; p <= 1; p++) {
            for (int q = -1; q <= 1; q++) {
                tempX = xpos + p;
                tempY = ypos + q;
                
                tempCell = world.getCellAt(tempX, tempY);
                
                if (tempCell != null) {
                    adj.add(tempCell);
                }
            }
        }
        
        adj.remove(this);
        Cell[] adjacents = new Cell[1];
        return adj.toArray(adjacents);
    }
    

}









