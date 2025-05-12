package com.github.nberezhnykh.usersubscriptionservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение, выбрасываемое при попытке операции с подпиской для несуществующего пользователя.
 * Возвращает HTTP статус 400 (BAD_REQUEST).
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserNotFoundForSubscriptionException extends RuntimeException {

    /**
     * Константа, представляющая операцию создания.
     */
    private static final String CREATING_OPERATION = "creating";

    /**
     * Конструктор исключения.
     *
     * @param userId    ID пользователя, который не найден.
     * @param operation Тип операции (например, "creating", "updating"), при которой произошла ошибка.
     */
    public UserNotFoundForSubscriptionException(final Long userId, final String operation) {
        super("User with id " + userId + " not found when " + operation + " subscription");
    }

    /**
     * Возвращает строку, представляющую операцию создания.
     *
     * @return Строка "creating".
     */
    public static String getCreatingOperation() {
        return CREATING_OPERATION;
    }

    /**
     * Можно добавить метод для получения строки "updating" и других операций, если это потребуется.
     * public static String getUpdatingOperation() {
     * return UPDATING_OPERATION;
     * }
     */
}
