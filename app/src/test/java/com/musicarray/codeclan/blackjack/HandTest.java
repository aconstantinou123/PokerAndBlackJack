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
    Card card;
    Card card2;
    Card card3;

    @Before
    public void before(){
        hand = new Hand();
        deck1 = new Deck();
        deck1.populateDeck();
        card = new Card(CardValue.JACK, SuitType.SPADES);
        card2 = new Card(CardValue.ACE, SuitType.CLUBS);
        card3 = new Card(CardValue.QUEEN, SuitType.CLUBS);

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

    @Test
    public void canSeeHand(){
        deck1.deal(hand);
        deck1.deal(hand);
        assertEquals("The Ace of Hearts\n" +
                "The Two of Hearts\n", hand.viewCards());
    }

    @Test
    public void canHideCards(){
        deck1.deal(hand);
        deck1.deal(hand);
        deck1.deal(hand);
        assertEquals("Secret\n" +
                "The Two of Hearts\n" +
                "The Three of Hearts\n", hand.viewComputerCards());
    }

    @Test
    public void canCheckForAce_True(){
        deck1.deal(hand);
        deck1.deal(hand);
        deck1.deal(hand);
        hand.addCards(card2);
        assertEquals(true, hand.checkAces());
    }

    @Test
    public void canCheckForAce_False(){
        hand.addCards(card);
        hand.addCards(card3);
        assertEquals(false, hand.checkAces());
    }

    @Test
    public void canGetAceToReturnAs11(){
        hand.addCards(card);
        hand.addCards(card2);
        assertEquals(21, hand.getHandValue());
    }

    @Test
    public void canGetAceToReturnAs1(){
        hand.addCards(card);
        hand.addCards(card2);
        hand.addCards(card3);
        assertEquals(21, hand.getHandValue());
    }



}
