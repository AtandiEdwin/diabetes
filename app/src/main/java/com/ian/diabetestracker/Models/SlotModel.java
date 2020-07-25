package com.ian.diabetestracker.Models;

public class SlotModel {

    public String stitle;
    public String stime;
    public SlotModel() {
    }

    public SlotModel(String stitle, String stime) {
        this.stitle = stitle;
        this.stime = stime;
    }

    public String getStitle() {
        return stitle;
    }

    public void setStitle(String stitle) {
        this.stitle = stitle;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }
}
