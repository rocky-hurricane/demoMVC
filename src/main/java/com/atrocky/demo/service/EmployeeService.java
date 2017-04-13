package com.atrocky.demo.service;

import com.atrocky.demo.entity.Employee;
import com.atrocky.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by rocky on 17/4/8.
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    //PageRequest is implementing Class of Pageable implement
    @Transactional(readOnly = true)
    public Page<Employee> getPage(int pageNo, int pageSize){
        PageRequest pageable = new PageRequest(pageNo-1, pageSize);
        return employeeRepository.findAll(pageable);
    }


}
