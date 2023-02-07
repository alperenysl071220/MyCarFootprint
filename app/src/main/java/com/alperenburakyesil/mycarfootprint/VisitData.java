package com.alperenburakyesil.mycarfootprint;

import java.io.Serializable;

public class VisitData {
    private String station_name, date, type, amount, perLiter;
    private double totalCost;
    private int carbonFoot;

    public VisitData(String station_name, String date, String type, String amount, String perLiter, double totalCost, int carbonFoot) {
        this.station_name = station_name;
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.perLiter = perLiter;
        this.totalCost = totalCost;
        this.carbonFoot = carbonFoot;
    }

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPerLiter() {
        return perLiter;
    }

    public void setPerLiter(String perLiter) {
        this.perLiter = perLiter;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public int getCarbonFoot() {
        return carbonFoot;
    }

    public void setCarbonFoot(int carbonFoot) {
        this.carbonFoot = carbonFoot;
    }
}