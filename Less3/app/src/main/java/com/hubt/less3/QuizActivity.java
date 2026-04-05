package com.hubt.less3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    private RadioGroup rgQuiz;
    private RadioButton r_a, r_b, r_c;
    private Button btnKetQua;
    private TextView tvSelection, tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        rgQuiz = findViewById(R.id.rgQuiz);
        r_a = findViewById(R.id.radio_a);
        r_b = findViewById(R.id.radio_b);
        r_c = findViewById(R.id.radio_c);
        btnKetQua = findViewById(R.id.btnKetQua);
        tvSelection = findViewById(R.id.tvSelection);
        tvResult = findViewById(R.id.tvResult);

        rgQuiz.setOnCheckedChangeListener((group, checkedId) -> {
            btnKetQua.setEnabled(true);
            RadioButton rb = findViewById(checkedId);
            tvSelection.setText("Bạn chọn: " + rb.getText());
        });

        btnKetQua.setOnClickListener(v -> {
            if (r_b.isChecked()) {
                tvResult.setText("Đáp án đúng");
                tvResult.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
            } else {
                tvResult.setText("Đáp án sai");
                tvResult.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            }
        });
    }
}