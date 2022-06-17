package com.example.shandi_memo;

public class MatchingItem {
    String profile;
    String planName;
    String planDate;
    String planText;

    public MatchingItem(String planName, String planDate, String planText){
        this.planName = planName;
        this.planDate = planDate;
        this.planText = planText;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanDate() {
        return planDate;
    }

    public void setPlanDate(String planDate) {
        this.planDate = planDate;
    }

    public String getPlanText() {
        return planText;
    }

    public void setPlanText(String planText) {
        this.planText = planText;
    }
}
