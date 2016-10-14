package com.ldv.bash_im.rest;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    public static final String BASE_URL = "http://www.umori.li/api/"; //доменное имя сервера
    private  UmoroliApi umoriliApi; //создаем обьект интерфейса - запрос

    //в конструкторе нужно сконфигурировать ретрофит
    public RestClient() {

        //конфигурируем логин интерсептор (перехватчик)
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        //конфигурируем хттп клиент
        OkHttpClient okHttpClient = new OkHttpClient.Builder() //чтоб при каждом запросе видить логи!
                .addInterceptor(logging)
                .build();

        //конфигурируем ретрофит
        Retrofit retrofit = new Retrofit.Builder()//в билдер ретрофита передаем
                .baseUrl(BASE_URL)//адрес сервера по которому будем формироваться запрос
                .client(okHttpClient)//передали логининтерсептор
                .addConverterFactory(GsonConverterFactory.create())//мост который говорить gson распарсить json на модель
                .build();

        umoriliApi = retrofit.create(UmoroliApi.class); //запрос связали с ретрофитом
    }

    public UmoroliApi getUmoriliApi() {
        return umoriliApi;
    }
}
