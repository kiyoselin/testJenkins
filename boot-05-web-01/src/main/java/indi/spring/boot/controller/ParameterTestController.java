package indi.spring.boot.controller;



import indi.spring.boot.bean.Person;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ParameterTestController {
    /**
     * 浏览器回显表单提交信息
     * @param person
     * @return person
     */
    @PostMapping("/saveuser")
    public Person saveUser(Person person){
        return person;
    }

    @GetMapping("/car/{id}/owner/{username}")
    public Map<String,Object> getCar(@PathVariable("id")Integer id,
                                     @PathVariable("username")String name,
                                     @PathVariable Map<String,String> pv,
                                     @RequestHeader("User-Agent") String userAgent,
                                     @RequestHeader Map<String,String> headers,
                                     @RequestParam("age") Integer age,
                                     @RequestParam("interest")List<String> interest,
                                     @RequestParam Map<String,String> Params/*,
                                     @CookieValue("_ga") String ga,
                                     @CookieValue("_ga") Cookie cookie*/){


        Map<String, Object> map = new HashMap<>();
//        map.put("id",id);
//        map.put("name",name);
//        map.put("pv",pv);
//        map.put("userAgent",userAgent);/*取当前请求头*/
//        map.put("headers",headers);/*获取所有的请求头*/
        map.put("age",age);
        map.put("interest",interest);
        map.put("Params",Params);/*所有的请求参数封装到map中*/
//        map.put("cookie",ga);
//        System.out.println("cookie = " + cookie);
        return map;

    }
    @PostMapping("/save")
    public Map postMethod(@RequestBody String content){
        Map<String,Object>map = new HashMap<>();
        map.put("request",content);
        return map;

    }
}
