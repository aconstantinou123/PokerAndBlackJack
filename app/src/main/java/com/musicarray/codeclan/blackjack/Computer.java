package com.musicarray.codeclan.blackjack;

/**
 * Created by user on 12/28/17.
 */

public class Computer {

    private Hand hand;
    private boolean holdStatus;

    public Computer(Hand hand) {
            this.hand = hand;
            this.holdStatus = false;
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


    public void computerTakeCard(Deck deck) {
        if(computerHandValue() <= 16){
            deck.deal(this.hand);
        }
        else{
            setHoldStatus(true);
        }
    }
}
