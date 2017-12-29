package com.musicarray.codeclan.blackjack;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by user on 12/28/17.
 */

public class ComputerTest {

    Computer computer;
    Hand hand;
    Deck deck;


    @Before
    public void before(){
        deck = new Deck();
        deck.populateDeck();
        hand = new Hand();
        computer = new Computer(hand);
    }

    @Test
    public void canGetComputerHandValue(){
        deck.deal(hand);
        assertEquals(11, computer.computerHandValue());
    }

    @Test
    public void willTakeCardsIfUnder16(){
        deck.deal(hand);
        computer.computerTakeCard(deck);
        assertEquals(13, computer.computerHandValue());
        computer.computerTakeCard(deck);
        assertEquals(16, computer.computerHandValue());
        computer.computerTakeCard(deck);
        assertEquals(20, computer.computerHandValue());
        computer.computerTakeCard(deck);
        assertEquals(20, computer.computerHandValue());

    }
}
