package com.hubt.less11;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Setup Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 2. Setup ListView for Context Menu
        listView = findViewById(R.id.listView);
        dataList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            dataList.add("Item " + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);

        // 3. Open ChildActivity
        Button btnOpenChild = findViewById(R.id.btnOpenChild);
        btnOpenChild.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ChildActivity.class);
            startActivity(intent);
        });

        // 4. Show Popup Menu
        Button btnShowPopup = findViewById(R.id.btnShowPopup);
        btnShowPopup.setOnClickListener(this::showPopupMenu);
    }

    // --- Options Menu (Bài 3 & 6) ---
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Toast.makeText(this, "Bạn đã nhấn Cài đặt", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // --- Context Menu (Bài 4) ---
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }

    // --- Popup Menu (Bài 5) ---
    private void showPopupMenu(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.getMenu().add("One");
        popup.getMenu().add("Two");
        popup.getMenu().add("Three");
        popup.setOnMenuItemClickListener(item -> {
            Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
            return true;
        });
        popup.show();
    }
}