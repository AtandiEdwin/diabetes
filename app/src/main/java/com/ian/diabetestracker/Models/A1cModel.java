package com.ian.diabetestracker.Models;

public class A1cModel {
    private String A1c_concentration,A1c_uom,A1c_date,A1c_time,A1c_notes;

    public A1cModel(String a1c_concentration, String a1c_uom, String a1c_date, String a1c_time, String a1c_notes) {
        A1c_concentration = a1c_concentration;
        A1c_uom = a1c_uom;
        A1c_date = a1c_date;
        A1c_time = a1c_time;
        A1c_notes = a1c_notes;
    }

    public A1cModel() {
    }

    public String getA1c_concentration() {
        return A1c_concentration;
    }

    public void setA1c_concentration(String a1c_concentration) {
        A1c_concentration = a1c_concentration;
    }

    public String getA1c_uom() {
        return A1c_uom;
    }

    public void setA1c_uom(String a1c_uom) {
        A1c_uom = a1c_uom;
    }

    public String getA1c_date() {
        return A1c_date;
    }

    public void setA1c_date(String a1c_date) {
        A1c_date = a1c_date;
    }

    public String getA1c_time() {
        return A1c_time;
    }

    public void setA1c_time(String a1c_time) {
        A1c_time = a1c_time;
    }

    public String getA1c_notes() {
        return A1c_notes;
    }

    public void setA1c_notes(String a1c_notes) {
        A1c_notes = a1c_notes;
    }
}
