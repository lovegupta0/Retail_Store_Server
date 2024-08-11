package com.retailStore.orderService.Controller;

import com.retailStore.orderService.Model.PurchasedProduct;
import com.retailStore.orderService.Service.PurchasedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("order/purchase/product/v1/")
public class PurchasedProductController {
    @Autowired
    private PurchasedProductService service;

    @GetMapping("{orderid}")
    public ResponseEntity<List<PurchasedProduct>> getPurchaseProducts(String id){
        return service.getPurchasedProduct(id);
    }

    @GetMapping("{ppid}")
    public ResponseEntity<PurchasedProduct> getProduct(String id){
        return service.getProduct(id);
    }
    @PostMapping("/save")
    public ResponseEntity<Object> saveProduct(@RequestBody PurchasedProduct pp){
        return service.saveProduct(pp);
    }
    @PutMapping("/update")
    public ResponseEntity<Object> updateProduct(@RequestBody PurchasedProduct pp){
        return service.updateProduct(pp);
    }
    @DeleteMapping("delete/{uid}")
    public ResponseEntity<Object> deleteProduct(@PathVariable String uid){
        return service.deleteProduct(uid);
    }
}
