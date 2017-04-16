package com.atrocky.demo.handler;

import com.atrocky.demo.entity.Employee;
import com.atrocky.demo.service.DepartmentService;
import com.atrocky.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    //this method will be execute before all target methods.
    @ModelAttribute
    public void getEmployee(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map){
        if (id != null){
            Employee employee = employeeService.get(id);
            //if do not set null, when update the department. it will go wrong.
            //cause hibernate does not allow to change the foreign key.
            employee.setDepartment(null);
            map.put("employee", employee);
        }
    }

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.PUT)
    public String update(Employee employee){
        employeeService.save(employee);
        return "redirect:/emps";
    }

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    public String input(@PathVariable("id") Integer id, Map<String,Object> map){
        Employee employee = employeeService.get(id);
        map.put("employee", employee); //the key value is equal to the <form:form modelAttribute=" ">
        map.put("departments", departmentService.getAll());
        return "input";
    }

    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public String save(Employee employee){
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
