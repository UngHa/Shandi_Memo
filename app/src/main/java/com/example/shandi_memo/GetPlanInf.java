package com.example.shandi_memo;

public class GetPlanInf {

    String plan;
    String title;
    String day;
    String month;
    String text;

    public GetPlanInf() {
    }
    public String getPlan() {
        return plan;
    }

    public void setplan(String plan) {
        this.plan = plan;
    }

    public String getTitle() {
        return title;
    }

    public void settitle(String title) {
        this.title = title;
    }

    public String getDay() {
        return day;
    }

    public void setday(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setmonth(String month) {
        this.month = month;
    }

    public String getText() {
        return text;
    }

    public void settext(String text) {
        this.text = text;
    }

    public GetPlanInf(String plan, String title, String day, String month, String text) {
        this.plan = plan;
        this.title = title;
        this.day = day;
        this.month = month;
        this.text = text;
    }
}