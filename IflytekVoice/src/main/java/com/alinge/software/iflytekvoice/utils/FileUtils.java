package com.alinge.software.iflytekvoice.utils;

import java.io.File;

/**
 * 作者： yejianlin
 * 日期：2016/1/15
 * 作用：
 */
public class FileUtils {
    public static void changeFileName() {
        String path = "C:\\Users\\Administrator\\Desktop\\语音助手\\早上好";
        String src = "早上好_000";
        String dis="morning";
        File dir = new File(path);
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.getAbsolutePath().contains(src)) {
                File newFile=new File(file.getAbsolutePath().replace(src,dis));
                file.renameTo(newFile);
            }
        }
    }
}
