CREATE TABLE user_last_request
(
    user_id           UUID PRIMARY KEY REFERENCES users (id),
    last_request_time TIMESTAMP NOT NULL
); 