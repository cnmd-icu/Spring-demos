package com.example.word.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class indexController {

    /**
     * Hello World
     * 访问根路径
     * @url http://localhost:9001/
     */
    @RequestMapping("/")
    public String index(){
        return "Hello World";
    }

    /**
     * 获取参数
     * @url http://localhost:9001/getParam?name=lrshuai
     * @param name
     * @return
     */
    @RequestMapping("/getParam")
    public String getParam(String name){
        return "Hello "+name;
    }

}
