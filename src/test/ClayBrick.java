package test;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;


public class ClayBrick extends Brick {

    private static final String NAME = "Clay Brick";
    private static final Color DEF_INNER = new Color(178, 34, 34).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int CLAY_STRENGTH = 1;


    public ClayBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    @Override
    public Shape getBrick() {
        //return super.brickFace;
        return super.getBrickFace();
    }


}
