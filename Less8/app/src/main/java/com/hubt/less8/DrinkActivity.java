package com.hubt.less8;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class DrinkActivity extends AppCompatActivity {

    ListView lvDrink;
    ArrayList<Drink> drinkArrayList;
    DrinkAdapter adapter;
    Button btnOrder;
    TextView txtTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        lvDrink = findViewById(R.id.lvDrink);
        btnOrder = findViewById(R.id.btnOrder);
        txtTotal = findViewById(R.id.txtTotal);

        drinkArrayList = new ArrayList<>();
        drinkArrayList.add(new Drink("Trà chanh", 15000, R.mipmap.ic_launcher));
        drinkArrayList.add(new Drink("Trà đào", 25000, R.mipmap.ic_launcher));
        drinkArrayList.add(new Drink("Trà vải", 25000, R.mipmap.ic_launcher));
        drinkArrayList.add(new Drink("Trà quất", 15000, R.mipmap.ic_launcher));
        drinkArrayList.add(new Drink("Hướng dương", 10000, R.mipmap.ic_launcher));

        adapter = new DrinkAdapter(this, R.layout.drink_item, drinkArrayList);
        lvDrink.setAdapter(adapter);

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int total = 0;
                for (Drink drink : drinkArrayList) {
                    total += drink.getPrice() * drink.getQuantity();
                }
                txtTotal.setText("Tổng tiền: " + total + " đồng");
            }
        });
    }
}
