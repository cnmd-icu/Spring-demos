package com.example.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/temp")
public class tempController {

    /**
     * 打开templates文件夹下的html
     *
     * @return
     */
    @GetMapping("/")
    public String index() {
        return "temp";
    }
}
