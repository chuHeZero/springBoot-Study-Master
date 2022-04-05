package com.example.manage.api.handle;

import cn.hutool.json.JSONUtil;
import com.example.manage.common.JwtUtils;
import com.example.manage.domain.ResultData;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * 登出处理器LogoutSuccessHandler
 * @author zzm
 * @date 2022年04月04日 22:01
 */

@Component
@RequiredArgsConstructor
public class JWTLogoutSuccessHandler implements LogoutSuccessHandler {

    private final JwtUtils jwtUtils;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, authentication);
        }

        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();

        httpServletResponse.setHeader(jwtUtils.getHeader(), "");

        ResultData result = ResultData.success("SuccessLogout");

        outputStream.write(JSONUtil.toJsonStr(result).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}