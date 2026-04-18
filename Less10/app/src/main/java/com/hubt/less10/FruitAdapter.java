package com.hubt.less10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FruitAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Fruit> fruitList;

    public FruitAdapter(Context context, int layout, List<Fruit> fruitList) {
        this.context = context;
        this.layout = layout;
        this.fruitList = fruitList;
    }

    @Override
    public int getCount() {
        return fruitList.size();
    }

    @Override
    public Object getItem(int position) {
        return fruitList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        ImageView imgHinh;
        TextView txtTen, txtMoTa, txtGia;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder = new ViewHolder();
            holder.txtTen = convertView.findViewById(R.id.txtTen);
            holder.txtMoTa = convertView.findViewById(R.id.txtMoTa);
            holder.txtGia = convertView.findViewById(R.id.txtGia);
            holder.imgHinh = convertView.findViewById(R.id.imgHinh);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Fruit fruit = fruitList.get(position);
        holder.txtTen.setText(fruit.getTentraicay());
        holder.txtMoTa.setText(fruit.getMieuta());
        holder.txtGia.setText("Giá: " + fruit.getGia());
        holder.imgHinh.setImageResource(fruit.getAnhid());

        return convertView;
    }
}
