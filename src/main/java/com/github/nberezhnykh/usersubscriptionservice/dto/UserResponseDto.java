package com.github.nberezhnykh.usersubscriptionservice.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) для ответа, содержащего информацию о пользователе.
 */
@Data
public class UserResponseDto {

    /**
     * Уникальный идентификатор пользователя.
     */
    private Long id;

    /**
     * Имя пользователя.
     */
    private String name;

    /**
     * Электронная почта пользователя.
     */
    private String email;
}
