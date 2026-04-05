package com.hubt.less3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class BT1Activity extends AppCompatActivity {
    EditText edtTen, edtToan, edtLy, edtHoa, edtDTB, edtXepLoai;
    Button btnTinh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt1);

        edtTen = findViewById(R.id.edtTen);
        edtToan = findViewById(R.id.edtToan);
        edtLy = findViewById(R.id.edtLy);
        edtHoa = findViewById(R.id.edtHoa);
        edtDTB = findViewById(R.id.edtDTB);
        edtXepLoai = findViewById(R.id.edtXepLoai);
        btnTinh = findViewById(R.id.btnTinh);

        btnTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tinhDiem();
            }
        });
    }

    private void tinhDiem() {
        try {
            double toan = Double.parseDouble(edtToan.getText().toString());
            double ly = Double.parseDouble(edtLy.getText().toString());
            double hoa = Double.parseDouble(edtHoa.getText().toString());

            double dtb = (toan + ly + hoa) / 3;
            edtDTB.setText(String.format("%.1f", dtb));

            String xepLoai = "";
            if (dtb >= 8.0) {
                xepLoai = "Giỏi";
            } else if (dtb >= 7.0) {
                xepLoai = "Khá";
            } else if (dtb >= 5.0) {
                xepLoai = "Trung bình";
            } else {
                xepLoai = "Yếu";
            }
            edtXepLoai.setText(xepLoai);
        } catch (Exception e) {
            edtDTB.setText("Lỗi nhập liệu");
        }
    }
}
