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
package Main;

import Debug.*;
import Element.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class HomeMenu extends JComponent implements MouseListener, MouseMotionListener {

    //Assign Shape
    private Rectangle menuFace;
    private Rectangle startButton;
    private Rectangle menuButton;

    //Assign datatype of the GameFrame(class)
    private GameFrame owner;

    //Assign boolean to the
    private boolean startClicked;
    private boolean menuClicked;


    //Add instruction button and score button
    private Rectangle instructionButton;
    private boolean instructionClicked;

    //Constructor (HomeMenu)
    public HomeMenu(GameFrame owner,Dimension area){
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.owner = owner;

        //Create a rectangle object
        menuFace = new Rectangle(new Point(0,0),area);

        //(also its related setMinimumSize and setMaximumSize) -- use when a parent layout manager exists.
        this.setPreferredSize(area);

        //Create an button object
        Dimension btnDim = new Dimension(area.width / 2, area.height / 12); //3,12
        startButton = new Rectangle(btnDim);
        menuButton = new Rectangle(btnDim);
        instructionButton = new Rectangle(btnDim);
    }

    //Method to draw the Menu pages
    //Parameter is graphic context
    public void paint(Graphics g){
        HomeMenuDraw homeMenuDraw = new HomeMenuDraw(owner);
        homeMenuDraw.setMenuFace(menuFace);
        homeMenuDraw.setButton(startButton,menuButton,instructionButton);
        homeMenuDraw.setClick(startClicked,menuClicked,instructionClicked);
        homeMenuDraw.drawMenu((Graphics2D)g);
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(startButton.contains(p)){
           owner.enableGameBoard();

        }
        else if(menuButton.contains(p)){
            System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(startButton.contains(p)){
            startClicked = true;
            startButtonRepaint();

        }
        else if(menuButton.contains(p)){
            menuClicked = true;
            menuButtonRepaint();
        }

        //Add instruction function
        else if(instructionButton.contains(p)){
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
        if(startButton.contains(p) || menuButton.contains(p) || instructionButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());

    }

    //Create new method for repaint startButton and menuButton
    public void startButtonRepaint(){
        repaint(startButton.x,startButton.y,startButton.width+1,startButton.height+1);
    }

    public void menuButtonRepaint(){
        repaint(menuButton.x,menuButton.y,menuButton.width+1,menuButton.height+1);
    }

    public void instructionButtonRepaint(){
        repaint(instructionButton.x,instructionButton.y,instructionButton.width+1,instructionButton.height+1);
    }
}
