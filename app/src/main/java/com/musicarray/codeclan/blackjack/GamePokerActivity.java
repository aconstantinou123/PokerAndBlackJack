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

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class GamePokerActivity extends AppCompatActivity {

    Button checkWinnerButton;
    Button foldButton;
    EditText moneyToBet;
    TextView warningMessage;
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
    boolean bluff;
    Locale locale;
    NumberFormat currencyFormatter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_poker);
        locale = new Locale("en", "GB");
        currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        currentCard = 2;
        checkWinnerButton = findViewById(R.id.check_winner);
        foldButton = findViewById(R.id.fold_button);
        playerName = findViewById(R.id.player_poker_name);
        computerName = findViewById(R.id.computer_poker_name);
        moneyToBet = findViewById(R.id.money_to_bet);
        bettingPot = findViewById(R.id.betting_pot);
        warningMessage = findViewById(R.id.warning_message);
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
        computer.getBank().setMoney(10000000);
        score = new Score();
        gameMaster = new GameMaster(player, computer, score);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "PlayfairDisplay-Regular.otf");
        deck.deal(player.getHand());
        deck.deal(player.getHand());
        deck.deal(computer.getHand());
        deck.deal(computer.getHand());
        double handStrength = computer.calculateHandStrength(deck);
        bluff = computer.bluff(handStrength);
        playerName.setText(player.getName());
        playerName.setTypeface(typeface);
        computerName.setTypeface(typeface);
        checkWinnerButton.setTypeface(typeface);
        foldButton.setTypeface(typeface);
        moneyToBet.setTypeface(typeface);
        bettingPot.setText("Betting Pot: \n" + currencyFormatter.format(gameMaster.getBettingPool().getMoney()));
        bettingPot.setTypeface(typeface);
        playerWallet.setText("Player Wallet: \n" + currencyFormatter.format(player.getWallet().getMoney()));
        playerWallet.setTypeface(typeface);
        warningMessage.setTypeface(typeface);
        playerCard1.setImageResource(getResources().getIdentifier(player.getHand().getCardsHeld().get(0).getCardPicture(), "drawable", getPackageName()));
        playerCard2.setImageResource(getResources().getIdentifier(player.getHand().getCardsHeld().get(1).getCardPicture(), "drawable", getPackageName()));
        playerCard3.setImageResource(R.drawable.playingcardback);
        playerCard4.setImageResource(R.drawable.playingcardback);
        playerCard5.setImageResource(R.drawable.playingcardback);
    }

    public void onCheckWinnerButtonClicked(View button){
        if (currentCard < 5){
            warningMessage.setText("");
            try {
                String betString = moneyToBet.getText().toString();
                Double bet = Double.parseDouble(betString);
                if (player.getWallet().checkValidBet(bet) == true) {
                    player.getWallet().removeMoney(bet);
                    double computerBet;
                    if (bluff == true){
                        computerBet = bet*2;
                    }
                    else {
                        computerBet = computer.computerBet(bet,deck);
                    }
                    computer.getBank().removeMoney(computerBet);
                    warningMessage.setText("Computer bets: " + currencyFormatter.format(computerBet));
                    gameMaster.getBettingPool().addMoney(bet);
                    gameMaster.getBettingPool().addMoney(computerBet);
                    if (computerBet == 0){
                        gameMaster.setGameStatus("Computer Folds. Player wins: " + currencyFormatter.format(gameMaster.getBettingPool().getMoney()));
                        double winnings =  gameMaster.getBettingPool().getMoney();
                        player.getWallet().addMoney(winnings);
                        gameMaster.getBettingPool().clearMoney();
                        Intent intent2 = new Intent(this, ResultPokerActivity.class);
                        intent2.putExtra("gameMaster", gameMaster);
                        startActivity(intent2);
                    }
                    deck.deal(computer.getHand());
                    deck.deal(player.getHand());
                    playerCards.get(currentCard).setImageResource(getResources().getIdentifier(player.getHand().getCardsHeld().get(currentCard).getCardPicture(),"drawable",getPackageName()));
                    currentCard += 1;
                    playerWallet.setText("Player Wallet: \n" + currencyFormatter.format(player.getWallet().getMoney()));
                    bettingPot.setText("Betting Pot: \n" + currencyFormatter.format(gameMaster.getBettingPool().getMoney()));
                    if (currentCard == 5){
                        checkWinnerButton.setText(R.string.check_winner);
                    }
                }
                else {
                   warningMessage.setText(R.string.warning_message);
                }
            }
            catch (NumberFormatException e){
                warningMessage.setText("Invalid Amount");
            }
        }
        else {
            gameMaster.checkWinnerPoker();
            double winnings = gameMaster.getBettingPool().getMoney();
                if(player.isWinner() == true){
                   player.getWallet().addMoney(winnings);
                }
                else if (computer.isWinner() == true){
                    computer.getBank().addMoney(winnings);
                }
                else {
                    player.getWallet().addMoney(winnings / 2);
                    computer.getBank().addMoney(winnings / 2);
                }
            gameMaster.getBettingPool().clearMoney();
            gameMaster.gameOver();
            Intent intent2 = new Intent(this,ResultPokerActivity.class);
            intent2.putExtra("gameMaster",gameMaster);
            startActivity(intent2);
        }
    }

    public void onFoldButtonClicked(View button){
        gameMaster.setGameStatus("Player Folds. Computer wins: " + currencyFormatter.format(gameMaster.getBettingPool().getMoney()));
        double winnings =  gameMaster.getBettingPool().getMoney();
        computer.getBank().addMoney(winnings);
        gameMaster.getBettingPool().clearMoney();
        Intent intent2 = new Intent(this, ResultPokerActivity.class);
        intent2.putExtra("gameMaster", gameMaster);
        startActivity(intent2);

    }
}
