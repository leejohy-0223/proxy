package hello.proxy.coupon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CouponController {

    private static final Logger log = LoggerFactory.getLogger(CouponController.class);
    private final CouponService couponService;
    private final RedisTemplate<String, String> redisTemplate;

    @GetMapping("/coupon")
    public void domain() {

        // init
        redisTemplate.opsForHash().put("account", "A", "100");
        redisTemplate.opsForHash().put("account", "B", "20");

        // transfer money with lua script
        this.couponService.transfer("A", "B", 20);

        // check the result
        log.info("A result is = {}", redisTemplate.opsForHash().get("account", "A"));
        log.info("B result is = {}", redisTemplate.opsForHash().get("account", "B"));
    }
}
