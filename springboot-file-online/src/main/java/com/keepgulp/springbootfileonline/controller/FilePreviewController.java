package com.keepgulp.springbootfileonline.controller;

import com.keepgulp.springbootfileonline.utils.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/preview")
public class FilePreviewController {

    @GetMapping("/picture")
    public String pictureFilePreviewHandler(@RequestParam("path") String path, Model model) {
        String fileSuffix = FileUtil.getSuffixFromFileName(path);


        return "preview/picture";
    }

    @GetMapping("/simtext")
    public String simTextFilePreviewHandler(@RequestParam("path") String path, Model model) {
        return "preview/simtext";
    }

    @GetMapping("/office")
    public String officeFilePreviewHandler(@RequestParam("path") String path, Model model) {
        return "preview/office";
    }

    @GetMapping("/pdf")
    public String pdfFilePreviewHandler(@RequestParam("path") String path, Model model) {
        return "preview/office";
    }

    @GetMapping("/media")
    public String mediaFilePreviewHandler(@RequestParam("path") String path, Model model) {
        return "preview/media";
    }

    @GetMapping("/compress")
    public String compressFilePreviewHandler(@RequestParam("path") String path, Model model) {
        return "preview/compress";
    }

}
