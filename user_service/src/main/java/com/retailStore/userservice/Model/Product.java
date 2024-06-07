package com.retailStore.userservice.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private String uid;
    private String name;
    private String unitType;
    private String description;
    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY,orphanRemoval = true)
    @JsonIgnore
    private List<ProductInventory> inventories;
    @ManyToOne
    private User ownedBy;

}
