package com.retailStore.userservice.Controller;

import com.retailStore.userservice.Model.Employee;
import com.retailStore.userservice.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/emp/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/email/{email}")
    public ResponseEntity<Employee> getEmpByEmail(@PathVariable String email){
        return service.getEmployeeByEmail(email);
    }
    @GetMapping("/{uid}")
    public ResponseEntity<Employee> getEmpByUID(@PathVariable String uid){
        return service.getEmployeeByUID(uid);
    }
    @PostMapping("/create")
    public ResponseEntity<Object> addEmp(@RequestBody Employee emp){
        return service.saveEmployee(emp);
    }
    @PatchMapping("/update")
    public ResponseEntity<Object> updateEmp(@RequestBody Employee emp){
        return service.updateEmployee(emp);
    }
    @DeleteMapping("/delete/{uid}")
    public ResponseEntity<Object> deleteEmp(@PathVariable String uid){
        return service.deleteEmployee(uid);
    }
}
