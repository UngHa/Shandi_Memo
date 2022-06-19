package com.example.shandi_memo;

//일정 리사이클러뷰에 사용될 일정 아이템
public class GetPlanInf {

    String title;
    String day;
    String month;
    String text;

    public GetPlanInf() {
    }

    public GetPlanInf(String title, String day, String month, String text) {
        this.title = title;
        this.day = day;
        this.month = month;
        this.text = text;
    }

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
}