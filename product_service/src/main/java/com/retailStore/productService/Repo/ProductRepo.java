package com.retailStore.productService.Repo;

import com.retailStore.productService.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product,String> {
    public Optional<List<Product>> findAllByName(String name);

    @Query("SELECT p FROM Product p WHERE p.name=:name and p.description=:des and p.ownedBy=:user ")
    public Optional<Product> getSavedProduct(@Param("name") String name,@Param("des") String des,@Param("user") String user);
}
