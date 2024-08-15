create table events
(
    id        serial8 primary key ,
    name      varchar(255) not null,
    created   timestamp,
    published boolean
);

insert into events (name, created, published)
values
    ('Event 1', '2024-08-12 10:00:00', true),
    ('Event 2', '2024-08-13 15:30:00', false),
    ('Event 3', '2024-08-14 09:00:00', true);

