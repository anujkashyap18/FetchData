package com.example.edutask.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edutask.Adapter.ListThreeAdapter;
import com.example.edutask.Adapter.ListTwoAdapter;
import com.example.edutask.R;
import com.example.edutask.ResponseModel.ListThreeResponseModel;
import com.example.edutask.ResponseModel.ListTwoResponseModel;
import com.example.edutask.RestConnection.ApiInterface;
import com.example.edutask.RestConnection.BaseUrl;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListThreeActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView listThreeRecycler;
    List<ListThreeResponseModel> listThreeResponseModelList;
    ListThreeAdapter listThreeAdapter;

    SwipeRefreshLayout swipe_refresh_layout_three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_list_three);

        listThreeRecycler = findViewById(R.id.listThreeRecycler);
        listThreeRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        swipe_refresh_layout_three = findViewById(R.id.swipe_refresh_layout_three);
        swipe_refresh_layout_three.setOnRefreshListener(this);
        swipe_refresh_layout_three.setColorSchemeResources(R.color.primaryColor);


        getView();

    }

    public void getView(){

        swipe_refresh_layout_three.setRefreshing(true);

        String dl="1";
        ApiInterface apiInterface = BaseUrl.getClient(this).create(ApiInterface.class);
        Call<List<ListThreeResponseModel>> call = apiInterface.getContent(dl);
        call.enqueue(new Callback<List<ListThreeResponseModel>>() {
            @Override
            public void onResponse(Call<List<ListThreeResponseModel>> call, Response<List<ListThreeResponseModel>> response) {
                //Toast.makeText(ListThreeActivity.this, "Success", Toast.LENGTH_SHORT).show();
                listThreeResponseModelList = response.body();
                if (response.isSuccessful()){
                    listThreeAdapter = new ListThreeAdapter(listThreeResponseModelList, ListThreeActivity.this, ListThreeActivity.this);
                    listThreeRecycler.setAdapter(listThreeAdapter);

                    swipe_refresh_layout_three.setRefreshing(false);
                }
                else{
                    Toast.makeText(ListThreeActivity.this, "Api Problem", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ListThreeResponseModel>> call, Throwable t) {
                Toast.makeText(ListThreeActivity.this, t+"", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRefresh() {

        getView();
    }
}