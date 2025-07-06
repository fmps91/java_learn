-- primero verificar si hay conexiones en la base de datos cuando no la puedes eleiminar 
SELECT pid, datname, usename, application_name, client_addr, client_port, backend_start, state
FROM pg_stat_activity
WHERE datname = 'ecommerce_java';

-- cerrar si hay conexiones
SELECT pg_terminate_backend(pid)
FROM pg_stat_activity
WHERE datname = 'ecommerce_java' AND pid <> pg_backend_pid();


DROP USER IF EXISTS java;
-- crear un usuario nuevo
CREATE USER java WITH PASSWORD 'java';

-- aqui cambio al usuario con superuser
ALTER USER java WITH SUPERUSER;

-- Verificar y eliminar el esquema 'ecommerce' si existe
--DROP SCHEMA IF EXISTS ecommerce CASCADE;

-- Ver los privilegios de los usuarios
---SELECT * FROM pg_roles;

DROP DATABASE IF EXISTS ecommerce_java;

CREATE DATABASE ecommerce_java WITH OWNER = java;

-- Paso 2: Usar dblink para conectarse a la nueva base de datos desde la sesión actual
-- Nota: Necesitarás la extensión dblink instalada
CREATE EXTENSION IF NOT EXISTS dblink;

-- Paso 3: Ejecutar comandos SQL en la nueva base de datos 'ecommerce_java' usando dblink
SELECT dblink_connect('host=localhost dbname=ecommerce_java user=java password=java');

-- Paso 4: Crear el esquema en la nueva base de datos
SELECT dblink_exec('CREATE SCHEMA ecommerce AUTHORIZATION java');


SELECT dblink_exec('GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA ecommerce TO java')
-- Paso 5: Crear la tabla dentro del esquema ecommerce_schema
SELECT dblink_exec('
Create table ecommerce.person(
    id BIGSERIAL PRIMARY KEY,
    public_id UUID NOT NULL UNIQUE,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(255) NOT NULL UNIQUE,
    address_street VARCHAR(255),
    address_zip_code VARCHAR(255),
    address_city VARCHAR(255),
    address_country VARCHAR(255),
    image_url VARCHAR(256),
    last_seen TIMESTAMP DEFAULT NULL,
    created_date TIMESTAMP not NULL DEFAULT NOW(),
    last_modified_date TIMESTAMP DEFAULT NULL,
    password varchar(250),
    username varchar(250)
)
');

SELECT dblink_exec('
create table ecommerce.authority(
    name VARCHAR(50) PRIMARY key,
    created_date TIMESTAMP not NULL DEFAULT NOW(),
    last_modified_date TIMESTAMP DEFAULT NULL
)
');

SELECT dblink_exec('
Create table ecommerce.user_authority(
    user_id bigint,
    authority_name varchar(50),
    created_date TIMESTAMP not NULL DEFAULT NOW(),
    last_modified_date TIMESTAMP DEFAULT NULL,
    primary key( user_id, authority_name),
    CONSTRAINT fk_authority_name FOREIGN KEY (authority_name) REFERENCES ecommerce.authority (name),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES ecommerce.person (id)
)
');

SELECT dblink_exec('
Create table ecommerce.product_category(
    id BIGSERIAL primary key,
    public_id UUID unique not null,
    name varchar(256) not null,
    created_date TIMESTAMP not NULL DEFAULT NOW(),
    last_modified_date TIMESTAMP DEFAULT NULL,
)
');

SELECT dblink_exec('
reate table ecommerce.product(
    id BIGSERIAL primary key,
    public_id UUID unique not null,
    name varchar(256) not null,
    price float,
    size varchar(256),
    color varchar(256),
    brand varchar(256),
    description varchar(2000),
    featured boolean,
    nb_in_stock bigint,
    created_date TIMESTAMP not NULL DEFAULT NOW(),
    last_modified_date TIMESTAMP DEFAULT NULL,
    category_fk bigint,
    CONSTRAINT fk_category_product_constraint FOREIGN KEY (category_fk) REFERENCES ecommerce.product_category (id) ON DELETE NO ACTION
 
)
');

SELECT dblink_exec('
CREATE TABLE ecommerce.product_picture (
    id BIGSERIAL primary KEY,        
    file bytea  NOT NULL,                            
    file_content_type VARCHAR(255) NOT NULL,        
    created_date TIMESTAMP not NULL DEFAULT NOW(),
    last_modified_date TIMESTAMP DEFAULT NULL,               
    product_fk bigint NOT NULL,                    
    CONSTRAINT fk_product_picture_constraint         
        FOREIGN KEY (product_fk) REFERENCES ecommerce.product (id) ON DELETE CASCADE
)
');

SELECT dblink_exec('
CREATE TABLE ecommerce."order" (
    id BIGSERIAL PRIMARY KEY NOT NULL,                
    public_id UUID NOT NULL UNIQUE,                 
    status VARCHAR(256) NOT NULL,                   
    fk_customer int NOT NULL,                   
    stripe_session_id VARCHAR(256) NOT NULL,        
    created_date TIMESTAMP not NULL DEFAULT NOW(),
    last_modified_date TIMESTAMP DEFAULT NULL,                   
    CONSTRAINT fk_user_order_id                    
        FOREIGN KEY (fk_customer) REFERENCES ecommerce.person (id)
)
');


SELECT dblink_exec('
CREATE TABLE ecommerce.ordered_product (
    fk_order bigint NOT NULL,                        
    fk_product UUID NOT NULL,                       
    quantity BIGINT NOT NULL,                        
    price FLOAT NOT NULL,                            
    product_name VARCHAR(256) NOT NULL,
    created_date TIMESTAMP not NULL DEFAULT NOW(),
    last_modified_date TIMESTAMP DEFAULT NULL,               
    CONSTRAINT pk_ordered_product PRIMARY KEY (fk_order, fk_product), 
    CONSTRAINT fk_ordered_product_constraint         
        FOREIGN KEY (fk_product) REFERENCES ecommerce.product (public_id),
    CONSTRAINT fk_ordered_order_constraint           
        FOREIGN KEY (fk_order) REFERENCES ecommerce."order" (id)
)
');

SELECT dblink_exec('
INSERT INTO ecommerce.authority (name) 
VALUES 
  ("ROLE_ADMIN"),
  ("ROLE_USER");
)
');


-- Paso 6: Desconectar de la base de datos 'ecommerce_java'
SELECT dblink_disconnect();

