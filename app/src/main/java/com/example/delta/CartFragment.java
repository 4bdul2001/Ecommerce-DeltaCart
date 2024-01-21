package com.example.delta;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.Arrays;

import RecyclerViewAdapter.CartList;

public class CartFragment extends Fragment{
    private MaterialToolbar topBar;
    private LinearLayoutManager llm;
    private RecyclerView rv;
    private CartList cardList;
    private double prices;
    private TextView orderValue, taxes, subtotal;

    @SuppressLint("NewApi")
    @Override
    public void onViewCreated(View view, Bundle b){
        topBar = (MaterialToolbar) view.findViewById(R.id.top_bar);
        topBar.setTitle("Shopping cart");

        rv = view.findViewById(R.id.cart_list);

        llm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        cardList = new CartList( ((DeltaActivity) getActivity()).cartThumbnails.stream().mapToInt(i->i).toArray(),
                ((DeltaActivity) getActivity()).cartNames.toArray(new String[0]),
                ((DeltaActivity) getActivity()).cartPrices.stream().mapToDouble(i->i).toArray() );

        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);
        rv.setAdapter(cardList);

        prices = ((DeltaActivity) getActivity()).cartPrices.stream().mapToDouble(i->i).sum();

        orderValue = view.findViewById(R.id.cart_order_value);
        taxes = view.findViewById(R.id.cart_taxes);
        subtotal = view.findViewById(R.id.cart_subtotal);

        orderValue.setText(String.format("$%.2f", prices));
        taxes.setText(String.format("$%.2f", (prices - ((DeltaActivity) getActivity()).DISCOUNT) * 0.13));
        subtotal.setText(String.format("$%.2f", prices - ((DeltaActivity) getActivity()).DISCOUNT * 1.13));
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }
}
