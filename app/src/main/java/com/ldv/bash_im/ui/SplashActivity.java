package com.ldv.bash_im.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.ldv.bash_im.MainActivity_;
import com.ldv.bash_im.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;


@EActivity(R.layout.splash_activity)
public class SplashActivity extends AppCompatActivity {


    public final int SPLASH_DISPLAY_LENGTH = 800;

    @AfterViews
    void ready(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                    Intent mainIntent = new Intent(SplashActivity.this, MainActivity_.class);
                    SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }


}
