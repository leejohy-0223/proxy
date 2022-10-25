package hello.proxy;

import java.lang.reflect.Proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.proxy.app.v1.OrderControllerV1;
import hello.proxy.app.v1.OrderControllerV1Impl;
import hello.proxy.trace.logtrace.LogTrace;

@Configuration
public class DynamicProxyBasicConfig {

    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace logTrace) {
        OrderControllerV1 orderController = new OrderControllerV1Impl(orderServiceV1(logTrace));
        OrderControllerV1 proxy = (OrderControllerV1)
            Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(),
                new Class[] {OrderControllerV1.class},
                new LogTraceBasicHandler(orderController, logTrace)
            );
        return proxy;
    }
}
