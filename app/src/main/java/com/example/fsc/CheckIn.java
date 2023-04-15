package com.example.fsc;

public class CheckIn {

    private String classes;   //班级
    private String state;
    private String name;    //姓名
    private String stdid;

    public CheckIn(String name, String state, String classes, String stdid){

        this.classes=classes;
        this.state=state;
        this.stdid=stdid;
        this.name=name;
    }


    public String getClasses()
    {
        return classes;
    }

    public String getName(){
        return name;
    }

    public String getState()
    {
        return state;
    }

    public String getStdid()
    {
        return stdid;
    }


}
