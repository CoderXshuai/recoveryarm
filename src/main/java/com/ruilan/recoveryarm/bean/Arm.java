package com.ruilan.recoveryarm.bean;

import org.apache.ibatis.type.Alias;

@Alias("arm")
public class Arm {
    private int id;
    private long msec;
    private double txa;
    private double tya;
    private double tza;
    private double bxa;
    private double bya;
    private double bza;
    private int modeGroup;
    private int doIndex;

    public Arm() {
    }

    public Arm(long msec, double txa, double tya, double tza, double bxa, double bya, double bza, int modeGroup, int doIndex) {
        this.msec = msec;
        this.txa = txa;
        this.tya = tya;
        this.tza = tza;
        this.bxa = bxa;
        this.bya = bya;
        this.bza = bza;
        this.modeGroup = modeGroup;
        this.doIndex = doIndex;
    }

    public Arm(int id, long msec, double txa, double tya, double tza, double bxa, double bya, double bza, int modeGroup, int doIndex) {
        this.id = id;
        this.msec = msec;
        this.txa = txa;
        this.tya = tya;
        this.tza = tza;
        this.bxa = bxa;
        this.bya = bya;
        this.bza = bza;
        this.modeGroup = modeGroup;
        this.doIndex = doIndex;
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

    public double getTxa() {
        return txa;
    }

    public void setTxa(double txa) {
        this.txa = txa;
    }

    public double getTya() {
        return tya;
    }

    public void setTya(double tya) {
        this.tya = tya;
    }

    public double getTza() {
        return tza;
    }

    public void setTza(double tza) {
        this.tza = tza;
    }

    public double getBxa() {
        return bxa;
    }

    public void setBxa(double bxa) {
        this.bxa = bxa;
    }

    public double getBya() {
        return bya;
    }

    public void setBya(double bya) {
        this.bya = bya;
    }

    public double getBza() {
        return bza;
    }

    public void setBza(double bza) {
        this.bza = bza;
    }

    public int getModeGroup() {
        return modeGroup;
    }

    public void setModeGroup(int modeGroup) {
        this.modeGroup = modeGroup;
    }

    @Override
    public String toString() {
        return "Arm{" +
                "msec=" + msec +
                ", txa=" + txa +
                ", tya=" + tya +
                ", tza=" + tza +
                ", bxa=" + bxa +
                ", bya=" + bya +
                ", bza=" + bza +
                ", modeGroup=" + modeGroup +
                '}';
    }
}