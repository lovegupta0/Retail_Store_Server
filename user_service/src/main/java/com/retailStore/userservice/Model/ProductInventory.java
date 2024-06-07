package com.retailStore.userservice.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class ProductInventory {
    @Id
    private String uid;
    private double costPrice;
    private double sellingPrice;
    private double quantity;
    private Date expiryDate;
    private Date manufaturingDate;
    private double discount=0;
    @ManyToOne
    private Product product;
    @ManyToOne
    private User stockedBy;

}
