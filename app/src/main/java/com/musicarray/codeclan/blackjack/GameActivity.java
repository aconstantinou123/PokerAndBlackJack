package com.musicarray.codeclan.blackjack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Deck deck = new Deck();
        deck.populateDeck();
        deck.shuffle();
        Hand playerHand = new Hand();
        Player player = new Player("Melvin Cornflake", playerHand);
        Hand computerHand = new Hand();
        Computer computer = new Computer(computerHand);
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
}
