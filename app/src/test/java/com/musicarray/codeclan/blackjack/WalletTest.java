package com.musicarray.codeclan.blackjack;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by user on 1/6/18.
 */

public class WalletTest {

    Wallet wallet;

    @Before
    public void before(){
        wallet = new Wallet();
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
}
