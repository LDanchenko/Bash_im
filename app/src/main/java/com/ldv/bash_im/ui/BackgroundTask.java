package com.ldv.bash_im.ui;

/**
 * Created by user on 11.10.2016.
 */

import android.database.Cursor;
import android.util.Log;
import android.widget.EditText;

import com.ldv.bash_im.MainActivity;
import com.ldv.bash_im.rest.RestService;
import com.ldv.bash_im.rest.StoriesModel;
import com.ldv.bash_im.ui.entities.StoriesEntity;
import com.ldv.bash_im.ui.fragments.StoriesFragment;


import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@EBean
public class BackgroundTask {


    private final String LOG_TAG = "Ответ запроса";
    @RootContext
    StoriesFragment mainActivity;


    @Background
    public void getStories()  {
        RestService restService = new RestService(); //там все связывается и реализуется
        //Сам запрос
        //кол инкапсулирует модель в которую передали результат запроса
        Call<List<StoriesEntity>> storiesModel = restService.get_story("bash.im", "bash", 4);
        storiesModel.enqueue(new Callback<List<StoriesEntity>>() {
            @Override
            public void onResponse(Call<List<StoriesEntity>> call, Response<List<StoriesEntity>> response) {
               if(response.isSuccessful()) {

                   List<StoriesEntity> stories_data = response.body();
//                StoriesEntity.save(response.body());


                for (int i = 0; i < stories_data.size(); i++) {
                    String name = stories_data.get(i).getName();

                    String link = "";
                    String text = "";
                    // String link = stories_data.get(i).getLink();
                    //String text = stories_data.get(i).getElementPureHtml();

                    updateRegistrationUI(stories_data);
                }
                }
            }

            @Override
            public void onFailure(Call<List<StoriesEntity>> call, Throwable t) {

            }

        });
    }
       /* try {

           //Синхронный запрос
            storiesModel.
           // storiesModel.get(1);
           // String result = storiesModel.getClass().getName();
           // Log.d(LOG_TAG, "Status: " + result);
           // updateRegistrationUI(storiesModel);//передаем в функицю, коротая передаетв активити реистрации
        } catch (IOException e) {
//Если запрос не выполнен – сообщаем пользователя об ошибке
            e.printStackTrace();
            UnknownRegistrationError();
        }
    }*/





    // Notice that we manipulate the activity ref only from the UI thread

    @UiThread

    void updateRegistrationUI(List<StoriesEntity> storiesEntities) {
        mainActivity.showResult(storiesEntities);
        //mainActivity.showResult(name,link,text);
    }

    //@UiThread
  //  void UnknownRegistrationError(){
     //  mainActivity.UnknownError();
   // }


}


