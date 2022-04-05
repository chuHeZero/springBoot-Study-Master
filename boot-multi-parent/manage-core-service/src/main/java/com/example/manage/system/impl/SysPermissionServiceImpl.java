package com.example.manage.system.impl;

import com.example.manage.entity.system.SysPermission;
import com.example.manage.mapper.system.SysPermissionMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.manage.system.ISysPermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author zzm
 * @since 2022-04-05
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {

    @Override
    public List<SysPermission> selectListByUser(Integer userId) {
        return baseMapper.selectListByUser(userId);
    }
}
