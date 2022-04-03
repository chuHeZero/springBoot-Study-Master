package com.example.manage.dto.security;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 * @author zzm
 */
@Data
public class AuthUserDto {

    @NotBlank
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @NotBlank
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @ApiModelProperty(value = "验证码")
    private String code;

    /*@ApiModelProperty(value = "Cookie字符")
    private String cookieStr;*/

    /**
     * 后台二次校验参数
     */
    @ApiModelProperty(value = "后台二次校验参数")
    private String captchaVerification;
}
