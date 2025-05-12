package com.github.nberezhnykh.usersubscriptionservice.service;

import com.github.nberezhnykh.usersubscriptionservice.dto.UserRequestDto;
import com.github.nberezhnykh.usersubscriptionservice.dto.UserResponseDto;

/**
 * Интерфейс сервиса для управления пользователями.
 * Определяет бизнес-логику, связанную с пользователями.
 */
public interface UserService {

    /**
     * Создает нового пользователя.
     *
     * @param userRequestDto DTO с данными для создания пользователя.
     * @return DTO созданного пользователя.
     */
    UserResponseDto createUser(UserRequestDto userRequestDto);

    /**
     * Получает информацию о пользователе по его ID.
     *
     * @param id ID пользователя.
     * @return DTO с информацией о пользователе.
     */
    UserResponseDto getUserById(Long id);

    /**
     * Обновляет информацию о пользователе.
     *
     * @param id             ID пользователя для обновления.
     * @param userRequestDto DTO с новыми данными пользователя.
     * @return DTO с обновленной информацией о пользователе.
     */
    UserResponseDto updateUser(Long id, UserRequestDto userRequestDto);

    /**
     * Удаляет пользователя по его ID.
     *
     * @param id ID пользователя для удаления.
     */
    void deleteUser(Long id);
}
