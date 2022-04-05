package com.example.manage.mapper.system;

import com.example.manage.entity.system.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author zzm
 * @since 2022-04-05
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 查询用户的权限列表
     *
     * @param userId
     * @return
     */
    List<SysPermission> selectListByUser(Integer userId);

}
