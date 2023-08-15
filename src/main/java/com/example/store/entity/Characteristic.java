package com.example.store.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "STORE_CHARACTERISTIC")
@Table(name = "characteristic")
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Characteristic extends BaseEntity {

    @Column(name = "weight")
    private Double weight;

    @Column(name = "country")
    private String country; // страна производитель

    @Column(name = "height")
    private Double height;

//    @OneToOne //(mappedBy = "characteristic")
//    @JoinColumn(name = "product_id", referencedColumnName = "id")
//    private Product product;


}
