package com.github.nberezhnykh.usersubscriptionservice.controller;

import com.github.nberezhnykh.usersubscriptionservice.dto.UserRequestDto;
import com.github.nberezhnykh.usersubscriptionservice.dto.UserResponseDto;
import com.github.nberezhnykh.usersubscriptionservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для управления пользователями.
 * Обрабатывает REST API запросы, связанные с пользователями.
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public final class UserController {

    /**
     * Сервис для работы с пользователями.
     */
    private final UserService userService;

    /**
     * Создает нового пользователя.
     *
     * @param userRequestDto Данные для создания пользователя.
     * @return ResponseEntity с созданным UserResponseDto и статусом CREATED (201).
     */
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(
            @RequestBody final UserRequestDto userRequestDto) {
        return new ResponseEntity<>(userService.createUser(userRequestDto), HttpStatus.CREATED);
    }

    /**
     * Получает информацию о пользователе по его ID.
     *
     * @param id ID пользователя.
     * @return ResponseEntity с UserResponseDto и статусом OK (200),
     * или NotFoundException, если пользователь не найден.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(
            @PathVariable final Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    /**
     * Обновляет информацию о пользователе.
     *
     * @param id             ID пользователя для обновления.
     * @param userRequestDto Данные для обновления пользователя.
     * @return ResponseEntity с обновленным UserResponseDto и статусом OK (200),
     * или NotFoundException, если пользователь не найден.
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable final Long id,
            @RequestBody final UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.updateUser(id, userRequestDto));
    }

    /**
     * Удаляет пользователя по его ID.
     *
     * @param id ID пользователя для удаления.
     * @return ResponseEntity со статусом NO_CONTENT (204).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable final Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
