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
package com.BrickBreaker.element.brick;

import com.BrickBreaker.element.brick.Brick;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * This is the SteelBrick class to store variable generate the steel brick
 * @author Alden Sia Zheng Heng
 * @version 1.0
 * @since 3/11/2021
 */
public class SteelBrick extends Brick {

    //Variable to set name and color for steel brick
    private static final String NAME = "Steel Brick";
    private static final Color DEF_INNER = new Color(203, 203, 201);
    private static final Color DEF_BORDER = Color.BLACK;

    //Variable to set the strength and score of the steel brick
    private static final int STEEL_STRENGTH = 1;
    private static final double STEEL_PROBABILITY = 0.4;
    private static final int gainScore = 40;

    //Object for creating random variable and shape
    private Random random;
    private Shape brickFace;

    /**
     * The constructor of steel brick
     * Pass the variable to super method and create the object
     * @param point The position to generate and place the brick
     * @param size The size of the brick
     */
    public SteelBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,STEEL_STRENGTH);
        random = new Random();
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
        impact();
        return super.isBroken();
    }

    /**
     *This method is used to set the probability of the strength of the steel brick
     * It will also pass the score if the brick break
     */
    public void impact(){
        if(random.nextDouble() < STEEL_PROBABILITY){
            super.impact(gainScore);
        }
    }

    /**
     * This is a override method to create the Steel brick face and size
     * @param pos The position to generate and place the steel brick
     * @param size The size of the brick
     * @return The Rectangle object with the position and size of the brick
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     * Getter method to get the interface of the brick
     * @return The interface of the brick
     */
    @Override
    public Shape getBrick() {
        return brickFace;
    }

}
