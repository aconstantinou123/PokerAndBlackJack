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

    public void clearHand(){
        cardsHeld.clear();
    }

    public int getHandValue() {
        int lowScore = 0;
        int highScore = 0;
        int score;
            for(Card card : cardsHeld){
                lowScore += card.getCardValue().getValue();
                highScore += card.getCardValue().getValue();
        }
        highScore += 10;
        if (checkAces() == true && highScore <= 21){
                score = highScore;
        }
        else {
            score = lowScore;
        }
        return score;
    }

    public boolean checkAces(){
        for (Card card : cardsHeld){
            if (card.getCardValue() == CardValue.ACE){
                return true;
            }
        }
        return false;
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

    public ArrayList<String> pictureIDs() {
        ArrayList<String> cards = new ArrayList<>();
        for (Card card : cardsHeld){
            cards.add(card.getCardPicture());
        }
       return cards;
    }

}
