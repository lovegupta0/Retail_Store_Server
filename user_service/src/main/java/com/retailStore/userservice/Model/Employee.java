package com.retailStore.userservice.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee{
    @Id
    @GeneratedValue(generator = "uidGenerator")
    @GenericGenerator(name = "uidGenerator",strategy = "com.retailStore.userservice.Config.UIDGenerator")
    private String uid;
    private double salary;

    private String mobileNo;
    private String role;
    private LocalDate joiningDate=LocalDate.now();
    @OneToOne(orphanRemoval = true)
    @JsonIgnore
    private User user;

    public void setJoiningDate(LocalDate date) {
    }
}
