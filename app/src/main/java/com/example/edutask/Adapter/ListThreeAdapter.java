package com.example.edutask.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edutask.R;
import com.example.edutask.ResponseModel.ListThreeResponseModel;


import java.util.List;

public class ListThreeAdapter extends RecyclerView.Adapter<ListThreeAdapter.PostViewHolder>{

        Context context;
        Activity activity;
        List<ListThreeResponseModel> listThreeResponseModels;

public ListThreeAdapter(List<ListThreeResponseModel> listThreeResponseModels, Context context, Activity activity) {
        this.context = context;
        this.listThreeResponseModels = listThreeResponseModels;
        this.activity=activity;
        }

        @NonNull
        @Override
        public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout_for_list_three,parent,false);
                return new ListThreeAdapter.PostViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {

                holder.title.setText(listThreeResponseModels.get(position).title);
                holder.web_view.loadData("<html><body>"+listThreeResponseModels.get(position).content+"</body></html>","text/html","UTF-8");

        }

        @Override
        public int getItemCount() {
                return listThreeResponseModels.size();
        }


        public class PostViewHolder extends RecyclerView.ViewHolder{

                TextView title;
                WebView web_view;
                public PostViewHolder(View itemView) {
                        super(itemView);

                        title = itemView.findViewById(R.id.title);
                        web_view = itemView.findViewById(R.id.web_view);
                }

        }

}
