package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Bai1Activity extends AppCompatActivity {

    private EditText etCelsius, etFahrenheit;
    private Button btnConvertToCelsius, btnConvertToFahrenheit, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai1);

        // Ánh xạ view
        etCelsius = findViewById(R.id.etCelsius);
        etFahrenheit = findViewById(R.id.etFahrenheit);
        btnConvertToCelsius = findViewById(R.id.btnConvertToCelsius);
        btnConvertToFahrenheit = findViewById(R.id.btnConvertToFahrenheit);
        btnClear = findViewById(R.id.btnClear);


        btnConvertToCelsius.setOnClickListener(v -> {
            try {
                String fahrenheitValue = etFahrenheit.getText().toString();
                if (!fahrenheitValue.isEmpty()) {
                    double fahrenheit = Double.parseDouble(fahrenheitValue);
                    double celsius = (fahrenheit - 32) * 5 / 9;
                    etCelsius.setText(String.format("%.2f", celsius));
                } else {
                    Toast.makeText(this, "Please enter °F value", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
            }
        });


        btnConvertToFahrenheit.setOnClickListener(v -> {
            try {
                String celsiusValue = etCelsius.getText().toString();
                if (!celsiusValue.isEmpty()) {
                    double celsius = Double.parseDouble(celsiusValue);
                    double fahrenheit = (celsius * 9 / 5) + 32;
                    etFahrenheit.setText(String.format("%.2f", fahrenheit));
                } else {
                    Toast.makeText(this, "Please enter °C value", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
            }
        });


        btnClear.setOnClickListener(v -> {
            etCelsius.setText("");
            etFahrenheit.setText("");
        });
    }
}
