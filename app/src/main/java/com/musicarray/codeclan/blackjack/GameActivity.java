package com.musicarray.codeclan.blackjack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    TextView playerName;
    TextView playerCards;
    TextView playerScore;
    TextView computerCards;
    TextView computerScore;
    Button hitButton;
    Button holdButton;
    Deck deck;
    Hand playerHand;
    Hand computerHand;
    Player player;
    Computer computer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        deck = new Deck();
        deck.populateDeck();
        deck.shuffle();
        playerHand = new Hand();
        player = new Player("Melvin Cornflake", playerHand);
        computerHand = new Hand();
        computer = new Computer(computerHand);
        deck.deal(playerHand);
        deck.deal(playerHand);
        deck.deal(computerHand);
        deck.deal(computerHand);
        playerName = findViewById(R.id.player_name);
        playerCards = findViewById(R.id.player_cards);
        playerScore = findViewById(R.id.player_score);
        computerCards = findViewById(R.id.computer_cards);
        computerScore = findViewById(R.id.computer_score);
        playerName.setText(player.getName());
        playerCards.setText(player.getHand().viewCards());
        playerScore.setText(player.getHandValue().toString());
        computerCards.setText(computer.getHand().viewComputerCards());
        computerScore.setText(computer.computerHandValue().toString());
    }

    public void onHitButtonClicked(View button){
            deck.deal(playerHand);
            computer.computerTakeCard(deck);
            playerCards.setText(player.getHand().viewCards());
            playerScore.setText(player.getHandValue().toString());
            computerCards.setText(computer.getHand().viewComputerCards());
            computerScore.setText(computer.computerHandValue().toString());
    }

    public void onHoldButtonClicked(View button){
        player.setHoldStatus(true);
        computer.computerTakeCard(deck);
        computerCards.setText(computer.getHand().viewComputerCards());
        computerScore.setText(computer.computerHandValue().toString());
    }


}
