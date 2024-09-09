--создание таблицы продуктов
create table if not exists product
(
    id          serial primary key,
    description varchar(255),
    price       integer check (price > 0),
    quantity    integer check (quantity > 0)
);
--создание таблицы покупателей
create table if not exists customer
(
    id          serial primary key,
    first_name  varchar(20),
    second_name varchar(20)
);
--создание таблицы заказов
create table if not exists the_order
(
    id          serial primary key,
    product_id  integer references product (id),
    customer_id integer references customer (id),
    date_time   timestamp,
    quantity    integer check (quantity > 0)
);
--вставка продуктов
insert into product (description, price, quantity)
values ('вкусная шоколадка', 100, 5),
       ('свежее молоко', 100, 10),
       ('хрустящий хлебушек', 50, 50),
       ('кислые мармеладки', 150, 50),
       ('манговый энергос', 100, 100),
       ('воздушный рис', 100, 100),
       ('корм для кошечек', 1500, 10),
       ('корм для собачек', 1500, 10),
       ('питательная паста заводного города', 100, 10000),
       ('шипучая суджамма', 1000, 10);
--вставка покупателей
insert into customer(first_name, second_name)
values ('Борис', 'Борисов'),
       ('Неборис', 'Неборисов'),
       ('Суперборис', 'Суперборисов'),
       ('Сигмаборис', 'Сигмаборисов'),
       ('Стив', 'Скелетиков'),
       ('Джек', 'Мойкотов'),
       ('Крис', 'Некрис'),
       ('Весенний', 'Охранник'),
       ('Джава', 'Зебестов'),
       ('User', 'User');
--вставка заказов
insert into the_order (product_id, customer_id, date_time, quantity)
values (1, 1, CURRENT_DATE, 5),
       (2, 2, CURRENT_DATE, 5),
       (3, 3, CURRENT_DATE, 5),
       (4, 4, CURRENT_DATE, 5),
       (5, 5, CURRENT_DATE, 5),
       (6, 6, CURRENT_DATE, 5),
       (7, 7, CURRENT_DATE, 5),
       (8, 8, CURRENT_DATE, 5),
       (9, 9, CURRENT_DATE, 5),
       (10, 10, CURRENT_DATE, 5);