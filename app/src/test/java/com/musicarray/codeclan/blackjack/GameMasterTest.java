package com.musicarray.codeclan.blackjack;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by user on 12/28/17.
 */

public class GameMasterTest {

    Player player;
    Computer computer;
    Deck deck;
    Hand playerHand;
    Hand computerHand;
    Card card1;
    Card card2;
    Card card3;
    Card card4;
    Card card5;
    Card card6;
    Score score;
    GameMaster gameMaster;

    @Before
    public void before(){
        deck = new Deck();
        playerHand = new Hand();
        computerHand = new Hand();
        player = new Player("Melvin Townsworth", playerHand);
        computer = new Computer(computerHand);
        card1 = new Card(CardValue.KING, SuitType.DIAMONDS);
        card2 = new Card(CardValue.KING, SuitType.SPADES);
        card3 = new Card(CardValue.QUEEN, SuitType.SPADES);
        card4 = new Card(CardValue.FIVE, SuitType.SPADES);
        card5 = new Card(CardValue.JACK, SuitType.SPADES);
        card6 = new Card(CardValue.ACE, SuitType.SPADES);
        score = new Score();
        gameMaster = new GameMaster(player, computer, score);

        deck.populateDeck();
    }

    @Test
    public void playerCanGet21(){
        playerHand.addCards(card1);
        playerHand.addCards(card2);
        playerHand.addCards(card6);
        player.setHoldStatus(true);
        computer.setHoldStatus(true);
        gameMaster.checkWinner();
        assertEquals("You Win!", gameMaster.getGameStatus());
        assertEquals(1, score.getPlayerScore(), 0.1);
    }

    @Test
    public void computerCanGet21(){
        computerHand.addCards(card1);
        computerHand.addCards(card2);
        computerHand.addCards(card6);
        player.setHoldStatus(true);
        computer.setHoldStatus(true);
        gameMaster.checkWinner();
        assertEquals("Computer Wins!", gameMaster.getGameStatus());
        assertEquals(1, score.getComputerScore(), 0.1);
    }

    @Test
    public void everybodyWins(){
        computerHand.addCards(card1);
        computerHand.addCards(card2);
        computerHand.addCards(card6);
        playerHand.addCards(card1);
        playerHand.addCards(card2);
        playerHand.addCards(card6);
        player.setHoldStatus(true);
        computer.setHoldStatus(true);
        gameMaster.checkWinner();
        assertEquals("Everybody Wins!", gameMaster.getGameStatus());
        assertEquals(1, score.getComputerScore(), 0.1);
        assertEquals(1, score.getPlayerScore(), 0.1);
    }

    @Test
    public void playerCanLose(){
        playerHand.addCards(card1);
        playerHand.addCards(card2);
        playerHand.addCards(card3);
        gameMaster.checkWinner();
        assertEquals("You Lose. Hand over 21", gameMaster.getGameStatus());
        assertEquals(0, score.getPlayerScore(), 0.1);
    }

    @Test
    public void computerCanLose(){
        computerHand.addCards(card1);
        computerHand.addCards(card2);
        computerHand.addCards(card3);
        gameMaster.checkWinner();
        assertEquals("You Win! The computers hand is over 21", gameMaster.getGameStatus());
        assertEquals(1, score.getPlayerScore(), 0.1);
    }

    @Test
    public void bothCanLose(){
        computerHand.addCards(card1);
        computerHand.addCards(card2);
        computerHand.addCards(card3);
        playerHand.addCards(card1);
        playerHand.addCards(card2);
        playerHand.addCards(card3);
        gameMaster.checkWinner();
        assertEquals("Everybody Loses! Both hands over 21", gameMaster.getGameStatus());
    }

    @Test
    public void computerHasHigherHand(){
        computerHand.addCards(card5);
        computerHand.addCards(card2);
        playerHand.addCards(card3);
        computer.setHoldStatus(true);
        player.setHoldStatus(true);
        gameMaster.checkWinner();
        assertEquals("Computer wins with the highest hand", gameMaster.getGameStatus());
    }

    @Test
    public void playerHasHigherHand(){
        playerHand.addCards(card5);
        playerHand.addCards(card2);
        computerHand.addCards(card3);
        computer.setHoldStatus(true);
        player.setHoldStatus(true);
        gameMaster.checkWinner();
        assertEquals("Player wins with the highest hand", gameMaster.getGameStatus());
    }

    @Test
    public void canDraw(){
        playerHand.addCards(card5);
        playerHand.addCards(card2);
        computerHand.addCards(card5);
        computerHand.addCards(card2);
        computer.setHoldStatus(true);
        player.setHoldStatus(true);
        gameMaster.checkWinner();
        assertEquals("Its a draw! Both hands are equal", gameMaster.getGameStatus());
    }

}
