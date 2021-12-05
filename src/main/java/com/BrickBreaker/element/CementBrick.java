package com.BrickBreaker.element;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

/**
 * This is the CementBrick class to store variable to generate the cement brick
 * @author Alden Sia Zheng Heng
 * @version 1.0
 * @since 3/11/2021
 */
public class CementBrick extends Brick {
    //Variable to store and generate the clay brick
    private static final String NAME = "Cement Brick";
    private static final Color DEF_INNER = new Color(147, 147, 147);
    private static final Color DEF_BORDER = new Color(217, 199, 175);
    private static final int CEMENT_STRENGTH = 2;
    private static final int gainScore = 30;

    //Object created
    private Crack crack;
    private Shape brickFace;


    /**
     * The constructor of the CementBrick class
     * Pass the variable to the superclass
     * Create object for crack
     * @param point The location to draw the brick
     * @param size The size of the brick to draw
     */
    public CementBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CEMENT_STRENGTH);
        crack = new Crack(DEF_CRACK_DEPTH,DEF_STEPS,super.getBrickFace());
        brickFace = super.getBrickFace();
    }

    /**
     * Update the brick face if the ball hit the brick
     */
    //Update the brick face
    private void updateBrick(){
        if(!super.isBroken()){
            GeneralPath gp = crack.draw();
            gp.append(super.getBrickFace(),false);
            brickFace = gp;
        }
    }

    /**
     * Repair the crack on the interface of the brick when the game reset
     */
    //Repair the cementBrick that is break
    public void repair(){
        super.repair();
        crack.reset();
        brickFace = super.getBrickFace();
    }

    /**
     * Create the object of the brickFace
     * @param pos The location of creating the brick
     * @param size The size to create the brick
     * @return The object created for the Rectangle object
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     * Override method from super class
     * Determine the impact between the ball and brick
     * Pass the score when the brick is broken
     * @param point The location of the brick
     * @param dir The direction where the ball hit on the brick
     * @return True when brick is not broken, False when the brick is broken
     */
    @Override
    public boolean setImpact(Point2D point, int dir) {
        if(super.isBroken())
            return false;
        super.impact(gainScore);
        if(!super.isBroken()){
            crack.makeCrack(point,dir);
            updateBrick();
            return false;
        }
        return true;
    }

    /**
     * Override method from super class
     * To get the brickFace to draw the brick
     * @return The brickFace object
     */
    @Override
    public Shape getBrick() {
        return brickFace;
    }


}
