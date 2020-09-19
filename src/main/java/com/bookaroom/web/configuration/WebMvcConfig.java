package com.bookaroom.web.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.bookaroom.util.Constants;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter
{

    public WebMvcConfig()
    {}

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/" + Constants.USER_PICTURES_RESOURCE_HANDLER + "/**")
                .addResourceLocations("file:" + Constants.USER_PICTURES_DIRECTORY);
        registry.addResourceHandler("/" + Constants.LISTING_PICTURES_RESOURCE_HANDLER_PATH + "/**")
                .addResourceLocations("file:" + Constants.LISTING_PICTURES_DIRECTORY);
        super.addResourceHandlers(registry);
    }

}
