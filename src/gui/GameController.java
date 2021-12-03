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

//import packages
import debug.*;
import element.*;
import score.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class GameController extends JComponent implements KeyListener,MouseListener,MouseMotionListener {

    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private static final Color BG_COLOR = Color.WHITE;

    private Timer gameTimer;

    private GameModel gameModel;
    public GameModel getGame(){
        return gameModel;
    }

    private boolean showPauseMenu;

    private DebugConsole debugConsole;
    private GameView view;

    private Brick brick;

    //Score
    private String highScore = "";
    private scoreController ScoreController;
    private scoreModel ScoreModel;

    public GameController(JFrame owner){
        super();
        //this.initialize();
        showPauseMenu = false;
        gameModel = new GameModel(new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT));
        debugConsole = new DebugConsole(owner, gameModel,this);
        view = new GameView(this);
        ScoreController = new scoreController();
        ScoreModel = new scoreModel();
        view.initialize();
        view.setMessage("");
        view.setMessage2("");
        view.setMessage3("");
        view.setStart_message("Press Space to Start");
        view.setBrick_info("");
        view.setBall_info("");
        view.setScore_info("");

        view.updatePause(showPauseMenu);
        //initialize the first level
        gameModel.nextLevel();

       // checkScore();
        gameTimer = new Timer(10,e ->{

            view.setHighScore(String.format("HighScore: " + ScoreController.GetHighScore()));
            gameModel.move();
            gameModel.findImpacts();

            view.setStart_message("");
            view.setMessage("");
            view.setMessage2("");
            view.setMessage3("");
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
            //view.setScore_info(String.format("Score: %d", brick.getScore()));
            view.setScore_info(String.format("Score: %d", ScoreModel.getScore()));

            if(gameModel.isBallLost()){
                ScoreController.givePenalty();
                if(gameModel.ballEnd()){
                    gameModel.wallReset();
                    view.setMessage("Game over");
                    view.setMessage2("");
                    view.setMessage3("");
                    view.setBrick_info("");
                    view.setScore_info("");
                    view.setBall_info("");
                    ScoreController.checkScore(view.getHighScore());
                }
                gameModel.ballReset();
                gameTimer.stop();
            }
            else if(gameModel.isDone()){
                ScoreController.givebonus(gameModel.getBallCount());
                if(gameModel.hasLevel()){
                    view.setMessage2("Go to Next Level");
                    gameTimer.stop();
                    gameModel.ballReset();
                    gameModel.wallReset();
                    gameModel.nextLevel();
                }
                else{
                    view.setMessage4("ALL WALLS DESTROYED");
                    gameTimer.stop();
                }
            }

            repaint();
        });

    }

    private void initialize(){
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }


    public void paint(Graphics g){

        Graphics2D g2d = (Graphics2D) g;
        clear(g2d);
        view.draw(g2d);
        Toolkit.getDefaultToolkit().sync();

    }

    private void clear(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(BG_COLOR);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

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
                //if(keyEvent.isAltDown() && keyEvent.isShiftDown())
                if(keyEvent.isShiftDown())
                    debugConsole.setVisible(true);
            default:
                gameModel.getPlayer().stop();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        gameModel.getPlayer().stop();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(!showPauseMenu)
            return;

        if(view.getContinueButtonRect().contains(p)){
            view.setMessage2("Press Space to continue..");
            view.setHighScore("");
            view.setStart_message("");
            view.setMessage("");
            view.setMessage3("");
            view.setMessage4("");
            showPauseMenu = false;
            view.updatePause(showPauseMenu);
            repaint();
        }
        else if(view.getRestartButtonRect().contains(p)){

            view.setMessage3("Restarting Game...");
            view.setHighScore("");
            view.setStart_message("");
            view.setMessage("");
            view.setMessage2("");
            view.setMessage4("");
            view.setScore_info("");
            view.setBrick_info("");
            view.setBall_info("");

            gameModel.ballReset();
            gameModel.wallReset();

            //Add
            //brick.setScore(0);
            ScoreModel.setScore(0);
            showPauseMenu = false;
            view.updatePause(showPauseMenu);
            repaint();
        }
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

    public void onLostFocus(){
        gameTimer.stop();
        view.setStart_message("Press Space to continue...");
        view.setHighScore("");
        view.setMessage2("");
        view.setMessage3("");
        repaint();
    }

}
