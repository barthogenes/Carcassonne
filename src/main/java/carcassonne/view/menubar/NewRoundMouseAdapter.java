package carcassonne.view.menubar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A simple mouse adapter for the new game button.
 * @author Timur Saglam
 */
public class NewRoundMouseAdapter extends MouseAdapter {
    private final MainMenuBar menuBar;

    /**
     * Simple constructor.
     * @param menuBar sets the controller
     */
    public NewRoundMouseAdapter(MainMenuBar menuBar) {
        super();
        this.menuBar = menuBar;
    }

    /**
     * Calls method on main menu bar for a new game.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        menuBar.newRound();
    }
}