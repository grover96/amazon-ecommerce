insert into orders (account_id, order_date, order_number, address_id, total_price) values (1, DATE '2017-12-12', 002923, 1, 50);
insert into orders (account_id, order_date, order_number, address_id, total_price) values (2, DATE '2017-12-19', 002924, 2, 55);
insert into orders (account_id, order_date, order_number, address_id, total_price) values (1, DATE '2017-12-11', 002925, 1, 50);
insert into orders (account_id, order_date, order_number, address_id, total_price) values (2, DATE '2017-12-18', 002926, 2, 55);

insert into order_line_items (account_id, price, product_id, quantity, shipment_id, total_price, order_id,) values (1, 10, 1, 5, 1, 55, 1);
insert into order_line_items (account_id, price, product_id, quantity, shipment_id, total_price, order_id,) values (1, 11, 2, 10, 2, 55, 2);
insert into order_line_items (account_id, price, product_id, quantity, shipment_id, total_price, order_id,) values (2, 10, 2, 5, 2, 50, 2);
insert into order_line_items (account_id, price, product_id, quantity, shipment_id, total_price, order_id,) values (2, 10, 1, 5, 1, 50, 1);
