package com.musicarray.codeclan.blackjack;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by user on 12/28/17.
 */

public class GameMaster implements Serializable {
    private Player player;
    private Computer computer;
    private String gameStatus;
    private boolean winState;

    public GameMaster(Player player, Computer computer) {
        this.player = player;
        this.computer = computer;
        this.gameStatus = "";
        this.winState = false;
    }

    public boolean getWinState() {
        return winState;
    }

    public void setWinState(boolean winState) {
        this.winState = winState;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void checkWinner(){
       if (computer.getHoldStatus() == true && player.getHoldStatus() == true &&
               player.getHandValue() == 21 && computer.computerHandValue() == 21){
           setGameStatus("Everybody Wins!");
           setWinState(true);
       }

       else if (computer.getHoldStatus() == true && player.getHoldStatus() == true &&
               computer.computerHandValue() == 21){
           setGameStatus("Computer Wins!");
           setWinState(true);
       }

       else if (computer.getHoldStatus() == true && player.getHoldStatus() == true &&
               player.getHandValue() == 21){
            setGameStatus("You Win!");
            setWinState(true);
        }

       else if (player.getHandValue() > 21 && computer.computerHandValue() < 21){
           setGameStatus("You Lose. Hand over 21");
           setWinState(true);
        }

        else if (computer.computerHandValue() > 21 && player.getHandValue() < 21){
           setGameStatus("You win! The computers hand is over 21");
           setWinState(true);
        }

        else if (computer.computerHandValue() > 21 && player.getHandValue() > 21){
            setGameStatus("Everybody Loses! Both hands over 21");
            setWinState(true);
       }

       else if (computer.getHoldStatus() == true && player.getHoldStatus() == true &&
               computer.computerHandValue() == player.getHandValue()){
           setGameStatus("Its a draw! Both hands are equal");
           setWinState(true);
       }

       else if (computer.getHoldStatus() == true && player.getHoldStatus() == true &&
               computer.computerHandValue() > player.getHandValue()){
            setGameStatus("Computer Wins with the highest hand");
            setWinState(true);
       }

       else if (computer.getHoldStatus() == true && player.getHoldStatus() == true &&
               player.getHandValue() > computer.computerHandValue()){
           setGameStatus("Player Wins with the highest hand");
           setWinState(true);
       }
    }


}
