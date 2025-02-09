CREATE DATABASE LOJA;

USE LOJA;

CREATE TABLE CLIENTE
(
    CPF      VARCHAR(11),
    NOME     VARCHAR(200),
    ENDERECO VARCHAR(200),
    TELEFONE VARCHAR(50),
    CONSTRAINT CLIENTE_PK PRIMARY KEY (CPF)
);

CREATE TABLE FUNCIONARIO
(
    CPF      VARCHAR(11),
    NOME     VARCHAR(200),
    ENDERECO VARCHAR(200),
    TELEFONE VARCHAR(50),
    CONSTRAINT FUNCIONARIO_PK PRIMARY KEY (CPF)
);

CREATE TABLE PRODUTO
(
    ID         INT AUTO_INCREMENT,
    NOME       VARCHAR(200),
    VALOR_UNIT DOUBLE,
    QUANTIDADE INT,
    CONSTRAINT PRODUTO_PK PRIMARY KEY (ID)
);

CREATE TABLE PEDIDO
(
    ID                 INT AUTO_INCREMENT,
    CPF_CLIENTE_FK     VARCHAR(11),
    CPF_FUNCIONARIO_FK VARCHAR(11),
    VALOR_TOTAL        DOUBLE,
    CONSTRAINT PEDIDO_CLIENTE_FK FOREIGN KEY (CPF_CLIENTE_FK) REFERENCES CLIENTE (CPF),
    CONSTRAINT PEDIDO_FUNCIONARIO_FK FOREIGN KEY (CPF_FUNCIONARIO_FK) REFERENCES FUNCIONARIO (CPF),
    CONSTRAINT PEDIDO_PK PRIMARY KEY (ID)
);

CREATE TABLE ITEM_PEDIDO
(
    ID            INT AUTO_INCREMENT,
    ID_PEDIDO_FK  INT,
    ID_PRODUTO_FK INT,
    QUANTIDADE    INT,
    VALOR         DOUBLE,
    CONSTRAINT ITEM_PEDIDO_PEDIDO_FK FOREIGN KEY (ID_PEDIDO_FK) REFERENCES PEDIDO (ID),
    CONSTRAINT ITEM_PEDIDO_PRODUTO_FK FOREIGN KEY (ID_PRODUTO_FK) REFERENCES PRODUTO (ID),
    CONSTRAINT ITEM_PEDIDO_PK PRIMARY KEY (ID)
);


INSERT INTO CLIENTE (CPF, NOME, ENDERECO, TELEFONE)
VALUES ('04881132105', 'Gabrielly Isis Eliane Ferreira', 'Rua Um, 364, Centro - Maceió - AL', '21998921725'),
       ('95092328401', 'Raul Kevin Rafael Vieira', 'Rua Fonte Nova, 7441, Centro - Arapiraca - AL', '68994965495'),
       ('43185649168', 'Nicole Ester Almada', 'Rua das Morangueiras, 874, Centro - Maceió - AL', '66994116773'),
       ('80523366515', 'Marcelo Daniel Murilo Nascimento', 'Rua Taipas 476, Centro - Recife - PE', '11997773091'),
       ('71824180101', 'Benedito Marcelo Cardos', 'Beco Samaritano 587, Centro - Arapiraca - AL', '92997427413');

INSERT INTO FUNCIONARIO (CPF, NOME, ENDERECO, TELEFONE)
VALUES ('92022367120', 'Ryan Enrico Leandro Santos', 'Travessa Persondas de Carvalho, 564, Centro - Maceió - AL',
        '98995048631'),
       ('43293608027', 'Nicolas Bryan Assis', 'Travessa 3, 4712, Centro - Arapiraca - AL', '63981910674'),
       ('24080075693', 'Ayla Lorena Alves', 'Rua Primeiro de Abril, 1234, Centro - Maceió - AL', '94981303450'),
       ('25806037606', 'Isabelly Clara Luana da Cruz', 'Rua Vereador Osni Ortiga. 8476, Centro - Recife - PE',
        '48998120514'),
       ('84095976241', 'Márcia Malu Adriana Baptista', 'Rua Nicolina Samaritano, 254, Centro - Arapiraca - AL',
        '21982885017');


INSERT INTO PRODUTO (NOME, VALOR_UNIT, QUANTIDADE)
VALUES ('TV 55" LED', 5000.0, 38),
       ('MONITOR 19" LED ULTRAWIDE', 1499.0, 50),
       ('PLAYSTATION 5', 3699.0, 24),
       ('NOTEBOOK 15" 8GB RAM 256GB SDD', 4287.0, 24),
       ('IMPRESSORA MULTIFUNCIONAL', 1199.0, 21),
       ('SMARTPHONE 8GB RAM 512GB', 3999.0, 74),
       ('SMARTPHONE 4GB RAM 128GB', 1599.0, 84),
       ('CAIXA DE SOM BLUETOOTH', 2547.0, 38),
       ('FECHADURA DIGITAL', 874.0, 100),
       ('FONE DE OUVIDO BLUETOOTH', 214.0, 254),
       ('FRITADEIRA AIR FRYER 12L', 752.0, 142),
       ('TECLADO SEM FIO', 91.0, 354),
       ('GELADEIRA 90L', 1199.0, 87),
       ('SMARTBAND GRAFITE', 357.0, 177),
       ('AR CONDICIONADO SPLIT INVERTER', 3687.0, 64),
       ('NOTEBOOK 13" 16GB RAM 512GB SDD', 15000.0, 17);

INSERT INTO PEDIDO (CPF_CLIENTE_FK, CPF_FUNCIONARIO_FK, VALOR_TOTAL)
VALUES ('04881132105', '92022367120', 16626),
       ('95092328401', '43293608027', 7797),
       ('43185649168', '24080075693', 6528),
       ('80523366515', '25806037606', 20182),
       ('71824180101', '84095976241', 6849);


INSERT INTO ITEM_PEDIDO (ID_PEDIDO_FK, ID_PRODUTO_FK, QUANTIDADE, VALOR)
VALUES (1, 16, 1, 15000),
       (1, 9, 1, 874),
       (1, 11, 1, 752),
       (2, 3, 2, 7398),
       (2, 6, 1, 399),
       (3, 10, 3, 642),
       (3, 4, 1, 4287),
       (3, 7, 1, 1599),
       (4, 1, 1, 5000),
       (4, 16, 1, 15000),
       (4, 12, 2, 182),
       (5, 13, 1, 1199),
       (5, 11, 1, 752),
       (5, 5, 1, 1199),
       (5, 6, 1, 3699);