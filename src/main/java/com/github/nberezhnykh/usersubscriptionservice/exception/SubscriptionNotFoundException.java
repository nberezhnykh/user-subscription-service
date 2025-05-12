package com.github.nberezhnykh.usersubscriptionservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение, выбрасываемое при попытке доступа к несуществующей подписке.
 * Возвращает HTTP статус 404 (NOT_FOUND).
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class SubscriptionNotFoundException extends RuntimeException {

    /**
     * Конструктор исключения.
     *
     * @param id ID несуществующей подписки.
     */
    public SubscriptionNotFoundException(final Long id) {
        super("Subscription not found with id: " + id);
    }
}
