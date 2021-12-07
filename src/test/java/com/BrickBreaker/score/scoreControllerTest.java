package com.BrickBreaker.score;

import com.BrickBreaker.score.scoreModel;
import org.junit.Test;

import javax.swing.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.*;

public class scoreControllerTest {

    //To check whether the score set is higher or lower than the highscore in the highscore text file
    @Test
    public void checkScore() {
        String checkscore;
        scoreModel ScoreModel = new scoreModel();
        FileReader readFile = null;
        BufferedReader reader = null;
        try
        {
            readFile = new FileReader("src/main/resources/scorelist/highscore.txt");
            reader = new BufferedReader(readFile);
            checkscore = reader.readLine();
        }
        catch (Exception e)
        {
            checkscore = "Nobody:0";
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
        int highSc;
        String parts[] = checkscore.split(":");
        highSc = Integer.parseInt(parts[1]);
        System.out.println(parts[0]);
        ScoreModel.setScore(50);
        assertTrue(ScoreModel.getScore() > highSc);
    }

    //To test that the file path read is correct
    @Test
    public void getHighScore() {
        String highscore;
        FileReader readFile = null;
        BufferedReader reader = null;
        try
        {
            readFile = new FileReader("src/main/resources/scorelist/highscore.txt");
            reader = new BufferedReader(readFile);
            highscore = reader.readLine();
        }
        catch (Exception e)
        {
            highscore = null;
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
        assertNotNull(highscore);
    }
}