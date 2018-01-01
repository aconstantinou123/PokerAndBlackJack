package com.musicarray.codeclan.blackjack;

import java.io.Serializable;

/**
 * Created by user on 1/1/18.
 */

public class Score implements Serializable {
    private Integer playerScore;
    private Integer computerScore;

    public Score() {
        this.playerScore = 0;
        this.computerScore = 0;
    }

    public Integer getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public Integer getComputerScore() {
        return computerScore;
    }

    public void setComputerScore(int computerScore) {
        this.computerScore = computerScore;
    }

    public void addPointPlayer(){
        this.playerScore += 1;
    }

    public void addPointComputer(){
        this.computerScore += 1;
    }
}
