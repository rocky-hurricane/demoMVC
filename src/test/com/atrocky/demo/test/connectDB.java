package com.atrocky.demo.test;


import com.atrocky.demo.entity.Department;
import com.atrocky.demo.repository.DepartmentRepository;
import com.atrocky.demo.repository.EmployeeRepository;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by rocky on 17/3/30.
 */
public class  connectDB {

    private ApplicationContext applicationContext = null;
    DepartmentRepository departmentRepository = null;
    {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        departmentRepository = applicationContext.getBean(DepartmentRepository.class);
    }

    //getAll is a method defined by user(rocky)
    @Test
    public void  testRepositorySecondLevelCache(){
        List<Department> departmentList = departmentRepository.getAll();
        departmentList = departmentRepository.getAll();
    }

    @Test
    //test the connection to mysql by DataSource
    public void test() throws SQLException {
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        System.out.printf(String.valueOf(dataSource.getConnection()));
    }

}
