package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button Addition, Subtraction, Multiplication;
    private Boolean add, sub, mult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Addition = findViewById(R.id.add);
        Subtraction = findViewById(R.id.subtract);
        Multiplication = findViewById(R.id.multiply);

        Addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add = true;
                Intent intent = new Intent(MainActivity.this, Game.class);
                intent.putExtra("add", add);
                startActivity(intent);
                finish();
            }
        });
        Subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sub = true;
                Intent intent = new Intent(MainActivity.this, Game.class);
                intent.putExtra("sub", sub);
                startActivity(intent);
                finish();
            }
        });
        Multiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mult = true;
                Intent intent = new Intent(MainActivity.this, Game.class);
                intent.putExtra("mult", mult);
                startActivity(intent);
                finish();
            }
        });
    }
}