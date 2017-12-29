package com.musicarray.codeclan.blackjack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView playerName;
    TextView playerCards;
    TextView playerScore;
    TextView computerCards;
    TextView computerScore;
    TextView gameState;
    Button playAgainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        playerName = findViewById(R.id.result_player_name);
        playerCards = findViewById(R.id.result_player_cards);
        playerScore = findViewById(R.id.result_player_score);
        computerCards = findViewById(R.id.result_computer_cards);
        computerScore = findViewById(R.id.result_computer_score);
        gameState = findViewById(R.id.result_game_status_message);
        playAgainButton = findViewById(R.id.play_again_button);
        Intent intent = getIntent();
        GameMaster gameMaster = (GameMaster) intent.getSerializableExtra("gameMaster");
        playerName.setText(gameMaster.getPlayer().getName());
        playerCards.setText(gameMaster.getPlayer().getHand().viewCards());
        playerScore.setText(gameMaster.getPlayer().getHandValue().toString());
        computerCards.setText(gameMaster.getComputer().getHand().viewCards());
        computerScore.setText(gameMaster.getComputer().computerHandValue().toString());
        gameState.setText(gameMaster.getGameStatus());

    }

    public void onPlayAgainButtonClicked(View button){
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }
}
