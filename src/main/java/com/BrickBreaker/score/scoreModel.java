package com.BrickBreaker.score;

/**
 * This is scoreModel class
 * @author Alden Sia Zheng Heng
 * @version 1.0
 * @since 3/11/2021
 */

public class scoreModel {
    private static int score;

    /**
     *  This method is used to get the current player's score
     * @return the score of the player
     */
    public int getScore(){
        return score;
    }

    /**
     * This method is used to set the score
     * @param Score Current player's score to be saved
     */
    public void setScore(int Score){
        this.score = Score;
    }

    /**
     * Add the bonus score to the current score when meet the requirement
     * @param bonus The bonus score to be added into current score
     * @return The score that is added with bonus score
     */
    public int bonusScore(int bonus){
        return score += bonus;
    }

    /**
     * Deduct current score when meet the penalty requirement
     * @param loss_mark The penalty score to deduct the current score
     * @return The score that has been deducted
     */
    public int penalty(int loss_mark){
        if(score < loss_mark){
            score = 0;
            return 0;
        }
        return score -= loss_mark;}
}
