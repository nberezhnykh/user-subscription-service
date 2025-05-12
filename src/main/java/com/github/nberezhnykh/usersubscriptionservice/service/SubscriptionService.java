package com.github.nberezhnykh.usersubscriptionservice.service;

import com.github.nberezhnykh.usersubscriptionservice.dto.SubscriptionRequestDto;
import com.github.nberezhnykh.usersubscriptionservice.dto.SubscriptionResponseDto;

import java.util.List;

/**
 * Интерфейс сервиса для управления подписками.
 * Определяет бизнес-логику, связанную с подписками пользователей.
 */
public interface SubscriptionService {

    /**
     * Создает новую подписку для указанного пользователя.
     *
     * @param userId                 ID пользователя, которому добавляется подписка.
     * @param subscriptionRequestDto DTO с данными для создания подписки.
     * @return DTO созданной подписки.
     */
    SubscriptionResponseDto createSubscription(Long userId, SubscriptionRequestDto subscriptionRequestDto);

    /**
     * Получает список всех подписок указанного пользователя.
     *
     * @param userId ID пользователя, чьи подписки необходимо получить.
     * @return Список DTO подписок пользователя.
     */
    List<SubscriptionResponseDto> getSubscriptionsByUserId(Long userId);

    /**
     * Удаляет указанную подписку у указанного пользователя.
     *
     * @param userId         ID пользователя, у которого необходимо удалить подписку.
     * @param subscriptionId ID подписки, которую необходимо удалить.
     */
    void deleteSubscriptionFromUser(Long userId, Long subscriptionId);

    /**
     * Получает список ТОП-3 самых популярных подписок.
     *
     * @return Список DTO, представляющих самые популярные подписки.
     */
    List<SubscriptionResponseDto> getTopSubscriptions();
}
