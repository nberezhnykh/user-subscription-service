package com.github.nberezhnykh.usersubscriptionservice.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) для ответа, содержащего информацию о подписке.
 */
@Data
public class SubscriptionResponseDto {

    /**
     * Уникальный идентификатор подписки.
     */
    private Long id;

    /**
     * Название подписки.
     */
    private String name;

    /**
     * ID пользователя, которому принадлежит подписка.
     */
    private Long userId;
}
