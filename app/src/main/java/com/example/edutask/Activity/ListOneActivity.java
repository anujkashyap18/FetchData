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
import android.widget.Toast;

import com.example.edutask.Adapter.ListOneAdapter;
import com.example.edutask.R;
import com.example.edutask.ResponseModel.ListOneResponseModel;
import com.example.edutask.RestConnection.ApiInterface;
import com.example.edutask.RestConnection.BaseUrl;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

public class ListOneActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    RecyclerView listOneRecycler;
    ListOneAdapter listOneAdapter;
    ListOneResponseModel listOneResponseModel;
    SwipeRefreshLayout swipeRefreshLayout;
    List<ListOneResponseModel.All_Data> all_dataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_one);

        listOneRecycler = findViewById(R.id.listOneRecycler);
        listOneRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.primaryColor);

        //For Notification

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("MyNotifications", "MyNotifications",
                    NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        FirebaseMessaging.getInstance().subscribeToTopic("g")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Successful";
                        if (!task.isSuccessful()) {
                            msg = "failed";
                        }
                        Toast.makeText(ListOneActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });


        getListOne();

    }

    public void getListOne(){

        swipeRefreshLayout.setRefreshing(true);

        String dl ="1";
        ApiInterface apiInterface = BaseUrl.getClient(this).create(ApiInterface.class);
        //Call<ListOneResponseModel> call = apiInterface.getDetails(dl);
        Call<ListOneResponseModel> call = apiInterface.getDetails(dl);
        call.enqueue(new Callback<ListOneResponseModel>() {
            @Override
            public void onResponse(Call<ListOneResponseModel> call, Response<ListOneResponseModel> response) {
                listOneResponseModel = response.body();
                if (response.isSuccessful()){
                   // Toast.makeText(ListOneActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    all_dataList = response.body().data;
                    listOneAdapter = new ListOneAdapter(all_dataList, ListOneActivity.this, ListOneActivity.this);
                    listOneRecycler.setAdapter(listOneAdapter);

                    swipeRefreshLayout.setRefreshing(false);

                }
            }

            @Override
            public void onFailure(Call<ListOneResponseModel> call, Throwable t) {
                Toast.makeText(ListOneActivity.this, t+"", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRefresh() {

        getListOne();
    }
}