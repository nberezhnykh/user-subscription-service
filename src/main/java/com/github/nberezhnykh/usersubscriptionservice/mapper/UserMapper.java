package com.github.nberezhnykh.usersubscriptionservice.mapper;

import com.github.nberezhnykh.usersubscriptionservice.dto.UserRequestDto;
import com.github.nberezhnykh.usersubscriptionservice.dto.UserResponseDto;
import com.github.nberezhnykh.usersubscriptionservice.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Маппер для преобразования между User и DTO.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Преобразует User в UserResponseDto.
     *
     * @param user Объект User для преобразования.
     * @return UserResponseDto, представляющий информацию о пользователе.
     */
    UserResponseDto toResponseDto(User user);

    /**
     * Преобразует UserRequestDto в User.
     * Игнорирует поле id при преобразовании.
     *
     * @param userRequestDto DTO, содержащий данные для создания пользователя.
     * @return User, созданный на основе DTO.
     */
    @Mapping(target = "id", ignore = true)
    User fromRequestDto(UserRequestDto userRequestDto);

    /**
     * Обновляет User на основе UserRequestDto.
     * Игнорирует поле id при преобразовании.
     *
     * @param userRequestDto DTO, содержащий данные для обновления пользователя.
     * @param user           User, который необходимо обновить.
     */
    @Mapping(target = "id", ignore = true)
    void updateUserFromDto(UserRequestDto userRequestDto, @MappingTarget User user);
}
