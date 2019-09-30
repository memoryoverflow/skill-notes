/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : skill-note-test

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 30/09/2019 19:01:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_knowledge
-- ----------------------------
DROP TABLE IF EXISTS `tb_knowledge`;
CREATE TABLE `tb_knowledge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` longtext,
  `status` int(11) DEFAULT '1',
  `deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_knowledge
-- ----------------------------
BEGIN;
INSERT INTO `tb_knowledge` VALUES (1, NULL, NULL, 'test记录', '> 大家好\n', 1, 0);
INSERT INTO `tb_knowledge` VALUES (2, NULL, NULL, 'tttttt', '>  大家好  \n\n#  同志们辛苦了\n\n## 我爱你们\n\n@[toc]', 1, 0);
INSERT INTO `tb_knowledge` VALUES (3, NULL, NULL, '1', '11', 1, 1);
INSERT INTO `tb_knowledge` VALUES (4, NULL, NULL, '你好', '>  源码大时代', 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT '0',
  `article_id` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT '1',
  `sort` int(11) DEFAULT NULL,
  `deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
BEGIN;
INSERT INTO `tb_menu` VALUES (1, '2019-09-30 18:14:12', '2019-09-30 18:21:51', 'test', 0, NULL, 1, 99, 0);
INSERT INTO `tb_menu` VALUES (2, '2019-09-30 18:21:43', '2019-09-30 18:47:10', 'test记录', 1, '1', 1, 99, 0);
INSERT INTO `tb_menu` VALUES (3, '2019-09-30 18:46:09', '2019-09-30 18:47:15', 'tttttt', 1, '2', 1, 99, 0);
INSERT INTO `tb_menu` VALUES (4, '2019-09-30 18:46:12', '2019-09-30 18:47:20', '1', 1, '3', 1, 99, 1);
INSERT INTO `tb_menu` VALUES (5, '2019-09-30 18:45:40', '2019-09-30 18:47:24', 'qqq', 0, NULL, 1, 99, 0);
INSERT INTO `tb_menu` VALUES (6, '2019-09-30 18:49:17', NULL, '源码时代', 0, NULL, 1, 99, 0);
INSERT INTO `tb_menu` VALUES (7, NULL, NULL, '你好', 6, '4', 1, 99, 0);
COMMIT;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `login_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `token_expire_time` bigint(20) DEFAULT '7200000',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
BEGIN;
INSERT INTO `tb_user` VALUES (1, '2019-09-30 17:59:32', '2019-09-30 18:59:59', NULL, 'admin', '123456', 'da0fbb3d-f48d-4c26-9f20-c0b6929877c4', 7200000);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
