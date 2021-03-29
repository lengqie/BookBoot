CREATE DATABASE  boot_book;

USE `boot_book`;
-- 用户表
CREATE TABLE `user`(
	`id` VARCHAR(20) PRIMARY KEY,
	`name` VARCHAR(20),
	`password` VARCHAR(20),
	`default_count` INT DEFAULT 4,
	`count` INT DEFAULT 0,
	`total` INT DEFAULT 0,
	`balance` INT DEFAULT 0
);
-- 书籍信息
CREATE TABLE `books`(
	`Isbn` VARCHAR(20) PRIMARY KEY,
	`name` VARCHAR(20),
	`type` VARCHAR(10),
	`hot` INT DEFAULT 0
);

-- 管理员 账号 密码
CREATE TABLE admin(
	`name` VARCHAR(20) PRIMARY KEY,
	`password` VARCHAR(20)
);
-- 借阅记录
CREATE TABLE record(
	`Isbn` VARCHAR(20),
	`user_id` VARCHAR(20),
	`time` DATE,
	`days` INT,
	`success` INT,
	FOREIGN KEY(`Isbn`) REFERENCES `books`(`Isbn`),
	FOREIGN KEY(`user_id`) REFERENCES `user`(`id`)
);

-- 预约 和 推荐 
CREATE TABLE appointment(
	`IsbN` VARCHAR(20),
	`user_id` VARCHAR(20),
	`time` DATE
); 
CREATE TABLE recommend(
	`name` VARCHAR(20),
	`Isbn` VARCHAR(20),
	`success` INT
);

-- 添加一些 数据
INSERT INTO `admin` VALUES ('root','root');
