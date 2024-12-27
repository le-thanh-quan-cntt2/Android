package com.example.a2210900057_ltq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ThemSuaActivity extends AppCompatActivity {

    private EditText edtTenSP57, edtSoLuong57, edtDonGia57;
    private Button btnLuu57, btnThoatSP57;
    private String trangThai;
    private SanPham sanPham;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sua);


        edtTenSP57 = findViewById(R.id.edtTenSP57);
        edtSoLuong57 = findViewById(R.id.edtSoLuong57);
        edtDonGia57 = findViewById(R.id.edtDonGia57);
        btnLuu57 = findViewById(R.id.btnLuu57);
        btnThoatSP57 = findViewById(R.id.btnThoatSP57);


        Intent intent = getIntent();
        trangThai = intent.getStringExtra("TRANGTHAI");

        if ("SUA".equals(trangThai)) {
            sanPham = (SanPham) intent.getSerializableExtra("SanPham");
            if (sanPham != null) {

                edtTenSP57.setText(sanPham.getTenSP());
                edtSoLuong57.setText(String.valueOf(sanPham.getSoLuong()));
                edtDonGia57.setText(String.valueOf(sanPham.getDonGia()));
            }
        }


        btnLuu57.setOnClickListener(v -> {
            String tenSP = edtTenSP57.getText().toString().trim();
            String soLuongStr = edtSoLuong57.getText().toString().trim();
            String donGiaStr = edtDonGia57.getText().toString().trim();

            if (tenSP.isEmpty() || soLuongStr.isEmpty() || donGiaStr.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            int soLuong;
            float donGia;
            try {
                soLuong = Integer.parseInt(soLuongStr);
                donGia = Float.parseFloat(donGiaStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Số lượng và đơn giá phải là số hợp lệ", Toast.LENGTH_SHORT).show();
                return;
            }

            if ("THEM".equals(trangThai)) {

                SanPham newSanPham = new SanPham(0, tenSP, soLuong, donGia);
                Intent resultIntent = new Intent();
                resultIntent.putExtra("SanPham", newSanPham);
                resultIntent.putExtra("TRANGTHAI", "THEM");
                setResult(RESULT_OK, resultIntent);
                finish();
            } else if ("SUA".equals(trangThai)) {

                sanPham.setTenSP(tenSP);
                sanPham.setSoLuong(soLuong);
                sanPham.setDonGia(donGia);
                Intent resultIntent = new Intent();
                resultIntent.putExtra("SanPham", sanPham);
                resultIntent.putExtra("TRANGTHAI", "SUA");
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });


        btnThoatSP57.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}
