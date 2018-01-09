package com.musicarray.codeclan.blackjack;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by user on 12/28/17.
 */

public class Computer implements Serializable {

    private Hand hand;
    private boolean holdStatus;
    private String computerStatus;
    private Wallet bank;
    private boolean winner;

    public Computer(Hand hand) {
            this.hand = hand;
            this.holdStatus = false;
            this.computerStatus = "";
            this.bank = new Wallet();
            this.winner = false;
        }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public Wallet getBank() {
        return bank;
    }

    public boolean getHoldStatus() {
        return holdStatus;
    }

    public void setHoldStatus(boolean holdStatus) {
        this.holdStatus = holdStatus;
    }

    public Hand getHand() {
            return hand;
        }

    public void setHand(Hand hand) {
            this.hand = hand;
        }

    public Integer computerHandValue(){
        return hand.getHandValue();
    }

    public String getComputerStatus() {
        return computerStatus;
    }

    public void setComputerStatus(String computerStatus) {
        this.computerStatus = computerStatus;
    }

    public void computerTakeCard(Deck deck) {
        if(computerHandValue() <= 16){
            deck.deal(this.hand);
            setComputerStatus("Computer takes a hit");
        }
        else{
            setHoldStatus(true);
            setComputerStatus("Computer holds");
        }
    }

    public double computerBet(double bet) {
        double computerBet;
        double min = 0.75;
        double max = 1.5;
        Random r = new Random();
        double randomBet = min + (max - min) * r.nextDouble();
        if (this.hand.checkHand() > 7) {
            computerBet = (bet * 5) * randomBet;
        } else if (this.hand.checkHand() > 3) {
            computerBet = (bet * 3) * randomBet;
        } else if (this.hand.checkHand() > 1) {
            computerBet = (bet * 2) / randomBet;
        } else {
            computerBet = bet * randomBet;
        }
        return 5*(Math.round(computerBet/5));
    }

    public double calculateHandStrength(Deck deck){
        double score = 0;
        int handSize = this.hand.getHandSize();
           for (int i = 0; i < 1000; i++){
               while (hand.getCardsHeld().size() <=4) {
                   deck.shuffle();
                   deck.deal(this.hand);
               }
               score += hand.checkHand();
                while (getHand().getHandSize() > handSize){
                   Card removedCard = hand.getCardsHeld().remove(getHand().getHandSize() - 1);
                   deck.addCard(removedCard);

                }
           }
        return score;
       }

}
