package com.example.edutask.RestConnection;

import java.io.IOException;

public class NoConnectivityException extends IOException {

    public String getMessage(){
        return "No Internet Connection";
    }

}