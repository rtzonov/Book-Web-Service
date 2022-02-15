DROP SCHEMA IF EXISTS bookShop;
CREATE SCHEMA IF NOT EXISTS bookShop;


CREATE TABLE IF NOT EXISTS `roles` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `users_type` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`));


CREATE TABLE IF NOT EXISTS `user` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `login` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `roles_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_user_roles`
    FOREIGN KEY (`roles_id`)
    REFERENCES `roles` (`id`)
);


CREATE TABLE IF NOT EXISTS `genre` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `type` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`));


CREATE TABLE IF NOT EXISTS `author` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `last_name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`));


CREATE TABLE IF NOT EXISTS `book` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `image_path` VARCHAR(255) NOT NULL DEFAULT './Defaultimage.JPG',
    `cost` DECIMAL(10,2) NOT NULL,
    `genre_id` INT NOT NULL,
    `author_id` INT NOT NULL,
    `is_deleted` INT DEFAULT FALSE ,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_book_genre1`
    FOREIGN KEY (`genre_id`)
    REFERENCES `genre` (`id`),
    CONSTRAINT `fk_book_author1`
    FOREIGN KEY (`author_id`)
    REFERENCES `author` (`id`)
);


CREATE TABLE IF NOT EXISTS `order` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `user_id` INT NOT NULL,
    `date` TIMESTAMP NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_order_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
);



CREATE TABLE IF NOT EXISTS `payment` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `order_id` INT NOT NULL,
    `date` TIMESTAMP NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_payment_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `order` (`id`)
);


CREATE TABLE IF NOT EXISTS `order_book` (
    `order_id` INT NOT NULL,
    `book_id` INT NOT NULL,
    PRIMARY KEY (`order_id`, `book_id`),
    CONSTRAINT `fk_order_has_book_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `order` (`id`),
    CONSTRAINT `fk_order_has_book_book1`
    FOREIGN KEY (`book_id`)
    REFERENCES `book` (`id`)
);
Set referential_integrity = 0;
