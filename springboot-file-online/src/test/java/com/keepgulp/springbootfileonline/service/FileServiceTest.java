package com.keepgulp.springbootfileonline.service;

import com.keepgulp.springbootfileonline.fileupload.file.FileTreeNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileServiceTest {

    @Autowired
    private FileService fileService;

    @Test
    public void scanAllFilesTree() {

        List<FileTreeNode> list = fileService.scanAllFilesTree();

        System.out.println("list's size:" + list.size());
    }
}