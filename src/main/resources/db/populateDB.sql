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
VALUES ('Anton', '{bcrypt}$2a$10$LYbBSCPTcvGscGGh7Sd5TOmIbsAcqaxu3bni1/Hi0DZ5QDGZqmBAq', 'USER', 'anton@gmail.com'),
       ('Victoria', '{bcrypt}$2a$12$dcu8iRkoxOzJ1feFKB2Ln.kUtcDAbFkBjFU.RRBvhuSM.xCJWPQpK', 'USER', 'victoria@gmail.com'),
       ('Alice', '{bcrypt}$2a$12$VJ3feD91GCq8bMlxiP1QBeUNVV3Jmidcuysk2a/u.3.1MtgbpYSOO', 'USER', 'alice@gmail.com'),
       ('Vasya', '{bcrypt}$2a$12$RICzwZTgQcBWnrdcLJ0Iku1qvR2oJKrxZLZFnOwXexG8vrh/PKPGe', 'USER', 'vasya@gmail.com'),
       ('Rudolf', '{bcrypt}$2a$12$uUXs1dSuppDsqfAGC0O4TOj4TuAuDzdKoO19BEqkA3O1jja/oZbbW', 'USER', 'rudolf@gmail.com'),
       ('Nikita', '{bcrypt}$2a$12$TtvrNWhaSvg98jBlxOJQtO9cJcd8Fu1uuZU.nbOreLaokCn1la2sK', 'USER', 'nikita@gmail.com'),
       ('Arnold', '{bcrypt}$2a$12$tZAaYnArJm87YO3AHf5d6uSoEelYG/R4Tttjy75CPuI/ooewOU3Vq', 'USER', 'arnold@gmail.com'),
       ('Donald', '{bcrypt}$2a$12$cwOGjAkfPpg3yvhGgQH8NO6h0wWr.n0NMQuY5XmVTgbIeQffuP5l2', 'USER', 'donald@gmail.com'),
       ('Galechka', '{bcrypt}$2a$12$8udv3UyKp5dbbVI8CR4sgOZjJ8cCOqCSLDxkOnXO35jj4r4rT.CeS', 'USER', 'galechka@gmail.com'),
       ('Fedor', '{bcrypt}$2a$12$SHZ8oFv12Ywj3ylrj6346OYQ73XOHmV1rcmIQNZhLLL6lBWHE6njS', 'ADMIN', 'viktor@gmail.com'),
       ('Admin', '{bcrypt}$2a$12$5LDE4S5YHYGoOnpbZH5qYeaiLfYegAr1.KTwgI6uDU8jYsU3BfwoO', 'ADMIN', 'admin@gmail.com'),
       ('Igor', '{bcrypt}$2a$12$BAFRZEgOvsL.7D27IljcTOkmCTQOWXia.vzoBU8MQ3Ll0IMFiNu1e', 'ADMIN', 'igor@gmail.com');

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

