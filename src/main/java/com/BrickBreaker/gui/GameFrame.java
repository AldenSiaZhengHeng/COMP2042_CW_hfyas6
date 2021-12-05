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
package com.BrickBreaker.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

/**
 * This is the GameFrame class
 * @author Alden Sia Zheng Heng
 * @version 1.0
 * @since 3/11/2021
 */
public class GameFrame extends JFrame implements WindowFocusListener {
    //Game Title on window
    private static final String DEF_TITLE = "Brick Destroy";

    //Object for Game and HomeMenu
    private GameModel gameModel;
    private GameView gameView;
    private GameController gameController;

    private HomeMenuController homeMenuController;
    private HomeMenuModel homeMenuModel;
    private HomeMenuView homeMenuView;

    //To determine window focus
    private boolean gaming;

    //Object for creating icon
    private Image icon;

    //Add new object about instruction
    private Instruction instruction;

    /**
     * The constructor of GameFrame class
     * Create object for the classes used
     */
    public GameFrame(){
        super();
        gaming = false;
        this.setLayout(new BorderLayout());

        //Add Icon images
        icon = Toolkit.getDefaultToolkit().getImage("src/com/BrickBreaker/Images/brick_Icon.jpg");
        icon = icon.getScaledInstance(120,120, java.awt.Image.SCALE_SMOOTH);
        this.setIconImage(icon);

        //new object created
        gameModel = new GameModel(new Rectangle(0,0,600,450));
        gameView = new GameView();
        gameController = new GameController(this, gameModel,gameView);

        homeMenuModel = new HomeMenuModel();
        homeMenuView = new HomeMenuView();
        homeMenuController = new HomeMenuController(this,new Dimension(600,450),homeMenuModel,homeMenuView);//450, 300

        instruction = new Instruction(this,new Dimension(600,450));

        //Add object to the container
        this.add(homeMenuController,BorderLayout.CENTER);

        //Disable the function to adjust the size of frame
        this.setResizable(false);

    }

    /**
     * This method will set the title and location of the game and make it visible
     */
    public void initialize(){
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.autoLocate();
        this.setVisible(true);
    }

    /**
     * Add the homemenu pages to the frame and show
     */
    public void enableHomeMenu(){
        this.dispose();
        this.remove(instruction);
        this.add(homeMenuController,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();

    }

    /**
     * Add the GameBoard to the frame and show
     */
    public void enableGameBoard(){
        this.dispose();
        this.remove(homeMenuController);
        this.add(gameController,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);

    }

    /**
     * Add the instruction page to the frame and show
     */
    public void enableInstructionPanel(){
        this.dispose();
        this.remove(homeMenuController);
        this.add(instruction,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
    }


    /**
     * Reallocate the frame on the middle of the screen when pop out
     */
    private void autoLocate(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
    }


    /**
     * To determine whether the focus on the frame/window is lost
     * @param windowEvent The object of window event
     */
    @Override
    public void windowGainedFocus(WindowEvent windowEvent) {
        /*
            the first time the frame loses focus is because
            it has been disposed to install the GameBoard,
            so went it regains the focus it's ready to play.
            of course calling a method such as 'onLostFocus'
            is useful only if the GameBoard as been displayed
            at least once
         */
        gaming = true;
    }

    /**
     * Call the method to show the message when the focus is lost
     * @param windowEvent The object of window event
     */
    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        if(gaming)
            gameController.onLostFocus();

    }
}
