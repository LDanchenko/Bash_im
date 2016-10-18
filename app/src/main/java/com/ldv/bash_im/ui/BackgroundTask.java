package com.ldv.bash_im.ui;

/**
 * Created by user on 11.10.2016.
 */

import com.ldv.bash_im.rest.RestService;
import com.ldv.bash_im.rest.StoriesModel;
import com.ldv.bash_im.ui.database.DataManager;
import com.ldv.bash_im.ui.database.StoriesDatabase;
import com.ldv.bash_im.ui.database.entities.StoriesEntity;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;


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

    DataManager task;


    private final String LOG_TAG = "Ответ запроса";
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
                //task.loadQuotes(storiesEntities);
                FlowManager.getDatabase(StoriesDatabase.class).executeTransaction(new ITransaction() {
                    @Override
                    public void execute(DatabaseWrapper databaseWrapper) {
                        for (StoriesModel quote : storiesEntities) {
                            StoriesEntity quoteEntity = new StoriesEntity();
                            quoteEntity.setId(quote.getLink());
                            quoteEntity.setElementPureHtml(quote.getElementPureHtml());
                            quoteEntity.setFavorite(false);
                            quoteEntity.save(databaseWrapper);
                        }
                    }
                });

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


