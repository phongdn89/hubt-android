package com.hubt.less1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class QuadraticEquationActivity extends AppCompatActivity {

    EditText edtA2, edtB2, edtC2;
    Button btnGiaiPT2;
    TextView txtNghiem2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadratic_equation);

        addControls();
        addEvents();
    }

    private void addControls() {
        edtA2 = findViewById(R.id.edtA2);
        edtB2 = findViewById(R.id.edtB2);
        edtC2 = findViewById(R.id.edtC2);
        btnGiaiPT2 = findViewById(R.id.btnGiaiPT2);
        txtNghiem2 = findViewById(R.id.txtNghiem2);
    }

    private void addEvents() {
        btnGiaiPT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulyGiaiPT2();
            }
        });
    }

    private void xulyGiaiPT2() {
        String sA = edtA2.getText().toString();
        String sB = edtB2.getText().toString();
        String sC = edtC2.getText().toString();

        if (sA.isEmpty() || sB.isEmpty() || sC.isEmpty()) {
            txtNghiem2.setText("Vui lòng nhập đủ a, b và c");
            return;
        }

        double a = Double.parseDouble(sA);
        double b = Double.parseDouble(sB);
        double c = Double.parseDouble(sC);

        if (a == 0) {
            // Giải phương trình bậc 1: bx + c = 0
            if (b == 0) {
                if (c == 0) txtNghiem2.setText("Vô số nghiệm");
                else txtNghiem2.setText("Vô nghiệm");
            } else {
                txtNghiem2.setText("x = " + (-c / b));
            }
        } else {
            double delta = b * b - 4 * a * c;
            if (delta < 0) {
                txtNghiem2.setText("Vô nghiệm");
            } else if (delta == 0) {
                double x = -b / (2 * a);
                txtNghiem2.setText("Nghiệm kép x1=x2=" + x);
            } else {
                double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                txtNghiem2.setText("x1=" + x1 + "; x2=" + x2);
            }
        }
    }
}
