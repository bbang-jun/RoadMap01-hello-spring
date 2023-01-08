package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello") // 웹 애플리케이션에서 /hello로 들어갈 시, 이 메서드를 실행함
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }
}
