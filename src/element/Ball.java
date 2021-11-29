package element;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;


abstract public class Ball {

    private Shape ballFace;
    private Point2D center;
    private Color border;
    private Color inner;
    private int speedX;
    private int speedY;

    //Additional
    //Create private field for up, down, left, and right
    private Point2D up;
    private Point2D down;
    private Point2D left;
    private Point2D right;

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

    protected abstract Shape makeBall(Point2D center,int radiusA,int radiusB);

    //Control the movement of the ball to move
    //Change tmp name to movement
    public void move(){
        RectangularShape movement = (RectangularShape) ballFace;
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));
        double width = movement.getWidth();
        double height = movement.getHeight();
        movement.setFrame((center.getX() -(width / 2)),(center.getY() - (height / 2)),width,height);
        setPoints(width,height);
        ballFace = movement;
    }

    //Move the ball to the start point when reset
    public void MoveToStartPoint(Point p){
        center.setLocation(p);
        RectangularShape Original_Location = (RectangularShape) ballFace;
        double width = Original_Location.getWidth();
        double height = Original_Location.getHeight();
        Original_Location.setFrame((center.getX() -(width / 2)),(center.getY() - (height / 2)),width,height);
        ballFace = Original_Location;
    }

    //Control ball movement when collide with brick
    private void setPoints(double width,double height){
        getUp().setLocation(center.getX(),center.getY()-(height / 2));
        getDown().setLocation(center.getX(),center.getY()+(height / 2));

        getLeft().setLocation(center.getX()-(width / 2),center.getY());
        getRight().setLocation(center.getX()+(width / 2),center.getY());
    }

    //Set initial speed of the ball or when reset the speed of the ball
    public void setSpeed(int x,int y){
        speedX = x;
        speedY = y;
    }

    public void setXSpeed(int s){
        speedX = s;
    }

    public void setYSpeed(int s){
        speedY = s;
    }

    public void reverseX(){
        speedX *= -1;
    }

    public void reverseY(){
        speedY *= -1;
    }

    public Color getBorderColor(){
        return border;
    }

    public Color getInnerColor(){
        return inner;
    }

    public Point2D getPosition(){
        return center;
    }

    public Shape getBallFace(){
        return ballFace;
    }

    public int getSpeedX(){
        return speedX;
    }

    public int getSpeedY(){
        return speedY;
    }

    public Point2D getUp() {
        return up;
    }

    public void setUp(Point2D up) {
        this.up = up;
    }

    public Point2D getDown() {
        return down;
    }

    public void setDown(Point2D down) {
        this.down = down;
    }

    public Point2D getLeft() {
        return left;
    }

    public void setLeft(Point2D left) {
        this.left = left;
    }

    public Point2D getRight() {
        return right;
    }

    public void setRight(Point2D right) {
        this.right = right;
    }
}
