package com.retailStore.productService.Controller;

import com.retailStore.productService.Model.ProductInventory;
import com.retailStore.productService.Service.ProductInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product/v1/inventory/")
public class ProductInventoryController {

    @Autowired
    private ProductInventoryService service;

    @GetMapping("get/{uid}")
    public ResponseEntity<ProductInventory> getInvByUid(@PathVariable String uid){
        return service.getProdcutInvByUID(uid);
    }
    @GetMapping("get/product")
    public ResponseEntity<List<ProductInventory>> getByProdUid(@RequestParam String uid){
        return service.getProductInvByProduct(uid);
    }
    @PostMapping("create")
    public ResponseEntity<Object> save(@RequestBody ProductInventory inventory){
        return service.saveProdInv(inventory);
    }
    @PatchMapping("update")
    public ResponseEntity<Object> update(@RequestBody ProductInventory inventory){
        return service.updateInv(inventory);
    }
    @DeleteMapping("delete/{uid}")
    public ResponseEntity<Object> delete(@PathVariable String uid){
        return service.delete(uid);
    }
}
