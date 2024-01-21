package com.example.delta;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.fragment.app.Fragment;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;

public class SettingsFragment extends Fragment{
    private MaterialToolbar topBar;
    private Button userIcon;
    private TextView userName;
    private String name;
    @Override
    public void onViewCreated(View view, Bundle b){
        topBar = (MaterialToolbar) view.findViewById(R.id.top_bar);
        topBar.setTitle(R.string.nav_4);

        userName = view.findViewById(R.id.user_name);
        userName.setText(name);

        userIcon = view.findViewById(R.id.user_card_icon);
        userIcon.setText(String.valueOf(userName.getText().charAt(0)));
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        name = "John Doe";
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }
}
