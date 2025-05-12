package com.github.nberezhnykh.usersubscriptionservice.mapper;

import com.github.nberezhnykh.usersubscriptionservice.dto.SubscriptionRequestDto;
import com.github.nberezhnykh.usersubscriptionservice.dto.SubscriptionResponseDto;
import com.github.nberezhnykh.usersubscriptionservice.model.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Маппер для преобразования между Subscription и DTO.
 */
@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    /**
     * Преобразует Subscription в SubscriptionResponseDto.
     *
     * @param subscription Объект Subscription для преобразования.
     * @return SubscriptionResponseDto, представляющий информацию о подписке.
     */
    @Mapping(source = "user.id", target = "userId")
    SubscriptionResponseDto toResponseDto(Subscription subscription);

    /**
     * Преобразует SubscriptionRequestDto в Subscription.
     * Игнорирует поля id и user при преобразовании.
     *
     * @param subscriptionRequestDto DTO, содержащий данные для создания подписки.
     * @return Subscription, созданный на основе DTO.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Subscription fromRequestDto(SubscriptionRequestDto subscriptionRequestDto);
}
