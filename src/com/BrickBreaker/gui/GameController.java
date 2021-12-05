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
package com.BrickBreaker.gui;

//import packages
import com.BrickBreaker.debug.*;
import com.BrickBreaker.element.*;
import com.BrickBreaker.score.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * This is the GameController class
 * @author Alden Sia Zheng Heng
 * @version 1.0
 * @since 3/11/2021
 */
public class GameController extends JComponent implements KeyListener,MouseListener,MouseMotionListener {

    //Variable to set the color for background
    private static final Color BG_COLOR = Color.WHITE;

    //Time system
    private Timer gameTimer;

    //Object for the classes used
    private GameModel gameModel;
    private DebugConsole debugConsole;
    private GameView view;
    private scoreController ScoreController;
    private scoreModel ScoreModel;
    private Brick brick;

    //Getter method to get the GameModel object
    public GameModel getGame(){
        return gameModel;
    }

    //Variable to check the pause action
    private boolean showPauseMenu;

    /**
     * The constructor for GameController class
     * Create object for the class used
     * Create timer system and update the game and message in the game
     * @param owner The object of the GameFrame
     * @param gameModel The object of the GameModel
     * @param gameView The object of the GaemView
     */
    public GameController(JFrame owner, GameModel gameModel, GameView gameView){
        super();
        this.view = gameView;
        this.gameModel = gameModel;
        debugConsole = new DebugConsole(owner, gameModel,this);

        ScoreController = new scoreController();
        ScoreModel = new scoreModel();
        showPauseMenu = false;

        view.initialize(this);
        view.initialize_message();
        view.setStart_message("Press Space to Start..");
        view.updatePause(showPauseMenu);

        //initialize the first level
        gameModel.nextLevel();

        //Timer system
        gameTimer = new Timer(10,e ->{
            view.setHighScore(String.format("HighScore: " + ScoreController.GetHighScore()));
            gameModel.move();
            gameModel.findImpacts();

            //To set the message on the GameBoard
            view.initialize_message();
            view.setBrick_info(String.format("Bricks: %d", gameModel.getBrickCount()));
            if(gameModel.getBallCount() == 3){
                view.setBall_info(String.format("Balls: ⚽ ⚽ ⚽", gameModel.getBallCount()));
            }
            else if(gameModel.getBallCount() == 2){
                view.setBall_info(String.format("Balls: ⚽ ⚽", gameModel.getBallCount()));
            }
            else if(gameModel.getBallCount() == 1){
                view.setBall_info(String.format("Balls: ⚽", gameModel.getBallCount()));
            }
            else{
                view.setBall_info(String.format("Balls: ", gameModel.getBallCount()));
            }
            view.setScore_info(String.format("Score: %d", ScoreModel.getScore()));

            //Check if the ball is lost
            if(gameModel.isBallLost()){
                ScoreController.givePenalty();
                if(gameModel.ballEnd()){
                    gameModel.wallReset();
                    view.initialize_message();
                    view.setMessage("Game over");
                    ScoreController.checkScore(view.getHighScore());
                }
                gameModel.ballReset();
                gameTimer.stop();
            }
            //Check if the level is done
            else if(gameModel.isDone()){
                ScoreController.givebonus(gameModel.getBallCount());
                if(gameModel.hasLevel()){
                    view.setMessage2("Go to Next Level");
                    gameTimer.stop();
                    gameModel.ballReset();
                    gameModel.wallReset();
                    gameModel.nextLevel();
                }
                //Check whether all the levels are completed
                else{
                    view.setMessage4("ALL WALLS DESTROYED");
                    gameTimer.stop();
                }
            }
            repaint();
        });

    }

    /**
     * To draw and paint the GameBoard and call the other draw method
     * @param g The object of graphics context
     */
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        clear(g2d);
        view.draw(g2d);
        Toolkit.getDefaultToolkit().sync();

    }

    /**
     * Initialize the background of the GameBoard
     * @param g2d The object of graphics context in 2D
     */
    private void clear(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(BG_COLOR);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    /**
     * To determine which key is pressed and give different function based on the key pressed
     * @param keyEvent The object of keyEvent
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_A:
                gameModel.getPlayer().moveLeft();
                break;
            case KeyEvent.VK_D:
                gameModel.getPlayer().movRight();
                break;
            case KeyEvent.VK_ESCAPE:
                showPauseMenu = !showPauseMenu;
                view.updatePause(showPauseMenu);

                repaint();
                gameTimer.stop();
                break;
            case KeyEvent.VK_SPACE:
                if(!showPauseMenu)
                    if(gameTimer.isRunning())
                        gameTimer.stop();
                    else
                        gameTimer.start();
                break;
            case KeyEvent.VK_F1:
                if(keyEvent.isAltDown() && keyEvent.isShiftDown())
                    debugConsole.setVisible(true);
            default:
                gameModel.getPlayer().stop();
        }
    }

    /**
     * To stop the player when the key pressed release
     * @param keyEvent The object of the keyEvent
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        gameModel.getPlayer().stop();
    }

    /**
     * This method will check on which button the player clicked on
     * @param mouseEvent The object of the keyEvent
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(!showPauseMenu)
            return;
        //Continue the game
        if(view.getContinueButtonRect().contains(p)){
            view.initialize_message();
            view.setMessage4("Press Space to continue..");
            showPauseMenu = false;
            view.updatePause(showPauseMenu);
            repaint();
        }
        //Restart the game
        else if(view.getRestartButtonRect().contains(p)){
            view.initialize_message();
            view.setMessage3("Restarting Game...");

            gameModel.ballReset();
            gameModel.wallReset();

            ScoreModel.setScore(0);
            showPauseMenu = false;
            view.updatePause(showPauseMenu);
            repaint();
        }
        //Exit the game
        else if(view.getExitButtonRect().contains(p)){
            System.exit(0);
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    /**
     * To detect the mouse cursor is pointing the button
     * @param mouseEvent The object of the mouseEvent
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(view.getExitButtonRect() != null && showPauseMenu) {
            if (view.getExitButtonRect().contains(p) || view.getContinueButtonRect().contains(p) || view.getRestartButtonRect().contains(p))
                this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                this.setCursor(Cursor.getDefaultCursor());
        }
        else{
            this.setCursor(Cursor.getDefaultCursor());
        }
    }

    /**
     * To show the message to inform player if the windoe/frame lost focus
     */
    public void onLostFocus(){
        gameTimer.stop();
        view.setStart_message("Press Space to continue...");
        view.setHighScore("");
        view.setMessage2("");
        view.setMessage3("");
        repaint();
    }

}
