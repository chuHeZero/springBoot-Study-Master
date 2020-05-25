package com.chuhezero.entity;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class UserEntity  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private int id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private int age;

    public UserEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
