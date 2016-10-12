package com.ldv.bash_im.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ldv.bash_im.MainActivity_;
import com.ldv.bash_im.R;
import com.ldv.bash_im.rest.NetworkStatusChecker;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.NonConfigurationInstance;


@EActivity(R.layout.splash_activity)
public class SplashActivity extends AppCompatActivity {


    public final int SPLASH_DISPLAY_LENGTH = 800;

    @NonConfigurationInstance
    @Bean
    BackgroundTask task;

    @AfterViews
    void ready() {

        checkInternet();

/*        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                    Intent mainIntent = new Intent(SplashActivity.this, MainActivity_.class);
                    SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
*/
    }


    public void showResult(String result) {
        Toast.makeText(getApplicationContext(),"RESULT "+ result , Toast.LENGTH_SHORT).show();
    }

    public void UnknownError(){ //неизвестная ошибка
        Toast.makeText(getApplicationContext(), R.string.unknown_error, Toast.LENGTH_SHORT).show();
    }


    void checkInternet(){
        NetworkStatusChecker networkStatusChecker = new NetworkStatusChecker();
        boolean internet = networkStatusChecker.isNetworkAvailable(getApplicationContext());
        if (internet==false) {
            Toast.makeText(getApplicationContext(), R.string.no_internet,Toast.LENGTH_SHORT).show();
        }
        else {
            task.getStories();
        }
    }
}