INSERT INTO categories (id, name) VALUES (1, 'Electronics'),(2,'Pets');
INSERT INTO suppliers (id, name) VALUES (1, 'Acme Co'),(2,'Random Inc');

INSERT INTO products (id, name, price, category_id) VALUES (1, 'Laptop', 12999, 1),(2, 'tablet', 3999, 1),(3,'Rabbit',50,2);
INSERT INTO product_suppliers (product_id, supplier_id) VALUES (1, 1),(1,2),(2,1),(3,2);

INSERT INTO product_details (id, description, manufacturer,  product_id)
VALUES (1, 'High-end gaming laptop', 'ACME Computers',  1),
(2, 'Low-end surf tablet', 'Temu',  2),
(3, 'A rabbit caught in the wild', 'Mother Nature',  3);
