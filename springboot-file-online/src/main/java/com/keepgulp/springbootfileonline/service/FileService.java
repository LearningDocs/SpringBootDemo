package com.keepgulp.springbootfileonline.service;

import com.keepgulp.springbootfileonline.fileupload.file.FileTreeNode;
import com.keepgulp.springbootfileonline.fileupload.file.UploadFile;

import java.util.List;

public interface FileService {

    List<UploadFile> scanAllFiles();

    List<FileTreeNode> scanAllFilesTree();
}
