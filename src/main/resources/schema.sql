CREATE TABLE IF NOT EXISTS users (
                                     id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                     name VARCHAR(255) NOT NULL UNIQUE,
                                     password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS catalogs (
                                        catalog_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                        name VARCHAR(255) NOT NULL,
                                        user_id UUID NOT NULL,
                                        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS websites (
                                        website_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                        name VARCHAR(255) NOT NULL,
                                        url VARCHAR(255) NOT NULL,
                                        user_id UUID NOT NULL,
                                        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS article_website (
                                               article_id UUID NOT NULL,
                                               website_id UUID NOT NULL,
                                               PRIMARY KEY (article_id, website_id),
                                               FOREIGN KEY (article_id) REFERENCES articles(article_id) ON DELETE CASCADE,
                                               FOREIGN KEY (website_id) REFERENCES websites(website_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS articles (
                                        article_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                        title VARCHAR(255) NOT NULL,
                                        content TEXT NOT NULL,
                                        date DATE NOT NULL,
                                        link TEXT NOT NULL,
                                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Таблица связи "статья-каталог" (многие ко многим)
CREATE TABLE IF NOT EXISTS article_catalog (
                                               article_id UUID NOT NULL,
                                               catalog_id UUID NOT NULL,
                                               PRIMARY KEY (article_id, catalog_id),
                                               FOREIGN KEY (article_id) REFERENCES articles(article_id) ON DELETE CASCADE,
                                               FOREIGN KEY (catalog_id) REFERENCES catalogs(catalog_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS user_last_request (
                                                 user_id UUID PRIMARY KEY,
                                                 last_request_time TIMESTAMP NOT NULL,
                                                 FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);