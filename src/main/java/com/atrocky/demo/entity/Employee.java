package com.atrocky.demo.entity;

import com.atrocky.demo.entity.Department;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by rocky on 17/4/5.
 */
@Table(name="DEMO_EMPLOYEES")  //auto create the table in database
@Entity
public class Employee {

    private Integer id;
    private String lastName;
    private String email;

    @DateTimeFormat(pattern="yyyy-MM-dd")  //define the Date format
    private Date birth;

    private Date createTime;

    private Department department;

    @GeneratedValue  //default strategy for generating the key value
    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Temporal(TemporalType.DATE)  //define the Date format
    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Temporal(TemporalType.TIMESTAMP)  //define the Date format
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @JoinColumn(name="DEPARTMENT_ID")  //define the foreign key
    @ManyToOne(fetch=FetchType.LAZY)
    //LAZY can improve the efficiency but lead to lazy-load exception
    //to fix the exception, should config a filter called OpenEntityManagerInViewFilter
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}

