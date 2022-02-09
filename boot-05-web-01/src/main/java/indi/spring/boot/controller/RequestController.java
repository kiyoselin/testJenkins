package indi.spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RequestController {
    @GetMapping("/goto")
    public String goToPage(HttpServletRequest request){
        request.setAttribute("msg","成功了");
        return "forward:success";/*转发到success*/
    }
    @GetMapping("/params")
    public String testParam(Map<String,Object> map, Model model, HttpServletRequest request, HttpServletResponse response){
        map.put("hello","world");
        model.addAttribute("world","spring");
        request.setAttribute("msg","request");
        Cookie cookie = new Cookie("c1","v1");
//        cookie.setDomain("localhost");
        response.addCookie(cookie);
        return "forward:/success";
    }

    @ResponseBody
    @GetMapping("/success")
    public Map success(@RequestAttribute("msg")String msg,  HttpServletRequest request){
        Object msg1 = request.getAttribute("msg");
        Object hello = request.getAttribute("hello");
        Object world = request.getAttribute("world");
        Object requestMsg = request.getAttribute("msg");
        Map<String,Object> map = new HashMap<>();
        map.put("reqMethod",msg1);
        map.put("anoMethod",msg);
        map.put("hello",hello);
        map.put("world",world);
        map.put("requestMsg",requestMsg);
        return map;
    }

    /*http://localhost:8080/cars/sell;low=34;brand=byd,audi,yd<*/
    /*springBoot默认禁用矩阵变量的功能*/
    //      手动开启：原理。对于路径的处理。UrlPathHelper进行解析。
    //              removeSemicolonContent（移除分号内容）支持矩阵变量的
    //3、矩阵变量必须有url路径变量才能被解析
    @ResponseBody
    @GetMapping("/cars/{path}")
    public Map carSell(@MatrixVariable("low") Integer low, @MatrixVariable("brand") List<String> brand,@PathVariable("path") String path){
        Map<String,Object> map = new HashMap<>();
        map.put("low",low);
        map.put("brand",brand);
        map.put("path",path);
        return map;
    }
    /*/boss/1/2 /boss/1;age=20/2;age=20*/
    @ResponseBody
    @GetMapping("/boss/{bossId}/{empId}")
    public Map boss(@MatrixVariable(value = "age",pathVar = "bossId")Integer bossAge,@MatrixVariable(value = "age",pathVar = "empId")Integer empAge){
        Map<String,Object> map = new HashMap<>();
        map.put("bossAge",bossAge);
        map.put("empAge",empAge);
        return map;

    }
}
