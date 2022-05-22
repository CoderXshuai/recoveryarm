package com.ruilan.recoveryarm.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("quater")
@Data
@AllArgsConstructor //全参构造函数
@NoArgsConstructor  //无参构造函数
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
}
