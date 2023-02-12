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
    balance DOUBLE  NOT NULL,
    file_name  VARCHAR(255) NOT NULL
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
    id              INTEGER      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name            VARCHAR(45)  NOT NULL,
    description     VARCHAR(255) NOT NULL,
    capacity        INTEGER      NOT NULL
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
    capacity        INTEGER      NOT NULL,
    start_of_cruise DATETIME     NOT NULL,
    duration        INT          NOT NULL,
    status          VARCHAR(45)  NOT NULL
);

--
-- Table structure for table `orders`
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



CREATE TABLE cruises_has_ships
(
    id                       INTEGER     NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cruises_id               INTEGER     NOT NULL,
    ships_id                 INTEGER     NOT NULL,
    start_of_contract        DATETIME    NOT NULL,
    end_of_contract          DATETIME    NOT NULL,
    status                   VARCHAR(45) NOT NULL,
    FOREIGN KEY (cruises_id) REFERENCES cruises (Id) ON DELETE CASCADE,
    FOREIGN KEY (ships_id)   REFERENCES ships (Id) ON DELETE CASCADE
);

CREATE VIEW orders_view AS SELECT o.id AS id,
                                  u.first_name AS first_name,
                                  u.last_name AS last_name,
                                  u.email AS email,
                                  u.id AS users_id,
                                  c.description AS description,
                                  c.status AS status_of_cruises,
                                  o.status AS status
                           FROM orders AS o
                                    JOIN users AS u ON o.users_id = u.id
                                    JOIN cruises AS c ON o.cruises_id = c.id;

CREATE VIEW ships_view AS SELECT  s.id AS id,
                                  s.name AS name,
                                  s.description AS description,
                                  s.capacity AS  capacity,
                                  chs.start_of_contract AS start_of_contract,
                                  chs.end_of_contract AS end_of_contract
                           FROM ships AS s
                                    JOIN cruises_has_ships AS chs ON chs.ships_id = s.id;

INSERT INTO roles (name)
VALUES ('admin');
INSERT INTO roles (name)
VALUES ('client');

INSERT INTO accounts (balance, file_name)
VALUES (0, 'Пусто');
INSERT INTO accounts (balance, file_name)
VALUES (0,'Пусто');

INSERT INTO users (login, password, first_name, last_name, email, phone, roles_id, accounts_id)
VALUES ('admin', '21232f297a57a5a743894a0e4a801fc3', ' Ivan ', 'Ivanov', 'admin@gmail.com',
        '+380992589898', 1, 1);

INSERT INTO users (login, password, first_name, last_name, email, phone, roles_id, accounts_id)
VALUES ('client', '62608e08adc29a8d6dbc9754e659f125', ' Petr ', 'Petrov', 'client@gmail.com',
        '+380992589896', 2, 2);


INSERT INTO ships (name, description,capacity)
VALUES ('Ориана', 'Океанический пассажирский',2000);
INSERT INTO ships (name, description,capacity)
VALUES ('Исследователь морей', 'Океанический пассажирский',2000);
INSERT INTO ships (name, description,capacity)
VALUES ('Минерва', 'Океанический пассажирский',2000);
INSERT INTO ships (name, description,capacity)
VALUES ('Титаник', 'Океанический пассажирский',2000);
INSERT INTO ships (name, description,capacity)
VALUES ('Титаник новый', 'Океанический пассажирский',2000);
INSERT INTO ships (name, description,capacity)
VALUES ('Титаник быстротонущий', 'Океанический пассажирский',2000);



INSERT INTO cruises (name, description, price,capacity, start_of_cruise, duration,status)
VALUES ('Малое путишествие', 'Одесса-Стамбул-Одесса', '2000', 1600,
        20230223120000, 10,'Не начался');
INSERT INTO cruises (name, description, price, capacity, start_of_cruise, duration,status)
VALUES ('Среднее путишествие', 'Одесса-Стамбул-Лисабон-Одесса', '5000', 1600,
        20230228120000, 15,'Не начался');
INSERT INTO cruises (name, description, price, capacity, start_of_cruise, duration,status)
VALUES ('Большое путишествие', 'Одесса-Стамбул-Палермо-Барселона-Лиссабон-Одесса', '10000',
        2000,20230130120000, 25,'Не начался');

INSERT INTO cruises (name, description, price,capacity, start_of_cruise, duration,status)
VALUES ('Малое путишествие', 'Одесса-Стамбул-Одесса', '2000',1600,
        20221223120000, 5,'Не начался');
INSERT INTO cruises (name, description, price,capacity, start_of_cruise, duration,status)
VALUES ('Среднее путишествие', 'Одесса-Стамбул-Лисабон-Одесса', '5000', 1600,
        20221228120000, 10,'Не начался');
INSERT INTO cruises (name, description, price,capacity, start_of_cruise, duration,status)
VALUES ('Большое путишествие', 'Одесса-Стамбул-Палермо-Барселона-Лиссабон-Одесса', '10000',
        2000,20221224120000, 15,'Не начался');


INSERT INTO cruises_has_ships (cruises_id, ships_id, start_of_contract,end_of_contract,status)
VALUES (1, 1, '20230223120000', 20230305120000,
        'Пусто');
INSERT INTO cruises_has_ships (cruises_id, ships_id, start_of_contract,end_of_contract,status)
VALUES (2, 2, '20230228120000', 20230315120000,
        'Пусто');
INSERT INTO cruises_has_ships (cruises_id, ships_id, start_of_contract,end_of_contract,status)
VALUES (3, 3, '20230130120000', 20230225120000,
        'Пусто');
INSERT INTO cruises_has_ships (cruises_id, ships_id, start_of_contract,end_of_contract,status)
VALUES (4, 4, '20221223120000', 20221228120000,
        'Пусто');
INSERT INTO cruises_has_ships (cruises_id, ships_id, start_of_contract,end_of_contract,status)
VALUES (5, 5, '20221228120000', 20230108120000,
        'Пусто');
INSERT INTO cruises_has_ships (cruises_id, ships_id, start_of_contract,end_of_contract,status)
VALUES (6, 6, '20230101120000', 20230116120000,
        'Пусто');


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
SELECT *
FROM cruises_has_ships;

