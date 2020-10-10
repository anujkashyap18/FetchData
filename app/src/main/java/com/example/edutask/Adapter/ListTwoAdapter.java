package com.example.edutask.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edutask.Activity.ListThreeActivity;
import com.example.edutask.R;
import com.example.edutask.ResponseModel.ListTwoResponseModel;

import java.util.List;

public class ListTwoAdapter extends RecyclerView.Adapter<ListTwoAdapter.PostViewHolder>{

    Context context;
    Activity activity;
    List<ListTwoResponseModel>  listTwoResponseModelList;


    public ListTwoAdapter(List<ListTwoResponseModel>  listTwoResponseModelList, Context context, Activity activity) {
        this.context = context;
        this.listTwoResponseModelList = listTwoResponseModelList;
        this.activity=activity;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout_for_list_two,parent,false);
        return new ListTwoAdapter.PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {

        holder.date_created.setText(listTwoResponseModelList.get(position).date_created);
        holder.amount.setText(listTwoResponseModelList.get(position).amount);
        holder.listTwoOuter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ListThreeActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {

       return listTwoResponseModelList.size();

    }

    public class PostViewHolder extends RecyclerView.ViewHolder{

        TextView date_created,amount;
        LinearLayout listTwoOuter;
        public PostViewHolder(View itemView) {
            super(itemView);

            date_created = itemView.findViewById(R.id.date_created);
            amount = itemView.findViewById(R.id.amount);
            listTwoOuter = itemView.findViewById(R.id.listTwoOuter);
        }

    }

}
