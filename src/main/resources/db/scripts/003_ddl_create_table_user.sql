CREATE TABLE users
(
    id SERIAL PRIMARY KEY,
    email    varchar unique not null,
    fullName     varchar        not null,
    password varchar        not null
);