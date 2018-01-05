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
        card7 = new Card(CardValue.ACE, SuitType.HEARTS);
        card8 = new Card(CardValue.TWO, SuitType.DIAMONDS);
        card9 = new Card(CardValue.THREE, SuitType.DIAMONDS);
        card10 = new Card(CardValue.FOUR, SuitType.DIAMONDS);
        card11 = new Card(CardValue.FIVE, SuitType.DIAMONDS);
        card12 = new Card(CardValue.SIX, SuitType.DIAMONDS);
        card13 = new Card(CardValue.TEN, SuitType.DIAMONDS);
        card14 = new Card(CardValue.JACK, SuitType.DIAMONDS);
        card15 = new Card(CardValue.QUEEN, SuitType.DIAMONDS);
        card16 = new Card(CardValue.KING, SuitType.DIAMONDS);
        card17 = new Card(CardValue.ACE, SuitType.DIAMONDS);
        card18 = new Card(CardValue.SIX, SuitType.CLUBS);
        card19 = new Card(CardValue.EIGHT, SuitType.HEARTS);
        card20 = new Card(CardValue.NINE, SuitType.DIAMONDS);
        card21 = new Card(CardValue.THREE, SuitType.HEARTS);
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

    @Test
    public void computerCanWinWithBetterHand(){
        playerHand.addCards(card4);
        playerHand.addCards(card5);
        playerHand.addCards(card6);
        playerHand.addCards(card7);
        playerHand.addCards(card8);
        computerHand.addCards(card8);
        computerHand.addCards(card9);
        computerHand.addCards(card10);
        computerHand.addCards(card11);
        computerHand.addCards(card12);
        gameMaster.checkWinnerPoker();
        assertEquals("Computer wins with a Straight Flush", gameMaster.getGameStatus());
    }

    @Test
    public void playerCanWinWithBetterHand(){
        computerHand.addCards(card4);
        computerHand.addCards(card5);
        computerHand.addCards(card6);
        computerHand.addCards(card7);
        computerHand.addCards(card8);
        playerHand.addCards(card13);
        playerHand.addCards(card14);
        playerHand.addCards(card15);
        playerHand.addCards(card16);
        playerHand.addCards(card17);
        gameMaster.checkWinnerPoker();
        assertEquals("Player wins with a Royal Flush", gameMaster.getGameStatus());
    }

    @Test
    public void computerCanWinWithHighestCard(){
        playerHand.addCards(card1);
        playerHand.addCards(card2);
        playerHand.addCards(card3);
        playerHand.addCards(card4);
        playerHand.addCards(card5);
        computerHand.addCards(card6);
        computerHand.addCards(card7);
        computerHand.addCards(card8);
        computerHand.addCards(card9);
        computerHand.addCards(card10);
        gameMaster.checkWinnerPoker();
        assertEquals("Computer wins with the highest card", gameMaster.getGameStatus());
    }

    @Test
    public void playerCanWithWithHighestCard(){
        computerHand.addCards(card16);
        computerHand.addCards(card18);
        computerHand.addCards(card19);
        computerHand.addCards(card20);
        computerHand.addCards(card21);
        playerHand.addCards(card1);
        playerHand.addCards(card3);
        playerHand.addCards(card4);
        playerHand.addCards(card5);
        playerHand.addCards(card6);
        gameMaster.checkWinnerPoker();
        assertEquals("Player wins with the highest card", gameMaster.getGameStatus());
    }

    @Test
    public void playerAndComputerCanDraw(){
        playerHand.addCards(card1);
        playerHand.addCards(card2);
        playerHand.addCards(card3);
        playerHand.addCards(card4);
        playerHand.addCards(card5);
        computerHand.addCards(card1);
        computerHand.addCards(card2);
        computerHand.addCards(card3);
        computerHand.addCards(card4);
        computerHand.addCards(card5);
        gameMaster.checkWinnerPoker();
        assertEquals("It's a draw", gameMaster.getGameStatus());

    }

}
