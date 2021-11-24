package Element;

import Debug.*;
import Main.*;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;

abstract public class Brick  {

    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;
    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;

    private static Random random;
    private int fullStrength;
    private int strength;
    private boolean broken;

    private String name;
    private Shape brickFace;
    private Color border;
    private Color inner;


    public Brick(String name, Point pos,Dimension size,Color border,Color inner,int strength){
        setRandom(new Random());
        setBrickFace(makeBrickFace(pos,size));
        broken = false;
        this.name = name;
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;

    }

    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    public void impact(){
        strength--;
        broken = (strength == 0);
    }

    public  boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact();
        return broken;
    }

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

    public abstract Shape getBrick();

    protected abstract Shape makeBrickFace(Point pos,Dimension size);

    public Color getBorderColor(){
        return  border;
    }

    public Color getInnerColor(){
        return inner;
    }

    public final boolean isBroken(){
        return broken;
    }

    public static void setRandom(Random rnd) {
        Brick.random= rnd;
    }

    public static Random getRandom() {
        return random;
    }

    public Shape getBrickFace() {
        return brickFace;
    }

    public void setBrickFace(Shape brickFace) {
        this.brickFace = brickFace;
    }
}





