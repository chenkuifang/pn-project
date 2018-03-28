package com.example.demo.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件读取工具类
 *
 * @author QuiFar
 * @version V1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileUtils {

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static List<String> readFileByLines(String fileName) {
        List<String> data = new ArrayList<>();

        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;

            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                data.add(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }

        return data;
    }

    /**
     * 根据路径获取文件列表
     *
     * @param strPath 文件夹路径
     * @return
     */
    private static List<File> getFileList(String strPath) {
        List<File> filelist = new ArrayList<>();
        File dir = new File(strPath);
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                    getFileList(files[i].getAbsolutePath()); // 获取文件绝对路径
                } else if (fileName.endsWith("log")) { // 判断文件名是否以.avi结尾
                    filelist.add(files[i]);
                }
            }

        }
        return filelist;
    }

    /**
     * 根据路径获取文件名称列表
     *
     * @param strPath 文件夹路径
     * @return
     */
    public static List<String> getFileNameList(String strPath) {
        List<String> filelist = new ArrayList<>();
        File dir = new File(strPath);
        // 该文件目录下文件全部放入数组
        File[] files = dir.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                // 判断是文件还是文件夹
                if (files[i].isDirectory()) {
                    // 获取文件绝对路径,递归
                    getFileList(files[i].getAbsolutePath());
                } else {
                    String strFileName = files[i].getAbsolutePath();
                    filelist.add(strFileName);
                }
            }
        }

        return filelist;
    }

    /**
     * 根据路径获取文件绝对路径、名称列表
     *
     * @param strPath 文件夹路径
     * @return
     */
    public static Map<String, String> getFileNameLists(String strPath) {
        Map<String, String> map = new HashMap<String, String>();
        File dir = new File(strPath);
        // 该文件目录下文件全部放入数组
        File[] files = dir.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                // 判断是文件还是文件夹
                if (files[i].isDirectory()) {
                    // 获取文件绝对路径,递归
                    getFileList(files[i].getAbsolutePath());
                } else {
                    String fileName = files[i].getName();
                    String strFileName = files[i].getAbsolutePath();
                    map.put(fileName, strFileName);
                }
            }
        }
        return map;
    }

    // /**
    // * 以行为单位读取文件，常用于读面向行的格式化文件
    // */
    // public static void readFileByLines(String fileName) {
    // File file = new File(fileName);
    // BufferedReader reader = null;
    // try {
    // System.out.println("以行为单位读取文件内容，一次读一整行：");
    // reader = new BufferedReader(new FileReader(file));
    // String tempString = null;
    // int line = 1;
    // // 一次读入一行，直到读入null为文件结束
    // while ((tempString = reader.readLine()) != null) {
    // // 显示行号
    // System.out.println("line " + line + ": " + tempString);
    // line++;
    // }
    // reader.close();
    // } catch (IOException e) {
    // e.printStackTrace();
    // } finally {
    // if (reader != null) {
    // try {
    // reader.close();
    // } catch (IOException e1) {
    // }
    // }
    // }
    // }

}