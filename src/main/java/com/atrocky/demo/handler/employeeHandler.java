package com.atrocky.demo.handler;

import com.atrocky.demo.entity.Employee;
import com.atrocky.demo.service.DepartmentService;
import com.atrocky.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by rocky on 17/4/8.
 */
@Controller
public class employeeHandler {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "emp", method = RequestMethod.POST)
    public  String save(Employee employee){
        employeeService.save(employee);
        return "redirect:/emps";
    }

    @ResponseBody //when the response content is not the html page, use this @
    @RequestMapping(value = "ajaxValidateLastName", method = RequestMethod.POST)
    public String getByLastName(@RequestParam(value="lastName",required = true) String lastName){
        Employee employee = employeeService.getByLastName(lastName);
        if(employee == null){
            return "0";
        }else {
            return "1";
        }
    }

    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    public String input(Map<String, Object> map){
        map.put("departments", departmentService.getAll());
        map.put("employee", new Employee());
        return "input";
    }

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
