package com.example.delta;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.Fragment;
import android.view.ViewGroup;

import com.google.android.material.appbar.MaterialToolbar;

public class EmptySavedFragment extends Fragment{
    private MaterialToolbar topBar;

    @Override
    public void onViewCreated(View view, Bundle b){
        topBar = (MaterialToolbar) view.findViewById(R.id.top_bar);
        topBar.setTitle("Saved items");
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_saved_empty, container, false);
    }
}
