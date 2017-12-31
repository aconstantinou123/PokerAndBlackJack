package com.musicarray.codeclan.blackjack;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView playerName;
    TextView computerName;
    TextView gameState;
    Button playAgainButton;
    GridView gridViewPlayer;
    GridView gridViewComputer;
    ImageAdapter playerImageAdaptor;
    ImageAdapter computerImageAdaptor;

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
        gridViewPlayer = findViewById(R.id.result_gridview_player);
        gridViewComputer = findViewById(R.id.result_gridview_computer);
        Intent intent = getIntent();
        GameMaster gameMaster = (GameMaster) intent.getSerializableExtra("gameMaster");
        playerName.setText(gameMaster.getPlayer().getName()+ ": " + gameMaster.getPlayer().getHandValue().toString());
        computerName.setText("Computer: " + gameMaster.getComputer().computerHandValue().toString());
        playerImageAdaptor = new ImageAdapter(this, gameMaster.getPlayer().getHand());
        gridViewPlayer.setAdapter(playerImageAdaptor);
        computerImageAdaptor = new ImageAdapter(this, gameMaster.getComputer().getHand());
        gridViewComputer.setAdapter(computerImageAdaptor);
        gameState.setText(gameMaster.getGameStatus());

    }

    public void onPlayAgainButtonClicked(View button){
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }
}
