package com.example.fsc;

public class Student {

    private String name;
    private String stdid;
    private String classes;
    private String std_password;
    private String parent_tel;


    public Student(String name, String stdid, String classes, String std_password,String parent_tel){

        this.name=name;
        this.stdid=stdid;
        this.classes=classes;
        this.std_password=std_password;
        this.parent_tel=parent_tel;
    }

    public String getName(){
        return name;
    }

    public String getStdid()
    {
        return stdid;
    }

    public String getClasses()
    {
        return classes;
    }

    public String getStd_password()
    {
        return std_password;
    }

    public String getParent_tel()
    {
        return parent_tel;
    }

}
