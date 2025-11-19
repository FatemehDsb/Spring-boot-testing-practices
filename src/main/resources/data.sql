INSERT INTO categories (id, name) VALUES (1, 'Electronics'),(2,'Pets');
INSERT INTO suppliers (id, name) VALUES (1, 'Acme Co'),(2,'Random Inc');

INSERT INTO products (id, name, price, category_id) VALUES (1, 'Laptop', 12999, 1),(2, 'tablet', 3999, 1),(3,'Rabbit',50,2);
INSERT INTO product_suppliers (product_id, supplier_id) VALUES (1, 1),(1,2),(2,1),(3,2);

INSERT INTO product_details (id, description, manufacturer,  product_id)
VALUES (1, 'High-end gaming laptop', 'ACME Computers',  1),
(2, 'Low-end surf tablet', 'Temu',  2),
(3, 'A rabbit caught in the wild', 'Mother Nature',  3);

INSERT INTO roles(name) VALUES ('ROLE_USER');
INSERT INTO roles(name) VALUES ('ROLE_ADMIN');

INSERT INTO users(username, password) VALUES ('test', '$2a$12$VAbgw3A6dpd75tEUudrqXOlelyuqecc54N9WNfxN7zStPQV7aY8EO'); -- 1234
INSERT INTO users(username, password) VALUES ('admin', '$2a$12$CXqSeBneszgxnW96G8KDZuk44Px5KeBTN3BHGnZKqv1jv8nkZBrTC'); -- admin123

INSERT INTO user_roles(user_id, role_id) VALUES (1, 1); -- test → USER
INSERT INTO user_roles(user_id, role_id) VALUES (2, 1); -- admin → USER
INSERT INTO user_roles(user_id, role_id) VALUES (2, 2); -- admin → ADMIN



