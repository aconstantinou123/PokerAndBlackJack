package com.musicarray.codeclan.blackjack;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class PokerGameOverActivity extends AppCompatActivity {

    TextView gameOverText;
    Button returnToStartButton;
    Player player;
    Locale locale;
    NumberFormat currencyFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poker_game_over);
        locale = new Locale("en", "GB");
        currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "PlayfairDisplay-Regular.otf");
        gameOverText = findViewById(R.id.game_over_text);
        gameOverText.setTypeface(typeface);
        returnToStartButton = findViewById(R.id.return_to_start);
        returnToStartButton.setTypeface(typeface);
        Intent intent = getIntent();
        player = (Player) intent.getSerializableExtra("player");
        gameOverText.setText(player.checkWinnings());
    }

    public void onReturnToStartButtonClicked(View button){
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }
}
