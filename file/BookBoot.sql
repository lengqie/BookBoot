/*
 Navicat Premium Data Transfer

 Source Server         : mysql5.7
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : boot_book

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 11/05/2021 13:41:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`
(
    `id`          bigint(20)                                             NOT NULL AUTO_INCREMENT,
    `name`        varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `password`    varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `status`      int(11)                                                NULL DEFAULT NULL,
    `create_time` datetime                                               NULL DEFAULT NULL,
    `update_time` datetime                                               NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `name` (`name`) USING BTREE,
    UNIQUE INDEX `id` (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`
(
    `isbn`        varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `name`        varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `type`        varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `hot`         int(11)                                                NULL DEFAULT 0,
    `num`         int(11)                                                NULL DEFAULT 5,
    `status`      int(11)                                                NULL DEFAULT 1,
    `create_time` datetime                                               NULL DEFAULT NULL,
    `update_time` datetime                                               NULL DEFAULT NULL,
    PRIMARY KEY (`isbn`) USING BTREE,
    INDEX `idx_name_type_hot` (`name`, `type`, `hot`) USING BTREE,
    FULLTEXT INDEX `ft_index` (`name`) WITH PARSER `ngram`
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment`
(
    `id`          bigint(20)     NOT NULL AUTO_INCREMENT,
    `record_id`   bigint(20)     NULL DEFAULT NULL,
    `amount`      decimal(20, 6) NULL DEFAULT NULL,
    `create_time` datetime       NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `record_id` (`record_id`) USING BTREE,
    CONSTRAINT `FK_payment_record` FOREIGN KEY (`record_id`) REFERENCES `record` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for recommend
-- ----------------------------
DROP TABLE IF EXISTS `recommend`;
CREATE TABLE `recommend`
(
    `id`          bigint(20)                                             NOT NULL DEFAULT 0,
    `user_id`     bigint(20)                                             NULL     DEFAULT NULL,
    `name`        varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL,
    `isbn`        char(50) CHARACTER SET utf8 COLLATE utf8_general_ci    NULL     DEFAULT NULL,
    `type`        varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL,
    `status`      int(11)                                                NULL     DEFAULT NULL,
    `create_time` datetime                                               NULL     DEFAULT NULL,
    `update_time` datetime                                               NULL     DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `FK_recommend_user` (`user_id`) USING BTREE,
    CONSTRAINT `FK_recommend_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`
(
    `id`          bigint(20)                                             NOT NULL AUTO_INCREMENT,
    `isbn`        varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `user_id`     bigint(20)                                             NULL DEFAULT NULL,
    `days`        int(11)                                                NULL DEFAULT NULL,
    `status`      int(11)                                                NULL DEFAULT NULL,
    `create_time` datetime                                               NULL DEFAULT NULL,
    `update_time` datetime                                               NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `Isbn` (`isbn`) USING BTREE,
    INDEX `FK_record_user` (`user_id`) USING BTREE,
    CONSTRAINT `FK_record_book` FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `FK_record_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`          bigint(20)                                             NOT NULL AUTO_INCREMENT,
    `name`        varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `password`    varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `max`         int(11)                                                NULL DEFAULT 4,
    `count`       int(11)                                                NULL DEFAULT 0,
    `total`       int(11)                                                NULL DEFAULT 0,
    `balance`     decimal(20, 6)                                         NULL DEFAULT NULL,
    `status`      int(11)                                                NULL DEFAULT NULL,
    `create_time` date                                                   NULL DEFAULT NULL,
    `update_time` date                                                   NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `name` (`name`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2022
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;



INSERT INTO `admin` (`id`, `name`, `password`, `status`, `create_time`, `update_time`)
VALUES (0, 'root', '1e1a6340077f90de59a64af1b17ab0a7', 0, '2021-04-06 05:54:36', '2021-04-27 05:54:36'),
       (2, 'test', 'a11ea616793d5018e54f6cffd34096df', -2, '2021-04-27 13:57:44', '2021-04-27 13:58:29'),
       (3, 'test0', 'a11ea616793d5018e54f6cffd34096df', 0, '2021-04-27 13:58:25', '2021-04-27 13:58:25');


INSERT INTO `book` (`isbn`, `name`, `type`, `hot`, `num`, `status`, `create_time`, `update_time`)
VALUES ('110-120-112-11', 'SpringBoot', '编程', 18, 5, -2, '2021-04-01 00:00:00', '2021-04-01 00:00:00'),
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



INSERT INTO `payment` (`id`, `record_id`, `amount`, `create_time`)
VALUES (3, 2, 0.500000, '2021-04-26 14:06:52');



INSERT INTO `recommend` (`id`, `user_id`, `name`, `isbn`, `type`, `status`, `create_time`, `update_time`)
VALUES (3, 2020, '深入了解计算机系统', '111-111-111-11', '计算机', 0, '2021-04-01 00:00:00', '2021-03-01 00:00:00');



INSERT INTO `record` (`id`, `isbn`, `user_id`, `days`, `status`, `create_time`, `update_time`)
VALUES (1, '110-120-119-00', 2021, 30, 0, '2021-03-31 00:00:00', NULL),
       (2, '110-120-119-00', 2021, 10, 0, '2021-03-31 00:00:00', NULL),
       (3, '110-120-119-00', 2020, 30, 0, '2021-03-31 00:00:00', NULL),
       (4, '110-120-119-00', 2020, 30, 0, '2021-03-31 00:00:00', NULL),
       (5, '110-120-119-00', 2020, 30, 0, '2021-01-28 00:00:00', NULL);


INSERT INTO `user` (`id`, `name`, `password`, `max`, `count`, `total`, `balance`, `status`, `create_time`,
                    `update_time`)
VALUES (2020, 'zhang3', 'fbe054703942f88a0fb6d7a97c7bfea0', 4, 3, 4, 0.000000, NULL, '2021-04-08', '2021-04-08'),
       (2021, 'li4', 'fbe054703942f88a0fb6d7a97c7bfea0', 4, 0, 0, 0.000000, NULL, '2021-04-08', '2021-04-08');