CREATE TABLE catalogs
(
    catalog_id UUID PRIMARY KEY,
    name       VARCHAR(20) NOT NULL,
    user_id    UUID        NOT NULL REFERENCES users (id)
); 