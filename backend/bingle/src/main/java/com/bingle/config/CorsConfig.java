package com.bingle.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Value("${CALLBACK_URI}")
    private String callbackURI;

    @Value("${ORIGINS}")
    private String origins;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(callbackURI)
                .allowedOrigins(origins)
                .allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name())
                .allowCredentials(true);
    }
}
