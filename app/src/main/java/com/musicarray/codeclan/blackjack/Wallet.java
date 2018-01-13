package com.musicarray.codeclan.blackjack;

import java.io.Serializable;

/**
 * Created by user on 1/6/18.
 */

public class Wallet implements Serializable {

    private double money;

    public Wallet() {
        this.money = 0;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void addMoney(double money) {
        this.money += money;
    }

    public void removeMoney(double money) {
        this.money -= money;
    }

    public void clearMoney(){
        this.money = 0;
    }

    public boolean checkValidBet(double bet){
        if (bet <= getMoney() && bet != 0){
            return true;
        }
        return false;
    }

    public boolean notAllMoney(double bet, Hand hand){
        if (hand.getHandSize() < 4 && bet == getMoney()){
            return false;
        }
        return true;
    }

    public boolean checkWalletEmpty(){
        if (this.money <= 0){
            return true;
        }
        return false;
    }
}
