package com.BrickBreaker.gui;


import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;


/**
 * This is the HomeMenuView class
 * @author Alden Sia Zheng Heng
 * @version 1.0
 * @since 3/11/2021
 */
public class HomeMenuView extends JComponent {

    //Text for the homemenu
    private static final String GREETINGS = "Welcome to:";
    private static final String GAME_TITLE = "Brick Destroy";
    private static final String CREDITS = "Version 0.1";
    private static final String START_TEXT = "START";
    private static final String MENU_TEXT = "EXIT";
    private static final String instruction_TEXT = "INFO";

    //Color for the text
    private static final Color TEXT_COLOR = new Color(245, 222, 179);
    private static final Color CLICKED_TEXT = Color.WHITE;

    //Rectangle variable to create the board and button
    private Rectangle menuFace;
    private Rectangle startButton;
    private Rectangle exitButton;
    private Rectangle instructionButton;

    //Variable to check the mouse clicked aciton
    private boolean startClicked;
    private boolean exitClicked;
    private boolean instructionClicked;

    //Object variable
    private Image Background;
    private WordFontStyle font;

    /**
     * Add the key and mouse listener to the homemenu and set the preferred size of the homemenu
     * @param owner HomeMenuController object
     * @param area The area for creating the homemenu board
     */
    public void initialize(HomeMenuController owner,Dimension area){
        owner.setFocusable(true);
        owner.requestFocusInWindow();
        owner.addMouseListener(owner);
        owner.addMouseMotionListener(owner);
        owner.setPreferredSize(area);
    }

    /**
     * To set the menuFace sizes
     * @param MenuFace The variable to set the size of the Homemenu
     */
    public void setMenuFace(Rectangle MenuFace){
        this.menuFace = MenuFace;
    }

    /**
     * To set the size of the button in homemenu
     * @param StartButton The variable to set the size of the startbutton
     * @param ExitButton The variable to set the size of the Exitbutton
     * @param InstructionButton The variable to set the size of the Instructionbutton
     */
    public void setButton(Rectangle StartButton, Rectangle ExitButton, Rectangle InstructionButton) {
        this.startButton = StartButton;
        this.exitButton = ExitButton;
        this.instructionButton = InstructionButton;
    }

    /**
     * To get the instruction whether the button is clicked or not
     * @param StartClicked The variable to determine the startbutton is clicked or not
     * @param ExitClicked The variable to determine the Exittbutton is clicked or not
     * @param InstructionClicked The variable to determine the Instructionbutton is clicked or not
     */
    public void setClick(boolean StartClicked, boolean ExitClicked, boolean InstructionClicked) {
        this.startClicked = StartClicked;
        this.exitClicked = ExitClicked;
        this.instructionClicked = InstructionClicked;
    }

    /**
     * This method is call the others draw methods to draw the homemenu
     * @param g2d The object of the graphics in 2D
     */
    public void drawMenu(Graphics2D g2d){
        //Adding background images
        Background = Toolkit.getDefaultToolkit().getImage("src/com/BrickBreaker/Images/homeMenu_background.jpg");
        g2d.drawImage(Background, 0, 0, (int)menuFace.getWidth(), (int)menuFace.getHeight(),this);

        font = new WordFontStyle();

        Color prevColor = g2d.getColor();
        Font prevFont = g2d.getFont();

        double x = menuFace.getX();
        double y = menuFace.getY();

        g2d.translate(x,y);
        drawText(g2d);
        drawButton(g2d);

        g2d.translate(-x,-y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);
    }

    /**
     * This method will draw the title on the homemenu
     * @param g2d The object of the graphics in 2D
     */
    private void drawText(Graphics2D g2d){
        g2d.setColor(Color.black);

        FontRenderContext frc = g2d.getFontRenderContext();
        Rectangle2D greetingsRect = font.getGreetingsFont().getStringBounds(GREETINGS,frc);
        Rectangle2D gameTitleRect = font.getGameTitleFont().getStringBounds(GAME_TITLE,frc);
        Rectangle2D creditsRect = font.getCreditsFont().getStringBounds(CREDITS,frc);

        int sX,sY, setX = 0, setY = 0;

        //Adjust the position of the greeting ("Welcome to:")
        sX = (int)(menuFace.getWidth() - greetingsRect.getWidth()) / 2;
        sY = (int)(menuFace.getHeight() / 8);

        g2d.setFont(font.getGreetingsFont());
        g2d.drawString(GREETINGS,sX,sY);

        setX = sX + 5;
        setY = sY - 5;
        g2d.setColor(Color.white);
        g2d.drawString(GREETINGS,setX,setY);

        //Adjust the position of the title ("Brick Destroyer")
        g2d.setColor(Color.black);
        sX = (int)(menuFace.getWidth() - gameTitleRect.getWidth()) / 2;
        sY += (int) gameTitleRect.getHeight() * 1.1;//add 10% of String height between the two strings

        g2d.setFont(font.getGameTitleFont());
        g2d.drawString(GAME_TITLE,sX,sY);

        setX = sX + 5;
        setY = sY - 5;
        g2d.setColor(Color.white);
        g2d.drawString(GAME_TITLE,setX,setY);

        //Adjust the position of the credit (Version)
        g2d.setColor(Color.black);
        sX = (int)(menuFace.getWidth() - creditsRect.getWidth()) / 2;
        sY += (int) creditsRect.getHeight() * 1.1;

        g2d.setFont(font.getCreditsFont());
        g2d.drawString(CREDITS,sX,sY);

        setX = sX + 2;
        setY = sY - 2;
        g2d.setColor(Color.white);
        g2d.drawString(CREDITS,setX,setY);
    }

    /**
     * This method will set the color and font of the button and draw them on the homemenu
     * @param g2d The object of the graphics in 2D
     */
    private void drawButton(Graphics2D g2d){
        //Method to set the color of the button background
        g2d.setColor(TEXT_COLOR);
        g2d.fill(startButton);
        g2d.fill(instructionButton);
        g2d.fill(exitButton);

        FontRenderContext frc = g2d.getFontRenderContext();
        Rectangle2D txtRect = font.getButtonFont().getStringBounds(START_TEXT,frc);
        Rectangle2D mTxtRect = font.getButtonFont().getStringBounds(MENU_TEXT,frc);
        Rectangle2D iTxtRect = font.getButtonFont().getStringBounds(instruction_TEXT,frc);

        g2d.setColor(Color.black);
        g2d.setFont(font.getButtonFont());

        //Adjust the position of the startbutton
        int x1 = (menuFace.width - startButton.width) / 2;
        int y1 =(int) ((menuFace.height - startButton.height) * 0.6); //0.8

        startButton.setLocation(x1,y1);

        x1 = (int)(startButton.getWidth() - txtRect.getWidth()) / 2;
        y1 = (int)(startButton.getHeight() - txtRect.getHeight()) / 2;
        //Adjust the text height
        x1 += startButton.x;
        y1 += startButton.y + (startButton.height * 0.9);

        startButtonClicked(g2d,x1,y1);

        //Adjsut the position of the instruction button
        int x2 = startButton.x;
        int y2 = startButton.y;

        y2 *= 1.2;
        instructionButton.setLocation(x2,y2);

        x2 = (int)(instructionButton.getWidth() - iTxtRect.getWidth()) / 2;
        y2 = (int)(instructionButton.getHeight() - iTxtRect.getHeight()) / 2;

        x2 += instructionButton.x;
        y2 += instructionButton.y + (startButton.height * 0.9);

        instructionButtonClicked(g2d,x2,y2);


        //Adjust the position of the exit button
        int x3 = instructionButton.x;
        int y3 = instructionButton.y;

        y3 *= 1.17;

        exitButton.setLocation(x3,y3);

        x3 = (int)(exitButton.getWidth() - mTxtRect.getWidth()) / 2;
        y3 = (int)(exitButton.getHeight() - mTxtRect.getHeight()) / 2;

        x3 += exitButton.x;
        y3 += exitButton.y + (instructionButton.height * 0.9);

        exitButtonClicked(g2d,x3,y3);
    }

    /**
     * To draw the startbutton with different effect depend whether there is clicked action
     * @param g2d The object of the graphics in 2D
     * @param x The x-axis of the text to draw
     * @param y The y-axis of the text to draw
     */
    public void startButtonClicked(Graphics2D g2d, int x, int y){
        if(startClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_TEXT);
            g2d.draw(startButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(START_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            //Show the start button on the menu page
            g2d.draw(startButton);
            g2d.drawString(START_TEXT,x,y);
        }
    }

    /**
     * To draw the instructionbutton with different effect depend whether there is clicked action
     * @param g2d The object of the graphics in 2D
     * @param x The x-axis of the text to draw
     * @param y The y-axis of the text to draw
     */
    public void instructionButtonClicked(Graphics2D g2d, int x, int y){
        if(instructionClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_TEXT);
            g2d.draw(instructionButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(instruction_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(instructionButton);
            g2d.drawString(instruction_TEXT,x,y);
        }
    }


    /**
     * To draw the exitbutton with different effect depend whether there is clicked action
     * @param g2d The object of the graphics in 2D
     * @param x The x-axis of the text to draw
     * @param y The y-axis of the text to draw
     */
    public void exitButtonClicked(Graphics2D g2d, int x, int y){
        if(exitClicked){
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_TEXT);
            g2d.draw(exitButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(MENU_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(exitButton);
            g2d.drawString(MENU_TEXT,x,y);
        }
    }

}
