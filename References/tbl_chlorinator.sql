/*
Navicat MySQL Data Transfer

Source Server         : ErickPOperations
Source Server Version : 50562
Source Host           : us-cdbr-east-02.cleardb.com:3306
Source Database       : heroku_75a84508a19a06f

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2020-08-04 11:21:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_chlorinator
-- ----------------------------
DROP TABLE IF EXISTS `tbl_chlorinator`;
CREATE TABLE `tbl_chlorinator` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Unique Identifier',
  `code` varchar(255) DEFAULT '',
  `brand_name` varchar(100) DEFAULT NULL,
  `model_no` int(11) DEFAULT NULL,
  `serial_no` int(11) DEFAULT NULL,
  `stroke_per_minute` double(11,0) DEFAULT '0',
  `active_status` tinyint(1) DEFAULT '0',
  `added_by` int(11) DEFAULT NULL COMMENT 'user',
  `date_added` datetime DEFAULT NULL,
  `modified_by` int(11) DEFAULT NULL,
  `date_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tbl_chlorinator
-- ----------------------------
INSERT INTO `tbl_chlorinator` VALUES ('1', 'code 1', 'ACEJET', '555', '325', '0', '0', '1', '2019-10-05 08:02:38', '13', '2020-07-17 00:21:28');
INSERT INTO `tbl_chlorinator` VALUES ('3', 'code 2', 'ASIAM', '123', '123', '0', '0', '5', '2019-10-01 12:43:05', null, null);
INSERT INTO `tbl_chlorinator` VALUES ('4', 'code 3', 'TEST 1', '123', '123', '0', '0', '5', '2019-10-01 12:43:35', null, null);
INSERT INTO `tbl_chlorinator` VALUES ('6', 'code 4', 'TEST 2', '7', '5', '0', '0', '1', '2019-10-02 15:33:33', null, null);
INSERT INTO `tbl_chlorinator` VALUES ('7', 'code 5', 'ACEJET', '0', '789', '0', '0', '1', '2019-10-02 13:08:50', null, null);
INSERT INTO `tbl_chlorinator` VALUES ('8', 'code 6', 'Brand Test', '456', '666', '0', '0', '5', '2019-10-01 12:44:24', null, null);
INSERT INTO `tbl_chlorinator` VALUES ('9', 'code 7', 'ACEJET', '666', '123', '0', '0', '1', '2019-10-07 10:56:40', null, null);
INSERT INTO `tbl_chlorinator` VALUES ('11', 'code 8', 'Brand 1', '0', '0', '0', '0', '5', '2019-10-01 12:43:59', null, null);
INSERT INTO `tbl_chlorinator` VALUES ('12', 'code 9', 'Brand 2', '123', '123', '0', '0', '5', '2019-10-01 12:44:11', null, null);
INSERT INTO `tbl_chlorinator` VALUES ('13', 'code 10', 'ASIAM Brand', '123', '123', '0', '0', '1', '2019-10-04 07:52:07', '13', '2020-07-17 00:21:31');
INSERT INTO `tbl_chlorinator` VALUES ('14', 'code 11', 'ASIAM', '2005', '5002', '0', '0', '1', '2019-10-03 13:25:17', null, null);
INSERT INTO `tbl_chlorinator` VALUES ('15', 'code 12', 'ACEJET BRAND', '3002', '36', '0', '0', '1', '2019-10-07 12:23:26', null, null);
INSERT INTO `tbl_chlorinator` VALUES ('16', 'code 13', 'ASIAM TESt', '5001', '5001', '0', '0', '1', '2019-10-07 12:31:22', null, null);
INSERT INTO `tbl_chlorinator` VALUES ('17', 'code 14', 'ACEJET TEST', '999', '6605', '0', '0', '1', '2019-10-07 12:47:15', null, null);
INSERT INTO `tbl_chlorinator` VALUES ('18', 'code 15', '123', '123', '123', '0', '0', '12', '2020-05-22 09:22:33', null, null);
INSERT INTO `tbl_chlorinator` VALUES ('19', 'code 16', 'asdf', '123', '123', '0', '0', '12', '2020-05-22 09:22:44', '13', '2020-07-17 00:21:35');
