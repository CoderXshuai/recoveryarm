package com.ruilan.recoveryarm.service.impl;

import com.ruilan.recoveryarm.bean.Arm;
import com.ruilan.recoveryarm.bean.ArmQuater;
import com.ruilan.recoveryarm.bean.Score;
import com.ruilan.recoveryarm.dao.ArmMapper;
import com.ruilan.recoveryarm.dao.ScoreMapper;
import com.ruilan.recoveryarm.service.FileService;
import com.ruilan.recoveryarm.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileServiceImpl implements FileService {
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @Autowired
    private ArmMapper armMapper;
    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private ScoreService scoreService;

    @Override
    public Map<String, Object> uploadOneFileQuater(MultipartHttpServletRequest mreq, int user) {
        //读取一个文件
        MultipartFile mf = mreq.getFile("file");
        if (mf.getOriginalFilename().endsWith(".txt")) {
            try {
                InputStream input = mf.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(input));

                List<String[]> topArmData = new ArrayList<>();
                List<String[]> bottomArmData = new ArrayList<>();
                int modeGroup = getMaxGroupNum() + 1;

                String str = br.readLine();//跳过标题
                while ((str = br.readLine()) != null) {
                    str = str.trim();
                    String[] strings = str.split("\t");

                    if (strings[0].contains("WT1")) {
                        bottomArmData.add(strings);
                    } else {
                        topArmData.add(strings);
                    }
                }

                List<ArmQuater> armList = new ArrayList<>();
                for (int i = 0; i < topArmData.size() && i < bottomArmData.size(); i++) {
                    armList.add(formatArmQuater(topArmData.get(i), bottomArmData.get(i), modeGroup));
                }

                int lines = armMapper.batchInsertQuater(armList);
                if (lines == 0) {
                    return dealResultMap(false, "上传失败");
                } else {
                    int s = scoreMapper.insertScore(new Score(user, modeGroup, 6, 1, 0));
                    if (s == 0) {
                        return dealResultMap(false, "更新用户记录失败");
                    }
                }
                System.out.println("四元数数据上传完成");
            } catch (Exception e) {
                e.printStackTrace();
                return dealResultMap(false, "上传失败");
            }
        } else {
            return dealResultMap(false, "上传文件类型不是txt文本文件！");
        }
        return dealResultMap(true, "上传成功");
    }

    @Override
    public Map<String, Object> uploadOneFile(MultipartHttpServletRequest mreq, int user) {
        //读取一个文件
        MultipartFile mf = mreq.getFile("file");
        if (mf.getOriginalFilename().endsWith(".txt")) {
            try {
                InputStream input = mf.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(input));

                List<String[]> topArmData = new ArrayList<>();
                List<String[]> bottomArmData = new ArrayList<>();
                int modeGroup = getMaxGroupNum() + 1;

                String str = br.readLine();//跳过标题
                while ((str = br.readLine()) != null) {
                    str = str.trim();
                    String[] strings = str.split("\t");

                    if (strings[0].contains("WT1")) {
                        bottomArmData.add(strings);
                    } else {
                        topArmData.add(strings);
                    }
                }

                List<Arm> armList = new ArrayList<>();
                for (int i = 0; i < topArmData.size() && i < bottomArmData.size(); i++) {
                    armList.add(formatArm(topArmData.get(i), bottomArmData.get(i), modeGroup, 1));
                }

                int lines = armMapper.batchInsert(armList);
                if (lines == 0) {
                    return dealResultMap(false, "上传失败");
                } else {
                    int s = scoreMapper.insertScore(new Score(user, modeGroup, 3, 1, 0));
                    if (s == 0) {
                        return dealResultMap(false, "更新用户记录失败");
                    }
                }
                System.out.println("数据上传完成");
            } catch (Exception e) {
                e.printStackTrace();
                return dealResultMap(false, "上传失败");
            }
        } else {
            return dealResultMap(false, "上传文件类型不是txt文本文件！");
        }
        return dealResultMap(true, "上传成功");
    }

    @Override
    public Map<String, Object> uploadFileGroup(MultipartHttpServletRequest mreq, int user, int[] scores) {
        //读取一个文件
        List<MultipartFile> fileList = new ArrayList<>();
        MultipartFile mf1 = mreq.getFile("file1");
        fileList.add(mf1);
        MultipartFile mf2 = mreq.getFile("file2");
        fileList.add(mf2);
        MultipartFile mf3 = mreq.getFile("file3");
        fileList.add(mf3);
        MultipartFile mf4 = mreq.getFile("file4");
        fileList.add(mf4);
        MultipartFile mf5 = mreq.getFile("file5");
        fileList.add(mf5);
        MultipartFile mf6 = mreq.getFile("file6");
        fileList.add(mf6);
        int doGroup = scoreService.getMaxDoGroup();
        for (int index = 0; index < fileList.size(); index++) {
            MultipartFile mf = fileList.get(index);
            if (mf.getOriginalFilename().endsWith(".txt")) {
                try {
                    InputStream input = mf.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(input));

                    List<String[]> topArmData = new ArrayList<>();
                    List<String[]> bottomArmData = new ArrayList<>();
                    int modeGroup = getMaxGroupNum() + 1;
                    //跳过标题
                    String str = br.readLine();
                    while ((str = br.readLine()) != null) {
                        str = str.trim();
                        String[] strings = str.split("\t");

                        if (strings[0].contains("WT1")) {
                            bottomArmData.add(strings);
                        } else {
                            topArmData.add(strings);
                        }
                    }

                    List<Arm> armList = new ArrayList<>();
                    for (int i = 0; i < topArmData.size() && i < bottomArmData.size(); i++) {
                        armList.add(formatArm(topArmData.get(i), bottomArmData.get(i), modeGroup, index));
                    }

                    int lines = armMapper.batchInsert(armList);
                    if (lines == 0) {
                        return dealResultMap(false, "上传失败");
                    } else {
                        int s = scoreMapper.insertScore(new Score(user, modeGroup, scores[index], doGroup, index));
                        if (s == 0) {
                            return dealResultMap(false, "更新用户记录失败");
                        }
                    }
                    System.out.println("数据" + index + "上传完成");
                } catch (Exception e) {
                    e.printStackTrace();
                    return dealResultMap(false, "上传失败");
                }
            } else {
                return dealResultMap(false, "上传文件类型不是txt文本文件！");
            }
        }

        return dealResultMap(true, "上传成功");
    }

    @Override
    public Map<String, Object> uploadTwoFiles(MultipartHttpServletRequest mreq, int user) {
        MultipartFile bigMF = mreq.getFile("topFile");
        MultipartFile smallMF = mreq.getFile("bottomFile");

        String bf = bigMF.getOriginalFilename();
        String sf = smallMF.getOriginalFilename();
        if (bf != null && bf != "" && sf != null && sf != "") {
            if (bf.endsWith(".txt") && sf.endsWith(".txt")) {
                try {
                    InputStream in1 = bigMF.getInputStream();
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(in1));

                    InputStream in2 = smallMF.getInputStream();
                    BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));

                    List<Arm> armList = new ArrayList<>();

                    String str1 = br1.readLine();//跳过标题
                    String[] strings1;
                    String str2 = br2.readLine();//跳过标题
                    String[] strings2;
                    int modeGroup = getMaxGroupNum() + 1;

                    while ((str1 = br1.readLine()) != null && (str2 = br2.readLine()) != null) {
                        strings1 = str1.split("\t");
                        strings2 = str2.split("\t");
                        armList.add(formatArm(strings1, strings2, modeGroup, 1));
                    }

                    int lines = armMapper.batchInsert(armList);
                    if (lines == 0) {
                        return dealResultMap(false, "上传失败");
                    } else {
                        int s = scoreMapper.insertScore(new Score(user, modeGroup, 0, 1, 0));
                        if (s == 0) {
                            return dealResultMap(false, "更新用户记录失败");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return dealResultMap(false, "上传失败");
                }
            } else {
                return dealResultMap(false, "上传文件类型不是txt文本文件！");
            }
        } else {
            return dealResultMap(false, "请同时上传大臂和小臂的数据！");
        }
        return dealResultMap(true, "上传成功");
    }

    @Override
    public int getMaxGroupNum() {
        if (getCount() == 0) {
            return 0;
        } else {
            return armMapper.getMaxGroupNum();
        }
    }

    @Override
    public int getCount() {
        return armMapper.getCount();
    }

    @Override
    public int getMaxQuaterGroupNum() {
        if (getQuaterCount() == 0) {
            return 0;
        } else {
            return armMapper.getMaxQuaterGroupNum();
        }
    }

    @Override
    public int getQuaterCount() {
        return armMapper.getQuaterCount();
    }

    private Map<String, Object> dealResultMap(boolean success, String msg) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        result.put("msg", msg);
        return result;
    }

    private Arm formatArm(String[] s1, String[] s2, int modeGroup, int doIndex) throws Exception {
        long msec = sdf.parse(s1[3]).getTime();
        double txa = Double.parseDouble(s1[10]);
        double tya = Double.parseDouble(s1[11]);
        double tza = Double.parseDouble(s1[12]);
        double bxa = Double.parseDouble(s2[10]);
        double bya = Double.parseDouble(s2[11]);
        double bza = Double.parseDouble(s2[12]);
        return new Arm(msec, txa, tya, tza, bxa, bya, bza, modeGroup, doIndex);
    }

    private ArmQuater formatArmQuater(String[] s1, String[] s2, int modeGroup) throws Exception {
        long msec = sdf.parse(s1[3]).getTime();
        double tq0 = Double.parseDouble(s1[16]);
        double tq1 = Double.parseDouble(s1[17]);
        double tq2 = Double.parseDouble(s1[18]);
        double tq3 = Double.parseDouble(s1[19]);
        double bq0 = Double.parseDouble(s2[16]);
        double bq1 = Double.parseDouble(s2[17]);
        double bq2 = Double.parseDouble(s2[18]);
        double bq3 = Double.parseDouble(s2[19]);
        return new ArmQuater(msec, tq0, tq1, tq2, tq3, bq0, bq1, bq2, bq3, modeGroup);
    }
}
