package com.example.manage.common;

import cn.hutool.core.util.ObjectUtil;
import com.example.manage.dto.security.JwtUserDto;
import com.example.manage.entity.system.SysPermission;
import com.example.manage.entity.system.SysUser;
import com.example.manage.system.ISysPermissionService;
import com.example.manage.system.ISysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzm
 * @date 2022年04月04日 21:58
 */

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final ISysUserService sysUserService;

    private final ISysPermissionService sysPermissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || "".equals(username)) {
            throw new RuntimeException("用户不能为空");
        }
        //根据用户名查询用户
        SysUser sysUser = sysUserService.getByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        return new JwtUserDto(sysUser.getId(), sysUser.getUserName(), sysUser.getPassword(), getUserAuthority(sysUser.getId()));
    }

    /**
     * 获取用户权限信息（角色、菜单权限）
     *
     * @param userId
     * @return
     */
    public List<GrantedAuthority> getUserAuthority(Integer userId) {
        // 实际怎么写以数据表结构为准，这里只是写个例子
        // 角色(比如ROLE_admin)，菜单操作权限(比如sys:user:list)
        List<GrantedAuthority> grantedAuthorityArrayList = new ArrayList<>();
        if (ObjectUtil.isNotEmpty(userId)) {
            //获取该用户所拥有的权限
            List<SysPermission> sysPermissions = sysPermissionService.selectListByUser(userId);
            // 声明用户授权
            sysPermissions.forEach(sysPermission -> {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(sysPermission.getPermissionCode());
                grantedAuthorityArrayList.add(grantedAuthority);
            });
        }
        return grantedAuthorityArrayList;
    }
}
