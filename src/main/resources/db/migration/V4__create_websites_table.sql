CREATE TABLE websites
(
    website_id UUID PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    url        VARCHAR(255) NOT NULL,
    user_id    UUID         NOT NULL REFERENCES users (id)
); 