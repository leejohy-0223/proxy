package hello.proxy.filterTest.config;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import hello.proxy.filterTest.ReaderFilter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // @Bean
    // public FilterRegistrationBean<Filter> testFilter() {
    //     FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
    //
    //     filterRegistrationBean.setFilter(new TestFilter());
    //     filterRegistrationBean.setOrder(1);
    //     filterRegistrationBean.addUrlPatterns("/*");
    //     filterRegistrationBean.setDispatcherTypes(DispatcherType.ERROR);
    //     return filterRegistrationBean;
    // }

    // @Bean
    // public FilterRegistrationBean<Filter> readerFilter() {
    //     FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();
    //     filter.setFilter(new ReaderFilter());
    //     filter.addUrlPatterns("/*");
    //     return filter;
    // }
}
