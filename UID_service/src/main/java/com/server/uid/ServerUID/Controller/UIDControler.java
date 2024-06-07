package com.server.uid.ServerUID.Controller;

import com.server.uid.ServerUID.Service.GenerateUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UIDControler {

    @Autowired
    GenerateUID generateUID;

    @GetMapping("/getUID")
    public String getUID(){
        return generateUID.getUID();
    }
}
