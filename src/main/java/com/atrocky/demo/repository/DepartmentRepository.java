package com.atrocky.demo.repository;

import com.atrocky.demo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Service;

import javax.persistence.QueryHint;
import java.util.List;

/**
 * Created by rocky on 17/4/14.
 */
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    /*
     * using the original API-- findAll() method cannot implement second_level_cache.
     * in order to do this, should add @QueryHints and set the value.
     */
    @QueryHints({@QueryHint(name=org.hibernate.ejb.QueryHints.HINT_CACHEABLE,value="true")})
    @Query("FROM Department d")
    public List<Department> getAll();

}
