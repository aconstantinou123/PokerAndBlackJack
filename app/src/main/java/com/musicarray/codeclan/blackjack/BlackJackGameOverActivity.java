package com.musicarray.codeclan.blackjack;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BlackJackGameOverActivity extends AppCompatActivity {

    Button startAgainButton;
    TextView blackJackGameOver;
    GameMaster gameMaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_jack_game_over);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "PlayfairDisplay-Regular.otf");
        Intent intent = getIntent();
        gameMaster = (GameMaster) intent.getSerializableExtra("gameMaster");
        startAgainButton = findViewById(R.id.back_to_welcome_screen);
        startAgainButton.setTypeface(typeface);
        blackJackGameOver = findViewById(R.id.blackjack_gameover_text);
        blackJackGameOver.setTypeface(typeface);
        blackJackGameOver.setText(gameMaster.blackJackResult());
    }

    public void onStartAgainButtonClicked(View button){
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }
}
