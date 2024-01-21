package com.example.delta;

import static com.example.delta.DeltaActivity.MYDEBUG;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.search.SearchBar;
import com.google.android.material.search.SearchView;

import java.util.ArrayList;
import java.util.List;

import RecyclerViewAdapter.ViewData;
import RecyclerViewAdapter.rvAdapterFirst;
import RecyclerViewAdapter.rvAdapterSecond;
import RecyclerViewAdapter.rvAdapterThird;
import SearchViewAdapters.SuggestionsAdapter;

public class HomeFragment extends Fragment{
    private SearchView searchView;
    private SearchBar searchBar;

    private RecyclerView recyclerView;
    private MaterialButton speaker;
    private List<String> originalSuggestions, filteredSuggestions;
    private SuggestionsAdapter adapter;

    RecyclerView rvFirst, rvSecond, rvThird;
    rvAdapterFirst rvAdapterFirst;
    rvAdapterSecond rvAdapterSecond;
    rvAdapterThird rvAdapterThird;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        originalSuggestions = new ArrayList<>();
        originalSuggestions.add("Christmas tree");
        originalSuggestions.add("jeans");
        originalSuggestions.add("cooking pan");
        originalSuggestions.add("DVD player");
        originalSuggestions.add("Baseball");
        originalSuggestions.add("Apple pen");
        originalSuggestions.add("shirts");
        originalSuggestions.add("computer");
        originalSuggestions.add("toys");
        originalSuggestions.add("desk");
        originalSuggestions.add("Toolbox");
        originalSuggestions.add("dictionary");
        originalSuggestions.add("pumpkin");
        originalSuggestions.add("Paint brush");

        filteredSuggestions = new ArrayList<>();
        filteredSuggestions.addAll(originalSuggestions);
        adapter = new SuggestionsAdapter(filteredSuggestions);
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle b){
        super.onViewCreated(view, b);

        searchView = (SearchView) getActivity().findViewById(R.id.main_searchview);
        searchBar = (SearchBar) view.findViewById(R.id.main_bar_search);
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.searchViewList);

        searchView.setupWithSearchBar(searchBar);

        speaker = view.findViewById(R.id.main_bar_mic);

        VoiceInput voice = new VoiceInput(getActivity(), speaker, searchBar);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        recyclerView.setClickable(true);


        // Handle the clicks of the drop down menu
        adapter.setOnItemClickListener((parent, view1, position, id) -> {
            String selectedItem = filteredSuggestions.get(position);

            if (checkAvailability(selectedItem)){
                searchView.hide();
                performFragmentChange(SearchFragment.class, selectedItem);
            } else Toast.makeText(getContext(), "Item Unavailable", Toast.LENGTH_SHORT).show();
        });

        // Handle what happens as text is typed into the searchBar/View
        searchView.getEditText().addTextChangedListener(new TextWatcher(){
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) { filterMenu(editable.toString()); }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
        });

        searchView.getEditText().setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {
                String text = textView.getText().toString().trim();
                if (!TextUtils.isEmpty(text)) {

                    if (checkAvailability(text)){
                        searchView.hide();
                        performFragmentChange(SearchFragment.class, text);
                    } else  Toast.makeText(getContext(), "Item Unavailable", Toast.LENGTH_SHORT).show();

                    Log.d(MYDEBUG, "Entered text: " + text);
                    filterMenu(text);
                }

                // Hide the keyboard and clear focus
                searchView.clearFocusAndHideKeyboard();
                return true;
            }
            return false;
        });

        this.rvFirst = view.findViewById(R.id.firstRecycler);
        this.rvSecond = view.findViewById(R.id.secondRecycler);
        this.rvThird = view.findViewById(R.id.thirdRecycler);

        // Populate the recycler views with content
        this.rvFirstPopulate();
        this.rvSecondPopulate();
        this.rvThirdPopulate();
    }

    // Populate the first recycler view with images
    private void rvFirstPopulate() {
        this.rvFirst.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        ArrayList<ViewData> rvFirstData = new ArrayList<>();
        rvFirstData.add(new ViewData(R.drawable.banner1));
        rvFirstData.add(new ViewData(R.drawable.banner2));
        rvFirstData.add(new ViewData(R.drawable.banner3));

        this.rvAdapterFirst = new rvAdapterFirst(rvFirstData);
        this.rvFirst.setLayoutManager(linearLayoutManager);
        this.rvFirst.setAdapter(this.rvAdapterFirst);
    }

    // Populate the second recycler view with images
    private void rvSecondPopulate() {
        this.rvSecond.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        ArrayList<ViewData> rvSecondData = new ArrayList<>();
        rvSecondData.add(new ViewData(
                R.drawable.thumbnail_dvd2,
                "DVD player (used)",
                "Used DVD player! Works Perfectly fine..."));
        rvSecondData.add(new ViewData(
                R.drawable.thumbnail_dvd1,
                "DVD player",
                "Brand New DVD player!"));
        rvSecondData.add(new ViewData(
                R.drawable.thumbnail_shirt1,
                "Cotton Shirt",
                "Brand new shirt, made in Cambodia"));

        this.rvAdapterSecond = new rvAdapterSecond(rvSecondData);
        this.rvSecond.setLayoutManager(linearLayoutManager);
        this.rvSecond.setAdapter(this.rvAdapterSecond);
    }

    // Populate the third recycler view with images
    private void rvThirdPopulate() {
        this.rvThird.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        ArrayList<ViewData> rvThirdData = new ArrayList<>();
        rvThirdData.add(new ViewData(
                R.drawable.thumbnail_shirt8,
                "Delta colored shirt",
                "MUST BUY!! Epic shirt"));
        rvThirdData.add(new ViewData(
                R.drawable.thumbnail_dvd6,
                "DVD player slim",
                "Brand NEW DVD player-slim"));
        rvThirdData.add(new ViewData(
                R.drawable.thumbnail_shirt7,
                "Delta colored shirt",
                "MUST BUY!! Epic shirt"));

        this.rvAdapterThird = new rvAdapterThird(rvThirdData);
        this.rvThird.setLayoutManager(linearLayoutManager);
        this.rvThird.setAdapter(this.rvAdapterThird);
    }

    // filter the suggestions on text input
    private void filterMenu(String query){
        filteredSuggestions.clear();
        // Filter the original data set based on the query

        // If the query is empty, show the original suggestions
        if (TextUtils.isEmpty(query)) filteredSuggestions.addAll(originalSuggestions);

        else for (String suggestion : originalSuggestions)
             if (suggestion.toLowerCase().contains(query.toLowerCase()))  filteredSuggestions.add(suggestion);

        // Update the adapter with the filtered data
        adapter.notifyDataSetChanged();

    }

    private void performFragmentChange(Class<? extends Fragment> fragment, String item) {
        Log.d(MYDEBUG, "performFragmentChange: " + fragment.getSimpleName());
        if (getActivity() instanceof DeltaActivity) {

            int[] thumbnails = this.getThumbnail(item);
            String[] names = this.getNames(item);
            double[] prices = this.getPrices(item);

            Bundle parameters = new Bundle();
            parameters.putString("searchText", item);
            parameters.putIntArray("thumbnails", thumbnails);
            parameters.putStringArray("names", names);
            parameters.putDoubleArray("prices", prices);

            ((DeltaActivity) getActivity()).changeFragment(fragment, parameters);
        }
    }

    // set images of items
    private int[] getThumbnail(String item){
        if (item.equals("DVD player"))
            return new int[] {R.drawable.thumbnail_dvd1, R.drawable.thumbnail_dvd2, R.drawable.thumbnail_dvd3, R.drawable.thumbnail_dvd4, R.drawable.thumbnail_dvd5, R.drawable.thumbnail_dvd6, R.drawable.thumbnail_dvd7, R.drawable.thumbnail_dvd8};
        else if(item.equals("shirts"))
            return new int[] {R.drawable.thumbnail_shirt1, R.drawable.thumbnail_shirt2, R.drawable.thumbnail_shirt3, R.drawable.thumbnail_shirt4, R.drawable.thumbnail_shirt5, R.drawable.thumbnail_shirt6, R.drawable.thumbnail_shirt7, R.drawable.thumbnail_shirt8, R.drawable.thumbnail_shirt9, R.drawable.thumbnail_shirt10, R.drawable.thumbnail_shirt11, R.drawable.thumbnail_shirt12};

        return null;
    }

    // set names of items
    private String[] getNames(String item){
        if (item.equals("DVD player"))
            return new String[] {"DVD player", "DVD player (used)", "Portable DVD player", "DVD player PRO", "Delta DVD player 7", "DVD player SLIM", "Delta DVD player 3", "PHILIPS DVD player"};
        else if(item.equals("shirts"))
            return new String[] {"Cotton shirt", "Cotton shirt", "Delta coloured shirt", "Loose fit shirt", "Delta coloured shirt", "Delta coloured shirt", "Delta coloured shirt", "Delta coloured shirt", "Delta coloured shirt", "Delta coloured shirt", "Delta coloured shirt", "Cotton shirt"};
        return null;
    }

    // set prices of items
    private double[] getPrices(String item){
        if (item.equals("DVD player"))
           return new double[] {50.00, 35.00, 38.00, 60.00, 85.00, 77.00, 43.00, 63.00};
        else if(item.equals("shirts"))
           return new double[] {25.00, 25.00, 23.00, 28.00, 23.00, 23.00, 23.00, 23.00, 23.00, 23.00, 23.00, 25.00};
        return null;
    }

    // put the item in here if it has been implemented
    private boolean checkAvailability(String item){
        return item.equals("DVD player") || item.equals("shirts");
    }

}