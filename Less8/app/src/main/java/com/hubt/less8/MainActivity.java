package com.hubt.less8;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvFruit;
    ArrayList<Fruit> fruitArrayList;
    FruitAdapter adapter;
    Button btnGoToDrink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvFruit = findViewById(R.id.lvFruit);
        btnGoToDrink = findViewById(R.id.btnGoToDrink);
        fruitArrayList = new ArrayList<>();

        fruitArrayList.add(new Fruit("Táo", "Táo đỏ giòn ngọt - Giá: 150.000 đồng", R.mipmap.ic_launcher));
        fruitArrayList.add(new Fruit("Dâu", "Dâu tươi mọng nước - Giá: 200.000 đồng", R.mipmap.ic_launcher));
        fruitArrayList.add(new Fruit("Chuối", "Chuối chín vàng tự nhiên - Giá: 30.000 đồng", R.mipmap.ic_launcher));
        fruitArrayList.add(new Fruit("Khế", "Khế chua chua ngọt ngọt - Giá: 25.000 đồng", R.mipmap.ic_launcher));
        fruitArrayList.add(new Fruit("Lựu", "Lựu đỏ ngọt mát - Giá: 45.000 đồng", R.mipmap.ic_launcher));

        adapter = new FruitAdapter(this, R.layout.fruit_item, fruitArrayList);
        lvFruit.setAdapter(adapter);

        btnGoToDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DrinkActivity.class);
                startActivity(intent);
            }
        });
    }
}