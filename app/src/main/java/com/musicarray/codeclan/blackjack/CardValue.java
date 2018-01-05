package com.musicarray.codeclan.blackjack;

/**
 * Created by user on 12/28/17.
 */

public enum CardValue {
    ACE (1, "Ace", 14),
    TWO (2, "Two", 2),
    THREE(3, "Three", 3),
    FOUR(4, "Four", 4),
    FIVE(5, "Five", 5),
    SIX(6, "Six", 6),
    SEVEN(7, "Seven", 7),
    EIGHT(8, "Eight", 8),
    NINE(9, "Nine", 9),
    TEN(10, "Ten", 10),
    JACK(10, "Jack", 11),
    QUEEN(10, "Queen", 12),
    KING(10, "King", 13);

    final int value;
    final String prettyName;
    final int pokerValue;

    CardValue(int value, String prettyName, int pokerValue) {
        this.value = value;
        this.prettyName = prettyName;
        this.pokerValue = pokerValue;
    }

    public int getValue() {
        return value;
    }

    public String getPrettyName() {
        return prettyName;
    }

    public int getPokerValue() {
        return pokerValue;
    }
}
