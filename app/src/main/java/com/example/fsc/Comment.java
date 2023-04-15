package com.example.fsc;

public class Comment {
    private String name;
    private String comment;   //评语
    private String classes; //班级
    private String stdid;   //学号

    public Comment(String name, String classes, String stdid,String comment){
        this.name=name;
        this.classes=classes;
        this.stdid=stdid;
        this.comment=comment;
    }

    public String getName(){
        return name;
    }

    public String getClasses()
    {
        return classes;
    }

    public String getStdid(){
        return stdid;
    }

    public String getComment(){
        return comment;
    }

}