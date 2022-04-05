package com.example.manage.api.common;

import com.example.manage.common.MD5Utils;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author zzm
 * @date 2022年04月04日 22:03
 */

@NoArgsConstructor
public class PasswordEncoder extends BCryptPasswordEncoder {

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        // 接收到的前端的密码
        String pwd = rawPassword.toString();
        // 进行MD5加密
        pwd = MD5Utils.getMD5(pwd, false, 64);
        if (encodedPassword != null && encodedPassword.length() != 0) {
            return encodedPassword.equals(pwd);
        } else {
            return false;
        }
    }
}
