DROP TABLE IF EXISTS ADDRESSES;
CREATE TABLE Addresses (
    ID SERIAL PRIMARY KEY,
    city VARCHAR(255) NOT NULL,
    street VARCHAR(255) NOT NULL,
    house VARCHAR(255) NOT NULL,
    apartment VARCHAR(255)
);


DROP TABLE IF EXISTS ORDERS;
CREATE TABLE Orders (
    ID SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    date DATE NOT NULL,
    status VARCHAR(255),
    total_price INT NOT NULL,
    address_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES Users(id),
    FOREIGN KEY (total_price) REFERENCES Addresses(id)
);

DROP TABLE IF EXISTS ORDER_ITEMS;
CREATE TABLE Order_items (
    ID SERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_variant_id BIGINT NOT NULL,
    amount INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES Orders(id),
    FOREIGN KEY (product_variant_id) REFERENCES ProductVariants(id)
);
