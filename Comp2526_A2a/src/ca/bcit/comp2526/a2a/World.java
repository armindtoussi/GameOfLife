package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

/**
 * the world upon which cells sit.
 * @author Armin T - A00942927  
 * @version Feb 18, 2016 - v1.0
 *
 */
public class World {

    /**10% chance. */
    private static final int TEN_PERCENT   = 10;
    /**30% chance. */
    private static final int THIRY_PERCENT = 40;
    /**random max for chance. */
    private static final int MAX = 100;
    
    /**2d array that represents the board. */
    private Cell[][] cells;
    
    /**number of rows on board. */
    private int rows;
    /**number of cols on board. */
    private int cols;
    
    
    /**
     * creates a world of size specified rows and cols.
     * @param rows number of rows.
     * @param cols number of cols.
     */
    public World(int rows, int cols) {
        this.rows = rows;
        this.cols = cols; 
        cells = new Cell[rows][cols];
    }
    
    /**
     * moves the actor.
     */
    public void takeTurn() {
        moveActor();
    }
    
    /**
     * initializes and populates the 2d array and places 
     * animals and plants on the world.
     */
    public void init() {
        RandomGenerator.reset();
        int rand = 0;
        
        for ( int i = 0; i < rows; i++) {
            for ( int j = 0; j < cols; j++) {
                rand = RandomGenerator.nextNumber(MAX);
                cells[i][j] = new Cell(this);
                cells[i][j].setCell(i, j);
                
                //10% chance to create an herbivore
                if (rand < TEN_PERCENT) {
                    //Actor herb = new Herbivore(cells[i][j]);
                    cells[i][j].setActor(new Herbivore(cells[i][j]));
                    //cells[i][j].setOccupied(true);
                    //herb.setCell(i, j);
                    cells[i][j].setBackground(cells[i][j].getActor().color);
                    
                 //a 30% chance to create a plant
                } else if (rand < THIRY_PERCENT) {
                    //Actor plant = new Plant(cells[i][j]);
                    cells[i][j].setActor(new Plant(cells[i][j]));
                    //plant.setCell(i,  j);
                    cells[i][j].setBackground(cells[i][j].getActor().color);
                } else if (rand < 50) {
                    cells[i][j].setActor(new Carnivore(cells[i][j]));
                    cells[i][j].setBackground(cells[i][j].getActor().color);
                } else if (rand < 60) {
                    cells[i][j].setActor(new Omnivores(cells[i][j]));
                    cells[i][j].setBackground(cells[i][j].getActor().color);
                }
            }
        }
    }
    
    /**
     * Moves each actor in all cells.
     */
    private void moveActor() {
        
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if ((cells[i][j].getActor() != null) 
                    && (cells[i][j].getActor() instanceof Movable) 
                    && cells[i][j].getActor().isAlive()) {
                    cells[i][j].getActor().move();
                        
                } else if (cells[i][j].getActor() instanceof Plant) {
                    cells[i][j].getActor().updateEaten();
                   
                }
                
                if (cells[i][j].getActor() != null) {
                    cells[i][j].getActor().procreate();
                }
                  /*  Color colo = cells[i][j].getActor().getColor();
                    Color newColor = colo.darker();
                    cells[i][j].setBackground(newColor);*/
            }
        }
        
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].getActor() != null) {
                    cells[i][j].getActor().resetMoved();
                    cells[i][j].getActor().resetReproduced();
                    
                    if (!cells[i][j].getActor().isAlive()) {
                        cells[i][j].removeActor();
                        cells[i][j].setBackground(Color.WHITE);
                    }
                }
            }
        }
        
    }
    
    /**
     * grabs a cell specified by the rows and cols params
     * @param row row number.
     * @param col col number.
     * @return the specified cell.
     */
    public Cell getCellAt(int row, int col) {
        if ((row >= 0) && row < rows) {
            if (col >= 0 && col < cols) {
                return cells[row][col];

            }
        }
        return null;
    }

    /**
     * gets number of rows.
     * @return number of rows.
     */
    public int getRowCount() {
        return rows;
    }

    /**
     * gets number of cols.
     * @return number of cols.
     */
    public int getColumnCount() {
        return cols;
    }

    /**
     * gets 2d cell array.
     * @return 2d cells array.
     */
    public Cell[][] getCells() {
        return cells;
    }

}




