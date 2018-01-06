package com.musicarray.codeclan.blackjack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultPokerActivity extends AppCompatActivity {

    ImageView computerCard1;
    ImageView computerCard2;
    ImageView computerCard3;
    ImageView computerCard4;
    ImageView computerCard5;
    ImageView playerCard1;
    ImageView playerCard2;
    ImageView playerCard3;
    ImageView playerCard4;
    ImageView playerCard5;
    TextView playerName;
    TextView pokerResult;
    GameMaster gameMaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_poker);
        computerCard1 = findViewById(R.id.computer_card_1);
        computerCard2 = findViewById(R.id.computer_card_2);
        computerCard3 = findViewById(R.id.computer_card_3);
        computerCard4 = findViewById(R.id.computer_card_4);
        computerCard5 = findViewById(R.id.computer_card_5);
        playerCard1 = findViewById(R.id.player_card_1_result);
        playerCard2 = findViewById(R.id.player_card_2_result);
        playerCard3 = findViewById(R.id.player_card_3_result);
        playerCard4 = findViewById(R.id.player_card_4_result);
        playerCard5 = findViewById(R.id.player_card_5_result);
        playerName = findViewById(R.id.player_poker_name_result);
        pokerResult = findViewById(R.id.poker_result_screen);
        Intent intent = getIntent();
        gameMaster = (GameMaster) intent.getSerializableExtra("gameMaster");
        computerCard1.setImageResource(getResources().getIdentifier(gameMaster.getComputer().getHand().getCardsHeld().get(0).getCardPicture(), "drawable", getPackageName()));
        computerCard2.setImageResource(getResources().getIdentifier(gameMaster.getComputer().getHand().getCardsHeld().get(1).getCardPicture(), "drawable", getPackageName()));
        computerCard3.setImageResource(getResources().getIdentifier(gameMaster.getComputer().getHand().getCardsHeld().get(2).getCardPicture(), "drawable", getPackageName()));
        computerCard4.setImageResource(getResources().getIdentifier(gameMaster.getComputer().getHand().getCardsHeld().get(3).getCardPicture(), "drawable", getPackageName()));
        computerCard5.setImageResource(getResources().getIdentifier(gameMaster.getComputer().getHand().getCardsHeld().get(4).getCardPicture(), "drawable", getPackageName()));
        playerCard1.setImageResource(getResources().getIdentifier(gameMaster.getPlayer().getHand().getCardsHeld().get(0).getCardPicture(), "drawable", getPackageName()));
        playerCard2.setImageResource(getResources().getIdentifier(gameMaster.getPlayer().getHand().getCardsHeld().get(1).getCardPicture(), "drawable", getPackageName()));
        playerCard3.setImageResource(getResources().getIdentifier(gameMaster.getPlayer().getHand().getCardsHeld().get(2).getCardPicture(), "drawable", getPackageName()));
        playerCard4.setImageResource(getResources().getIdentifier(gameMaster.getPlayer().getHand().getCardsHeld().get(3).getCardPicture(), "drawable", getPackageName()));
        playerCard5.setImageResource(getResources().getIdentifier(gameMaster.getPlayer().getHand().getCardsHeld().get(4).getCardPicture(), "drawable", getPackageName()));
        playerName.setText(gameMaster.getPlayer().getName());
        pokerResult.setText(gameMaster.getGameStatus());



    }
}