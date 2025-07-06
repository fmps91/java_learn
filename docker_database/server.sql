
DROP DATABASE IF EXISTS ecommerce_java;

CREATE DATABASE ecommerce_java WITH OWNER postgres;

CREATE TABLE Person (
  id SERIAL PRIMARY KEY,
  username varchar(250) DEFAULT NULL,
  email varchar(250) DEFAULT NULL,
  password varchar(250) DEFAULT NULL
); 

CREATE TABLE Direction (
  id SERIAL PRIMARY KEY,
  calle varchar(250) DEFAULT NULL
);

/* 
esta tabla podria ser pero la cuestion que utiliza springboot3 es que cambia el person_id por user_id 
esto se debe por la configuracion que tiene en application.properties
spring.jpa.hibernate.ddl-auto=update
CREATE TABLE Direction (
  id SERIAL PRIMARY KEY,
  calle varchar(250) DEFAULT NULL,
  person_id bigint DEFAULT NULL,
  CONSTRAINT person_id FOREIGN KEY (person_id) REFERENCES Person(id)
);  */


#esto es cuando se muestra la relacion one to many
/* DROP TABLE IF EXISTS "direction";
DROP SEQUENCE IF EXISTS direction_id_seq;
CREATE SEQUENCE direction_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

DROP SEQUENCE IF EXISTS direction_user_id_seq;
CREATE SEQUENCE direction_user_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."direction" (
    "id" integer DEFAULT nextval('direction_id_seq') NOT NULL,
    "calle" character varying(255),
    "user_id" integer DEFAULT nextval('direction_user_id_seq') NOT NULL,
    CONSTRAINT "direction_pkey" PRIMARY KEY ("id")
) WITH (oids = false);


DROP TABLE IF EXISTS "person";
DROP SEQUENCE IF EXISTS person_id_seq;
CREATE SEQUENCE person_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."person" (
    "id" integer DEFAULT nextval('person_id_seq') NOT NULL,
    "username" character varying(255),
    "email" character varying(255),
    "password" character varying(255),
    CONSTRAINT "person_pkey" PRIMARY KEY ("id")
) WITH (oids = false);


ALTER TABLE ONLY "public"."direction" ADD CONSTRAINT "fk5gwym39kkgi6a0fk04sy28ufq" FOREIGN KEY (user_id) REFERENCES person(id) NOT DEFERRABLE; */