package com.example.store.repository;

import com.example.store.entity.Discount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DiscountRepository extends CommonRepository<Discount> {

    @Modifying
    @Query("UPDATE STORE_PRODUCT p SET p.discount = null WHERE p.discount.id IN (" +
            "SELECT d.id FROM STORE_DISCOUNT d WHERE d.discountPeriod < ?1 AND d.active = true)")
    void removeExpiredDiscountsFromProducts(LocalDate currentDate);

    @Modifying
    @Query("UPDATE STORE_DISCOUNT d SET d.active = false WHERE d.discountPeriod < ?1 AND d.active = true")
    void deactivateExpiredDiscounts(LocalDate currentDate);


}
