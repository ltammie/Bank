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
 expiration_date date NOT NULL,
 card_number     varchar(16) NOT NULL,
 CONSTRAINT PK_card PRIMARY KEY (card_id, account_id, client_id),
 CONSTRAINT FK_20 FOREIGN KEY (account_id, client_id) REFERENCES accounts (account_id, client_id)
);

CREATE INDEX fkIdx_20 ON cards
(
 account_id,
 client_id
);