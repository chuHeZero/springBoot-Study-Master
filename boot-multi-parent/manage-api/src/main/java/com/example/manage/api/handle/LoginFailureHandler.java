package com.example.manage.api.handle;

import cn.hutool.json.JSONUtil;
import com.example.manage.domain.ResultData;
import com.example.manage.enums.common.AuthErrorCodeEnum;
import com.example.manage.enums.common.BizErrorCodeEnum;
import com.example.manage.enums.common.ResultCode;
import com.example.manage.exception.CaptchaException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author zzm
 * @date 2022/4/4 21:08
 */

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();

        ResultData result;

        if (e instanceof AccountExpiredException) {
            //账号过期
            result = ResultData.fail(AuthErrorCodeEnum.USER_ACCOUNT_EXPIRED.getCode(), AuthErrorCodeEnum.USER_ACCOUNT_EXPIRED.getMsg());
        } else if (e instanceof BadCredentialsException) {
            //密码错误
            result = ResultData.fail(AuthErrorCodeEnum.USER_CREDENTIALS_ERROR.getCode(), AuthErrorCodeEnum.USER_CREDENTIALS_ERROR.getMsg());
        } else if (e instanceof CredentialsExpiredException) {
            //密码过期
            result = ResultData.fail(AuthErrorCodeEnum.USER_CREDENTIALS_EXPIRED.getCode(), AuthErrorCodeEnum.USER_CREDENTIALS_EXPIRED.getMsg());
        } else if (e instanceof DisabledException) {
            //账号不可用
            result = ResultData.fail(AuthErrorCodeEnum.USER_ACCOUNT_DISABLE.getCode(), AuthErrorCodeEnum.USER_ACCOUNT_DISABLE.getMsg());
        } else if (e instanceof LockedException) {
            //账号锁定
            result = ResultData.fail(AuthErrorCodeEnum.USER_ACCOUNT_LOCKED.getCode(), AuthErrorCodeEnum.USER_ACCOUNT_LOCKED.getMsg());
        } else if (e instanceof InternalAuthenticationServiceException) {
            //用户不存在
            result = ResultData.fail(AuthErrorCodeEnum.USER_ACCOUNT_NOT_EXIST.getCode(), AuthErrorCodeEnum.USER_ACCOUNT_NOT_EXIST.getMsg());
        } else if (e instanceof CaptchaException) {
            result = ResultData.fail(AuthErrorCodeEnum.VERIFICATION_CODE_ERROR.getCode(), AuthErrorCodeEnum.VERIFICATION_CODE_ERROR.getMsg());
        } else {
            //其他错误
            result = ResultData.fail(BizErrorCodeEnum.SYSTEM_ERROR.getCode(), BizErrorCodeEnum.SYSTEM_ERROR.getMsg());
        }
        outputStream.write(JSONUtil.toJsonStr(result).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
