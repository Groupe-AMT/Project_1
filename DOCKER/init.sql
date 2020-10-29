DROP SCHEMA IF EXISTS AMT;

CREATE SCHEMA IF NOT EXISTS AMT CHARACTER SET utf8;

USE AMT;

CREATE USER 'admin'@'%' IDENTIFIED BY 'root';
GRANT ALL PRIVILEGES ON *.* to 'admin'@'%';

CREATE TABLE IF NOT EXISTS AMT.Person (
	id varchar(100) NOT NULL,
	username varchar(100) NOT NULL,
	email varchar(100) NOT NULL,
	firstname varchar(100) NOT NULL,
	lastname varchar(100) NOT NULL,
	password varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS AMT.Vote (
	id varchar(100) NOT NULL,
	author varchar(100) NOT NULL,
	`type` varchar(100) NOT NULL,
	questionId varchar(100) NULL,
	answerId varchar(100) NULL,
	vote INTEGER NULL
);

CREATE TABLE IF NOT EXISTS AMT.Comment (
	id varchar(100) NOT NULL,
	author varchar(100) NOT NULL,
	content LONGTEXT NOT NULL,
	`type` varchar(100) NOT NULL,
	questionId varchar(100) NULL,
	answerId varchar(100) NULL,
	`date` varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS AMT.Answer (
	id varchar(100) NOT NULL,
	author varchar(100) NOT NULL,
	content LONGTEXT NOT NULL,
	questionId varchar(100) NOT NULL,
	`date` varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS AMT.Question (
	id varchar(100) NOT NULL,
	subject varchar(100) NOT NULL,
	author varchar(100) NOT NULL,
	content LONGTEXT NOT NULL,
	tags varchar(100) NOT NULL,
	`date` varchar(100) NOT NULL
);
