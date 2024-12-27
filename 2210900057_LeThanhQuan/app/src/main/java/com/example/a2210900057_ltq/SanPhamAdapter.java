package com.example.a2210900057_ltq;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class SanPhamAdapter extends ArrayAdapter<SanPham> {

    private Context context;
    private List<SanPham> sanPhamList;

    public SanPhamAdapter(Context context, List<SanPham> list) {
        super(context, 0, list);
        this.context = context;
        this.sanPhamList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_sanpham, parent, false);
        }

        SanPham sanPham = sanPhamList.get(position);

        // Ánh xạ các view
        TextView tvMaSP57 = convertView.findViewById(R.id.tvMaSP5);
        TextView tvTenSP57 = convertView.findViewById(R.id.tvTenSP5);
        TextView tvSoLuong57 = convertView.findViewById(R.id.tvSoLuong5);
        TextView tvDonGia57 = convertView.findViewById(R.id.tvDonGia5);
        TextView tvThanhTien57 = convertView.findViewById(R.id.tvThanhTien5);

        // Gán dữ liệu từ cơ sở dữ liệu vào các view
        tvMaSP57.setText("Mã SP: " + sanPham.getMaSP());
        tvTenSP57.setText("Tên SP: " + sanPham.getTenSP());
        tvSoLuong57.setText("Số lượng: " + sanPham.getSoLuong());
        tvDonGia57.setText("Đơn giá: " + formatCurrency(sanPham.getDonGia()));
        tvThanhTien57.setText("Thành tiền: " + formatCurrency(sanPham.getThanhTien()));

        return convertView;
    }

    // Định dạng số tiền
    private String formatCurrency(float amount) {
        NumberFormat format = NumberFormat.getInstance(new Locale("vi", "VN"));
        return format.format(amount) + " VNĐ";
    }
}
