package com.retailStore.productService.Service;

import com.retailStore.productService.Model.Product;
import com.retailStore.productService.Model.ProductInventory;
import com.retailStore.productService.Repo.ProductInventoryRepo;
import com.retailStore.productService.Repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductInventoryService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductInventoryRepo repo;

    public ResponseEntity<ProductInventory> getProdcutInvByUID(String uid){
        ProductInventory productInventory=repo.findById(uid).orElse(null);
        if(productInventory==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(productInventory,HttpStatus.FOUND);
    }
    public ResponseEntity<List<ProductInventory>> getProductInvByProduct(String prodUID){
        Product data=productRepo.findById(prodUID).orElse(null);
        if(data==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        List<ProductInventory> lst=repo.findAllByProduct(data).orElse(new ArrayList<>());
        if(lst.size()==0) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(lst,HttpStatus.FOUND);
    }
    public ResponseEntity<Object> saveProdInv(ProductInventory productInventory){
        Product data=productRepo.findById(productInventory.getProduct().getUid()).orElse(null);
        if(data==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        repo.save(productInventory);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
