package gui;

//Add and import package

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;


public class HomeMenuDraw extends JComponent {

    private static final String GREETINGS = "Welcome to:";
    private static final String GAME_TITLE = "Brick Destroy";
    private static final String CREDITS = "Version 0.1";
    private static final String START_TEXT = "START";
    private static final String MENU_TEXT = "EXIT";

    private static final Color TEXT_COLOR = new Color(245, 222, 179);
    private static final Color CLICKED_TEXT = Color.WHITE;

    private GameFrame owner;
    private WordFontStyle font;

    private Rectangle menuFace;
    private Rectangle startButton;
    private Rectangle exitButton;

    private boolean startClicked;
    private boolean exitClicked;

    //Create New instruction button and high score button
    private Rectangle instructionButton;
    private Rectangle score;

    private boolean instructionClicked;
    private boolean scoreClicked;

    private static final String instruction_TEXT = "INFO";

    private Image Background;


    public HomeMenuDraw(GameFrame Owner){
        this.owner = Owner;
    }

    public void setMenuFace(Rectangle MenuFace){
        this.menuFace = MenuFace;
    }

    public void setButton(Rectangle StartButton, Rectangle MenuButton, Rectangle InstructionButton) {
        this.startButton = StartButton;
        this.exitButton = MenuButton;
        this.instructionButton = InstructionButton;
    }

    public void setClick(boolean StartClicked, boolean MenuClicked, boolean InstructionClicked) {
        this.startClicked = StartClicked;
        this.exitClicked = MenuClicked;
        this.instructionClicked = InstructionClicked;
    }

    //Method to draw menu
    public void drawMenu(Graphics2D g2d){
        Background = Toolkit.getDefaultToolkit().getImage("src/Images/homeMenu_background.jpg");
        g2d.drawImage(Background, 0, 0, (int)menuFace.getWidth(), (int)menuFace.getHeight(),this);

        font = new WordFontStyle();

        /*
        all the following method calls need a relative
        painting directly into the HomeMenu rectangle,
        so the translation is made here so the other methods do not do that.
         */
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

    private void drawText(Graphics2D g2d){

        g2d.setColor(Color.black);

        //Get the rendering context of the Font within this Graphics2D context.
        FontRenderContext frc = g2d.getFontRenderContext();

        //Returns the bounds of the specified String in the specified Graphics context.
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

    //Method to draw the button
    private void drawButton(Graphics2D g2d){
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

        //Start Button
        int x1 = (menuFace.width - startButton.width) / 2;
        int y1 =(int) ((menuFace.height - startButton.height) * 0.6); //0.8

        startButton.setLocation(x1,y1);

        x1 = (int)(startButton.getWidth() - txtRect.getWidth()) / 2;
        y1 = (int)(startButton.getHeight() - txtRect.getHeight()) / 2;
        //Adjust the text height
        x1 += startButton.x;
        y1 += startButton.y + (startButton.height * 0.9);

        startButtonClicked(g2d,x1,y1);

        //Instruction button
        int x2 = startButton.x;
        int y2 = startButton.y;

        y2 *= 1.2;
        instructionButton.setLocation(x2,y2);

        x2 = (int)(instructionButton.getWidth() - iTxtRect.getWidth()) / 2;
        y2 = (int)(instructionButton.getHeight() - iTxtRect.getHeight()) / 2;

        x2 += instructionButton.x;
        y2 += instructionButton.y + (startButton.height * 0.9);

        instructionButtonClicked(g2d,x2,y2);


        //Exit Button
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

    public void startButtonClicked(Graphics2D g2d, int x, int y){
        //May can refactor as the condition is not necessary
        //Respond when click the start button and show that start button is clicked
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
