package com.example.store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity(name = "STORE_CHARACTERISTIC")
@Table(name = "characteristic")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Characteristic extends BaseEntity {

    @Column(name = "weight")
    private Double weight;

    @Column(name = "country")
    private String country; // страна производитель

    @Column(name = "height")
    private Double height;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Characteristic that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getWeight(), that.getWeight()) && Objects.equals(getCountry(), that.getCountry()) && Objects.equals(getHeight(), that.getHeight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getWeight(), getCountry(), getHeight());
    }




//        @Override
//    public final boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null) return false;
//        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
//        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
//        if (thisEffectiveClass != oEffectiveClass) return false;
//        Characteristic that = (Characteristic) o;
//        return getId() != null && Objects.equals(getId(), that.getId());
//    }
//
//    @Override
//    public final int hashCode() {
//        return getClass().hashCode();
//    }
}
