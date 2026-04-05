package com.hubt.less3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnBT1 = findViewById(R.id.btnBT1);
        Button btnBT2 = findViewById(R.id.btnBT2);

        btnBT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BT1Activity.class);
                startActivity(intent);
            }
        });

        btnBT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BT2Activity.class);
                startActivity(intent);
            }
        });
    }
}
