package com.musicarray.codeclan.blackjack;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GamePokerActivity extends AppCompatActivity {

    Button checkWinnerButton;
    TextView playerName;
    TextView computerName;
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
    int currentCard;
    ArrayList<ImageView> playerCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_poker);
        currentCard = 2;
        checkWinnerButton = findViewById(R.id.check_winner);
        playerName = findViewById(R.id.player_poker_name);
        computerName = findViewById(R.id.computer_poker_name);
        playerCard1 = findViewById(R.id.player_card_1);
        playerCard2 = findViewById(R.id.player_card_2);
        playerCard3 = findViewById(R.id.player_card_3);
        playerCard4 = findViewById(R.id.player_card_4);
        playerCard5 = findViewById(R.id.player_card_5);
        playerCards = new ArrayList<>();
        playerCards.add(playerCard1);
        playerCards.add(playerCard2);
        playerCards.add(playerCard3);
        playerCards.add(playerCard4);
        playerCards.add(playerCard5);
        Intent intent = getIntent();
        player = (Player) intent.getSerializableExtra("player");
        deck = new Deck();
        deck.populateDeck();
        deck.shuffle();
        computerHand = new Hand();
        computer = new Computer(computerHand);
        score = new Score();
        gameMaster = new GameMaster(player, computer, score);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "PlayfairDisplay-Regular.otf");
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
        playerName.setTypeface(typeface);
        computerName.setTypeface(typeface);
        checkWinnerButton.setTypeface(typeface);
        playerCard1.setImageResource(getResources().getIdentifier(player.getHand().getCardsHeld().get(0).getCardPicture(), "drawable", getPackageName()));
        playerCard2.setImageResource(getResources().getIdentifier(player.getHand().getCardsHeld().get(1).getCardPicture(), "drawable", getPackageName()));
        playerCard3.setImageResource(R.drawable.playingcardback);
        playerCard4.setImageResource(R.drawable.playingcardback);
        playerCard5.setImageResource(R.drawable.playingcardback);
    }

    public void onCheckWinnerButtonClicked(View button){
        if (currentCard < 5){
            playerCards.get(currentCard).setImageResource(getResources().getIdentifier(player.getHand().getCardsHeld().get(currentCard).getCardPicture(), "drawable", getPackageName()));
            currentCard += 1;
        }
        else {
            gameMaster.checkWinnerPoker();
            Intent intent2 = new Intent(this,ResultPokerActivity.class);
            intent2.putExtra("gameMaster",gameMaster);
            startActivity(intent2);
        }
    }
}
