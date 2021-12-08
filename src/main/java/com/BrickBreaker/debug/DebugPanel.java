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
package com.BrickBreaker.debug;

import com.BrickBreaker.gui.*;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;


/**
 * This is the DebugPanel Class create the button and slider to show on the debug dialog panel
 * @author Alden Sia Zheng Heng
 * @version 1.0
 * @since 3/11/2021
 */
public class DebugPanel extends JPanel {

    private static final Color DEF_BKG = Color.WHITE;

    //JButton and JSlider created
    private JButton skipLevel;
    private JButton resetBalls;
    private JSlider ballXSpeed;
    private JSlider ballYSpeed;

    private GameModel gameModel;

    /**
     * The constructor of the DebugPanel class.
     * Assign the function and variable
     * @param gameModel The object of the GameModel class
     */
    public DebugPanel(GameModel gameModel){

        this.gameModel = gameModel;

        initialize();

        skipLevel = makeButton("Skip Level",e -> gameModel.nextLevel());
        resetBalls = makeButton("Reset Balls",e -> gameModel.resetBallCount());

        ballXSpeed = makeSlider(5,10,e -> gameModel.setBallXSpeed(ballXSpeed.getValue()));
        ballYSpeed = makeSlider(-10,-5,e -> gameModel.setBallYSpeed(ballYSpeed.getValue()));

        this.add(skipLevel);
        this.add(resetBalls);

        this.add(ballXSpeed);
        this.add(ballYSpeed);

    }

    /**
     * To set the color of the background and the layout for the debug panel
     */
    private void initialize(){
        this.setBackground(DEF_BKG);
        this.setLayout(new GridLayout(2,2));
    }

    /**
     * This method will create the button and return the action
     * @param title The string to show on the button
     * @param e The action on the button
     * @return The action listened from the button
     */
    private JButton makeButton(String title, ActionListener e){
        JButton out = new JButton(title);
        out.addActionListener(e);
        return out;
    }

    /**
     * This method will create the slider to adjust the speed of the ball
     * @param min Minimum value to be set for the ball
     * @param max Maximum value to be set for the ball
     * @param e The action on the slider
     * @return The changes on the slider
     */
    private JSlider makeSlider(int min, int max, ChangeListener e){
        JSlider out = new JSlider(min,max);
        out.setMajorTickSpacing(1);
        out.setSnapToTicks(true);
        out.setPaintTicks(true);
        out.addChangeListener(e);
        return out;
    }

    /**
     * To adjust the speed of the ball
     * @param x Horizontal speed of the ball
     * @param y Vertical speed of the ball
     */
    public void setValues(int x,int y){
        ballXSpeed.setValue(x);
        ballYSpeed.setValue(y);
    }

}
