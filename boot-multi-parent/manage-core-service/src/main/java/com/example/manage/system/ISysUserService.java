package com.example.manage.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.manage.entity.system.SysUser;

public interface ISysUserService extends IService<SysUser> {

    SysUser getByUsername(String userName);

    Integer update(SysUser sysUser);
}
