INSERT INTO vs_restaurants (rest_name)
VALUES ('Geraldine'),
       ('Uncle Sam cafe'),
       ('Apartment 44'),
       ('Odessa mama'),
       ('Khachapuri'),
       ('Boston seafood & bar'),
       ('White rabbit'),
       ('Beer & brut'),
       ('Caravaevy brothers'),
       ('Prazhechka');

INSERT INTO vs_dishes (dish_name, dish_price, rest_id)
VALUES ('Pumpkin cream soup', 450, 1),
       ('Oatmeal porrige with backed milk, candied fruits, nuts and dried fruits', 380, 1),
       ('Three fried eggs', 180, 1),
       ('Wild west chicken', 590, 2),
       ('Saute of zucchini', 260, 3),
       ('Sweet dumplings with cherries', 260, 4),
       ('Vegetable dolma', 390, 5),
       ('Lightly salted herring', 380, 6),
       ('Shrimp gambero rosso', 2400, 7),
       ('Super breakfast with beer', 990, 8),
       ('Croissant', 99, 9),
       ('Bonbon', 150, 9),
       ('Сapuccino', 150, 9);

INSERT INTO vs_users (name, password, role, email)
VALUES ('Anton', '{noop}password', 'USER', 'anton@gmail.com'),
       ('Victoria', '{noop}password', 'USER', 'victoria@gmail.com'),
       ('Alice', '{noop}password', 'USER', 'alice@gmail.com'),
       ('Vasya', '{noop}password', 'USER', 'vasya@gmail.com'),
       ('Rudolf', '{noop}password', 'USER', 'rudolf@gmail.com'),
       ('Nikita', '{noop}password', 'USER', 'mikita@gmail.com'),
       ('Arnold', '{noop}password', 'USER', 'arnold@gmail.com'),
       ('Donald', '{noop}password', 'USER', 'donald@gmail.com'),
       ('Galechka', '{noop}password', 'USER', 'galechka@gmail.com'),
       ('Fedor', '{noop}admin', 'ADMIN', 'viktor@gmail.com'),
       ('Admin', '{noop}admin', 'ADMIN', 'admin@gmail.com'),
       ('Igor', '{noop}admin', 'ADMIN', 'igor@gmail.com');

INSERT INTO vs_votes (rest_id, user_id, vote_date, vote_time)
VALUES (3, 1, '2021-08-28', '16:00:00'),
       (4, 2, '2020-09-03', '10:25:00'),
       (3, 3, '2021-08-28', '14:25:00'),
       (3, 6, '2021-08-28', '14:29:00'),
       (3, 7, '2021-08-28', '20:02:00'),
       (7, 1, '2021-08-30', '23:08:00');

INSERT INTO vs_votes (rest_id, user_id, vote_date)
VALUES (3, 4, '2021-08-28'),
       (4, 5, '2021-09-13'),
       (4, 7, '2021-09-13'),
       (4, 1, '2021-09-13'),
       (4, 2, '2021-09-13');

INSERT INTO vs_votes (rest_id, user_id, vote_time)
VALUES (3, 9, '17:00:00'),
       (4, 8, '17:39:00');

