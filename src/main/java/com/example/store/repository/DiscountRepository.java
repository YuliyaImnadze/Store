package com.example.store.repository;

import com.example.store.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface DiscountRepository extends CommonRepository<Discount> {

    @Query("SELECT d FROM STORE_DISCOUNT d WHERE d.discountPeriod < ?1 AND d.active = true")
    List<Discount> findByDiscountPeriodLessThan(LocalDate currentDate);

    // переопределить delete (by Id) (All)
    //

    // дописать этот метод и вместо того, что выше. чтобы не удалять - добавить колонку с флагом
//    @Query("UPDATE STORE_DISCOUNT SET d WHERE d.discountPeriod < ?1")
//    void updateByDiscountPeriodLessThan(LocalDate currentDate);

}
