package com.hubt.less1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LinearEquationActivity extends AppCompatActivity {

    EditText edtA, edtB;
    Button btnGiaiPT;
    TextView txtNghiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_equation);

        addControls();
        addEvents();
    }

    private void addControls() {
        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        btnGiaiPT = findViewById(R.id.btnGiaiPT);
        txtNghiem = findViewById(R.id.txtNghiem);
    }

    private void addEvents() {
        btnGiaiPT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulyGiaiPT();
            }
        });
    }

    private void xulyGiaiPT() {
        String sA = edtA.getText().toString();
        String sB = edtB.getText().toString();

        if (sA.isEmpty() || sB.isEmpty()) {
            txtNghiem.setText("Vui lòng nhập đủ a và b");
            return;
        }

        double a = Double.parseDouble(sA);
        double b = Double.parseDouble(sB);

        if (a == 0) {
            if (b == 0) {
                txtNghiem.setText("Vô số nghiệm");
            } else {
                txtNghiem.setText("Vô nghiệm");
            }
        } else {
            double x = -b / a;
            txtNghiem.setText(String.valueOf(x));
        }
    }
}
