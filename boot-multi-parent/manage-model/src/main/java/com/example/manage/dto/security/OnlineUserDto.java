package com.example.manage.dto.security;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 在线用户
 * @author abs
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlineUserDto {

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickName;

    /**
     * 部门
     */
    @ApiModelProperty(value = "部门")
    private String deptNames;

    /**
     * 浏览器
     */
    @ApiModelProperty(value = "浏览器")
    private String browser;

    /**
     * IP
     */
    @ApiModelProperty(value = "IP")
    private String ip;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String address;

    /**
     * token
     */
    @ApiModelProperty(value = "token")
    private String key;

    /**
     * 登录时间
     */
    @ApiModelProperty(value = "登录时间")
    private Date loginTime;

}
