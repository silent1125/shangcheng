package com.taotao.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author hzlei
 * @Package com.taotao.web
 * @date 2019/10/29 16:19
 * @Description: 伪静态
 */
// 把当前文件当做配置文件
@Configuration
public class MyMVCConfiguration implements WebMvcConfigurer {
    @Autowired
    private OrderInterceptor orderInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(orderInterceptor).addPathPatterns("/order/**");
    }


    /**
     * 设置URI匹配规则
     * @param configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        //设置URI后缀模式匹配支持，如handler匹配请求/index,
        // 那么你访问/index.*(你配置的后缀)也可以到达handler
        configurer.setUseSuffixPatternMatch(true);

    }

    /**
     * 改变SpringMVC DispatcherServlet默认配置
     * @param dispatcherServlet
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(dispatcherServlet);
        // 除了默认的/，增加对*.html后缀请求的处理
        servletRegistrationBean.addUrlMappings("*.html");
        servletRegistrationBean.addUrlMappings("/");
        return servletRegistrationBean;
    }

}
