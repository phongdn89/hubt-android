package com.hubt.less10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private ImageView imgDetailImage;
    private TextView txtDetailTen, txtDetailMoTa, txtDetailGia;
    private Button btnBack, btnXoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgDetailImage = findViewById(R.id.imgDetailImage);
        txtDetailTen = findViewById(R.id.txtDetailTen);
        txtDetailMoTa = findViewById(R.id.txtDetailMoTa);
        txtDetailGia = findViewById(R.id.txtDetailGia);
        btnBack = findViewById(R.id.btnBack);
        btnXoa = findViewById(R.id.btnXoa);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String description = intent.getStringExtra("description");
        double price = intent.getDoubleExtra("price", 0);
        int imageResId = intent.getIntExtra("image", 0);

        txtDetailTen.setText(name);
        txtDetailMoTa.setText(description);
        txtDetailGia.setText("$ " + price);
        imgDetailImage.setImageResource(imageResId);

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("delete", true);
                resultIntent.putExtra("name", name);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
