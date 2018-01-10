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
    Card card11;
    Card card12;
    Card card13;
    Card card14;
    Card card15;
    Card card16;
    Card card17;
    Card card18;
    Card card19;
    Card card20;
    Card card21;
    Card card22;
    Card card23;
    Card card24;
    Card card25;
    Card card26;
    Card card27;
    Card card28;
    Card card29;
    Card card30;



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
        card11 = new Card(CardValue.ACE, SuitType.SPADES);
        card12 = new Card(CardValue.TWO, SuitType.SPADES);
        card13 = new Card(CardValue.THREE, SuitType.SPADES);
        card14 = new Card(CardValue.FOUR, SuitType.SPADES);
        card15 = new Card(CardValue.FIVE, SuitType.SPADES);
        card16 = new Card(CardValue.ACE, SuitType.HEARTS);
        card17 = new Card(CardValue.TWO, SuitType.HEARTS);
        card18 = new Card(CardValue.THREE, SuitType.HEARTS);
        card19 = new Card(CardValue.FOUR, SuitType.HEARTS);
        card20 = new Card(CardValue.FIVE, SuitType.HEARTS);
        card21 = new Card(CardValue.ACE, SuitType.DIAMONDS);
        card22 = new Card(CardValue.TWO, SuitType.DIAMONDS);
        card23 = new Card(CardValue.THREE, SuitType.DIAMONDS);
        card24 = new Card(CardValue.FOUR, SuitType.DIAMONDS);
        card25 = new Card(CardValue.FIVE, SuitType.DIAMONDS);
        card26 = new Card(CardValue.ACE, SuitType.CLUBS);
        card27 = new Card(CardValue.TWO, SuitType.CLUBS);
        card28 = new Card(CardValue.THREE, SuitType.CLUBS);
        card29 = new Card(CardValue.FOUR, SuitType.CLUBS);
        card30 = new Card(CardValue.FIVE, SuitType.CLUBS);

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
        assertNotNull(computer.computerBet(10, deck));
    }

    @Test
    public void canCalculateHandStrength(){
        computer.getHand().addCards(card11);
        computer.getHand().addCards(card13);
        computer.getHand().addCards(card14);
        computer.getHand().addCards(card12);
        assertNotNull(computer.calculateHandStrength(deck));
    }
}
