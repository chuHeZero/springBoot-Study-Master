package com.chuhezero.service.impl;

import com.chuhezero.entity.StudentEntity;
import com.chuhezero.mapper.BaseMapper;
import com.chuhezero.mapper.cluster.StudentMapper;
import com.chuhezero.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentServiceImpl extends BaseServiceImpl<StudentEntity> implements StudentService {
    @Autowired
    private StudentMapper studentDao;

    @Override
    protected BaseMapper<StudentEntity> getMapper() {
        return this.studentDao;
    }

}
