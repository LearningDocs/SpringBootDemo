package com.keepgulp.springbootfileonline.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class MyWebAppConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * @Description: 对文件的路径进行配置,创建一个虚拟路径/Path/** ，即只要在<img src="/Path/picName.jpg" />便可以直接引用图片
         *这是图片的物理路径  "file:/+本地图片的地址"
         */
        String path = ConfigProperties.getUploadPath() + File.separator;
        path = path.replaceAll("\\\\", "/");
        System.out.println("path = [" + path + "]");
        registry.addResourceHandler("/root/**").addResourceLocations("file:" + path);
    }
}
