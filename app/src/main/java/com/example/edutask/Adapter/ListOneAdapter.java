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


import com.example.edutask.Activity.ListTwoActivity;
import com.example.edutask.R;
import com.example.edutask.ResponseModel.ListOneResponseModel;

import java.util.List;

public class ListOneAdapter extends RecyclerView.Adapter<ListOneAdapter.PostViewHolder>{

 Context context;
Activity activity;
List<ListOneResponseModel.All_Data> details;


public ListOneAdapter(List<ListOneResponseModel.All_Data> details, Context context, Activity activity) {
        this.context = context;
        this.details = details;
        this.activity=activity;
        }

    @NonNull
    @Override
    public ListOneAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout_for_list_one,parent,false);
        return new ListOneAdapter.PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListOneAdapter.PostViewHolder holder, int position) {

    holder.listOneID.setText(details.get(position).id);
    holder.name.setText(details.get(position).name);
    holder.email.setText(details.get(position).email);
    holder.listOneOuter.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            context.startActivity(new Intent(context, ListTwoActivity.class));

        }
    });

    }

    @Override
    public int getItemCount() {

        if(details.size()>10){
            return 10;
        }else{
            return details.size();
        }
    }

    public class PostViewHolder extends RecyclerView.ViewHolder{

        TextView listOneID,name,email;
        LinearLayout listOneOuter;
        public PostViewHolder(View itemView) {
            super(itemView);

            listOneID = itemView.findViewById(R.id.listOneID);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            listOneOuter = itemView.findViewById(R.id.listOneOuter);
        }

    }

}
