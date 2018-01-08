package com.musicarray.codeclan.blackjack;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GamePokerActivity extends AppCompatActivity {

    Button checkWinnerButton;
    Button foldButton;
    EditText moneyToBet;
    TextView playerName;
    TextView computerName;
    TextView playerWallet;
    TextView bettingPot;
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
        foldButton = findViewById(R.id.fold_button);
        playerName = findViewById(R.id.player_poker_name);
        computerName = findViewById(R.id.computer_poker_name);
        moneyToBet = findViewById(R.id.money_to_bet);
        bettingPot = findViewById(R.id.betting_pot);
        playerWallet = findViewById(R.id.player_wallet);
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
        computer.getBank().setMoney(10000);
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
        foldButton.setTypeface(typeface);
        moneyToBet.setTypeface(typeface);
        bettingPot.setText("Betting Pot: £" + gameMaster.getBettingPool().getMoney());
        bettingPot.setTypeface(typeface);
        playerWallet.setText("Player Wallet: £" + player.getWallet().getMoney());
        playerWallet.setTypeface(typeface);
        playerCard1.setImageResource(getResources().getIdentifier(player.getHand().getCardsHeld().get(0).getCardPicture(), "drawable", getPackageName()));
        playerCard2.setImageResource(getResources().getIdentifier(player.getHand().getCardsHeld().get(1).getCardPicture(), "drawable", getPackageName()));
        playerCard3.setImageResource(R.drawable.playingcardback);
        playerCard4.setImageResource(R.drawable.playingcardback);
        playerCard5.setImageResource(R.drawable.playingcardback);
    }

    public void onCheckWinnerButtonClicked(View button){
        if (currentCard < 5){
            try {
                String betString = moneyToBet.getText().toString();
                Double bet = Double.parseDouble(betString);
                player.getWallet().removeMoney(bet);
                computer.getBank().removeMoney(bet);
                gameMaster.getBettingPool().addMoney(bet * 2);
                playerCards.get(currentCard).setImageResource(getResources().getIdentifier(player.getHand().getCardsHeld().get(currentCard).getCardPicture(),"drawable",getPackageName()));
                currentCard += 1;
                playerWallet.setText("Player Wallet: £" + player.getWallet().getMoney());
                bettingPot.setText("Betting Pot: £" + gameMaster.getBettingPool().getMoney());
            }
            catch (NumberFormatException e){
                playerWallet.setText("Invalid Amount");
            }
        }
        else {
            gameMaster.checkWinnerPoker();
            Intent intent2 = new Intent(this,ResultPokerActivity.class);
            intent2.putExtra("gameMaster",gameMaster);
            startActivity(intent2);
        }
    }

    public void onFoldButtonClicked(View button){
        gameMaster.setGameStatus("Player Folds. Computer wins £" + gameMaster.getBettingPool().getMoney() + "0");
        double winnings =  gameMaster.getBettingPool().getMoney();
        computer.getBank().addMoney(winnings);
        gameMaster.getBettingPool().clearMoney();
        Intent intent2 = new Intent(this, ResultPokerActivity.class);
        intent2.putExtra("gameMaster", gameMaster);
        startActivity(intent2);

    }
}
