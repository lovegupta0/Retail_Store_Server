package com.retailStore.orderService.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDetails {
    @Id
    @GeneratedValue(generator = "UIDGenerator")
    @GenericGenerator(name = "UIDGenerator",strategy = "com.retailStore.orderService.Config.UIDGenerator")
    private String uid;
    private String customerName;
    private String customerPhoneNo;
    private String deliverType;
    private String customerAddress;
    private Double amount;
    private String servicedBy;
    private Date dateOfPurchase;
    private String modeOfPayment;
    private String status;
    @OneToMany(mappedBy = "orderDetails")
    private List<PurchasedProduct> purchasedProduct;
    
}
