-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.26 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  11.2.0.6221
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- 导出 boot_book 的数据库结构
CREATE DATABASE IF NOT EXISTS `boot_book` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `boot_book`;

-- 导出  表 boot_book.admin 结构
CREATE TABLE IF NOT EXISTS `admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 正在导出表  boot_book.admin 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` (`id`, `name`, `password`, `status`, `create_time`, `update_time`) VALUES
	(0, 'root', '1e1a6340077f90de59a64af1b17ab0a7', 0, '2021-04-06 05:54:36', '2021-04-27 05:54:36'),
	(2, 'test', 'a11ea616793d5018e54f6cffd34096df', -2, '2021-04-27 13:57:44', '2021-04-27 13:58:29'),
	(3, 'test0', 'a11ea616793d5018e54f6cffd34096df', 0, '2021-04-27 13:58:25', '2021-04-27 13:58:25');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;

-- 导出  表 boot_book.book 结构
CREATE TABLE IF NOT EXISTS `book` (
  `isbn` varchar(20) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `hot` int(11) DEFAULT '0',
  `num` int(11) DEFAULT '5',
  `status` int(11) DEFAULT '1',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`isbn`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  boot_book.book 的数据：~19 rows (大约)
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` (`isbn`, `name`, `type`, `hot`, `num`, `status`, `create_time`, `update_time`) VALUES
	('110-120-112-11', 'SpringBoot', '编程', 18, 5, -2, '2021-04-01 00:00:00', '2021-04-01 00:00:00'),
	('110-120-112-14', 'C-plus-plus', '编程', 6, 5, 0, '2021-04-01 00:00:00', '2021-04-01 00:00:00'),
	('110-120-119-00', 'Java语言', '编程', 25, 4, 0, '2021-04-01 00:00:00', '2021-04-01 00:00:00'),
	('110-120-119-01', 'JavaScript语言', '编程', 26, 5, 0, '2021-04-01 00:00:00', '2021-04-01 00:00:00'),
	('110-120-119-10', 'Go语言', '编程', 24, 5, 0, '2021-04-01 00:00:00', '2021-04-01 00:00:00'),
	('110-120-119-12', 'Mybatis实战', '编程', 4, 5, 0, '2021-04-01 00:00:00', '2021-04-01 00:00:00'),
	('110-120-119-13', 'C指针', '编程', 5, 5, 0, '2021-04-01 00:00:00', '2021-04-01 00:00:00'),
	('110-120-119-15', 'Lua脚本', '编程', 4, 5, 0, '2021-04-01 00:00:00', '2021-04-01 00:00:00'),
	('110-120-119-16', '算法', '编程', 4, 5, 0, '2021-04-01 00:00:00', '2021-04-01 00:00:00'),
	('110-120-119-17', 'Linux', '编程', 4, 5, 0, '2021-04-01 00:00:00', '2021-04-01 00:00:00'),
	('110-120-119-18', 'Docker核心技术', '编程', 5, 5, 0, '2021-04-01 00:00:00', '2021-04-01 00:00:00'),
	('111-120-119-11', '鲁迅小说', '文学', 4, 5, 0, '2021-04-01 00:00:00', '2021-04-01 00:00:00'),
	('111-120-119-12', '朝花夕拾', '文学', 5, 5, 0, '2021-04-01 00:00:00', '2021-04-01 00:00:00'),
	('111-120-119-13', '毛选', '政治', 1962, 5, 0, '2021-04-01 00:00:00', '2021-04-01 00:00:00'),
	('111-120-119-14', '硬笔书法', '书法', 5, 5, 0, '2021-04-01 00:00:00', '2021-04-01 00:00:00'),
	('111-120-119-15', '草书', '书法', 4, 5, 0, '2021-04-01 00:00:00', '2021-04-01 00:00:00'),
	('111-120-119-16', '小篆', '书法', 4, 5, 0, '2021-04-01 00:00:00', '2021-04-01 00:00:00'),
	('111-120-119-17', '行书', '书法', 4, 5, 0, '2021-04-01 00:00:00', '2021-04-01 00:00:00'),
	('111-120-119-18', '唐诗宋词', '文学', 22, 5, 0, '2021-04-01 00:00:00', '2021-04-01 00:00:00');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;

-- 导出  表 boot_book.payment 结构
CREATE TABLE IF NOT EXISTS `payment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `record_id` bigint(20) DEFAULT NULL,
  `amount` decimal(20,6) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `record_id` (`record_id`),
  CONSTRAINT `FK_payment_record` FOREIGN KEY (`record_id`) REFERENCES `record` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 正在导出表  boot_book.payment 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` (`id`, `record_id`, `amount`, `create_time`) VALUES
	(3, 2, 0.500000, '2021-04-26 14:06:52');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;

-- 导出  表 boot_book.recommend 结构
CREATE TABLE IF NOT EXISTS `recommend` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `user_id` bigint(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `isbn` char(50) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_recommend_user` (`user_id`),
  CONSTRAINT `FK_recommend_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  boot_book.recommend 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `recommend` DISABLE KEYS */;
INSERT INTO `recommend` (`id`, `user_id`, `name`, `isbn`, `type`, `status`, `create_time`, `update_time`) VALUES
	(3, 2020, '深入了解计算机系统', '111-111-111-11', '计算机', 0, '2021-04-01 00:00:00', '2021-03-01 00:00:00');
/*!40000 ALTER TABLE `recommend` ENABLE KEYS */;

-- 导出  表 boot_book.record 结构
CREATE TABLE IF NOT EXISTS `record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `isbn` varchar(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `days` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `Isbn` (`isbn`) USING BTREE,
  KEY `FK_record_user` (`user_id`),
  CONSTRAINT `FK_record_book` FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`),
  CONSTRAINT `FK_record_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- 正在导出表  boot_book.record 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `record` DISABLE KEYS */;
INSERT INTO `record` (`id`, `isbn`, `user_id`, `days`, `status`, `create_time`, `update_time`) VALUES
	(1, '110-120-119-00', 2021, 30, 0, '2021-03-31 00:00:00', NULL),
	(2, '110-120-119-00', 2021, 10, 0, '2021-03-31 00:00:00', NULL),
	(3, '110-120-119-00', 2020, 30, 0, '2021-03-31 00:00:00', NULL),
	(4, '110-120-119-00', 2020, 30, 0, '2021-03-31 00:00:00', NULL),
	(5, '110-120-119-00', 2020, 30, 0, '2021-01-28 00:00:00', NULL);
/*!40000 ALTER TABLE `record` ENABLE KEYS */;

-- 导出  表 boot_book.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `max` int(11) DEFAULT '4',
  `count` int(11) DEFAULT '0',
  `total` int(11) DEFAULT '0',
  `balance` decimal(20,6) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `update_time` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2022 DEFAULT CHARSET=utf8;

-- 正在导出表  boot_book.user 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `name`, `password`, `max`, `count`, `total`, `balance`, `status`, `create_time`, `update_time`) VALUES
	(2020, 'zhang3', 'fbe054703942f88a0fb6d7a97c7bfea0', 4, 3, 4, 0.000000, NULL, '2021-04-08', '2021-04-08'),
	(2021, 'li4', 'fbe054703942f88a0fb6d7a97c7bfea0', 4, 0, 0, 0.000000, NULL, '2021-04-08', '2021-04-08');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
