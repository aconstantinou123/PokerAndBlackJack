package com.musicarray.codeclan.blackjack;

/**
 * Created by user on 12/28/17.
 */

public class Player {

    private String name;
    private Hand hand;

    public Player(String name,Hand hand) {
        this.name = name;
        this.hand = hand;
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
