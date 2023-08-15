CREATE TABLE characteristic
(
    id         UUID NOT NULL,
    weight     DOUBLE PRECISION,
    country    VARCHAR(255),
    height     DOUBLE PRECISION,
    product_id UUID,
    CONSTRAINT pk_characteristic PRIMARY KEY (id)
);

CREATE TABLE company
(
    id          UUID         NOT NULL,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    logo        VARCHAR(255),
    owner_id    UUID,
    CONSTRAINT pk_company PRIMARY KEY (id)
);

CREATE TABLE discount
(
    id              UUID NOT NULL,
    discount        DOUBLE PRECISION,
    discount_period INTEGER,
    CONSTRAINT pk_discount PRIMARY KEY (id)
);

CREATE TABLE notification
(
    id           UUID NOT NULL,
    header       VARCHAR(255),
    date         date,
    text         VARCHAR(255),
    recipient_id UUID,
    sender_id    UUID,
    CONSTRAINT pk_notification PRIMARY KEY (id)
);

CREATE TABLE product
(
    id                UUID         NOT NULL,
    name              VARCHAR(255) NOT NULL,
    description       VARCHAR(255),
    supplier_id       UUID,
    price             DECIMAL,
    quantity          INTEGER,
    discount_id       UUID,
    characteristic_id UUID,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE product_line
(
    id          UUID NOT NULL,
    product_id  UUID,
    count       INTEGER,
    total_sum   DECIMAL,
    company_id  UUID,
    purchase_id UUID,
    CONSTRAINT pk_product_line PRIMARY KEY (id)
);

CREATE TABLE purchase
(
    id                  UUID NOT NULL,
    product_list_amount DECIMAL,
    buyer_id            UUID,
    created_date        date,
    CONSTRAINT pk_purchase PRIMARY KEY (id)
);

CREATE TABLE purchase_product_list
(
    store_purchase_id UUID NOT NULL,
    product_list_id   UUID NOT NULL,
    CONSTRAINT pk_purchase_productlist PRIMARY KEY (store_purchase_id, product_list_id)
);

CREATE TABLE reviews
(
    id         UUID NOT NULL,
    reviews    VARCHAR(255),
    grade      INTEGER,
    product_id UUID,
    CONSTRAINT pk_reviews PRIMARY KEY (id)
);

CREATE TABLE role
(
    id    UUID         NOT NULL,
    title VARCHAR(255) NOT NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE user_store
(
    id       UUID NOT NULL,
    username VARCHAR(255),
    email    VARCHAR(255),
    password VARCHAR(255),
    balance  DECIMAL,
    role_id  UUID,
    CONSTRAINT pk_user_store PRIMARY KEY (id)
);

ALTER TABLE purchase_product_list
    ADD CONSTRAINT uc_purchase_product_list_productlist UNIQUE (product_list_id);

ALTER TABLE user_store
    ADD CONSTRAINT uc_user_store_email UNIQUE (email);

ALTER TABLE user_store
    ADD CONSTRAINT uc_user_store_password UNIQUE (password);

ALTER TABLE user_store
    ADD CONSTRAINT uc_user_store_username UNIQUE (username);

ALTER TABLE characteristic
    ADD CONSTRAINT FK_CHARACTERISTIC_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (id);

ALTER TABLE company
    ADD CONSTRAINT FK_COMPANY_ON_OWNER FOREIGN KEY (owner_id) REFERENCES user_store (id);

ALTER TABLE notification
    ADD CONSTRAINT FK_NOTIFICATION_ON_RECIPIENT FOREIGN KEY (recipient_id) REFERENCES user_store (id);

ALTER TABLE notification
    ADD CONSTRAINT FK_NOTIFICATION_ON_SENDER FOREIGN KEY (sender_id) REFERENCES user_store (id);

ALTER TABLE product_line
    ADD CONSTRAINT FK_PRODUCT_LINE_ON_COMPANY FOREIGN KEY (company_id) REFERENCES company (id);

ALTER TABLE product_line
    ADD CONSTRAINT FK_PRODUCT_LINE_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (id);

ALTER TABLE product_line
    ADD CONSTRAINT FK_PRODUCT_LINE_ON_PURCHASE FOREIGN KEY (purchase_id) REFERENCES purchase (id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CHARACTERISTIC FOREIGN KEY (characteristic_id) REFERENCES characteristic (id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_DISCOUNT FOREIGN KEY (discount_id) REFERENCES discount (id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_SUPPLIER FOREIGN KEY (supplier_id) REFERENCES company (id);

ALTER TABLE purchase
    ADD CONSTRAINT FK_PURCHASE_ON_BUYER FOREIGN KEY (buyer_id) REFERENCES user_store (id);

ALTER TABLE reviews
    ADD CONSTRAINT FK_REVIEWS_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (id);

ALTER TABLE user_store
    ADD CONSTRAINT FK_USER_STORE_ON_ROLE FOREIGN KEY (role_id) REFERENCES role (id);

ALTER TABLE purchase_product_list
    ADD CONSTRAINT fk_purprolis_on_product_line FOREIGN KEY (product_list_id) REFERENCES product_line (id);

ALTER TABLE purchase_product_list
    ADD CONSTRAINT fk_purprolis_on_purchase FOREIGN KEY (store_purchase_id) REFERENCES purchase (id);