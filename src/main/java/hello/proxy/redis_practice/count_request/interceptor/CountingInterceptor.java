package hello.proxy.redis_practice.count_request.interceptor;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CountingInterceptor implements HandlerInterceptor {

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
        Exception {
        // String requestURI = request.getRequestURI();
        String today = LocalDate.now().toString();
        // String key = today + ":" + requestURI;
        redisTemplate.opsForValue().increment(today);

        return true;
    }
}
