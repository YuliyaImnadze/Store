package com.example.store.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity(name = "STORE_DISCOUNT")
@Table(name = "discount")
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
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


}
