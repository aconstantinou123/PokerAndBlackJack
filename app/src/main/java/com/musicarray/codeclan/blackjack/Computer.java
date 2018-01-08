package com.musicarray.codeclan.blackjack;

import java.io.Serializable;

/**
 * Created by user on 12/28/17.
 */

public class Computer implements Serializable {

    private Hand hand;
    private boolean holdStatus;
    private String computerStatus;
    private Wallet bank;
    private boolean winner;

    public Computer(Hand hand) {
            this.hand = hand;
            this.holdStatus = false;
            this.computerStatus = "";
            this.bank = new Wallet();
            this.winner = false;
        }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public Wallet getBank() {
        return bank;
    }

    public boolean getHoldStatus() {
        return holdStatus;
    }

    public void setHoldStatus(boolean holdStatus) {
        this.holdStatus = holdStatus;
    }

    public Hand getHand() {
            return hand;
        }

    public void setHand(Hand hand) {
            this.hand = hand;
        }

    public Integer computerHandValue(){
        return hand.getHandValue();
    }

    public String getComputerStatus() {
        return computerStatus;
    }

    public void setComputerStatus(String computerStatus) {
        this.computerStatus = computerStatus;
    }

    public void computerTakeCard(Deck deck) {
        if(computerHandValue() <= 16){
            deck.deal(this.hand);
            setComputerStatus("Computer takes a hit");
        }
        else{
            setHoldStatus(true);
            setComputerStatus("Computer holds");
        }
    }
}
