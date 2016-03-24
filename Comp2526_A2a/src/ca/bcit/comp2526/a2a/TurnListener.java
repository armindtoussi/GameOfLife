package ca.bcit.comp2526.a2a;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * coordinates the moves.
 * @author Armin T - A00942927
 * @version Feb 18, 2016 - v1.0
 *
 */
public class TurnListener extends MouseAdapter {

    /**a gameframe obj. */
    GameFrame gameFrame;
    
    /**
     * creates a turn listener with a gameframe.
     * @param gameFrame the frame that is passed in.
     */
    public TurnListener(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }
    
    /**
     * controls the mouse clicked event.
     */
    @Override
    public void mouseClicked(MouseEvent event) {
        System.out.println("Mouse clicked");
        gameFrame.takeTurn();
        
    }

  

}
