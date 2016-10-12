package com.ldv.bash_im.rest;

import com.ldv.bash_im.ui.entities.StoriesEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface UmoroliApi {

    @GET ("get")

    Call<List<StoriesModel>> get_stories (@Query("site") String site,
                                     @Query("name") String name,
                                     @Query("num") int num);
}
