package com.BrickBreaker.gui;

import java.awt.*;

public class HomeMenuModel {

    private Dimension buttonDimension;
    private Rectangle HomemenuFace;

    private Rectangle startButton;
    private Rectangle exitButton;
    private Rectangle instructionButton;

    public void setButtonDimension(){
        startButton = new Rectangle(getButtonDimension());
        exitButton = new Rectangle(getButtonDimension());
        instructionButton = new Rectangle(getButtonDimension());
    }
    public Rectangle getHomemenuFace(){
        return HomemenuFace;
    }

    public void setHomemenuFace(Rectangle menuface){
        this.HomemenuFace = menuface;
    }

    public void setButtonDimension(Dimension ButtonDimension){
        this.buttonDimension = ButtonDimension;
    }

    public Dimension getButtonDimension(){
        return buttonDimension;
    }

    public Rectangle getStartButton(){
        return startButton;
    }

    public Rectangle getExitButton(){
        return exitButton;
    }

    public Rectangle getInstructionButton(){
        return instructionButton;
    }
}
