CREATE TABLE IF NOT EXISTS users
(
    id       UUID PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS articles
(
    id         UUID PRIMARY KEY,
    title      VARCHAR(255) NOT NULL,
    content    TEXT         NOT NULL,
    author_id  UUID REFERENCES users (id),
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);