package com.musicarray.codeclan.blackjack;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by user on 12/28/17.
 */

public class Player implements Serializable {

    private String name;
    private Hand hand;
    private boolean holdStatus;
    private Wallet wallet;
    private boolean winner;

    public Player(String name,Hand hand) {
        this.name = name;
        this.hand = hand;
        this.holdStatus = false;
        this.wallet = new Wallet();
        this.winner = false;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet){
        this.wallet = wallet;
    }

    public boolean getHoldStatus() {
        return holdStatus;
    }

    public void setHoldStatus(boolean holdStatus) {
        this.holdStatus = holdStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Integer getHandValue(){
        return hand.getHandValue();
    }

    public String checkWinnings(){
        String result = new String();
        Locale locale = new Locale("en", "GB");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        if (getWallet().getMoney() == 200){
            result = "You finished with " + currencyFormatter.format(getWallet().getMoney()) + ". You broke even";
        }
        else if(getWallet().getMoney() > 200){
            result = "Well done. You won " + currencyFormatter.format(getWallet().getMoney() - 200);
        }
        else if (getWallet().getMoney() < 200){
            result = "Bad Luck you lost " + currencyFormatter.format(200 - getWallet().getMoney());
        }
        return result;
    }
}
