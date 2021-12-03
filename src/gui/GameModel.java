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
package gui;

import element.*;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class GameModel {

    private Random random;
    private Rectangle area;

    private Brick[] bricks;
    private Ball ball;
    private Player player;


    private Brick[][] levels;
    private int level;

    //private Point startPoint;
    private int brickCount;
    private int ballCount;
    private boolean ballLost;

    //Addtional variable
    private static final Point startPoint = new Point(300,430);
    private static double brickDimensionRatio = 3;
    private static int lineCount = 3;
    private Rectangle drawContainerArea;

    //Additional
    private Levels create_levels;
    Crack crack;
    //public Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos)

    public GameModel(Rectangle drawArea){
    //wall = new Wall(new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT),30,3,6/2,new Point(300,430));

        //this.startPoint = new Point(ballPos);

        //Addtional variable
        create_levels  = new Levels();
        this.drawContainerArea = drawArea;
        brickCount = 30;
        levels = create_levels.makeLevels(drawContainerArea,brickCount,lineCount,brickDimensionRatio);

        //levels = makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
        level = 0;

        ballCount = 3;
        ballLost = false;

        random = new Random();

        //makeBall(ballPos);
        makeBall((Point)startPoint.clone());


        int speedX,speedY;
        do{
            //speedX = random.nextInt(5) - 2;
            speedX = 5;
        }while(speedX == 0);
        do{
            //speedY = -random.nextInt(3);
            speedY = -5;
        }while(speedY == 0);

        getBall().setSpeed(speedX,speedY);

        //player = new Player((Point) ballPos.clone(),150,10, drawArea);
        setPlayer(new Player((Point) startPoint.clone(),150,10, drawArea));

        area = drawArea;


        //Additional

    }

    //Method to move (track) the movement of ball and player (rectangle board)
    public void move(){
        getPlayer().move();
        getBall().move();
    }

    //To find is the ball contact with any bricks
    public void findImpacts(){
        if(getPlayer().impact(getBall())){
            getBall().reverseY();
        }
        else if(impactWall()){
            /*for efficiency reverse is done into method impactWall
            * because for every brick program checks for horizontal and vertical impacts
            */
            brickCount--;
        }
        else if(impactBorder()) {
            getBall().reverseX();
        }
        else if(getBall().getPosition().getY() < area.getY()){
            getBall().reverseY();
        }
        else if(getBall().getPosition().getY() > area.getY() + area.getHeight()){
            ballCount--;
            ballLost = true;
        }
    }

    private boolean impactWall(){
        for(Brick b : getBricks()){
            switch(b.findImpact(getBall())) {
                //Vertical Impact
                case Brick.UP_IMPACT:
                    getBall().reverseY();
                    return b.setImpact(getBall().getDown(), crack.UP);
                case Brick.DOWN_IMPACT:
                    getBall().reverseY();
                    return b.setImpact(getBall().getUp(),crack.DOWN);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    getBall().reverseX();
                    return b.setImpact(getBall().getRight(),crack.RIGHT);
                case Brick.RIGHT_IMPACT:
                    getBall().reverseX();
                    return b.setImpact(getBall().getLeft(),crack.LEFT);
            }
        }

        return false;
    }

    private boolean impactBorder(){
        Point2D p = getBall().getPosition();
        return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
    }

    public int getBrickCount(){
        return brickCount;
    }

//////////////////////////////////////////////////////////////////////////
    //Ball status - shouldn't include in wall class
    public int getBallCount(){
        return ballCount;
    }

    public boolean isBallLost(){
        return ballLost;
    }

    private void makeBall(Point2D ballPos){
        setBall(new RubberBall(ballPos));
    }

    public void ballReset(){
        getPlayer().moveTo(startPoint);
        getBall().MoveToStartPoint(startPoint);
        int speedX,speedY;
        do{
            //speedX = random.nextInt(5) - 2;
            speedX = 5;
        }while(speedX == 0);
        do{
            //speedY = -random.nextInt(3);
            speedY = -5;
        }while(speedY == 0);

        getBall().setSpeed(speedX,speedY);
        ballLost = false;
    }

    public boolean ballEnd(){
        return ballCount == 0;
    }

    public void setBallXSpeed(int s){
        getBall().setXSpeed(s);
    }

    public void setBallYSpeed(int s){
        getBall().setYSpeed(s);
    }

    public void resetBallCount(){
        ballCount = 3;
    }
////////////////////////////////////////////////////////////////////////////

    public void wallReset(){
        for(Brick b : getBricks())
            b.repair();
        brickCount = getBricks().length;
        ballCount = 3;
    }

    public boolean isDone(){
        return brickCount == 0;
    }

    public void nextLevel(){
        try{
            setBricks(levels[level++]);
            this.brickCount = getBricks().length;
        }
        catch (Exception e){

        }
    }

    public boolean hasLevel(){
        return level < levels.length;
    }


    public Brick[] getBricks() {
        return bricks;
    }

    public void setBricks(Brick[] bricks) {
        this.bricks = bricks;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
