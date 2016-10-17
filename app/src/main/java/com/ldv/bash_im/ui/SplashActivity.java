package com.ldv.bash_im.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.ldv.bash_im.MainActivity_;
import com.ldv.bash_im.R;
import com.ldv.bash_im.rest.NetworkStatusChecker;
import com.ldv.bash_im.rest.StoriesModel;
import com.ldv.bash_im.ui.entities.StoriesEntity;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;


import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.NonConfigurationInstance;

import java.util.List;


@EActivity(R.layout.splash_activity)
public class SplashActivity extends AppCompatActivity {

    public final String LOG_TAG = "LOGGGG";
    public final int SPLASH_DISPLAY_LENGTH = 1000;

    @NonConfigurationInstance
    @Bean
    BackgroundTask task;

    @AfterViews
    void ready() {

       checkInternet();
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


   public void updateDB(final List<StoriesModel> storiesEntities) {
       FlowManager.getDatabase(StoriesDatabase.class).executeTransaction(new ITransaction() {
           @Override
           public void execute(DatabaseWrapper databaseWrapper) {
               if (StoriesEntity.selectAll().isEmpty()) {
                   for (StoriesModel stories : storiesEntities) {
                       StoriesEntity story = new StoriesEntity(stories.getName(), stories.getSite(),
                               stories.getDesc(), stories.getLink(), stories.getElementPureHtml(), false);
                       story.save();
                   }
               } else if (StoriesEntity.selectAll().size() != 0) {
                   for (StoriesModel stories : storiesEntities) {
                       String link = stories.getLink();
                       StoriesEntity tr = StoriesEntity.selectBylink(link);


                       try { //если нашли с такой ссылкой обьект в базе, просто переписываем ему все кроме favorite
                           tr.setName(stories.getName());
                           tr.setSite(stories.getSite());
                           tr.setDesc(stories.getDesc());
                           tr.setLink(stories.getLink());
                           tr.setElementPureHtml(stories.getElementPureHtml());
                           tr.save();
                       } catch (NullPointerException e) { //если такого обьекта нет, создаем новый
                           StoriesEntity storiesEntity = new StoriesEntity(stories.getName(), stories.getSite(),
                                   stories.getDesc(), stories.getLink(), stories.getElementPureHtml(), false);
                           storiesEntity.save();
                       }
                   }

               }
           }
       });
   }







    void checkInternet(){
        NetworkStatusChecker networkStatusChecker = new NetworkStatusChecker();
        boolean internet = networkStatusChecker.isNetworkAvailable(getApplicationContext());
        if (internet==false) {
            Toast.makeText(getApplicationContext(), R.string.no_internet,Toast.LENGTH_SHORT).show();
        }
        else {
            task.setStories();
        }
    }

}