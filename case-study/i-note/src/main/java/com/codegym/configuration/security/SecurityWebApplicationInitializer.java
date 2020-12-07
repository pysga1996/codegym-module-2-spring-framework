package com.codegym.configuration.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.multipart.support.MultipartFilter;

import javax.servlet.ServletContext;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
    @Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        insertFilters(servletContext, encodingFilter);

//        insertFilters(servletContext, new HiddenHttpMethodFilter());
//
//        MultipartFilter multipartFilter = new MultipartFilter();
//        multipartFilter.setMultipartResolverBeanName("multipartResolver");
//        insertFilters(servletContext, multipartFilter);
    }
}
