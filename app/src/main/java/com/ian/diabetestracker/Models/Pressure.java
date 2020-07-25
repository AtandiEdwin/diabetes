package com.ian.diabetestracker.Models;

//import com.google.gson.annotations.SerializedName;

public class Pressure {
//    @SerializedName("p_date")
    private int day;
//    @SerializedName("pulse")
    private int pulse;

    public int getDay() {
        return day;
    }

    public int getPulse() {
        return pulse;
    }
}
