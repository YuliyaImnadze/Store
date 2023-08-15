package com.example.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "STORE_COMPANY")
@Table(name = "company")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
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

}
