package com.ldv.bash_im.rest;

import android.support.annotation.NonNull;

import org.androidannotations.annotations.EBean;

import java.util.List;

@EBean
public class RestService {

    private RestClient restClient;//инстанс класса, который отвечает за домен,там где все связано

    public RestService(){
        restClient = new RestClient(); //тут инициализируем этот инстанст,потому что private
    }

    //запрос на регистрацию
    public retrofit2.Call<List<StoriesModel>> getStory (@NonNull String site, //создаем инстанс нашей модели для запроса, третий параметр вынесли в константу
                                                          @NonNull String name,
                                                          @NonNull int num) { //IOEXception - чтоб не было трай кеч
        return restClient.getUmoriliApi() //рест клиент - класс где связали запрос,апи и ретрофит.
                // регистрЮзерАпи- тт позвращаем запрос, передаем туда переметры
                .get_stories(site, name, num);
                 //

    }


}
