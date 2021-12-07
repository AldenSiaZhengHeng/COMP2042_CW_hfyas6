package com.BrickBreaker.element.brick;

import com.BrickBreaker.element.brick.Brick;

import java.awt.*;
import java.awt.Point;

/**
 * This is the ClayBrick class to store variable to generate Clay brick
 * @author Alden Sia Zheng Heng
 * @version 1.0
 * @since 3/11/2021
 */
public class ClayBrick extends Brick {
    //Variable to store and generate the clay brick
    private static final String NAME = "Clay Brick";
    private static final Color DEF_INNER = new Color(178, 34, 34).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int CLAY_STRENGTH = 1;


    /**
     * The constructor of ClayBrick class
     * Pass variable to super class
     * @param point The location for the brick generated
     * @param size The size of the brick
     */
    public ClayBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
    }

    /**
     * Set the object for the Rectangle to create the interface of the brick
     * @param pos The location of the brick
     * @param size The size of the brick
     * @return The object of the Rectangle brickFace created
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     * Override method for getting the shape of the brickFace
     * @return The object of brickFace from super class
     */
    @Override
    public Shape getBrick() {
        return super.getBrickFace();
    }
}
