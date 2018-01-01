package com.musicarray.codeclan.blackjack;

/**
 * Created by user on 12/28/17.
 */

public enum CardValue {
    ACE (1, "Ace"),
    TWO (2, "Two"),
    THREE(3, "Three"),
    FOUR(4, "Four"),
    FIVE(5, "Five"),
    SIX(6, "Six"),
    SEVEN(7, "Seven"),
    EIGHT(8, "Eight"),
    NINE(9, "Nine"),
    TEN(10, "Ten"),
    JACK(10, "Jack"),
    QUEEN(10, "Queen"),
    KING(10, "King");

    final int value;
    final String prettyName;

    CardValue(int value, String prettyName) {
        this.value = value;
        this.prettyName = prettyName;
    }

    public int getValue() {
        return value;
    }

    public String getPrettyName() {
        return prettyName;
    }
}
