DROP TABLE IF EXISTS vs_dishes;
DROP TABLE IF EXISTS vs_votes;
DROP TABLE IF EXISTS vs_restaurants;
DROP TABLE IF EXISTS vs_users;
DROP SEQUENCE IF EXISTS rest_seq;
DROP SEQUENCE IF EXISTS dish_seq;
DROP SEQUENCE IF EXISTS vote_seq;
DROP SEQUENCE IF EXISTS user_seq;

CREATE SEQUENCE rest_seq START WITH 1;
CREATE SEQUENCE dish_seq START WITH 1;
CREATE SEQUENCE vote_seq START WITH 1;
CREATE SEQUENCE user_seq START WITH 1;


CREATE TABLE vs_restaurants
(
    rest_id   INTEGER DEFAULT nextval('rest_seq'),
    rest_name VARCHAR(100) NOT NULL UNIQUE,
    PRIMARY KEY (rest_id)
);

CREATE
UNIQUE INDEX restaurants_unique_name ON vs_restaurants (rest_name);

CREATE TABLE vs_dishes
(
    dish_id    INTEGER DEFAULT nextval('dish_seq'),
    dish_name  VARCHAR(100) NOT NULL,
    dish_price INTEGER      NOT NULL,
    rest_id    INTEGER      NOT NULL,
    PRIMARY KEY (dish_id),
    FOREIGN KEY (rest_id) REFERENCES vs_restaurants (rest_id) ON DELETE RESTRICT
);

CREATE TABLE vs_votes
(
    vote_id   INTEGER DEFAULT nextval('vote_seq'),
    rest_id   INTEGER               NOT NULL,
    user_id   INTEGER               NOT NULL,
    vote_date DATE    DEFAULT now() NOT NULL,
    vote_time TIME    DEFAULT now() NOT NULL,
    PRIMARY KEY (vote_id),
    FOREIGN KEY (rest_id) REFERENCES vs_restaurants (rest_id) ON DELETE RESTRICT
);

CREATE
UNIQUE INDEX vote_unique_user_date_idx ON vs_votes (user_id, rest_id, vote_date);

CREATE TABLE vs_users
(
    id       INTEGER DEFAULT nextval('user_seq'),
    name     VARCHAR(50)          NOT NULL,
    password VARCHAR(20)          NOT NULL,
    role     VARCHAR              NOT NULL,
    email    VARCHAR(50)          NOT NULL,
    enable   BOOLEAN DEFAULT TRUE NOT NULL,
    PRIMARY KEY (id)
);

CREATE
UNIQUE INDEX users_unique_email_idx ON vs_users (email);


