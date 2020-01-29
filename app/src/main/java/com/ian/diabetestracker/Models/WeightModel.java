package com.ian.diabetestracker.Models;

public class WeightModel {
    private String w_value,w_date,w_time,w_notes;

    public WeightModel(String w_value, String w_date, String w_time, String w_notes) {
        this.w_value = w_value;
        this.w_date = w_date;
        this.w_time = w_time;
        this.w_notes = w_notes;
    }

    public WeightModel() {
    }

    public String getW_value() {
        return w_value;
    }

    public void setW_value(String w_value) {
        this.w_value = w_value;
    }

    public String getW_date() {
        return w_date;
    }

    public void setW_date(String w_date) {
        this.w_date = w_date;
    }

    public String getW_time() {
        return w_time;
    }

    public void setW_time(String w_time) {
        this.w_time = w_time;
    }

    public String getW_notes() {
        return w_notes;
    }

    public void setW_notes(String w_notes) {
        this.w_notes = w_notes;
    }

}
