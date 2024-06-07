package com.retailStore.productService.Service;

import com.retailStore.productService.DTO.UserDTOV1;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient("USER-SERVICE")
public interface UserService {

    @GetMapping("/user/v1/get/{uid}")
    public ResponseEntity<UserDTOV1> getUserByUID(@PathVariable String uid);
}
