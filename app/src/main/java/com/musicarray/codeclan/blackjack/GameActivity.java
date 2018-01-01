package com.musicarray.codeclan.blackjack;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import com.google.gson.Gson;

public class GameActivity extends AppCompatActivity {

    TextView playerName;
    TextView computerName;
    TextView computerStatus;
    Button hitButton;
    Button holdButton;
    GridView gridViewPlayer;
    GridView gridViewComputer;
    Deck deck;
    Player player;
    Hand computerHand;
    Computer computer;
    GameMaster gameMaster;
    ImageAdapter playerImageAdapter;
    ComputerImageAdaptor computerImageAdapter;
    Score score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gridViewPlayer = findViewById(R.id.gridview_player);
        gridViewComputer = findViewById(R.id.result_gridview_computer);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "PlayfairDisplay-Regular.otf");
        hitButton = findViewById(R.id.hit_button);
        hitButton.setTypeface(typeface);
        holdButton = findViewById(R.id.hold_button);
        holdButton.setTypeface(typeface);
        deck = new Deck();
        deck.populateDeck();
        deck.shuffle();
        Intent intent = getIntent();
        player = (Player) intent.getSerializableExtra("player");
        computerHand = new Hand();
        computer = new Computer(computerHand);
        score = new Score();
        if (intent.getSerializableExtra("score") != null){
            score = (Score) intent.getSerializableExtra("score");
        }
        gameMaster = new GameMaster(player, computer, score);
        deck.deal(player.getHand());
        deck.deal(player.getHand());
        deck.deal(computerHand);
        deck.deal(computerHand);
        playerImageAdapter = new ImageAdapter(this, player.getHand());
        gridViewPlayer.setAdapter(playerImageAdapter);
        computerImageAdapter = new ComputerImageAdaptor(this, computerHand);
        gridViewComputer.setAdapter(computerImageAdapter);
        playerName = findViewById(R.id.player_name);
        playerName.setText(player.getName());
        playerName.setTypeface(typeface);
        computerName = findViewById(R.id.computer_name);
        computerName.setTypeface(typeface);
        computerStatus = findViewById(R.id.computer_status);
        computerStatus.setTypeface(typeface);
        computerStatus.setText(computer.getComputerStatus());
    }

    public void onHitButtonClicked(View button){
            deck.deal(player.getHand());
            gridViewPlayer.setAdapter(playerImageAdapter);
            computer.computerTakeCard(deck);
            gridViewComputer.setAdapter(computerImageAdapter);
            computerStatus.setText(computer.getComputerStatus());
            gameMaster.checkWinner();
            player.setHoldStatus(false);
                if(gameMaster.getWinState() == true){
                    Intent intent2 = new Intent(this, ResultActivity.class);
                    intent2.putExtra("gameMaster", gameMaster);
                    startActivity(intent2);
                }

    }

    public void onHoldButtonClicked(View button){
        player.setHoldStatus(true);
        computer.computerTakeCard(deck);
        gridViewComputer.setAdapter(computerImageAdapter);
        computerStatus.setText(computer.getComputerStatus());
        gameMaster.checkWinner();
        if(gameMaster.getWinState() == true){
            Intent intent2 = new Intent(this, ResultActivity.class);
            intent2.putExtra("gameMaster", gameMaster);
            startActivity(intent2);
        }
    }


}
