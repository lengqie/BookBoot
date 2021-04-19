CREATE DATABASE  boot_book;

USE `boot_book`;
-- 用户表
CREATE TABLE `user`(
	`id` VARCHAR(50) PRIMARY KEY,
	`name` VARCHAR(20),
	`password` VARCHAR(20),
	`defaultCount` INT DEFAULT 4,
	`count` INT DEFAULT 0,
	`total` INT DEFAULT 0,
	`balance` DECIMAL DEFAULT 0
);
-- 书籍信息
CREATE TABLE `book`(
	`Isbn` VARCHAR(20) PRIMARY KEY,
	`name` VARCHAR(20) UNIQUE,
	`type` VARCHAR(10),
	`hot` INT DEFAULT 0,
	`num` INT DEFAULT 5
);

-- 管理员 账号 密码
CREATE TABLE admin(
	`name` VARCHAR(20) PRIMARY KEY,
	`password` VARCHAR(20)
);
-- 借阅记录
CREATE TABLE record(
	`record_id` VARCHAR(50) PRIMARY KEY,
	`Isbn` VARCHAR(20),
	`user_id` VARCHAR(50),
	`time` DATE,
	`days` INT,
	`success` INT,
	FOREIGN KEY(`Isbn`) REFERENCES `book`(`Isbn`),
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
INSERT INTO `admin` VALUES ('admin','root');

INSERT INTO `user` VALUES ('2020','go','pass123',4,0,0,5);

INSERT INTO `book` VALUES ('110-120-119-00','Java语言','编程',0,5),
                           ('110-120-119-01','JavaScript语言','编程',0,5),
                           ('110-120-119-10','Go语言','编程',0,5),
                           ('110-120-112-11','SpringBoot','编程',0,5),
                           ('110-120-112-11','SpringBoot','编程',0,5),
                           ('110-120-112-11','SpringBoot','编程',0,5),
                           ('110-120-112-11','SpringBoot','编程',0,5),
                           ('110-120-119-12','Mybatis实战','编程',0,5),
                           ('110-120-119-13','C指针','编程',0,5),
                           ('110-120-112-14','C-plus-plus','编程',0,5),
                           ('110-120-119-15','Lua脚本','编程',0,5),
                           ('110-120-119-16','算法','编程',0,5),
                           ('110-120-119-17','Linux','编程',0,5),
                           ('110-120-119-18','Docker核心技术','编程',0,5),
                           ('111-120-119-11','鲁迅小说','文学',0,5),
                           ('111-120-119-12','朝花夕拾','文学',0,5),
                           ('111-120-119-13','毛选','政治',0,5),
                           ('111-120-119-14','硬笔书法','书法',0,5),
                           ('111-120-119-15','草书','书法',0,5),
                           ('111-120-119-16','小篆','书法',0,5),
                           ('111-120-119-17','行书','书法',0,5),
                           ('111-120-119-18','唐诗宋词','文学',0,5);



-- Test SQL...
SELECT COUNT(1) FROM admin WHERE `password`='123'
select book.name,book., record.time,record.days from record, book where book.Isbn = record.Isbn AND record.user_id = 2020
ALTER TABLE user ADD UNIQUE (name)