CREATE TABLE IF NOT EXISTS users
(
    id       UUID PRIMARY KEY,
    name     VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS catalogs
(
    id      UUID PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    user_id UUID,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS articles
(
    id         UUID PRIMARY KEY,
    title      VARCHAR(255) NOT NULL,
    content    TEXT         NOT NULL,
    catalog_id UUID,
    FOREIGN KEY (catalog_id) REFERENCES catalogs (id)
);