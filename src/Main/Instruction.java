package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class Instruction extends JComponent implements MouseListener, MouseMotionListener {

    private static final Color BG_COLOR = Color.GREEN.darker();
    private BasicStroke borderStoke;
    private BasicStroke borderStoke_noDashes;
    private static final Color BORDER_COLOR = new Color(200,8,21); //Venetian Red
    private static final Color DASH_BORDER_COLOR = new  Color(255, 216, 0);//school bus yellow
    private static final int BORDER_SIZE = 5;
    private static final float[] DASHES = {12,6};

    private int DEF_WIDTH = 600;
    private int DEF_HEIGHT = 450;

    private Rectangle instruction_Board;

    private Rectangle backButton;
    private boolean backClicked;
    private static final String BACK_TEXT = "Back";
    private Font buttonFont;
    private static final Color CLICKED_BUTTON_COLOR = BG_COLOR.brighter();
    private static final Color CLICKED_TEXT = Color.WHITE;

    private GameFrame owner;


    public Instruction(GameFrame owner, Dimension area){
        initialize();

        this.owner = owner;

        //instruction_Board = new Rectangle(new Point(0,0),area);
        borderStoke = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0,DASHES,0);
        borderStoke_noDashes = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);

        Dimension btnDim = new Dimension(area.width / 2, area.height / 12); //3,12
        backButton = new Rectangle(btnDim);

        buttonFont = new Font("Monospaced",Font.PLAIN,35);

        instruction_Board = new Rectangle(new Point(0,0),area);
    }

    private void initialize(){
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public void paint(Graphics g){
        //super.paintComponent(g);
        //g.fillRect(0,0,getWidth(),getHeight());
        Font font = g.getFont().deriveFont( 20.0f );
        g.setFont(font);
        drawContainer((Graphics2D) g);
        instruction_list((Graphics2D) g);
        drawBackButton((Graphics2D) g);
    }


    private void drawContainer(Graphics2D g2d){
        Color prev = g2d.getColor();

        g2d.setColor(BG_COLOR);

        //Fill the color of the menu face
        g2d.fill(instruction_Board);

        //Returns the current Stroke in the Graphics2D context.
        Stroke tmp = g2d.getStroke();

        //Setting the stroke
        g2d.setStroke(borderStoke_noDashes);
        g2d.setColor(DASH_BORDER_COLOR);
        g2d.draw(instruction_Board);

        g2d.setStroke(borderStoke);
        g2d.setColor(BORDER_COLOR);
        g2d.draw(instruction_Board);

        g2d.setStroke(tmp);

        g2d.setColor(prev);
    }

    public void instruction_list(Graphics2D g2d){
        Font f = new Font("Gill Sans Ultra Bold",Font.PLAIN,50);
        g2d.setFont(f);
        g2d.drawString("HOW TO PLAY",20,40);

        Font t = new Font("Times New Roman",Font.PLAIN,20);
        g2d.setFont(t);
        g2d.drawString("1. The Controller will be [A], [D], [SPACE] and [ESC] button.", 10,80);
        g2d.drawString("2. Press [SPACE] to start the game.", 10,100);
        g2d.drawString("3. Press [A] to move to left.", 10,120);
        g2d.drawString("4. Press [D] to move to right.", 10,140);
        g2d.drawString("5. Press [ESC] to open Pause Menu.", 10,160);
        g2d.drawString("6. Press [ALT] + [SHIFT] + [F1] to open console.", 10,180);
        g2d.drawString("7. Destroy all the brick and you will enter next level.", 10,200);
        g2d.drawString("8. Lose all 3 lives and you lose the game.", 10,220);

    }

    private void drawBackButton(Graphics2D g2d){
        FontRenderContext frc = g2d.getFontRenderContext();
        Rectangle2D txtRect = buttonFont.getStringBounds(BACK_TEXT,frc);

        int x, y;
        g2d.setFont(buttonFont);
        x = (instruction_Board.width - backButton.width) / 2;
        y = (int) ((instruction_Board.height - backButton.height) * 0.6);

        backButton.setLocation(x,y);

        x = (int)(backButton.getWidth() - txtRect.getWidth()) / 2;
        y = (int)(backButton.getHeight() - txtRect.getHeight()) / 2;

        x += backButton.x;
        y += backButton.y + (backButton.height * 0.9);

        backButtonClicked(g2d,x,y);
    }

    public void backButtonClicked(Graphics2D g2d, int x, int y){
        if(backClicked){
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(backButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(BACK_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(backButton);
            g2d.drawString(BACK_TEXT,x,y);
        }

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(backButton.contains(p)){
            owner.enableHomeMenu();
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(backButton.contains(p)){
            backClicked = true;
            backButtonRepaint();
        }
    }

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
