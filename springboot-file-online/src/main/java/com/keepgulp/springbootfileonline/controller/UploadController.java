package com.keepgulp.springbootfileonline.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.keepgulp.springbootfileonline.configure.ConfigProperties;
import com.keepgulp.springbootfileonline.fileupload.file.FileTreeNode;
import com.keepgulp.springbootfileonline.fileupload.file.UploadFile;
import com.keepgulp.springbootfileonline.fileupload.form.UploadForm;
import com.keepgulp.springbootfileonline.service.FileService;
import com.keepgulp.springbootfileonline.utils.FileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.constraints.NotNull;
import javax.xml.ws.Response;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: guodongqing
 * @create: 2018-06-29 16:57
 **/
@Controller
public class UploadController {

    @Autowired
    private FileService fileService;

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @GetMapping("/fm")
    public String fileManager(Model model) {
        List<UploadFile> files = fileService.scanAllFiles();
        model.addAttribute("files", files);
        model.addAttribute("title", "文件管理器");
        return "filemanager";
    }

    @GetMapping(value = "/filelist/{type}")
    public String fileList(@PathVariable @NotNull String type, Model model) {
        List<UploadFile> files = new ArrayList<>();
        if("all".equals(type)) {
            files = fileService.scanAllFiles();
        }
        model.addAttribute("files", files);
        return "fragments/fileList";
    }

    @GetMapping(value = "/filetree")
    @ResponseBody
    public String fileTree(Model model) {
        FileTreeNode rootNode = new FileTreeNode();
        rootNode.setText("/");
        rootNode.setType(FileType.FOLDER.getType());
        List<FileTreeNode> list = fileService.scanAllFilesTree();
        rootNode.setChildren(list);
        List<FileTreeNode> rList = new ArrayList<>();
        rList.add(rootNode);
        model.addAttribute("data", rList);
        return JSON.toJSONString(rList);
    }

    @RequestMapping("/uploadfiles")
    public String uploadFiles(@RequestParam("file") MultipartFile file,
                              Model model) {
        if (file.isEmpty()) {
            return "redirect:uploadStatus";
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(ConfigProperties.getUploadPath(), file.getOriginalFilename());
            Files.write(path, bytes);
            List<UploadFile> files = fileService.scanAllFiles();
            model.addAttribute("files", files);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/fm";
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(ConfigProperties.getUploadPath(), file.getOriginalFilename());
            Files.write(path, bytes);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }


    @PostMapping("/uploadMultiFiles")
    public ResponseEntity<?> multiUploadFileModel(@ModelAttribute UploadForm form) {
        System.out.println("Description:" + form.getDescription());
        String result = null;
        try {
            result = this.saveUploadedFiles(form.getFiles());
        }
        // Here Catch IOException only.
        // Other Exceptions catch by RestGlobalExceptionHandler class.
        catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Uploaded to: <br/>" + result, HttpStatus.OK);
    }
    // Save Files
    private String saveUploadedFiles(MultipartFile[] files) throws IOException {
        // Make sure directory exists!
        File uploadDir = new File(ConfigProperties.getUploadPath());
        uploadDir.mkdirs();
        StringBuilder sb = new StringBuilder();
        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue;
            }
            byte[] bytes = file.getBytes();
            Path path = Paths.get(ConfigProperties.getUploadPath(), file.getOriginalFilename());
            Files.write(path, bytes);
            sb.append(path.toString()).append("<br/>");
        }
        return sb.toString();
    }

}
