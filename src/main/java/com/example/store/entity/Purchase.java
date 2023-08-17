package com.example.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

// чтобы создать покупку, нужно передать несколько продук.лайнов. потом у юзера вычесть всю сумму. а по организации по
// продавцам перевести суммы
// тогда у продукт.лайна не нужен юзер. если потом искать все покупки юзера

@Entity(name = "STORE_PURCHASE")
@Table(name = "purchase")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Purchase extends BaseEntity {

    // по id продукт-лайна можно найти лист продукт лайнов и у листа определить юзера
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL) // (mappedBy = "purchase")
    private Set<ProductLine> productList; // у одной покупки может быть много прод.лайнов

    @Column(name = "product_list_amount")
    private BigDecimal productListAmount; // 85r
    // должен быть юзер или нет? в прод лайне же есть уже

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;

    @Column(name = "created_date")
    @CreationTimestamp // проверить
    private LocalDate createdDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchase purchase)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getProductListAmount(), purchase.getProductListAmount()) && Objects.equals(getCreatedDate(), purchase.getCreatedDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getProductListAmount(), getCreatedDate());
    }
}
