drop table if exists consumer cascade ;
Create table consumer
(
    id   serial      not null Primary Key,
    name varchar(15) not null
);

Insert into consumer (name)
values ('Ivan'),
       ('Ruslan'),
       ('Maxim'),
       ('Pasha'),
       ('Fedor');

drop table if exists product cascade;
Create table product
(
    id           serial      not null Primary Key,
    product_name varchar(30) not null unique,
    price        numeric     not null
);

Insert into product (product_name, price)
values ('Bread', 14.5),
       ('Cheese', 130.67),
       ('Apple', 43.3),
       ('Juice', 87.9),
       ('Chicken', 220),
       ('Fish', 1100),
       ('Beer', 67.9),
       ('Milk', 55),
       ('Potatos', 33.4),
       ('Sugar', 19);

drop table if exists consumer_product cascade;
create table consumer_product
(
    consumer_id int references consumer (id),
    product_id  int references product (id),
    Primary key (consumer_id, product_id)
);

Insert into consumer_product (consumer_id, product_id)
values (1, 1),
       (1, 2),
       (1, 9),
       (1, 3),
       (2, 2),
       (2, 6),
       (2, 3),
       (2, 8),
       (3, 1),
       (3, 8),
       (3, 3),
       (3, 10),
       (4, 7),
       (4, 2),
       (4, 6),
       (4, 9),
       (5, 4),
       (5, 6),
       (5, 5),
       (5, 10);