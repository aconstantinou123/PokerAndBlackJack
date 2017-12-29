package com.musicarray.codeclan.blackjack;

import java.util.ArrayList;

/**
 * Created by user on 12/28/17.
 */

public class Hand {

    private ArrayList<Card> cardsHeld;


    public Hand() {
        this.cardsHeld = new ArrayList<>();
    }

    public ArrayList<Card> getCardsHeld() {
        return cardsHeld;
    }

    public void setCardsHeld(ArrayList<Card> cardsHeld) {
        this.cardsHeld = cardsHeld;
    }

    public int getHandSize(){
        return cardsHeld.size();
    }

    public int getHandValue() {
        int score = 0;
        for(Card card : cardsHeld){
            score += card.getCardValue().getValue();
        }
        return score;
    }
}
