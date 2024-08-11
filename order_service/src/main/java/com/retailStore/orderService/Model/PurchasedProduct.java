package com.retailStore.orderService.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PurchasedProduct {
    @Id
    @GeneratedValue(generator = "UIDGenerator")
    @GenericGenerator(name = "UIDGenerator",strategy = "com.retailStore.orderService.Config.UIDGenerator")
    private String uid;
    private String name;
    private String unitType;
    private String description;
    private double costPrice;
    private double sellingPrice;
    private double quantity;
    private Date expiryDate;
    private Date manufaturingDate;
    private double discount=0;
    @ManyToOne
    private OrderDetails orderDetails;

}

