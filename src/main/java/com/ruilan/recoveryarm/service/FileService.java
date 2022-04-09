package com.ruilan.recoveryarm.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Map;

public interface FileService {
    Map<String, Object> uploadOneFileQuater(MultipartHttpServletRequest mreq, int user);

    Map<String, Object> uploadOneFile(MultipartHttpServletRequest mreq, int user);

    Map<String, Object> uploadTwoFiles(MultipartHttpServletRequest mreq, int user);

    Map<String, Object> uploadFileGroup(MultipartHttpServletRequest mreq, int user, int[] scores);

    int getMaxGroupNum();

    int getCount();

    int getMaxQuaterGroupNum();

    int getQuaterCount();
}
