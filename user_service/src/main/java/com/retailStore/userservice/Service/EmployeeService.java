package com.retailStore.userservice.Service;

import com.retailStore.userservice.Model.Employee;
import com.retailStore.userservice.Model.User;
import com.retailStore.userservice.Repo.EmployeeRepo;
import com.retailStore.userservice.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo repo;
    @Autowired
    private UserRepo userRepo;


    public ResponseEntity<Employee> getEmployeeByUID(String uid){
        Employee emp=repo.findById(uid).orElse(null);
        if(emp==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(emp,HttpStatus.OK);
    }
    public ResponseEntity<Employee> getEmployeeByEmail(String email){
        User usr=userRepo.findByEmail(email);
        if(usr==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Employee emp=repo.findByUser(usr).orElse(null);
        if(emp==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(emp,HttpStatus.OK);
    }
    public ResponseEntity<Object> saveEmployee(Employee employee){
        User user=userRepo.findById(employee.getUser().getUid()).orElse(null);
        if(user==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        employee.setUser(user);
        repo.save(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    public ResponseEntity<Object> updateEmployee(Employee employee){
        User user=userRepo.findById(employee.getUser().getUid()).orElse(null);
        if(user==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        employee.setUser(user);
        repo.save(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    public ResponseEntity<Object> deleteEmployee(String uid){
        Employee emp=repo.findById(uid).orElse(null);
        if(emp==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        repo.deleteById(uid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
