/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : p-operations

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2020-09-24 13:16:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_bulk_water
-- ----------------------------
DROP TABLE IF EXISTS `tbl_bulk_water`;
CREATE TABLE `tbl_bulk_water` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pur_date` date DEFAULT NULL,
  `supplier` varchar(255) DEFAULT NULL,
  `bulk_pres` int(11) DEFAULT NULL,
  `bulk_prev` int(11) DEFAULT NULL,
  `bulk_total_cum` int(11) DEFAULT NULL,
  `bulk_amt_cum` int(11) DEFAULT NULL,
  `bulk_total_cost` int(11) DEFAULT NULL,
  `action` tinyint(1) DEFAULT NULL,
  `added_by` int(11) DEFAULT NULL,
  `date_added` datetime DEFAULT NULL,
  `modified_by` int(11) DEFAULT NULL,
  `date_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tbl_bulk_water
-- ----------------------------
INSERT INTO `tbl_bulk_water` VALUES ('1', '2020-09-09', '42', '1050', '500', '550', '12', '6600', null, '13', '2020-09-23 05:20:31', '13', '2020-09-24 03:25:27');
INSERT INTO `tbl_bulk_water` VALUES ('2', '2020-09-24', '43', '1500', '600', '900', '15', '13500', null, '13', '2020-09-24 02:15:26', null, null);
INSERT INTO `tbl_bulk_water` VALUES ('3', '2020-09-25', '42', '2000', '650', '1350', '15', '20250', null, '13', '2020-09-24 02:18:20', null, null);
INSERT INTO `tbl_bulk_water` VALUES ('4', '2020-09-26', '15', '3500', '1500', '2000', '20', '40000', null, '13', '2020-09-24 02:19:57', '13', '2020-09-24 03:25:16');
