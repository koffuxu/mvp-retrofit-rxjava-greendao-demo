package com.example.mvp_demo.model;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * *Created by Steven Sun on 2016/10/22.
 * Email:tyhj_sf@163.com
 * 网络访问接口
 */
public interface Server {
    @GET("account")
    Observable<String> uploadUser(@Query("username") User user);

    @GET("query")
    Observable<User> queryUser(@Query("username") String username, @Query("pw") String password);
}
