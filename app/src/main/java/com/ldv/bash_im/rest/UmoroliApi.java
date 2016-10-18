package com.ldv.bash_im.rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface UmoroliApi {

    @GET ("get")

    Call<List<StoriesModel>> get_stories (@Query("site") String site,
                                     @Query("name") String name,
                                     @Query("num") int num);
}
