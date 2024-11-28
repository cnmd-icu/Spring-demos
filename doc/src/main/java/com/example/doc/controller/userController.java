package com.example.doc.controller;

import com.example.doc.bean.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class userController {
    @Operation(summary = "获取用户信息接口", description = "通过用户ID获取用户信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "用户信息",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "无法获取用户信息")
    })
    @GetMapping("/{id}")
    public User getUserById(@Parameter(description = "用户ID") @PathVariable Long id) {
        //模拟数据库获取用户
        User user = new User();
        user.setId(1L);
        user.setName("张三");
        user.setEmail("zhansan@qq.com");
        return user;
    }

    @Operation(summary = "创建用户接口", description = "创建一个新用户并返回带有用户id的User对象")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "用户创建",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))})
    })
    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        //模拟数据库保存用户并返回用户ID主键
        user.setId(1L);
        return user;
    }
}
