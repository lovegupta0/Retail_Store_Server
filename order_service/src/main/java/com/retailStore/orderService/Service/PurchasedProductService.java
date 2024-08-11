package com.retailStore.orderService.Service;

import com.retailStore.orderService.Model.OrderDetails;
import com.retailStore.orderService.Model.PurchasedProduct;
import com.retailStore.orderService.Repo.PurchasedProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchasedProductService {
    @Autowired
    private PurchasedProductRepo repo;
    @Autowired
    private OrderService orderService;
    public ResponseEntity<List<PurchasedProduct>> getPurchasedProduct(String orderId){
        ResponseEntity<OrderDetails> order=orderService.getOrderByUid(orderId);
        if(!order.getStatusCode().is2xxSuccessful()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        List<PurchasedProduct> data=repo.findPurchasedProductByOrderDetails(order.getBody());
        return new ResponseEntity<>(data,HttpStatus.FOUND);
    }
    public ResponseEntity<Object> saveProduct(PurchasedProduct pp){
        ResponseEntity<OrderDetails> order=orderService.getOrderByUid(pp.getOrderDetails().getUid());
        if(!order.getStatusCode().is2xxSuccessful()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        repo.save(pp);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    public ResponseEntity<PurchasedProduct> getProduct(String id){
        PurchasedProduct pp=repo.findById(id).orElse(null);
        if(pp==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(pp,HttpStatus.OK);
    }
    public ResponseEntity<Object> updateProduct(PurchasedProduct pp){
        PurchasedProduct data=repo.findById(pp.getUid()).orElse(null);
        if(data==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        data.setDiscount(pp.getDiscount());
        data.setCostPrice(pp.getCostPrice());
        data.setSellingPrice(pp.getSellingPrice());
        data.setQuantity(pp.getQuantity());
        data.setExpiryDate(pp.getManufaturingDate()==null?data.getManufaturingDate():pp.getManufaturingDate());
        data.setExpiryDate(pp.getExpiryDate()==null?data.getExpiryDate():pp.getExpiryDate());
        data.setQuantity(pp.getQuantity());
        repo.save(data);
        return new ResponseEntity<>(data,HttpStatus.OK);
    }
    public ResponseEntity<Object> deleteProduct(String uid){
        repo.deleteById(uid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
