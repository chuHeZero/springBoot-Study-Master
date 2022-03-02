package com.example.manage.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@ApiModel(description = "返回信息")
@NoArgsConstructor
@Data
public class ResultData<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "code值", required = true)
    private String code;

    @ApiModelProperty(value = "是否成功", required = true)
    private Boolean success;

    @ApiModelProperty(value = "消息", required = true)
    private String message;

    @ApiModelProperty("返回对象")
    private T data;

    public ResultData(String code, String message, Boolean success, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.success = success;
    }


}
