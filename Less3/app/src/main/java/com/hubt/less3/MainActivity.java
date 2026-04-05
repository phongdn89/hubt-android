package com.hubt.less3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView imgdn2;
    private EditText edtUser, edtPass;
    private Button btnDN, btnExit;
    private boolean isFirstImage = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        imgdn2 = findViewById(R.id.imgdn2);
        edtUser = findViewById(R.id.edtUser);
        edtPass = findViewById(R.id.edtPass);
        btnDN = findViewById(R.id.btnDN);
        btnExit = findViewById(R.id.btnExit);

        // Requirement 1: Click image to change it
        imgdn2.setOnClickListener(v -> {
            if (isFirstImage) {
                imgdn2.setImageResource(R.drawable.ic_launcher_background);
            } else {
                imgdn2.setImageResource(R.drawable.ic_launcher_foreground);
            }
            isFirstImage = !isFirstImage;
        });

        // Requirement 2: Login logic
        btnDN.setOnClickListener(v -> {
            String username = edtUser.getText().toString();
            String password = edtPass.getText().toString();

            if (username.equals("admin") && password.equals("123")) {
                Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Sai username hoặc password", Toast.LENGTH_SHORT).show();
            }
        });

        // Requirement 3: Exit logic with AlertDialog
        btnExit.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Thông báo");
            builder.setMessage("Bạn có muốn thoát không?");
            builder.setPositiveButton("YES", (dialog, which) -> finish());
            builder.setNegativeButton("NO", (dialog, which) -> dialog.dismiss());
            builder.setNeutralButton("CANCEL", (dialog, which) -> dialog.cancel());
            builder.show();
        });

        // Navigation to other exercises
        findViewById(R.id.btnGoOrder).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, OrderActivity.class));
        });
        findViewById(R.id.btnGoInfo).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, InfoActivity.class));
        });
        findViewById(R.id.btnGoQuiz).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, QuizActivity.class));
        });
    }
}