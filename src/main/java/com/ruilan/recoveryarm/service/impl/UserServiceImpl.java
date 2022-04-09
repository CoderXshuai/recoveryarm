package com.ruilan.recoveryarm.service.impl;

import com.ruilan.recoveryarm.bean.Score;
import com.ruilan.recoveryarm.bean.User;
import com.ruilan.recoveryarm.dao.ArmMapper;
import com.ruilan.recoveryarm.dao.ScoreMapper;
import com.ruilan.recoveryarm.dao.UserMapper;
import com.ruilan.recoveryarm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private ArmMapper armMapper;

    @Override
    public int register(User user) {
        return userMapper.register(user);
    }

    @Override
    public User queryUser(String username, String password) {
        return userMapper.queryUser(username, password);
    }

    @Override
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }

    @Override
    public User findUserByName(String username) {
        return userMapper.findUserByName(username);
    }

    @Override
    public int userCount() {
        return userMapper.userCount();
    }

    @Override
    public List<List> weekRate() {
        List<List> resultList = new ArrayList<>();
        List<User> userList = userMapper.findAllUser();
        List<String> nameList = new ArrayList<>();
        List<Integer> rateList = new ArrayList<>();
        for (User user : userList) {
            List<Score> scoreList = scoreMapper.findScoreByUid(user.getUserId());

            if (scoreList.size() > 0) {
                for (Score score : scoreList) {
                    long time = armMapper.getExerStartTime(score.getModeGroup());
                    Date date = new Date();
                    date.setTime(time);

                    if (isLatestWeek(date, new Date())) {
                        int index = whetherHaveName(nameList, user.getUsername());
                        if (index == -1) {
                            nameList.add(user.getUsername());
                            rateList.add(1);
                        } else {
                            rateList.set(index, rateList.get(index) + 1);
                        }
                    } else {
                        int index = whetherHaveName(nameList, user.getUsername());
                        if (index == -1) {
                            nameList.add(user.getUsername());
                            rateList.add(0);
                        } else {
                            rateList.set(index, 0);
                        }
                    }
                }
            } else {
                nameList.add(user.getUsername());
                rateList.add(0);
            }
        }
        for (int k = 0; k < rateList.size(); k++) {
            rateList.set(k, rateList.get(k) / 6);
        }
        resultList.add(nameList);
        resultList.add(rateList);
        return resultList;
    }

    private int whetherHaveName(List<String> nameList, String target) {
        for (int i = 0; i < nameList.size(); i++) {
            if (nameList.get(i).equals(target)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isLatestWeek(Date addtime, Date now) {
        Calendar calendar = Calendar.getInstance();  //得到日历
        calendar.setTime(now);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -7);  //设置为7天前
        Date before7days = calendar.getTime();   //得到7天前的时间
        return before7days.getTime() < addtime.getTime();
    }
}
