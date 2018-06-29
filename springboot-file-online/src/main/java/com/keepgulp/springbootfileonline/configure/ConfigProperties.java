package com.keepgulp.springbootfileonline.configure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigProperties {
    private static String uploadPath;
    @Value("${app.paths.uploadedFiles}")
    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }
    public static String getUploadPath() {
        return uploadPath;
    }

}
