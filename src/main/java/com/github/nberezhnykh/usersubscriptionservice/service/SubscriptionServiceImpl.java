package com.github.nberezhnykh.usersubscriptionservice.service;

import com.github.nberezhnykh.usersubscriptionservice.dto.SubscriptionRequestDto;
import com.github.nberezhnykh.usersubscriptionservice.dto.SubscriptionResponseDto;
import com.github.nberezhnykh.usersubscriptionservice.dto.TopSubscriptionDto;
import com.github.nberezhnykh.usersubscriptionservice.exception.SubscriptionNotFoundException;
import com.github.nberezhnykh.usersubscriptionservice.exception.UserNotFoundException;
import com.github.nberezhnykh.usersubscriptionservice.exception.UserNotFoundForSubscriptionException;
import com.github.nberezhnykh.usersubscriptionservice.model.Subscription;
import com.github.nberezhnykh.usersubscriptionservice.repository.SubscriptionRepository;
import com.github.nberezhnykh.usersubscriptionservice.repository.UserRepository;
import com.github.nberezhnykh.usersubscriptionservice.mapper.SubscriptionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса для управления подписками.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionServiceImpl implements SubscriptionService {

    /**
     * Репозиторий для работы с подписками.
     */
    private final SubscriptionRepository subscriptionRepository;

    /**
     * Репозиторий для работы с пользователями.
     */
    private final UserRepository userRepository;

    /**
     * Маппер для преобразования между Subscription и DTO.
     */
    private final SubscriptionMapper subscriptionMapper;

    /**
     * Создает новую подписку для указанного пользователя.
     *
     * @param userId                 ID пользователя, которому добавляется подписка.
     * @param subscriptionRequestDto DTO с данными для создания подписки.
     * @return DTO созданной подписки.
     * @throws UserNotFoundForSubscriptionException если пользователь с указанным ID не найден.
     */
    @Override
    public SubscriptionResponseDto createSubscription(final Long userId, final SubscriptionRequestDto subscriptionRequestDto) {
        log.info("Creating subscription for user ID {}: {}", userId, subscriptionRequestDto);
        return userRepository.findById(userId)
                .map(user -> {
                    final Subscription subscription = subscriptionMapper.fromRequestDto(subscriptionRequestDto);
                    subscription.setUser(user);
                    final Subscription savedSubscription = subscriptionRepository.save(subscription);
                    final SubscriptionResponseDto responseDto = subscriptionMapper.toResponseDto(savedSubscription);
                    log.info("Created subscription with ID: {} for user ID: {}", responseDto.getId(), user.getId());
                    return responseDto;
                })
                .orElseThrow(() -> new UserNotFoundForSubscriptionException(userId, UserNotFoundForSubscriptionException.getCreatingOperation()));
    }

    /**
     * Получает список всех подписок указанного пользователя.
     *
     * @param userId ID пользователя, чьи подписки необходимо получить.
     * @return Список DTO подписок пользователя.
     */
    @Override
    public List<SubscriptionResponseDto> getSubscriptionsByUserId(final Long userId) {
        log.info("Getting subscriptions for user ID: {}", userId);
        return subscriptionRepository.findByUserId(userId).stream()
                .map(subscriptionMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * Удаляет указанную подписку у указанного пользователя.
     *
     * @param userId         ID пользователя, у которого необходимо удалить подписку.
     * @param subscriptionId ID подписки, которую необходимо удалить.
     * @throws UserNotFoundException        если пользователь с указанным ID не найден.
     * @throws SubscriptionNotFoundException если подписка с указанным ID не найдена
     * или если подписка не принадлежит указанному пользователю.
     */
    @Override
    public void deleteSubscriptionFromUser(final Long userId, final Long subscriptionId) {
        log.info("Deleting subscription with ID {} for user ID: {}", subscriptionId, userId);

        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        final Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new SubscriptionNotFoundException(subscriptionId));

        if (!subscription.getUser().getId().equals(userId)) {
            log.warn("Subscription with ID {} does not belong to user with ID {}", subscriptionId, userId);
            throw new SubscriptionNotFoundException(subscriptionId);
        }

        subscriptionRepository.deleteById(subscriptionId);
        log.info("Deleted subscription with ID {} for user ID: {}", subscriptionId, userId);
    }

    /**
     * Получает список ТОП-3 самых популярных подписок.
     *
     * @return Список DTO, представляющих самые популярные подписки.
     */
    @Override
    public List<SubscriptionResponseDto> getTopSubscriptions() {
        log.info("Getting top subscriptions");

        final List<TopSubscriptionDto> topSubscriptions = subscriptionRepository.findTop3PopularSubscriptions();

        return topSubscriptions.stream()
                .map(dto -> {
                    final SubscriptionResponseDto responseDto = new SubscriptionResponseDto();
                    responseDto.setName(dto.getName());
                    responseDto.setId(null);
                    return responseDto;
                })
                .collect(Collectors.toList());
    }
}
