package com.BrickBreaker.score;

//Import Package
import com.BrickBreaker.element.*;

import javax.swing.*;
import java.io.*;

public class scoreController {
    private Brick brick;
    private scoreModel ScoreModel;

    public void givebonus(int ball_lives){
        if(ball_lives == 3){
            ScoreModel.bonusScore(30);
        }
        else if(ball_lives == 2){
            ScoreModel.bonusScore(20);
        }
        else if(ball_lives == 1){
            ScoreModel.bonusScore(10);
        }
        else{
        }
    }

    public void givePenalty(){
        ScoreModel.penalty(50);
    }


    public scoreController() {
        ScoreModel = new scoreModel();

    }

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
            String name = JOptionPane.showInputDialog("You set a new high com.BrickBreaker.score. What is your name?");
            String scoring = name + ":" + ScoreModel.getScore();
            writeFile(scoring,ScoreModel.getScore());
        }
    }


    public String GetHighScore(){
        FileReader readFile = null;
        BufferedReader reader = null;
        try
        {
            readFile = new FileReader("src/com.BrickBreaker.score/highscore.txt");
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

    public void writeFile(String name,int score){
        File scoreFile = new File("src/com.BrickBreaker.score/highscore.txt");
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
