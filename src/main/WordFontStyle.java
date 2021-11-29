package main;

import java.awt.*;

public class WordFontStyle {

    private Font greetingsFont;
    private Font gameTitleFont;
    private Font creditsFont;
    private Font buttonFont;
    private Font pauseMenuFont;
    private Font backButtonFont;
    private Font instructionTitleFont;
    private Font instructionListFont;

    public WordFontStyle(){
        greetingsFont = new Font("Stencil",Font.ITALIC,45);
        gameTitleFont = new Font("Stencil",Font.BOLD,55);
        creditsFont = new Font("Stencil",Font.PLAIN,20);
        buttonFont = new Font("Aharoni",Font.PLAIN,35);
        pauseMenuFont = new Font("Monospaced",Font.PLAIN,30);
        backButtonFont = new Font("Aharoni",Font.PLAIN,35);
        instructionTitleFont = new Font("Gill Sans Ultra Bold",Font.ITALIC,50);
        instructionListFont = new Font("Times New Roman",Font.PLAIN,22);
    }

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
