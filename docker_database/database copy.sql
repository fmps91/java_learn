
DROP DATABASE IF EXISTS example;

CREATE DATABASE example;

Create table Address(
    id SERIAL PRIMARY KEY,
    first_name TEXT,
    last_name TEXT,
    street_address TEXT,
    city TEXT,
    zip_code TEXT
)

Create table User{
    id SERIAL PRIMARY KEY,
    name TEXT,
    author_id Text,
    CONSTRAINT author_id FOREIGN KEY (author_id) REFERENCES Address(id)
}

create table Person(
    id SERIAL PRIMARY KEY,
    name VARCHAR(250),
    email  VARCHAR(250),
    password  VARCHAR(250)
);

create table token(
    id SERIAL PRIMARY KEY,
    user_id Long,
    expired BOOLEAN,
    revoked BOOLEAN,
    token TEXT,
    token_type TEXT,
    CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES user(id)
)


CREATE TABLE Person (
  id SERIAL PRIMARY KEY,
  username varchar(250) DEFAULT NULL,
  email varchar(250) DEFAULT NULL,
  password varchar(250) DEFAULT NULL
); 

