package hello.proxy.redis_practice.rate_limiter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/event")
@RestController
public class LateLimiterController {

    private static final Logger log = LoggerFactory.getLogger(LateLimiterController.class);

    private final LateLimiterService service;

    @GetMapping("/{userId}")
    public String callCount(@PathVariable int userId) {
        Object results = service.checkLimit(String.valueOf(userId));
        log.info("results is : {}", results);
        return String.valueOf(results);
    }
}
