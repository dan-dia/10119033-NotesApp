package com.dandiahmadin.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.dandiahmadin.notesapp.activity.SignInActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent goToSignInActivity = new Intent(SplashScreenActivity.this, SignInActivity.class);
                startActivity(goToSignInActivity);
                finish();
            }
        }, 1000);
    }
}