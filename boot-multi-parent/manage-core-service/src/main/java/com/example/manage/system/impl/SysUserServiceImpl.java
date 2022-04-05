package com.example.manage.system.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.manage.entity.system.SysUser;
import com.example.manage.mapper.system.SysUserMapper;
import com.example.manage.system.ISysUserService;
import org.springframework.stereotype.Service;

/**
 * @author zzm
 * @date 2022年04月04日 22:19
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public SysUser getByUsername(String userName) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name", userName);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public Integer update(SysUser sysUser) {
        return baseMapper.updateById(sysUser);
    }

}
