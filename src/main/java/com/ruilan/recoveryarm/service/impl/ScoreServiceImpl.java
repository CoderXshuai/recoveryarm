package com.ruilan.recoveryarm.service.impl;

import com.ruilan.recoveryarm.bean.Score;
import com.ruilan.recoveryarm.bean.User;
import com.ruilan.recoveryarm.dao.ArmMapper;
import com.ruilan.recoveryarm.dao.ScoreMapper;
import com.ruilan.recoveryarm.dao.UserMapper;
import com.ruilan.recoveryarm.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private ArmMapper armMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public int insertScore(Score score) {
        return scoreMapper.insertScore(score);
    }

    @Override
    public List<Score> findScoreByUid(int userId) {
        List<Score> scoreList = scoreMapper.findScoreByUid(userId);
        for (Score score : scoreList) {
            score.setTime(armMapper.getExerStartTime(score.getModeGroup()));
        }
        return scoreList;
    }

    @Override
    public List<Score> findScoreGroupMaxByUid(int userId) {
        int maxDoGroup = scoreMapper.findUserDoGroupMax(userId);
        List<Score> scoreList = scoreMapper.findScoreGroupByUid(userId, maxDoGroup);
        for (Score score : scoreList) {
            score.setTime(armMapper.getExerStartTime(score.getModeGroup()));
        }
        return scoreList;
    }

    @Override
    public List<List<Score>> findScoreGroupAllByUid(int userId) {
        List<Integer> groupList = scoreMapper.findUserDoGroup(userId);
        List<List<Score>> lists = new ArrayList<>();
        for (int i = (groupList.size() - 1); i >= 0; i--) {
            int gid = groupList.get(i);
            List<Score> scoreList = scoreMapper.findScoreGroupByUid(userId, gid);
            for (Score score : scoreList) {
                score.setTime(armMapper.getExerStartTime(score.getModeGroup()));
            }
            lists.add(scoreList);
        }
        return lists;
    }

    @Override
    public List<Score> findScoreByUidAndDoIndex(int userId, int doIndex) {
        List<Score> scoreList = scoreMapper.findScoreByUidAndDoIndex(userId, doIndex);
        if (scoreList == null) {
            scoreList = new ArrayList<>();
            scoreList.add(new Score(userId, 0, doIndex));
        } else {
            for (Score score : scoreList) {
                score.setTime(armMapper.getExerStartTime(score.getModeGroup()));
            }
        }
        return scoreList;
    }

    @Override
    public List<Integer> findUserDoGroup(int userId) {
        return scoreMapper.findUserDoGroup(userId);
    }

    @Override
    public List<Score> getUserTotalScore(int userId) {
        List<Integer> groups = scoreMapper.findUserDoGroup(userId);
        List<Score> result = new ArrayList<>();
        for (int group : groups) {
            List<Score> scores = scoreMapper.findScoreGroupByUid(userId, group);
            int total = 0;
            for (Score score : scores) {
                total = total + score.getScore();
            }
            result.add(new Score(userId, total, group));
        }
        return result;
    }

    @Override
    public int[] countGrade(int userId) {
        int[] count = {0, 0, 0, 0};
        List<Score> scoreList = scoreMapper.findScoreByUid(userId);
        for (Score score : scoreList) {
            count[score.getScore()] = count[score.getScore()] + 1;
        }
        return count;
    }

    @Override
    public List<String> getAllStandard() {
        return scoreMapper.findAllStandard();
    }

    @Override
    public List<List> averageScore() {
        DecimalFormat df = new DecimalFormat("#.00");
        List<List> resultList = new ArrayList<>();
        List<User> userList = userMapper.findAllUser();
        List nameList = new ArrayList<String>();
        List averageList = new ArrayList<Double>();
        for (User user : userList) {
            List<Integer> groups = scoreMapper.findUserDoGroup(user.getUserId());
            int utotal = 0;
            for (int group : groups) {
                List<Score> scores = scoreMapper.findScoreGroupByUid(user.getUserId(), group);
                int total = 0;
                for (Score score : scores) {
                    total = total + score.getScore();
                }
                utotal = utotal + total;
            }
            nameList.add(user.getUsername());
            double len = groups.size();
            double aver;
            if (len == 0) {
                aver = 0;
            } else {
                aver = utotal / len;
            }
            aver = Double.parseDouble(df.format(aver));
            averageList.add(aver);

//            List<Score> scoreList = scoreMapper.findScoreByUid(user.getUserId());
//            nameList.add(user.getUsername());
//
//            int sum = 0;
//            if(scoreList.size() > 0){
//                for(Score score : scoreList){
//                    sum = sum + score.getScore();
//                }
//                double length = scoreList.size();
//                double average = sum/length;
//                averageList.add(average);
//            }else{
//                averageList.add(0);
//            }
        }
        resultList.add(nameList);
        resultList.add(averageList);
        return resultList;
    }

    @Override
    public int getCount() {
        return scoreMapper.getCount();
    }

    @Override
    public int getMaxDoGroup() {
        if (scoreMapper.getCount() == 0) {
            return 1;
        } else {
            return scoreMapper.findMaxDoGroup() + 1;
        }
    }
}
