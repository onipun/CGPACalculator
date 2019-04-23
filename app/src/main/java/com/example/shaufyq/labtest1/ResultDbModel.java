package com.example.shaufyq.labtest1;

public class ResultDbModel {

    String subCode = "code";
    String subName = "name";
    String subHour = "hour";
    String subGrade = "grade";

    ResultDbModel(String code, String name, String hour, String grade){
        this.subCode = code;
        this.subName = name;
        this.subHour = hour;
        this.subGrade = grade;
    }

    ResultDbModel(){}

    void setSubGrade(String grade){
        this.subGrade = grade;
    }

    String getSubGrade(){
        return  subGrade;
    }
    void setSubHour(String hour){
        this.subHour = hour;
    }

    String getSubHour(){
        return subHour;
    }
    void setSubName(String name){
        this.subName = name;
    }
    String getSubName(){
        return subName;
    }
    void setSubCode(String code){
        this.subCode = code;
    }

    String getSubCode(){
        return subCode;
    }

}
