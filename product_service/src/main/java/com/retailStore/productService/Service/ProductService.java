package com.retailStore.productService.Service;

import com.retailStore.productService.DTO.UserDTOV1;
import com.retailStore.productService.Model.Product;
import com.retailStore.productService.Repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private UserService userService;

    public ResponseEntity<Product> getProductByUID(String uid){
        Product data=productRepo.findById(uid).orElse(null);
        if(data==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(data,HttpStatus.FOUND);
    }
    public ResponseEntity<List<Product>> getProductByName(String name){
        List<Product> lst=productRepo.findAllByName(name).orElse(new ArrayList<>());
        if(lst.size()==0) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(lst,HttpStatus.FOUND);
    }
    public ResponseEntity<Object> saveProduct(Product product){
        ResponseEntity<UserDTOV1> response=userService.getUserByUID(product.getOwnedBy().getUid());
        if(!response.getStatusCode().is2xxSuccessful()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        productRepo.save(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<Object> updateProduct(Product product){
        Product data=productRepo.findById(product.getUid()).orElse(null);
        if(data==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        product.setOwnedBy(data.getOwnedBy());
        product.setInventories(data.getInventories());
        productRepo.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
