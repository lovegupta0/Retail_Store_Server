package com.retailStore.productService.Service;

import com.retailStore.productService.DTO.UserDTOV1;
import com.retailStore.productService.Model.Product;
import com.retailStore.productService.Repo.ProductInventoryRepo;
import com.retailStore.productService.Repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductInventoryService invService;
    @Value("${upload.dir}")
    private String uploadDir;
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
        return new ResponseEntity<>(product,HttpStatus.CREATED);
    }

    public ResponseEntity<Object> updateProduct(Product product){
        Product data=productRepo.findById(product.getUid()).orElse(null);
        if(data==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        product.setOwnedBy(data.getOwnedBy());
        product.setInventories(data.getInventories());
        product.setName(product.getName()==null?data.getName():product.getName());
        product.setDescription(product.getDescription()==null?data.getDescription():product.getDescription());
        product.setUnitType(product.getUnitType()==null?data.getUnitType():product.getUnitType());
        productRepo.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    public ResponseEntity<Object> uploadImage(MultipartFile image,String uid){
        Product data=productRepo.findById(uid).orElse(null);
        if(data==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        try {
            if(data.getImgae()!=null){
                Files.deleteIfExists(Path.of(data.getImgae()));
            }
            Path copyLocation = Paths.get(uploadDir  + image.getOriginalFilename());
            Files.copy(image.getInputStream(), copyLocation);
            data.setImgae(copyLocation.toString());
            productRepo.save(data);
        }catch (IOException e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    public ResponseEntity<Object> delete(String uid){
        Product data=productRepo.findById(uid).orElse(null);
        if(data==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        productRepo.deleteById(uid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    public ResponseEntity<Object> deleteAll(List<Product> lst){
        for (Product p:lst){
            delete(p.getUid());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
