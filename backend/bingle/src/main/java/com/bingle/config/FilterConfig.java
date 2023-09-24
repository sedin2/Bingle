package com.bingle.config;

import com.bingle.filter.BearerTokenFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<BearerTokenFilter> bearerTokenFilter() {
        FilterRegistrationBean<BearerTokenFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new BearerTokenFilter());
        registrationBean.addUrlPatterns("/nickname/check");
        return registrationBean;
    }
}
