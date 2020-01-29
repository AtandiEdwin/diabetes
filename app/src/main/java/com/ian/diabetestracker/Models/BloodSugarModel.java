package com.ian.diabetestracker.Models;

public class BloodSugarModel {
   private String whenMeasured,unitofMeasure,notes,date,time;

    public BloodSugarModel(String whenMeasured, String unitofMeasure, String notes, String date, String time) {
        this.whenMeasured = whenMeasured;
        this.unitofMeasure = unitofMeasure;
        this.notes = notes;
        this.date = date;
        this.time = time;
    }

    public BloodSugarModel() {
    }

    public String getWhenMeasured() {
        return whenMeasured;
    }

    public void setWhenMeasured(String whenMeasured) {
        this.whenMeasured = whenMeasured;
    }

    public String getUnitofMeasure() {
        return unitofMeasure;
    }

    public void setUnitofMeasure(String unitofMeasure) {
        this.unitofMeasure = unitofMeasure;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
