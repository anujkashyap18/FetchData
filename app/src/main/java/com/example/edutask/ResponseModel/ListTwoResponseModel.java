package com.example.edutask.ResponseModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ListTwoResponseModel implements Serializable {

    @SerializedName("date_created")
    public String date_created;

    @SerializedName("amount")
    public String amount;

}
