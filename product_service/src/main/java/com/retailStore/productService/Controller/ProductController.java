package com.retailStore.productService.Controller;

import com.retailStore.productService.Model.Product;
import com.retailStore.productService.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/product/v1/")
public class ProductController {

    @Autowired
    private ProductService prodService;

    @GetMapping("{uid}")
    public ResponseEntity<Product> getProductByUid(@PathVariable String uid){
        return prodService.getProductByUID(uid);
    }

    @GetMapping("name")
    public ResponseEntity<List<Product>> getProductByName(@RequestParam String name){
        return prodService.getProductByName(name);
    }
    @PostMapping("save")
    public ResponseEntity<Object> saveProduct(@RequestBody Product product){
        return prodService.saveProduct(product);
    }

    @PatchMapping("update")
    public ResponseEntity<Object> update(@RequestBody Product product){
        return prodService.updateProduct(product);
    }
    @PostMapping("upload/{uid}")
    public ResponseEntity<Object> uploadImage(@RequestParam("file") MultipartFile image,@PathVariable String uid){
        return prodService.uploadImage(image,uid);
    }

    @DeleteMapping("delete/{uid}")
    public ResponseEntity<Object> deleteProd(@PathVariable String uid){
        return prodService.delete(uid);
    }
    @DeleteMapping("delete-all")
    public ResponseEntity<Object> deleteAll(@RequestBody List<Product> lst){
        return prodService.deleteAll(lst);
    }

}
