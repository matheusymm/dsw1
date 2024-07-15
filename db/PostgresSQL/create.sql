-- Drop the database if Bicicleta exists
DROP DATABASE IF EXISTS Bicicleta;

-- Create the database
CREATE DATABASE Bicicleta;

-- Connect to the Bike database
\c bicicleta

CREATE SEQUENCE ClienteSeq;
CREATE SEQUENCE LocadoraSeq;
CREATE SEQUENCE LocacaoSeq;

-- Create table Cliente
CREATE TABLE Cliente (
    id INTEGER NOT NULL PRIMARY KEY DEFAULT NEXTVAL('ClienteSeq'),
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    cpf VARCHAR(15) NOT NULL UNIQUE,
    nome VARCHAR(255) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    sexo CHAR(15) NOT NULL,
    dataNascimento DATE NOT NULL,
    papel VARCHAR(10) NOT NULL
);

-- Create table Locadora
CREATE TABLE Locadora (
    id INTEGER NOT NULL PRIMARY KEY DEFAULT NEXTVAL('LocadoraSeq'),
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    cnpj VARCHAR(18) NOT NULL UNIQUE,
    nome VARCHAR(255) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    papel VARCHAR(10) NOT NULL
);

CREATE TABLE Locacao (
    id INTEGER NOT NULL PRIMARY KEY DEFAULT NEXTVAL('LocacaoSeq'),
    cpfCliente VARCHAR(15) NOT NULL,
    cnpjLocadora VARCHAR(18) NOT NULL,
    -- dataLocacao DATE NOT NULL,
    FOREIGN KEY (cpfCliente) REFERENCES Cliente(cpf),
    FOREIGN KEY (cnpjLocadora) REFERENCES Locadora(cnpj)
);

-- Insert data into Cliente
INSERT INTO Cliente(email, senha, cpf, nome, telefone, sexo, dataNascimento, papel) VALUES 
('user1@ufscar.br', 'user1', '123.456.789-01', 'User1', '(12)34567-8901', 'Masculino', '2024-01-01', 'user'),
('user2@ufscar.br', 'user2', '123.456.789-02', 'User2', '(12)34567-8902', 'Feminino', '2024-01-01', 'admin');

-- Insert data into Locadora
INSERT INTO Locadora(email, senha, cnpj, nome, cidade, papel) VALUES 
('Lugar1', 'lugar1', '12.345.678/9012-34', 'lugar1', 'São Carlos', 'vendor'),
('Lugar2', 'lugar2', '12.345.678/9012-35', 'lugar2', 'São Carlos', 'admin');

-- Insert data into Locacao
INSERT INTO Locacao(cpfCliente, cnpjLocadora) VALUES 
('123.456.789-01', '12.345.678/9012-34'),
('123.456.789-01', '12.345.678/9012-35');