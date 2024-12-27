package com.example.lab2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Bai6Activity extends AppCompatActivity {

    private EditText etName, etMsv, etAge;
    private RadioGroup radioGroupGender;
    private CheckBox cbFootball, cbGaming;
    private Button btnSave;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai6);


        etName = findViewById(R.id.etName);
        etMsv = findViewById(R.id.etMsv);
        etAge = findViewById(R.id.etAge);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        cbFootball = findViewById(R.id.cbFootball);
        cbGaming = findViewById(R.id.cbGaming);
        btnSave = findViewById(R.id.btnSave);
        tvResult = findViewById(R.id.tvResult);


        btnSave.setOnClickListener(v -> saveData());
    }

    private void saveData() {

        String name = etName.getText().toString();
        String msv = etMsv.getText().toString();
        String age = etAge.getText().toString();


        if (name.isEmpty() || msv.isEmpty() || age.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }


        int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
        RadioButton selectedGender = findViewById(selectedGenderId);
        String gender = selectedGender.getText().toString();


        StringBuilder hobbies = new StringBuilder();
        if (cbFootball.isChecked()) {
            hobbies.append("Đá bóng ");
        }
        if (cbGaming.isChecked()) {
            hobbies.append("Chơi game ");
        }


        if (hobbies.length() == 0) {
            hobbies.append("Không có sở thích");
        }


        String result = "Tên: " + name + "\n" +
                "MSV: " + msv + "\n" +
                "Tuổi: " + age + "\n" +
                "Giới tính: " + gender + "\n" +
                "Sở thích: " + hobbies.toString();


        tvResult.setText(result);
        tvResult.setVisibility(View.VISIBLE);
    }
}
