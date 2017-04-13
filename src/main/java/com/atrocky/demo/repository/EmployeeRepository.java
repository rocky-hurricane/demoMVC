package com.atrocky.demo.repository;

import com.atrocky.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by rocky on 17/4/8.
 */
//JpaRepository extends pagingAndsortingRepository which implements the pages function
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
