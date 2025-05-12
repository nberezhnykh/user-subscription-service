package com.github.nberezhnykh.usersubscriptionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object (DTO) для представления информации о популярной подписке.
 */
@Data
@AllArgsConstructor
public class TopSubscriptionDto {

    /**
     * Название подписки.
     */
    private String name;

    /**
     * Количество упоминаний подписки (популярность).
     */
    private Long count;
}

