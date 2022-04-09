package com.ruilan.recoveryarm.bean;

import org.apache.ibatis.type.Alias;

@Alias("quater")
public class ArmQuater {
    private int id;
    private long msec;
    private double tq0;
    private double tq1;
    private double tq2;
    private double tq3;
    private double bq0;
    private double bq1;
    private double bq2;
    private double bq3;
    private int modeGroup;

    public ArmQuater() {
    }

    public ArmQuater(int id, long msec, double tq0, double tq1, double tq2, double tq3, double bq0, double bq1, double bq2, double bq3, int modeGroup) {
        this.id = id;
        this.msec = msec;
        this.tq0 = tq0;
        this.tq1 = tq1;
        this.tq2 = tq2;
        this.tq3 = tq3;
        this.bq0 = bq0;
        this.bq1 = bq1;
        this.bq2 = bq2;
        this.bq3 = bq3;
        this.modeGroup = modeGroup;
    }

    public ArmQuater(long msec, double tq0, double tq1, double tq2, double tq3, double bq0, double bq1, double bq2, double bq3, int modeGroup) {
        this.msec = msec;
        this.tq0 = tq0;
        this.tq1 = tq1;
        this.tq2 = tq2;
        this.tq3 = tq3;
        this.bq0 = bq0;
        this.bq1 = bq1;
        this.bq2 = bq2;
        this.bq3 = bq3;
        this.modeGroup = modeGroup;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getMsec() {
        return msec;
    }

    public void setMsec(long msec) {
        this.msec = msec;
    }

    public double getTq0() {
        return tq0;
    }

    public void setTq0(double tq0) {
        this.tq0 = tq0;
    }

    public double getTq1() {
        return tq1;
    }

    public void setTq1(double tq1) {
        this.tq1 = tq1;
    }

    public double getTq2() {
        return tq2;
    }

    public void setTq2(double tq2) {
        this.tq2 = tq2;
    }

    public double getTq3() {
        return tq3;
    }

    public void setTq3(double tq3) {
        this.tq3 = tq3;
    }

    public double getBq0() {
        return bq0;
    }

    public void setBq0(double bq0) {
        this.bq0 = bq0;
    }

    public double getBq1() {
        return bq1;
    }

    public void setBq1(double bq1) {
        this.bq1 = bq1;
    }

    public double getBq2() {
        return bq2;
    }

    public void setBq2(double bq2) {
        this.bq2 = bq2;
    }

    public double getBq3() {
        return bq3;
    }

    public void setBq3(double bq3) {
        this.bq3 = bq3;
    }

    public int getModeGroup() {
        return modeGroup;
    }

    public void setModeGroup(int modeGroup) {
        this.modeGroup = modeGroup;
    }
}
