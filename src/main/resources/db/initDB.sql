DROP TABLE IF EXISTS vs_dishes;
DROP TABLE IF EXISTS vs_restaurants;

CREATE TABLE vs_restaurants
(
    rest_id SERIAL,
    rest_name VARCHAR (100) NOT NULL,
    PRIMARY KEY (rest_id)
);

CREATE TABLE vs_dishes
(
    dish_id SERIAL,
    dish_name VARCHAR (100) NOT NULL,
    dish_price INTEGER NOT NULL,
    rest_id INTEGER NOT NULL,
    PRIMARY KEY (dish_id),
    FOREIGN KEY (rest_id) REFERENCES vs_restaurants (rest_id) ON DELETE RESTRICT
);
