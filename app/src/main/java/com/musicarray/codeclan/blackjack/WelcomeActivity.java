package com.musicarray.codeclan.blackjack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        playButton = findViewById(R.id.playButton);
    }

    public void onPlayButtonClicked(View button){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}
