package com.example.fsc;

public class Notice {

    private String classes;
    private String notice_content;


    public Notice(String classes, String notice_content){
        this.classes=classes;
        this.notice_content=notice_content;
    }


    public String getClasses()
    {
        return classes;
    }


    public String getNotice_content(){
        return notice_content;
    }

}
