CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(255),
                       email VARCHAR(255)
);

CREATE TABLE subscriptions (
                               id BIGSERIAL PRIMARY KEY,
                               name VARCHAR(255),
                               user_id BIGINT REFERENCES users(id)
);

CREATE INDEX idx_subscription_name ON subscriptions (name);