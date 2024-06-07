package com.retailStore.productService.Repo;

import com.retailStore.productService.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product,String> {
    public Optional<List<Product>> findAllByName(String name);
}
