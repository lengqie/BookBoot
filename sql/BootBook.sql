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

INSERT INTO `user` VALUES ('2020','go','pass123',4,0,0,0);

INSERT INTO `books` VALUES ('110-120-119-10','Go语言','编程',0),
                           ('110-120-112-11','SpringBoot','编程',0),
                           ('110-120-112-11','SpringBoot','编程',0),
                           ('110-120-112-11','SpringBoot','编程',0),
                           ('110-120-112-11','SpringBoot','编程',0),
                           ('110-120-119-12','Mybatis实战','编程',0),
                           ('110-120-119-13','C指针','编程',0),
                           ('110-120-112-14','C-plus-plus','编程',0),
                           ('110-120-119-15','Lua脚本','编程',0),
                           ('110-120-119-16','算法','编程',0),
                           ('110-120-119-17','Linux','编程',0),
                           ('110-120-119-18','Docker核心技术','编程',0),
                           ('111-120-119-11','鲁迅小说','文学',0),
                           ('111-120-119-12','朝花夕拾','文学',0),
                           ('111-120-119-13','毛选','政治',0),
                           ('111-120-119-14','硬笔书法','书法',0),
                           ('111-120-119-15','草书','书法',0),
                           ('111-120-119-16','小篆','书法',0),
                           ('111-120-119-17','行书','书法',0),
                           ('111-120-119-18','唐诗宋词','文学',0)