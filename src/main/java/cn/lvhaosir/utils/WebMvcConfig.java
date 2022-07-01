package cn.lvhaosir.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        boolean win = System.getProperty("os.name").toLowerCase().contains("win");
        if (!win) {
            //linux
            registry.addResourceHandler("/uploadFile/**").addResourceLocations("file:/usr/local/software/car/uploadFile/");
        } else {
            //windows
            registry.addResourceHandler("/uploadFile/**").addResourceLocations("file:D://uploadFile//");
        }
    }
}
