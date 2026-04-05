package com.hubt.less3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {

    private CheckBox cbPho, cbBun, cbCom, cbNem;
    private EditText edtQtyPho, edtQtyBun, edtQtyCom, edtQtyNem;
    private Button btnCalc;
    private TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        cbPho = findViewById(R.id.cbPho);
        cbBun = findViewById(R.id.cbBun);
        cbCom = findViewById(R.id.cbCom);
        cbNem = findViewById(R.id.cbNem);

        edtQtyPho = findViewById(R.id.edtQtyPho);
        edtQtyBun = findViewById(R.id.edtQtyBun);
        edtQtyCom = findViewById(R.id.edtQtyCom);
        edtQtyNem = findViewById(R.id.edtQtyNem);

        btnCalc = findViewById(R.id.btnCalc);
        tvTotal = findViewById(R.id.tvTotal);

        btnCalc.setOnClickListener(v -> {
            long total = 0;
            if (cbPho.isChecked()) {
                total += 50000 * Integer.parseInt(edtQtyPho.getText().toString());
            }
            if (cbBun.isChecked()) {
                total += 45000 * Integer.parseInt(edtQtyBun.getText().toString());
            }
            if (cbCom.isChecked()) {
                total += 60000 * Integer.parseInt(edtQtyCom.getText().toString());
            }
            if (cbNem.isChecked()) {
                total += 35000 * Integer.parseInt(edtQtyNem.getText().toString());
            }
            tvTotal.setText("Tổng tiền: " + total + " VND");
        });
    }
}