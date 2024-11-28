package com.example.doc.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * description 字段描述
 * example 字段返回示例
 **/
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Data
public class User {
    @Schema(description = "用户ID", example = "1")
    private Long id;

    @Schema(description = "用户姓名", example = "张三")
    @NotNull(message = "Name必填")
    private String name;

    @Schema(description = "用户邮箱", example = "zhansan@qq.com")
    @NotNull(message = "Email必填")
    @Email(message = "邮箱格式不正确")
    private String email;
}
