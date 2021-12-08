/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.BrickBreaker.debug;

import com.BrickBreaker.element.ball.Ball;
import com.BrickBreaker.gui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * This is the DebugConsole class to create the pop put of the debug panel
 * @author Alden Sia Zheng Heng
 * @version 1.0
 * @since 3/11/2021
 */
public class DebugConsole extends JDialog implements WindowListener{

    private static final String TITLE = "Debug Console";
    private JFrame owner;
    private DebugPanel debugPanel;
    private GameController gameController;
    private GameModel gameModel;


    /**
     * The construtor of the DebugConsole class
     * Assign the parameter to the variable
     * Call the method to initialize the panel
     * @param owner The object of the Jframe
     * @param gameModel The object of the GameModel
     * @param gameController The object of the GameController
     */
    public DebugConsole(JFrame owner, GameModel gameModel, GameController gameController){

        this.gameModel = gameModel;
        this.owner = owner;
        this.gameController = gameController;
        initialize();

        debugPanel = new DebugPanel(gameModel);
        this.add(debugPanel,BorderLayout.CENTER);

        this.pack();
    }

    /**
     * This method will set the title of the dialog pop out and show on the preferred size
     */
    private void initialize(){
        this.setModal(true);
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.addWindowListener(this);
        this.setFocusable(true);
    }


    /**
     * This method will allocate the pop out panel on the middle of the screen
     */
    private void setLocation(){
        int x = ((owner.getWidth() - this.getWidth()) / 2) + owner.getX();
        int y = ((owner.getHeight() - this.getHeight()) / 2) + owner.getY();
        this.setLocation(x,y);
    }


    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    /**
     * This will update the screen when the debug panel is closed
     * @param windowEvent The status of the window
     */
    @Override
    public void windowClosing(WindowEvent windowEvent) {
        gameController.repaint();
    }

    /**
     * Unused method
     * @param windowEvent
     */
    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    /**
     * Unused method
     * @param windowEvent
     */
    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    /**
     * Unused method
     * @param windowEvent
     */
    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    /**
     * Activate the dialog and set the ball speed based on the slider changes
     * @param windowEvent The status of the window
     */
    @Override
    public void windowActivated(WindowEvent windowEvent) {
        setLocation();
        Ball b = gameModel.getBall();
        debugPanel.setValues(b.getSpeedX(),b.getSpeedY());
    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
