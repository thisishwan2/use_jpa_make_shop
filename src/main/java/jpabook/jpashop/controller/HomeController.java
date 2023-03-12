package jpabook.jpashop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {
    //slf4j
    //Logger log = LoggerFactory.getLogger(getClass());
    //이처럼 로거를 뽑을 수 있는데 lombok을 쓰니 어노테이션으로 간단히 할 수 있다.
    @RequestMapping("/") // "/"만 적으면 기본 화면
    public String home(){
        log.info("home controller"); //slf4j를 사용해서 사용가능
        return "home";
    }
}
