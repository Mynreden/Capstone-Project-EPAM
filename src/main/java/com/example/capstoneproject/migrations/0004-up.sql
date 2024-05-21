DROP TABLE IF EXISTS CART;
CREATE TABLE cart (
    ID BIGINT PRIMARY KEY,
    total_price BIGINT NOT NULL,
    FOREIGN KEY (id) REFERENCES Users(id)
);

DROP TABLE IF EXISTS CART_ITEM;
CREATE TABLE cart_item (
    ID SERIAL PRIMARY KEY,
    cart_id BIGINT NOT NULL,
    product_variant_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (cart_id) REFERENCES cart(id),
    FOREIGN KEY (product_variant_id) REFERENCES ProductVariants(ID)
);