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

//unused import statement


public class GameFrame extends JFrame implements WindowFocusListener {

    private static final String DEF_TITLE = "Brick Destroy";

    private GameModel gameModel;
    private GameView gameView;
    private GameController gameController;

    private HomeMenuController homeMenuController;
    private HomeMenuModel homeMenuModel;
    private HomeMenuView homeMenuView;

    private boolean gaming;

    private Image icon;

    //Add new object about instruction
    private Instruction instruction;

    public GameFrame(){
        super();
        gaming = false;

        this.setLayout(new BorderLayout());
        //Add Icon images
        icon = Toolkit.getDefaultToolkit().getImage("src/com/BrickBreaker/Images/brick_Icon.jpg");
        icon = icon.getScaledInstance(120,120, java.awt.Image.SCALE_SMOOTH);
        this.setIconImage(icon);

        gameModel = new GameModel(new Rectangle(0,0,600,450));
        gameView = new GameView();
        gameController = new GameController(this, gameModel,gameView);

        homeMenuModel = new HomeMenuModel();
        homeMenuView = new HomeMenuView();
        homeMenuController = new HomeMenuController(this,new Dimension(600,450),homeMenuModel,homeMenuView);//450, 300

        //Add object to the container
        this.add(homeMenuController,BorderLayout.CENTER);

        //This will hide the frame on homemenu
        //this.setUndecorated(true);

        //Add instruction page
        instruction = new Instruction(this,new Dimension(600,450));
    }

    public void initialize(){
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.autoLocate();
        this.setVisible(true);
    }

    public void enableHomeMenu(){
        this.dispose();
        this.remove(instruction);
        this.add(homeMenuController,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        //this.addWindowFocusListener(this);
    }

    public void enableGameBoard(){
        this.dispose();
        this.remove(homeMenuController);
        this.add(gameController,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);

    }

    public void enableInstructionPanel(){
        this.dispose();
        this.remove(homeMenuController);
        this.add(instruction,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        //this.addWindowFocusListener(this);
    }


    private void autoLocate(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
    }


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

    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        if(gaming)
            gameController.onLostFocus();

    }
}
