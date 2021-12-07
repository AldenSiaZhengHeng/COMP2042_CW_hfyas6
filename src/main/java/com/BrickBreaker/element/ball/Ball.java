package com.BrickBreaker.element.ball;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * This is the ClayBrick class to store variable to generate Clay brick
 * @author Alden Sia Zheng Heng
 * @version 1.0
 * @since 3/11/2021
 */
abstract public class Ball {
    //Variable to store and generate the ball
    private Shape ballFace;
    private Point2D center;
    private Color border;
    private Color inner;
    private int speedX;
    private int speedY;

    //Create private field for up, down, left, and right
    private Point2D up;
    private Point2D down;
    private Point2D left;
    private Point2D right;

    /**
     * The Constructor of the Ball class
     * Assign the parameter and set to the variable
     * @param center The center of point of the ball
     * @param radiusA The horizontal radius for creating the ball
     * @param radiusB The vertical radius for creating the ball
     * @param inner The inner color for filling the ball
     * @param border The color for painting the border
     */
    public Ball(Point2D center,int radiusA,int radiusB,Color inner,Color border){
        this.center = center;
        this.ballFace = makeBall(center,radiusA,radiusB);
        this.border = border;
        this.inner  = inner;
        this.speedX = 0;
        this.speedY = 0;

        setUp(new Point2D.Double());
        setDown(new Point2D.Double());
        setLeft(new Point2D.Double());
        setRight(new Point2D.Double());
    }

    /**
     * The base constructor to make the ball
     * It gives an outline to creating the ball
     * @param center The center point of the ball
     * @param radiusA The horizontal radius to draw the ball
     * @param radiusB The vertical radius to draw the ball
     * @return The shapes of the ball
     */
    protected abstract Shape makeBall(Point2D center,int radiusA,int radiusB);

    /**
     * Control the movement of the ball to move
     */
    public void move(){
        RectangularShape movement = (RectangularShape) ballFace;
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));
        double width = movement.getWidth();
        double height = movement.getHeight();
        movement.setFrame((center.getX() -(width / 2)),(center.getY() - (height / 2)),width,height);
        setPoints(width,height);
        ballFace = movement;
    }

    /**
     * Move the ball to the start point when the game reset
     * @param p The location of the ball
     */
    public void MoveToStartPoint(Point p){
        center.setLocation(p);
        RectangularShape Original_Location = (RectangularShape) ballFace;
        double width = Original_Location.getWidth();
        double height = Original_Location.getHeight();
        Original_Location.setFrame((center.getX() -(width / 2)),(center.getY() - (height / 2)),width,height);
        ballFace = Original_Location;
    }

    /**
     * Control ball movement when collide with brick
     * @param width the width of the ball
     * @param height the height of the ball
     */
    //Control ball movement when collide with brick
    private void setPoints(double width,double height){
        getUp().setLocation(center.getX(),center.getY()-(height / 2));
        getDown().setLocation(center.getX(),center.getY()+(height / 2));

        getLeft().setLocation(center.getX()-(width / 2),center.getY());
        getRight().setLocation(center.getX()+(width / 2),center.getY());
    }

    /**
     * This method will set the speed of the ball when the game start
     * @param x x-axis of the ball
     * @param y y-axis of the ball
     */
    //Set initial speed of the ball or when reset the speed of the ball
    public void setSpeed(int x,int y){
        speedX = x;
        speedY = y;
    }

    /**
     * Set the x-axis for the speed of the ball
     * @param s Value for the x-axis of the speed
     */
    public void setXSpeed(int s){
        speedX = s;
    }

    /**
     * Set the y-axis for the speed of the ball
     * @param s Value for the y-axis of the speed
     */
    public void setYSpeed(int s){
        speedY = s;
    }

    /**
     * Reverse the x-axis point for ball to bounce back when hit the brick
     */
    public void reverseX(){
        speedX *= -1;
    }

    /**
     * Reverse the y-axis point for ball to bounce back when hit the brick
     */
    public void reverseY(){
        speedY *= -1;
    }

    /**
     * Getter method to get the color for painting the ball border
     * @return The color for the ball border
     */
    public Color getBorderColor(){
        return border;
    }

    /**
     * Getter method to get the color for painting the ball
     * @return The color for the ball
     */
    public Color getInnerColor(){
        return inner;
    }

    /**
     * Getter method to get the center point of the ball
     * @return The center point of the ball
     */
    public Point2D getPosition(){
        return center;
    }

    /**
     * Getter method to get the shape of ballFace
     * @return The shape of the ball
     */
    public Shape getBallFace(){
        return ballFace;
    }

    /**
     * Getter method to get the speed of the ball
     * @return the x-axis of the speed of the ball
     */
    public int getSpeedX(){
        return speedX;
    }

    /**
     * Getter method to get the speed of the ball
     * @return the y-axis of the speed of the ball
     */
    public int getSpeedY(){
        return speedY;
    }

    /**
     * To get the up location when the ball move upward
     * @return The up location of the ball
     */
    public Point2D getUp() {
        return up;
    }

    /**
     * To keep update the up location of the ball when the ball move
     * @param up The up location of the ball
     */
    public void setUp(Point2D up) {
        this.up = up;
    }

    /**
     * To get the down location when the ball move downward
     * @return The down location of the ball
     */
    public Point2D getDown() {
        return down;
    }

    /**
     * Update the down location of the ball when moved
     * @param down The down location of the ball
     */
    public void setDown(Point2D down) {
        this.down = down;
    }

    /**
     * To get the left location of the ball
     * @return left location of the ball
     */
    public Point2D getLeft() {
        return left;
    }

    /**
     * Keep update the left location of the ball when the ball move
     * @param left The left location of the ball
     */
    public void setLeft(Point2D left) {
        this.left = left;
    }

    /**
     * To get the right location of the ball
     * @return The right location of the ball
     */
    public Point2D getRight() {
        return right;
    }

    /**
     * Keep Update the right location of the ball when the ball move
     * @param right The right location of the ball
     */
    public void setRight(Point2D right) {
        this.right = right;
    }
}
