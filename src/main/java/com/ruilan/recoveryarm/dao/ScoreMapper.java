package com.ruilan.recoveryarm.dao;

import com.ruilan.recoveryarm.bean.Score;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreMapper {
    int insertScore(Score score);

    List<Score> findScoreByUid(@Param("userId") int userId);

    List<Score> findScoreGroupByUid(@Param("userId") int userId, @Param("doGroup") int doGroup);

    List<Score> findScoreByUidAndDoIndex(@Param("userId") int userId, @Param("doIndex") int doIndex);

    String getStandard(@Param("sid") int score);

    List<String> findAllStandard();

    List<Integer> findUserDoGroup(@Param("userId") int userId);

    int findMaxDoGroup();

    int getCount();

    int findUserDoGroupMax(@Param("userId") int userId);//得到用户最新的数据是哪一组
}
