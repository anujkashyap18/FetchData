package com.example.edutask.RestConnection;

import com.example.edutask.ResponseModel.ListOneResponseModel;
import com.example.edutask.ResponseModel.ListThreeResponseModel;
import com.example.edutask.ResponseModel.ListTwoResponseModel;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("s/1hh8vh7whv6cjme/list1.json")
    Call<ListOneResponseModel> getDetails(@Query("dl") String dl);

    @GET("s/n4i57r22rdx89cw/list2.json")
    Call<List<ListTwoResponseModel>> getDateAmount(@Query("dl") String dl);

    @GET("s/ep7v5yex3fjs3s1/webview.json?dl=1")
    Call<List<ListThreeResponseModel>> getContent(@Query("dl") String dl);



}