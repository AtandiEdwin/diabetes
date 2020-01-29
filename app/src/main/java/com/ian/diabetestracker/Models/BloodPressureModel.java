package com.ian.diabetestracker.Models;

public class BloodPressureModel {
    private String systolic,diastolic,pulse,arm,date,time,note;

    public BloodPressureModel(String systolic, String diastolic, String pulse, String arm, String date, String time, String note) {
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.pulse = pulse;
        this.arm = arm;
        this.date = date;
        this.time = time;
        this.note = note;
    }

    public BloodPressureModel() {
    }

    public String getSystolic() {
        return systolic;
    }

    public void setSystolic(String systolic) {
        this.systolic = systolic;
    }

    public String getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(String diastolic) {
        this.diastolic = diastolic;
    }

    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
    }

    public String getArm() {
        return arm;
    }

    public void setArm(String arm) {
        this.arm = arm;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
