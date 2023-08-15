package com.example.store.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity(name = "STORE_NOTIFICATION")
@Table(name = "notification")
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
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

}
