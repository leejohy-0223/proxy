package hello.proxy.redis_practice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.web.servlet.MockMvc;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class CountingTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    String[] urls = {"/api/post", "/api/comment"};
    Random random = new Random();

    @Test
    void requestCount() throws InterruptedException {
        int threadCount = 100000;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    int cur = random.nextInt(2);
                    mvc.perform(get(urls[cur]));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        // String today = LocalDate.now().toString();

        // log.info("all counting : {}", redisTemplate.opsForValue().get(today));
        // log.info("comment counting : {}", redisTemplate.opsForValue().get(today + urls[1]));
    }

}
