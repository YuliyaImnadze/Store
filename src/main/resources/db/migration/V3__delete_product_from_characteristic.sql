ALTER TABLE characteristic
    DROP CONSTRAINT fk_characteristic_on_product;

ALTER TABLE product
    DROP CONSTRAINT fk_product_on_characteristic;

ALTER TABLE product
    DROP COLUMN characteristic_id;

ALTER TABLE characteristic
    DROP COLUMN product_id;