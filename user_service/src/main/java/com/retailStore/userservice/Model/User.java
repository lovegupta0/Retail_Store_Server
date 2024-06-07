package com.retailStore.userservice.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(generator = "uidGenerator")
    @GenericGenerator(name = "uidGenerator",strategy = "com.retailStore.userservice.Config.UIDGenerator")
    @Column(updatable = false, nullable = false)
    private String uid;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String firstName;
    private String lastName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String role;
    @OneToMany(orphanRemoval = true)
    @JsonIgnore
    private List<Product> products;
    @OneToMany(orphanRemoval = true)
    @JsonIgnore
    private List<ProductInventory> inventoryList;
    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Employee employee;

}
