package com.ruilan.recoveryarm.dao;

import com.ruilan.recoveryarm.bean.Arm;
import com.ruilan.recoveryarm.bean.ArmQuater;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArmMapper {
    List<Arm> getAllArmDatas(@Param("modeGroup") int modeGroup);

    List<ArmQuater> getAllArmQuaterData(@Param("modeGroup") int modeGroup);

    List<Integer> getAllGroupNum();

    List<Integer> getAllQuaterGroupNum();

    int batchInsert(@Param("armList") List<Arm> armList);

    int batchInsertQuater(@Param("armListQuater") List<ArmQuater> armListQuater);

    int getMaxGroupNum();

    int getMaxQuaterGroupNum();

    int getCount();

    int getQuaterCount();

    long getExerStartTime(@Param("modeGroup") int modeGroup);

    long getQuaterExerStartTime(@Param("modeGroup") int modeGroup);

    int armCount();

    int armQuaterCount();
}
