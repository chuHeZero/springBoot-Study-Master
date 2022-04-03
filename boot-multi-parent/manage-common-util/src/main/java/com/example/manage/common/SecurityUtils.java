package com.example.manage.common;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.manage.constant.SecurityConstant;
import com.example.manage.dto.security.JwtUserDto;
import com.example.manage.enums.common.AuthErrorCodeEnum;
import com.example.manage.exception.BizException;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * 权限工具
 *
 * @author zzm
 * @date 2022/4/3 18:10
 */
public class SecurityUtils {

    /**
     * 获取当前登录用户
     *
     * @return org.springframework.security.core.userdetails.UserDetails
     * @author zzm
     * @date 2022/4/3 18:10
     */
    public static UserDetails getUserDetails() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new BizException(AuthErrorCodeEnum.AUTH_STATUS_ERROR);
        }
        if (authentication.getPrincipal() instanceof UserDetails) {
            return (JwtUserDto) authentication.getPrincipal();
            /*UserDetailsService userDetailsService = SpringContextHolder.getBean(UserDetailsService.class);
            return userDetailsService.loadUserByUsername(userDetails.getUsername());*/
        }
        throw new BizException(AuthErrorCodeEnum.AUTH_NO_ERROR);
    }

    /**
     * 获取系统用户名称
     *
     * @return java.lang.String
     * @author zzm
     * @date 2022/4/3 18:10
     */
    public static String getCurrentUsername() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new BizException(AuthErrorCodeEnum.AUTH_STATUS_ERROR);
        }
        if (authentication.getPrincipal() instanceof UserDetails) {
            JwtUserDto userDetails = (JwtUserDto) authentication.getPrincipal();
            return userDetails.getUser().getUsername();
        }
        throw new BizException(AuthErrorCodeEnum.AUTH_NO_ERROR);
    }

    /**
     * 获取系统用户名称
     *
     * @return java.lang.String
     * @author zzm
     * @date 2022/4/3 18:10
     */
    public static String getCurrentUserNickName() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new BizException(AuthErrorCodeEnum.AUTH_STATUS_ERROR);
        }
        if (authentication.getPrincipal() instanceof UserDetails) {
            JwtUserDto userDetails = (JwtUserDto) authentication.getPrincipal();
            return userDetails.getUser().getNickName();
        }
        throw new BizException(AuthErrorCodeEnum.AUTH_NO_ERROR);
    }

    /**
     * 获取系统用户ID
     *
     * @return java.lang.Integer
     * @author zzm
     * @date 2022/4/3 18:11
     */
    public static Integer getCurrentUserId() {
        JwtUserDto userDetails = (JwtUserDto) getUserDetails();
        return userDetails.getUser().getId();
    }

    /**
     * 根据ip获取详细地址
     *
     * @param ip
     * @return java.lang.String
     * @author zzm
     * @date 2022/4/3 18:11
     */
    public static String getCityInfo(String ip) {
        try {
            String api = String.format(SecurityConstant.Url.IP_URL, ip);
            JSONObject object = JSONUtil.parseObj(HttpUtil.get(api));
            return object.get("addr", String.class);
        } catch (Exception ex) {
            return "";
        }
    }

    /**
     * 获取浏览器
     *
     * @param request
     * @return java.lang.String
     * @author zzm
     * @date 2022/4/3 18:11
     */
    public static String getBrowser(HttpServletRequest request) {
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        Browser browser = userAgent.getBrowser();
        return browser.getName();
    }

}
