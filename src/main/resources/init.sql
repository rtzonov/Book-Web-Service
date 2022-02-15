INSERT INTO `user` (id, login, password, roles_id)
VALUES (3, 'login1', '$2a$12$uXSrVV5yG5JBZtE/73//4OCD/Hh5B1n4/ESEw82EpqFJH2EUaQqKO', 2),/*12345678qwe */
       (4, 'login2', '$2a$12$4ObQNd/Hg3RQorYA9ZYYWOg9VyUZ6YtvmHgSUeK1a8mxXTjG3.JAu', 2);/*qwe12345678 */

INSERT INTO roles (users_type)
VALUES ('ADMIN'),
       ('User');

INSERT INTO book (id, name, image_path, cost, genre_id, author_id)
VALUES (1, 'Skeleton king', 'defaultpath.jpg', '40.5', 1, 1),
       (2, 'Cut eye', 'defaultpath.jpg', '50.6', 2, 2);

INSERT INTO author (id, name, last_name)
VALUES (1, 'Levon', 'Orshan'),
       (2, 'Raul', 'Ostin');

INSERT IntO genre (id, type)
VALUES (1, 'Fantasy'),
       (2, 'Horror');

