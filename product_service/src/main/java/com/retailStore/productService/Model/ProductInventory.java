package com.retailStore.productService.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.action.internal.OrphanRemovalAction;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class ProductInventory {
    @Id
    @GeneratedValue(generator = "UIDGenerator")
    @GenericGenerator(name = "UIDGenerator",strategy = "com.retailStore.productService.Config.UIDGenerator")
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
