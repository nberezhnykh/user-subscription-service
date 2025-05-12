package com.github.nberezhnykh.usersubscriptionservice.controller;

import com.github.nberezhnykh.usersubscriptionservice.dto.SubscriptionRequestDto;
import com.github.nberezhnykh.usersubscriptionservice.dto.SubscriptionResponseDto;
import com.github.nberezhnykh.usersubscriptionservice.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Контроллер для управления подписками.
 * Обрабатывает REST API запросы, связанные с подписками пользователей.
 */
@RestController
@RequiredArgsConstructor
public final class SubscriptionController {

    /**
     * Сервис для работы с подписками.
     */
    private final SubscriptionService subscriptionService;

    /**
     * Создает новую подписку для пользователя.
     *
     * @param userId ID пользователя, которому добавляется подписка.
     * @param subscriptionRequestDto Данные для создания подписки.
     * @return ResponseEntity с созданной SubscriptionResponseDto и статусом
     * CREATED (201), или BadRequestException, если пользователь не найден.
     */
    @PostMapping("/users/{userId}/subscriptions")
    public ResponseEntity<SubscriptionResponseDto> createSubscription(
            @PathVariable final Long userId,
            @RequestBody final SubscriptionRequestDto subscriptionRequestDto) {
        SubscriptionResponseDto createdSubscription =
                subscriptionService.createSubscription(userId, subscriptionRequestDto);
        return new ResponseEntity<>(createdSubscription, HttpStatus.CREATED);
    }

    /**
     * Получает список подписок пользователя по его ID.
     *
     * @param userId ID пользователя, для которого требуется получить список подписок.
     * @return ResponseEntity с списком SubscriptionResponseDto и статусом OK (200).
     */
    @GetMapping("/users/{userId}/subscriptions")
    public ResponseEntity<List<SubscriptionResponseDto>> getSubscriptionsByUserId(
            @PathVariable final Long userId) {
        return ResponseEntity.ok(subscriptionService.getSubscriptionsByUserId(userId));
    }

    /**
     * Удаляет подписку пользователя по ID пользователя и ID подписки.
     *
     * @param userId         ID пользователя, у которого удаляется подписка.
     * @param subscriptionId ID подписки, которую необходимо удалить.
     * @return ResponseEntity со статусом NO_CONTENT (204) в случае успешного удаления.
     */
    @DeleteMapping("/users/{userId}/subscriptions/{subscriptionId}")
    public ResponseEntity<Void> deleteSubscriptionFromUser(
            @PathVariable final Long userId,
            @PathVariable final Long subscriptionId) {
        subscriptionService.deleteSubscriptionFromUser(userId, subscriptionId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Получает ТОП-3 популярных подписок.
     *
     * @return ResponseEntity с списком TopSubscriptionDto и статусом OK (200).
     */
    @GetMapping("/top")
    public ResponseEntity<List<SubscriptionResponseDto>> getTopSubscriptions() {
        return ResponseEntity.ok(subscriptionService.getTopSubscriptions());
    }
}
