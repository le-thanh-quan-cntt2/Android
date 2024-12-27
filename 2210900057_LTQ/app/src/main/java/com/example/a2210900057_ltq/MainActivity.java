package com.example.a2210900057_ltq;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String dbName = "QLSanpham57.db";
    SQLiteDatabase db = null;
    ArrayAdapter<SanPham> adapter;
    ListView lvSanPham57;
    TextView tvEmptyMessage;
    SanPham sanPhamChon;
    int posUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        copyDatabase();
        addViews();
        hienThiSanPham();
        addEvents();
    }

    private void addViews() {
        lvSanPham57 = findViewById(R.id.lvSanPham57);
        tvEmptyMessage = findViewById(R.id.tvEmptyMessage);
        adapter = new SanPhamAdapter(this, new ArrayList<>());
        lvSanPham57.setAdapter(adapter);
        registerForContextMenu(lvSanPham57);
    }

    private void addEvents() {
        findViewById(R.id.btnThemSP57).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ThemSuaActivity.class);
            intent.putExtra("TRANGTHAI", "THEM");
            startActivityForResult(intent, 113);
        });

        lvSanPham57.setOnItemClickListener((parent, view, position, id) -> {
            sanPhamChon = adapter.getItem(position);
            posUpdate = position;
        });
    }

    private void hienThiSanPham() {
        db = openOrCreateDatabase(dbName, MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM SanPham57", null);
        adapter.clear();
        while (cursor.moveToNext()) {
            int maSP = cursor.getInt(0);
            String tenSP = cursor.getString(1);
            int soLuong = cursor.getInt(2);
            float donGia = cursor.getFloat(3);
            adapter.add(new SanPham(maSP, tenSP, soLuong, donGia));
        }
        cursor.close();
        toggleEmptyMessage();
    }

    private void toggleEmptyMessage() {
        if (adapter.isEmpty()) {
            tvEmptyMessage.setVisibility(View.VISIBLE);
            lvSanPham57.setVisibility(View.GONE);
        } else {
            tvEmptyMessage.setVisibility(View.GONE);
            lvSanPham57.setVisibility(View.VISIBLE);
        }
    }

    private void copyDatabase() {
        try {
            File dbFile = getDatabasePath(dbName);
            if (!dbFile.exists()) {
                InputStream myInput = getAssets().open(dbName);
                String outFileName = getApplicationInfo().dataDir + "/databases/" + dbName;
                File dir = new File(getApplicationInfo().dataDir + "/databases/");
                if (!dir.exists()) dir.mkdir();
                OutputStream myOutput = new FileOutputStream(outFileName);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }
                myOutput.flush();
                myOutput.close();
                myInput.close();
            }
        } catch (Exception e) {
            Log.e("Database Error", e.toString());
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu57, menu);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        sanPhamChon = adapter.getItem(info.position);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (sanPhamChon == null) {
            Toast.makeText(this, "Không có sản phẩm nào được chọn", Toast.LENGTH_SHORT).show();
            return super.onContextItemSelected(item);
        }

        if (item.getItemId() == R.id.mnuSua57) {
            Intent intent = new Intent(MainActivity.this, ThemSuaActivity.class);
            intent.putExtra("TRANGTHAI", "SUA");
            intent.putExtra("SanPham", sanPhamChon);
            startActivityForResult(intent, 113);
        } else if (item.getItemId() == R.id.mnuXoa57) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Xác nhận xóa")
                    .setMessage("Bạn có chắc chắn muốn xóa sản phẩm này không?")
                    .setPositiveButton("Có", (dialog, which) -> {
                        db.delete("SanPham57", "MaSP=?", new String[]{String.valueOf(sanPhamChon.getMaSP())});
                        adapter.remove(sanPhamChon);
                        adapter.notifyDataSetChanged();
                        toggleEmptyMessage();
                        Toast.makeText(this, "Đã xóa sản phẩm", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Không", null)
                    .show();
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 113) {
            SanPham newSanPham = (SanPham) data.getSerializableExtra("SanPham");
            ContentValues values = new ContentValues();
            values.put("TenSP", newSanPham.getTenSP());
            values.put("SoLuong", newSanPham.getSoLuong());
            values.put("DonGia", newSanPham.getDonGia());

            if ("THEM".equals(data.getStringExtra("TRANGTHAI"))) {
                long newId = db.insert("SanPham57", null, values);
                newSanPham.setMaSP((int) newId);
                adapter.add(newSanPham);
                adapter.notifyDataSetChanged();
                toggleEmptyMessage();
                Toast.makeText(this, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
            } else if ("SUA".equals(data.getStringExtra("TRANGTHAI"))) {
                int updatedRows = db.update("SanPham57", values, "MaSP=?", new String[]{String.valueOf(newSanPham.getMaSP())});
                if (updatedRows > 0) {
                    adapter.getItem(posUpdate).setTenSP(newSanPham.getTenSP());
                    adapter.getItem(posUpdate).setSoLuong(newSanPham.getSoLuong());
                    adapter.getItem(posUpdate).setDonGia(newSanPham.getDonGia());
                    adapter.notifyDataSetChanged();
                    Toast.makeText(this, "Cập nhật sản phẩm thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Cập nhật sản phẩm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
