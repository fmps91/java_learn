
DROP DATABASE IF EXISTS ecommerce_java;

CREATE DATABASE ecommerce_java WITH OWNER postgres;

Create table Address(
    id SERIAL PRIMARY KEY,
    first_name TEXT,
    last_name TEXT,
    street_address TEXT,
    city TEXT,
    zip_code TEXT,
    user_id Foreign Key () REFERENCES ()
)

Create table Category{
    id KEY SERIAL,
    name TEXT,
    parent_category_id Foreign Key () REFERENCES (),
}


Create table PaymentInformation{
    id KEY SERIAL,
    cardholder_name TEXT,
    cardholder_number TEXT,
    expiration_date TEXT,
    cvv TEXT
}

Create table Review{
    id KEY SERIAL,
    review TEXT,
    product_id Foreign Key () REFERENCES (),
    user_id Foreign Key () REFERENCES (),
    crearedAt TEXT,
}


Create table Raiting{
     id KEY SERIAL,
     product_id Foreign Key () REFERENCES (),
     user_id Foreign Key () REFERENCES ()
}

Create table Product{
    id KEY SERIAL,
    title TEXT,
    description TEXT,
    price DOUBLE,
    discounted_price DOUBLE,
    discount_persent DOUBLE,
    brand TEXT,
    color TEXT,
    size INT,
    image_url TEXT,
    num_ratings INT,
    category_id Foreign Key () REFERENCES (),

}
