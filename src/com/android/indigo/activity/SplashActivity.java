package com.android.indigo.activity;

import com.android.indigo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by hoang_nam on 2014/05/07.
 */
public class SplashActivity extends Activity {
    private String action = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Intent intent = getIntent();
        action = intent.getAction();

        Handler handler = new Handler();
        handler.postDelayed(new Splash(), 1000);
    }

    class Splash implements Runnable {

        @Override
        public void run() {
            Intent homeIntent = new Intent(SplashActivity.this, HomeActivity.class);
            homeIntent.setAction(action);
            startActivity(homeIntent);
            SplashActivity.this.finish();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        action = null;
    }
}
