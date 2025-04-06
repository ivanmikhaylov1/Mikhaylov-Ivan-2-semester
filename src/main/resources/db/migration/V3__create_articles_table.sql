CREATE TABLE articles
(
    article_id  UUID PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    content     VARCHAR(500) NOT NULL,
    date        VARCHAR(255) NOT NULL,
    link        VARCHAR(255) NOT NULL,
    created_at  TIMESTAMP    NOT NULL,
    category_id UUID REFERENCES article_categories (id)
); 