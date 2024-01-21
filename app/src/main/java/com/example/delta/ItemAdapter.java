package com.example.delta;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {
    private Context context;
    private ImageView itemThumbnail;
    private TextView name, price;
    private int thumbnails[];
    private String names[];
    private double prices[];
    private LayoutInflater li;

    public ItemAdapter(Context c, int[] tb, String[] n, double[] p){
        this.context = c;
        this.thumbnails = tb;
        this.names = n;
        this.prices = p;
        li = LayoutInflater.from(c);
    }

    @Override
    public int getCount() { return names.length; }

    @Override
    public Object getItem(int i) { return i; }

    @Override
    public long getItemId(int i) { return i; }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (viewGroup.getId() == R.id.results_grid) view = li.inflate(R.layout.results_item, null);
        else if (viewGroup.getId() == R.id.saved_listview) view = li.inflate(R.layout.saved_item, null);

        itemThumbnail = (ImageView) view.findViewById(R.id.product_thumbnail);
        itemThumbnail.setImageResource(thumbnails[i]);

        name = (TextView) view.findViewById(R.id.product_name);
        name.setText(names[i]);

        price = (TextView) view.findViewById(R.id.product_price);
        price.setText(String.format("$%.2f", prices[i]));
        return view;
    }
}
