package com.chuhezero.controller;

import com.chuhezero.entity.StudentEntity;
import com.chuhezero.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/api")
public class StudentController {
    @Autowired
    private StudentService studentService;


    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public boolean addStudent(@RequestBody StudentEntity student) {
        System.out.println("丛数据源开始新增...");
        return studentService.insert(student);
    }


    @RequestMapping(value = "/student", method = RequestMethod.PUT)
    public boolean updateStudent(@RequestBody StudentEntity student) {
        System.out.println("丛数据源开始更新...");
        return studentService.update(student);
    }


    @RequestMapping(value = "/student", method = RequestMethod.DELETE)
    public boolean delete(@RequestBody StudentEntity student) {
        System.out.println("丛数据源开始删除...");
        return studentService.delete(student);
    }


    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public List<StudentEntity> findByStudent(StudentEntity student) {
        System.out.println("丛数据源开始查询...");
        return studentService.findByListEntity(student);
    }
}
