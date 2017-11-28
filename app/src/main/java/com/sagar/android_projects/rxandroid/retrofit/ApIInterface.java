package com.sagar.android_projects.rxandroid.retrofit;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sagar on 11/27/2017.
 */

public interface ApIInterface {

    @GET("/api/users")
    Observable<com.sagar.android_projects.rxandroid.retrofit.models.Response> getResponse(@Query("page") String page);
}
