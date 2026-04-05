package com.hubt.less3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class BT2Activity extends AppCompatActivity {
    EditText edtTenBMI, edtChieuCao, edtCanNang, edtBMIResult, edtChanDoan;
    Button btnTinhBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt2);

        edtTenBMI = findViewById(R.id.edtTenBMI);
        edtChieuCao = findViewById(R.id.edtChieuCao);
        edtCanNang = findViewById(R.id.edtCanNang);
        edtBMIResult = findViewById(R.id.edtBMIResult);
        edtChanDoan = findViewById(R.id.edtChanDoan);
        btnTinhBMI = findViewById(R.id.btnTinhBMI);

        btnTinhBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tinhBMI();
            }
        });
    }

    private void tinhBMI() {
        try {
            double H = Double.parseDouble(edtChieuCao.getText().toString());
            double W = Double.parseDouble(edtCanNang.getText().toString());

            // Đổi cm sang m nếu người dùng nhập số lớn (ví dụ > 3)
            if (H > 3) {
                H = H / 100;
            }

            double bmi = W / (H * H);
            edtBMIResult.setText(String.format(Locale.getDefault(), "%.1f", bmi));

            String chanDoan = "";
            if (bmi < 18) {
                chanDoan = "Người gầy";
            } else if (bmi <= 24.9) {
                chanDoan = "Người bình thường";
            } else if (bmi <= 29.9) {
                chanDoan = "Người béo phì độ I";
            } else if (bmi <= 34.9) {
                chanDoan = "Người béo phì độ II";
            } else {
                chanDoan = "Người béo phì độ III";
            }
            edtChanDoan.setText(chanDoan);
        } catch (Exception e) {
            edtBMIResult.setText("Lỗi nhập liệu");
        }
    }
}
