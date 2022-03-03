package com.example.manage.api.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.example.manage.common.SecurityUtils;
import com.example.manage.constant.CommonConstant;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MetaHandler implements MetaObjectHandler {

    /**
     * 新增数据执行
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {

        String userName = getUserName();

        // 创建时间
        this.setInsertFieldValByName(CommonConstant.DEFAULT_FIELD_CREATE_TIME, LocalDateTime.now(), metaObject);

        // 修改时间
        this.setUpdateFieldValByName(CommonConstant.DEFAULT_FIELD_UPDATE_TIME, LocalDateTime.now(), metaObject);

        // 创建人
        this.setInsertFieldValByName(CommonConstant.DEFAULT_FIELD_CREATE_BY, userName, metaObject);

        // 修改人
        this.setUpdateFieldValByName(CommonConstant.DEFAULT_FIELD_UPDATE_BY, userName, metaObject);

        // 修改时间
        this.setUpdateFieldValByName(CommonConstant.DEFAULT_FIELD_MODIFY_TIME, LocalDateTime.now(), metaObject);

        Integer userId = getUserId();

        // 创建人
        this.setInsertFieldValByName(CommonConstant.DEFAULT_FIELD_CREATE_USER_ID, userId, metaObject);

        // 修改人
        this.setUpdateFieldValByName(CommonConstant.DEFAULT_FIELD_MODIFY_USER_ID, userId, metaObject);

    }


    /**
     * 更新数据执行
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {

        // 修改时间
        this.setUpdateFieldValByName(CommonConstant.DEFAULT_FIELD_UPDATE_TIME, LocalDateTime.now(), metaObject);

        // 修改人
        this.setUpdateFieldValByName(CommonConstant.DEFAULT_FIELD_UPDATE_BY, getUserName(), metaObject);

        // 修改时间
        this.setUpdateFieldValByName(CommonConstant.DEFAULT_FIELD_MODIFY_TIME, LocalDateTime.now(), metaObject);

        // 修改人
        this.setUpdateFieldValByName(CommonConstant.DEFAULT_FIELD_MODIFY_USER_ID, getUserId(), metaObject);

    }

    /**
     * 用户名
     */
    private String getUserName() {
        try {
            return SecurityUtils.getCurrentUserNickName();
        } catch (Exception e) {
            return "";
        }
    }

    private Integer getUserId() {
        try {
            return SecurityUtils.getCurrentUserId();
        } catch (Exception e) {
            return null;
        }
    }
}