package com.BrickBreaker.element;

import java.awt.*;
import java.awt.Point;
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
    }

    /**
     * This method is used to detect the impact between the brick and ball
     * @param point The position of current ball
     * @param dir The direction for the ball to bounce
     * @return False if the brick is broken, True if there is a impact between ball and brick
     */
    public boolean setImpact(Point2D point , int dir){
        if(super.isBroken()) {
            return false;
        }
        super.impact(gainScore);
        return super.isBroken();
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
