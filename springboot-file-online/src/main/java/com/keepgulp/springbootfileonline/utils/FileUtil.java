package com.keepgulp.springbootfileonline.utils;

import com.keepgulp.springbootfileonline.Exceptions.ScanFilesException;

import java.io.File;
import java.util.*;

public class FileUtil {

    public static final Map<String, Integer> fileTypeMap = new HashMap<String, Integer>();

    /**
     * 1：文本
     * 2：图片
     * 3：音频
     * 4：excel
     * 5：视频
     * 6：压缩
     * 7：word
     * 8：代码
     */
    static {
        fileTypeMap.put("txt", 1);
        fileTypeMap.put("sql", 1);
        fileTypeMap.put("md", 1);
        fileTypeMap.put("png", 2);
        fileTypeMap.put("jpg", 2);
        fileTypeMap.put("mp3", 3);
        fileTypeMap.put("xls", 4);
        fileTypeMap.put("xlsx", 4);
        fileTypeMap.put("mp4", 5);
        fileTypeMap.put("avi", 5);
        fileTypeMap.put("zip", 6);
        fileTypeMap.put("rar", 6);
        fileTypeMap.put("gz", 6);
        fileTypeMap.put("doc", 7);
        fileTypeMap.put("css", 8);
        fileTypeMap.put("js", 8);
        fileTypeMap.put("html", 8);
        fileTypeMap.put("java", 8);
        fileTypeMap.put("py", 8);
        fileTypeMap.put("bat", 8);
        fileTypeMap.put("sh", 8);
    }

    public static List<File> scanFilesDeepest(String folderPath) throws ScanFilesException {
        LinkedList<File> queueFiles = new LinkedList<File>();
        List<File> scanFiles = new ArrayList<File>();
        File directory = new File(folderPath);
        if (!directory.isDirectory()) {
            throw new ScanFilesException('"' + folderPath + '"' + " input path is not a Directory , please input the right path of the Directory. ^_^...^_^");
        } else {
            //首先将第一层目录扫描一遍
            File[] files = directory.listFiles();
            //遍历扫出的文件数组，如果是文件夹，将其放入到linkedList中稍后处理
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    queueFiles.add(files[i]);
                } else {
                    scanFiles.add(files[i]);
                }
            }

            //如果linkedList非空遍历linkedList
            while (!queueFiles.isEmpty()) {
                //移出linkedList中的第一个
                File headDirectory = queueFiles.removeFirst();
                File[] currentFiles = headDirectory.listFiles();
                for (int j = 0; j < currentFiles.length; j++) {
                    if (currentFiles[j].isDirectory()) {
                        //如果仍然是文件夹，将其放入linkedList中
                        queueFiles.add(currentFiles[j]);
                    } else {
                        scanFiles.add(currentFiles[j]);
                    }
                }
            }
        }

        return scanFiles;
    }

    public static List<File> scanFileAndDirWithOneLevel(String folderPath) throws ScanFilesException {
        List<File> scanFiles = new ArrayList<File>();
        File directory = new File(folderPath);
        if (!directory.isDirectory()) {
            throw new ScanFilesException('"' + folderPath + '"' + " input path is not a Directory , please input the right path of the Directory. ^_^...^_^");
        } else {
            //首先将第一层目录扫描一遍
            File[] files = directory.listFiles();
            //遍历扫出的文件数组，如果是文件夹，将其放入到linkedList中稍后处理
            for (int i = 0; i < files.length; i++) {
                scanFiles.add(files[i]);
            }
            return scanFiles;
        }
    }

    /**
     * 获取文件后缀
     * @param fileName
     * @return
     */
    public static String getSuffixFromFileName(String fileName) {
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
        return suffix;
    }
}
