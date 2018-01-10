package com.musicarray.codeclan.blackjack;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by user on 12/28/17.
 */

public class GameMaster implements Serializable {
    private Player player;
    private Computer computer;
    private String gameStatus;
    private boolean winState;
    private Score score;
    private Wallet bettingPool;

    public GameMaster(Player player, Computer computer, Score score) {
        this.player = player;
        this.computer = computer;
        this.gameStatus = "";
        this.winState = false;
        this.score = score;
        this.bettingPool = new Wallet();
    }

    public Wallet getBettingPool() {
        return bettingPool;
    }

    public void setBettingPool(Wallet bettingPool) {
        this.bettingPool = bettingPool;
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

    public Score getScore() {
        return score;
    }

    public void checkWinner(){
       if (computer.getHoldStatus() == true && player.getHoldStatus() == true &&
               player.getHandValue() == 21 && computer.computerHandValue() == 21){
           score.addPointPlayer();
           score.addPointComputer();
           setGameStatus("Everybody Wins!");
           setWinState(true);
       }

       else if (computer.getHoldStatus() == true && player.getHoldStatus() == true &&
               computer.computerHandValue() == 21){
           score.addPointComputer();
           setGameStatus("Computer Wins!");
           setWinState(true);
       }

       else if (computer.getHoldStatus() == true && player.getHoldStatus() == true &&
               player.getHandValue() == 21){
            score.addPointPlayer();
            setGameStatus("You Win!");
            setWinState(true);
        }

       else if (player.getHandValue() > 21 && computer.computerHandValue() < 21){
           score.addPointComputer();
           setGameStatus("You Lose. Hand over 21");
           setWinState(true);
        }

        else if (computer.computerHandValue() > 21 && player.getHandValue() < 21){
           score.addPointPlayer();
           setGameStatus("You Win! The computers hand is over 21");
           setWinState(true);
        }

        else if (computer.computerHandValue() > 21 && player.getHandValue() > 21){
            setGameStatus("Everybody Loses! Both hands over 21");
            setWinState(true);
       }

       else if (computer.getHoldStatus() == true && player.getHoldStatus() == true &&
               computer.computerHandValue() == player.getHandValue()){
             score.addPointPlayer();
             score.addPointComputer();
             setGameStatus("Its a draw! Both hands are equal");
             setWinState(true);
       }

       else if (computer.getHoldStatus() == true && player.getHoldStatus() == true &&
               computer.computerHandValue() > player.getHandValue()){
           score.addPointComputer();
            setGameStatus("Computer wins with the highest hand");
            setWinState(true);
       }

       else if (computer.getHoldStatus() == true && player.getHoldStatus() == true &&
               player.getHandValue() > computer.computerHandValue()){
           score.addPointPlayer();
           setGameStatus("Player wins with the highest hand");
           setWinState(true);
       }
    }

    public void checkWinnerPoker(){
        if (player.getHand().checkHand() == computer.getHand().checkHand()) {
            if (computer.getHand().getHighestPokerCard() > player.getHand().getHighestPokerCard()) {
                setGameStatus("Computer wins with the highest card");
                computer.setWinner(true);
                setWinState(true);
            } else if (player.getHand().getHighestPokerCard() > computer.getHand().getHighestPokerCard()) {
                setGameStatus("Player wins with the highest card");
                setWinState(true);
                player.setWinner(true);
            } else {
                setGameStatus("It's a draw");
                setWinState(true);
                computer.setWinner(true);
                player.setWinner(true);
            }
        }
        else if (computer.getHand().checkHand() > player.getHand().checkHand()){
            setGameStatus("Computer wins with " + computer.getHand().getPokerWinMessage());
            setWinState(true);
            computer.setWinner(true);
        }
        else if (player.getHand().checkHand() > computer.getHand().checkHand()){
            setGameStatus("Player wins with " + player.getHand().getPokerWinMessage());
            setWinState(true);
            player.setWinner(true);
        }

    }

    public boolean gameOver(){
        if (player.getWallet().checkWalletEmpty() == true &&
                computer.isWinner() == true && player.isWinner() == false){
            setGameStatus("You lost all your money. Game Over");
            return true;
        }
        return false;
    }

}
