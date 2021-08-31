INSERT INTO vs_restaurants (rest_name)
VALUES ('Geraldine'),
       ('Uncle Sam cafe'),
       ('Apartment 44'),
       ('Odessa mama'),
       ('Khachapuri'),
       ('Boston seafood & bar'),
       ('White rabbit'),
       ('Beer & brut'),
       ('Caravaevy brothers ');

INSERT INTO vs_dishes (dish_name, dish_price, rest_id)
VALUES ('Pumpkin cream soup', 450, 1),
       ('Wild west chicken', 590, 2),
       ('Saute of zucchini', 260, 3),
       ('Sweet dumplings with cherries', 260, 4),
       ('Vegetable dolma', 390, 5),
       ('Lightly salted herring', 380, 6),
       ('Shrimp gambero rosso', 2400, 7),
       ('Super breakfast with beer', 990, 8),
       ('Croissant', 99, 9),
       ('Сappuccino', 150, 9);

INSERT INTO vs_users (user_name, user_password, user_role)
VALUES ('Anton', 'password', 'USER'),
       ('Victoria', 'password', 'USER'),
       ('Nikita', 'password', 'ADMIN');
