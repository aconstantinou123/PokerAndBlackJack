package com.musicarray.codeclan.blackjack;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PlayerNameActivity extends AppCompatActivity {

    EditText playerName;
    TextView welcomeText;
    TextView nameText;
    Button continueButton;
    Button playPokerButton;
    Player player;
    Hand hand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_name);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "PlayfairDisplay-Regular.otf");
        nameText = findViewById(R.id.name_text);
        nameText.setTypeface(typeface);
        welcomeText = findViewById(R.id.welcome_text);
        welcomeText.setTypeface(typeface);
        playerName = findViewById(R.id.player_name);
        playerName.setTypeface(typeface);
        continueButton = findViewById(R.id.continue_button2);
        continueButton.setTypeface(typeface);
        playPokerButton = findViewById(R.id.play_poker_button);
        playPokerButton.setTypeface(typeface);

    }

    public void OnContinueButtonClicked(View button){
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        String newPlayerName = playerName.getText().toString();
        hand = new Hand();
        player = new Player(newPlayerName, hand);
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("player", player);
        startActivity(intent);
    }

    public void OnPlayPokerButtonClicked(View button){
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        String newPlayerName = playerName.getText().toString();
        hand = new Hand();
        player = new Player(newPlayerName, hand);
        Intent intent = new Intent(this, GamePokerActivity.class);
        intent.putExtra("player", player);
        startActivity(intent);
    }
}
