package com.ldv.bash_im.ui;

/**
 * Created by user on 11.10.2016.
 */

import com.ldv.bash_im.rest.RestService;
import com.ldv.bash_im.rest.StoriesModel;
import com.ldv.bash_im.ui.database.DataManager;



import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ldv.bash_im.ui.ConstantsManager.NAME;
import static com.ldv.bash_im.ui.ConstantsManager.NUM;
import static com.ldv.bash_im.ui.ConstantsManager.SITE;


@EBean
public class BackgroundTask {
    @Bean
    DataManager task;


    @RootContext
    SplashActivity splashActivity;

        @Background
        public void setStories(){
                RestService restService = new RestService();
                Call<List<StoriesModel>> storiesModel = restService.getStory(SITE, NAME, NUM);
                    storiesModel.enqueue(new Callback<List<StoriesModel>>() {
        @Override
        public void onResponse(Call<List<StoriesModel>> call, Response<List<StoriesModel>> response) {
            if(response.isSuccessful()) {

                final List<StoriesModel> storiesEntities = response.body();
                task.loadQuotes(storiesEntities);
            }}

        @Override
        public void onFailure(Call<List<StoriesModel>> call, Throwable t) {
        UnknownError();
        }
    });
}



    @UiThread
   void UnknownError(){
        splashActivity.UnknownError();
    }

}


