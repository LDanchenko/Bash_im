package com.ldv.bash_im.ui;

/**
 * Created by user on 11.10.2016.
 */

import com.ldv.bash_im.MainActivity;
import com.ldv.bash_im.rest.RestService;
import com.ldv.bash_im.ui.entities.StoriesEntity;


import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@EBean
public class BackgroundTask {


    private final String LOG_TAG = "Ответ запроса";
    @RootContext
    SplashActivity mainActivity;

        @Background
        public void setStories(){
                RestService restService = new RestService();
                Call<List<StoriesEntity>> storiesModel = restService.get_story("bash.im", "bash", 100);
                storiesModel.enqueue(new Callback<List<StoriesEntity>>() {
        @Override
        public void onResponse(Call<List<StoriesEntity>> call, Response<List<StoriesEntity>> response) {
            if(response.isSuccessful()) {

                List<StoriesEntity> storiesEntities = response.body();

               updateRegistrationUI(storiesEntities);

            }}

        @Override
        public void onFailure(Call<List<StoriesEntity>> call, Throwable t) {
        UnknownError();
        }
    });
}

    @UiThread

   void updateRegistrationUI(List<StoriesEntity> storiesEntities) {
        mainActivity.updateDB(storiesEntities);
    }

    @UiThread
   void UnknownError(){
        mainActivity.UnknownError();
    }

}


