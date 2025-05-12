package com.github.nberezhnykh.usersubscriptionservice.repository;

import com.github.nberezhnykh.usersubscriptionservice.dto.TopSubscriptionDto;
import com.github.nberezhnykh.usersubscriptionservice.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для управления подписками в базе данных.
 */
@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    /**
     * Находит все подписки, принадлежащие указанному пользователю.
     *
     * @param userId ID пользователя.
     * @return Список подписок пользователя.
     */
    List<Subscription> findByUserId(Long userId);

    /**
     * Находит ТОП-3 самых популярных подписок на основе количества их упоминаний.
     * Использует нативный SQL-запрос для агрегации данных.
     *
     * @return Список DTO, содержащих название подписки и количество её упоминаний.
     */
    @Query(value = "SELECT s.name as name, COUNT(s.name) as count FROM subscriptions s GROUP BY s.name ORDER BY count DESC LIMIT 3", nativeQuery = true)
    List<TopSubscriptionDto> findTop3PopularSubscriptions();
}
