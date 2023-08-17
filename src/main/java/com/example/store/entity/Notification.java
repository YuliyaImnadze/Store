package com.example.store.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "STORE_NOTIFICATION")
@Table(name = "notification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification extends BaseEntity {

    @Column(name = "header")
    private String header;

    @Column(name = "date")
    @CreationTimestamp
    private LocalDate date;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "recipient_id") // односторонняя связь. проверила
    private User recipient;


    @ManyToOne
    @JoinColumn(name = "sender_id") // нужен он вообще или нет? отправка же с email магазина
    private User sender;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notification that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getHeader(), that.getHeader()) && Objects.equals(getDate(), that.getDate()) && Objects.equals(getText(), that.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getHeader(), getDate(), getText());
    }
}
