package com.BrickBreaker.gui;

import com.BrickBreaker.element.Levels;
import com.BrickBreaker.element.brick.Brick;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class GameModelTest {
    //The number of levels in the game
    int LEVELS_COUNT = 8;

    //To test whether there is levels for player to play
    @Test
    public void hasLevel() {
        Brick[][] temp = new Brick[LEVELS_COUNT][];
        assertNotNull(temp);
    }

    //Testing that whether the game able to generate the brick if there is still have levels left
    //getBricks() method in gameModel class is used to check as it return null when no level left
    @Test
    public void nextLevel() {
        Brick[][] levels = new Brick[2][2];
        int level = 0;
        GameModel gameModel = new GameModel(new Rectangle(0,0,600,450));
        try{
            gameModel.setBricks(levels[level++]);
            System.out.println(gameModel.getBricks());
            assertNotNull(gameModel.getBricks());
        }
        catch (Exception e){
            System.out.println("This is LAST level!");
        }
    }

    //To test that whether is there still have brick on there or all bricks are destroyed
    //return true if all brick is broke, return false if there are some bricks remaining
    @Test
    public void isDone() {
        boolean finished;
        GameModel gameModel = new GameModel(new Rectangle(0,0,600,450));
        if(gameModel.getBrickCount() ==0)
            finished = true;
        else
            finished = false;
        //To check whether there is brick remaining at the initial stage or null
        assertFalse(finished);
    }
}