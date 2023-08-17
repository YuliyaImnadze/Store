package com.example.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

@Entity(name = "STORE_COMPANY")
@Table(name = "company")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "logo")
    private String logo;

    @JsonIgnore
    @OneToMany(mappedBy = "supplier") // у одной компании может быть много товаров, а у товара может быть только 1 комп
    private List<Product> productList;

    @ManyToOne   //(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id") // у одного юзера может быть много компаний. у 1 комп может быть только 1 юзер
    private User owner;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company company)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getName(), company.getName()) && Objects.equals(getDescription(), company.getDescription()) && Objects.equals(getLogo(), company.getLogo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName(), getDescription(), getLogo());
    }
}
