package com.coconut.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileConfig implements WebMvcConfigurer {
    @Value("${file-dir}")
    private String fileDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * Resource mapping
         * From file://fileDir/ to http://host:port/file/
         */
        registry.addResourceHandler("/file/**")
                .addResourceLocations("file:" + fileDir);
    }
}
