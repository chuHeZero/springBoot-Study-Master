package com.example.manage.api.filter;

import com.example.manage.api.handle.LoginFailureHandler;
import com.example.manage.cache.IRedisService;
import com.example.manage.exception.CaptchaException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.manage.constant.Constant.CAPTCHA_KEY;

/**
 * 验证码过滤器CaptchaFilter
 * CaptchaFilter继承了OncePerRequestFilter抽象类，该抽象类在每次请求时只执行一次过滤，即它的作用就是保证一次请求只通过一次filter,而不需要重复执行。CaptchaFilter需要重写其doFilterInternal方法来自定义处理逻辑
 *
 * @author zzm
 * @date 2022年04月04日 21:22
 */

@Component
@RequiredArgsConstructor
public class CaptchaFilter extends OncePerRequestFilter {


    private final IRedisService redisService;

    private final LoginFailureHandler loginFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String url = httpServletRequest.getRequestURI();
        if ("/login".equals(url) && httpServletRequest.getMethod().equals("POST")) {
            // 校验验证码
            try {
                validate(httpServletRequest);
            } catch (CaptchaException e) {

                // 交给认证失败处理器
                loginFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    // 校验验证码逻辑
    private void validate(HttpServletRequest httpServletRequest) {
        String code = httpServletRequest.getParameter("code");
        String key = httpServletRequest.getParameter("userKey");

        if (StringUtils.isBlank(code) || StringUtils.isBlank(key)) {
            throw new CaptchaException("验证码错误");
        }

        if (!code.equals(redisService.getSet(CAPTCHA_KEY + key))) {
            throw new CaptchaException("验证码错误");
        }

        // 若验证码正确，执行以下语句
        // 一次性使用
        redisService.removeSet(CAPTCHA_KEY + key);
    }
}
