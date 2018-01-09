package com.musicarray.codeclan.blackjack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by user on 12/28/17.
 */

public class Deck implements Serializable {

    private ArrayList<Card> cards;
    private SuitType[] suitType;
    private CardValue[] cardValue;

    public Deck() {
        this.cards = new ArrayList<>();
        this.suitType = SuitType.values();
        this.cardValue = CardValue.values();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void populateDeck(){
        for(int suit = 0; suit < 4; suit ++){
            for (int card = 0; card < 13; card ++){
                cards.add(new Card(cardValue[card], suitType[suit]));
            }
        }
        for (Card card : cards){
            card.setCardPicture();
        }
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    public void deal(Hand hand) {
        Card topCard = cards.remove(0);
        hand.getCardsHeld().add(topCard);
    }

    public void addCard(Card card){
        this.cards.add(card);
    }
}
