package com.musicarray.codeclan.blackjack;

/**
 * Created by user on 12/28/17.
 */

public enum SuitType {
    HEARTS(0, "Hearts"),
    DIAMONDS(1, "Diamonds"),
    SPADES(2, "Spades"),
    CLUBS(3, "Clubs");

    final int value;
    final String type;

    SuitType(int value,String type) {
        this.value = value;
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public String getType() {
        return type;
    }
}
