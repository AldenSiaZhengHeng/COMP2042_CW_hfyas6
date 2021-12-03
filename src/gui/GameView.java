package gui;

import element.Ball;
import element.Brick;
import element.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;

public class GameView extends  JComponent{
    private static final String CONTINUE = "Continue";
    private static final String RESTART = "Restart";
    private static final String EXIT = "Exit";
    private static final String PAUSE = "Pause Menu";
    private static final Color MENU_COLOR = new Color(0,255,0);
    private static final Color FONT_COLOR = new Color(7, 7, 7);

    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private String message;
    private String message2;
    private String message3;
    private String message4;
    private String start_message;
    private String brick_info;
    private String ball_info;
    private String score_info;

    private String highScore="";

    private boolean showPauseMenu;

    private Rectangle continueButtonRect;
    private Rectangle exitButtonRect;
    private Rectangle restartButtonRect;

    private int strLen;

    private WordFontStyle font;

    private GameController owner;


    public GameView(GameController owner){
        this.owner = owner;
        font = new WordFontStyle();
        strLen = 0;
        showPauseMenu = false;

        initialize_message();
    }

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

    public void initialize(){
        owner.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        owner.setFocusable(true);
        owner.requestFocusInWindow();
        owner.addKeyListener(owner);
        owner.addMouseListener(owner);
        owner.addMouseMotionListener(owner);
    }

    public void updatePause(boolean pause){
        this.showPauseMenu = pause;
    }


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

    private void drawBrick(Brick brick,Graphics2D g2d){
        Color tmp = g2d.getColor();

        g2d.setColor(brick.getInnerColor());
        g2d.fill(brick.getBrick());

        g2d.setColor(brick.getBorderColor());
        g2d.draw(brick.getBrick());


        g2d.setColor(tmp);
    }

    private void drawBall(Ball ball, Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = ball.getBallFace();

        g2d.setColor(ball.getInnerColor());
        g2d.fill(s);

        g2d.setColor(ball.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    private void drawPlayer(Player p, Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = p.getPlayerFace();
        g2d.setColor(Player.INNER_COLOR);
        g2d.fill(s);

        g2d.setColor(Player.BORDER_COLOR);
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    private void drawMenu(Graphics2D g2d){
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

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

    private void drawPauseMenu(Graphics2D g2d){
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();


        g2d.setFont(font.getPauseMenuFont());
        g2d.setColor(MENU_COLOR);

        if(strLen == 0){
            FontRenderContext frc = g2d.getFontRenderContext();
            strLen = font.getPauseMenuFont().getStringBounds(PAUSE,frc).getBounds().width;
        }

        int x = (owner.getWidth() - strLen) / 2;
        int y = owner.getHeight() / 10;

        g2d.drawString(PAUSE,x,y);

        x = owner.getWidth() / 8;
        y = owner.getHeight() / 4;


        if(continueButtonRect == null){
            FontRenderContext frc = g2d.getFontRenderContext();
            continueButtonRect = font.getPauseMenuFont().getStringBounds(CONTINUE,frc).getBounds();
            continueButtonRect.setLocation(x,y-continueButtonRect.height);
        }

        g2d.drawString(CONTINUE,x,y);

        y *= 2;

        if(restartButtonRect == null){
            restartButtonRect = (Rectangle) continueButtonRect.clone();
            restartButtonRect.setLocation(x,y-restartButtonRect.height);
        }

        g2d.drawString(RESTART,x,y);

        y *= 3.0/2;

        if(exitButtonRect == null){
            exitButtonRect = (Rectangle) continueButtonRect.clone();
            exitButtonRect.setLocation(x,y-exitButtonRect.height);
        }

        g2d.drawString(EXIT,x,y);
        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage2() {
        return message2;
    }

    public void setMessage2(String message2) {
        this.message2 = message2;
    }

    public String getMessage3() {
        return message3;
    }

    public void setMessage3(String message3) {
        this.message3 = message3;
    }

    public String getStart_message() {
        return start_message;
    }

    public void setStart_message(String start_message) {
        this.start_message = start_message;
    }

    public String getBrick_info() {
        return brick_info;
    }

    public void setBrick_info(String brick_info) {
        this.brick_info = brick_info;
    }

    public String getBall_info() {
        return ball_info;
    }

    public void setBall_info(String ball_info) {
        this.ball_info = ball_info;
    }

    public String getScore_info() {
        return score_info;
    }

    public void setScore_info(String score_info) {
        this.score_info = score_info;
    }

    public String getMessage4() {
        return message4;
    }

    public void setMessage4(String message4) {
        this.message4 = message4;
    }

    public Rectangle getContinueButtonRect(){
        return continueButtonRect;
    }

    public Rectangle getExitButtonRect(){
        return exitButtonRect;
    }

    public Rectangle getRestartButtonRect(){
        return restartButtonRect;
    }

    public void setHighScore(String HighScore){
        this.highScore = HighScore;
    }

    public String getHighScore(){
        return highScore;
    }
}
