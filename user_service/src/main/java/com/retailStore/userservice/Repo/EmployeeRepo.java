package com.retailStore.userservice.Repo;

import com.retailStore.userservice.Model.Employee;
import com.retailStore.userservice.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,String> {
    public Optional<Employee> findByUser(User user);
}
