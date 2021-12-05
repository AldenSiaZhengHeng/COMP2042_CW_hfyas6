package com.BrickBreaker.element;

//Import package
import com.BrickBreaker.score.*;
import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * This is an abstract Brick class to store the variables to generate different types of brick in game
 * @author Alden Sia Zheng Heng
 * @version 1.0
 * @since 3/11/2021
 */
abstract public class Brick  {
    //Variable to store and generate the brick
    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;
    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;

    private String name;
    private Shape brickFace;
    private Color border;
    private Color inner;
    private static Random random;
    private scoreModel ScoreModel;

    private int fullStrength;
    private int strength;
    private boolean broken;
    private int gainscore = 10;

    /**
     * The Constructor of Brick Class
     * Assign the parameter to the variable
     * @param name The name/type of the brick
     * @param pos The location of the brick generated
     * @param size The size of the brick
     * @param border The GameBorder
     * @param inner The color to fill the brick created
     * @param strength The strength for the brick
     */
    public Brick(String name, Point pos,Dimension size,Color border,Color inner,int strength){
        ScoreModel = new scoreModel();
        setRandom(new Random());
        setBrickFace(makeBrickFace(pos,size));
        broken = false;
        this.name = name;
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;

    }

    /**
     * Repair the bricks that broken when game reset
     */
    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    /**
     * To determine whether the brick is broken and pass the score
     * @param ScoreObtain The default score obtained when the brick is broken
     */
    public void impact(int ScoreObtain){
        strength--;
        broken = (strength == 0);
        if(broken){
            ScoreModel.setScore(ScoreModel.getScore()+ScoreObtain);
        }
    }

    /**
     * To check for the impact occur in the game
     * @param point The location of the current brick
     * @param dir The direction of the ball
     * @return False if the brick is broken, True if the brick is not broken
     */
    public boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact(gainscore);
        return broken;
    }

    /**
     * This method will find the impact point where the ball hit the brick
     * @param b The object of the Ball class
     * @return The point to bounce the ball
     */
    public final int findImpact(Ball b){
        if(broken)
            return 0;
        int out  = 0;

        if(getBrickFace().contains(b.getRight()))
            out = LEFT_IMPACT;
        else if(getBrickFace().contains(b.getLeft()))
            out = RIGHT_IMPACT;
        else if(getBrickFace().contains(b.getUp()))
            out = DOWN_IMPACT;
        else if(getBrickFace().contains(b.getDown()))
            out = UP_IMPACT;
        return out;
    }

    /**
     * For creating override method to other sub class
     * @return the shape of the brick
     */
    public abstract Shape getBrick();

    /**
     * For creating override method to other sub class
     * @param pos The location of the current brick
     * @param size The size of the brick
     * @return the shape of the brickFace
     */
    protected abstract Shape makeBrickFace(Point pos,Dimension size);

    /**
     * Getter method to get the color of the brick border
     * @return The color to paint on the brick border
     */
    public Color getBorderColor(){
        return  border;
    }

    /**
     * Getter method to get the color to fill brick
     * @return The color to fill the brick
     */
    public Color getInnerColor(){
        return inner;
    }

    /**
     * This method is to determine whether the brick is broken
     * @return True if the brick is broken
     */
    public final boolean isBroken(){
        return broken;
    }

    /**
     * To create random variable point
     * @param rnd The object of the Random class
     */
    public static void setRandom(Random rnd) {
        Brick.random= rnd;
    }

    /**
     * Getter method to get the random point generated
     * @return The random variable
     */
    public static Random getRandom() {
        return random;
    }

    /**
     * Getter method to get the brickFace object
     * @return The object of the brickFace for creating the brick
     */
    public Shape getBrickFace() {
        return brickFace;
    }

    /**
     * Setter method to set the different type of brickFace
     * @param brickFace The object created for brickFace
     */
    public void setBrickFace(Shape brickFace) {
        this.brickFace = brickFace;
    }
}





