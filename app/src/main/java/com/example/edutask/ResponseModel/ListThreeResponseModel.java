package com.example.edutask.ResponseModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ListThreeResponseModel implements Serializable {

    @SerializedName("title")
    public String title;

    @SerializedName("content")
    public String content;
}
