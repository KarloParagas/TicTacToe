package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //Fields
    private Button[] buttons;
    private TextView turn;
    private Player x;
    private Player o;
    public Player currentPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Connect and initialize the buttons array and turn TextView to activity_main.xml
        createButtons();
        turn = findViewById(R.id.playerTurn);
        
        //Start the game
        startGame();
    }

    private void createButtons() {
        //Create an array of buttons created from activity_main.xml
        buttons = new Button[]
        {
            findViewById(R.id.button1),
            findViewById(R.id.button2),
            findViewById(R.id.button3),
            findViewById(R.id.button4),
            findViewById(R.id.button5),
            findViewById(R.id.button6),
            findViewById(R.id.button7),
            findViewById(R.id.button8),
            findViewById(R.id.button9)
        };
    }

    private void startGame() {
        //Create 2 players
        x = new Player("X");
        o = new Player("O");

        //Set a current player
        currentPlayer = getPlayer();

        //Display that text in the "turn" field
        displayCurrentPlayer();
    }

    /**
     * This method is executed whenever the user clicks on a button/box
     */
    public void onClick(View v) {
        //Loop through the button array and determine which button the user has clicked
        for (int i = 0; i < buttons.length; i++) {
            if (v == buttons[i] && buttons[i].getText() == "") {
                buttons[i].setText(currentPlayer.playerName);
                changePlayer();
            }
        }
    }

    public void onClickNewGame(View v) {
        //Clear all text from the buttons
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setText("");
        }

        //Randomize a new player to start
        startGame();
    }

    private void changePlayer() {
        if (currentPlayer == x) {
            currentPlayer = o;
            displayCurrentPlayer();
        }
        else {
            currentPlayer = x;
            displayCurrentPlayer();
        }
    }

    private void displayCurrentPlayer() {
        turn.setText("Player " + currentPlayer.playerName + "'s Turn");
    }

    /**
     * This method is only executed when the application is started.
     * It will randomize which player is the first one to start.
     */
    private Player getPlayer() {
        Random rand = new Random();
        int num = rand.nextInt(2); //Generate a number from 0 - 1
        if (num == 0) {
            return x;
        }
        else {
            return o;
        }
    }
}
