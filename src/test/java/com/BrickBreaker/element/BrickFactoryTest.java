package com.BrickBreaker.element;

import com.BrickBreaker.element.brick.*;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class BrickFactoryTest {

    @Test
    public void getBrickType() {
        BrickFactory factory = new BrickFactory();
        Brick brick = factory.getBrickType(1,new Point(),new Dimension(0,0));
        assertTrue(brick.getClass() == ClayBrick.class);

        brick = factory.getBrickType(3,new Point(),new Dimension(0,0));
        assertTrue(brick.getClass() == CementBrick.class);

    }
}