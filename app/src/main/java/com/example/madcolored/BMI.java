package com.madapplication.bmicalculator;

public class BMI {

    private String date;
    private String height;
    private String weight;
    private String age;

    String id;



    public BMI() {
    }

    public BMI(String id,String date, String height, String weight, String age) {
        this.id = id;
        this.date = date;
        this.height = height;
        this.weight = weight;
        this.age = age;

    }

    public BMI(String id, String height, String weight, String age) {

        this.id=id;
        this.height=height;
        this.weight=weight;
        this.age=age;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }




}
