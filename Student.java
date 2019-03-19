package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private static Student student = new Student();
    private Student(){}
    public  static Student getStudent(){
        return student;
    }
    public String stu_id;
    public String name;
    public String id;
    public String sex;
    public String nativePlace;
    public Integer year;
    public Integer month;
    public Integer day;
    public String school;
    public String major;
    public String stuClass;
    public String Cell;
    public String email;
    public String weixin;
    public String info;
    public List<String> skill =new ArrayList<>();
}
