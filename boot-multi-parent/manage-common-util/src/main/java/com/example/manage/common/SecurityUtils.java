package com.example.manage.common;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.manage.constant.CommonConstant;
import com.example.manage.constant.SecurityConstant;
import com.example.manage.dto.security.JwtUserDto;
import com.example.manage.enums.common.AuthErrorCodeEnum;
import com.example.manage.exception.BizException;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限工具
 *
 * @author abs
 * @date 2020.08.10 11:00
 */
public class SecurityUtils {

    /**
     * 获取当前登录用户
     *
     * @return
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
     * @return 系统用户名称
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
     * @return 系统用户名称
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
     * @return 系统用户ID
     */
    public static Integer getCurrentUserId() {
        JwtUserDto userDetails = (JwtUserDto) getUserDetails();
        return userDetails.getUser().getId();
    }

    /**
     * 根据ip获取详细地址
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
     * 是否是超级管理员
     */
    public static boolean isSuperAdmin() {
        List<String> permissions = SecurityUtils.getUserDetails().getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return permissions.contains(CommonConstant.ADMIN_AUTH);
    }

    /**
     * 是否脱敏
     */
    public static boolean hideSensitive() {
        List<String> permissions = SecurityUtils.getUserDetails().getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return !permissions.contains(CommonConstant.SENSITIVE_AUTH);
    }

    /**
     * 获取浏览器
     *
     * @param request
     * @return
     */
    public static String getBrowser(HttpServletRequest request) {
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        Browser browser = userAgent.getBrowser();
        return browser.getName();
    }


}
