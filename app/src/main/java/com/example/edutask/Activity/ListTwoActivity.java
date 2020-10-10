package com.example.edutask.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.edutask.Adapter.ListTwoAdapter;
import com.example.edutask.R;
import com.example.edutask.ResponseModel.ListTwoResponseModel;
import com.example.edutask.RestConnection.ApiInterface;
import com.example.edutask.RestConnection.BaseUrl;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTwoActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView listTwoRecycler;
    ListTwoAdapter listTwoAdapter;
    List<ListTwoResponseModel> listTwoResponseModelList;

    SwipeRefreshLayout swipe_refresh_layout_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Graphical Interface");
        setContentView(R.layout.activity_list_two);

        listTwoRecycler = findViewById(R.id.listTwoRecycler);
        listTwoRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        swipe_refresh_layout_two = findViewById(R.id.swipe_refresh_layout_two);
        swipe_refresh_layout_two.setOnRefreshListener(this);
        swipe_refresh_layout_two.setColorSchemeResources(R.color.primaryColor);

        BarChart chart = findViewById(R.id.barChart);

        ArrayList noOfDateAndAmount = new ArrayList();

        noOfDateAndAmount.add(new BarEntry(945f,0));
        noOfDateAndAmount.add(new BarEntry(1040f, 1));

        ArrayList year = new ArrayList();

        year.add("2018");
        year.add("2019");

        BarDataSet bardataset = new BarDataSet(noOfDateAndAmount, "No Of Date and Amount");
        chart.animateY(12000);
        BarData data = new BarData(year, bardataset);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.setData(data);

        getData();

    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(ListTwoActivity.this, ListOneActivity.class));
    }

    public void getData(){

        swipe_refresh_layout_two.setRefreshing(true);

        String dl="1";
        ApiInterface apiInterface = BaseUrl.getClient(this).create(ApiInterface.class);
        Call<List<ListTwoResponseModel>> call = apiInterface.getDateAmount(dl);
        call.enqueue(new Callback<List<ListTwoResponseModel>>() {
            @Override
            public void onResponse(Call<List<ListTwoResponseModel>> call, Response<List<ListTwoResponseModel>> response) {
                listTwoResponseModelList = response.body();
                if (response.isSuccessful()){
                    listTwoAdapter = new ListTwoAdapter(listTwoResponseModelList, ListTwoActivity.this, ListTwoActivity.this);
                    listTwoRecycler.setAdapter(listTwoAdapter);

                    swipe_refresh_layout_two.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<List<ListTwoResponseModel>> call, Throwable t) {
                Toast.makeText(ListTwoActivity.this, t+"", Toast.LENGTH_SHORT).show();
                swipe_refresh_layout_two.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {

        getData();
    }
}