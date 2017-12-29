package com.musicarray.codeclan.blackjack;

/**
 * Created by user on 12/28/17.
 */

public class Computer {

    private Hand hand;

    public Computer(Hand hand) {
            this.hand = hand;
        }

    public Hand getHand() {
            return hand;
        }

    public void setHand(Hand hand) {
            this.hand = hand;
        }

    public int computerHandValue(){
        return hand.getHandValue();
    }


    public void computerTakeCard(Deck deck) {
        if(computerHandValue() <= 16){
            deck.deal(this.hand);
        }
    }
}
