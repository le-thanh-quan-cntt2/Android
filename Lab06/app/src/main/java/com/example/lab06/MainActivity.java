package com.example.lab06;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mode.DanhMuc;
import com.example.mode.SanPham;

public class MainActivity extends AppCompatActivity {
    Spinner spnDanhMuc;
    ArrayAdapter<DanhMuc> danhMucArrayAdapter;
    ArrayAdapter<SanPham> sanPhamArrayAdapter;
    EditText edtMaSP, edtTenSP, edtGia;
    ListView lvSanPham;
    Button btnThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addview();
        addEvent();
    }

    private void addEvent() {
        btnThem.setOnClickListener(v -> {
            String ma = edtMaSP.getText().toString();
            String ten = edtTenSP.getText().toString();
            int gia = Integer.parseInt(edtGia.getText().toString());
            SanPham sanPham = new SanPham(ma, ten, gia);
            sanPhamArrayAdapter.add(sanPham);
        });
    }

    private void addview() {
        spnDanhMuc = findViewById(R.id.spnSanPham);
        danhMucArrayAdapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_item);
        spnDanhMuc.setAdapter(danhMucArrayAdapter);

        lvSanPham = findViewById(R.id.lvSanPham);
        sanPhamArrayAdapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1);
        lvSanPham.setAdapter(sanPhamArrayAdapter);

        edtMaSP = findViewById(R.id.editMaSP);
        edtTenSP = findViewById(R.id.editTenSP);
        edtGia = findViewById(R.id.editGiaSP);
        btnThem = findViewById(R.id.btnaddSP);

        // Thêm dữ liệu mẫu vào spinner
        danhMucArrayAdapter.add(new DanhMuc("1", "Bear"));
        danhMucArrayAdapter.add(new DanhMuc("2", "Rượu"));
        danhMucArrayAdapter.add(new DanhMuc("3", "Thuốc lá"));
    }
}
