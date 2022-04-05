package com.example.manage.api.config;

import com.example.manage.api.common.JwtAuthenticationEntryPoint;
import com.example.manage.api.common.PasswordEncoder;
import com.example.manage.api.filter.CaptchaFilter;
import com.example.manage.api.filter.JwtAuthenticationFilter;
import com.example.manage.api.handle.JWTLogoutSuccessHandler;
import com.example.manage.api.handle.JwtAccessDeniedHandler;
import com.example.manage.api.handle.LoginFailureHandler;
import com.example.manage.api.handle.LoginSuccessHandler;
import com.example.manage.common.UserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author zzm
 * @date 2022年04月04日 22:08
 */

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final LoginFailureHandler loginFailureHandler;

    private final LoginSuccessHandler loginSuccessHandler;

    private final CaptchaFilter captchaFilter;

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    private final UserDetailServiceImpl userDetailService;

    private final JWTLogoutSuccessHandler jwtLogoutSuccessHandler;

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager());
        return jwtAuthenticationFilter;
    }


    private static final String[] URL_WHITELIST = {
            "/",
            "/adminLogin",
            "/login/**",
            "/logout",
            "/captcha",
            "/favicon.ico",
            "/doc.html",
            "/webjars/**",
            "/swagger-ui.html",
            "/v2/**",
            "/swagger-resources/**",
            "/js/vue.min.js"
    };

    /**
     * 新版本的Spring security规定必须设置一个默认的加密方式，不允许使用明文。这个加密方式是用于在登录时验证密码、注册时需要用到。
     * 我们可以自己选择一种加密方式，Spring security为我们提供了多种加密方式，我们这里使用一种强hash方式进行加密。
     * @author zzm
     * @date 2022/4/5 11:25
     * @return com.example.manage.api.common.PasswordEncoder
     */
    @Bean
    PasswordEncoder PasswordEncoder() {
        return new PasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                // 登录配置
                .formLogin()
                .loginPage("/adminLogin").permitAll()
                .usernameParameter("username").passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureUrl("/adminLogin?error")
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)

                .and()
                .logout()
                .logoutUrl("/myLogout")
                .logoutSuccessUrl("/adminLogin")
                .logoutSuccessHandler(jwtLogoutSuccessHandler)

                // 禁用session
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 配置拦截规则
                .and()
                .authorizeRequests()
                .antMatchers(URL_WHITELIST).permitAll()
                .anyRequest().authenticated()
                // 异常处理器
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // 配置自定义的过滤器
                .and()
                .addFilter(jwtAuthenticationFilter())
                // 验证码过滤器放在UsernamePassword过滤器之前
                .addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class)
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }
}
