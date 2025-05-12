package com.github.nberezhnykh.usersubscriptionservice.repository;

import com.github.nberezhnykh.usersubscriptionservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для управления пользователями в базе данных.
 * Предоставляет стандартные операции CRUD, унаследованные от JpaRepository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
