package com.musicarray.codeclan.blackjack;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by user on 12/28/17.
 */

public class CardTest {

    Card card1;

    @Before
    public void before(){
        card1 = new Card(CardValue.ACE, SuitType.SPADES);
    }

    @Test
    public void canGetName(){
        assertEquals(CardValue.ACE, card1.getCardValue());
    }

    @Test
    public void canSetSuit(){
        card1.setSuitType(SuitType.CLUBS);
        assertEquals(SuitType.CLUBS, card1.getSuitType());
    }

    @Test
    public void canGetValueOfCard(){
        assertEquals(11, card1.getCardValue().value);
    }

    @Test
    public void canGetPrettyName(){
        assertEquals("The Ace of Spades", card1.prettyName());
    }
}
