package Main;

//Add and import package
import Debug.*;
import Element.*;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;


public class HomeMenuDraw extends JComponent {

    private static final String GREETINGS = "Welcome to:";
    private static final String GAME_TITLE = "Brick Destroy";
    private static final String CREDITS = "Version 0.1";
    private static final String START_TEXT = "Start";
    private static final String MENU_TEXT = "Exit";

    private static final Color BG_COLOR = Color.GREEN.darker();
    private static final Color TEXT_COLOR = new Color(16, 52, 166);//egyptian blue
    private static final Color CLICKED_BUTTON_COLOR = BG_COLOR.brighter();
    private static final Color CLICKED_TEXT = Color.WHITE;

    private GameFrame owner;

    private Rectangle menuFace;
    private Rectangle startButton;
    private Rectangle exitButton;

    //Font datatype
    private Font greetingsFont;
    private Font gameTitleFont;
    private Font creditsFont;
    private Font buttonFont;

    private boolean startClicked;
    private boolean exitClicked;

    //Border size and the dash
    private static final int BORDER_SIZE = 5;
    private static final float[] DASHES = {12,6};

    //BasicStroke -> Border
    private BasicStroke borderStoke;
    private BasicStroke borderStoke_noDashes;

    private static final Color BORDER_COLOR = new Color(200,8,21); //Venetian Red
    private static final Color DASH_BORDER_COLOR = new  Color(255, 216, 0);//school bus yellow


    public HomeMenuDraw(GameFrame Owner){
        this.owner = Owner;
    }

    public void setMenuFace(Rectangle MenuFace){
        this.menuFace = MenuFace;
    }

    public Rectangle getMenuFace(){
        return menuFace;
    }

    public void setFontStyle(Font GreetingsFont, Font GameTitleFont, Font CreditsFont, Font ButtonFont){
        this.greetingsFont = GreetingsFont;
        this.gameTitleFont = GameTitleFont;
        this.creditsFont = CreditsFont;
        this.buttonFont = ButtonFont;
    }

    public void setButton(Rectangle StartButton, Rectangle MenuButton) {
        this.startButton = StartButton;
        this.exitButton = MenuButton;
    }

    public void setClick(boolean StartClicked, boolean MenuClicked) {
        this.startClicked = StartClicked;
        this.exitClicked = MenuClicked;
    }

    //Method to draw menu
    public void drawMenu(Graphics2D g2d){

         //Create a borderstoke object
         borderStoke = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0,DASHES,0);
         borderStoke_noDashes = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);

        //call method
        //May not required for this
        drawContainer(g2d);

        /*
        all the following method calls need a relative
        painting directly into the HomeMenu rectangle,
        so the translation is made here so the other methods do not do that.
         */
        //variable to gets this graphics context's current color.
        Color prevColor = g2d.getColor();

        //variable to gets the current font
        Font prevFont = g2d.getFont();

        //Returns the X coordinate of menuFace in double precision.
        double x = getMenuFace().getX();
        //Returns the Y coordinate of menuFace in double precision.
        double y = getMenuFace().getY();

        //Translates the origin of the graphics context to the point (x, y) in the current coordinate system.
        g2d.translate(x,y);

        //methods calls for draw text and button
        drawText(g2d);
        drawButton(g2d);
        //end of methods calls

        g2d.translate(-x,-y);

        //Sets this graphics context's font to the specified font.
        g2d.setFont(prevFont);
        //Sets this graphics context's current color to the specified color.
        g2d.setColor(prevColor);
    }

    //Method for drawContainer
    //May be useless as

    private void drawContainer(Graphics2D g2d){
        Color prev = g2d.getColor();

        g2d.setColor(BG_COLOR);

        //Fill the color of the menu face
        g2d.fill(menuFace);

        //Returns the current Stroke in the Graphics2D context.
        Stroke tmp = g2d.getStroke();

        //Setting the stroke
        g2d.setStroke(borderStoke_noDashes);
        g2d.setColor(DASH_BORDER_COLOR);
        g2d.draw(menuFace);

        g2d.setStroke(borderStoke);
        g2d.setColor(BORDER_COLOR);
        g2d.draw(menuFace);

        g2d.setStroke(tmp);

        g2d.setColor(prev);
    }


    private void drawText(Graphics2D g2d){

        g2d.setColor(TEXT_COLOR);

        //Get the rendering context of the Font within this Graphics2D context.
        FontRenderContext frc = g2d.getFontRenderContext();

        //Returns the bounds of the specified String in the specified Graphics context.
        Rectangle2D greetingsRect = greetingsFont.getStringBounds(GREETINGS,frc);
        Rectangle2D gameTitleRect = gameTitleFont.getStringBounds(GAME_TITLE,frc);
        Rectangle2D creditsRect = creditsFont.getStringBounds(CREDITS,frc);

        int sX,sY;

        //Adjust the position of the greeting ("Welcome to:")
        sX = (int)(menuFace.getWidth() - greetingsRect.getWidth()) / 2;
        sY = (int)(menuFace.getHeight() / 4);

        g2d.setFont(greetingsFont);
        g2d.drawString(GREETINGS,sX,sY);

        //Adjust the position of the title ("Brick Destroyer")
        sX = (int)(menuFace.getWidth() - gameTitleRect.getWidth()) / 2;
        sY += (int) gameTitleRect.getHeight() * 1.1;//add 10% of String height between the two strings

        g2d.setFont(gameTitleFont);
        g2d.drawString(GAME_TITLE,sX,sY);

        //Adjust the position of the credit (Version)
        sX = (int)(menuFace.getWidth() - creditsRect.getWidth()) / 2;
        sY += (int) creditsRect.getHeight() * 1.1;

        g2d.setFont(creditsFont);
        g2d.drawString(CREDITS,sX,sY);

    }

    //Method to draw the button
    private void drawButton(Graphics2D g2d){

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D txtRect = buttonFont.getStringBounds(START_TEXT,frc);
        Rectangle2D mTxtRect = buttonFont.getStringBounds(MENU_TEXT,frc);

        g2d.setFont(buttonFont);

        int x = (menuFace.width - startButton.width) / 2;
        int y =(int) ((menuFace.height - startButton.height) * 0.8);

        startButton.setLocation(x,y);

        x = (int)(startButton.getWidth() - txtRect.getWidth()) / 2;
        y = (int)(startButton.getHeight() - txtRect.getHeight()) / 2;

        //Adjust the text height
        x += startButton.x;
        y += startButton.y + (startButton.height * 0.9);


        //May can refactor as the condition is not necessary
        //Respond when click the start button and show that start button is clicked
        if(startClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
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

        x = startButton.x;
        y = startButton.y;

        y *= 1.2;

        exitButton.setLocation(x,y);


        x = (int)(exitButton.getWidth() - mTxtRect.getWidth()) / 2;
        y = (int)(exitButton.getHeight() - mTxtRect.getHeight()) / 2;

        x += exitButton.x;
        y += exitButton.y + (startButton.height * 0.9);

        if(exitClicked){
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
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
