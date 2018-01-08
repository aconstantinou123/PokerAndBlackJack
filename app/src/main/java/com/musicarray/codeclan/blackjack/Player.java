package com.musicarray.codeclan.blackjack;

import java.io.Serializable;

/**
 * Created by user on 12/28/17.
 */

public class Player implements Serializable {

    private String name;
    private Hand hand;
    private boolean holdStatus;
    private Wallet wallet;
    private boolean winner;

    public Player(String name,Hand hand) {
        this.name = name;
        this.hand = hand;
        this.holdStatus = false;
        this.wallet = new Wallet();
        this.winner = false;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public boolean getHoldStatus() {
        return holdStatus;
    }

    public void setHoldStatus(boolean holdStatus) {
        this.holdStatus = holdStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Integer getHandValue(){
        return hand.getHandValue();
    }
}
