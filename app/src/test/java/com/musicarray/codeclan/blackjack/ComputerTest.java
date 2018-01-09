package com.musicarray.codeclan.blackjack;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by user on 12/28/17.
 */

public class ComputerTest {

    Computer computer;
    Hand hand;
    Deck deck;
    Card card1;
    Card card2;
    Card card3;
    Card card4;
    Card card5;
    Card card6;
    Card card7;
    Card card8;
    Card card9;
    Card card10;



    @Before
    public void before(){
        deck = new Deck();
        deck.populateDeck();
        hand = new Hand();
        computer = new Computer(hand);
        card1 = new Card(CardValue.KING, SuitType.DIAMONDS);
        card2 = new Card(CardValue.KING, SuitType.SPADES);
        card3 = new Card(CardValue.KING, SuitType.SPADES);
        card4 = new Card(CardValue.KING, SuitType.SPADES);
        card5 = new Card(CardValue.JACK, SuitType.SPADES);
        card6 = new Card(CardValue.ACE, SuitType.SPADES);
        card7 = new Card(CardValue.ACE, SuitType.HEARTS);
        card8 = new Card(CardValue.TWO, SuitType.DIAMONDS);
        card9 = new Card(CardValue.QUEEN, SuitType.SPADES);
        card10 = new Card(CardValue.TEN, SuitType.SPADES);

    }

    @Test
    public void canGetComputerHandValue(){
        deck.deal(hand);
        assertEquals(11, computer.computerHandValue(), 0.1);
    }
//
    @Test
    public void willTakeCardsIfUnder16(){
        deck.deal(hand);
        computer.computerTakeCard(deck);
        assertEquals(13, computer.computerHandValue(),0.1);
        computer.computerTakeCard(deck);
        assertEquals(16, computer.computerHandValue(), 0.1);
        computer.computerTakeCard(deck);
        assertEquals(20, computer.computerHandValue(), 0.1);
        computer.computerTakeCard(deck);
        assertEquals(20, computer.computerHandValue(), 0.1);
        assertEquals(true, computer.getHoldStatus());

    }

    @Test
    public void computerCanBet(){
        computer.getHand().addCards(card1);
        computer.getHand().addCards(card2);
        computer.getHand().addCards(card3);
        computer.getHand().addCards(card4);
        assertNotNull(computer.computerBet(10));
    }

    @Test
    public void canCalculateHandStrength(){
        computer.getHand().addCards(card1);
        computer.getHand().addCards(card2);
        computer.getHand().addCards(card3);
        computer.getHand().addCards(card4);
        assertNotNull(computer.calculateHandStrength(deck));
    }
}
