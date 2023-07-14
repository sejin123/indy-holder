package com.example.app;

import android.app.Application;

import kr.co.bdgen.indywrapper.IndyWrapper;

public class MyApplicatrion extends Application {

    @Override
    public void onCreate()  { /*보통 여기에 초기화 구문 넣음*/
        super.onCreate();
        IndyWrapper.init(this);
    }
}
