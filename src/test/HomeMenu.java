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
package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class HomeMenu extends JComponent implements MouseListener, MouseMotionListener {

    //Assign Shape
    private Rectangle menuFace;
    private Rectangle startButton;
    private Rectangle menuButton;


    //Font datatype
    private Font greetingsFont;
    private Font gameTitleFont;
    private Font creditsFont;
    private Font buttonFont;

    //Assign datatype of the GameFrame(class)
    private GameFrame owner;

    //Assign boolean to the
    private boolean startClicked;
    private boolean menuClicked;

    //Constructor (HomeMenu)
    public HomeMenu(GameFrame owner,Dimension area){
        //setFocusable() = try to focus next field to prepare it to receive text, gives the ability to potentially gain the focus to the component.
        this.setFocusable(true);

        // gets the focus for the component on which it is called only when its top-level ancestor is the focused window.
        this.requestFocusInWindow();

        //notified whenever you change the state of mouse
        this.addMouseListener(this);
        //notified whenever you move or drag mouse.
        this.addMouseMotionListener(this);

        this.owner = owner;

        //Create a rectangle object
        menuFace = new Rectangle(new Point(0,0),area);

        //(also its related setMinimumSize and setMaximumSize) -- use when a parent layout manager exists.
        this.setPreferredSize(area);

        //Create an button object
        Dimension btnDim = new Dimension(area.width / 3, area.height / 12);
        startButton = new Rectangle(btnDim);
        menuButton = new Rectangle(btnDim);

        /**
        //Create a borderstoke object
        borderStoke = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0,DASHES,0);
        borderStoke_noDashes = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
        **/
        //Create Font style,size object
        greetingsFont = new Font("Noto Mono",Font.PLAIN,25);
        gameTitleFont = new Font("Noto Mono",Font.BOLD,40);
        creditsFont = new Font("Monospaced",Font.PLAIN,10);
        buttonFont = new Font("Monospaced",Font.PLAIN,startButton.height-2);

    }

    //Method to draw the Menu pages
    //Parameter is graphic context
    public void paint(Graphics g){
        HomeMenuDraw homeMenuDraw = new HomeMenuDraw(owner);
        homeMenuDraw.setMenuFace(menuFace);
        homeMenuDraw.setFontStyle(greetingsFont,gameTitleFont,creditsFont,buttonFont);
        homeMenuDraw.setButton(startButton,menuButton);
        homeMenuDraw.setClick(startClicked,menuClicked);
        homeMenuDraw.drawMenu((Graphics2D)g);

        //drawMenu((Graphics2D)g);
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
            repaint(startButton.x,startButton.y,startButton.width+1,startButton.height+1);

        }
        else if(menuButton.contains(p)){
            menuClicked = true;
            repaint(menuButton.x,menuButton.y,menuButton.width+1,menuButton.height+1);
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(startClicked ){
            startClicked = false;
            repaint(startButton.x,startButton.y,startButton.width+1,startButton.height+1);
        }
        else if(menuClicked){
            menuClicked = false;
            repaint(menuButton.x,menuButton.y,menuButton.width+1,menuButton.height+1);
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
        if(startButton.contains(p) || menuButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());

    }
}
