package com.keepgulp.springbootfileonline.controller;

import com.keepgulp.springbootfileonline.configure.ConfigProperties;
import com.keepgulp.springbootfileonline.fileupload.form.UploadForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @description:
 * @author: guodongqing
 * @create: 2018-06-29 16:57
 **/
@Controller
public class UploadController {

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload") // //new annotation since 4.3
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
