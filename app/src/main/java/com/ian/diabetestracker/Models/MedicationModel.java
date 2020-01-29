package com.ian.diabetestracker.Models;

public class MedicationModel {
    private String m_value,m_dosage,m_uom,m_date,m_time,m_notes;

    public MedicationModel(String m_value, String m_dosage, String m_uom, String m_date, String m_time, String m_notes) {
        this.m_value = m_value;
        this.m_dosage = m_dosage;
        this.m_uom = m_uom;
        this.m_date = m_date;
        this.m_time = m_time;
        this.m_notes = m_notes;
    }

    public MedicationModel() {
    }

    public String getM_value() {
        return m_value;
    }

    public void setM_value(String m_value) {
        this.m_value = m_value;
    }

    public String getM_dosage() {
        return m_dosage;
    }

    public void setM_dosage(String m_dosage) {
        this.m_dosage = m_dosage;
    }

    public String getM_uom() {
        return m_uom;
    }

    public void setM_uom(String m_uom) {
        this.m_uom = m_uom;
    }

    public String getM_date() {
        return m_date;
    }

    public void setM_date(String m_date) {
        this.m_date = m_date;
    }

    public String getM_time() {
        return m_time;
    }

    public void setM_time(String m_time) {
        this.m_time = m_time;
    }

    public String getM_notes() {
        return m_notes;
    }

    public void setM_notes(String m_notes) {
        this.m_notes = m_notes;
    }
}
