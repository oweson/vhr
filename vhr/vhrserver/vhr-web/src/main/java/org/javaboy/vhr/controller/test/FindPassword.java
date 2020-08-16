package org.javaboy.vhr.controller.test;

import org.javaboy.vhr.utils.sea521.HttpClientUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2019/6/28 21:30
 */
@RestController
public class FindPassword {
    // 多路径映射
    @RequestMapping({"jre", "/search", "/search.html"})
    public String test() {
        return "hot deploy is success 666666";
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String admin = encoder.encode("123");
        String admin02 = encoder.encode("123");

        System.out.println(admin);
        System.out.println(admin02);
        // 密码相同，加密不同！！！
        // $2a$10$TElXRbffVghdfrF7GWlLhekxyvDzkf/A6HLalsi32v/UPFKMEdT7m
        //$2a$10$.NhMr6MS1SjMyhY.vY8IQ.tw2Yz/NMZ8dbij/sXabbert1KSFLIXu

    }
    @RequestMapping("helloWorld")
    public Object demo1(){
        Map<String,String> map = new HashMap<>();
        map.put("key","are you ok???");
        String doPost = HttpClientUtil.doPost("127.0.0.1:8082/oweson", map);
        return "hahha"+doPost;
    }
    @PostMapping("oweson")
    public Object demo2(Map<String,String> map){
        String key = map.get("key");
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            System.out.println(stringStringEntry.getKey());

        }
        return "加油！"+key;
    }
}
