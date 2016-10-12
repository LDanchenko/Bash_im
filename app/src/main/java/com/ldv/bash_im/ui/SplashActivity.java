package com.ldv.bash_im.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ldv.bash_im.MainActivity_;
import com.ldv.bash_im.R;
import com.ldv.bash_im.rest.NetworkStatusChecker;
import com.ldv.bash_im.rest.StoriesModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.ViewById;

import java.util.List;


@EActivity(R.layout.splash_activity)
public class SplashActivity extends AppCompatActivity {


    public final int SPLASH_DISPLAY_LENGTH = 800;

    @NonConfigurationInstance
    @Bean
    BackgroundTask task;


    @ViewById
    TextView textView1;
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


    public void showResult(String name, String link, String text) {
        //storiesModels.
       //Toast.makeText(getApplicationContext(),"RESULT "+ name + "  " + link , Toast.LENGTH_SHORT).show();
        textView1.setText(" name: " + name +  "  link:" + link + "  text" + text);
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