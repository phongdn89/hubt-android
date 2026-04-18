package com.hubt.less10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView txtWelcome;
    private ListView lvFruit;
    private ArrayList<Fruit> fruitList;
    private FruitAdapter fruitAdapter;
    private ActivityResultLauncher<Intent> detailActivityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtWelcome = findViewById(R.id.txtWelcome);
        lvFruit = findViewById(R.id.lvFruit);

        // Exercise 1: Get username from LoginActivity
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("KEY_USERNAME")) {
            String user = intent.getStringExtra("KEY_USERNAME");
            txtWelcome.setText("Welcome: " + user);
        }

        // Exercise 2: Product List
        fruitList = new ArrayList<>();
        fruitList.add(new Fruit("Táo", "Táo đỏ Mỹ thơm ngon", 50.0, R.drawable.ic_apple));
        fruitList.add(new Fruit("Chuối", "Chuối sứ chín vàng", 20.0, R.drawable.ic_banana));
        fruitList.add(new Fruit("Cam", "Cam sành mọng nước", 35.0, R.drawable.ic_orange));
        fruitList.add(new Fruit("Xoài", "Xoài cát Hòa Lộc", 60.0, R.drawable.ic_mango));

        fruitAdapter = new FruitAdapter(this, R.layout.item_fruit, fruitList);
        lvFruit.setAdapter(fruitAdapter);

        // Initialize ActivityResultLauncher
        detailActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent data = result.getData();
                            if (data != null && data.getBooleanExtra("delete", false)) {
                                String nameToDelete = data.getStringExtra("name");
                                for (int i = 0; i < fruitList.size(); i++) {
                                    if (fruitList.get(i).getTentraicay().equals(nameToDelete)) {
                                        fruitList.remove(i);
                                        break;
                                    }
                                }
                                fruitAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                }
        );

        lvFruit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit selectedFruit = fruitList.get(position);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("name", selectedFruit.getTentraicay());
                intent.putExtra("description", selectedFruit.getMieuta());
                intent.putExtra("price", selectedFruit.getGia());
                intent.putExtra("image", selectedFruit.getAnhid());
                detailActivityLauncher.launch(intent);
            }
        });
    }
}
