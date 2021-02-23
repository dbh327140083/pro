package com.ujiuye.utils;
/*
 截取文件名
 */
public class SubStringFileName {

    public static String subFileName(String fileName){
        fileName=fileName.substring(fileName.lastIndexOf("/")+1);
        return fileName;
    }
}
