package hello.proxy.filterTest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DomainController {

    private static final Logger log = LoggerFactory.getLogger(DomainController.class);

    @GetMapping("/domain")
    public String domain() {
        log.info("called domain!!");
        throw new IllegalStateException();
    }

    @PostMapping("/domain")
    public String domain(@RequestBody PostDto postDto) {
        log.info("컨트롤러에서 읽어야지!");
        log.info("domain is called ! : {}", postDto);
        return "ok";
    }
}
