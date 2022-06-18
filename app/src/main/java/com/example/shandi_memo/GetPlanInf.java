package com.example.shandi_memo;

public class GetPlanInf {

    /*String plan;*/
    String title;
    String day;
    String month;
    String text;

    public GetPlanInf() {
    }
    /*public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }*/

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public GetPlanInf(/*String plan, */String title, String day, String month, String text) {
        /*this.plan = plan;*/
        this.title = title;
        this.day = day;
        this.month = month;
        this.text = text;
    }
}