package com.ruilan.recoveryarm.service.impl;

import com.ruilan.recoveryarm.bean.Arm;
import com.ruilan.recoveryarm.bean.ArmQuater;
import com.ruilan.recoveryarm.dao.ArmMapper;
import com.ruilan.recoveryarm.service.ArmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ArmServiceImpl implements ArmService {
    private final SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
    @Autowired
    private ArmMapper armMapper;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<Arm> getArmDatas(int modeGroup) {
        List<Arm> armList = armMapper.getAllArmDatas(modeGroup);
//        List<Arm> arms = new ArrayList<>();
//        for(int i=0;i<armList.size();i=i+10){
//            arms.add(armList.get(i));
//        }
        return armList;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<ArmQuater> getArmQuaterData(int modeGroup) {
        List<ArmQuater> armQuaterList = armMapper.getAllArmQuaterData(modeGroup);
        return armQuaterList;
    }

    @Override
    public long getExerStartTime(int modeGroup) {
        return armMapper.getExerStartTime(modeGroup);
    }

    @Override
    public int getMaxGroupNum() {
        return armMapper.getMaxGroupNum();
    }

    @Override
    public int armCount() {
        return armMapper.armCount();
    }

    @Override
    public List<List> rate() {
        List<List> resultList = new ArrayList<>();

        List<Integer> groupList = armMapper.getAllGroupNum();
        List<String> dateList = new ArrayList<>();
        List<Integer> rateList = new ArrayList<>();
        for (Integer modeGroup : groupList) {
            long time = armMapper.getExerStartTime(modeGroup);
            Date date = new Date();
            date.setTime(time);
            String dateStr = sdf.format(date);
            int index = whetherHaveDate(dateList, dateStr);

            if (index == -1) {
                dateList.add(dateStr);
                rateList.add(1);
            } else {
                rateList.set(index, rateList.get(index) + 1);
            }
        }
        resultList.add(dateList);
        resultList.add(rateList);
        return resultList;
    }

    private int whetherHaveDate(List<String> dateList, String target) {
        for (int i = 0; i < dateList.size(); i++) {
            if (dateList.get(i).equals(target)) {
                return i;
            }
        }
        return -1;
    }
}
