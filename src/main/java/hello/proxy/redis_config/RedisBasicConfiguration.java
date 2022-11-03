package hello.proxy.redis_config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.ScriptSource;
import org.springframework.scripting.support.ResourceScriptSource;

@Configuration
public class RedisBasicConfiguration {

    private static final Logger log = LoggerFactory.getLogger(RedisBasicConfiguration.class);

    @Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.port}")
    private int redisPort;

    @Bean
    @ConditionalOnMissingBean(RedisConnectionFactory.class)
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(redisHost, redisPort);
    }

    @Bean
    public StringRedisTemplate redisTemplate() {
        StringRedisTemplate template = new StringRedisTemplate(redisConnectionFactory());
        template.setEnableTransactionSupport(true);
        return template;
    }

    @Bean
    public RedisScript<Boolean> script() {
        Resource script = new ClassPathResource("scripts/moneyTransfer.lua");
        return RedisScript.of(script, Boolean.class);
    }

    @Bean
    public RedisScript<Boolean> lateLimiter() {
        Resource script = new ClassPathResource("scripts/rateLimiter.lua");
        return RedisScript.of(script, Boolean.class);
    }

    @Bean
    public RedisScript<Boolean> couponLimiter() {
        Resource script = new ClassPathResource("scripts/couponLimiter.lua");
        return RedisScript.of(script, Boolean.class);
    }
}
