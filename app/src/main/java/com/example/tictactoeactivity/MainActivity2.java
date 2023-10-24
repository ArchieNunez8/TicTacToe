package com.example.tictactoeactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Scanner;

public class MainActivity2 extends AppCompatActivity  {

    public static TextView txtName1, txtName2;
    public Button start;


    Scanner scanner = new Scanner(System.in);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txtName1 = findViewById(R.id.txtPlayer1Name);
        txtName2 = findViewById(R.id.txtPlayer2Name);
        start = (Button) findViewById(R.id.btnStart);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity_main();
            }
        });
    }
    public void openactivity_main(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}