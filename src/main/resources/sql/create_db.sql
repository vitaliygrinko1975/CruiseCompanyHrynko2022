SET NAMES utf8;

DROP DATABASE IF EXISTS `cruises`;
CREATE DATABASE cruises CHARACTER SET utf8 COLLATE utf8_bin;

USE cruises;
--
-- Table structure for table `roles`
--

CREATE TABLE roles
(
    id   INTEGER     NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(10) NOT NULL UNIQUE
);

--
-- Table structure for table `Accounts`
--

CREATE TABLE accounts
(
    id      INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    balance DOUBLE  NOT NULL
);


--
-- Table structure for table `users`
--

CREATE TABLE users
(
    id          INTEGER     NOT NULL AUTO_INCREMENT PRIMARY KEY,
    login       VARCHAR(20) NOT NULL UNIQUE,
    password    VARCHAR(45) NOT NULL,
    first_name  VARCHAR(45) NOT NULL,
    last_name   VARCHAR(45) NOT NULL,
    email       VARCHAR(20) NOT NULL UNIQUE,
    phone       VARCHAR(45) NOT NULL,
    roles_id    INTEGER     NOT NULL,
    accounts_id INTEGER     NOT NULL,
    FOREIGN KEY (roles_id) REFERENCES roles (Id),
    FOREIGN KEY (accounts_id) REFERENCES accounts (Id) ON DELETE CASCADE
);

--
-- Table structure for table `Ships`
--


CREATE TABLE ships
(
    id          INTEGER      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(45)  NOT NULL,
    description VARCHAR(255) NOT NULL,
    capacity    INTEGER      NOT NULL
);

--
-- Table structure for table `cruises`
--

CREATE TABLE cruises
(
    id              INTEGER      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name            VARCHAR(255)  NOT NULL,
    description     VARCHAR(255) NOT NULL,
    price           DOUBLE       NOT NULL,
    ships_id        INTEGER      NOT NULL,
    capacity        INTEGER      NOT NULL,
    start_of_cruise DATETIME     NOT NULL,
    duration        INT NOT          NULL,
    status          VARCHAR(45) NOT NULL,
    FOREIGN KEY (ships_id) REFERENCES ships (Id)
);

--
-- Table structure for table `users_has_cruises`
--
CREATE TABLE orders
(
    id         INTEGER     NOT NULL AUTO_INCREMENT PRIMARY KEY,
    users_id   INTEGER     NOT NULL,
    cruises_id INTEGER     NOT NULL,
    status     VARCHAR(45) NOT NULL,
    FOREIGN KEY (users_id) REFERENCES users (Id) ON DELETE CASCADE,
    FOREIGN KEY (cruises_id) REFERENCES cruises (Id) ON DELETE CASCADE

);

CREATE VIEW orders_view AS SELECT o.id AS id,
                                  u.first_name AS first_name,
                                  u.last_name AS last_name,
                                  u.email AS email,
                                  c.description AS description,
                                  c.status AS status_of_cruises,
                                  o.status AS status
                                  FROM orders AS o
                                  JOIN users AS u ON o.users_id = u.id
                                  JOIN cruises AS c ON o.cruises_id = c.id;



INSERT INTO roles (name)
VALUES ('admin');
INSERT INTO roles (name)
VALUES ('client');

INSERT INTO accounts (balance)
VALUES (0);
INSERT INTO accounts (balance)
VALUES (0);

INSERT INTO users (login, password, first_name, last_name, email, phone, roles_id, accounts_id)
VALUES ('admin', 'admin', ' Ivan ', 'Ivanov', 'admin@gmail.com',
        '+380992589898', 1, 1);

INSERT INTO users (login, password, first_name, last_name, email, phone, roles_id, accounts_id)
VALUES ('client', 'client', ' Petr ', 'Petrov', 'client@gmail.com',
        '+380992589896', 2, 2);


INSERT INTO ships (name, description,capacity)
VALUES ('Ориана', 'Океанический пассажирский',2000);
INSERT INTO ships (name, description,capacity)
VALUES ('Исследователь морей', 'Океанический пассажирский',2000);
INSERT INTO ships (name, description,capacity)
VALUES ('Минерва', 'Океанический пассажирский',2000);



INSERT INTO cruises (name, description, price, ships_id,capacity, start_of_cruise, duration,status)
VALUES ('Малое путишествие', 'Одесса-Стамбул-Одесса', '2000', '1',1600,
        20230123120000, 10,'Не начался');
INSERT INTO cruises (name, description, price, ships_id,capacity, start_of_cruise, duration,status)
VALUES ('Среднее путишествие', 'Одесса-Стамбул-Лисабон-Одесса', '5000', '2',1600,
        20230128120000, 15,'Не начался');
INSERT INTO cruises (name, description, price, ships_id,capacity, start_of_cruise, duration,status)
VALUES ('Большое путишествие', 'Одесса-Стамбул-Палермо-Барселона-Лиссабон-Одесса', '10000',
        '3',2000,20230130120000, 25,'Не начался');

INSERT INTO cruises (name, description, price, ships_id,capacity, start_of_cruise, duration,status)
VALUES ('Малое путишествие', 'Одесса-Стамбул-Одесса', '2000', '1',1600,
        20221223120000, 5,'Не начался');
INSERT INTO cruises (name, description, price, ships_id,capacity, start_of_cruise, duration,status)
VALUES ('Среднее путишествие', 'Одесса-Стамбул-Лисабон-Одесса', '5000', '1',1600,
        20221228120000, 10,'Не начался');
INSERT INTO cruises (name, description, price, ships_id,capacity, start_of_cruise, duration,status)
VALUES ('Большое путишествие', 'Одесса-Стамбул-Палермо-Барселона-Лиссабон-Одесса', '10000',
        '2',2000,20221224120000, 15,'Не начался');



# INSERT INTO orders (users_id, cruises_id,status)
# VALUES (2, 1, 'В обработке');



SELECT *
FROM roles;
SELECT *
FROM accounts;
SELECT *
FROM users;
SELECT *
FROM ships;
SELECT *
FROM cruises;
SELECT *
FROM orders;


