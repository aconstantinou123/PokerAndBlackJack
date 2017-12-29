package com.musicarray.codeclan.blackjack;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNotSame;

/**
 * Created by user on 12/28/17.
 */

public class DeckTest {

    Deck deck1;
    Deck deck2;

    @Before
    public void before(){
        deck1 = new Deck();
        deck2 = new Deck();
    }

    @Test
    public void canPopulateDeck(){
        deck1.populateDeck();
        assertNotNull(deck1);
    }

    @Test
    public void canShuffleDeck(){
        deck1.populateDeck();
        deck1.shuffle();
        deck2.populateDeck();
        assertNotSame(deck2.getCards().get(0), deck1.getCards().get(0));
    }
}
