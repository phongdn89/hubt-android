package com.hubt.less1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnCong, btnNhan, btnMoBai2, btnMoBai3;
    EditText edtNhapa, edtNhapb;
    TextView txtTieuDe, txtKQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ View
        btnCong = findViewById(R.id.btnCong);
        btnNhan = findViewById(R.id.btnNhan);
        btnMoBai2 = findViewById(R.id.btnMoBai2);
        btnMoBai3 = findViewById(R.id.btnMoBai3);
        edtNhapa = findViewById(R.id.edtNhapa);
        edtNhapb = findViewById(R.id.edtNhapb);
        txtTieuDe = findViewById(R.id.txtTieuDe);
        txtKQ = findViewById(R.id.txtKQ);

        // 1. Cách bắt sự kiện bằng Anonymous Listener (dùng cho Cộng và Nhân)
        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tinhToan("+");
            }
        });

        btnNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tinhToan("*");
            }
        });

        btnMoBai2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LinearEquationActivity.class);
                startActivity(intent);
            }
        });

        btnMoBai3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuadraticEquationActivity.class);
                startActivity(intent);
            }
        });
    }

    // 2. Cách bắt sự kiện onClick trong XML (dùng cho Trừ và Chia)
    public void xulyTru(View view) {
        tinhToan("-");
    }

    public void xulyChia(View view) {
        tinhToan("/");
    }

    // Hàm xử lý chung cho 4 phép toán
    private void tinhToan(String phepToan) {
        String strA = edtNhapa.getText().toString();
        String strB = edtNhapb.getText().toString();

        if (strA.isEmpty() || strB.isEmpty()) {
            txtKQ.setText("Vui lòng nhập đủ 2 số a và b");
            return;
        }

        try {
            double a = Double.parseDouble(strA);
            double b = Double.parseDouble(strB);
            double kq = 0;

            switch (phepToan) {
                case "+": kq = a + b; break;
                case "-": kq = a - b; break;
                case "*": kq = a * b; break;
                case "/":
                    if (b == 0) {
                        txtKQ.setText("Không thể chia cho 0");
                        return;
                    }
                    kq = a / b;
                    break;
            }
            txtKQ.setText("Kết quả: " + kq);
        } catch (NumberFormatException e) {
            txtKQ.setText("Vui lòng nhập số hợp lệ");
        }
    }
}