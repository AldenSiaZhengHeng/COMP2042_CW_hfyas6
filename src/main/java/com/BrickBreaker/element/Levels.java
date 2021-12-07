package com.BrickBreaker.element;

import com.BrickBreaker.element.brick.Brick;

import java.awt.*;

/**
 * This is the Levels class to generate the levels
 * @author Alden Sia Zheng Heng
 * @version 1.0
 * @since 3/11/2021
 */
public class Levels {
    //Variable to create the levels
    private static final int LEVELS_COUNT = 8;
    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;
    private static final int DIAMOND = 4;

    private BrickFactory factory;
    public Levels(){
        factory = new BrickFactory();
    }

    /**
     * This method will store and pass the levels created
     * @param drawArea The area for the GameBoard
     * @param brickCount The amount of brick to draw
     * @param lineCount The amount of line to draw the brick
     * @param brickDimensionRatio The dimension of the brick ratio
     * @return The array that store the levels created
     */
    public Brick[][] makeLevels(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio){
        Brick[][] tmp = new Brick[LEVELS_COUNT][];
        tmp[0] = makeSingleTypeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY);
        tmp[1] = makeSingleTypeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,STEEL);
        tmp[2] = makeSingleTypeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CEMENT);
        tmp[3] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CEMENT,DIAMOND);
        tmp[4] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,CEMENT);
        tmp[5] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,STEEL,CEMENT);
        tmp[6] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,STEEL);
        tmp[7] = makeSingleTypeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,DIAMOND);
        return tmp;
    }


    /**
     * This method will generate a levels that only consists one type of brick.
     * The type of brick to build the wall is depended on the type passed
     * @param drawArea The area for the GameBoard
     * @param brickCnt The amount of brick to draw
     * @param lineCnt The amount of line to draw the brick
     * @param brickSizeRatio The size ratio for drawing the brick
     * @param type Type of brick to generate
     * @return The levels created with the brick passed
     */
    private Brick[] makeSingleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int type){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            double x = (i % brickOnLine) * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);
            tmp[i] = factory.getBrickType(type,p,brickSize);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = factory.getBrickType(type,p,brickSize);
        }
        return tmp;
    }

    /**
     * This method will created two type of brick in a levels
     * @param drawArea The area for the GameBoard
     * @param brickCnt The amount of brick to draw
     * @param lineCnt The amount of line to draw the brick
     * @param brickSizeRatio The size ratio for drawing the brick
     * @param typeA The type of brickA to draw
     * @param typeB The type of brickB to draw
     * @return The levels created with two type of bricks
     */
    private Brick[] makeChessboardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            tmp[i] = b ? factory.getBrickType(typeA,p,brickSize) : factory.getBrickType(typeB,p,brickSize);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = factory.getBrickType(typeA,p,brickSize);
        }
        return tmp;
    }
}
