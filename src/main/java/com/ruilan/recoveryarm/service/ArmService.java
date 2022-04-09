package com.ruilan.recoveryarm.service;

import com.ruilan.recoveryarm.bean.Arm;
import com.ruilan.recoveryarm.bean.ArmQuater;

import java.util.List;

public interface ArmService {
    List<Arm> getArmDatas(int modeGroup);

    List<ArmQuater> getArmQuaterData(int modeGroup);

    long getExerStartTime(int modeGroup);

    int getMaxGroupNum();

    int armCount();

    List<List> rate();
}
