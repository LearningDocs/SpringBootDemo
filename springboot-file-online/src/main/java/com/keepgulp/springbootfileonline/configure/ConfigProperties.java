package com.keepgulp.springbootfileonline.configure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

@Component
public class ConfigProperties {

    private static String uploadDirName;

    @Value("${app.paths.uploadDirName}")
    public void setUploadDirName(String uploadDirName) {
        ConfigProperties.uploadDirName = uploadDirName;
    }

    public static String getUploadPath() {
        //获取跟目录
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        if(!path.exists()) path = new File("");
        File upload = new File(path.getAbsolutePath(),uploadDirName);
        if(!upload.exists())
            upload.mkdirs();
        return upload.getAbsolutePath();
    }

}
