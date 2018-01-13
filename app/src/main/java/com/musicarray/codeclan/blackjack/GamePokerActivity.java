package com.musicarray.codeclan.blackjack;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class GamePokerActivity extends AppCompatActivity {

    Button checkWinnerButton;
    Button foldButton;
    EditText moneyToBet;
    TextView computerBet;
    TextView playerName;
    TextView computerName;
    TextView playerWallet;
    TextView bettingPot;
    ImageView playerCard1;
    ImageView playerCard2;
    ImageView playerCard3;
    ImageView playerCard4;
    ImageView playerCard5;
    ImageView computerCard1;
    ImageView computerCard2;
    ImageView computerCard3;
    ImageView computerCard4;
    ImageView computerCard5;
    Deck deck;
    Player player;
    Hand computerHand;
    Computer computer;
    Score score;
    GameMaster gameMaster;
    int currentCard;
    ArrayList<ImageView> playerCards;
    ArrayList<ImageView> computerCards;
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
        computerBet = findViewById(R.id.computer_bet);
        playerWallet = findViewById(R.id.player_wallet);
        playerCard1 = findViewById(R.id.player_card_1);
        playerCard2 = findViewById(R.id.player_card_2);
        playerCard3 = findViewById(R.id.player_card_3);
        playerCard4 = findViewById(R.id.player_card_4);
        playerCard5 = findViewById(R.id.player_card_5);
        computerCard1 = findViewById(R.id.computer_card_1);
        computerCard2 = findViewById(R.id.computer_card_2);
        computerCard3 = findViewById(R.id.computer_card_3);
        computerCard4 = findViewById(R.id.computer_card_4);
        computerCard5 = findViewById(R.id.computer_card_5);
        playerCards = new ArrayList<>();
        playerCards.add(playerCard1);
        playerCards.add(playerCard2);
        playerCards.add(playerCard3);
        playerCards.add(playerCard4);
        playerCards.add(playerCard5);
        computerCards = new ArrayList<>();
        computerCards.add(computerCard1);
        computerCards.add(computerCard2);
        computerCards.add(computerCard3);
        computerCards.add(computerCard4);
        computerCards.add(computerCard5);
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
        computerBet.setTypeface(typeface);
        playerCard1.setImageResource(getResources().getIdentifier(player.getHand().getCardsHeld().get(0).getCardPicture(), "drawable", getPackageName()));
        playerCard2.setImageResource(getResources().getIdentifier(player.getHand().getCardsHeld().get(1).getCardPicture(), "drawable", getPackageName()));
        computerCard1.setImageResource(getResources().getIdentifier("playingcardback", "drawable", getPackageName()));
        computerCard2.setImageResource(getResources().getIdentifier("playingcardback", "drawable", getPackageName()));
    }

    public void onCheckWinnerButtonClicked(View button){
        if (player.getWallet().getMoney() == 0 && currentCard < 5){
            computer.setWinner(true);
            player.setWinner(false);
            gameMaster.gameOver();
            Intent intent2 = new Intent(this,ResultPokerActivity.class);
            intent2.putExtra("gameMaster",gameMaster);
            startActivity(intent2);
        }
        else if (currentCard < 5){
            computerBet.setText("");
            try {
                String betString = moneyToBet.getText().toString();
                final Double bet = Double.parseDouble(betString);
                if (player.getWallet().checkValidBet(bet) == true) {
                    player.getWallet().removeMoney(bet);
                    final double computerBet;
                    if (bluff == true){
                        computerBet = bet*2;
                    }
                    else {
                        computerBet = computer.computerBet(bet,deck);
                    }
                        if (computerBet > bet) {
                            Typeface typeface = Typeface.createFromAsset(getAssets(), "PlayfairDisplay-Regular.otf");
                            final AlertDialog.Builder builder = new AlertDialog.Builder(GamePokerActivity.this);
                            View view = getLayoutInflater().inflate(R.layout.raise_dialog_box, null);
                            TextView computerRaise = view.findViewById(R.id.computer_raise);
                            computerRaise.setTypeface(typeface);
                            final TextView computerRaiseMessage = view.findViewById(R.id.computer_raises_message);
                            computerRaiseMessage.setTypeface(typeface);
                            computerRaiseMessage.setText("Computer raises by " + currencyFormatter.format(computerBet - bet));
                            Button raiseButton = view.findViewById(R.id.raise_alert_button);
                            raiseButton.setTypeface(typeface);
                            Button foldButtonAlert = view.findViewById(R.id.fold_alert_button);
                            foldButtonAlert.setTypeface(typeface);
                            builder.setView(view);
                            final AlertDialog dialog = builder.create();
                            dialog.show();
                            raiseButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    double increaseBet = computerBet - bet;
                                     if (player.getWallet().checkValidBet(increaseBet) == false){
                                         computerRaiseMessage.setText("Not enough money. Player must fold");
                                     }
                                     else {
                                         player.getWallet().removeMoney(increaseBet);
                                         gameMaster.getBettingPool().addMoney(increaseBet);
                                         playerWallet.setText("Player Wallet: \n" + currencyFormatter.format(player.getWallet().getMoney()));
                                         bettingPot.setText("Betting Pot: \n" + currencyFormatter.format(gameMaster.getBettingPool().getMoney()));
                                         dialog.dismiss();
                                     }
                                }
                            });
                            foldButtonAlert.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    gameMaster.setGameStatus("Player Folds. Computer wins: " + currencyFormatter.format(gameMaster.getBettingPool().getMoney()));
                                    double winnings = gameMaster.getBettingPool().getMoney();
                                    computer.getBank().addMoney(winnings);
                                    gameMaster.getBettingPool().clearMoney();
                                    Intent intent2 = new Intent(GamePokerActivity.this,ResultPokerActivity.class);
                                    intent2.putExtra("gameMaster",gameMaster);
                                    startActivity(intent2);
                                }
                            });
                        }
                    computer.getBank().removeMoney(computerBet);
                    this.computerBet.setText("Computer bets: " + currencyFormatter.format(computerBet));
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
                    computerCards.get(currentCard).setImageResource(getResources().getIdentifier("playingcardback", "drawable", getPackageName()));
                    currentCard += 1;
                    playerWallet.setText("Player Wallet: \n" + currencyFormatter.format(player.getWallet().getMoney()));
                    bettingPot.setText("Betting Pot: \n" + currencyFormatter.format(gameMaster.getBettingPool().getMoney()));
                    if (currentCard == 5){
                        checkWinnerButton.setText(R.string.check_winner);
                    }
                }
                else {
                    Toast.makeText(this, "Not enough money - min £10", Toast.LENGTH_LONG).show();
                }
            }
            catch (NumberFormatException e){
                Toast.makeText(this, "Invalid Amount", Toast.LENGTH_LONG).show();
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
