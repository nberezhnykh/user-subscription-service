package com.github.nberezhnykh.usersubscriptionservice.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) для запроса на создание/обновление пользователя.
 */
@Data
public class UserRequestDto {

    /**
     * Имя пользователя.
     */
    private String name;

    /**
     * Электронная почта пользователя.
     */
    private String email;
}
