package com.atrocky.demo.repository;

import com.atrocky.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by rocky on 17/4/8.
 */
/*
 * JpaRepository extends pagingAndsortingRepository which implements the moving pages function
 * JpaRepository include the basic database operation function.
 */

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    //take advantage of jpa, only need to declare the method.
    public Employee getByLastName(String lastName);

}
