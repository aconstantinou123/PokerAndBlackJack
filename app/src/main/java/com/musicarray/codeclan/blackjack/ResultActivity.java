package com.musicarray.codeclan.blackjack;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.renderscript.Type;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import static java.lang.Character.getType;

public class ResultActivity extends AppCompatActivity {

    TextView playerName;
    TextView computerName;
    TextView gameState;
    TextView playerScore;
    TextView computerScore;
    Button playAgainButton;
    Button returnToStartButton;
    GridView gridViewPlayer;
    GridView gridViewComputer;
    ImageAdapter playerImageAdaptor;
    ImageAdapter computerImageAdaptor;
    GameMaster gameMaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "PlayfairDisplay-Regular.otf");
        playerName = findViewById(R.id.result_player_name);
        playerName.setTypeface(typeface);
        computerName = findViewById(R.id.result_computer_name);
        computerName.setTypeface(typeface);
        gameState = findViewById(R.id.result_game_status_message);
        gameState.setTypeface(typeface);
        playAgainButton = findViewById(R.id.play_again_button);
        playAgainButton.setTypeface(typeface);
        returnToStartButton = findViewById(R.id.start_button);
        returnToStartButton.setTypeface(typeface);
        gridViewPlayer = findViewById(R.id.result_gridview_player);
        gridViewComputer = findViewById(R.id.result_gridview_computer);
        Intent intent = getIntent();
        gameMaster = (GameMaster) intent.getSerializableExtra("gameMaster");
        playerName.setText(gameMaster.getPlayer().getName()+ ": " + gameMaster.getPlayer().getHandValue().toString());
        computerName.setText("Computer: " + gameMaster.getComputer().computerHandValue().toString());
        playerImageAdaptor = new ImageAdapter(this, gameMaster.getPlayer().getHand());
        gridViewPlayer.setAdapter(playerImageAdaptor);
        computerImageAdaptor = new ImageAdapter(this, gameMaster.getComputer().getHand());
        gridViewComputer.setAdapter(computerImageAdaptor);
        gameState.setText(gameMaster.getGameStatus());
        playerScore = findViewById(R.id.player_score);
        playerScore.setTypeface(typeface);
        computerScore = findViewById(R.id.computer_score);
        computerScore.setTypeface(typeface);
        playerScore.setText("Player score: " + gameMaster.getScore().getPlayerScore().toString());
        computerScore.setText("Computer score: " + gameMaster.getScore().getComputerScore().toString());

    }

    public void onPlayAgainButtonClicked(View button){
        gameMaster.getPlayer().getHand().clearHand();
        gameMaster.getPlayer().setHoldStatus(false);
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("player", gameMaster.getPlayer());
        intent.putExtra("score", gameMaster.getScore());
        startActivity(intent);
    }

    public void onStartButtonClicked(View button){
        Intent intent = new Intent(this, BlackJackGameOverActivity.class);
        intent.putExtra("gameMaster", gameMaster);
        startActivity(intent);
    }
}
