CREATE DATABASE AMT;

CREATE TABLE AMT.Person (
	id varchar(100) NOT NULL,
	username varchar(100) NOT NULL,
	email varchar(100) NOT NULL,
	firstname varchar(100) NOT NULL,
	lastname varchar(100) NOT NULL,
	password varchar(100) NOT NULL
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE AMT.Vote (
	id varchar(100) NOT NULL,
	author varchar(100) NOT NULL,
	`type` varchar(100) NOT NULL,
	questionId varchar(100) NULL,
	answerId varchar(100) NULL,
	vote BOOL NULL
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE AMT.Comment (
	id varchar(100) NOT NULL,
	author varchar(100) NOT NULL,
	content LONGTEXT NOT NULL,
	`type` varchar(100) NOT NULL,
	questionId varchar(100) NULL,
	answerId varchar(100) NULL,
	`date` varchar(100) NOT NULL
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE AMT.Answer (
	id varchar(100) NOT NULL,
	author varchar(100) NOT NULL,
	content LONGTEXT NOT NULL,
	questionId varchar(100) NOT NULL,
	`date` varchar(100) NOT NULL
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE AMT.Question (
	id varchar(100) NOT NULL,
	subject varchar(100) NOT NULL,
	author varchar(100) NOT NULL,
	content LONGTEXT NOT NULL,
	tags varchar(100) NOT NULL,
	`date` varchar(100) NOT NULL
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;
