package com.cm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>
 * 本机资源映射到网络资源
 * </p>
 *
 * @author Dico
 * @since 2022-04-29
 */

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
    private static final String baseFilePath = "F:/GraduationProject/files/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // /files/**是静态映射，接在前端的，例如“http://localhost:8081/files/”
        // file:/root/images/是文件在服务器的路径
        registry.addResourceHandler("/files/**")
                .addResourceLocations("file:" + baseFilePath);
    }
}
