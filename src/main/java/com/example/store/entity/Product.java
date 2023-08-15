package com.example.store.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity(name = "STORE_PRODUCT")
@Table(name = "product")
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

    public Product() {
    }

    public Product(String name, String description, Company supplier, BigDecimal price, Integer quantity, Discount discount, List<Reviews> reviewsList, Characteristic characteristic) {
        this.name = name;
        this.description = description;
        this.supplier = supplier;
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
        this.reviewsList = reviewsList;
        this.characteristic = characteristic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Company getSupplier() {
        return supplier;
    }

    public void setSupplier(Company supplier) {
        this.supplier = supplier;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public List<Reviews> getReviewsList() {
        return reviewsList;
    }

    public void setReviewsList(List<Reviews> reviewsList) {
        this.reviewsList = reviewsList;
    }

    public Characteristic getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(Characteristic characteristic) {
        this.characteristic = characteristic;
    }

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
