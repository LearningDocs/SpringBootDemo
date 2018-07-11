package com.keepgulp.springbootfileonline.controller;

import com.keepgulp.springbootfileonline.configure.ConfigProperties;
import com.keepgulp.springbootfileonline.utils.FileType;
import com.keepgulp.springbootfileonline.utils.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;

@Controller
@RequestMapping("/preview")
public class FilePreviewController {

    @RequestMapping("/picture")
    public String pictureFilePreviewHandler(@RequestParam("path") String path, Model model) {
        String fileSuffix = FileUtil.getSuffixFromFileName(path);
        Integer type = FileUtil.fileTypeMap.get(fileSuffix);
        if(null != type) {
            if(FileType.IMG.getIndex() == type) {
                model.addAttribute("path", "/root" + path);
            }
        } else {
            return "preview/error";
        }

        return "preview/picture";
    }

    @RequestMapping("/simtext")
    public String simTextFilePreviewHandler(@RequestParam("path") String path, Model model) {
        String fileSuffix = FileUtil.getSuffixFromFileName(path);
        Integer type = FileUtil.fileTypeMap.get(fileSuffix);
        if(null != type) {
            if(FileType.TEXT.getIndex() == type) {
                String absoutePath = ConfigProperties.getUploadPath() + path;
                String content = FileUtil.readTxtFile(absoutePath, "UTF-8");
                System.out.println("content = [" + content + "]");
                model.addAttribute("path", "/root" + path);
                model.addAttribute("content", content);
            }
        } else {
            return "preview/error";
        }
        return "preview/simtext";
    }

    @RequestMapping("/office")
    public String officeFilePreviewHandler(@RequestParam("path") String path, Model model) {
        return "preview/office";
    }

    @RequestMapping("/pdf")
    public String pdfFilePreviewHandler(@RequestParam("path") String path, Model model) {
        return "preview/office";
    }

    @RequestMapping("/media")
    public String mediaFilePreviewHandler(@RequestParam("path") String path, Model model) {
        return "preview/media";
    }

    @RequestMapping("/compress")
    public String compressFilePreviewHandler(@RequestParam("path") String path, Model model) {
        return "preview/compress";
    }

}
