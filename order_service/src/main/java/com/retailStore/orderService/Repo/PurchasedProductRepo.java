package com.retailStore.orderService.Repo;
import java.util.*;

import com.retailStore.orderService.Model.OrderDetails;
import com.retailStore.orderService.Model.PurchasedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchasedProductRepo extends JpaRepository<PurchasedProduct,String> {
    public List<PurchasedProduct> findPurchasedProductByOrderDetails(OrderDetails details);
}
