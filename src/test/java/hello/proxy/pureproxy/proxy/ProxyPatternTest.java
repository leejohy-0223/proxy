package hello.proxy.pureproxy.proxy;

import org.junit.jupiter.api.Test;

import hello.proxy.pureproxy.proxy.code.CacheProxy;
import hello.proxy.pureproxy.proxy.code.ProxyPatternClient;
import hello.proxy.pureproxy.proxy.code.RealSubject;

public class ProxyPatternTest {

    @Test
    void noProxyTest() {
        RealSubject realSubject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(realSubject);
        client.execute();
        client.execute();
        client.execute();
    }

    @Test
    void cacheProxyTest() throws InterruptedException {
        RealSubject realSubject = new RealSubject();
        CacheProxy cacheProxy = new CacheProxy(realSubject);
        // 클라이언트는 프록시를 주입받습니다.
        ProxyPatternClient client = new ProxyPatternClient(cacheProxy);

        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(client::execute);
            thread.start();
        }

        Thread.sleep(5000);
    }
}
