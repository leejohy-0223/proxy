package hello.proxy.postprocessor;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.Repeat;

import lombok.extern.slf4j.Slf4j;

public class BasicOrderTest {

    @RepeatedTest(value = 10)
    void basicConfig() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
            BasicConfig.class);

        A a = applicationContext.getBean("beanA", A.class);
        a.helloA();
    }

    @Slf4j
    @Configuration
    static class BasicConfig {
        @Bean(name = "beanA", initMethod = "hook")
        public A a() {
            return new A(b());
        }

        @Bean(name = "beanB")
        public B b() {
            return new B();
        }
    }

    @Slf4j
    static class A {
        private B b;

        public A(B b) {
            this.b = b;
        }

        public void helloA() {
            log.info("helloA");
            log.info("after print, ");
            b.helloB();
        }

        private void hook() {
            b.helloB();
        }
    }

    @Slf4j
    static class B {
        public void helloB() {
            log.info("helloB");
        }
    }



}
