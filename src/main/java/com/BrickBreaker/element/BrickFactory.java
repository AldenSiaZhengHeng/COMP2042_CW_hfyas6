package com.BrickBreaker.element;

import com.BrickBreaker.element.brick.*;

import java.awt.*;

/**
 * This is BrickFactory class.
 * It is used for implement factory design pattern on brick abstract class and to provide a best way to create other bricks classes object
 */
public class BrickFactory {
    /**
     * This method will return the object of the brick class depend on the brick type passes.
     * This is used to easily create the object for the brick class without exposing the creation logic
     * @param BrickType Type of brick selected to generated in level
     * @param p The location to generate the brick
     * @param bricksize The size of the brick
     * @return The object of the brick class based on the bricktype selected
     */
    public Brick getBrickType(int BrickType, Point p, Dimension bricksize){
        if(BrickType == 0){
            return null;
        }
        if(BrickType == 1){
            return new ClayBrick(p,bricksize);
        }
        else if(BrickType == 2){
            return new SteelBrick(p,bricksize);
        }
        else if(BrickType == 3){
            return new CementBrick(p,bricksize);
        }
        else if(BrickType == 4){
            return new DiamondBrick(p,bricksize);
        }
        return null;
    }
}
