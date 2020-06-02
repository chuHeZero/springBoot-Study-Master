package com.chuhezero.bean;

public class ChuHe {

    private String name;
    private int age;

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
        return "ChuHe{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public ChuHe(ChuHe chuHe){
        System.out.println(chuHe);
    }
}
