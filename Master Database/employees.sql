/*
Navicat MySQL Data Transfer

Source Server         : My Connection
Source Server Version : 50505
Source Host           : 127.0.0.1:3306
Source Database       : updated-p-op

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2020-09-09 10:14:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for employees
-- ----------------------------
DROP TABLE IF EXISTS `employees`;
CREATE TABLE `employees` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `branchid` int(10) DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `first_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `middle_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `suffixed` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_multipos` tinyint(1) DEFAULT 0,
  `added_by` int(11) DEFAULT NULL COMMENT 'user',
  `date_added` datetime DEFAULT NULL,
  `modified_by` int(11) DEFAULT NULL,
  `date_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of employees
-- ----------------------------
INSERT INTO `employees` VALUES ('1', '1', null, 'Admin', 'CBIT', null, null, '0', null, '2019-10-23 16:05:26', null, '2020-06-18 10:07:28');
INSERT INTO `employees` VALUES ('4', '12', null, 'Salangsang', 'Erickson', null, null, '0', null, '2019-10-24 11:32:41', null, null);
INSERT INTO `employees` VALUES ('5', '13', null, 'Juco', 'Yumika', null, null, '0', null, '2019-10-24 11:34:02', null, '2020-06-18 10:20:02');
INSERT INTO `employees` VALUES ('6', '14', null, 'Fernandez', 'Arnold', null, null, '0', null, '2019-10-24 11:35:42', null, null);
INSERT INTO `employees` VALUES ('7', '15', null, 'Guevarra', 'Jayriz', null, null, '0', null, '2019-10-24 11:41:09', null, null);
INSERT INTO `employees` VALUES ('8', '16', null, 'Pamintuan', 'Randy', null, null, '0', null, '2019-10-28 14:51:09', null, null);
INSERT INTO `employees` VALUES ('9', '17', null, 'Lambino', 'Kevin', null, null, '0', null, '2019-10-28 15:44:47', null, null);
INSERT INTO `employees` VALUES ('10', '18', null, 'Account', 'Dummy', null, null, '0', null, '2020-06-19 14:11:16', null, null);
INSERT INTO `employees` VALUES ('11', '19', null, 'Salangsang', 'John', 'Ellar', null, '1', '18', '2020-06-19 06:47:47', null, '2020-06-19 14:53:12');
INSERT INTO `employees` VALUES ('12', '20', null, 'Peralta', 'Charmaine', 'Ellar', null, '1', '18', '2020-06-19 06:49:57', null, '2020-06-19 14:53:01');
INSERT INTO `employees` VALUES ('13', '21', null, 'Salangsang', 'Jeffrey', 'Daytao', null, '1', '18', '2020-06-19 06:52:15', null, '2020-06-19 14:52:51');
