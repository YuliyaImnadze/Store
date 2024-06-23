package com.example.store.repository;

import com.example.store.entity.Purchase;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends CommonRepository<Purchase> {

    @Query("select p from STORE_PURCHASE p where p.buyer.id in " +
            "(select u.id from STORE_USER u where u.email = :email)")
    List<Purchase> findByBuyerByEmail(@Param("email") String email);





}
