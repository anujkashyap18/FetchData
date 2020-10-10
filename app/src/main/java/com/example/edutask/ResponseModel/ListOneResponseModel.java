package com.example.edutask.ResponseModel;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class ListOneResponseModel implements Serializable {

    @SerializedName("page")
    public String page;

    @SerializedName("per_page")
    public String per_page;

    @SerializedName("total")
    public String total;

    @SerializedName("total_pages")
    public String total_pages;

    @SerializedName("data")
    public List<All_Data> data;

    public class All_Data{

        @SerializedName("id")
        public String id;

        @SerializedName("name")
        public String name;

        @SerializedName("email")
        public String email;


    }

}