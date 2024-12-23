package com.example.pojarprotektmobileapp.http.base;


import com.example.pojarprotektmobileapp.models.ArtikulModel;

import java.util.List;

public abstract class RequestCallback {


    public abstract void callback(List<ArtikulModel> objects);
}
