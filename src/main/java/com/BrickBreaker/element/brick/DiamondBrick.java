package com.BrickBreaker.element.brick;

import com.BrickBreaker.element.brick.Brick;
import com.BrickBreaker.element.brick.Crack;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

/**
 * This is the DiamondBrick Class which will store the variable to create the diamond brick
 * @author Alden Sia Zheng Heng
 * @version 1.0
 * @since 3/11/2021
 */
public class DiamondBrick extends Brick {
    //Variable to store the details to create the diamond brick
    private static final String NAME = "Diamond Brick";
    private static final Color DEF_INNER = new Color(105, 255, 233);
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int DIAMOND_STRENGTH = 5;
    private static final int gainScore = 50;
    private Shape brickFace;
    private Crack crack;
    /**
     * The constructor of DiamondBrick class
     * Pass variable to super class
     * Create object for brick interface
     * @param point The position for draw the brick
     * @param size The size to draw the brick
     */
    public DiamondBrick(Point point, Dimension size) {
        super(NAME, point, size,DEF_BORDER,DEF_INNER,DIAMOND_STRENGTH);
        brickFace = super.getBrickFace();
        crack = new Crack(DEF_CRACK_DEPTH,DEF_STEPS,super.getBrickFace());
    }


    /**
     * Update the brick face if the ball hit the brick
     */
    private void updateBrick(){
        if(!super.isBroken()){
            GeneralPath gp = crack.draw();
            gp.append(super.getBrickFace(),false);
            brickFace = gp;
        }
    }

    /**
     * Determine the impact between the ball and brick
     * Pass the score when the brick is broken
     * @param point The location of the current brick
     * @param dir   The direction of the ball
     * @return
     */
    public boolean setImpact(Point2D point , int dir){
        if(super.isBroken()) {
            return false;
        }
        super.impact(gainScore);
        if(!super.isBroken()){
            crack.makeCrack(point,dir);
            updateBrick();
            return false;
        }
        return true;
    }

    /**
     * Repair the crack on the interface of the brick when the game reset
     */
    public void repair(){
        super.repair();
        crack.reset();
        brickFace = super.getBrickFace();
    }
    /**
     * Getter method to get the interface of the brick
     * @return The interface of the brick
     */
    @Override
    public Shape getBrick() {
        return brickFace;
    }

    /**
     * This is a override method to create the Diamond brick face and size
     * @param pos The position to generate and place the diamond brick
     * @param size The size of the brick
     * @return The Rectangle object with the position and size of the brick
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos, size);
    }
}
