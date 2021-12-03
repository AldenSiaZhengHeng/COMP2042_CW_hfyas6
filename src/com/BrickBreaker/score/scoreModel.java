package com.BrickBreaker.score;

public class scoreModel {
    private static int score;

    public scoreModel(){
    }

    public int getScore(){
        return score;
    }

    public void setScore(int Score){
        this.score = Score;
    }

    public int bonusScore(int bonus){
        return score += bonus;
    }

    public int penalty(int loss_mark){
        if(score < loss_mark){
            score = 0;
            return 0;
        }
        return score -= 20;}
}
