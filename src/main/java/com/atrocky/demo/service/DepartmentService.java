package com.atrocky.demo.service;

import com.atrocky.demo.entity.Department;
import com.atrocky.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by rocky on 17/4/14.
 */
@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    @Transactional(readOnly = true)
    public List<Department> getAll(){
        return  departmentRepository.getAll();
    }
}

