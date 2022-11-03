package hello.proxy.redis_practice.rate_limiter;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LateLimiterService {

    private static final Logger log = LoggerFactory.getLogger(LateLimiterService.class);

    private final RedisScript<Boolean> lateLimiter;
    private final RedisTemplate<String, String> redisTemplate;

    public Object checkLimit(String key) {
        long millis = System.currentTimeMillis();

        log.info("millis is : {}", millis);
        return redisTemplate
            .execute(lateLimiter, List.of(key), String.valueOf(millis));
    }

}
