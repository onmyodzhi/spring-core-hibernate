BEGIN;

DROP TABLE IF EXISTS Users;
CREATE TABLE IF NOT EXISTS Users
(
    id
    bigserial
    primary
    key,
    name
    varchar
(
    40
) not null,
    age int not null
    );

INSERT INTO Users (name, age)
VALUES ('Alex', 21),
       ('Bob', 42),
       ('Pedro', 25),
       ('Julia', 20);

DROP TABLE IF EXISTS Products CASCADE;
CREATE TABLE IF NOT EXISTS Products
(
    id
    bigserial
    primary
    key,
    title
    varchar
(
    60
) not null,
    price int not null
    );

INSERT INTO Products (title, price)
VALUES ('Table', 60),
       ('Phone', 1000),
       ('Knife', 30),
       ('Monitor', 400),
       ('Hammer', 20),
       ('Milk', 5);

COMMIT;