package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Bai2Activity extends AppCompatActivity {

    private EditText etName, etWeight, etHeight;
    private Button btnCalculateBMI;
    private TextView tvBMIResult, tvDiagnosis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);


        etName = findViewById(R.id.etName);
        etWeight = findViewById(R.id.etWeight);
        etHeight = findViewById(R.id.etHeight);
        btnCalculateBMI = findViewById(R.id.btnCalculateBMI);
        tvBMIResult = findViewById(R.id.tvBMIResult);
        tvDiagnosis = findViewById(R.id.tvDiagnosis);


        btnCalculateBMI.setOnClickListener(v -> {
            try {

                String name = etName.getText().toString();
                String weightStr = etWeight.getText().toString();
                String heightStr = etHeight.getText().toString();


                if (name.isEmpty() || weightStr.isEmpty() || heightStr.isEmpty()) {
                    Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                double weight = Double.parseDouble(weightStr);
                double height = Double.parseDouble(heightStr);

                if (height <= 0 || weight <= 0) {
                    Toast.makeText(this, "Chiều cao và cân nặng phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                    return;
                }


                double bmi = weight / (height * height);
                tvBMIResult.setText(String.format("BMI: %.2f", bmi));


                String diagnosis;
                if (bmi < 18) {
                    diagnosis = "Người gầy";
                } else if (bmi >= 18 && bmi <= 24.9) {
                    diagnosis = "NGười bình thường";
                } else if (bmi >= 25 && bmi <= 29.9) {
                    diagnosis = "Người béo phì cấp độ I";
                } else if (bmi >= 30 && bmi <= 34.9) {
                    diagnosis = "Người béo phì cấp độ II)";
                } else {
                    diagnosis = "Người béo phì cấp độ  III";
                }

                tvDiagnosis.setText(String.format("Diagnosis: %s", diagnosis));
            } catch (Exception e) {
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
