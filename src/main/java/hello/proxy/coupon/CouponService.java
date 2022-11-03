package hello.proxy.coupon;

import java.util.List;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

@Service
public class CouponService {

    private final RedisScript<Boolean> script;
    private final RedisTemplate<String, String> redisTemplate;

    public CouponService(RedisScript<Boolean> script, RedisTemplate<String, String> redisTemplate) {
        this.script = script;
        this.redisTemplate = redisTemplate;
    }

    public Object transfer(String fromAccount, String toAccount, int amount) {
        return this.redisTemplate
            .execute(script, List.of(fromAccount, toAccount), String.valueOf(amount));
    }
}
