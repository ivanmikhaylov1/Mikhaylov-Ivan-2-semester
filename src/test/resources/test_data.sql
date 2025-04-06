-- Тестовые пользователи
INSERT INTO users (id, name, password)
VALUES ('11111111-1111-1111-1111-111111111111', 'testuser1', 'password1'),
       ('22222222-2222-2222-2222-222222222222', 'testuser2', 'password2');

-- Тестовые категории
INSERT INTO article_categories (id, name)
VALUES ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Technology'),
       ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'Science');

-- Тестовые статьи
INSERT INTO articles (id, title, content, date, link, category_id)
VALUES ('cccccccc-cccc-cccc-cccc-cccccccccccc', 'Test Article 1', 'This is test content for article 1', '2025-01-01',
        'http://example.com/1', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa'),
       ('dddddddd-dddd-dddd-dddd-dddddddddddd', 'Test Article 2', 'This is test content for article 2', '2025-01-02',
        'http://example.com/2', 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb');

-- Каталоги пользователей
INSERT INTO catalogs (id, name, user_id)
VALUES ('eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee', 'Test Catalog 1', '11111111-1111-1111-1111-111111111111'),
       ('ffffffff-ffff-ffff-ffff-ffffffffffff', 'Test Catalog 2', '22222222-2222-2222-2222-222222222222');

-- Сайты пользователей
INSERT INTO websites (id, name, url, user_id)
VALUES ('11111111-2222-3333-4444-555555555555', 'Test Website 1', 'http://testsite1.com',
        '11111111-1111-1111-1111-111111111111'),
       ('22222222-3333-4444-5555-666666666666', 'Test Website 2', 'http://testsite2.com',
        '22222222-2222-2222-2222-222222222222');

-- Связь между статьями и каталогами
INSERT INTO article_catalog (article_id, catalog_id)
VALUES ('cccccccc-cccc-cccc-cccc-cccccccccccc', 'eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee'),
       ('dddddddd-dddd-dddd-dddd-dddddddddddd', 'ffffffff-ffff-ffff-ffff-ffffffffffff');

-- Связь между статьями и сайтами
INSERT INTO article_website (article_id, website_id)
VALUES ('cccccccc-cccc-cccc-cccc-cccccccccccc', '11111111-2222-3333-4444-555555555555'),
       ('dddddddd-dddd-dddd-dddd-dddddddddddd', '22222222-3333-4444-5555-666666666666');

-- Время последнего запроса пользователей
INSERT INTO user_last_request (user_id, last_request_time)
VALUES ('11111111-1111-1111-1111-111111111111', '2025-01-01 12:00:00'),
       ('22222222-2222-2222-2222-222222222222', '2025-01-02 12:00:00');