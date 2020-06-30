package co.desarrollo.blockchain.activities;

import androidx.appcompat.app.AppCompatActivity;
import co.desarrollo.blockchain.R;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {
    int SPLASH_DISPLAY_TIME = 2500; // splash screen delay time

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                IniciarAplicacion();
            }
        }, SPLASH_DISPLAY_TIME);
    }

    public void IniciarAplicacion() {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        this.startActivity(intent);
        this.finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
}