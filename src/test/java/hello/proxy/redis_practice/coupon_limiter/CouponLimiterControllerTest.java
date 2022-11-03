package hello.proxy.redis_practice.coupon_limiter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@AutoConfigureMockMvc
@SpringBootTest
class CouponLimiterControllerTest {

    private static final Logger log = LoggerFactory.getLogger(CouponLimiterControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @DisplayName("10000개 요청이 들어와도 발행은 10개만 된다.")
    @Test
    void test() throws InterruptedException {
        int requestCount = 10000;
        ExecutorService service = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(requestCount);
        String couponName = "party";

        for (int i = 0; i < requestCount; i++) {
            service.submit(() -> {
                try {
                    MvcResult results = mockMvc.perform(get("/api/coupon/" + couponName))
                        .andReturn();
                    if (results.getResponse().getContentAsString().equals("true")) {
                        log.info("난 받았지~ {}", Thread.currentThread().getName());
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        Integer results = Integer.valueOf(Objects.requireNonNull(redisTemplate.opsForValue().get(couponName)));

        Assertions.assertThat(results).isEqualTo(10);
    }

}
