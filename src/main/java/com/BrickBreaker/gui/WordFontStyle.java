package com.BrickBreaker.gui;

import java.awt.*;

/**
 * This is the WordFontStyle class
 * @author Alden Sia Zheng Heng
 * @version 1.0
 * @since 3/11/2021
 */
public class WordFontStyle {

    private Font greetingsFont;
    private Font gameTitleFont;
    private Font creditsFont;
    private Font buttonFont;
    private Font pauseMenuFont;
    private Font backButtonFont;
    private Font instructionTitleFont;
    private Font instructionListFont;

    /**
     * This is the constructor of WordFontStyle
     */
    public WordFontStyle(){
        //Assign Font style to each variable
        greetingsFont = new Font("Stencil",Font.ITALIC,45);
        gameTitleFont = new Font("Stencil",Font.BOLD,55);
        creditsFont = new Font("Stencil",Font.PLAIN,20);
        buttonFont = new Font("Aharoni",Font.PLAIN,35);
        pauseMenuFont = new Font("Monospaced",Font.PLAIN,30);
        backButtonFont = new Font("Aharoni",Font.PLAIN,35);
        instructionTitleFont = new Font("Gill Sans Ultra Bold",Font.ITALIC,50);
        instructionListFont = new Font("Times New Roman",Font.PLAIN,22);
    }

    /**
     * @return the Greeting font styles
     */
    public Font getGreetingsFont(){
        return greetingsFont;
    }

    public Font getGameTitleFont(){
        return gameTitleFont;
    }

    public Font getCreditsFont(){
        return creditsFont;
    }

    public Font getButtonFont(){
        return buttonFont;
    }

    public Font getPauseMenuFont(){
        return pauseMenuFont;
    }

    public Font getBackButtonFont(){
        return backButtonFont;
    }

    public Font getInstructionTitleFont(){
        return instructionTitleFont;
    }

    public Font getInstructionListFont(){
        return instructionListFont;
    }
}
