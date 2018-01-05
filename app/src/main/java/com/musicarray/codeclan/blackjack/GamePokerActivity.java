package com.musicarray.codeclan.blackjack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GamePokerActivity extends AppCompatActivity {

    Button checkWinnerButton;
    TextView playerName;
    ImageView playerCard1;
    ImageView playerCard2;
    ImageView playerCard3;
    ImageView playerCard4;
    ImageView playerCard5;
    Deck deck;
    Player player;
    Hand computerHand;
    Computer computer;
    Score score;
    GameMaster gameMaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_poker);
        checkWinnerButton = findViewById(R.id.check_winner);
        playerName = findViewById(R.id.player_poker_name);
        playerCard1 = findViewById(R.id.player_card_1);
        playerCard2 = findViewById(R.id.player_card_2);
        playerCard3 = findViewById(R.id.player_card_3);
        playerCard4 = findViewById(R.id.player_card_4);
        playerCard5 = findViewById(R.id.player_card_5);
        Intent intent = getIntent();
        player = (Player) intent.getSerializableExtra("player");
        deck = new Deck();
        deck.populateDeck();
        deck.shuffle();
        computerHand = new Hand();
        computer = new Computer(computerHand);
        score = new Score();
        gameMaster = new GameMaster(player, computer, score);
        deck.deal(player.getHand());
        deck.deal(player.getHand());
        deck.deal(player.getHand());
        deck.deal(player.getHand());
        deck.deal(player.getHand());
        deck.deal(computer.getHand());
        deck.deal(computer.getHand());
        deck.deal(computer.getHand());
        deck.deal(computer.getHand());
        deck.deal(computer.getHand());
        playerName.setText(player.getName());
        playerCard1.setImageResource(getResources().getIdentifier(player.getHand().getCardsHeld().get(0).getCardPicture(), "drawable", getPackageName()));
        playerCard2.setImageResource(getResources().getIdentifier(player.getHand().getCardsHeld().get(1).getCardPicture(), "drawable", getPackageName()));
        playerCard3.setImageResource(getResources().getIdentifier(player.getHand().getCardsHeld().get(2).getCardPicture(), "drawable", getPackageName()));
        playerCard4.setImageResource(getResources().getIdentifier(player.getHand().getCardsHeld().get(3).getCardPicture(), "drawable", getPackageName()));
        playerCard5.setImageResource(getResources().getIdentifier(player.getHand().getCardsHeld().get(4).getCardPicture(), "drawable", getPackageName()));
    }
}
