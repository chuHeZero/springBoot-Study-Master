package com.chuhezero.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.chuhezero.mapper.UserMapper;
import com.chuhezero.entity.UserEntity;
import com.chuhezero.service.UserService;

import java.sql.SQLException;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper udao;


    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;
    @Autowired
    private TransactionDefinition transactionDefinition;

    @Override
    @Transactional
    public boolean test1(UserEntity userEntity) throws Exception {
        /*
         * 简单的事物回滚 由Spring框架进行回滚
         */
        long id = userEntity.getId();
        System.out.println("查询的数据1:" + udao.findById(id));
        // 新增两次，会出现主键ID冲突，看是否可以回滚该条数据
        udao.insert(userEntity);
        System.out.println("查询的数据2:" + udao.findById(id));
        udao.insert(userEntity);
        return false;
    }

    @Override
    @Transactional
    public boolean test2(UserEntity userEntity) {

        /*
         * 简单的事物回滚 自己捕获该异常进行手动回滚
         */
        long id = userEntity.getId();
        try {
            System.out.println("查询的数据1:" + udao.findById(id));
            // 新增两次，会出现主键ID冲突，看是否可以回滚该条数据
            udao.insert(userEntity);
            System.out.println("查询的数据2:" + udao.findById(id));
            udao.insert(userEntity);
        } catch (Exception e) {
            System.out.println("发生异常,进行手动回滚！");
            // 手动回滚事物
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // 注意手动回滚事物要在异常抛出之前！
            e.printStackTrace();
        }

        return false;
    }

    @Override
    @Transactional
    public boolean test3(UserEntity userEntity) {

        /*
         * 子方法出现异常进行回滚
         */
        try {
            System.out.println("查询的数据1:" + udao.findById(userEntity.getId()));
            deal1(userEntity);
            deal2(userEntity);
            deal3(userEntity);
        } catch (Exception e) {
            System.out.println("发生异常,进行手动回滚！");
            // 手动回滚事物
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return false;

    }

    public void deal1(UserEntity userEntity) throws SQLException {
        udao.insert(userEntity);
        System.out.println("查询的数据2:" + udao.findById(userEntity.getId()));
    }

    public void deal2(UserEntity userEntity) throws SQLException {
        if (userEntity.getAge() < 20) {
            //SQL异常
            udao.insert(userEntity);
        } else {
            userEntity.setAge(21);
            udao.update(userEntity);
            System.out.println("查询的数据3:" + udao.findById(userEntity.getId()));
        }
    }


    @Transactional(rollbackFor = SQLException.class)
    public void deal3(UserEntity userEntity) {
        if (userEntity.getAge() > 20) {
            //SQL异常
            udao.insert(userEntity);
        }

    }


    @Override
    public boolean test4(UserEntity userEntity) {
        /*
         * 手动进行事物控制
         */
        TransactionStatus transactionStatus = null;
        boolean isCommit = false;
        try {
            transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
            System.out.println("查询的数据1:" + udao.findById(userEntity.getId()));
            // 进行新增/修改
            udao.insert(userEntity);
            System.out.println("查询的数据2:" + udao.findById(userEntity.getId()));
            if (userEntity.getAge() < 20) {
                userEntity.setAge(userEntity.getAge() + 2);
                udao.update(userEntity);
                System.out.println("查询的数据3:" + udao.findById(userEntity.getId()));
            } else {
                throw new Exception("模拟一个异常!");
            }
            //手动提交
            dataSourceTransactionManager.commit(transactionStatus);
            isCommit = true;
            System.out.println("手动提交事物成功!");
            throw new Exception("模拟第二个异常!");

        } catch (Exception e) {
            //如果未提交就进行回滚
            if (!isCommit) {
                System.out.println("发生异常,进行手动回滚！");
                //手动回滚事物
                dataSourceTransactionManager.rollback(transactionStatus);
            }

            e.printStackTrace();
        }
        return false;
    }


}
