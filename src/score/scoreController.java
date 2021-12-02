package score;

//Import Package
import element.*;

import javax.swing.*;
import java.io.*;

public class scoreController {
    private Brick brick;
    private scoreModel ScoreModel;

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
            String name = JOptionPane.showInputDialog("You set a new high score. What is your name?");
            String scoring = name + ":" + ScoreModel.getScore();
            writeFile(scoring,ScoreModel.getScore());
        }
    }


    public String GetHighScore(){
        FileReader readFile = null;
        BufferedReader reader = null;
        try
        {
            readFile = new FileReader("src/score/highscore.txt");
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
        File scoreFile = new File("src/score/highscore.txt");
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
