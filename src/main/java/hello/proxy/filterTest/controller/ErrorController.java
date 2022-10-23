package hello.proxy.filterTest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {

    private static final Logger log = LoggerFactory.getLogger(ErrorController.class);

    @GetMapping("/custom-error")
    public String errorHandler() {
        log.info("error 발생했읍니다.");
        return "에러입니다.";
    }
}
