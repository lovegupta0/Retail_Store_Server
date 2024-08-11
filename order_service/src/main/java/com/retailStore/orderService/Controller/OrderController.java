package com.retailStore.orderService.Controller;

import com.retailStore.orderService.Model.OrderDetails;
import com.retailStore.orderService.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("order/v1/")
public class OrderController {
    @Autowired
    private OrderService service;

    @GetMapping("{uid}")
    public ResponseEntity<OrderDetails> getOrderByUid(@PathVariable String uid){
        return service.getOrderByUid(uid);
    }

    @PostMapping("/save")
    public ResponseEntity<OrderDetails> saveOrder(@RequestBody OrderDetails od){
        return  service.saveOrder(od);
    }

    @PutMapping("/update")
    public ResponseEntity<OrderDetails> updateOrder(@RequestBody OrderDetails od){
        return service.updateOrder(od);
    }
}
