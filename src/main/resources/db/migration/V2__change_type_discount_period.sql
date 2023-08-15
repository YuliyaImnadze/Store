ALTER TABLE discount
    DROP COLUMN discount_period;

ALTER TABLE discount
    ADD discount_period date;