--выбрать все из товаров
select *
from kris_database.public.product;
--выбрать товары со стоимостью менее 100
select *
from product
where price < 100;
--выбрать данные о заказе для покупателя с id 9
select *
from the_order
where customer_id = 9;
--вставить новый продукт в таблицу продуктов (и удостовериться, что вставился)
insert into product (description, price, quantity)
values ('Пельмени: много мяса и мало теста', 1000, 10);
select *
from product;
--обновить (изменить) фамилию кому-нибудь (и удостовериться, что изменилась)
update customer
set second_name = 'Лапкин'
where id = 5;
select *
from customer;
--удалить товар с id 11 (и удостовериться, что удалилось)
delete
from product
where id = 11;
select *
from product;