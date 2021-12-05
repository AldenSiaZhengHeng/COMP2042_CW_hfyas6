package com.BrickBreaker.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * This is the Instruction class
 * @author Alden Sia Zheng Heng
 * @version 1.0
 * @since 3/11/2021
 */
public class Instruction extends JComponent implements MouseListener, MouseMotionListener {

    //Variable to set the text color
    private static final Color txt_COLOR = new Color(245, 222, 179, 255);
    private static final Color CLICKED_TEXT = Color.WHITE;

    //The width and height of the frame
    private int DEF_WIDTH = 600;
    private int DEF_HEIGHT = 450;

    //Rectangle variable to create the board and button
    private Rectangle instruction_Board;
    private Rectangle backButton;

    //Variable to check the click action and back button text
    private boolean backClicked;
    private static final String BACK_TEXT = "BACK";

    //Object variable
    private Image Background;
    private GameFrame owner;
    private WordFontStyle font;

    /**
     * This is instruction constructor
     * @param owner This object of the GameFrame
     * @param area The dimension area to set for the instruction pages
     */
    public Instruction(GameFrame owner, Dimension area){
        font = new WordFontStyle();
        instruction_Board = new Rectangle(new Point(0,0),area);
        this.owner = owner;
        initialize();
        Dimension btnDim = new Dimension(area.width / 4, area.height / 12); //3,12
        backButton = new Rectangle(btnDim);

    }

    /**
     * Set the size of the frame and add mouse and key listener to instruction page
     */
    private void initialize(){
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    /**
     * Allow us to draw and paint on the frame (window)
     * @param g It is the graphic object
     */
    public void paint(Graphics g){
        instruction_list((Graphics2D) g);
        drawBackButton((Graphics2D) g);
    }


    /**
     * This will show the background and draw the instruction list on the instruction page
     * @param g2d The object of the Graphics in 2D
     */
    public void instruction_list(Graphics2D g2d){
        //Take background image and show
        Background = Toolkit.getDefaultToolkit().getImage("src/com/BrickBreaker/Images/instruction_background.jpg");
        g2d.drawImage(Background, 0, 0, (int)instruction_Board.getWidth(), (int)instruction_Board.getHeight(),this);

        //Set the color and font for title
        g2d.setColor(Color.BLACK);
        g2d.setFont(font.getInstructionTitleFont());
        g2d.drawString("HOW TO PLAY",70,55);
        g2d.setColor(Color.WHITE);
        g2d.drawString("HOW TO PLAY",80,50);

        //Set the color and font for the instruction list
        g2d.setColor(Color.black);
        g2d.setFont(font.getInstructionListFont());
        g2d.drawString("1. The Controller will be [A], [D], [SPACE], and", 50,90);
        g2d.drawString("    [ESC] key button.", 50,110);
        g2d.drawString("2. Press [SPACE] to start the game.", 50,130);
        g2d.drawString("3. Press [A] to move to left.", 50,152);
        g2d.drawString("4. Press [D] to move to right.", 50,174);
        g2d.drawString("5. Press [ESC] to open Pause Menu.", 50,196);
        g2d.drawString("6. Press [ALT] + [SHIFT] + [F1] to open console.", 50,218);
        g2d.drawString("7. Destroy all the brick and you will enter next level.", 50,240);
        g2d.drawString("8. Lose all 3 lives and you lose the game.", 50,262);
        g2d.drawString("9. Deduct 50 scores per live lose.", 50, 284);
        g2d.drawString("10. Extra scores will be awarded if successful go to next level.",35, 306);
    }


    /**
     * Draw the Back button in instruction page
     * @param g2d The object of the graphics in 2D
     */
    private void drawBackButton(Graphics2D g2d){
        //Fill the background color
        g2d.setColor(txt_COLOR);
        g2d.fill(backButton);

        FontRenderContext frc = g2d.getFontRenderContext();
        Rectangle2D txtRect = font.getBackButtonFont().getStringBounds(BACK_TEXT,frc);

        //Set the text font and the button location
        int x, y;
        g2d.setFont(font.getBackButtonFont());
        x = (instruction_Board.width - backButton.width) / 2;
        y = (int) ((instruction_Board.height - backButton.height) * 0.8);

        backButton.setLocation(x,y);

        x = (int)(backButton.getWidth() - txtRect.getWidth()) / 2;
        y = (int)(backButton.getHeight() - txtRect.getHeight()) / 2;

        x += backButton.x;
        y += backButton.y + (backButton.height * 0.9);

        backButtonClicked(g2d,x,y);
    }

    /**
     * To show the special effect when the back button is clicked
     * @param g2d The object of the Graphics in 2D
     * @param x The x-axis of the back button
     * @param y The y-axis of the back button
     */
    public void backButtonClicked(Graphics2D g2d, int x, int y){
        //If back button is clicked
        if(backClicked){
            //Show the special effect when the button is clicked
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_TEXT);
            g2d.draw(backButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(BACK_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            //Set the color and draw the back button
            g2d.setColor(Color.BLACK);
            g2d.draw(backButton);
            g2d.drawString(BACK_TEXT,x,y);
        }
    }

    /**
     * Return to homeMenu when the back button is pressed and released
     * @param mouseEvent Mouse action object
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        //call the method to return to homemenu if backbutton is pressed and released
        if(backButton.contains(p)){
            owner.enableHomeMenu();
        }
    }

    /**
     * Show the special effect on the button pressed
     * @param mouseEvent Mouse action object
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(backButton.contains(p)){
            backClicked = true;
            backButtonRepaint();
        }
    }

    /**
     * Show the special effect when the button pressed is released
     * @param mouseEvent Mouse action object
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(backClicked){
            backClicked = false;
            backButtonRepaint();
        }
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
     * To detect whether the mouse cursor is pointed at the back button
     * @param mouseEvent The mouse action object
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(backButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());
    }

    public void backButtonRepaint(){
        repaint(backButton.x,backButton.y,backButton.width+1,backButton.height+1);
    }
}
