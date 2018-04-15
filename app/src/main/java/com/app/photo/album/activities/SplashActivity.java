package com.app.photo.album.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.app.photo.album.R;
import com.app.photo.album.lifecycle.ApplicationCrashHandler;
import com.app.photo.album.lifecycle.ApplicationWrapper;

/**
 * Created by ajay on 14/4/18.
 */

public class SplashActivity extends AppCompatActivity {


    public static String TAG = "com.app.photo.album.activities.SplashActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        ApplicationCrashHandler.installHandler();

        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - ApplicationWrapper.startTime;
        if (elapsedTime > 3000) {
            Log.e(TAG, String.format("LONG STARTUP TIME"));
        }
        Log.d(TAG, String.format("Elapsed Time = %d ms", elapsedTime));

        // Transition to the MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
