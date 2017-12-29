package com.musicarray.codeclan.blackjack;

import java.io.Serializable;

/**
 * Created by user on 12/28/17.
 */

public class Card implements Serializable {

    private CardValue cardValue;
    private SuitType suitType;

    public Card(CardValue cardValue, SuitType suitType) {
        this.cardValue = cardValue;
        this.suitType = suitType;
    }

    public CardValue getCardValue() {
        return cardValue;
    }

    public void setCardValue(CardValue cardValue) {
        this.cardValue = cardValue;
    }

    public SuitType getSuitType() {
        return suitType;
    }

    public void setSuitType(SuitType suitType) {
        this.suitType = suitType;
    }

    public String prettyName() {
        return "The " + getCardValue().prettyName + " of " + getSuitType().type;
    }

    public String hideCard() {
        return "Secret";
    }
}
