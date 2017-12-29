package com.musicarray.codeclan.blackjack;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by user on 12/28/17.
 */

public class HandTest {

    Hand hand;
    Deck deck1;

    @Before
    public void before(){
        hand = new Hand();
        deck1 = new Deck();
        deck1.populateDeck();
    }

    @Test
    public void canGetHandSize(){
        assertEquals(0, hand.getHandSize());
    }

    @Test
    public void canGetTopCard(){
        deck1.deal(hand);
        assertEquals("The Ace of Hearts", hand.getCardsHeld().get(0).prettyName());
    }

    @Test
    public void canGetHandValue(){
        deck1.deal(hand);
        deck1.deal(hand);
        assertEquals(13, hand.getHandValue());
    }


}
