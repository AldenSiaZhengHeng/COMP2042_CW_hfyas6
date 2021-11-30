package scorelist;

import element.*;

import java.io.*;

public class scoreController {
    private Brick brick;
    public scoreController() {
    }

    public String GetHighScore(){
        FileReader readFile = null;
        BufferedReader reader = null;
        try
        {
            readFile = new FileReader("src/scorelist/highscore.txt");
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
        File scoreFile = new File("src/scorelist/highscore.txt");
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
