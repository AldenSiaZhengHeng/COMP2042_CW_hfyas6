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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * This is the HomeMenuController class to call the data in HomeMenuModel class and call the method from HomeMenuView class to show the HomeMenu pages in frame.
 * The HomeMenu class is renamed as HomeMenuController to act as a controller class to adhere mvc pattern
 * @author Alden Sia Zheng Heng
 * @version 1.0
 * @since 3/11/2021
 */
public class HomeMenuController extends JComponent implements MouseListener, MouseMotionListener {


    //Assign datatype of the GameFrame (class)
    private GameFrame owner;

    //Variable to determine the button clicked
    private boolean startClicked;
    private boolean exitClicked;
    private boolean instructionClicked;

    //The object created
    private HomeMenuModel homeMenuModel;
    private HomeMenuView homeMenuView;

    private Dimension area;

    /**
     * The Constructor of the HomeMenuController to create object and assign variable.
     * @param owner The current object of the GameFrame
     * @param area The dimension area for set the preferred size for the homemenu
     * @param homemenuModel The object of the HomeMenuModel
     * @param homemenuView The object of the HomeMenuView
     */
    public HomeMenuController(GameFrame owner, Dimension area,HomeMenuModel homemenuModel, HomeMenuView homemenuView){
        this.owner = owner;
        this.area = area;
        this.homeMenuModel = homemenuModel;
        this.homeMenuView = homemenuView;

        homeMenuView.initialize(this,area);
        homeMenuModel.setHomemenuFace(new Rectangle(new Point(0,0),area));
        homeMenuModel.setButtonDimension(new Dimension(area.width / 2, area.height / 12));
        homeMenuModel.setButtonDimension();

    }


    /**
     * To paint and draw the homemenu
     * @param g The object of Graphics context
     */
    public void paint(Graphics g){
        homeMenuView.setMenuFace(homeMenuModel.getHomemenuFace());
        homeMenuView.setButton(homeMenuModel.getStartButton(), homeMenuModel.getExitButton(), homeMenuModel.getInstructionButton());
        homeMenuView.setClick(startClicked,exitClicked,instructionClicked);
        homeMenuView.drawMenu((Graphics2D)g);
    }


    /**
     * Go to different pages based on the button clicked and released
     * @param mouseEvent Mouse action object
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        //Go to gameboard
        if(homeMenuModel.getStartButton().contains(p)){
           owner.enableGameBoard();

        }
        //Exit the game
        else if(homeMenuModel.getExitButton().contains(p)){
            System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
        }
        //Go to instruction pages
        else if(homeMenuModel.getInstructionButton().contains(p)){
            owner.enableInstructionPanel();
        }
    }

    /**
     * Show the special effect when the button is pressed
     * @param mouseEvent Mouse action object
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(homeMenuModel.getStartButton().contains(p)){
            startClicked = true;
            startButtonRepaint();

        }
        else if(homeMenuModel.getExitButton().contains(p)){
            exitClicked = true;
            exitButtonRepaint();
        }
        else if(homeMenuModel.getInstructionButton().contains(p)){
            instructionClicked = true;
            instructionButtonRepaint();
        }
    }

    /**
     * Show the special effect when the button pressed is released
     * @param mouseEvent Mouse action object
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(startClicked ){
            startClicked = false;
            startButtonRepaint();
        }
        else if(exitClicked){
            exitClicked = false;
            exitButtonRepaint();
        }
        else if(instructionClicked){
            instructionClicked = false;
            instructionButtonRepaint();
        }
    }

    /**
     * Unused method
     * @param mouseEvent
     */
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    /**
     * Unused method
     * @param mouseEvent
     */
    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }


    /**
     * Unused method
     * @param mouseEvent
     */
    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    /**
     * To detect whether the mouse cursor is pointed at the button
     * @param mouseEvent The mouse action object
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(homeMenuModel.getStartButton().contains(p) || homeMenuModel.getExitButton().contains(p) || homeMenuModel.getInstructionButton().contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());

    }


    /**
     * To show the special effect on the startbutton
     */
    public void startButtonRepaint(){
        repaint(homeMenuModel.getStartButton().x,homeMenuModel.getStartButton().y,homeMenuModel.getStartButton().width+1,homeMenuModel.getStartButton().height+1);
    }

    /**
     * To show the special effect on the the exitbutton
     */
    public void exitButtonRepaint(){
        repaint(homeMenuModel.getExitButton().x,homeMenuModel.getExitButton().y,homeMenuModel.getExitButton().width+1,homeMenuModel.getExitButton().height+1);
    }

    /**
     * To show the special effect on the instructionbutton
     */
    public void instructionButtonRepaint(){
        repaint(homeMenuModel.getInstructionButton().x,homeMenuModel.getInstructionButton().y,homeMenuModel.getInstructionButton().width+1,homeMenuModel.getInstructionButton().height+1);
    }
}
