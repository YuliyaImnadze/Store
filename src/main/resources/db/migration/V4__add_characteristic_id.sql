ALTER TABLE product
    ADD characteristic_id UUID;

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CHARACTERISTIC FOREIGN KEY (characteristic_id) REFERENCES characteristic (id);