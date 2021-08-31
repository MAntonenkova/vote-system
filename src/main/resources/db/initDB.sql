DROP TABLE IF EXISTS vs_dishes;
DROP TABLE IF EXISTS vs_votes;
DROP TABLE IF EXISTS vs_restaurants;
DROP TABLE IF EXISTS vs_users;

CREATE TABLE vs_restaurants
(
    rest_id   SERIAL,
    rest_name VARCHAR(100) NOT NULL,
    PRIMARY KEY (rest_id)
);

CREATE TABLE vs_dishes
(
    dish_id    SERIAL,
    dish_name  VARCHAR(100) NOT NULL,
    dish_price INTEGER      NOT NULL,
    rest_id    INTEGER      NOT NULL,
    PRIMARY KEY (dish_id),
    FOREIGN KEY (rest_id) REFERENCES vs_restaurants (rest_id) ON DELETE RESTRICT
);

CREATE TABLE vs_votes
(
    vote_id        SERIAL,
    user_id        INTEGER                 NOT NULL,
    rest_id        INTEGER                 NOT NULL,
    vote_date_time TIMESTAMP DEFAULT now() NOT NULL,
    PRIMARY KEY (vote_id),
    FOREIGN KEY (rest_id) REFERENCES vs_restaurants(rest_id) ON DELETE RESTRICT
);

CREATE TABLE vs_users
(
    user_id       SERIAL,
    user_name     VARCHAR(50) NOT NULL,
    user_password VARCHAR(20) NOT NULL,
    user_role     VARCHAR     NOT NULL,
    PRIMARY KEY (user_id)
);
