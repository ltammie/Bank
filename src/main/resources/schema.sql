DROP ALL OBJECTS;

CREATE TABLE clients
(
 client_id      bigint NOT NULL auto_increment,
 name           varchar(50) NOT NULL,
 phone_number   varchar(20) NOT NULL,
 passport       varchar(10) NOT NULL,
 CONSTRAINT PK_client PRIMARY KEY ( client_id )
);

CREATE TABLE accounts
(
 account_id     bigint NOT NULL auto_increment,
 client_id      bigint NOT NULL,
 balance        bigint NOT NULL,
 account_number varchar(20) NOT NULL,
 CONSTRAINT PK_account PRIMARY KEY ( account_id, client_id ),
 CONSTRAINT FK_11 FOREIGN KEY (client_id) REFERENCES clients (client_id)
);

CREATE INDEX fkIdx_11 ON accounts
(
 client_id
);

CREATE TABLE cards
(
 card_id         bigint NOT NULL auto_increment,
 account_id      bigint NOT NULL,
 client_id       bigint NOT NULL,
 expiration_date varchar(50) NOT NULL,
 card_number     varchar(16) NOT NULL,
 CONSTRAINT PK_card PRIMARY KEY (card_id, account_id, client_id),
 CONSTRAINT FK_20 FOREIGN KEY (account_id, client_id) REFERENCES accounts (account_id, client_id)
);

CREATE INDEX fkIdx_20 ON cards
(
 account_id,
 client_id
);

CREATE TABLE counter_agents
(
 counter_agent_id   bigint NOT NULL auto_increment,
 name               varchar(50) NOT NULL,
 inn                varchar(20) NOT NULL,
 balance            bigint NOT NULL,
 CONSTRAINT PK_counter_agent PRIMARY KEY ( counter_agent_id )
);

INSERT INTO clients (name, phone_number, passport) VALUES
('John Wick', '8-800-555-35-35', '4132532543'),
('Bob Wick', '8-300-455-22-11', '6767222333'),
('Max Caulfield', '1-111-222-33-44', '1111222000'),
('The Dude', '4-333-222-11-00', '0000111000'),
('Bob Marlin', '6-333-444-22-99', '9999000888');

INSERT INTO accounts (client_id, balance, account_number) VALUES
(1, 1000000, '12345123451234512345'),
(2, 0, '00000111110000011111'),
(3, 300000, '22222333334444455555'),
(4, 599300, '12345000001234500000'),
(5, 123456, '99999000008888800000');

INSERT INTO cards (account_id, client_id, expiration_date, card_number) VALUES
(1, 1, '12/23', '1111222233334444'),
(2, 2,  '12/23', '1111000022223333'),
(3, 3,  '12/23', '0000111122223333'),
(3, 3,  '12/23', '4444222233331111'),
(3, 3,  '12/23', '0000222200002222');