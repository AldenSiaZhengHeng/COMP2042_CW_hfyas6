package com.BrickBreaker.gui;

import com.BrickBreaker.element.ball.Ball;
import com.BrickBreaker.element.brick.Brick;
import com.BrickBreaker.element.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;

/**
 * This is the GameView class
 * @author Alden Sia Zheng Heng
 * @version 1.0
 * @since 3/11/2021
 */
public class GameView extends  JComponent{
    //variable for text and color
    private static final String CONTINUE = "Continue";
    private static final String RESTART = "Restart";
    private static final String EXIT = "Exit";
    private static final String PAUSE = "Pause Menu";
    private static final Color MENU_COLOR = new Color(0,255,0);
    private static final Color FONT_COLOR = new Color(7, 7, 7);

    //variable to set the width and height of the gameboard
    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    //String variable to store the messages
    private String message;
    private String message2;
    private String message3;
    private String message4;
    private String start_message;
    private String brick_info;
    private String ball_info;
    private String score_info;
    private String highScore="";

    //variable to determine the pause action
    private boolean showPauseMenu;

    //variable to store the shape of the button
    private Rectangle continueButtonRect;
    private Rectangle exitButtonRect;
    private Rectangle restartButtonRect;

    //variable to store the string length
    private int strLen;

    //The object of the classes
    private WordFontStyle font;
    private GameController owner;


    /**
     * The constructor of the GameView class
     */
    public GameView(){
        font = new WordFontStyle();
        strLen = 0;
        showPauseMenu = false;
        initialize_message();
    }

    /**
     * To initialize the message to print on the gameboard
     */
    public void initialize_message(){
        setMessage("");
        setMessage2("");
        setMessage3("");
        setMessage4("");
        setStart_message("");
        setBrick_info("");
        setBall_info("");
        setScore_info("");
    }

    /**
     * Add the key and mouse listener to the Gamebord and set the preferred size of the GameBoard
     * @param Owner The object of the GameController class
     */
    public void initialize(GameController Owner){
        this.owner = Owner;
        owner.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        owner.setFocusable(true);
        owner.requestFocusInWindow();
        owner.addKeyListener(owner);
        owner.addMouseListener(owner);
        owner.addMouseMotionListener(owner);
    }

    /**
     * To update the variable for the pause action
     * @param pause The variable to determine the whether there is pause action
     */
    public void updatePause(boolean pause){
        this.showPauseMenu = pause;
    }


    /**
     * To draw the brick, player, balls and others messages on the GameBoard
     * @param g2d The object of the graphics context in 2D
     */
    public void draw(Graphics2D g2d){
        drawinsturciton(g2d);

        drawBall(owner.getGame().getBall(),g2d);

        for(Brick b : owner.getGame().getBricks())
            if(!b.isBroken())
                drawBrick(b,g2d);

        drawPlayer(owner.getGame().getPlayer(),g2d);

        if(showPauseMenu)
            drawMenu(g2d);
    }

    /**
     * The method to set the font and color and draw the instruction list
     * @param g2d The object of graphics context in 2D
     */
    public void drawinsturciton(Graphics2D g2d){
        g2d.setColor(FONT_COLOR);
        g2d.setFont(new Font("Stencil",Font.BOLD,30));
        g2d.drawString(getHighScore(), 160,120);
        g2d.drawString(getMessage(),220,150);
        g2d.drawString(getMessage2(),180,150);
        g2d.drawString(getMessage3(),160,150);
        g2d.drawString(getMessage4(),120,150);
        g2d.drawString(getStart_message(),120,180);
        g2d.setFont(new Font("Aharoni",Font.BOLD,20));
        g2d.drawString(getBrick_info(),250,210);
        g2d.drawString(getBall_info(),250,240);
        g2d.drawString(getScore_info(),250,270);
    }

    /**
     * To draw the brick on the GameBoard
     * @param brick The object of the Brick class
     * @param g2d The object of the graphic context in 2D
     */
    private void drawBrick(Brick brick,Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(brick.getInnerColor());
        g2d.fill(brick.getBrick());
        g2d.setColor(brick.getBorderColor());
        g2d.draw(brick.getBrick());
        g2d.setColor(tmp);
    }

    /**
     * This method will draw the ball on the GameBoard
     * @param ball The object of the Ball class
     * @param g2d The object of the graphic context in 2D
     */
    private void drawBall(Ball ball, Graphics2D g2d){
        Color tmp = g2d.getColor();
        Shape s = ball.getBallFace();
        g2d.setColor(ball.getInnerColor());
        g2d.fill(s);
        g2d.setColor(ball.getBorderColor());
        g2d.draw(s);
        g2d.setColor(tmp);
    }

    /**
     * To draw the player container on the GameBoard
     * @param p The object of the Player class
     * @param g2d The object of the graphics context in 2D
     */
    private void drawPlayer(Player p, Graphics2D g2d){
        Color tmp = g2d.getColor();
        Shape s = p.getPlayerFace();
        g2d.setColor(Player.INNER_COLOR);
        g2d.fill(s);
        g2d.setColor(Player.BORDER_COLOR);
        g2d.draw(s);
        g2d.setColor(tmp);
    }

    /**
     * To call method to draw the pause menu
     * @param g2d The object of the graphics context in 2D
     */
    private void drawMenu(Graphics2D g2d){
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

    /**
     * Showing special effect when the game is paused
     * @param g2d The object of the graphics context in 2D
     */
    private void obscureGameBoard(Graphics2D g2d){
        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.55f);
        g2d.setComposite(ac);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,DEF_WIDTH,DEF_HEIGHT);
        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

    /**
     * The method to draw the pause menu and the button on it
     * @param g2d The object of the graphics context in 2D
     */
    private void drawPauseMenu(Graphics2D g2d){
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();

        //Draw the PauseMenu Title
        g2d.setFont(font.getPauseMenuFont());
        g2d.setColor(MENU_COLOR);

        if(strLen == 0){
            FontRenderContext frc = g2d.getFontRenderContext();
            strLen = font.getPauseMenuFont().getStringBounds(PAUSE,frc).getBounds().width;
        }

        int x = (owner.getWidth() - strLen) / 2;
        int y = owner.getHeight() / 10;

        g2d.drawString(PAUSE,x,y);

        //Draw the Continue Button
        x = owner.getWidth() / 8;
        y = owner.getHeight() / 4;

        if(continueButtonRect == null){
            FontRenderContext frc = g2d.getFontRenderContext();
            continueButtonRect = font.getPauseMenuFont().getStringBounds(CONTINUE,frc).getBounds();
            continueButtonRect.setLocation(x,y-continueButtonRect.height);
        }

        g2d.drawString(CONTINUE,x,y);

        //Draw the Restart button
        y *= 2;
        if(restartButtonRect == null){
            restartButtonRect = (Rectangle) continueButtonRect.clone();
            restartButtonRect.setLocation(x,y-restartButtonRect.height);
        }

        g2d.drawString(RESTART,x,y);

        //Draw the Exit Button
        y *= 3.0/2;

        if(exitButtonRect == null){
            exitButtonRect = (Rectangle) continueButtonRect.clone();
            exitButtonRect.setLocation(x,y-exitButtonRect.height);
        }

        g2d.drawString(EXIT,x,y);
        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }

    /**
     * Getter method to return the message
     * @return The message set
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter method to set the message
     * @param message The string to be set in message variable
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Getter method to get the message2
     * @return The message2
     */
    public String getMessage2() {
        return message2;
    }

    /**
     * Setter method to set the message2
     * @param message2 The string to be set in message2 variable
     */
    public void setMessage2(String message2) {
        this.message2 = message2;
    }

    /**
     * Getter method to get the message3
     * @return The message3
     */
    public String getMessage3() {
        return message3;
    }

    /**
     * Setter method to set the message3
     * @param message3 The string to be set in message3 variable
     */
    public void setMessage3(String message3) {
        this.message3 = message3;
    }

    /**
     * Getter method to get the start message
     * @return The start message
     */
    public String getStart_message() {
        return start_message;
    }

    /**
     * Setter method to set the start message
     * @param start_message The string to be set in start_message variable
     */
    public void setStart_message(String start_message) {
        this.start_message = start_message;
    }

    /**
     * Getter method to get the brick info/brick left
     * @return The current brick info message
     */
    public String getBrick_info() {
        return brick_info;
    }

    /**
     * Setter method to set and update the brick info
     * @param brick_info The string to be set in brick_info variable
     */
    public void setBrick_info(String brick_info) {
        this.brick_info = brick_info;
    }

    /**
     * Getter method to get the ball info/ball lives
     * @return The current ball_info message
     */
    public String getBall_info() {
        return ball_info;
    }

    /**
     * Setter method to set the ball info
     * @param ball_info The string to be set in ball_info variable
     */
    public void setBall_info(String ball_info) {
        this.ball_info = ball_info;
    }

    /**
     * Getter method to get the score info
     * @return The current score_info message
     */
    public String getScore_info() {
        return score_info;
    }

    /**
     * Setter method to set the score info
     * @param score_info The string to set in score_info variable
     */
    public void setScore_info(String score_info) {
        this.score_info = score_info;
    }

    /**
     * Getter method to get the message4
     * @return The message 4
     */
    public String getMessage4() {
        return message4;
    }

    /**
     * Setter method to set the message4
     * @param message4 The string to set in message4 variable
     */
    public void setMessage4(String message4) {
        this.message4 = message4;
    }

    /**
     * To get the dimension, shape and size of the CONTINUE button
     * @return The dimension, shape and size of the CONTINUE button
     */
    public Rectangle getContinueButtonRect(){
        return continueButtonRect;
    }

    /**
     * To get the dimension, shape and size of the EXIT button
     * @return The dimension, shape and size of the EXIT button
     */
    public Rectangle getExitButtonRect(){
        return exitButtonRect;
    }

    /**
     * To get the dimension, shape and size of the RESTART button
     * @return The dimension, shape and size of the RESTART button
     */
    public Rectangle getRestartButtonRect(){
        return restartButtonRect;
    }

    /**
     * Setter method to set the highscore string
     * @param HighScore The string to set in HighScore variable
     */
    public void setHighScore(String HighScore){
        this.highScore = HighScore;
    }

    /**
     * Getter method to get the highscore string
     * @return The current highscore string
     */
    public String getHighScore(){
        return highScore;
    }
}
