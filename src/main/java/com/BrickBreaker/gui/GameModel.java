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

import com.BrickBreaker.element.*;
import com.BrickBreaker.element.ball.Ball;
import com.BrickBreaker.element.ball.RubberBall;
import com.BrickBreaker.element.brick.Brick;
import com.BrickBreaker.element.brick.Crack;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * This is the GameModel class that store the variable used for GameController and GameView class.
 * The wall class is renamed to GameModel class as it contains most of the data to generate the game.
 * @author Alden Sia Zheng Heng
 * @version 1.0
 * @since 3/11/2021
 */
public class GameModel {

    //Variable to set the random and the area of the GameBoard
    private Random random;
    private Rectangle area;

    //Object created
    private Brick[] bricks;
    private Ball ball;
    private Player player;
    private Brick[][] levels;
    private Levels create_levels;
    Crack crack;

    //Instruction for the GameBoard
    private int level;
    private int brickCount;
    private int ballCount;
    private boolean ballLost;

    //Variable to set the player, ball and brick
    private static final Point startPoint = new Point(300,430);
    private static double brickDimensionRatio = 3;
    private static int lineCount = 3;
    private Rectangle drawContainerArea;

    /**
     * The constructor of GameModel
     * @param drawArea The dimension, shape and size to draw the GameBoard
     */
    public GameModel(Rectangle drawArea){
        create_levels  = new Levels();
        this.drawContainerArea = drawArea;
        brickCount = 30;
        levels = create_levels.makeLevels(drawContainerArea,brickCount,lineCount,brickDimensionRatio);
        level = 0;
        ballCount = 3;
        ballLost = false;
        random = new Random();

        //Method to draw the ball
        makeBall((Point)startPoint.clone());

        //Set the ball speed
        int speedX,speedY;
        do{
            speedX = 5;
        }while(speedX == 0);
        do{
            speedY = -5;
        }while(speedY == 0);
        getBall().setSpeed(speedX,speedY);

        //Method to set the player start point
        setPlayer(new Player((Point) startPoint.clone(),150,10, drawArea));

        area = drawArea;
    }

    /**
     * Method to track the movement of ball and player
     */
    public void move(){
        getPlayer().move();
        getBall().move();
    }

    /**
     * This method will finds whether the balls is collided to the bricks
     */
    public void findImpacts(){
        //Bounce the ball when collide with player
        if(getPlayer().impact(getBall())){
            getBall().reverseY();
        }
        //Deduct the amount of the brick
        else if(impactWall()){
            brickCount--;
        }
        //Bounce the ball when collide with border
        else if(impactBorder()) {
            getBall().reverseX();
        }
        //Bounce the ball upward
        else if(getBall().getPosition().getY() < area.getY()){
            getBall().reverseY();
        }
        //Deduct the ball lives if the ball bounces to the bottom
        else if(getBall().getPosition().getY() > area.getY() + area.getHeight()){
            ballCount--;
            ballLost = true;
        }
    }

    /**
     * To determine whether the ball is collided with brick
     * @return The boolean variable where the brick is broke or crack
     */
    private boolean impactWall(){
        for(Brick b : getBricks()){
            switch(b.findImpact(getBall())) {
                //Vertical Impact
                case Brick.UP_IMPACT:
                    getBall().reverseY();
                    return b.setImpact(getBall().getDown(), crack.UP);
                case Brick.DOWN_IMPACT:
                    getBall().reverseY();
                    return b.setImpact(getBall().getUp(),crack.DOWN);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    getBall().reverseX();
                    return b.setImpact(getBall().getRight(),crack.RIGHT);
                case Brick.RIGHT_IMPACT:
                    getBall().reverseX();
                    return b.setImpact(getBall().getLeft(),crack.LEFT);
            }
        }
        return false;
    }

    /**
     * To determine whether the ball is collided with border
     * @return The boolean variable
     */
    private boolean impactBorder(){
        Point2D p = getBall().getPosition();
        return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
    }

    /**
     * Getter method to get the current brick info/left
     * @return Current brick left
     */
    public int getBrickCount(){
        return brickCount;
    }


    /**
     * Getter method to get the current ball count/lives
     * @return Current ball left
     */
    //Ball status - shouldn't include in wall class
    public int getBallCount(){
        return ballCount;
    }

    /**
     * To check whether the ball is lost or not
     * @return True if ball lost, False if ball does not lose
     */
    public boolean isBallLost(){
        return ballLost;
    }

    /**
     * This method will create and draw the ball
     * @param ballPos The position of the ball
     */
    private void makeBall(Point2D ballPos){
        setBall(new RubberBall(ballPos));
    }

    /**
     * This method will reset the ball position and speed when the ball lost
     */
    public void ballReset(){
        //Move the player and ball to the start point
        getPlayer().moveTo(startPoint);
        getBall().MoveToStartPoint(startPoint);
        //Set the speed of the ball
        int speedX,speedY;
        do{
            speedX = 5;
        }while(speedX == 0);
        do{
            speedY = -5;
        }while(speedY == 0);

        getBall().setSpeed(speedX,speedY);
        ballLost = false;
    }

    /**
     * To determine whether the ballCount/lives is 0
     * @return True if the ballCount/lives is 0, False if the ballCount/lives more than 0
     */
    public boolean ballEnd(){
        return ballCount == 0;
    }

    /**
     * Set the x-axis value for the speed of the ball from Slider
     * @param s The x-axis value for the speed
     */
    public void setBallXSpeed(int s){
        getBall().setXSpeed(s);
    }

    /**
     * Set the y-axis value for the speed of the ball from Slider
     * @param s The y-axis value for the speed
     */
    public void setBallYSpeed(int s){
        getBall().setYSpeed(s);
    }

    /**
     * Reset the ballCount/lives
     */
    public void resetBallCount(){
        ballCount = 3;
    }

    /**
     * Rest the bricks that are break or cracked
     */
    public void wallReset(){
        for(Brick b : getBricks())
            b.repair();
        brickCount = getBricks().length;
        ballCount = 3;
    }

    /**
     * To determine whether all the bricks are broken
     * @return 0 if all the bricks are broken
     */
    public boolean isDone(){
        return brickCount == 0;
    }

    /**
     * To move to the next level
     */
    public void nextLevel(){
        try{
            setBricks(levels[level++]);
            this.brickCount = getBricks().length;
        }
        catch (Exception e){
            System.out.println("This is LAST level!");
        }
    }

    /**
     * To check whether there is level left or not
     * @return True if there is a level next to current level
     */
    public boolean hasLevel(){
        return level < levels.length;
    }

    /**
     * Getter method to get the Brick object
     * @return The object of the Brick class
     */
    public Brick[] getBricks() {
        return bricks;
    }

    /**
     * Setter method to set the Brick object
     * @param bricks The object of the Brick class
     */
    public void setBricks(Brick[] bricks) {
        this.bricks = bricks;
    }

    /**
     * Getter method to get the Ball object
     * @return The object of the ball class
     */
    public Ball getBall() {
        return ball;
    }

    /**
     * Setter method to set the Ball object
     * @param ball The object of the Ball class
     */
    public void setBall(Ball ball) {
        this.ball = ball;
    }

    /**
     * Getter method to get the Player object
     * @return The object of the Player class
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Setter method to set the Player object
     * @param player The object of the Player class
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
}
