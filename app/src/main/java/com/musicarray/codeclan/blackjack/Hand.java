package com.musicarray.codeclan.blackjack;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by user on 12/28/17.
 */

public class Hand implements Serializable {

    private ArrayList<Card> cardsHeld;


    public Hand() {
        this.cardsHeld = new ArrayList<>();
    }

    public ArrayList<Card> getCardsHeld() {
        return cardsHeld;
    }

    public void addCards(Card card){
        cardsHeld.add(card);
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

    public String viewCards() {
        ArrayList<String> cards = new ArrayList<>();
        for (Card card : cardsHeld){
            cards.add(card.prettyName());
        }
        StringBuilder result = new StringBuilder();
        for (String s : cards)
        {
            result.append(s);
            result.append("\n");
        }
        return result.toString();
    }

    public String viewComputerCards() {
        ArrayList<String> cards = new ArrayList<>();
        for (Card card : cardsHeld) {
            if (card == cardsHeld.get(0)) {
                cards.add(card.hideCard());
            } else if (card != cardsHeld.get(0)) {
                cards.add(card.prettyName());
            }
        }
        StringBuilder result = new StringBuilder();
        for (String s : cards)
        {
            result.append(s);
            result.append("\n");
        }
            return result.toString();
        }

}
