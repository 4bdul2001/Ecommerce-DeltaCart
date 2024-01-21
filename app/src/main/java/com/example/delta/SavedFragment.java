package com.example.delta;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.Fragment;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.material.appbar.MaterialToolbar;

public class SavedFragment extends Fragment{
    private MaterialToolbar topBar;
    private ListView savedList;
    private ItemAdapter adapter;

    @SuppressLint("NewApi")
    @Override
    public void onViewCreated(View view, Bundle b){
        topBar = (MaterialToolbar) view.findViewById(R.id.top_bar);
        topBar.setTitle("Saved items");

        adapter = new ItemAdapter(this.getContext(),
                ((DeltaActivity) getActivity()).savedThumbnails.stream().mapToInt(i->i).toArray(),
                ((DeltaActivity) getActivity()).savedNames.toArray(new String[0]),
                ((DeltaActivity) getActivity()).savedPrices.stream().mapToDouble(i->i).toArray() );

        savedList = (ListView) view.findViewById(R.id.saved_listview);
        savedList.setAdapter(adapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_saved, container, false);
    }
}
