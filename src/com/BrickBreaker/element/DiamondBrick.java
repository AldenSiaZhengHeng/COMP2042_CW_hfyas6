package com.BrickBreaker.element;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;

public class DiamondBrick extends Brick {

    private static final String NAME = "Diamond Brick";
    private static final Color DEF_INNER = new Color(105, 255, 233);
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int DIAMOND_STRENGTH = 5;

    private static final int gainScore = 50;
    private Shape brickFace;

    public DiamondBrick(Point point, Dimension size) {
        super(NAME, point, size,DEF_BORDER,DEF_INNER,DIAMOND_STRENGTH);
        brickFace = super.getBrickFace();
    }

    public boolean setImpact(Point2D point , int dir){
        if(super.isBroken()) {
            return false;
        }
        super.impact(gainScore);
        return super.isBroken();
    }

    @Override
    public Shape getBrick() {
        return brickFace;
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos, size);
    }
}
