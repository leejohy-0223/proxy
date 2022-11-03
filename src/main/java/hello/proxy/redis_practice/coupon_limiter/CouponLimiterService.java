package hello.proxy.redis_practice.coupon_limiter;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CouponLimiterService {

    private final RedisScript<Boolean> couponLimiter;
    private final RedisTemplate<String, String> redisTemplate;

    public Object getCoupon(String couponName) {
        return redisTemplate
            .execute(couponLimiter, List.of(couponName));
    }

}
