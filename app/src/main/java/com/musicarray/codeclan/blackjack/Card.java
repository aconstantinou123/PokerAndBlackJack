package com.musicarray.codeclan.blackjack;

import android.media.Image;
import android.media.ImageReader;
import android.media.ImageWriter;

import java.io.File;
import java.io.Serializable;

/**
 * Created by user on 12/28/17.
 */

public class Card implements Serializable {

    private CardValue cardValue;
    private SuitType suitType;
    private String cardPicture;

    public Card(CardValue cardValue, SuitType suitType) {
        this.cardValue = cardValue;
        this.suitType = suitType;
        this.cardPicture = null;
    }

    public String getCardPicture() {
        return cardPicture;
    }

    public String getImage() {
        String fileName = this.prettyName().replaceAll("\\s", "").toLowerCase();
        return fileName;
    }


    public void setCardPicture() {
        this.cardPicture = getImage();
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

    public int getPokerValue(){
        return cardValue.getPokerValue();
    }


}
