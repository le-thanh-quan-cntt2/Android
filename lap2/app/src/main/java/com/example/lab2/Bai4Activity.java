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

public class Bai4Activity extends AppCompatActivity {

    EditText etFullName;
    RadioGroup rgProgram;
    RadioButton rbCollege, rbUniversity;
    CheckBox cbC, cbJava, cbJavaScript;
    Button btnSave;
    TextView tvResult; // Thêm TextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai4);

        // Liên kết các view
        etFullName = findViewById(R.id.etFullName);
        rgProgram = findViewById(R.id.rgProgram);
        rbCollege = findViewById(R.id.rbCollege);
        rbUniversity = findViewById(R.id.rbUniversity);
        cbC = findViewById(R.id.cbC);
        cbJava = findViewById(R.id.cbJava);
        cbJavaScript = findViewById(R.id.cbJavaScript);
        btnSave = findViewById(R.id.btnSave);
        tvResult = findViewById(R.id.tvResult); // Liên kết TextView

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu
                String fullName = etFullName.getText().toString().trim();
                String program = "";
                StringBuilder languages = new StringBuilder();

                // Xác định hệ học
                int selectedId = rgProgram.getCheckedRadioButtonId();
                if (selectedId == rbCollege.getId()) {
                    program = "Cao đẳng";
                } else if (selectedId == rbUniversity.getId()) {
                    program = "Đại học";
                }

                // Kiểm tra ngôn ngữ yêu thích
                if (cbC.isChecked()) {
                    languages.append("Lập trình C ");
                }
                if (cbJava.isChecked()) {
                    languages.append("Java ");
                }
                if (cbJavaScript.isChecked()) {
                    languages.append("JavaScript ");
                }

                // Hiển thị thông tin trong TextView
                String message = "Họ tên: " + fullName +
                        "\nHệ học: " + program +
                        "\nNgôn ngữ yêu thích: " + (languages.length() > 0 ? languages : "Không có");

                tvResult.setText(message);
                tvResult.setVisibility(View.VISIBLE); // Hiển thị TextView
            }
        });
    }
}
