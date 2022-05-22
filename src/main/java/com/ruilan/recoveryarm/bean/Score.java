package com.ruilan.recoveryarm.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("score")
@Data
@AllArgsConstructor //全参构造函数
@NoArgsConstructor  //无参构造函数
public class Score {
    private int id;
    private int userId;
    private int modeGroup;
    private int score;
    private long time;
    private int doIndex;
    private int doGroup;


    public Score(int userId, int score, int doGroup) {
        this.userId = userId;
        this.score = score;
        this.doGroup = doGroup;
    }

    public Score(int userId, int modeGroup, int score, int doGroup, int doIndex) {
        this.userId = userId;
        this.modeGroup = modeGroup;
        this.score = score;
        this.doGroup = doGroup;
        this.doIndex = doIndex;
    }
}
