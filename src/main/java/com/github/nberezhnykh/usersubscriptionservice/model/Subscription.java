package com.github.nberezhnykh.usersubscriptionservice.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущность, представляющая подписку пользователя.
 */
@Entity
@Table(name = "subscriptions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {

    /**
     * Уникальный идентификатор подписки.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Название подписки (например, "YouTube Premium", "Netflix").
     */
    private String name;

    /**
     * Пользователь, которому принадлежит данная подписка.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
