package com.musicarray.codeclan.blackjack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    TextView playerName;
    Button hitButton;
    Button holdButton;
    GridView gridViewPlayer;
    GridView gridViewComputer;
    Deck deck;
    Hand playerHand;
    Hand computerHand;
    Player player;
    Computer computer;
    GameMaster gameMaster;
    ImageAdapter playerImageAdapter;
    ImageAdapter computerImageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gridViewPlayer = findViewById(R.id.gridview_player);
        gridViewComputer = findViewById(R.id.gridview_computer);
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
        playerImageAdapter = new ImageAdapter(this, playerHand);
        gridViewPlayer.setAdapter(playerImageAdapter);
        computerImageAdapter = new ImageAdapter(this, computerHand);
        gridViewComputer.setAdapter(computerImageAdapter);
        playerName = findViewById(R.id.player_name);
        playerName.setText(player.getName());
        gameMaster.checkWinner();
        if(gameMaster.getWinState() == true){
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("gameMaster", gameMaster);
            startActivity(intent);
        }
    }

    public void onHitButtonClicked(View button){
            deck.deal(playerHand);
            gridViewPlayer.setAdapter(playerImageAdapter);
            computer.computerTakeCard(deck);
            gridViewComputer.setAdapter(computerImageAdapter);
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
        gridViewComputer.setAdapter(computerImageAdapter);
        gameMaster.checkWinner();
        if(gameMaster.getWinState() == true){
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("gameMaster", gameMaster);
            startActivity(intent);
        }
    }


}
