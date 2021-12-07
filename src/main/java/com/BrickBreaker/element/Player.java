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
package com.BrickBreaker.element;

import com.BrickBreaker.element.ball.Ball;

import java.awt.*;

/**
 * This is the Player class to set and create the player's board
 * @author Alden Sia Zheng Heng
 * @version 1.0
 * @since 3/11/2021
 */
public class Player {
    //Variable to set the color
    public static final Color BORDER_COLOR = Color.GREEN.darker().darker();
    public static final Color INNER_COLOR = Color.GREEN;

    //Variable to store the Point and Rectangle object
    private Rectangle playerFace;
    private Point ballPoint;

    //Variable to store the value for player movement and size
    private static final int DEF_MOVE_AMOUNT = 5;
    private int moveAmount;
    private int min;
    private int max;


    /**
     * The constructor of Player class to set the value that passes from other class
     * @param ballPoint The position of the ball
     * @param width The width value to set for creating the player container
     * @param height The height value to set for creating the player container
     * @param container The area to draw the player container
     */
    public Player(Point ballPoint,int width,int height,Rectangle container) {
        this.ballPoint = ballPoint;
        moveAmount = 0;
        playerFace = makeRectangle(width, height);
        min = container.x + (width / 2);
        max = min + container.width - width;

    }

    /**
     * This method will set the position of the player container to create and pass the object of the Rectangle created
     * @param width  The width value for creating the player container
     * @param height The height value for creating the player container
     * @return The object of the Rectangle created with position,width and height
     */
    private Rectangle makeRectangle(int width,int height){
        Point p = new Point((int)(ballPoint.getX() - (width / 2)),(int)ballPoint.getY());
        return  new Rectangle(p,new Dimension(width,height));
    }

    /**
     * To detect whether the ball is collide with player container
     * @param b The object of the Ball class
     * @return True if the ball hit the player container, False if didn't hit the player container
     */
    public boolean impact(Ball b){
        return playerFace.contains(b.getPosition()) && playerFace.contains(b.getDown()) ;
    }

    /**
     * This method will control the player movement to move the player
     */
    public void move(){
        double x = ballPoint.getX() + moveAmount;
        if(x < min || x > max)
            return;
        ballPoint.setLocation(x,ballPoint.getY());
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }

    /**
     * Control the player container to move left
     */
    public void moveLeft(){
        moveAmount = -DEF_MOVE_AMOUNT;
    }

    /**
     * Control the player container to move right
     */
    public void movRight(){
        moveAmount = DEF_MOVE_AMOUNT;
    }

    /**
     * Stop the current movement of the player
     */
    public void stop(){
        moveAmount = 0;
    }

    /**
     * Getter method to get the object of the Rectangle created
     * @return The object of the Rectangle set with the position,shape and size
     */
    public Shape getPlayerFace(){
        return  playerFace;
    }

    /**
     * This method will move the player container to the start point when the game reset
     * @param p The position to set the player container
     */
    public void moveTo(Point p){
        ballPoint.setLocation(p);
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }
}
