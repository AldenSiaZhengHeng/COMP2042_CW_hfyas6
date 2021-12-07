package com.BrickBreaker.element.brick;

import com.BrickBreaker.element.ball.Ball;
import com.BrickBreaker.element.ball.RubberBall;
import com.BrickBreaker.gui.GameModel;
import com.BrickBreaker.element.brick.*;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class BrickTest {

    //To test that whether there is impact occur on the cement brick
    @Test
    public void setImpact() {
        CementBrick cement = new CementBrick(new Point(),new Dimension(0,0));
        cement.impact(30);
        assertFalse(cement.isBroken());
    }

    //This test case is used to find whether there is any impact on the broken brick
    //If not then it will return 0
    @Test
    public void findImpact() {
        boolean broken = true;
        int out = 0;
        int UP_IMPACT = 100;
        int DOWN_IMPACT = 200;
        int LEFT_IMPACT = 300;
        int RIGHT_IMPACT = 400;
        if(broken)
            out = 0;
        CementBrick cement = new CementBrick(new Point(),new Dimension(0,0));
        Ball b = new RubberBall(new Point(300,430));
        if(cement.getBrickFace().contains(b.getRight()))
            out = LEFT_IMPACT;
        else if(cement.getBrickFace().contains(b.getLeft()))
            out = RIGHT_IMPACT;
        else if(cement.getBrickFace().contains(b.getUp()))
            out = DOWN_IMPACT;
        else if(cement.getBrickFace().contains(b.getDown()))
            out = UP_IMPACT;
        
        assertEquals(out,0);
    }
}