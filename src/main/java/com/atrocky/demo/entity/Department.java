package com.atrocky.demo.entity;

import javax.persistence.*;

/**
 * Created by rocky on 17/4/5.
 */
@Cacheable
@Table(name="DEMO_DEPARTMENTS")  //auto create the table in database
@Entity
public class Department {

    private Integer id;
    private String departmentName;

    @GeneratedValue //default strategy for generating the key value
    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

}
