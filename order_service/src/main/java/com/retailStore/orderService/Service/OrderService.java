package com.retailStore.orderService.Service;

import com.retailStore.orderService.Model.OrderDetails;
import com.retailStore.orderService.Model.PurchasedProduct;
import com.retailStore.orderService.Repo.OrderDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderDetailsRepo repo;
    public ResponseEntity<OrderDetails> getOrderByUid(String uid){
        OrderDetails data=repo.findById(uid).orElse(null);
        if(data==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(data,HttpStatus.FOUND);
    }
    public ResponseEntity<OrderDetails> saveOrder(OrderDetails orderDetails){
        repo.save(orderDetails);
        return new ResponseEntity<>(orderDetails,HttpStatus.CREATED);
    }
    public ResponseEntity<OrderDetails> updateOrder(OrderDetails orderDetails){
        OrderDetails data=repo.findById(orderDetails.getUid()).orElse(null);
        if(data==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if(data.getStatus().equalsIgnoreCase("completed")){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        data.setAmount(orderDetails.getAmount()==null? data.getAmount() : orderDetails.getAmount());
        data.setCustomerName(orderDetails.getCustomerName()==null? data.getCustomerName() : orderDetails.getCustomerName());
        data.setCustomerAddress(orderDetails.getCustomerAddress()==null? data.getCustomerAddress() : orderDetails.getCustomerAddress());
        data.setDeliverType(orderDetails.getDeliverType()==null?data.getDeliverType():orderDetails.getDeliverType());
        data.setDateOfPurchase(orderDetails.getDateOfPurchase()==null?data.getDateOfPurchase():orderDetails.getDateOfPurchase());
        data.setCustomerPhoneNo(orderDetails.getCustomerPhoneNo()==null? data.getCustomerPhoneNo() : orderDetails.getCustomerPhoneNo());
        data.setModeOfPayment(orderDetails.getModeOfPayment()==null?data.getModeOfPayment():orderDetails.getModeOfPayment());
        data.setPurchasedProduct(orderDetails.getPurchasedProduct()==null?data.getPurchasedProduct():orderDetails.getPurchasedProduct());
        data.setStatus(orderDetails.getStatus()==null?data.getStatus():orderDetails.getStatus());
        repo.save(data);
        return new ResponseEntity<>(data,HttpStatus.OK);
    }

}
