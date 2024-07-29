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
    dataLocacao VARCHAR(50) NOT NULL,
    FOREIGN KEY (cpfCliente) REFERENCES Cliente(cpf),
    FOREIGN KEY (cnpjLocadora) REFERENCES Locadora(cnpj)
);

-- Insert data into Cliente
INSERT INTO Cliente(email, senha, cpf, nome, telefone, sexo, dataNascimento, papel) VALUES 
('admin@ufscar.br', 'admin', '123.456.789-00', 'Admin', '(12)34567-8900', 'Masculino', '2024-01-01', 'admin'),
('user@ufscar.br', 'user', '123.456.789-01', 'User', '(12)34567-8901', 'Feminino', '2024-01-01', 'cliente');

-- Insert data into Locadora
INSERT INTO Locadora(email, senha, cnpj, nome, cidade, papel) VALUES 
('admin@locadora.br', 'admin', '12.345.678/9012-34', 'Admin', 'São Carlos', 'admin'),
('loc1@locadora.br', 'loc1', '12.345.678/9012-35', 'Loc1', 'São Carlos', 'locadora'),
('loc2@locadora.br', 'loc2', '12.345.678/9012-36', 'Loc2', 'São Paulo', 'locadora');