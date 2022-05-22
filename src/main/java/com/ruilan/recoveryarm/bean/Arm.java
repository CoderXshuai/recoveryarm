package com.ruilan.recoveryarm.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("arm")
@Data
@AllArgsConstructor //全参构造函数
@NoArgsConstructor  //无参构造函数
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
}