package com.example.fsc;

public class Grade {
    private String name;
    private String grade;   //成绩
    private String classes; //班级
    private String stdid;   //学号

    public Grade(String name, String classes, String stdid,String grade){
        this.name=name;
        this.classes=classes;
        this.stdid=stdid;
        this.grade=grade;
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

    public String getGrade(){
        return grade;
    }

}