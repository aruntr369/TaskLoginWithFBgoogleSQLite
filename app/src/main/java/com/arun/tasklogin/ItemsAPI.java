package com.arun.tasklogin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ItemsAPI {

    @GET("/b/WO6S")
    public Call<List<ItemsModel>> getItemss();
}
