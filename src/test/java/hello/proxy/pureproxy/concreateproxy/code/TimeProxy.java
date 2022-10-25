package hello.proxy.pureproxy.concreateproxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeProxy extends ConcreteLogic {

    private ConcreteLogic realLogic;

    public TimeProxy(ConcreteLogic realLogic) {
        super();
        this.realLogic = realLogic;
    }

    @Override
    public String operation() {
        log.info("Time Decorator 실행");

        long startTime = System.currentTimeMillis();
        String result = realLogic.operation();
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("TimeDecorator 종료, resultTime = {}", resultTime);
        return result;
    }
}
