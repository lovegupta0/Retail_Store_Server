package com.retailStore.userservice.Service;

import com.retailStore.userservice.DTO.UserDTOV1;
import com.retailStore.userservice.DTO.UserDTOV2;
import com.retailStore.userservice.Model.User;
import com.retailStore.userservice.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private EmployeeService employeeService;
    public ResponseEntity<Object> saveUser(User usr){
        userRepo.save(usr);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    public ResponseEntity<UserDTOV1> getUser(String email){
        User usr=userRepo.findByEmail(email);
        if(usr==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new UserDTOV1(usr.getUid(),usr.getEmail(),usr.getFirstName(),usr.getLastName(),usr.getRole()),HttpStatus.OK);
    }
    public ResponseEntity<UserDTOV2> getUserV2(String email){
        User usr=userRepo.findByEmail(email);
        if(usr==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new UserDTOV2(usr.getUid(),usr.getEmail(),usr.getPassword(),usr.getRole()),HttpStatus.OK);
    }
    public ResponseEntity<UserDTOV1> getUserByUID(String uid){
        User usr=userRepo.findById(uid).orElse(null);
        if(usr==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new UserDTOV1(usr.getUid(),usr.getEmail(),usr.getFirstName(),usr.getLastName(),usr.getRole()),HttpStatus.OK);
    }
    public ResponseEntity<Object> updateUser(User user){
        User usr=userRepo.findById(user.getUid()).orElse(null);
        if(usr==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        user.setPassword(usr.getPassword());
        user.setInventoryList(usr.getInventoryList());
        user.setProducts(usr.getProducts());
        user.setEmployee(usr.getEmployee());
        userRepo.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    public ResponseEntity<Object> deleteUser(String uid){
        User usr=userRepo.findById(uid).orElse(null);
        if(usr==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        employeeService.deleteEmployee(usr.getEmployee().getUid());
        userRepo.deleteById(uid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    public ResponseEntity<Object> updatePassword(User user){

        User usr=userRepo.findById(user.getUid()).orElse(null);
        if(usr==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        user.setEmail(usr.getEmail());
        user.setEmployee(usr.getEmployee());
        user.setRole(usr.getRole());
        user.setProducts(usr.getProducts());
        user.setInventoryList(usr.getInventoryList());
        user.setFirstName(usr.getFirstName());
        user.setLastName(usr.getLastName());
        userRepo.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
