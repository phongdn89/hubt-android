package com.hubt.less8;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DrinkAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Drink> drinkList;

    public DrinkAdapter(Context context, int layout, List<Drink> drinkList) {
        this.context = context;
        this.layout = layout;
        this.drinkList = drinkList;
    }

    @Override
    public int getCount() {
        return drinkList.size();
    }

    @Override
    public Object getItem(int i) {
        return drinkList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder = new ViewHolder();
            holder.txtName = view.findViewById(R.id.txtDrinkName);
            holder.txtPrice = view.findViewById(R.id.txtDrinkPrice);
            holder.imgDrink = view.findViewById(R.id.imgDrink);
            holder.edtQuantity = view.findViewById(R.id.edtQuantity);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Drink drink = drinkList.get(i);
        holder.txtName.setText(drink.getName());
        holder.txtPrice.setText("Giá: " + drink.getPrice() + " đồng");
        holder.imgDrink.setImageResource(drink.getImage());
        holder.edtQuantity.setText(String.valueOf(drink.getQuantity()));

        holder.edtQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    int qty = Integer.parseInt(editable.toString());
                    drink.setQuantity(qty);
                } catch (NumberFormatException e) {
                    drink.setQuantity(0);
                }
            }
        });

        return view;
    }

    private static class ViewHolder {
        TextView txtName, txtPrice;
        ImageView imgDrink;
        EditText edtQuantity;
    }
}
