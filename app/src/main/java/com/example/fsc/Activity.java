package com.example.fsc;

public class Activity {
    private String classes;
    private String title;
    private String activity_content;


    public Activity(String classes, String title,String activity_content){
        this.classes=classes;
        this.title=title;
        this.activity_content=activity_content;
    }


    public String getClasses()
    {
        return classes;
    }

    public String getTitle(){
        return title;
    }

    public String getActivity_content(){
        return activity_content;
    }

}
