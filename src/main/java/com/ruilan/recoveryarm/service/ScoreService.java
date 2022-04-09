package com.ruilan.recoveryarm.service;

import com.ruilan.recoveryarm.bean.Score;

import java.util.List;

public interface ScoreService {
    int insertScore(Score score);

    List<Score> findScoreByUid(int userId);

    List<Score> findScoreGroupMaxByUid(int userId);

    List<List<Score>> findScoreGroupAllByUid(int userId);

    List<Score> findScoreByUidAndDoIndex(int userId, int doIndex);

    int[] countGrade(int userId);

    List<String> getAllStandard();

    List<List> averageScore();

    int getCount();

    int getMaxDoGroup();

    List<Integer> findUserDoGroup(int userId);

    List<Score> getUserTotalScore(int userId);
}
