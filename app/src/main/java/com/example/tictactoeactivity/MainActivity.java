package com.example.tictactoeactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //in this part I created a variable for all the widgets that I,ve used on my application.
    public TextView playerOneScore, playerTwoScore, playerStatus, playerOneName, playertwoName;
    public Button playAgain, reset, newPlayer ;
    public Button [] buttons = new Button[9];
    //in this part I created a variable for all the widgets that I,ve used on my application


    // in this part I've created a new variables that will help me on the conditions in my app.
    boolean playerOneActive;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8},{0,3,6}, {1,4,7},{2,5,8},{0,4,8}, {2,4,6}};
    int rounds;
    private int txtPlayerOneScoreCount, txtPlayerTwoScoreCount;
    // in this part I've created a new variables that will help me on the conditions in my app.


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //assigning the IDs in xml file, on the variables that I've created for widget.
        playerOneScore = findViewById(R.id.txtPlayerOneScore);
        playerTwoScore = findViewById(R.id.txtPlayerTwoScore);
        playerStatus = findViewById(R.id.txtStatus);
        reset = findViewById(R.id.btnReset);
        playAgain = findViewById(R.id.btnPlayAgain);
        newPlayer = findViewById(R.id.btnNewPlayer);
        playerOneName = findViewById(R.id.txtPlayerOne);
        playertwoName = findViewById(R.id.txtPlayerTwo);

        buttons[0] = findViewById(R.id.btn0);
        buttons[1] = findViewById(R.id.btn1);
        buttons[2] = findViewById(R.id.btn2);
        buttons[3] = findViewById(R.id.btn3);
        buttons[4] = findViewById(R.id.btn4);
        buttons[5] = findViewById(R.id.btn5);
        buttons[6] = findViewById(R.id.btn6);
        buttons[7] = findViewById(R.id.btn7);
        buttons[8] = findViewById(R.id.btn8);
        //assigning the IDs in xml file, on the variables that I've created for widget.

        playerOneName.setText(MainActivity2.txtName1.getText().toString() + "'s Score ");
        playertwoName.setText(MainActivity2.txtName2.getText().toString() + "'s Score ");

        for (int i=0; i<buttons.length; i++)
        {
            buttons[i].setOnClickListener(this);
        }

        txtPlayerOneScoreCount = 0;
        txtPlayerTwoScoreCount = 0;
        playerOneActive = true;
        rounds = 0;

    }

    @Override
    public void onClick(View view)
    {
        if (!((Button)view).getText().toString().equals(""))
        {
            return;
        } else if (checkWinner())
        {
            return;
        }
        String buttonID = view.getResources().getResourceEntryName(view.getId());
        int gameStatePointer = Integer.parseInt(buttonID.substring(buttonID.length()-1,buttonID.length()));

        if (playerOneActive)
        {
            ((Button)view).setText("X");
            ((Button)view).setTextColor(Color.parseColor("#006600"));
            gameState[gameStatePointer] = 0;
        }
        else
        {
            ((Button)view).setText("O");
            ((Button)view).setTextColor(Color.parseColor("#cc0000"));
            gameState[gameStatePointer] = 1;
        }
        rounds++;

        if (checkWinner())
        {
            if (playerOneActive)
            {
                txtPlayerOneScoreCount++;
                updatePlayerScore();
                playerStatus.setText(MainActivity2.txtName1.getText().toString() + " has WON");
            }
            else
            {
                txtPlayerTwoScoreCount++;
                updatePlayerScore();
                playerStatus.setText(MainActivity2.txtName2.getText().toString() + " has WON");
            }
        }
        else if (rounds == 9)
        {
            playerStatus.setText("No winner");
        }
        else
        {
            playerOneActive = !playerOneActive;
        }

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
                txtPlayerOneScoreCount = 0;
                txtPlayerTwoScoreCount = 0;
                updatePlayerScore();
            }

        });

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
            }
        });

        newPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity_main2();
            }
        });
    }



    public boolean checkWinner()
    {
        boolean winnerResult = false;
        for (int[] winningPositions: winningPositions)
        {
            if(gameState[winningPositions[0]]==gameState[winningPositions[1]] &&
                    gameState[winningPositions[1]]==gameState[winningPositions[2]] &&
                    gameState[winningPositions[0]] !=2 )
            {
                winnerResult = true;
            }
        }
        return winnerResult;
    }

    public void playAgain()
    {
        rounds = 0;
        playerOneActive = true;
        for (int i=0; i<buttons.length; i++)
        {
            gameState[i] = 2;
            buttons[i].setText("");
        }
        playerStatus.setText("Status");
    }
    public void updatePlayerScore()
    {
        playerOneScore.setText(Integer.toString(txtPlayerOneScoreCount));
        playerTwoScore.setText(Integer.toString(txtPlayerTwoScoreCount));
    }
    public void openactivity_main2(){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

}