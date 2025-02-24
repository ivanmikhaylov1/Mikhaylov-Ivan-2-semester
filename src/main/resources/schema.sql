CREATE TABLE IF NOT EXISTS users (
                                     id UUID PRIMARY KEY,
                                     name VARCHAR(255) NOT NULL UNIQUE,
                                     password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS catalogs (
                                        catalog_id UUID PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL,
                                        user_id UUID,
                                        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS websites (
                                        website_id UUID PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL,
                                        url VARCHAR(255) NOT NULL,
                                        user_id UUID,
                                        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS articles (
                                        article_id UUID PRIMARY KEY,
                                        title VARCHAR(255) NOT NULL,
                                        content TEXT,
                                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS article_catalog (
                                               article_id UUID,
                                               catalog_id UUID,
                                               PRIMARY KEY (article_id, catalog_id),
                                               FOREIGN KEY (article_id) REFERENCES articles(article_id) ON DELETE CASCADE,
                                               FOREIGN KEY (catalog_id) REFERENCES catalogs(catalog_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS article_website (
                                               article_id UUID,
                                               website_id UUID,
                                               PRIMARY KEY (article_id, website_id),
                                               FOREIGN KEY (article_id) REFERENCES articles(article_id) ON DELETE CASCADE,
                                               FOREIGN KEY (website_id) REFERENCES websites(website_id) ON DELETE CASCADE
);