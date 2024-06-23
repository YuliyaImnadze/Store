package com.example.store.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity (name = "STORE_PRODUCT")
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Company supplier;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "quantity")
    @Min(0) // не должно быть отрицат
    private Integer quantity; // на складе

    @ManyToOne // у одного товара может быть 1 скидка. у 1 скидки может быть несколько товаров. проверила
    @JoinColumn(name = "discount_id", referencedColumnName = "id")
    private Discount discount;

    @OneToMany(mappedBy = "product") // у одного товара может быть много отзывов. в 1 отзыве может содерж инф только об 1 товаре
    private List<Reviews> reviewsList; // Отзывы

//    не знаю как
//    private List<Keyword> keywords;


    @OneToOne (cascade = CascadeType.ALL)// у одного товара может быть только 1 характеристика. у одной хар-ки только 1 товар
    @JoinColumn(name = "characteristic_id")
    private Characteristic characteristic;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getName(), product.getName()) && Objects.equals(getDescription(), product.getDescription()) && Objects.equals(getPrice(), product.getPrice()) && Objects.equals(getQuantity(), product.getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName(), getDescription(), getPrice(), getQuantity());
    }
}
