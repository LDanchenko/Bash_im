package com.ldv.bash_im.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.ldv.bash_im.MainActivity_;
import com.ldv.bash_im.R;
import com.ldv.bash_im.rest.NetworkStatusChecker;
import com.ldv.bash_im.rest.RestService;
import com.ldv.bash_im.ui.entities.StoriesEntity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@EActivity(R.layout.splash_activity)
public class SplashActivity extends AppCompatActivity {

    public final String LOG_TAG = "LOGGGG";
    public final int SPLASH_DISPLAY_LENGTH = 800;

    @NonConfigurationInstance
    @Bean
    BackgroundTask task;


    @ViewById
    TextView textView1;
    @AfterViews
    void ready() {

        NetworkStatusChecker networkStatusChecker = new NetworkStatusChecker();
        boolean internet = networkStatusChecker.isNetworkAvailable(getApplicationContext());
        if (internet==false) {
            Toast.makeText(getApplicationContext(), R.string.no_internet,Toast.LENGTH_SHORT).show();
        }
        else {
            task.setStories();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                    Intent mainIntent = new Intent(SplashActivity.this, MainActivity_.class);
                    SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }

    public void UnknownError(){ //неизвестная ошибка
        Toast.makeText(getApplicationContext(), R.string.unknown_error, Toast.LENGTH_SHORT).show();
    }

    public void updateDB(List<StoriesEntity> storiesEntities){
        if (StoriesEntity.selectAll().isEmpty()){
            for (StoriesEntity stori : storiesEntities) {

                StoriesEntity stor = new StoriesEntity(stori.getName(), stori.getSite(),
                        stori.getDesc(), stori.getLink(), stori.getElementPureHtml(), false);
                stor.save();
            }
        }
    }

/*
    public void showResult(String name, String link, String text) {
        //storiesModels.
       //Toast.makeText(getApplicationContext(),"RESULT "+ name + "  " + link , Toast.LENGTH_SHORT).show();
        textView1.setText(" name: " + name +  "  link:" + link + "  text" + Html.fromHtml(text));
    }

    public void UnknownError(){ //неизвестная ошибка
        Toast.makeText(getApplicationContext(), R.string.unknown_error, Toast.LENGTH_SHORT).show();
    }


 /*   public void showTResult(String name, String link, String text){
        StoriesEntity storiesEntity = new StoriesEntity();
        storiesEntity.setName(name);
       // storiesEntity.setElementPureHtml("ff");
        storiesEntity.setLink(link);
        storiesEntity.setElementPureHtml(text);
        storiesEntity.save();
    }*/
/*
    void checkInternet(){
        NetworkStatusChecker networkStatusChecker = new NetworkStatusChecker();
        boolean internet = networkStatusChecker.isNetworkAvailable(getApplicationContext());
        if (internet==false) {
            Toast.makeText(getApplicationContext(), R.string.no_internet,Toast.LENGTH_SHORT).show();
        }
        else {
            task.getStories();
        }
    }*/
/*
    public void setStories(){
        RestService restService = new RestService();
        Call<List<StoriesEntity>> storiesModel = restService.get_story("bash.im", "bash", 70);
        storiesModel.enqueue(new Callback<List<StoriesEntity>>() {
            @Override
            public void onResponse(Call<List<StoriesEntity>> call, Response<List<StoriesEntity>> response) {
                if(response.isSuccessful()) {

                    List<StoriesEntity> storiesEntities = response.body();

                    if (StoriesEntity.selectAll().isEmpty()){
                        for (StoriesEntity stori : storiesEntities) {
                            StoriesEntity ty = new StoriesEntity();

                            StoriesEntity stor = new StoriesEntity(stori.getName(), stori.getSite(),
                                    stori.getDesc(), stori.getLink(), stori.getElementPureHtml(), false);
                            stor.save();
                        }
                    }
                    //  StoriesAdapter storiesAdapter = new StoriesAdapter(storiesEntities);
                    //recyclerView.setAdapter(storiesAdapter);
                }}


            @Override
            public void onFailure(Call<List<StoriesEntity>> call, Throwable t) {

            }
        });
    }
*/
}