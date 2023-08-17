package com.example.store.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity(name = "STORE_DISCOUNT")
@Table(name = "discount")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Discount extends BaseEntity {

    @OneToMany(mappedBy = "discount", fetch = FetchType.EAGER) // проверила
    private Set<Product> discountedProducts;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "discountPeriod")
//    @Future
    private LocalDate discountPeriod;

    @Column(name = "created_date")
    @CreationTimestamp // проверить
    private LocalDate createdDate;

    @Column(name = "active")
    private Boolean active = Boolean.TRUE;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Discount discount1)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getDiscountedProducts(), discount1.getDiscountedProducts()) && Objects.equals(getDiscount(), discount1.getDiscount()) && Objects.equals(getDiscountPeriod(), discount1.getDiscountPeriod()) && Objects.equals(getCreatedDate(), discount1.getCreatedDate()) && Objects.equals(getActive(), discount1.getActive());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDiscountedProducts(), getDiscount(), getDiscountPeriod(), getCreatedDate(), getActive());
    }
}
