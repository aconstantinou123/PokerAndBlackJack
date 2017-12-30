package com.musicarray.codeclan.blackjack;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class WelcomeActivity extends AppCompatActivity {

    Button playButton;
    ImageView testImage;
    Card testCard;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        playButton = findViewById(R.id.playButton);
        testImage = (ImageView) findViewById(R.id.cardTest);
        testCard = new Card(CardValue.ACE, SuitType.SPADES);
        testCard.setCardPicture();
        int drawableResourceId = getResources().getIdentifier(testCard.getCardPicture(), "drawable", getPackageName());
        testImage.setImageResource(drawableResourceId);
    }

    public void onPlayButtonClicked(View button){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}
