package com.example.myapplication;



public class Goals {

    String id;
    private String goal;
    private String startDate;
    private String endDate;

    public Goals() { }

    public Goals(String id,String goal, String startDate, String endDate) {
        this.id = id;
        this.goal = goal;
        this.startDate = startDate;
        this.endDate = endDate;
    }

//    public Goals(String goal, String startDate, String endDate) {
//        this.goal = goal;
//        this.startDate = startDate;
//        this.endDate = endDate;
//    }


    public Goals(String id, String goal, String endDate) {
        this.id = id;
        this.goal = goal;
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
