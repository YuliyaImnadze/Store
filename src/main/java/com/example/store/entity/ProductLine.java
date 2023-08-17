package com.example.store.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Entity(name = "STORE_PRODUCT_LINE")
@Table(name = "product_line")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductLine extends BaseEntity { // Покупка + еще одна сущность, кот будет содержать лист покупок
    // если сущность будет когда то сериализ, то нужно делать INTEGER и пр

    @ManyToOne  // у одного прод.лайна 1 продукт. у 1 продукта много прод.лайнов
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "count")
    private Integer count;

    @Column(name = "totalSum")
//    @Transient - не транзиентн?
    private BigDecimal totalSum;

    // у 1 прод.л. 1 компания, у 1 компании много прод.л
    @ManyToOne 
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne  // (cascade = CascadeType.ALL) проверить с персист
    @JoinColumn(name = "purchase_id")
    private Purchase purchase; // у 1 пролукт лайна может быть только 1 покупка. у 1 пок. много прод.лайнов

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductLine that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getCount(), that.getCount()) && Objects.equals(getTotalSum(), that.getTotalSum());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCount(), getTotalSum());
    }
}


//
//    public BigDecimal calculateTotalSum() {  // где должен быть метод и когда его вызывать
//        if (product != null && count != null) {
//            BigDecimal productPrice = product.getPrice();
//            totalSum = productPrice.multiply(BigDecimal.valueOf(count));
//        } else {
//            totalSum = BigDecimal.ZERO;
//        }
//        return totalSum;
//    }

//}
