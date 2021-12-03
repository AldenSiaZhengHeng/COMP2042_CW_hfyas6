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

public class HomeMenuController extends JComponent implements MouseListener, MouseMotionListener {

    //Assign Shape
    private Rectangle menuFace;
    private Rectangle startButton;
    private Rectangle menuButton;

    //Assign datatype of the GameFrame (class)
    private GameFrame owner;

    //Assign boolean to the
    private boolean startClicked;
    private boolean menuClicked;


    //Add instruction button and com.BrickBreaker.score button
    private Rectangle instructionButton;
    private boolean instructionClicked;

    //HomeMenumodel
    private HomeMenuModel homemenuModel;
    private HomeMenuView homeMenuView;

    private Dimension area;
    //Constructor (HomeMenu)
    public HomeMenuController(GameFrame owner, Dimension area){

        this.owner = owner;
        this.area = area;
        homemenuModel = new HomeMenuModel();
        homeMenuView = new HomeMenuView(this);

        homemenuModel.setHomemenuFace(new Rectangle(new Point(0,0),area));
        homeMenuView.initialize(area);
        homemenuModel.setButtonDimension(new Dimension(area.width / 2, area.height / 12));
        homemenuModel.setButtonDimension();

    }


    //Method to draw the Menu pages
    //Parameter is graphic context
    public void paint(Graphics g){
        homeMenuView.setMenuFace(homemenuModel.getHomemenuFace());
        homeMenuView.setButton(homemenuModel.getStartButton(), homemenuModel.getExitButton(), homemenuModel.getInstructionButton());
        homeMenuView.setClick(startClicked,menuClicked,instructionClicked);
        homeMenuView.drawMenu((Graphics2D)g);
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        //if(startButton.contains(p)){
        if(homemenuModel.getStartButton().contains(p)){
           owner.enableGameBoard();

        }
        //else if(menuButton.contains(p)){
        else if(homemenuModel.getExitButton().contains(p)){
            System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
        }
        //else if(instructionButton.contains(p)){
        else if(homemenuModel.getInstructionButton().contains(p)){
            owner.enableInstructionPanel();
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        //if(startButton.contains(p)){
        if(homemenuModel.getStartButton().contains(p)){
            startClicked = true;
            startButtonRepaint();

        }
        //else if(menuButton.contains(p)){
        else if(homemenuModel.getExitButton().contains(p)){
            menuClicked = true;
            menuButtonRepaint();
        }

        //Add instruction function
       // else if(instructionButton.contains(p)){
        else if(homemenuModel.getInstructionButton().contains(p)){
            instructionClicked = true;
            instructionButtonRepaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(startClicked ){
            startClicked = false;
            startButtonRepaint();
        }
        else if(menuClicked){
            menuClicked = false;
            menuButtonRepaint();
        }
        else if(instructionClicked){
            instructionClicked = false;
            instructionButtonRepaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }


    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        //if(startButton.contains(p) || menuButton.contains(p) || instructionButton.contains(p))
        if(homemenuModel.getStartButton().contains(p) || homemenuModel.getExitButton().contains(p) || homemenuModel.getInstructionButton().contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());

    }

    //Create new method for repaint startButton and menuButton
    public void startButtonRepaint(){
       // repaint(startButton.x,startButton.y,startButton.width+1,startButton.height+1);
        repaint(homemenuModel.getStartButton().x,homemenuModel.getStartButton().y,homemenuModel.getStartButton().width+1,homemenuModel.getStartButton().height+1);
    }

    public void menuButtonRepaint(){
        //repaint(menuButton.x,menuButton.y,menuButton.width+1,menuButton.height+1);
        repaint(homemenuModel.getExitButton().x,homemenuModel.getExitButton().y,homemenuModel.getExitButton().width+1,homemenuModel.getExitButton().height+1);
    }

    public void instructionButtonRepaint(){
        repaint(homemenuModel.getInstructionButton().x,homemenuModel.getInstructionButton().y,homemenuModel.getInstructionButton().width+1,homemenuModel.getInstructionButton().height+1);
    }
}
