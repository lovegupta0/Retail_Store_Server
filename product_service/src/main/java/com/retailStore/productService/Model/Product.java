package com.retailStore.productService.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(generator = "UIDGenerator")
    @GenericGenerator(name = "UIDGenerator",strategy = "com.retailStore.productService.Config.UIDGenerator")
    private String uid;
    private String name;
    private String unitType;
    private String description;
    private String imgae;
    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY,orphanRemoval = true)
    @JsonIgnore
    private List<ProductInventory> inventories;
    @ManyToOne
    private User ownedBy;

}
