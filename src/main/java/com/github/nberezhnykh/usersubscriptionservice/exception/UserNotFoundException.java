package com.github.nberezhnykh.usersubscriptionservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение, выбрасываемое при попытке доступа к несуществующему пользователю.
 * Возвращает HTTP статус 404 (NOT_FOUND).
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    /**
     * Конструктор исключения.
     *
     * @param id ID несуществующего пользователя.
     */
    public UserNotFoundException(final Long id) {
        super("User not found with id: " + id);
    }
}
