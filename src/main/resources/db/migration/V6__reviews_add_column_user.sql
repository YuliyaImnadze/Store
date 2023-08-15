ALTER TABLE reviews
    ADD user_id UUID;

ALTER TABLE reviews
    ADD CONSTRAINT FK_REVIEWS_ON_USER FOREIGN KEY (user_id) REFERENCES user_store (id);