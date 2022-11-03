package hello.proxy.redis_practice;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import hello.proxy.redis_practice.count_request.interceptor.CountingInterceptor;
import lombok.RequiredArgsConstructor;

// @Configuration
@RequiredArgsConstructor
public class TempConfig implements WebMvcConfigurer {

    private final CountingInterceptor countingInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(countingInterceptor)
            .order(1)
            .addPathPatterns("/**");
    }
}
