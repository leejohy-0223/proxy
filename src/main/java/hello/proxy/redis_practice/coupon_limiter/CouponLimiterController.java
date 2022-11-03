package hello.proxy.redis_practice.coupon_limiter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/coupon")
@RestController
public class CouponLimiterController {

    private final CouponLimiterService service;

    @GetMapping("/{name}")
    public String getCoupon(@PathVariable String name) {
        Object results = service.getCoupon(name);
        return String.valueOf(results);
    }
}
