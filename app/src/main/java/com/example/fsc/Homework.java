package com.example.fsc;

import android.webkit.WebChromeClient;

public class Homework {

    private String classes; //班级
    private String homework;   //学号

    public Homework(String classes, String homework){
        this.classes=classes;
        this.homework=homework;
    }


    public String getClasses()
    {
        return classes;
    }

    public String getHomework(){
        return homework;
    }

}