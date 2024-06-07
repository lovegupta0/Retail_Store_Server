package com.retailStore.userservice.Controller;

import com.retailStore.userservice.DTO.UserDTOV1;
import com.retailStore.userservice.DTO.UserDTOV2;
import com.retailStore.userservice.Model.User;
import com.retailStore.userservice.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/v1")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/create")
    public ResponseEntity<Object> saveUser(@RequestBody User usr){
        return userService.saveUser(usr);
    }
    @GetMapping
    public ResponseEntity<Object> started(){
        return new ResponseEntity<>("User Service working fine....", HttpStatus.OK);
    }
    @GetMapping("/{email}")
    public ResponseEntity<UserDTOV1> getUserV1(@PathVariable String email){
        return userService.getUser(email);
    }
    @GetMapping("/verify/{email}")
    public ResponseEntity<UserDTOV2> getUserV2(@PathVariable String email){
        return userService.getUserV2(email);
    }
    @GetMapping("/get/{uid}")
    public ResponseEntity<UserDTOV1> getUserByUID(@PathVariable String uid){
        return userService.getUserByUID(uid);
    }
    @PutMapping("/update")
    public ResponseEntity<Object> updateUser(@RequestBody User usr){
        return userService.updateUser(usr);
    }
    @DeleteMapping("/delete/{uid}")
    public ResponseEntity<Object> deleteUser(@PathVariable String uid){
        return userService.deleteUser(uid);
    }

}
