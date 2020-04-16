package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Fields
    private Button[][] buttons;
    private TextView textMessage;
    private Player x;
    private Player o;
    public Player currentPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Connect and initialize the buttons array and turn TextView to activity_main.xml
        createButtons();
        textMessage = findViewById(R.id.playerTurn);
        
        //Start the game
        startGame();
    }

    private void createButtons() {
        //Create a two dimensional array of buttons created from activity_main.xml
        buttons = new Button[][]
        {
            {findViewById(R.id.button1), findViewById(R.id.button2), findViewById(R.id.button3)},
            {findViewById(R.id.button4), findViewById(R.id.button5), findViewById(R.id.button6)},
            {findViewById(R.id.button7), findViewById(R.id.button8), findViewById(R.id.button9)}
        };
    }

    private void startGame() {
        //Create 2 players
        x = new Player("X");
        o = new Player("O");

        //Set a current player
        currentPlayer = x;

        //Display that text in the "turn" field
        displayCurrentPlayer();
    }

    /**
     * This method is executed whenever the user clicks on a button/box
     */
    public void onClick(View v) {
        //Loop through the button array and determine which button the user has clicked
        for (int rows = 0; rows < buttons.length; rows++) {
            for (int cols = 0; cols < buttons[0].length; cols++) {
                if (v == buttons[rows][cols] && buttons[rows][cols].getText() == "") {
                    buttons[rows][cols].setText(currentPlayer.playerName);
                }
            }
        }

        //Check the game's current state
        if (checkWinner("X")) {
            textDisplay("Player X Won!");
            gameOver();
        }
        else if (checkWinner("O")) {
            textDisplay("Player O Won!");
            gameOver();
        }
        else if (tieGame()) {
            textDisplay("Tie Game!");
            gameOver();
        }
        else {
            changePlayer();
        }
    }

    private boolean tieGame() {
        //Loop through each buttons and see if it's all full
        for (int rows = 0; rows < buttons.length; rows++) {
            for (int cols = 0; cols < buttons[0].length; cols++) {
                if (buttons[rows][cols].getText() == "") {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkWinner(String player) {
        //Horizontal combination
        if (buttons[0][0].getText() == player && buttons[0][1].getText() == player && buttons[0][2].getText() == player ||
            buttons[1][0].getText() == player && buttons[1][1].getText() == player && buttons[1][2].getText() == player ||
            buttons[2][0].getText() == player && buttons[2][1].getText() == player && buttons[2][2].getText() == player) {
            return true;
        }
        //Vertical combination
        else if (buttons[0][0].getText() == player && buttons[1][0].getText() == player && buttons[2][0].getText() == player ||
                 buttons[0][1].getText() == player && buttons[1][1].getText() == player && buttons[2][1].getText() == player ||
                 buttons[0][2].getText() == player && buttons[1][2].getText() == player && buttons[2][2].getText() == player) {
            return true;
        }
        //Diagonal combination
        else if (buttons[0][0].getText() == player && buttons[1][1].getText() == player && buttons[2][2].getText() == player ||
                 buttons[0][2].getText() == player && buttons[1][1].getText() == player && buttons[2][0].getText() == player) {
            return true;
        }
        return false;
    }

    private void gameOver() {
        //Disable every button
        for (int rows = 0; rows < buttons.length; rows++) {
            for (int cols = 0; cols < buttons[0].length; cols++) {
                buttons[rows][cols].setEnabled(false);
            }
        }
    }

    public void onClickNewGame(View v) {
        //Clear all text from the buttons
        for (int rows = 0; rows < buttons.length; rows++) {
            for (int cols = 0; cols < buttons[0].length; cols++) {
                buttons[rows][cols].setText("");
                buttons[rows][cols].setEnabled(true);
            }
        }
        //Start the game
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
        textDisplay("Player " + currentPlayer.playerName + "'s Turn");
    }

    private void textDisplay(String s) {
        textMessage.setText(s);
    }
}
