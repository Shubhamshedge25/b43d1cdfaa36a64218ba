package com.example.builder_task.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface api {

    @GET("users?")//endpoint

    Call<ResponseBody> countries(@Query("page") int pages);
}
