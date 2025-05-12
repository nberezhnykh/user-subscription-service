# User Subscription Service

## Описание

Этот микросервис, разработанный на Spring Boot 3, предоставляет REST API для управления пользователями и их подписками на различные сервисы.

## Функциональные требования

* **API для управления пользователями:**
    * Создание пользователя.
    * Получение информации о пользователе.
    * Обновление данных пользователя.
    * Удаление пользователя.
* **API для подписок:**
    * Добавление подписки пользователю.
    * Получение списка подписок пользователя.
    * Удаление подписки.
    * Получение ТОП-3 популярных подписок.
* **Интеграция с базой данных:**
    * Используется PostgreSQL.
    * Схема данных: `users`, `subscriptions`.
* **Логирование:**
    * SLF4J.
* **Docker:**
    * Dockerfile для развертывания сервиса.
    * docker-compose.yml для локального запуска с PostgreSQL.

## API Endpoints

Основные API Endpoints:

* `POST /users` - Создать пользователя
* `GET /users/{id}` - Получить информацию о пользователе
* `PUT /users/{id}` - Обновить пользователя
* `DELETE /users/{id}` - Удалить пользователя
* `POST /users/{userId}/subscriptions` - Добавить подписку пользователю
* `GET /users/{userId}/subscriptions` - Получить подписки пользователя
* `DELETE /users/{userId}/subscriptions/{subscriptionId}` - Удалить подписку
* `GET /subscriptions/top` - Получить ТОП-3 популярных подписок

**Для просмотра интерактивной документации API используйте Swagger UI, который доступен по адресу:**

* `http://localhost:8080/swagger-ui.html`

## Технологии

* Java 17
* Spring Boot 3
* Spring Data JPA
* PostgreSQL
* SLF4J
* Lombok
* MapStruct
* Flyway
* Docker
* Docker Compose

## Сборка и запуск

1.  **Клонирование репозитория:**

    ```bash
    git clone https://github.com/nberezhnykh/user-subscription-service.git
    cd user-subscription-service
    ```

2.  **Сборка приложения:**

    ```bash
    ./gradlew build
    ```

3.  **Запуск с использованием Docker Compose (рекомендуется):**

    * Установите Docker и Docker Compose.
    * Запустите контейнеры:

        ```bash
        docker-compose up -d --build
        ```

    * Приложение будет доступно по адресу: `http://localhost:8080`

4.  **Запуск без Docker:**

    * Установите PostgreSQL и создайте базу данных `user_subscription_db`.
    * Настройте параметры подключения в `src/main/resources/application.properties`.
    * Запустите приложение:

        ```bash
        ./gradlew bootRun
        ```

    * Приложение будет доступно по адресу: `http://localhost:8080`

## Настройка

* **Переменные окружения (Docker):**
    * `POSTGRES_DB` - Имя базы данных PostgreSQL.
    * `POSTGRES_USER` - Имя пользователя PostgreSQL.
    * `POSTGRES_PASSWORD` - Пароль пользователя PostgreSQL.
    * `SPRING_DATASOURCE_URL` - URL для подключения к базе данных.
    * `SPRING_DATASOURCE_USERNAME` - Имя пользователя базы данных.
    * `SPRING_DATASOURCE_PASSWORD` - Пароль пользователя базы данных.

## Примечания

* В данном проекте не реализована интеграция с внешними API сервисов подписок (YouTube, Netflix и т. д.). Подписки рассматриваются как сущности в рамках микросервиса.

---