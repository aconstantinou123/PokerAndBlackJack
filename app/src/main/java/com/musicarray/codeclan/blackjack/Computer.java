package com.musicarray.codeclan.blackjack;

import java.io.Serializable;
import java.util.Map;
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


    public double computerBet(double bet, Deck deck){
        double computerBet = 0;
        double min = 1.0;
        double max = 2.0;
        Random r = new Random();
        double handStrength = calculateHandStrength(deck);
        double randomBet = min + (max - min) * r.nextDouble();
        if (handStrength < 1250){
            computerBet = 0;
        }
        else if(handStrength < 1500){
            computerBet = bet;
        }
        else if(handStrength < 2250){
            computerBet = (bet * 1.2) * randomBet;
        }
        else if(handStrength < 3000){
            computerBet = (bet * 1.5) * randomBet;
        }
        else if(handStrength >= 3000){
            computerBet = (bet * 2) * randomBet;
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

       public boolean bluff(double handStrength){
        Random r = new Random();
        int chanceOfBluff = r.nextInt(10) + 1;
        if (handStrength < 1500){
            if (chanceOfBluff <= 2){
                return true;
                }
            }
        return false;
       }

}
