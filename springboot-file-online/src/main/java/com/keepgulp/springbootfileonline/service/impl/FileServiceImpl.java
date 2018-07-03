package com.keepgulp.springbootfileonline.service.impl;

import com.keepgulp.springbootfileonline.configure.ConfigProperties;
import com.keepgulp.springbootfileonline.fileupload.file.FileTreeNode;
import com.keepgulp.springbootfileonline.fileupload.file.UploadFile;
import com.keepgulp.springbootfileonline.service.FileService;
import com.keepgulp.springbootfileonline.utils.DateUtil;
import com.keepgulp.springbootfileonline.utils.FileType;
import com.keepgulp.springbootfileonline.utils.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.util.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public List<UploadFile> scanAllFiles() {
        List<UploadFile> uploadFiles = new ArrayList<>();
        List<File> allFilesList = FileUtil.scanFilesDeepest(ConfigProperties.getUploadPath());
        for(File file : allFilesList) {
            String name = file.getName();
            String suffix = name.substring(name.lastIndexOf(".") + 1);
            int type = 0;
            if(!StringUtils.isEmpty(suffix)) {
                Integer v = FileUtil.fileTypeMap.get(suffix);
                if(null != v) {
                    type = v;
                }
            }
            String time = DateUtil.formatSimpleDate(file.lastModified());
            String path = file.getAbsolutePath().substring(ConfigProperties.getUploadPath().length()).replaceAll("\\\\","/");
            UploadFile uf = new UploadFile(name, type, time, "/root" + path);
            uploadFiles.add(uf);
        }
        return uploadFiles;
    }

    @Override
    public List<FileTreeNode> scanAllFilesTree() {
        List<FileTreeNode> treeNodes = scanFileAndFolder(ConfigProperties.getUploadPath());
        return treeNodes;
    }

    private List<FileTreeNode> scanFileAndFolder(String folderPath) {
        List<FileTreeNode> list = new ArrayList<>();
        List<File> firstLevelFiles = FileUtil.scanFileAndDirWithOneLevel(folderPath);
        for(File file : firstLevelFiles) {
            FileTreeNode node = new FileTreeNode();
            String name = file.getName();
            node.setText(name);
            String path = file.getAbsolutePath().substring(ConfigProperties.getUploadPath().length());
            node.setPath(path);
            if(file.isDirectory()) {
                // 文件夹
                List<FileTreeNode> childrenList = scanFileAndFolder(file.getAbsolutePath());
                node.setChildren(childrenList);
                node.setType(FileType.FOLDER.getType());
            } else {
                // 文件
                String suffix = name.substring(name.lastIndexOf(".")+1);
                Integer v = FileUtil.fileTypeMap.get(suffix);
                if(null != v) {
                    String type = FileType.getType(v);
                    if(null != type) {
                        node.setType(type);
                    }
                } else {
                    node.setType(FileType.FILE.getType());
                }
            }
            list.add(node);
        }

        Collections.sort(list, new Comparator<FileTreeNode>() {
            @Override
            public int compare(FileTreeNode o1, FileTreeNode o2) {
                if(CollectionUtils.isEmpty(o1.getChildren()) && CollectionUtils.isEmpty(o2.getChildren()))
                    return o1.getText().compareTo(o2.getText());
                if(!CollectionUtils.isEmpty(o1.getChildren()) && !CollectionUtils.isEmpty(o2.getChildren())) {
                    return o1.getText().compareTo(o2.getText());
                }
                if(!CollectionUtils.isEmpty(o1.getChildren())) {
                    return 1;
                }
                if(!CollectionUtils.isEmpty(o2.getChildren())) {
                    return -1;
                }
                return 0;
            }
        });
        Collections.reverse(list);
        return list;
    }
}
