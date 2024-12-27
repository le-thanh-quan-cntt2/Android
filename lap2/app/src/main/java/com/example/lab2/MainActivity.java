package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnOpenTask1, btnOpenTask2, btnOpenTask3,btnOpenTask4,btnOpenTask5,btnOpenTask6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnOpenTask1 = findViewById(R.id.btnOpenTask1);
        btnOpenTask2 = findViewById(R.id.btnOpenTask2);
        btnOpenTask3 = findViewById(R.id.btnOpenTask3);
        btnOpenTask4 = findViewById(R.id.btnOpenTask4);
        btnOpenTask5 = findViewById(R.id.btnOpenTask5);
        btnOpenTask6 = findViewById(R.id.btnOpenTask6);


        btnOpenTask1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Bai1Activity.class);
            startActivity(intent);
        });

        btnOpenTask2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Bai2Activity.class);
            startActivity(intent);
        });

        btnOpenTask3.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Bai3Activity.class);
            startActivity(intent);
        });

        btnOpenTask4.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Bai4Activity.class);
            startActivity(intent);
        });

        btnOpenTask5.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Bai5Activity.class);
            startActivity(intent);
        });
        btnOpenTask6.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Bai6Activity.class);
            startActivity(intent);
        });


    }
}