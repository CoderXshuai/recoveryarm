package com.ruilan.recoveryarm.bean;

import org.apache.ibatis.type.Alias;

@Alias("score")
public class Score {
    private int id;
    private int userId;
    private int modeGroup;
    private int score;
    private long time;
    private int doIndex;
    private int doGroup;

    public Score() {
    }

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

    public Score(int id, int userId, int modeGroup, int score, int doGroup, int doIndex) {
        this.id = id;
        this.userId = userId;
        this.modeGroup = modeGroup;
        this.score = score;
        this.doGroup = doGroup;
        this.doIndex = doIndex;
    }

    public Score(int id, int userId, int modeGroup, int score, long time, int doGroup, int doIndex) {
        this.id = id;
        this.userId = userId;
        this.modeGroup = modeGroup;
        this.score = score;
        this.time = time;
        this.doGroup = doGroup;
        this.doIndex = doIndex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getModeGroup() {
        return modeGroup;
    }

    public void setModeGroup(int modeGroup) {
        this.modeGroup = modeGroup;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getDoGroup() {
        return doGroup;
    }

    public void setDoGroup(int doGroup) {
        this.doGroup = doGroup;
    }

    public int getDoIndex() {
        return doIndex;
    }

    public void setDoIndex(int doIndex) {
        this.doIndex = doIndex;
    }
}
