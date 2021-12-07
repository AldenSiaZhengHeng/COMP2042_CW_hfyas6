package com.BrickBreaker.element;

import java.awt.*;

public class brickFactory {
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
