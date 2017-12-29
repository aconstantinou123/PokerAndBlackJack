package com.musicarray.codeclan.blackjack;

/**
 * Created by user on 12/28/17.
 */

public class Player {

    private String name;
    private Hand hand;
    private boolean holdStatus;

    public Player(String name,Hand hand) {
        this.name = name;
        this.hand = hand;
        this.holdStatus = false;
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
