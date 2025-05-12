package com.github.nberezhnykh.usersubscriptionservice.service;

import com.github.nberezhnykh.usersubscriptionservice.dto.UserRequestDto;
import com.github.nberezhnykh.usersubscriptionservice.dto.UserResponseDto;
import com.github.nberezhnykh.usersubscriptionservice.exception.UserNotFoundException;
import com.github.nberezhnykh.usersubscriptionservice.model.User;
import com.github.nberezhnykh.usersubscriptionservice.repository.UserRepository;
import com.github.nberezhnykh.usersubscriptionservice.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Реализация сервиса для управления пользователями.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    /**
     * Репозиторий для работы с пользователями.
     */
    private final UserRepository userRepository;

    /**
     * Маппер для преобразования между User и DTO.
     */
    private final UserMapper userMapper;

    /**
     * Создает нового пользователя.
     *
     * @param userRequestDto DTO с данными для создания пользователя.
     * @return DTO созданного пользователя.
     */
    @Override
    public UserResponseDto createUser(final UserRequestDto userRequestDto) {
        log.info("Creating user: {}", userRequestDto);
        final User user = userMapper.fromRequestDto(userRequestDto);
        final User savedUser = userRepository.save(user);
        final UserResponseDto responseDto = userMapper.toResponseDto(savedUser);
        log.info("Created user with ID: {}", responseDto.getId());
        return responseDto;
    }

    /**
     * Получает информацию о пользователе по его ID.
     *
     * @param id ID пользователя.
     * @return DTO с информацией о пользователе.
     * @throws UserNotFoundException если пользователь с указанным ID не найден.
     */
    @Override
    public UserResponseDto getUserById(final Long id) {
        log.info("Getting user by ID: {}", id);
        return userRepository.findById(id)
                .map(userMapper::toResponseDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * Обновляет информацию о пользователе.
     *
     * @param id             ID пользователя для обновления.
     * @param userRequestDto DTO с новыми данными пользователя.
     * @return DTO с обновленной информацией о пользователе.
     * @throws UserNotFoundException если пользователь с указанным ID не найден.
     */
    @Override
    public UserResponseDto updateUser(final Long id, final UserRequestDto userRequestDto) {
        log.info("Updating user with ID {}: {}", id, userRequestDto);
        return userRepository.findById(id)
                .map(existingUser -> {
                    userMapper.updateUserFromDto(userRequestDto, existingUser);
                    final User updatedUser = userRepository.save(existingUser);
                    return userMapper.toResponseDto(updatedUser);
                })
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * Удаляет пользователя по его ID.
     *
     * @param id ID пользователя для удаления.
     * @throws UserNotFoundException если пользователь с указанным ID не найден.
     */
    @Override
    public void deleteUser(final Long id) {
        log.info("Deleting user with ID: {}", id);
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        log.info("Deleted user with ID: {}", id);
    }
}
