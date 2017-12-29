package com.musicarray.codeclan.blackjack;

import java.util.ArrayList;

/**
 * Created by user on 12/28/17.
 */

public class GameMaster {
    private Player player;
    private Computer computer;
    private String gameStatus;

    public GameMaster(Player player, Computer computer) {
        this.player = player;
        this.computer = computer;
        this.gameStatus = "";
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
       if (player.getHandValue() == 21 && computer.computerHandValue() == 21){
           setGameStatus("Everybody Wins!");
       }

       else if (computer.computerHandValue() == 21){
           setGameStatus("Computer Wins!");
       }

       else if (player.getHandValue() == 21){
            setGameStatus("You Win!");
        }

       else if (player.getHandValue() > 21 && computer.computerHandValue() < 21){
           setGameStatus("You Lose. Hand over 21");
        }

        else if (computer.computerHandValue() > 21 && player.getHandValue() < 21){
           setGameStatus("You win! The computers hand is over 21");
        }

        else if (computer.computerHandValue() > 21 && player.getHandValue() > 21){
            setGameStatus("Everybody Loses! Both hands over 21");
       }

       else if (computer.getHoldStatus() == true && player.getHoldStatus() == true &&
               computer.computerHandValue() == player.getHandValue()){
           setGameStatus("Its a draw! Both hands are equal");
       }

       else if (computer.getHoldStatus() == true && player.getHoldStatus() == true &&
               computer.computerHandValue() > player.getHandValue()){
            setGameStatus("Computer Wins with the highest hand");
       }

       else if (computer.getHoldStatus() == true && player.getHoldStatus() == true &&
               player.getHandValue() > computer.computerHandValue()){
           setGameStatus("Player Wins with the highest hand");
       }
    }


}
