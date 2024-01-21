package com.example.delta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

@SuppressWarnings("Deprecated")
public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME = 1500;

    ImageView logo;
    Animation topAnimation;

    @Override
    @SuppressWarnings("Deprecated")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        logo = findViewById(R.id.imageView);
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);

        logo.setAnimation(topAnimation);

        new Handler().postDelayed(() -> {
            Intent homeIntent = new Intent(SplashActivity.this, DeltaActivity.class);
            startActivity(homeIntent);
            finish();
        }, SPLASH_TIME);

    }
}