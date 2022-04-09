package com.ruilan.recoveryarm.controller;

import com.ruilan.recoveryarm.bean.Arm;
import com.ruilan.recoveryarm.bean.ArmQuater;
import com.ruilan.recoveryarm.bean.Score;
import com.ruilan.recoveryarm.bean.User;
import com.ruilan.recoveryarm.service.ArmService;
import com.ruilan.recoveryarm.service.FileService;
import com.ruilan.recoveryarm.service.ScoreService;
import com.ruilan.recoveryarm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/arm")
public class ArmController {
    @Autowired
    private ArmService armService;
    @Autowired
    private FileService fileService = null;
    @Autowired
    private UserService userService = null;
    @Autowired
    private ScoreService scoreService;

    @RequestMapping("/data/{modeGroup}")
    @CrossOrigin
    @ResponseBody
    public List<Arm> getArmData(@PathVariable("modeGroup") int modeGroup) {
        return armService.getArmDatas(modeGroup);
    }

    @RequestMapping("/dataQuater/{modeGroup}")
    @CrossOrigin
    @ResponseBody
    public List<ArmQuater> getArmQuaterData(@PathVariable("modeGroup") int modeGroup) {
        return armService.getArmQuaterData(modeGroup);
    }

    @RequestMapping("/page")
    @CrossOrigin
    public String uploadPage() {
        return "upload";
    }

    @RequestMapping("/upload")
    @CrossOrigin
    @ResponseBody
    public Map<String, Object> uploadFile(HttpServletRequest request, int user, int[] scores) {
        MultipartHttpServletRequest mreq = null;
        if (request instanceof MultipartHttpServletRequest) {
            mreq = (MultipartHttpServletRequest) request;
        } else {
            return dealResultMap(false, "上传失败");
        }
        return fileService.uploadFileGroup(mreq, user, scores);
    }

    @RequestMapping("/uploadQuater")
    @CrossOrigin
    @ResponseBody
    public Map<String, Object> uploadQuaterFile(HttpServletRequest request, int user) {
        MultipartHttpServletRequest mreq = null;
        if (request instanceof MultipartHttpServletRequest) {
            mreq = (MultipartHttpServletRequest) request;
        } else {
            return dealResultMap(false, "上传失败");
        }
        return fileService.uploadOneFileQuater(mreq, user);
    }

    @RequestMapping("/register")
    @CrossOrigin
    @ResponseBody
    public Map<String, Object> insertUser(String username, String password, String note) {
        User u = userService.findUserByName(username);
        if (u != null) {
            return dealUserResultMap(false, "用户名重复，注册失败", 0, -1);
        } else {
            User user = new User(username, password, note);
            int result = userService.register(user);
            if (result == 0) {
                return dealUserResultMap(false, "注册失败", 0, -1);
            } else {
                return dealUserResultMap(true, "注册成功", 0, user.getUserId());
            }
        }
    }

    @RequestMapping("/signIn")
    @CrossOrigin
    @ResponseBody
    public Map<String, Object> signIn(String username, String password) {
        User user = userService.queryUser(username, password);
        if (user == null) {
            return dealUserResultMap(false, "登录失败", 0, -1);
        } else {
            return dealUserResultMap(true, "登陆成功", user.getAdmin(), user.getUserId());
        }
    }

    @RequestMapping("/findAllUser")
    @CrossOrigin
    @ResponseBody
    public List<User> getUserList() {
        return userService.findAllUser();
    }

    @RequestMapping("/userScore")
    @CrossOrigin
    @ResponseBody
    public List<Score> getUserScore(int userId) {
        return scoreService.findScoreByUid(userId);
    }

    @RequestMapping("/userScoreGroup")
    @CrossOrigin
    @ResponseBody
    public List<Score> getUserScoreGroup(int userId) {
        return scoreService.findScoreGroupMaxByUid(userId);
    }

    @RequestMapping("/userScoreAllGroup")
    @CrossOrigin
    @ResponseBody
    public List<List<Score>> getUserScoreGroupAll(int userId) {
        return scoreService.findScoreGroupAllByUid(userId);
    }

    @RequestMapping("/userTotalScore")
    @CrossOrigin
    @ResponseBody
    public List<Score> getUserTotalScore(int userId) {
        return scoreService.getUserTotalScore(userId);
    }

    @RequestMapping("/userScoreByIndex")
    @CrossOrigin
    @ResponseBody
    public List<Score> findScoreByUidAndDoIndex(int userId, int doIndex) {
        List<Score> scoreList = scoreService.findScoreByUidAndDoIndex(userId, doIndex);
        return scoreList;
    }

    @RequestMapping("/countGrade")
    @CrossOrigin
    @ResponseBody
    public int[] countGrade(int userId) {
        return scoreService.countGrade(userId);
    }

    @RequestMapping("/linearScore")
    @CrossOrigin
    @ResponseBody
    public List<Score> linearGraph(int userId) {
        return scoreService.findScoreByUid(userId);
    }

    @RequestMapping("/standards")
    @CrossOrigin
    @ResponseBody
    public List<String> allStanard() {
        return scoreService.getAllStandard();
    }

    @RequestMapping("/userCount")
    @CrossOrigin
    @ResponseBody
    public Map<String, Object> countUser() {
        return dealResultMap(true, userService.userCount() + "");
    }

    @RequestMapping("/dataGroupCount")
    @CrossOrigin
    @ResponseBody
    public Map<String, Object> countDataGroup() {
        return dealResultMap(true, armService.getMaxGroupNum() + "");
    }

    @RequestMapping("/dataCount")
    @CrossOrigin
    @ResponseBody
    public Map<String, Object> countData() {
        return dealResultMap(true, armService.armCount() + "");
    }

    @RequestMapping("/averageScore")
    @CrossOrigin
    @ResponseBody
    public List<List> averageScore() {
        return scoreService.averageScore();
    }

    @RequestMapping("/rate")
    @CrossOrigin
    @ResponseBody
    public List<List> rate() {
        return armService.rate();
    }

    @RequestMapping("/weekRate")
    @CrossOrigin
    @ResponseBody
    public List<List> weekRate() {
        return userService.weekRate();
    }

    private Map<String, Object> dealResultMap(boolean success, String msg) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        result.put("msg", msg);
        return result;
    }

    private Map<String, Object> dealUserResultMap(boolean success, String msg, int admin, int userId) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        result.put("msg", msg);
        result.put("admin", admin);
        result.put("userId", userId);
        return result;
    }
}
