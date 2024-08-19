create table users
(
    id       serial8 primary key,
    login    varchar   not null unique,
    password varchar   not null,
    name     varchar,
    lastname varchar,
    role     integer,
    created  timestamp not null
);

create table orders
(
    id serial8 primary key,
    user_id int8 references users(id),
    status integer,
    address varchar(255) not null,
    date_order timestamp
);

create table order_products
(
    id serial8 primary key,
    order_id int8 references orders(id),
    product_id int8 references products(id),
    quantity int8 not null
);

create table reviews
(
    id serial8 primary key,
    user_id int references users(id),
    product_id int references products(id),
    text text,
    grade int not null,
    is_published boolean,
    date_publication timestamp
);

create table categories
(
    id   serial8 primary key,
    name varchar not null
);

create table options
(
    id          serial8 primary key,
    name        varchar not null,
    category_id int8 references categories (id)
);

create table products
(
    id          serial8 primary key,
    name        varchar          not null,
    price       double precision not null,
    category_id int8 references categories (id)
);

create table values
(
    id         serial8 primary key,
    name       varchar not null,
    product_id int8 references products (id),
    option_id  int8 references options (id)
);

insert into categories (name)
values ('Процессоры'),
       ('Мониторы');

insert into options (name, category_id)
values ('Производитель', 1),
       ('Количество ядер', 1),
       ('Сокет', 1),
       ('Производитель', 2),
       ('Диагональ', 2),
       ('Матрица', 2),
       ('Разрешение', 2);

insert into products (name, price, category_id)
values ('Intel Core I9 9900', 349990, 1),
       ('AMD Ryzen R7 7700', 279990, 1),
       ('Samsung SU556270', 189990, 2),
       ('AOC Z215S659', 129990, 2);

insert into values (name, product_id, option_id)
values ('Intel', 1, 1),
       ('8', 1, 2),
       ('1250', 1, 3),
       ('AMD', 2, 1),
       ('12', 2, 2),
       ('AM4', 2, 3),
       ('Samsung', 3, 4),
       ('27', 3, 5),
       ('TN', 3, 6),
       ('2560*1440', 3, 7),
       ('AOC', 4, 4),
       ('21.5', 4, 5),
       ('AH-IPS', 4, 6),
       ('1920*1080', 4, 7);