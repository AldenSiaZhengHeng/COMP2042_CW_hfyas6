package com.BrickBreaker.element.brick;

import com.BrickBreaker.element.brick.Brick;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;


/**
 * This is the Crack class to store the variable to make the crack effect on the brick.
 * The Crack class is separated from Brick class so that any other brick can use it without extend Brick class.
 * @author Alden Sia Zheng Heng
 * @version 1.0
 * @since 3/11/2021
 */
public class Crack{

    private static final int CRACK_SECTIONS = 3;
    private static final double JUMP_PROBABILITY = 0.7;
    public static final int LEFT = 10;
    public static final int RIGHT = 20;
    public static final int UP = 30;
    public static final int DOWN = 40;
    public static final int VERTICAL = 100;
    public static final int HORIZONTAL = 200;

    private int crackDepth;
    private int steps;

    private Random random;
    private Shape brickFace;
    private GeneralPath crack;
    private Brick brick;

    /**
     * The constructor of crack class
     * @param crackDepth The depth to draw the crack on the brick
     * @param steps The value to set the crack on brick
     * @param BrickFace The interface of the brick
     */
    public Crack(int crackDepth, int steps, Shape BrickFace){

        crack = new GeneralPath();
        this.crackDepth = crackDepth;
        this.steps = steps;
        this.brickFace = BrickFace;
        random = brick.getRandom();
    }


    /**
     * Draw the crack effect on the brick
     * @return The object of GeneralPath
     */
    public GeneralPath draw(){
        return crack;
    }

    /**
     * Reset the crack effect on the brick
     */
    public void reset(){
        crack.reset();
    }

    /**
     * Randomly generate path from the impact point in a direction
     * @param point The position of the brick
     * @param direction The direction of the ball to bounce
     */
    protected void makeCrack(Point2D point, int direction){
        Rectangle bounds = brickFace.getBounds();

        Point impact = new Point((int)point.getX(),(int)point.getY());
        Point start = new Point();
        Point end = new Point();

        switch(direction){
            case LEFT:
                start.setLocation(bounds.x + bounds.width, bounds.y);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                Point tmp = makeRandomPoint(start,end,VERTICAL);
                makeCrack(impact,tmp);
                break;

            case RIGHT:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x, bounds.y + bounds.height);
                tmp = makeRandomPoint(start,end,VERTICAL);
                makeCrack(impact,tmp);
                break;

            case UP:
                start.setLocation(bounds.x, bounds.y + bounds.height);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                tmp = makeRandomPoint(start,end,HORIZONTAL);
                makeCrack(impact,tmp);
                break;

            case DOWN:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x + bounds.width, bounds.y);
                tmp = makeRandomPoint(start,end,HORIZONTAL);
                makeCrack(impact,tmp);
                break;
        }
    }

    /**
     * Overloading method for making crack on the brick.
     * Randomly generate path from start point to end point
     * @param start The impact point on the brick
     * @param end Random point
     */
    protected void makeCrack(Point start, Point end){
        GeneralPath path = new GeneralPath();
        path.moveTo(start.x,start.y);

        double w = (end.x - start.x) / (double)steps;
        double h = (end.y - start.y) / (double)steps;

        int bound = crackDepth;
        int jump  = bound * 5;

        double x,y;

        for(int i = 1; i < steps;i++){

            x = (i * w) + start.x;
            y = (i * h) + start.y + randomInBounds(bound);

            if(inMiddle(i,CRACK_SECTIONS,steps))
                y += jumps(jump,JUMP_PROBABILITY);

            path.lineTo(x,y);

        }
        path.lineTo(end.x,end.y);
        crack.append(path,true);
    }

    /**
     * Gives a random between bound up and bound down
     * @param bound  The crackDepth value
     * @return Random direction when the ball hit the brick
     */
    private int randomInBounds(int bound){
        int n = (bound * 2) + 1;
        return random.nextInt(n) - bound;
    }

    /**
     *  To determine whether the ball is still in the middle of air
     * @param i The value in a for loop
     * @param steps The crack sessions for brick
     * @param divisions The value to set the crack on brick
     * @return True if the ball still in the middle of the air, False when hit the brick
     */
    private boolean inMiddle(int i,int steps,int divisions){
        int low = (steps / divisions);
        int up = low * (divisions - 1);
        return  (i > low) && (i < up);
    }

    /**
     * Randomly generate the point for the ball to bounce
     * @param bound Current bounce point
     * @param probability Probability to generate the bounce point
     * @return The random generated bounce point
     */
    private int jumps(int bound,double probability){
        if(random.nextDouble() > probability)
            return randomInBounds(bound);
        return  0;

    }

    /**
     * Generate random point for the ball to bounce
     * @param from Start point
     * @param to End Point
     * @param direction Direction of the ball bounces
     * @return The random point generate
     */
    private Point makeRandomPoint(Point from,Point to, int direction){
        Point out = new Point();
        int pos;

        switch(direction){
            case HORIZONTAL:
                pos = random.nextInt(to.x - from.x) + from.x;
                out.setLocation(pos,to.y);
                break;

            case VERTICAL:
                pos = random.nextInt(to.y - from.y) + from.y;
                out.setLocation(to.x,pos);
                break;
        }
        return out;
    }

}
