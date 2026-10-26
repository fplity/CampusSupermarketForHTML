-- Optional: use this only if the receiving computer does not have these tables.
-- Existing tables and rows are kept; this script does not reset any data.
CREATE DATABASE IF NOT EXISTS cs CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE cs;

CREATE TABLE IF NOT EXISTS `user` (
    account VARCHAR(64) NOT NULL PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    tel VARCHAR(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS shopping (
    sid VARCHAR(8) NOT NULL PRIMARY KEY,
    sname VARCHAR(64) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    image VARCHAR(64) NOT NULL,
    stock INT NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
