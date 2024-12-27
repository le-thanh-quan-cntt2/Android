package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Bai3Activity extends AppCompatActivity {

    private EditText etNumberA, etNumberB;
    private Button btnAdd, btnSubtract, btnMultiply, btnDivide;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai3);


        etNumberA = findViewById(R.id.etNumberA);
        etNumberB = findViewById(R.id.etNumberB);
        btnAdd = findViewById(R.id.btnAdd);
        btnSubtract = findViewById(R.id.btnSubtract);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDivide = findViewById(R.id.btnDivide);
        tvResult = findViewById(R.id.tvResult);


        btnAdd.setOnClickListener(v -> calculate('+'));


        btnSubtract.setOnClickListener(v -> calculate('-'));


        btnMultiply.setOnClickListener(v -> calculate('*'));


        btnDivide.setOnClickListener(v -> calculate('/'));
    }

    private void calculate(char operator) {
        try {
            String numberAStr = etNumberA.getText().toString();
            String numberBStr = etNumberB.getText().toString();

            if (numberAStr.isEmpty() || numberBStr.isEmpty()) {
                Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
                return;
            }

            double numberA = Double.parseDouble(numberAStr);
            double numberB = Double.parseDouble(numberBStr);

            double result = 0;
            String operation = "";

            switch (operator) {
                case '+':
                    result = numberA + numberB;
                    operation = String.format("a + b = %.2f", result);
                    break;
                case '-':
                    result = numberA - numberB;
                    operation = String.format("a - b = %.2f", result);
                    break;
                case '*':
                    result = numberA * numberB;
                    operation = String.format("a * b = %.2f", result);
                    break;
                case '/':
                    if (numberB == 0) {
                        Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    result = numberA / numberB;
                    operation = String.format("a / b = %.2f", result);
                    break;
            }

            tvResult.setText(operation);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
        }
    }


}
