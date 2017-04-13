package com.atrocky.demo.handler;

import com.atrocky.demo.entity.Employee;
import com.atrocky.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by rocky on 17/4/8.
 */
@Controller
public class employeeHandler {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/emps")
    public String list(@RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNoStr, Map<String, Object> map){
       int pageNo = 1;

       try{
           pageNo = Integer.parseInt(pageNoStr);
           if (pageNo<1){
               pageNo = 1;
           }
       }catch (Exception e){}

        Page<Employee> page = employeeService.getPage(pageNo, 5);
        map.put("page", page);

        return "list";
    }
}
