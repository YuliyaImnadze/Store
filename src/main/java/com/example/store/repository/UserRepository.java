package com.example.store.repository;

import com.example.store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CommonRepository<User> {
        @Query("SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END " +
            "FROM STORE_PURCHASE p " +
            "INNER JOIN p.productList pl " + // можно через on
            "WHERE p.buyer.id = :userId AND pl.product.id = :productId")
    boolean existsProductIdByUserId(@Param("userId") UUID userId, @Param("productId") UUID productId);

}
