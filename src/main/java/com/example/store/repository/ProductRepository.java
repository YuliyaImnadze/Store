package com.example.store.repository;

import com.example.store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ProductRepository extends CommonRepository<Product> {

    Set<Product> findAllByIdIn(Set<UUID> productIds);

    @Query("SELECT p FROM STORE_PRODUCT p WHERE p.supplier.id IN (SELECT c.id FROM STORE_COMPANY c WHERE c.active = true)")
    List<Product> findProductsFromActiveCompanies();

}
