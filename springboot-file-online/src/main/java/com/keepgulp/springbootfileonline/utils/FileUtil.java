package com.keepgulp.springbootfileonline.utils;

import com.keepgulp.springbootfileonline.Exceptions.ScanFilesException;
import org.thymeleaf.util.StringUtils;

import java.io.*;
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

    /**
     * 将文本文件中的内容读入到buffer中
     * @param buffer buffer
     * @param filePath 文件路径
     * @throws IOException 异常
     */
    public static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {
        InputStream is = new FileInputStream(filePath);
        String line; // 用来保存每行读取的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine(); // 读取第一行
        while (line != null) { // 如果 line 为空说明读完了
            buffer.append(line); // 将读到的内容添加到 buffer 中
            buffer.append("\n"); // 添加换行符
            line = reader.readLine(); // 读取下一行
        }
        reader.close();
        is.close();
    }

    /**
     * 读取文本文件内容
     * @param filePath 文件所在路径
     * @return 文本内容
     * @throws IOException 异常
     * @author cn.outofmemory
     * @date 2013-1-7
     */
    public static String readFile(String filePath) throws IOException {
        StringBuffer sb = new StringBuffer();
        FileUtil.readToBuffer(sb, filePath);
        return sb.toString();
    }

    /**
     * 功能：Java读取txt文件的内容
     * 步骤：1：先获得文件句柄
     * 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
     * 3：读取到输入流后，需要读取生成字节流
     * 4：一行一行的输出。readline()。
     * 备注：需要考虑的是异常情况
     * @param filePath
     */
    public static String readTxtFile(String filePath, String encoding){
        StringBuilder result = new StringBuilder();
        try {
            if(StringUtils.isEmpty(encoding)) {
                encoding = "UTF-8";
            }
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    result.append(lineTxt + "<br>");
                }
                read.close();
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return result.toString();
    }
}
