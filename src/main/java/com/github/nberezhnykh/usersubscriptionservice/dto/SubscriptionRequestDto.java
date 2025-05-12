package com.github.nberezhnykh.usersubscriptionservice.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) для запроса на создание/обновление подписки.
 */
@Data
public class SubscriptionRequestDto {

    /**
     * Название подписки.
     */
    private String name;

    /**
     * ID пользователя, которому принадлежит подписка.
     */
    private Long userId;
}
