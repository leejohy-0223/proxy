package hello.proxy.redis_practice.count_request;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/comment")
@RestController
public class CommentController {

    @GetMapping
    private String getComment() {
        return "comment, ok";
    }

}
