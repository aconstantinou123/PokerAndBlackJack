package com.musicarray.codeclan.blackjack;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by user on 1/6/18.
 */

public class WalletTest {

    Wallet wallet;
    Hand hand;
    Card card1;
    Card card2;
    Card card3;
    Card card4;
    Card card5;


    @Before
    public void before(){
        wallet = new Wallet();
        hand = new Hand();
        card1 = new Card(CardValue.KING, SuitType.DIAMONDS);
        card2 = new Card(CardValue.KING, SuitType.SPADES);
        card3 = new Card(CardValue.QUEEN, SuitType.SPADES);
        card4 = new Card(CardValue.FIVE, SuitType.SPADES);
        card5 = new Card(CardValue.JACK, SuitType.SPADES);
    }

    @Test
    public void canGetWallet(){
        assertEquals(0.0, wallet.getMoney());
    }

    @Test
    public void canSetMoney(){
        wallet.setMoney(200);
        assertEquals(200.0, wallet.getMoney());
    }

    @Test
    public void canAddMoney(){
        wallet.setMoney(200);
        wallet.addMoney(200);
        assertEquals(400.0, wallet.getMoney());
    }

    @Test
    public void canRemoveMoney(){
        wallet.setMoney(300);
        wallet.removeMoney(200);
        assertEquals(100.0, wallet.getMoney());
    }

    @Test
    public void walletIsEmpty(){
        wallet.setMoney(0);
        assertEquals(true, wallet.checkWalletEmpty());
    }

    @Test
    public void validBet_True(){
        wallet.setMoney(200);
        double bet = 100;
        assertEquals(true, wallet.checkValidBet(bet));
    }

    @Test
    public void validBet_False(){
        wallet.setMoney(200);
        double bet = 300;
        assertEquals(false, wallet.checkValidBet(bet));
    }

    @Test
    public void cantBetAllMoneyBeforeLastRound_False(){
        hand.addCards(card1);
        hand.addCards(card2);
        hand.addCards(card3);
        wallet.setMoney(200);
        assertEquals(false, wallet.notAllMoney(200, hand));
    }

    @Test
    public void cantBetAllMoneyBeforeLastRound_True(){
        hand.addCards(card1);
        hand.addCards(card2);
        hand.addCards(card3);
        hand.addCards(card4);
        wallet.setMoney(200);
        assertEquals(true, wallet.notAllMoney(200, hand));
    }
}
