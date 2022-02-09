package indi.spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ViewTestController {
    @GetMapping("/test")
    public String testTest(Model model){
        //model中的数据会放在请求域中
        model.addAttribute("msg","我来了百度");
        model.addAttribute("link","https://www.baidu.com/");

        return "success";

    }
}
