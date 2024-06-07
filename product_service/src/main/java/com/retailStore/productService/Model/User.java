package com.retailStore.productService.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "users")
public class User {
    @Id
    @Column(updatable = false, nullable = false)
    private String uid;
    @Column(nullable = false,unique = true)
    private String email;
    private String role;
    @OneToMany(orphanRemoval = true)
    @JsonIgnore
    private List<Product> products;
    @OneToMany(orphanRemoval = true)
    @JsonIgnore
    private List<ProductInventory> inventoryList;
}
