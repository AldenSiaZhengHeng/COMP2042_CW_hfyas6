package com.BrickBreaker.score;

//Import Package
import com.BrickBreaker.element.brick.Brick;

import javax.swing.*;
import java.io.*;

/**
 * This is scoreController class that used to call the data from model, read and write the file.
 * @author Alden Sia Zheng Heng
 * @version 1.0
 * @since 3/11/2021
 */

public class scoreController {
    private Brick brick;
    private scoreModel ScoreModel;

    /**
     * This is scoreController class constructor
     */
    public scoreController() {
        ScoreModel = new scoreModel();
    }

    /**
     * This class is used to give bonus score based on the ball lives
     * @param ball_lives To compare and give bonusScore
     */
    public void givebonus(int ball_lives){
        if(ball_lives == 3){
            ScoreModel.bonusScore(100);
        }
        else if(ball_lives == 2){
            ScoreModel.bonusScore(50);
        }
        else if(ball_lives == 1){
            ScoreModel.bonusScore(20);
        }
    }

    /**
     * This is a method to pass the penalty mark to the penalty method
     */
    public void givePenalty(){
        ScoreModel.penalty(50);
    }


    /**
     * To check whether the current score is higher than highscore
     * @param highscore highest score in the list
     */
    public void checkScore(String highscore){
        int highSc;
        String parts[] = highscore.split(":");
        //System.out.println(parts[1]);
        if(GetHighScore() == "0" || GetHighScore()==null){
            highSc = 0;
        }
        else{
            highSc = Integer.parseInt(parts[2]);
        }

        if(ScoreModel.getScore() > highSc || GetHighScore()==null){
            String name = JOptionPane.showInputDialog("You set a new high score. What is your name?");
            String scoring = name + ":" + ScoreModel.getScore();
            writeFile(scoring,ScoreModel.getScore());
        }
    }


    /**
     * This method is to read the txt file and return the text read
     * @return Return the text read from the txt file
     */
    public String GetHighScore(){
        FileReader readFile = null;
        BufferedReader reader = null;
        try
        {
            readFile = new FileReader("src/main/resources/scorelist/highscore.txt");
            reader = new BufferedReader(readFile);
            return reader.readLine();
        }
        catch (Exception e)
        {
            return "Nobody:0";
        }
        finally
        {
            try
            {
                if(reader != null)
                    reader.close();
            } catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * This method is used to write the file if current score is higher than highscore
     * @param name Player's name to write in the txt file
     * @param score Player's score to write in the txt file
     */
    public void writeFile(String name,int score){
        File scoreFile = new File("src/main/resources/scorelist/highscore.txt");
        if(!scoreFile.exists()) {
            try {
                scoreFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileWriter writeFile = null;
        BufferedWriter  writer = null;
        try
        {
            writeFile = new FileWriter(scoreFile);
            writer = new BufferedWriter(writeFile);
            writer.write(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(writer != null){
                    writer.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
