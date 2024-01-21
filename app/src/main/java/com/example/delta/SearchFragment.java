package com.example.delta;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.ColorUtils;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;

public class SearchFragment extends Fragment {
    private MaterialToolbar searchBar;
    private ScrollView productDetailsScroll;
    private ConstraintLayout productTopBar;
    private GridView resultsGrid;
    private int thumbnails[], thumbnail;
    private String names[], name, searchText;
    private double prices[], price;
    private ItemAdapter adapter;
    private TextView numResults;

    @SuppressLint("NewApi")
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        searchBar = (MaterialToolbar) view.findViewById(R.id.search_result);
        resultsGrid = (GridView) view.findViewById(R.id.results_grid);
        numResults = (TextView) view.findViewById(R.id.results_num_items);

        Bundle args = getArguments();
        if (args != null) {
            searchText = args.getString("searchText");
            thumbnails = args.getIntArray("thumbnails");
            names = args.getStringArray("names");
            prices = args.getDoubleArray("prices");

            numResults.setText(String.format("%d items", names.length));
        }

        adapter = new ItemAdapter(this.getContext(), thumbnails, names, prices);

        searchBar.setTitle(searchText);

        searchBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MYDEBUG", "search back pressed!");
                ((DeltaActivity) getActivity()).fm.popBackStack();
            }
        });

        resultsGrid.setAdapter(adapter);
        resultsGrid.setOnItemClickListener((parent, view1, position, id) -> {
            ImageView productImage = view.findViewById(R.id.product_image);
            TextView productName = view.findViewById(R.id.product_detail_name);
            TextView productPrice = view.findViewById(R.id.product_detail_price);

            productImage.setImageResource(thumbnails[position]); ((DeltaActivity) getActivity()).thumbnail = thumbnails[position];
            productName.setText(names[position]); ((DeltaActivity) getActivity()).name = names[position];
            productPrice.setText(String.format("$%.2f", prices[position])); ((DeltaActivity) getActivity()).price = prices[position];

            view.findViewById(R.id.product_details).setScrollY(0);
            view.findViewById(R.id.product_details).setVisibility(View.VISIBLE);
        });

        /* PRODUCT DETAILS SCROLLVIEW */
        productTopBar = view.findViewById(R.id.product_top_bar);
        productDetailsScroll = view.findViewById(R.id.body);

        productDetailsScroll.setOnScrollChangeListener(new View.OnScrollChangeListener(){
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                int scrollY = productDetailsScroll.getScrollY();
                double h = 500;

                if (scrollY < h)
                    productTopBar.setBackgroundColor(ColorUtils.setAlphaComponent(
                            getResources().getColor(R.color.white), (int)(scrollY/h * 255)
                    ));
                else productTopBar.setBackgroundColor(getResources().getColor(R.color.white));
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_search, container, false);
    }
}
