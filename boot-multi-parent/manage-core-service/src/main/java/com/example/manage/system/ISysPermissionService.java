package com.example.manage.system;

import com.example.manage.entity.system.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author zzm
 * @since 2022-04-05
 */
public interface ISysPermissionService extends IService<SysPermission> {

    /**
     * 查询用户的权限列表
     *
     * @param userId
     * @return
     */
    List<SysPermission> selectListByUser(Integer userId);
}
