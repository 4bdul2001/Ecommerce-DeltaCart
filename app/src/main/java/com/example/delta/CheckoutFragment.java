package com.example.delta;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

public class CheckoutFragment extends Fragment {
    MaterialButton completePurchase;
    MaterialToolbar topBar;

    TextView orderValue, taxes, orderTotal;
    double prices;

    @SuppressLint("NewApi")
    @Override
    public void onViewCreated(View view, Bundle b){
        completePurchase = (MaterialButton) view.findViewById(R.id.complete_checkout_button);
        topBar = view.findViewById(R.id.top_bar);
        topBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MYDEBUG", "checkout back pressed!");
                ((DeltaActivity) getActivity()).fm.popBackStack();
                ((DeltaActivity) getActivity()).navBar.setVisibility(View.VISIBLE);
            }
        });

        orderValue = view.findViewById(R.id.checkout_order_value);
        taxes = view.findViewById(R.id.checkout_taxes);
        orderTotal = view.findViewById(R.id.checkout_total);

        prices = ((DeltaActivity) getActivity()).cartPrices.stream().mapToDouble(i->i).sum();

        orderValue.setText(String.format("$%.2f", prices));
        taxes.setText(String.format("$%.2f", (prices - ((DeltaActivity) getActivity()).DISCOUNT) * 0.13));
        orderTotal.setText(String.format("$%.2f", prices - ((DeltaActivity) getActivity()).DISCOUNT * 1.13));
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_checkout, container, false);
    }
}
