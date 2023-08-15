package com.example.store.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity(name = "STORE_REVIEWS")
@Table(name = "reviews")
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Reviews extends BaseEntity {

    @Column(name = "reviews")
    private String reviews;

    @Column(name = "grade")
    private Integer grade;

    @ManyToOne // у 1 продукта может быть много отзывов, у 1 отзыва - 1 продукт
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne // у одного отзыва может быть только 1 автор, у юзера может быть много отзывов
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDate createdDate;

}
