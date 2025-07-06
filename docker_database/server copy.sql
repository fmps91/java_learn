
DROP DATABASE IF EXISTS ecommerce_java;

CREATE DATABASE ecommerce_java WITH OWNER postgres;

CREATE TABLE Person (
  id SERIAL PRIMARY KEY,
  username varchar(250) DEFAULT NULL,
  email varchar(250) DEFAULT NULL UNIQUE,
  password varchar(250) DEFAULT NULL
); 

CREATE TABLE House (
  id SERIAL PRIMARY KEY,
  color varchar(250) DEFAULT NULL,
  nro varchar(250) DEFAULT NULL
); 

CREATE TABLE Direction (
  id SERIAL PRIMARY KEY,
  calle varchar(250) DEFAULT NULL,
  user_id integer NOT NULL,
  house_id integer NOT NULL,
  CONSTRAINT uc_user_house UNIQUE (user_id, house_id),
  CONSTRAINT fk_user_id FOREIGN KEY (user_id ) REFERENCES Person (id),
  CONSTRAINT fk_house_id FOREIGN KEY (house_id ) REFERENCES House (id)
);
