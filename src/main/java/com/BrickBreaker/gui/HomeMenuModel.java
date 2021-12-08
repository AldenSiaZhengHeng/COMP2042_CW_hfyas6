package com.BrickBreaker.gui;

import java.awt.*;

/**
 * This is the HomeMenuModel class to store the data for creating HomeMenu pages on window.
 * This is a Model class created to adhere MVC pattern
 * @author Alden Sia Zheng Heng
 * @version 1.0
 * @since 3/11/2021
 */
public class HomeMenuModel {

    //Variable to create the homemenu board and button
    private Dimension buttonDimension;
    private Rectangle HomemenuFace;
    private Rectangle startButton;
    private Rectangle exitButton;
    private Rectangle instructionButton;

    /**
     * This method will set the preferred size for each button on homemenu
     */
    public void setButtonDimension(){
        startButton = new Rectangle(getButtonDimension());
        exitButton = new Rectangle(getButtonDimension());
        instructionButton = new Rectangle(getButtonDimension());
    }

    /**
     * To return the HomeMenuFace board
     * @return The HomemenuFace size for add into frame
     */
    public Rectangle getHomemenuFace(){
        return HomemenuFace;
    }

    /**
     * To set the homemenuFace variable
     * @param menuface The menuface board created in HomeMenuController
     */
    public void setHomemenuFace(Rectangle menuface){
        this.HomemenuFace = menuface;
    }

    /**
     * Setter method for button dimension
     * @param ButtonDimension The Button dimension and size created in HomeMenuController
     */
    public void setButtonDimension(Dimension ButtonDimension){
        this.buttonDimension = ButtonDimension;
    }

    /**
     * Getter method for buttonDimension
     * @return The button dimension and size
     */
    public Dimension getButtonDimension(){
        return buttonDimension;
    }

    /**
     * The getter method for StartButton
     * @return The start button dimension and size
     */
    public Rectangle getStartButton(){
        return startButton;
    }

    /**
     * The getter method for ExitButton
     * @return The exit button dimension and size
     */
    public Rectangle getExitButton(){
        return exitButton;
    }

    /**
     * The getter method for instructionButton
     * @return The instruction button dimension and size
     */
    public Rectangle getInstructionButton(){
        return instructionButton;
    }
}
