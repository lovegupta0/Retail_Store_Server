package com.retailStore.productService.Repo;

import com.retailStore.productService.Model.Product;
import com.retailStore.productService.Model.ProductInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductInventoryRepo extends JpaRepository<ProductInventory,String> {
    public Optional<List<ProductInventory>> findAllByProduct(Product product);
}
