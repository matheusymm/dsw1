-- Drop the database if it exists
DROP DATABASE IF EXISTS Bicicleta;

-- Create the database
CREATE DATABASE Bicicleta;

-- Connect to the Bike database
\c bicicleta

CREATE SEQUENCE Cliente_id_seq;
CREATE SEQUENCE Locadora_id_seq;
CREATE SEQUENCE Locacao_id_seq;

-- Create table Cliente
CREATE TABLE Cliente (
    id INTEGER NOT NULL PRIMARY KEY DEFAULT NEXTVAL('Cliente_id_seq'),
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    cpf VARCHAR(15) NOT NULL UNIQUE,
    nome VARCHAR(255) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    sexo CHAR(15) NOT NULL,
    data_nascimento DATE NOT NULL,
    tipo VARCHAR(10) NOT NULL
);

-- Create table Locadora
CREATE TABLE Locadora (
    id INTEGER NOT NULL PRIMARY KEY DEFAULT NEXTVAL('Locadora_id_seq'),
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    cnpj VARCHAR(18) NOT NULL UNIQUE,
    nome VARCHAR(255) NOT NULL,
    cidade VARCHAR(100) NOT NULL
);

CREATE TABLE Locacao (
    id INTEGER NOT NULL PRIMARY KEY DEFAULT NEXTVAL('Locacao_id_seq'),
    cpf_cliente VARCHAR(15) NOT NULL UNIQUE,
    cnpj_locadora VARCHAR(18) NOT NULL UNIQUE,
    data_locacao DATE NOT NULL,
    FOREIGN KEY (cpf_cliente) REFERENCES Cliente(cpf),
    FOREIGN KEY (cnpj_locadora) REFERENCES Locadora(cnpj)
);

-- Insert data into Cliente
INSERT INTO Cliente(email, senha, cpf, nome, telefone, sexo, data_nascimento, tipo) VALUES ('user@ufscar.br', 'user', '123.456.789-01', 'User', '(12)34567-8901', 'M', '2024-01-01', 'user');

-- Insert data into Locadora
INSERT INTO Locadora(email, senha, cnpj, nome, cidade) VALUES ('Lugar', 'lugar', '12.345.678/9012-34', 'lugar', 'São Carlos');

-- Insert data into Locacao
INSERT INTO Locacao(cpf_cliente, cnpj_locadora, data_locacao) VALUES ('123.456.789-01', '12.345.678/9012-34', '2024-07-01');