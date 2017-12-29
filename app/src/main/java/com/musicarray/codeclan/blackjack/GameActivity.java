package com.musicarray.codeclan.blackjack;

import android.content.Intent;
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
    Button hitButton;
    Button holdButton;
    Deck deck;
    Hand playerHand;
    Hand computerHand;
    Player player;
    Computer computer;
    GameMaster gameMaster;

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
        gameMaster = new GameMaster(player, computer);
        deck.deal(playerHand);
        deck.deal(playerHand);
        deck.deal(computerHand);
        deck.deal(computerHand);
        playerName = findViewById(R.id.player_name);
        playerCards = findViewById(R.id.player_cards);
        playerScore = findViewById(R.id.player_score);
        computerCards = findViewById(R.id.computer_cards);
        playerName.setText(player.getName());
        playerCards.setText(player.getHand().viewCards());
        playerScore.setText(player.getHandValue().toString());
        computerCards.setText(computer.getHand().viewComputerCards());
        gameMaster.checkWinner();
        if(gameMaster.getWinState() == true){
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("gameMaster", gameMaster);
            startActivity(intent);
        }
    }

    public void onHitButtonClicked(View button){
            deck.deal(playerHand);
            computer.computerTakeCard(deck);
            playerCards.setText(player.getHand().viewCards());
            playerScore.setText(player.getHandValue().toString());
            computerCards.setText(computer.getHand().viewComputerCards());
            gameMaster.checkWinner();
                if(gameMaster.getWinState() == true){
                    Intent intent = new Intent(this, ResultActivity.class);
                    intent.putExtra("gameMaster", gameMaster);
                    startActivity(intent);
                }

    }

    public void onHoldButtonClicked(View button){
        player.setHoldStatus(true);
        computer.computerTakeCard(deck);
        computerCards.setText(computer.getHand().viewComputerCards());
        gameMaster.checkWinner();
        if(gameMaster.getWinState() == true){
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("gameMaster", gameMaster);
            startActivity(intent);
        }
    }


}
