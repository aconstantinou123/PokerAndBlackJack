package com.musicarray.codeclan.blackjack;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by user on 12/28/17.
 */

public class PlayerTest {

    Player player;
    Hand hand;
    Deck deck;


    @Before
    public void before(){
        deck = new Deck();
        deck.populateDeck();
        hand = new Hand();
        player = new Player("Alex", hand);
    }

    @Test
    public void canGetName(){
        assertEquals("Alex", player.getName());
    }

    @Test
    public void canSeePlayerHand(){
        deck.deal(hand);
        assertEquals("The Ace of Hearts\n", player.getHand().viewCards());
    }

//    @Test
//    public void cangetPlayerHandValue(){
//        deck.deal(hand);
//        assertEquals(11, player.getHandValue());
//    }
}
