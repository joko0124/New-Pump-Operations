/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50505
Source Host           : 127.0.0.1:3306
Source Database       : pump-operations

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2020-08-16 23:32:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for android_users
-- ----------------------------
DROP TABLE IF EXISTS `android_users`;
CREATE TABLE `android_users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `userpassword` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `added_by` int(11) DEFAULT NULL COMMENT 'user',
  `date_added` datetime DEFAULT NULL,
  `modified_by` int(11) DEFAULT NULL,
  `date_modified` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of android_users
-- ----------------------------
INSERT INTO `android_users` VALUES ('1', 'admin', 'admin', '1', '2019-10-01 14:02:24', null, null);
INSERT INTO `android_users` VALUES ('2', 'joko', '0124', '1', '2019-10-01 13:26:58', null, null);

-- ----------------------------
-- Table structure for branches
-- ----------------------------
DROP TABLE IF EXISTS `branches`;
CREATE TABLE `branches` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) DEFAULT NULL,
  `company_id` int(11) NOT NULL,
  `description` varchar(125) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `province_id` int(11) DEFAULT NULL,
  `city_id` int(11) DEFAULT NULL,
  `contact_no` varchar(100) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `tin` varchar(20) DEFAULT NULL,
  `nfee` decimal(9,2) DEFAULT NULL,
  `system_mode` enum('dev','parallel','prod') DEFAULT 'parallel',
  `created_by` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `company_id` (`company_id`) USING BTREE,
  KEY `code` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of branches
-- ----------------------------
INSERT INTO `branches` VALUES ('1', '', '1', 'BWSI - Garden Villas Branch', null, null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('2', '1009', '1', 'BWSI - Laoac Branch', 'Municipal Compund, Poblacion, Laoac, Pangasinan', '60', '1257', 'CEL#: 0947-2742-600', null, '000-743-345-008', null, 'parallel', '1');
INSERT INTO `branches` VALUES ('3', '1010', '1', 'BWSI - Laurel Branch', 'Public Market, Poblacion 2, Laurel, Batangas 4221', null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('4', '1003', '1', 'BWSI - Llanera Branch', 'Corpuz St.,Cor. Lagasca Ave., Victoria Llanera, N.E', null, null, 'CEL#: 0929 363 4993', null, '000-743-345-006', null, 'parallel', '1');
INSERT INTO `branches` VALUES ('5', '', '1', 'BWSI - Main/Central Branch', null, null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('6', '1013', '1', 'BWSI - Mapalacsiao Branch', 'Sitio Silangan, Mapalacsiao, Tarlac City', '75', '1523', 'CEL# 0948-436-4104', null, '000-743-345-016', null, 'parallel', '1');
INSERT INTO `branches` VALUES ('7', '', '1', 'BWSI - San Jacinto Branch', null, '60', '1269', null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('8', '1007', '1', 'BWSI - Sta. Barbara Branch', 'ORC Bldg, Poblacion Sur,Sta Barbara,Pangasinan', '60', '1273', 'CEL# 0932-403-1933, TEL# 653-6005', null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('9', '1004', '1', 'BWSI - Sto. Domingo Branch', 'Malasin, Sto. Domingo, Nueva Ecija', '54', '1143', 'CEL#: 0998-471-6495', '', '000-743-345-004', null, 'parallel', '1');
INSERT INTO `branches` VALUES ('10', '1011', '1', 'BWSI - Talisay Branch', 'Unit 202 Balai Sofia, Com. Arcade Banga, Talisay, Batangas', null, null, 'CEL#: 0921-785-7588', null, '000-743-345-013', null, 'parallel', '1');
INSERT INTO `branches` VALUES ('11', '4004', '1', 'BWSI - Victoria Tarlac Branch', 'San Fernando, Victoria, Tarlac', '75', '1540', 'CEL#: 0929-354-8205', null, '000-743-345-015', null, 'parallel', '1');
INSERT INTO `branches` VALUES ('12', '2005', '1', 'BWSI - Zaragoza Branch', 'Prk. 2 Brgy. San Isidro, Zaragoza, Nueva Ecija', '54', '1146', 'CEL#: 0921-616-0492', '', '000-743-345-009', null, 'parallel', '1');
INSERT INTO `branches` VALUES ('13', '', '1', 'BWSI - Canyon Woods Branch', null, null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('14', '3004', '2', 'Apalit Branch', 'Mc Arthur Hiway, Sampaloc, Apalit, Pampanga', null, null, 'CEL# 0942-780-2830 / 0949-410-4168', null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('15', '3001', '2', 'CLPI - Bamban', 'Anupul, Bamban, Tarlac 2317', '75', '1525', 'CEL# 0933-522-3246', null, '205-497-971-001', null, 'parallel', '1');
INSERT INTO `branches` VALUES ('16', '', '2', 'CLPI - Caba Branch', null, null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('17', '', '2', 'CLPI - Meycauayan Branch', null, null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('18', '', '2', 'CLPI - Minalin Branch', null, null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('19', '', '2', 'CLPI - Porac Branch', null, null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('20', '', '2', 'CLPI - Pugo Branch', null, null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('21', '', '2', 'CLPI - San Simon Branch', null, null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('22', '', '2', 'CLPI - Sta. Maria Branch', null, null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('23', '', '2', 'CLPI - Sta. Rita Branch', null, null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('24', '', '2', 'CLPI - Sto. Tomas Branch', null, null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('25', '', '3', 'HWRI - Balagtas Branch', null, null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('26', '', '3', 'HWRI - Bulakan Branch', null, null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('27', '', '3', 'HWRI - Bustos Branch', null, null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('28', '4003', '3', 'Malis Branch', 'North Bel-Air, Malis, Guiguinto, Bulacan', null, null, 'CEL# 0932-370-4412', null, '223-772-603-002', null, 'parallel', '1');
INSERT INTO `branches` VALUES ('29', '4001', '3', 'Agatha Branch', 'St. Agatha Subd., Sta. Rita, Guiguinto, Bulacan', null, null, 'TEL# 794-4927 & CEL# 0917-8944927', null, '223-772-603-000', null, 'parallel', '1');
INSERT INTO `branches` VALUES ('30', '4002', '3', 'Tuktukan Branch', '2nd Floor Cundangan Bldg, Tuktukan, Guiguinto, Bulacan', null, null, 'TEL# 794-4300', null, '223-772-603-000', null, 'parallel', '1');
INSERT INTO `branches` VALUES ('31', '', '3', 'HWRI - Pandi I Branch', null, null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('32', '', '3', 'HWRI - Pandi II Branch', null, null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('33', '', '3', 'HWRI - Pandi III Branch', null, null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('34', '', '3', 'HWRI - Pandi IV Branch', null, null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('35', '', '3', 'HWRI - Pandi V Branch', null, null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('36', '4008', '3', 'Paombong Branch', '467 A Sto Nino, Paombong, Bulacan', null, null, 'CEL# 0915-1804927', null, '223-772-603-007', null, 'parallel', '1');
INSERT INTO `branches` VALUES ('37', '', '4', 'BCBI - Alfonso Castañeda Branch', null, null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('38', '', '4', 'BCBI - Arayat Branch', null, null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('39', '', '4', 'BCBI - Aritao Branch', null, null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('40', '', '4', 'BCBI - Bagabag Branch', null, null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('41', '', '4', 'BCBI - Bayombong Branch', null, null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('42', '', '4', 'BCBI - Capas Branch', null, null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('43', '2002', '4', 'BCBI - Dapdap Branch', 'DAPDAP REST. CENTER, BAMBAN, TARLAC', '75', '1525', 'CP#: 0922-660-7814', null, '006-588-335-002', null, 'parallel', '1');
INSERT INTO `branches` VALUES ('44', '2010', '4', 'BCBI - Magalang Branch', 'San Nicolas II, Magalang, Pampanga', null, null, 'CEL# 0943-447-4332', null, '006-588-335-009', null, 'parallel', '1');
INSERT INTO `branches` VALUES ('45', '', '4', 'BCBI - Maria Aurora Branch', null, null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('46', '', '4', 'BCBI - San Luis Branch', null, null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('47', '', '4', 'BCBI - Sta. Ana Branch', null, null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('48', '2008', '4', 'BCBI - Telabastagan Branch', 'Essel Park Subd., Telabastagan, CSFP', null, null, 'TEL# (045) 887-3753', null, '006-588-335-005', null, 'parallel', '1');
INSERT INTO `branches` VALUES ('49', '', '5', 'BIRI - Tuba Branch', null, null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('50', '', '6', 'LBWI - Victoria Branch', null, null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('51', '', '7', 'SBRI - Bingawan Branch', null, null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('52', '', '7', 'SBRI - Bohol Branch', null, null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('53', '8001', '7', 'SBRI - Passi Branch', 'Comm.wealth Drive St. Pob. Ilawod, Passi City', null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('54', '6003', '7', 'SBRI - SmartTec Branch', 'Brgy. Bongco,  Pototan, Iloilo', null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('55', '', '8', 'MR3H - Bamban Branch', null, null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('56', '', '8', 'MR3H - Sto. Domingo Branch', null, null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('57', '', '3', 'HWRI - Malolos Heights Branch', null, null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('58', '4007', '3', 'San Francisco Branch', 'Purok 4, San Francisco, Bulakan, Bulacan', null, null, 'CEL# 0932-614-7605', null, '223-772-603-005', null, 'prod', '1');
INSERT INTO `branches` VALUES ('59', '', '3', 'Pandi - Salintubig', null, '17', '295', null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('60', '2025', '4', 'BCBI - Carranglan Branch', 'Brgy. TL Padilla, Carranglan, Nueva Ecija', null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('61', '1014', '1', 'BWSI - Cuyapo Branch', 'Brgy. Bulala, Cuyapo, Nueva Ecija', null, null, '0936-2728182 / 0950-3795939', null, '000-743-345-017', null, 'parallel', '1');
INSERT INTO `branches` VALUES ('62', '1055', '2', 'Balucuc Branch', 'Sitio Centro, Apalit, Pampanga', null, null, '0943-0967759 / 0966-3177531', null, '205-497-971-003', null, 'prod', '1');
INSERT INTO `branches` VALUES ('63', '', '7', 'SBRI - Buug Branch', 'Purok 4, Brgy. Datu Panas, Buug, Zamboanga Sibugay', null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('64', '3008', '2', 'CLPI - Minalin Branch', 'San Nicolas, Minalin, Pampanga', null, null, null, null, '205-497-971-005', null, 'parallel', '1');
INSERT INTO `branches` VALUES ('65', '6003', '7', 'SBRI - Jaro Branch', 'Brgy. Tagbac, Jaro, Iloilo City', null, null, null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('66', null, '7', 'SBRI - Mati Branch', 'Bonifacio Street, Brgy. Central, Mati City, Davao Oriental', '30', '546', 'CEL# 0929-349-9606', null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('67', '4016', '3', 'HWRI - Villa Louise', null, '17', '295', null, null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('68', '4017', '3', 'HWRI - San Miguel', 'Scout Rangers Ville, Brgy. Calumpang/Tartaro', '17', '300', 'CEL# 0917-169-9416', null, null, null, 'parallel', '1');
INSERT INTO `branches` VALUES ('69', '1020', '1', 'BWSI - Dasmariñas', 'Dasmariñas, Cavite', '24', '415', null, null, null, null, 'parallel', '0');
INSERT INTO `branches` VALUES ('70', '1021', '1', 'BWSI - General Trias', 'General Trias, Cavite', '24', '424', null, null, null, null, 'parallel', '0');

-- ----------------------------
-- Table structure for branch_details
-- ----------------------------
DROP TABLE IF EXISTS `branch_details`;
CREATE TABLE `branch_details` (
  `company_id` int(11) NOT NULL,
  `branch_id` int(11) NOT NULL,
  `reading_period` varchar(25) DEFAULT NULL,
  `reading_from` date DEFAULT NULL,
  `reading_to` date DEFAULT NULL,
  `nrw` decimal(11,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of branch_details
-- ----------------------------
INSERT INTO `branch_details` VALUES ('2', '15', null, null, null, null);

-- ----------------------------
-- Table structure for companies
-- ----------------------------
DROP TABLE IF EXISTS `companies`;
CREATE TABLE `companies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(25) NOT NULL,
  `description` varchar(125) NOT NULL,
  `created_by` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of companies
-- ----------------------------
INSERT INTO `companies` VALUES ('1', 'BWSI', 'BALIBAGO WATERWORKS SYSTEM, INC.', '1');
INSERT INTO `companies` VALUES ('2', 'CLPI', 'CRYSTAL LIQUID PHILIPPINES, INC.', '1');
INSERT INTO `companies` VALUES ('3', 'HWRI', 'HIYAS WATER RESOURCES, INC.', '1');
INSERT INTO `companies` VALUES ('4', 'BCBI', 'BWSI-CLPI BRANCHES, INC.', '1');
INSERT INTO `companies` VALUES ('5', 'BIRI', 'BALI IRISAN RESOURCES, INC.', '1');
INSERT INTO `companies` VALUES ('6', 'LBWI', 'LAGUNA BAY WATER, INC.', '1');
INSERT INTO `companies` VALUES ('7', 'SBRI', 'SOUTH BALIBAGO RESOURCES, INC.', '1');
INSERT INTO `companies` VALUES ('8', 'MR3H', 'MAKATI ROTARY', '1');

-- ----------------------------
-- Table structure for constant__authorized_cons
-- ----------------------------
DROP TABLE IF EXISTS `constant__authorized_cons`;
CREATE TABLE `constant__authorized_cons` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of constant__authorized_cons
-- ----------------------------
INSERT INTO `constant__authorized_cons` VALUES ('1', 'Billed Consumption');
INSERT INTO `constant__authorized_cons` VALUES ('2', 'Unbilled Concumption');

-- ----------------------------
-- Table structure for constant__authorized_cons_class
-- ----------------------------
DROP TABLE IF EXISTS `constant__authorized_cons_class`;
CREATE TABLE `constant__authorized_cons_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `authorized_cons_id` int(11) DEFAULT NULL,
  `desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of constant__authorized_cons_class
-- ----------------------------
INSERT INTO `constant__authorized_cons_class` VALUES ('1', '1', 'Metered Consumption');
INSERT INTO `constant__authorized_cons_class` VALUES ('2', '1', 'Unmetered Concumption');
INSERT INTO `constant__authorized_cons_class` VALUES ('3', '2', 'Metered Consumption');
INSERT INTO `constant__authorized_cons_class` VALUES ('4', '2', 'Unmetered Concumption');

-- ----------------------------
-- Table structure for constant__authorized_cons_type
-- ----------------------------
DROP TABLE IF EXISTS `constant__authorized_cons_type`;
CREATE TABLE `constant__authorized_cons_type` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) DEFAULT NULL,
  `desc` varchar(255) DEFAULT NULL,
  `is_mainline` tinyint(1) DEFAULT 0,
  `is_service_connection` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of constant__authorized_cons_type
-- ----------------------------
INSERT INTO `constant__authorized_cons_type` VALUES ('1', '1', 'Residential Consumption', '0', '1');
INSERT INTO `constant__authorized_cons_type` VALUES ('2', '1', 'Small Commercial Consumption', '0', '1');
INSERT INTO `constant__authorized_cons_type` VALUES ('3', '1', 'Medium Commercial Consumption', '0', '1');
INSERT INTO `constant__authorized_cons_type` VALUES ('4', '1', 'Large Commercial Consumption', '0', '1');
INSERT INTO `constant__authorized_cons_type` VALUES ('5', '1', 'Industrial Consumption', '0', '1');
INSERT INTO `constant__authorized_cons_type` VALUES ('6', '1', 'Religious Group Consumption', '0', '1');
INSERT INTO `constant__authorized_cons_type` VALUES ('7', '2', 'Bulk Water', '1', '0');
INSERT INTO `constant__authorized_cons_type` VALUES ('8', '2', 'Lorry Water', '1', '0');
INSERT INTO `constant__authorized_cons_type` VALUES ('9', '2', 'Contractor\'s Usage', '0', '0');
INSERT INTO `constant__authorized_cons_type` VALUES ('10', '3', 'Office Usage', '0', '0');
INSERT INTO `constant__authorized_cons_type` VALUES ('11', '3', 'Pump House Usage', '0', '0');
INSERT INTO `constant__authorized_cons_type` VALUES ('12', '3', 'Customer\'s Discount', '0', '0');
INSERT INTO `constant__authorized_cons_type` VALUES ('13', '4', 'Tapping', '0', '1');
INSERT INTO `constant__authorized_cons_type` VALUES ('14', '4', 'Plug Connection', '0', '1');
INSERT INTO `constant__authorized_cons_type` VALUES ('15', '4', 'Relocate Meter', '0', '1');
INSERT INTO `constant__authorized_cons_type` VALUES ('16', '4', 'Mainline', '1', '0');
INSERT INTO `constant__authorized_cons_type` VALUES ('17', '4', 'Meter Stand', '1', '0');
INSERT INTO `constant__authorized_cons_type` VALUES ('18', '4', 'Pump Draining', '1', '0');
INSERT INTO `constant__authorized_cons_type` VALUES ('19', '4', 'Backwash', '1', '0');
INSERT INTO `constant__authorized_cons_type` VALUES ('20', '4', 'Fire Usage', '0', '0');

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
  `date_modified` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp(),
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

-- ----------------------------
-- Table structure for geo_brgys
-- ----------------------------
DROP TABLE IF EXISTS `geo_brgys`;
CREATE TABLE `geo_brgys` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city_id` int(11) DEFAULT NULL,
  `name` varchar(125) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `id` (`id`) USING BTREE,
  KEY `city_id` (`city_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=915 DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of geo_brgys
-- ----------------------------
INSERT INTO `geo_brgys` VALUES ('1', '1212', 'Agapito del Rosario');
INSERT INTO `geo_brgys` VALUES ('2', '1212', 'Amsic');
INSERT INTO `geo_brgys` VALUES ('3', '1212', 'Anunas');
INSERT INTO `geo_brgys` VALUES ('4', '1212', 'Balibago');
INSERT INTO `geo_brgys` VALUES ('5', '1212', 'Capaya');
INSERT INTO `geo_brgys` VALUES ('6', '1212', 'Claro M. Recto');
INSERT INTO `geo_brgys` VALUES ('7', '1212', 'Cuayan');
INSERT INTO `geo_brgys` VALUES ('8', '1212', 'Cutcut');
INSERT INTO `geo_brgys` VALUES ('9', '1212', 'Cutud');
INSERT INTO `geo_brgys` VALUES ('10', '1212', 'Mining');
INSERT INTO `geo_brgys` VALUES ('11', '199', 'Ansagan');
INSERT INTO `geo_brgys` VALUES ('12', '199', 'Camp One');
INSERT INTO `geo_brgys` VALUES ('13', '199', 'Camp 3');
INSERT INTO `geo_brgys` VALUES ('14', '199', 'Camp 4');
INSERT INTO `geo_brgys` VALUES ('15', '199', 'Nangalisan');
INSERT INTO `geo_brgys` VALUES ('16', '199', 'Poblacion');
INSERT INTO `geo_brgys` VALUES ('17', '199', 'San Pascual');
INSERT INTO `geo_brgys` VALUES ('18', '199', 'Tabaan Norte');
INSERT INTO `geo_brgys` VALUES ('19', '199', 'Tabaan Sur');
INSERT INTO `geo_brgys` VALUES ('20', '199', 'Tadiangan');
INSERT INTO `geo_brgys` VALUES ('21', '199', 'Taloy Norte');
INSERT INTO `geo_brgys` VALUES ('22', '199', 'Taloy Sur');
INSERT INTO `geo_brgys` VALUES ('23', '199', 'Twin Peaks');
INSERT INTO `geo_brgys` VALUES ('24', '187', 'A. Bonifacio-Caguioa-Rimando');
INSERT INTO `geo_brgys` VALUES ('25', '187', 'Abanao-Zandueta-Kayong-Chugum-Otek');
INSERT INTO `geo_brgys` VALUES ('26', '187', 'Alfonso Tabora');
INSERT INTO `geo_brgys` VALUES ('27', '187', 'Ambiong');
INSERT INTO `geo_brgys` VALUES ('28', '187', 'Andres Bonifacio');
INSERT INTO `geo_brgys` VALUES ('29', '187', 'Apugan-Loakan');
INSERT INTO `geo_brgys` VALUES ('30', '187', 'Asin Road');
INSERT INTO `geo_brgys` VALUES ('31', '187', 'Atok Trail');
INSERT INTO `geo_brgys` VALUES ('32', '187', 'Aurora Hill Proper');
INSERT INTO `geo_brgys` VALUES ('33', '187', 'Aurora Hill, North Central');
INSERT INTO `geo_brgys` VALUES ('34', '187', 'Aurora Hill, South Central');
INSERT INTO `geo_brgys` VALUES ('35', '187', 'Bagong Lipunan');
INSERT INTO `geo_brgys` VALUES ('36', '187', 'Bakakeng Central');
INSERT INTO `geo_brgys` VALUES ('37', '187', 'Bakakeng North');
INSERT INTO `geo_brgys` VALUES ('38', '187', 'Bal-Marcoville');
INSERT INTO `geo_brgys` VALUES ('39', '187', 'Balsigan');
INSERT INTO `geo_brgys` VALUES ('40', '187', 'Bayan Park East');
INSERT INTO `geo_brgys` VALUES ('41', '187', 'Bayan Park Village');
INSERT INTO `geo_brgys` VALUES ('42', '187', 'Bayan Park West');
INSERT INTO `geo_brgys` VALUES ('43', '187', 'BGH Compound');
INSERT INTO `geo_brgys` VALUES ('44', '187', 'Brookside');
INSERT INTO `geo_brgys` VALUES ('45', '187', 'Brookspoint');
INSERT INTO `geo_brgys` VALUES ('46', '187', 'Cabinet Hill-Teacher\'s Camp');
INSERT INTO `geo_brgys` VALUES ('47', '187', 'Camdas Subdivision');
INSERT INTO `geo_brgys` VALUES ('48', '187', 'Camp 7');
INSERT INTO `geo_brgys` VALUES ('49', '187', 'Camp 8');
INSERT INTO `geo_brgys` VALUES ('50', '187', 'Camp Allen');
INSERT INTO `geo_brgys` VALUES ('51', '187', 'Campo Filipino');
INSERT INTO `geo_brgys` VALUES ('52', '187', 'City Camp Central');
INSERT INTO `geo_brgys` VALUES ('53', '187', 'City Camp Proper');
INSERT INTO `geo_brgys` VALUES ('54', '187', 'Country Club Village');
INSERT INTO `geo_brgys` VALUES ('55', '187', 'Cresencia Village');
INSERT INTO `geo_brgys` VALUES ('56', '187', 'Dagsian, Lower');
INSERT INTO `geo_brgys` VALUES ('57', '187', 'Dagsian, Upper');
INSERT INTO `geo_brgys` VALUES ('58', '187', 'Dizon Subdivision');
INSERT INTO `geo_brgys` VALUES ('59', '187', 'Dominican Hill-Mirador');
INSERT INTO `geo_brgys` VALUES ('60', '187', 'Dontogan');
INSERT INTO `geo_brgys` VALUES ('61', '187', 'DPS Compound');
INSERT INTO `geo_brgys` VALUES ('62', '187', 'Engineer\'s Hill');
INSERT INTO `geo_brgys` VALUES ('63', '187', 'Fairview Village');
INSERT INTO `geo_brgys` VALUES ('64', '187', 'Ferdinand');
INSERT INTO `geo_brgys` VALUES ('65', '187', 'Fort del Pilar');
INSERT INTO `geo_brgys` VALUES ('66', '187', 'Gabriela Silang');
INSERT INTO `geo_brgys` VALUES ('67', '187', 'General Emilio F. Aguinaldo');
INSERT INTO `geo_brgys` VALUES ('68', '187', 'General Luna, Upper');
INSERT INTO `geo_brgys` VALUES ('69', '187', 'General Luna, Lower');
INSERT INTO `geo_brgys` VALUES ('70', '187', 'Gibraltar');
INSERT INTO `geo_brgys` VALUES ('71', '187', 'Greenwater Village');
INSERT INTO `geo_brgys` VALUES ('72', '187', 'Guisad Central');
INSERT INTO `geo_brgys` VALUES ('73', '187', 'Guisad Sorong');
INSERT INTO `geo_brgys` VALUES ('74', '187', 'Happy Hollow');
INSERT INTO `geo_brgys` VALUES ('75', '187', 'Happy Homes');
INSERT INTO `geo_brgys` VALUES ('76', '187', 'Harrison-Claudio Carantes');
INSERT INTO `geo_brgys` VALUES ('77', '187', 'Hillside');
INSERT INTO `geo_brgys` VALUES ('78', '187', 'Holy Ghost Extension');
INSERT INTO `geo_brgys` VALUES ('79', '187', 'Holy Ghost Proper');
INSERT INTO `geo_brgys` VALUES ('80', '187', 'Honeymoon');
INSERT INTO `geo_brgys` VALUES ('81', '187', 'Imelda R. Marcos');
INSERT INTO `geo_brgys` VALUES ('82', '187', 'Imelda Village');
INSERT INTO `geo_brgys` VALUES ('83', '187', 'Irisan');
INSERT INTO `geo_brgys` VALUES ('84', '187', 'Kabayanihan');
INSERT INTO `geo_brgys` VALUES ('85', '187', 'Kagitingan');
INSERT INTO `geo_brgys` VALUES ('86', '187', 'Kayang Extension');
INSERT INTO `geo_brgys` VALUES ('87', '187', 'Kayang-Hilltop');
INSERT INTO `geo_brgys` VALUES ('88', '187', 'Kias');
INSERT INTO `geo_brgys` VALUES ('89', '187', 'Legarda-Burnham-Kisad');
INSERT INTO `geo_brgys` VALUES ('90', '187', 'Liwanag-Loakan');
INSERT INTO `geo_brgys` VALUES ('91', '187', 'Loakan Proper');
INSERT INTO `geo_brgys` VALUES ('92', '187', 'Lopez Jaena');
INSERT INTO `geo_brgys` VALUES ('93', '187', 'Lourdes Subdivision Extension');
INSERT INTO `geo_brgys` VALUES ('94', '187', 'Lourdes Subdivision, Lower ');
INSERT INTO `geo_brgys` VALUES ('95', '187', 'Lourdes Subdivision, Proper');
INSERT INTO `geo_brgys` VALUES ('96', '187', 'Lualhati');
INSERT INTO `geo_brgys` VALUES ('97', '187', 'Lucnab');
INSERT INTO `geo_brgys` VALUES ('98', '187', 'Magsaysay Private Road');
INSERT INTO `geo_brgys` VALUES ('99', '187', 'Magsaysay, Lower');
INSERT INTO `geo_brgys` VALUES ('100', '187', 'Magsaysay, Upper');
INSERT INTO `geo_brgys` VALUES ('101', '187', 'Malcolm Square-Perfecto');
INSERT INTO `geo_brgys` VALUES ('102', '187', 'Manuel A. Roxas');
INSERT INTO `geo_brgys` VALUES ('103', '187', 'Market Subdivision, Upper');
INSERT INTO `geo_brgys` VALUES ('104', '187', 'Middle Quezon Hill Subdivision');
INSERT INTO `geo_brgys` VALUES ('105', '187', 'Military Cut-off');
INSERT INTO `geo_brgys` VALUES ('106', '187', 'Mines View Park');
INSERT INTO `geo_brgys` VALUES ('107', '187', 'Modern Site, East');
INSERT INTO `geo_brgys` VALUES ('108', '187', 'Modern Site, West');
INSERT INTO `geo_brgys` VALUES ('109', '187', 'MRR-Queen of Peace');
INSERT INTO `geo_brgys` VALUES ('110', '187', 'New Lucban');
INSERT INTO `geo_brgys` VALUES ('111', '187', 'Outlook Drive');
INSERT INTO `geo_brgys` VALUES ('112', '187', 'Pacdal');
INSERT INTO `geo_brgys` VALUES ('113', '187', 'Padre Burgos');
INSERT INTO `geo_brgys` VALUES ('114', '187', 'Padre Zamora');
INSERT INTO `geo_brgys` VALUES ('115', '187', 'Palma-Urbano');
INSERT INTO `geo_brgys` VALUES ('116', '187', 'Phil-Am');
INSERT INTO `geo_brgys` VALUES ('117', '187', 'Pinget');
INSERT INTO `geo_brgys` VALUES ('118', '187', 'Pinsao Pilot Project');
INSERT INTO `geo_brgys` VALUES ('119', '187', 'Pinsao Proper');
INSERT INTO `geo_brgys` VALUES ('120', '187', 'Poliwes');
INSERT INTO `geo_brgys` VALUES ('121', '187', 'Pucsusan');
INSERT INTO `geo_brgys` VALUES ('122', '187', 'Quezon Hill Proper');
INSERT INTO `geo_brgys` VALUES ('123', '187', 'Quezon Hill, Upper');
INSERT INTO `geo_brgys` VALUES ('124', '187', 'Quirino Hill, East');
INSERT INTO `geo_brgys` VALUES ('125', '187', 'Quirino Hill, Lower');
INSERT INTO `geo_brgys` VALUES ('126', '187', 'Quirino Hill, Middle');
INSERT INTO `geo_brgys` VALUES ('127', '187', 'Quirino Hill, West');
INSERT INTO `geo_brgys` VALUES ('128', '187', 'Quirino-Magsaysay, Upper');
INSERT INTO `geo_brgys` VALUES ('129', '187', 'Rizal Monument Area');
INSERT INTO `geo_brgys` VALUES ('130', '187', 'Rock Quarry, Lower');
INSERT INTO `geo_brgys` VALUES ('131', '187', 'Rock Quarry, Middle');
INSERT INTO `geo_brgys` VALUES ('132', '187', 'Rock Quarry, Upper');
INSERT INTO `geo_brgys` VALUES ('133', '187', 'Saint Joseph Village');
INSERT INTO `geo_brgys` VALUES ('134', '187', 'Salud Mitra');
INSERT INTO `geo_brgys` VALUES ('135', '187', 'San Antonio Village');
INSERT INTO `geo_brgys` VALUES ('136', '187', 'San Luis Village');
INSERT INTO `geo_brgys` VALUES ('137', '187', 'San Roque Villa');
INSERT INTO `geo_brgys` VALUES ('138', '187', 'San Vicente');
INSERT INTO `geo_brgys` VALUES ('139', '187', 'Sanitary Camp, North');
INSERT INTO `geo_brgys` VALUES ('140', '187', 'Sanitary Camp, South');
INSERT INTO `geo_brgys` VALUES ('141', '187', 'Santa Escolastica');
INSERT INTO `geo_brgys` VALUES ('142', '187', 'Santo Rosario');
INSERT INTO `geo_brgys` VALUES ('143', '187', 'Santo Tomas Proper');
INSERT INTO `geo_brgys` VALUES ('144', '187', 'Santo Tomas School Area');
INSERT INTO `geo_brgys` VALUES ('145', '187', 'Scout Barrio');
INSERT INTO `geo_brgys` VALUES ('146', '187', 'Session Road Area');
INSERT INTO `geo_brgys` VALUES ('147', '187', 'Slaughter House Area');
INSERT INTO `geo_brgys` VALUES ('148', '187', 'SLU-SVP Housing Village');
INSERT INTO `geo_brgys` VALUES ('149', '187', 'South Drive');
INSERT INTO `geo_brgys` VALUES ('150', '187', 'Teodora Alonzo');
INSERT INTO `geo_brgys` VALUES ('151', '187', 'Trancoville');
INSERT INTO `geo_brgys` VALUES ('152', '187', 'Victoria Village');
INSERT INTO `geo_brgys` VALUES ('153', '296', 'Binakod (sitio matalaba)');
INSERT INTO `geo_brgys` VALUES ('154', '296', 'Kapitangan ');
INSERT INTO `geo_brgys` VALUES ('155', '296', 'Malumot');
INSERT INTO `geo_brgys` VALUES ('156', '296', 'Masukol');
INSERT INTO `geo_brgys` VALUES ('157', '296', 'Pinalagdan');
INSERT INTO `geo_brgys` VALUES ('158', '296', 'Poblacion');
INSERT INTO `geo_brgys` VALUES ('159', '296', 'San Isidro I');
INSERT INTO `geo_brgys` VALUES ('160', '296', 'San Isidro II');
INSERT INTO `geo_brgys` VALUES ('161', '296', 'San Jose');
INSERT INTO `geo_brgys` VALUES ('162', '296', 'San Roque');
INSERT INTO `geo_brgys` VALUES ('163', '296', 'San Vicente');
INSERT INTO `geo_brgys` VALUES ('164', '296', 'Sta. Cruz');
INSERT INTO `geo_brgys` VALUES ('165', '296', 'Sto. Niño');
INSERT INTO `geo_brgys` VALUES ('166', '296', 'Sto. Rosario');
INSERT INTO `geo_brgys` VALUES ('167', '290', 'Cutcut');
INSERT INTO `geo_brgys` VALUES ('168', '290', 'Daungan');
INSERT INTO `geo_brgys` VALUES ('169', '290', 'Ilang-Ilang');
INSERT INTO `geo_brgys` VALUES ('170', '290', 'Malis');
INSERT INTO `geo_brgys` VALUES ('171', '290', 'Panginay');
INSERT INTO `geo_brgys` VALUES ('172', '290', 'Poblacion');
INSERT INTO `geo_brgys` VALUES ('173', '290', 'Pritil');
INSERT INTO `geo_brgys` VALUES ('174', '290', 'Pulong Gubat');
INSERT INTO `geo_brgys` VALUES ('175', '290', 'Santa Cruz');
INSERT INTO `geo_brgys` VALUES ('176', '290', 'Santa Rita');
INSERT INTO `geo_brgys` VALUES ('177', '290', 'Tabang');
INSERT INTO `geo_brgys` VALUES ('178', '290', 'Tabe');
INSERT INTO `geo_brgys` VALUES ('179', '290', 'Tiaong');
INSERT INTO `geo_brgys` VALUES ('180', '290', 'Tuktukan');
INSERT INTO `geo_brgys` VALUES ('181', '279', 'Anilao');
INSERT INTO `geo_brgys` VALUES ('182', '279', 'Atlag');
INSERT INTO `geo_brgys` VALUES ('183', '279', 'Babatnin');
INSERT INTO `geo_brgys` VALUES ('184', '279', 'Bagna');
INSERT INTO `geo_brgys` VALUES ('185', '279', 'Bagong Bayan');
INSERT INTO `geo_brgys` VALUES ('186', '279', 'Balayong');
INSERT INTO `geo_brgys` VALUES ('187', '279', 'Balite');
INSERT INTO `geo_brgys` VALUES ('188', '279', 'Bangkal');
INSERT INTO `geo_brgys` VALUES ('189', '279', 'Barihan');
INSERT INTO `geo_brgys` VALUES ('190', '279', 'Bulihan');
INSERT INTO `geo_brgys` VALUES ('191', '279', 'Bungahan');
INSERT INTO `geo_brgys` VALUES ('192', '279', 'Caingin');
INSERT INTO `geo_brgys` VALUES ('193', '279', 'Calero');
INSERT INTO `geo_brgys` VALUES ('194', '279', 'Caliligawan');
INSERT INTO `geo_brgys` VALUES ('195', '279', 'Canalete');
INSERT INTO `geo_brgys` VALUES ('196', '279', 'Caniogan');
INSERT INTO `geo_brgys` VALUES ('197', '279', 'Catmon');
INSERT INTO `geo_brgys` VALUES ('198', '279', 'Cofradia');
INSERT INTO `geo_brgys` VALUES ('199', '279', 'Dakila');
INSERT INTO `geo_brgys` VALUES ('200', '279', 'Guinhawa');
INSERT INTO `geo_brgys` VALUES ('201', '279', 'Ligas');
INSERT INTO `geo_brgys` VALUES ('202', '279', 'Liang');
INSERT INTO `geo_brgys` VALUES ('203', '279', 'Longos');
INSERT INTO `geo_brgys` VALUES ('204', '279', 'Look 1st');
INSERT INTO `geo_brgys` VALUES ('205', '279', 'Look 2nd');
INSERT INTO `geo_brgys` VALUES ('206', '279', 'Lugam');
INSERT INTO `geo_brgys` VALUES ('207', '279', 'Mabolo');
INSERT INTO `geo_brgys` VALUES ('208', '279', 'Mambog');
INSERT INTO `geo_brgys` VALUES ('209', '279', 'Masile');
INSERT INTO `geo_brgys` VALUES ('210', '279', 'Matimbo');
INSERT INTO `geo_brgys` VALUES ('211', '279', 'Mojon');
INSERT INTO `geo_brgys` VALUES ('212', '279', 'Namayan');
INSERT INTO `geo_brgys` VALUES ('213', '279', 'Niugan');
INSERT INTO `geo_brgys` VALUES ('214', '279', 'Pamarawan');
INSERT INTO `geo_brgys` VALUES ('215', '279', 'Panasahan');
INSERT INTO `geo_brgys` VALUES ('216', '279', 'Pinagbakahan');
INSERT INTO `geo_brgys` VALUES ('217', '279', 'San Agustin');
INSERT INTO `geo_brgys` VALUES ('218', '279', 'San Gabriel');
INSERT INTO `geo_brgys` VALUES ('219', '279', 'San Juan');
INSERT INTO `geo_brgys` VALUES ('220', '279', 'San Pablo');
INSERT INTO `geo_brgys` VALUES ('221', '279', 'San Vicente (Poblacion)');
INSERT INTO `geo_brgys` VALUES ('222', '279', 'Santiago');
INSERT INTO `geo_brgys` VALUES ('223', '279', 'Santisima Trinidad');
INSERT INTO `geo_brgys` VALUES ('224', '279', 'Santo Cristo');
INSERT INTO `geo_brgys` VALUES ('225', '279', 'Santo Niño (Poblacion)');
INSERT INTO `geo_brgys` VALUES ('226', '279', 'Santo Rosario');
INSERT INTO `geo_brgys` VALUES ('227', '279', 'Santor');
INSERT INTO `geo_brgys` VALUES ('228', '279', 'Sumapang Bata');
INSERT INTO `geo_brgys` VALUES ('229', '279', 'Sumapang Matanda');
INSERT INTO `geo_brgys` VALUES ('230', '279', 'Taal');
INSERT INTO `geo_brgys` VALUES ('231', '279', 'Tikay');
INSERT INTO `geo_brgys` VALUES ('232', '286', 'San Francisco');
INSERT INTO `geo_brgys` VALUES ('233', '1214', 'Balucuc');
INSERT INTO `geo_brgys` VALUES ('234', '1214', 'Calantipe');
INSERT INTO `geo_brgys` VALUES ('235', '1214', 'Cansinala');
INSERT INTO `geo_brgys` VALUES ('236', '1214', 'Capalangan');
INSERT INTO `geo_brgys` VALUES ('237', '1214', 'Colgante');
INSERT INTO `geo_brgys` VALUES ('238', '1214', 'Paligui');
INSERT INTO `geo_brgys` VALUES ('239', '1214', 'Sampaloc');
INSERT INTO `geo_brgys` VALUES ('240', '1214', 'San Juan');
INSERT INTO `geo_brgys` VALUES ('241', '1214', 'San Vicente');
INSERT INTO `geo_brgys` VALUES ('242', '1214', 'Sucad');
INSERT INTO `geo_brgys` VALUES ('243', '1214', 'Sulipan');
INSERT INTO `geo_brgys` VALUES ('244', '1214', 'Tabuyuc');
INSERT INTO `geo_brgys` VALUES ('245', '1222', 'Batasan [Bitas]');
INSERT INTO `geo_brgys` VALUES ('246', '1222', 'Caduang Tete');
INSERT INTO `geo_brgys` VALUES ('247', '1222', 'Candelaria');
INSERT INTO `geo_brgys` VALUES ('248', '1222', 'Castuli');
INSERT INTO `geo_brgys` VALUES ('249', '1222', 'Consuelo');
INSERT INTO `geo_brgys` VALUES ('250', '1222', 'Dalayap');
INSERT INTO `geo_brgys` VALUES ('251', '1222', 'Mataguiti');
INSERT INTO `geo_brgys` VALUES ('252', '1222', 'San Esteban');
INSERT INTO `geo_brgys` VALUES ('253', '1222', 'San Francisco');
INSERT INTO `geo_brgys` VALUES ('254', '1222', 'San Gabriel (Poblacion)');
INSERT INTO `geo_brgys` VALUES ('255', '1222', 'San Isidro (Poblacion)');
INSERT INTO `geo_brgys` VALUES ('256', '1222', 'San Jose');
INSERT INTO `geo_brgys` VALUES ('257', '1222', 'San Juan2');
INSERT INTO `geo_brgys` VALUES ('258', '1222', 'San Rafael');
INSERT INTO `geo_brgys` VALUES ('259', '1222', 'San Roque (Poblacion)');
INSERT INTO `geo_brgys` VALUES ('260', '1222', 'San Vicente2');
INSERT INTO `geo_brgys` VALUES ('261', '1222', 'Santa Cruz (Poblacion)');
INSERT INTO `geo_brgys` VALUES ('262', '1222', 'Santa Lutgarda');
INSERT INTO `geo_brgys` VALUES ('263', '1222', 'Santa Maria');
INSERT INTO `geo_brgys` VALUES ('264', '1222', 'Santa Rita (Poblacion)');
INSERT INTO `geo_brgys` VALUES ('265', '1222', 'Santo Niño');
INSERT INTO `geo_brgys` VALUES ('266', '1222', 'Santo Rosario (Poblacion)');
INSERT INTO `geo_brgys` VALUES ('267', '1222', 'Sapang Pari');
INSERT INTO `geo_brgys` VALUES ('268', '1222', 'Saplad David');
INSERT INTO `geo_brgys` VALUES ('269', '1222', 'Tacasan');
INSERT INTO `geo_brgys` VALUES ('270', '1222', 'Telacsan');
INSERT INTO `geo_brgys` VALUES ('271', '1213', 'Alasas');
INSERT INTO `geo_brgys` VALUES ('272', '1213', 'Baliti');
INSERT INTO `geo_brgys` VALUES ('273', '1213', 'Bulaon');
INSERT INTO `geo_brgys` VALUES ('274', '1213', 'Calulut');
INSERT INTO `geo_brgys` VALUES ('275', '1213', 'Dela Paz Norte');
INSERT INTO `geo_brgys` VALUES ('276', '1213', 'Dela Paz Sur');
INSERT INTO `geo_brgys` VALUES ('277', '1213', 'Del Carmen');
INSERT INTO `geo_brgys` VALUES ('278', '1213', 'Del Pilar');
INSERT INTO `geo_brgys` VALUES ('279', '1213', 'Del Rosario');
INSERT INTO `geo_brgys` VALUES ('280', '1213', 'Dolores');
INSERT INTO `geo_brgys` VALUES ('281', '1213', 'Juliana');
INSERT INTO `geo_brgys` VALUES ('282', '1213', 'Lara');
INSERT INTO `geo_brgys` VALUES ('283', '1213', 'Lourdes');
INSERT INTO `geo_brgys` VALUES ('284', '1213', 'Maimpis');
INSERT INTO `geo_brgys` VALUES ('285', '1213', 'Magliman');
INSERT INTO `geo_brgys` VALUES ('286', '1213', 'Malino');
INSERT INTO `geo_brgys` VALUES ('287', '1213', 'Malpitic');
INSERT INTO `geo_brgys` VALUES ('288', '1213', 'Pandaras');
INSERT INTO `geo_brgys` VALUES ('289', '1213', 'Panipuan');
INSERT INTO `geo_brgys` VALUES ('290', '1213', 'Pulung Bulo');
INSERT INTO `geo_brgys` VALUES ('291', '1213', 'Santo Rosario (Poblacion)');
INSERT INTO `geo_brgys` VALUES ('292', '1213', 'Quebiawan');
INSERT INTO `geo_brgys` VALUES ('293', '1213', 'Saguin');
INSERT INTO `geo_brgys` VALUES ('294', '1213', 'San Agustin');
INSERT INTO `geo_brgys` VALUES ('295', '1213', 'San Felipe');
INSERT INTO `geo_brgys` VALUES ('296', '1213', 'San Isidro');
INSERT INTO `geo_brgys` VALUES ('297', '1213', 'San Jose');
INSERT INTO `geo_brgys` VALUES ('298', '1213', 'San Juan');
INSERT INTO `geo_brgys` VALUES ('299', '1213', 'San Nicolas');
INSERT INTO `geo_brgys` VALUES ('300', '1213', 'San Pedro Cutud');
INSERT INTO `geo_brgys` VALUES ('301', '1213', 'Santa Lucia');
INSERT INTO `geo_brgys` VALUES ('302', '1213', 'Santa Teresita');
INSERT INTO `geo_brgys` VALUES ('303', '1213', 'Santo Niño');
INSERT INTO `geo_brgys` VALUES ('304', '1213', 'Sindalan');
INSERT INTO `geo_brgys` VALUES ('305', '1213', 'Telabastagan');
INSERT INTO `geo_brgys` VALUES ('306', '1232', 'Moras De La Paz');
INSERT INTO `geo_brgys` VALUES ('307', '1232', 'Sto. Tomas');
INSERT INTO `geo_brgys` VALUES ('308', '1232', 'San Bartolome');
INSERT INTO `geo_brgys` VALUES ('309', '1232', 'San Matias');
INSERT INTO `geo_brgys` VALUES ('310', '1232', 'San Vicente');
INSERT INTO `geo_brgys` VALUES ('311', '1232', 'Santo Rosario (Pau)');
INSERT INTO `geo_brgys` VALUES ('312', '1232', 'Sapa (Santo Niño)');
INSERT INTO `geo_brgys` VALUES ('313', '1232', 'Poblacion');
INSERT INTO `geo_brgys` VALUES ('314', '1226', 'Bulac');
INSERT INTO `geo_brgys` VALUES ('315', '1226', 'Dawe');
INSERT INTO `geo_brgys` VALUES ('316', '1226', 'Lourdes');
INSERT INTO `geo_brgys` VALUES ('317', '1226', 'Maniango');
INSERT INTO `geo_brgys` VALUES ('318', '1226', 'San Francisco Javier (2)');
INSERT INTO `geo_brgys` VALUES ('319', '1226', 'San Francisco De Asisi (1)');
INSERT INTO `geo_brgys` VALUES ('320', '1226', 'San Isidro');
INSERT INTO `geo_brgys` VALUES ('321', '1226', 'San Nicolas (Poblacion)');
INSERT INTO `geo_brgys` VALUES ('322', '1226', 'San Pedro');
INSERT INTO `geo_brgys` VALUES ('323', '1226', 'Sta. Catalina');
INSERT INTO `geo_brgys` VALUES ('324', '1226', 'Sta. Maria');
INSERT INTO `geo_brgys` VALUES ('325', '1226', 'Sta. Rita');
INSERT INTO `geo_brgys` VALUES ('326', '1226', 'Sto. Domingo');
INSERT INTO `geo_brgys` VALUES ('327', '1226', 'Sto. Rosario');
INSERT INTO `geo_brgys` VALUES ('328', '1226', 'Saplad');
INSERT INTO `geo_brgys` VALUES ('329', '1229', 'Concepcion');
INSERT INTO `geo_brgys` VALUES ('330', '1229', 'De La Paz');
INSERT INTO `geo_brgys` VALUES ('331', '1229', 'San Juan');
INSERT INTO `geo_brgys` VALUES ('332', '1229', 'San Agustin');
INSERT INTO `geo_brgys` VALUES ('333', '1229', 'San Isidro');
INSERT INTO `geo_brgys` VALUES ('334', '1229', 'San Jose');
INSERT INTO `geo_brgys` VALUES ('335', '1229', 'San Miguel');
INSERT INTO `geo_brgys` VALUES ('336', '1229', 'San Nicolas');
INSERT INTO `geo_brgys` VALUES ('337', '1229', 'San Pablo Libutad');
INSERT INTO `geo_brgys` VALUES ('338', '1229', 'San Pablo Propio');
INSERT INTO `geo_brgys` VALUES ('339', '1229', 'San Pedro');
INSERT INTO `geo_brgys` VALUES ('340', '1229', 'Sta. Cruz');
INSERT INTO `geo_brgys` VALUES ('341', '1229', 'Sta. Monica');
INSERT INTO `geo_brgys` VALUES ('342', '1229', 'Sto. Niño');
INSERT INTO `geo_brgys` VALUES ('343', '1132', 'A. Bonifacio Sur (A Bonifacio)');
INSERT INTO `geo_brgys` VALUES ('344', '1132', 'A. Bonifacio Norte (San Francisco)');
INSERT INTO `geo_brgys` VALUES ('345', '1132', 'Bagumbayan (Poblacion)');
INSERT INTO `geo_brgys` VALUES ('346', '1132', 'Bosque');
INSERT INTO `geo_brgys` VALUES ('347', '1132', 'Caridad Norte');
INSERT INTO `geo_brgys` VALUES ('348', '1132', 'Caridad Sur');
INSERT INTO `geo_brgys` VALUES ('349', '1132', 'Casile');
INSERT INTO `geo_brgys` VALUES ('350', '1132', 'Florida Blanca');
INSERT INTO `geo_brgys` VALUES ('351', '1132', 'General Luna');
INSERT INTO `geo_brgys` VALUES ('352', '1132', 'General Ricarte');
INSERT INTO `geo_brgys` VALUES ('353', '1132', 'Gomez');
INSERT INTO `geo_brgys` VALUES ('354', '1132', 'Inanama');
INSERT INTO `geo_brgys` VALUES ('355', '1132', 'Ligaya');
INSERT INTO `geo_brgys` VALUES ('356', '1132', 'Mabini');
INSERT INTO `geo_brgys` VALUES ('357', '1132', 'Murcon');
INSERT INTO `geo_brgys` VALUES ('358', '1132', 'Plaridel');
INSERT INTO `geo_brgys` VALUES ('359', '1132', 'San Felipe');
INSERT INTO `geo_brgys` VALUES ('360', '1132', 'San Nicolas');
INSERT INTO `geo_brgys` VALUES ('361', '1132', 'San Vicente');
INSERT INTO `geo_brgys` VALUES ('362', '1132', 'Santa Barbara');
INSERT INTO `geo_brgys` VALUES ('363', '1132', 'Victoria');
INSERT INTO `geo_brgys` VALUES ('364', '1132', 'Villa Viniegas');
INSERT INTO `geo_brgys` VALUES ('365', '1525', 'Anupul');
INSERT INTO `geo_brgys` VALUES ('366', '1525', 'Banaba');
INSERT INTO `geo_brgys` VALUES ('367', '1525', 'Bangcu');
INSERT INTO `geo_brgys` VALUES ('368', '1525', 'Culubasa');
INSERT INTO `geo_brgys` VALUES ('369', '1525', 'Dela Cruz');
INSERT INTO `geo_brgys` VALUES ('370', '1525', 'La Paz');
INSERT INTO `geo_brgys` VALUES ('371', '1525', 'Lourdes');
INSERT INTO `geo_brgys` VALUES ('372', '1525', 'Malonzo');
INSERT INTO `geo_brgys` VALUES ('373', '1525', 'San Nicolas');
INSERT INTO `geo_brgys` VALUES ('374', '1525', 'San Pedro');
INSERT INTO `geo_brgys` VALUES ('375', '1525', 'San Rafael');
INSERT INTO `geo_brgys` VALUES ('376', '1525', 'San Roque');
INSERT INTO `geo_brgys` VALUES ('377', '1525', 'San Vicente');
INSERT INTO `geo_brgys` VALUES ('378', '1525', 'Santo Niño');
INSERT INTO `geo_brgys` VALUES ('379', '1525', 'Virgen de los Remedios (Pacalcal)');
INSERT INTO `geo_brgys` VALUES ('380', '1223', 'Camias');
INSERT INTO `geo_brgys` VALUES ('381', '1223', 'Dolores');
INSERT INTO `geo_brgys` VALUES ('382', '1223', 'San Antonio');
INSERT INTO `geo_brgys` VALUES ('383', '1223', 'San Agustin');
INSERT INTO `geo_brgys` VALUES ('384', '1223', 'Navaling');
INSERT INTO `geo_brgys` VALUES ('385', '1223', 'La Paz');
INSERT INTO `geo_brgys` VALUES ('386', '1223', 'Escaler');
INSERT INTO `geo_brgys` VALUES ('387', '1223', 'San Francisco');
INSERT INTO `geo_brgys` VALUES ('388', '1223', 'San Ildefonso');
INSERT INTO `geo_brgys` VALUES ('389', '1223', 'San Isidro');
INSERT INTO `geo_brgys` VALUES ('390', '1223', 'San Jose');
INSERT INTO `geo_brgys` VALUES ('391', '1223', 'San Miguel');
INSERT INTO `geo_brgys` VALUES ('392', '1223', 'San Nicolas 1st (Poblacion)');
INSERT INTO `geo_brgys` VALUES ('393', '1223', 'San Nicolas 2nd');
INSERT INTO `geo_brgys` VALUES ('394', '1223', 'San Pablo (Poblacion)');
INSERT INTO `geo_brgys` VALUES ('395', '1223', 'San Pedro I');
INSERT INTO `geo_brgys` VALUES ('396', '1223', 'San Pedro II');
INSERT INTO `geo_brgys` VALUES ('397', '1223', 'San Roque');
INSERT INTO `geo_brgys` VALUES ('398', '1223', 'San Vicente');
INSERT INTO `geo_brgys` VALUES ('399', '1223', 'Santa Cruz (Poblacion)');
INSERT INTO `geo_brgys` VALUES ('400', '1223', 'Santa Lucia');
INSERT INTO `geo_brgys` VALUES ('401', '1223', 'Santa Maria');
INSERT INTO `geo_brgys` VALUES ('402', '1223', 'Santo Niño');
INSERT INTO `geo_brgys` VALUES ('403', '1223', 'Santo Rosario');
INSERT INTO `geo_brgys` VALUES ('404', '1223', 'Bucanan');
INSERT INTO `geo_brgys` VALUES ('405', '1223', 'Turu');
INSERT INTO `geo_brgys` VALUES ('406', '1223', 'Ayala');
INSERT INTO `geo_brgys` VALUES ('407', '1215', 'Arenas');
INSERT INTO `geo_brgys` VALUES ('408', '1215', 'Baliti');
INSERT INTO `geo_brgys` VALUES ('409', '1215', 'Batasan');
INSERT INTO `geo_brgys` VALUES ('410', '1215', 'Buensuceso');
INSERT INTO `geo_brgys` VALUES ('411', '1215', 'Candating');
INSERT INTO `geo_brgys` VALUES ('412', '1215', 'Gatiawin');
INSERT INTO `geo_brgys` VALUES ('413', '1215', 'Guemasan');
INSERT INTO `geo_brgys` VALUES ('414', '1215', 'La Paz (Turu)');
INSERT INTO `geo_brgys` VALUES ('415', '1215', 'Lacmit');
INSERT INTO `geo_brgys` VALUES ('416', '1215', 'Lacquios');
INSERT INTO `geo_brgys` VALUES ('417', '1215', 'Mangga-Cacutud');
INSERT INTO `geo_brgys` VALUES ('418', '1215', 'Mapalad');
INSERT INTO `geo_brgys` VALUES ('419', '1215', 'Palinlang');
INSERT INTO `geo_brgys` VALUES ('420', '1215', 'Paralaya');
INSERT INTO `geo_brgys` VALUES ('421', '1215', 'Plazang Luma');
INSERT INTO `geo_brgys` VALUES ('422', '1215', 'Poblacion');
INSERT INTO `geo_brgys` VALUES ('423', '1215', 'San Agustin Norte');
INSERT INTO `geo_brgys` VALUES ('424', '1215', 'San Agustin Sur');
INSERT INTO `geo_brgys` VALUES ('425', '1215', 'San Antonio');
INSERT INTO `geo_brgys` VALUES ('426', '1215', 'San Jose Mesulo');
INSERT INTO `geo_brgys` VALUES ('427', '1215', 'San Juan Bano');
INSERT INTO `geo_brgys` VALUES ('428', '1215', 'San Mateo');
INSERT INTO `geo_brgys` VALUES ('429', '1215', 'San Nicolas');
INSERT INTO `geo_brgys` VALUES ('430', '1215', 'San Roque Bitas');
INSERT INTO `geo_brgys` VALUES ('431', '1215', 'Cupang (Santa Lucia)');
INSERT INTO `geo_brgys` VALUES ('432', '1215', 'Matamo (Santa Lucia)');
INSERT INTO `geo_brgys` VALUES ('433', '1215', 'Santo Niño Tabuan');
INSERT INTO `geo_brgys` VALUES ('434', '1215', 'Suclayin');
INSERT INTO `geo_brgys` VALUES ('435', '1215', 'Telapayong');
INSERT INTO `geo_brgys` VALUES ('436', '1215', 'Kaledian (Camba)');
INSERT INTO `geo_brgys` VALUES ('437', '653', 'Abangay');
INSERT INTO `geo_brgys` VALUES ('438', '653', 'Amamaros');
INSERT INTO `geo_brgys` VALUES ('439', '653', 'Arsenal Aduana');
INSERT INTO `geo_brgys` VALUES ('440', '653', 'Bagacay');
INSERT INTO `geo_brgys` VALUES ('441', '653', 'Barasan');
INSERT INTO `geo_brgys` VALUES ('442', '653', 'Batuan');
INSERT INTO `geo_brgys` VALUES ('443', '653', 'Baybay Tanza');
INSERT INTO `geo_brgys` VALUES ('444', '653', 'Bongco');
INSERT INTO `geo_brgys` VALUES ('445', '653', 'Bonifacio Tanza');
INSERT INTO `geo_brgys` VALUES ('446', '653', 'Cahaguichican');
INSERT INTO `geo_brgys` VALUES ('447', '653', 'Callan');
INSERT INTO `geo_brgys` VALUES ('448', '653', 'Cansilayan');
INSERT INTO `geo_brgys` VALUES ('449', '653', 'Casalsagan');
INSERT INTO `geo_brgys` VALUES ('450', '653', 'Cato-ogan');
INSERT INTO `geo_brgys` VALUES ('451', '653', 'Cau-ayan');
INSERT INTO `geo_brgys` VALUES ('452', '653', 'Concepcion-Montes');
INSERT INTO `geo_brgys` VALUES ('453', '653', 'Culob');
INSERT INTO `geo_brgys` VALUES ('454', '653', 'Danao');
INSERT INTO `geo_brgys` VALUES ('455', '653', 'Dapitan');
INSERT INTO `geo_brgys` VALUES ('456', '653', 'Dawis');
INSERT INTO `geo_brgys` VALUES ('457', '653', 'Delgado-Jalandoni-Bagumbayan');
INSERT INTO `geo_brgys` VALUES ('458', '653', 'Dongsol');
INSERT INTO `geo_brgys` VALUES ('459', '653', 'Edganzon');
INSERT INTO `geo_brgys` VALUES ('460', '653', 'Fernando Parcon Ward');
INSERT INTO `geo_brgys` VALUES ('461', '653', 'Flores');
INSERT INTO `geo_brgys` VALUES ('462', '653', 'Fundacion');
INSERT INTO `geo_brgys` VALUES ('463', '653', 'General Hughes-Montes');
INSERT INTO `geo_brgys` VALUES ('464', '653', 'Gloria');
INSERT INTO `geo_brgys` VALUES ('465', '653', 'Guibuangan');
INSERT INTO `geo_brgys` VALUES ('466', '653', 'Guinacas');
INSERT INTO `geo_brgys` VALUES ('467', '653', 'Hipodromo');
INSERT INTO `geo_brgys` VALUES ('468', '653', 'Igang');
INSERT INTO `geo_brgys` VALUES ('469', '653', 'Inday');
INSERT INTO `geo_brgys` VALUES ('470', '653', 'Intaluan');
INSERT INTO `geo_brgys` VALUES ('471', '653', 'Iwa Ilaud');
INSERT INTO `geo_brgys` VALUES ('472', '653', 'Iwa Ilaya');
INSERT INTO `geo_brgys` VALUES ('473', '653', 'Jalandoni-Wilson');
INSERT INTO `geo_brgys` VALUES ('474', '653', 'Jamabalud');
INSERT INTO `geo_brgys` VALUES ('475', '653', 'Jebioc');
INSERT INTO `geo_brgys` VALUES ('476', '653', 'Kahirupan');
INSERT INTO `geo_brgys` VALUES ('477', '653', 'Kauswagan');
INSERT INTO `geo_brgys` VALUES ('478', '653', 'Lay-Ahan');
INSERT INTO `geo_brgys` VALUES ('479', '653', 'Legaspi dela Rama');
INSERT INTO `geo_brgys` VALUES ('480', '653', 'Liberation');
INSERT INTO `geo_brgys` VALUES ('481', '653', 'Lopez Jaena Ward');
INSERT INTO `geo_brgys` VALUES ('482', '653', 'Lumbo');
INSERT INTO `geo_brgys` VALUES ('483', '653', 'Mabolo-Delgado');
INSERT INTO `geo_brgys` VALUES ('484', '653', 'Macatol');
INSERT INTO `geo_brgys` VALUES ('485', '653', 'Magsaysay');
INSERT INTO `geo_brgys` VALUES ('486', '653', 'Malipayon-Delgado');
INSERT INTO `geo_brgys` VALUES ('487', '653', 'Malusgod');
INSERT INTO `geo_brgys` VALUES ('488', '653', 'Maria Clara');
INSERT INTO `geo_brgys` VALUES ('489', '653', 'Monica Blumentritt');
INSERT INTO `geo_brgys` VALUES ('490', '653', 'Muelle Loney-Montes');
INSERT INTO `geo_brgys` VALUES ('491', '653', 'Nabitasan');
INSERT INTO `geo_brgys` VALUES ('492', '653', 'Naga');
INSERT INTO `geo_brgys` VALUES ('493', '653', 'Nanga');
INSERT INTO `geo_brgys` VALUES ('494', '653', 'Naslo');
INSERT INTO `geo_brgys` VALUES ('495', '653', 'Nonoy');
INSERT INTO `geo_brgys` VALUES ('496', '653', 'Ortiz');
INSERT INTO `geo_brgys` VALUES ('497', '653', 'Osmeña');
INSERT INTO `geo_brgys` VALUES ('498', '653', 'Pajo');
INSERT INTO `geo_brgys` VALUES ('499', '653', 'Palanguia');
INSERT INTO `geo_brgys` VALUES ('500', '653', 'Pitogo');
INSERT INTO `geo_brgys` VALUES ('501', '653', 'Polot-an');
INSERT INTO `geo_brgys` VALUES ('502', '653', 'President Roxas');
INSERT INTO `geo_brgys` VALUES ('503', '653', 'Primitivo Ledesma Ward');
INSERT INTO `geo_brgys` VALUES ('504', '653', 'Purog');
INSERT INTO `geo_brgys` VALUES ('505', '653', 'Rima-Rizal');
INSERT INTO `geo_brgys` VALUES ('506', '653', 'Rizal Estanzuela');
INSERT INTO `geo_brgys` VALUES ('507', '653', 'Rizal Ibarra');
INSERT INTO `geo_brgys` VALUES ('508', '653', 'Rizal Palapala I');
INSERT INTO `geo_brgys` VALUES ('509', '653', 'Rizal Palapala II');
INSERT INTO `geo_brgys` VALUES ('510', '653', 'Roxas Village');
INSERT INTO `geo_brgys` VALUES ('511', '653', 'Rumbang');
INSERT INTO `geo_brgys` VALUES ('512', '653', 'Sampaguita');
INSERT INTO `geo_brgys` VALUES ('513', '653', 'San Agustin');
INSERT INTO `geo_brgys` VALUES ('514', '653', 'San Felix');
INSERT INTO `geo_brgys` VALUES ('515', '653', 'San Jose');
INSERT INTO `geo_brgys` VALUES ('516', '653', 'San Jose Ward');
INSERT INTO `geo_brgys` VALUES ('517', '653', 'Santo Rosario-Duran');
INSERT INTO `geo_brgys` VALUES ('518', '653', 'Sinuagan');
INSERT INTO `geo_brgys` VALUES ('519', '653', 'Tanza-Esperanza');
INSERT INTO `geo_brgys` VALUES ('520', '653', 'Timawa Tanza I');
INSERT INTO `geo_brgys` VALUES ('521', '653', 'Timawa Tanza II');
INSERT INTO `geo_brgys` VALUES ('522', '653', 'Tuburan');
INSERT INTO `geo_brgys` VALUES ('523', '653', 'Tumcon Ilaud');
INSERT INTO `geo_brgys` VALUES ('524', '653', 'Tumcon Ilaya');
INSERT INTO `geo_brgys` VALUES ('525', '653', 'Ubang');
INSERT INTO `geo_brgys` VALUES ('526', '653', 'Veterans Village');
INSERT INTO `geo_brgys` VALUES ('527', '653', 'Villa Anita');
INSERT INTO `geo_brgys` VALUES ('528', '653', 'Yulo-Arroyo');
INSERT INTO `geo_brgys` VALUES ('529', '653', 'Zamora-Melliza');
INSERT INTO `geo_brgys` VALUES ('530', '653', 'Zarrague');
INSERT INTO `geo_brgys` VALUES ('531', '653', 'Bonifacio, Arevalo');
INSERT INTO `geo_brgys` VALUES ('532', '653', 'Calaparan, Arevalo');
INSERT INTO `geo_brgys` VALUES ('533', '653', 'Dulonan, Arevalo');
INSERT INTO `geo_brgys` VALUES ('534', '653', 'Mohon, Arevalo');
INSERT INTO `geo_brgys` VALUES ('535', '653', 'Quezon, Arevalo');
INSERT INTO `geo_brgys` VALUES ('536', '653', 'San Jose, Arevalo');
INSERT INTO `geo_brgys` VALUES ('537', '653', 'Santa Cruz, Arevalo');
INSERT INTO `geo_brgys` VALUES ('538', '653', 'Santa Filomena, Arevalo');
INSERT INTO `geo_brgys` VALUES ('539', '653', 'Santo Domingo, Arevalo');
INSERT INTO `geo_brgys` VALUES ('540', '653', 'Santo Niño Norte, Arevalo');
INSERT INTO `geo_brgys` VALUES ('541', '653', 'Santo Niño Sur, Arevalo');
INSERT INTO `geo_brgys` VALUES ('542', '653', 'So-oc, Arevalo');
INSERT INTO `geo_brgys` VALUES ('543', '653', 'Yulo Drive, Arevalo');
INSERT INTO `geo_brgys` VALUES ('544', '653', 'Arguelles, Jaro');
INSERT INTO `geo_brgys` VALUES ('545', '653', 'Balabago, Jaro');
INSERT INTO `geo_brgys` VALUES ('546', '653', 'Balantang, Jaro');
INSERT INTO `geo_brgys` VALUES ('547', '653', 'Benedicto, Jaro');
INSERT INTO `geo_brgys` VALUES ('548', '653', 'Bito-on, Jaro');
INSERT INTO `geo_brgys` VALUES ('549', '653', 'Buhang, Jaro');
INSERT INTO `geo_brgys` VALUES ('550', '653', 'Buntatala, Jaro');
INSERT INTO `geo_brgys` VALUES ('551', '653', 'Calubihan, Jaro');
INSERT INTO `geo_brgys` VALUES ('552', '653', 'Camalig, Jaro');
INSERT INTO `geo_brgys` VALUES ('553', '653', 'Cuartero, Jaro');
INSERT INTO `geo_brgys` VALUES ('554', '653', 'Cubay, Jaro');
INSERT INTO `geo_brgys` VALUES ('555', '653', 'Democracia, Jaro');
INSERT INTO `geo_brgys` VALUES ('556', '653', 'Desamparados, Jaro');
INSERT INTO `geo_brgys` VALUES ('557', '653', 'Dungon A, Jaro');
INSERT INTO `geo_brgys` VALUES ('558', '653', 'Dungon B, Jaro');
INSERT INTO `geo_brgys` VALUES ('559', '653', 'El 98 Castilla (Claudio Lopez), Jaro');
INSERT INTO `geo_brgys` VALUES ('560', '653', 'Fajardo, Jaro');
INSERT INTO `geo_brgys` VALUES ('561', '653', 'Javellana, Jaro');
INSERT INTO `geo_brgys` VALUES ('562', '653', 'Lanit, Jaro');
INSERT INTO `geo_brgys` VALUES ('563', '653', 'Libertad, Santa Isabel, Jaro');
INSERT INTO `geo_brgys` VALUES ('564', '653', 'Lopez Jaena, Jaro');
INSERT INTO `geo_brgys` VALUES ('565', '653', 'Luna, Jaro');
INSERT INTO `geo_brgys` VALUES ('566', '653', 'M.V. Hechanova, Jaro');
INSERT INTO `geo_brgys` VALUES ('567', '653', 'Marcelo H. del Pilar, Jaro');
INSERT INTO `geo_brgys` VALUES ('568', '653', 'Maria Cristina, Jaro');
INSERT INTO `geo_brgys` VALUES ('569', '653', 'Montinola, Jaro');
INSERT INTO `geo_brgys` VALUES ('570', '653', 'Our Lady of Fatima, Jaro');
INSERT INTO `geo_brgys` VALUES ('571', '653', 'Our Lady of Lourdes, Jaro');
INSERT INTO `geo_brgys` VALUES ('572', '653', 'Quintin Salas, Jaro');
INSERT INTO `geo_brgys` VALUES ('573', '653', 'Sambag, Jaro');
INSERT INTO `geo_brgys` VALUES ('574', '653', 'San Isidro, Jaro');
INSERT INTO `geo_brgys` VALUES ('575', '653', 'San Jose, Jaro');
INSERT INTO `geo_brgys` VALUES ('576', '653', 'San Pedro, Jaro');
INSERT INTO `geo_brgys` VALUES ('577', '653', 'San Roque, Jaro');
INSERT INTO `geo_brgys` VALUES ('578', '653', 'San Vicente, Jaro');
INSERT INTO `geo_brgys` VALUES ('579', '653', 'Seminario (Burgos Jalandoni), Jaro');
INSERT INTO `geo_brgys` VALUES ('580', '653', 'Simon Ledesma, Jaro');
INSERT INTO `geo_brgys` VALUES ('581', '653', 'Tabuc Suba, Jaro');
INSERT INTO `geo_brgys` VALUES ('582', '653', 'Tacas, Jaro');
INSERT INTO `geo_brgys` VALUES ('583', '653', 'Tagbac, Jaro');
INSERT INTO `geo_brgys` VALUES ('584', '653', 'Taytay Zone II, Jaro');
INSERT INTO `geo_brgys` VALUES ('585', '653', 'Ungka, Jaro');
INSERT INTO `geo_brgys` VALUES ('586', '653', 'Aguinaldo, La Paz');
INSERT INTO `geo_brgys` VALUES ('587', '653', 'Baldoza, Lapaz');
INSERT INTO `geo_brgys` VALUES ('588', '653', 'Bantud, Lapaz');
INSERT INTO `geo_brgys` VALUES ('589', '653', 'Banuyao, Lapaz');
INSERT INTO `geo_brgys` VALUES ('590', '653', 'Burgos-Mabini-Plaza, Lapaz');
INSERT INTO `geo_brgys` VALUES ('591', '653', 'Caingin, Lapaz');
INSERT INTO `geo_brgys` VALUES ('592', '653', 'Divinagracia, Lapaz');
INSERT INTO `geo_brgys` VALUES ('593', '653', 'Gustilo, Lapaz');
INSERT INTO `geo_brgys` VALUES ('594', '653', 'Hinactacan, Lapaz');
INSERT INTO `geo_brgys` VALUES ('595', '653', 'Ingore, Lapaz');
INSERT INTO `geo_brgys` VALUES ('596', '653', 'Jereos, Lapaz');
INSERT INTO `geo_brgys` VALUES ('597', '653', 'Laguda, Lapaz');
INSERT INTO `geo_brgys` VALUES ('598', '653', 'Lopez Jaena Norte, Lapaz');
INSERT INTO `geo_brgys` VALUES ('599', '653', 'Lopez Jaena Sur, Lapaz');
INSERT INTO `geo_brgys` VALUES ('600', '653', 'Luna, Lapaz');
INSERT INTO `geo_brgys` VALUES ('601', '653', 'MacArthur, Lapaz');
INSERT INTO `geo_brgys` VALUES ('602', '653', 'Magdalo, Lapaz');
INSERT INTO `geo_brgys` VALUES ('603', '653', 'Magsaysay Village, Lapaz');
INSERT INTO `geo_brgys` VALUES ('604', '653', 'Nabitasan, Lapaz');
INSERT INTO `geo_brgys` VALUES ('605', '653', 'Railway, Lapaz');
INSERT INTO `geo_brgys` VALUES ('606', '653', 'Rizal, Lapaz');
INSERT INTO `geo_brgys` VALUES ('607', '653', 'San Isidro, Lapaz');
INSERT INTO `geo_brgys` VALUES ('608', '653', 'San Nicolas, Lapaz');
INSERT INTO `geo_brgys` VALUES ('609', '653', 'Tabuc Suba, Lapaz');
INSERT INTO `geo_brgys` VALUES ('610', '653', 'Ticud, Lapaz');
INSERT INTO `geo_brgys` VALUES ('611', '653', 'Alalasan, Lapuz');
INSERT INTO `geo_brgys` VALUES ('612', '653', 'Don Esteban, Lapuz');
INSERT INTO `geo_brgys` VALUES ('613', '653', 'Jalandoni Estate, Lapuz');
INSERT INTO `geo_brgys` VALUES ('614', '653', 'Lapuz Norte, Lapuz');
INSERT INTO `geo_brgys` VALUES ('615', '653', 'Lapuz Sur, Lapuz');
INSERT INTO `geo_brgys` VALUES ('616', '653', 'Libertad, Lapuz');
INSERT INTO `geo_brgys` VALUES ('617', '653', 'Loboc, Lapuz');
INSERT INTO `geo_brgys` VALUES ('618', '653', 'Mansaya, Lapuz');
INSERT INTO `geo_brgys` VALUES ('619', '653', 'Obrero, Lapuz');
INSERT INTO `geo_brgys` VALUES ('620', '653', 'Progreso, Lapuz');
INSERT INTO `geo_brgys` VALUES ('621', '653', 'Punong, Lapuz');
INSERT INTO `geo_brgys` VALUES ('622', '653', 'Sinikway (Bangkerohan), Lapuz');
INSERT INTO `geo_brgys` VALUES ('623', '653', 'Abeto Mirasol Taft South (Quirino Abeto), Mandurriao');
INSERT INTO `geo_brgys` VALUES ('624', '653', 'Airport (Tabucan Airport), Mandurriao');
INSERT INTO `geo_brgys` VALUES ('625', '653', 'Bakhaw, Mandurriao');
INSERT INTO `geo_brgys` VALUES ('626', '653', 'Bolilao, Mandurriao');
INSERT INTO `geo_brgys` VALUES ('627', '653', 'Buhang Taft North, Mandurriao');
INSERT INTO `geo_brgys` VALUES ('628', '653', 'Calahunan, Mandurriao');
INSERT INTO `geo_brgys` VALUES ('629', '653', 'Dungon, Mandurriao');
INSERT INTO `geo_brgys` VALUES ('630', '653', 'Guzman-Jesena, Mandurriao');
INSERT INTO `geo_brgys` VALUES ('631', '653', 'Hibao-an Norte, Mandurriao');
INSERT INTO `geo_brgys` VALUES ('632', '653', 'Hibao-an Sur, Mandurriao');
INSERT INTO `geo_brgys` VALUES ('633', '653', 'Navais, Mandurriao');
INSERT INTO `geo_brgys` VALUES ('634', '653', 'Oñate de Leon, Mandurriao');
INSERT INTO `geo_brgys` VALUES ('635', '653', 'Pale Benedicto Rizal, Mandurriao');
INSERT INTO `geo_brgys` VALUES ('636', '653', 'PHHC Block 17, Mandurriao');
INSERT INTO `geo_brgys` VALUES ('637', '653', 'PHHC Block 22 NHA, Mandurriao');
INSERT INTO `geo_brgys` VALUES ('638', '653', 'San Rafael, Mandurriao');
INSERT INTO `geo_brgys` VALUES ('639', '653', 'Santa Rosa, Mandurriao');
INSERT INTO `geo_brgys` VALUES ('640', '653', 'Tabucan, Mandurriao');
INSERT INTO `geo_brgys` VALUES ('641', '653', 'Calumpang, Molo');
INSERT INTO `geo_brgys` VALUES ('642', '653', 'Cochero, Molo');
INSERT INTO `geo_brgys` VALUES ('643', '653', 'Compania, Molo');
INSERT INTO `geo_brgys` VALUES ('644', '653', 'East Baluarte, Molo');
INSERT INTO `geo_brgys` VALUES ('645', '653', 'East Timawa, Molo');
INSERT INTO `geo_brgys` VALUES ('646', '653', 'Habog-Habog Salvacion, Molo');
INSERT INTO `geo_brgys` VALUES ('647', '653', 'Infante, Molo');
INSERT INTO `geo_brgys` VALUES ('648', '653', 'Kasingkasing, Molo');
INSERT INTO `geo_brgys` VALUES ('649', '653', 'Katilingban, Molo');
INSERT INTO `geo_brgys` VALUES ('650', '653', 'Molo Boulevard, Molo');
INSERT INTO `geo_brgys` VALUES ('651', '653', 'North Avanceña, Molo');
INSERT INTO `geo_brgys` VALUES ('652', '653', 'North Baluarte, Molo');
INSERT INTO `geo_brgys` VALUES ('653', '653', 'North Fundidor, Molo');
INSERT INTO `geo_brgys` VALUES ('654', '653', 'North San Jose, Molo');
INSERT INTO `geo_brgys` VALUES ('655', '653', 'Poblacion Molo, Molo');
INSERT INTO `geo_brgys` VALUES ('656', '653', 'San Antonio, Molo');
INSERT INTO `geo_brgys` VALUES ('657', '653', 'San Juan, Molo');
INSERT INTO `geo_brgys` VALUES ('658', '653', 'San Pedro, Molo');
INSERT INTO `geo_brgys` VALUES ('659', '653', 'South Baluarte, Molo');
INSERT INTO `geo_brgys` VALUES ('660', '653', 'South Fundidor, Molo');
INSERT INTO `geo_brgys` VALUES ('661', '653', 'South San Jose, Molo');
INSERT INTO `geo_brgys` VALUES ('662', '653', 'Taal, Molo');
INSERT INTO `geo_brgys` VALUES ('663', '653', 'Tap-oc, Molo');
INSERT INTO `geo_brgys` VALUES ('664', '653', 'West Habog-Habog, Molo');
INSERT INTO `geo_brgys` VALUES ('665', '653', 'West Timawa, Molo');
INSERT INTO `geo_brgys` VALUES ('666', '677', 'M.V. Hechanova (Balagon)');
INSERT INTO `geo_brgys` VALUES ('667', '677', 'Bigke');
INSERT INTO `geo_brgys` VALUES ('668', '677', 'Buntatala');
INSERT INTO `geo_brgys` VALUES ('669', '677', 'Cagamutan Norte');
INSERT INTO `geo_brgys` VALUES ('670', '677', 'Cagamutan Sur');
INSERT INTO `geo_brgys` VALUES ('671', '677', 'Calaboa');
INSERT INTO `geo_brgys` VALUES ('672', '677', 'Camangay');
INSERT INTO `geo_brgys` VALUES ('673', '677', 'Cari Mayor');
INSERT INTO `geo_brgys` VALUES ('674', '677', 'Cari Minor');
INSERT INTO `geo_brgys` VALUES ('675', '677', 'Gua-an');
INSERT INTO `geo_brgys` VALUES ('676', '677', 'Guihaman');
INSERT INTO `geo_brgys` VALUES ('677', '677', 'Guinobatan');
INSERT INTO `geo_brgys` VALUES ('678', '677', 'Guintas');
INSERT INTO `geo_brgys` VALUES ('679', '677', 'Lapayon');
INSERT INTO `geo_brgys` VALUES ('680', '677', 'Nabitasan');
INSERT INTO `geo_brgys` VALUES ('681', '677', 'Napnud');
INSERT INTO `geo_brgys` VALUES ('682', '677', 'Poblacion');
INSERT INTO `geo_brgys` VALUES ('683', '677', 'San Vicente');
INSERT INTO `geo_brgys` VALUES ('684', '654', 'Agdahon');
INSERT INTO `geo_brgys` VALUES ('685', '654', 'Agdayao');
INSERT INTO `geo_brgys` VALUES ('686', '654', 'Aglalana');
INSERT INTO `geo_brgys` VALUES ('687', '654', 'Agtabo');
INSERT INTO `geo_brgys` VALUES ('688', '654', 'Agtambo');
INSERT INTO `geo_brgys` VALUES ('689', '654', 'Alimono');
INSERT INTO `geo_brgys` VALUES ('690', '654', 'Arac');
INSERT INTO `geo_brgys` VALUES ('691', '654', 'Ayuyan');
INSERT INTO `geo_brgys` VALUES ('692', '654', 'Bacuranan');
INSERT INTO `geo_brgys` VALUES ('693', '654', 'Bagacay');
INSERT INTO `geo_brgys` VALUES ('694', '654', 'Batu');
INSERT INTO `geo_brgys` VALUES ('695', '654', 'Bayan');
INSERT INTO `geo_brgys` VALUES ('696', '654', 'Bitaogan');
INSERT INTO `geo_brgys` VALUES ('697', '654', 'Buenavista');
INSERT INTO `geo_brgys` VALUES ('698', '654', 'Buyo');
INSERT INTO `geo_brgys` VALUES ('699', '654', 'Cabunga');
INSERT INTO `geo_brgys` VALUES ('700', '654', 'Cadilang');
INSERT INTO `geo_brgys` VALUES ('701', '654', 'Cairojan');
INSERT INTO `geo_brgys` VALUES ('702', '654', 'Dalicanan');
INSERT INTO `geo_brgys` VALUES ('703', '654', 'Gemat y');
INSERT INTO `geo_brgys` VALUES ('704', '654', 'Gemumua Agahon');
INSERT INTO `geo_brgys` VALUES ('705', '654', 'Gegacjac');
INSERT INTO `geo_brgys` VALUES ('706', '654', 'Gines Viejo');
INSERT INTO `geo_brgys` VALUES ('707', '654', 'Imbang Grande');
INSERT INTO `geo_brgys` VALUES ('708', '654', 'Jaguimitan');
INSERT INTO `geo_brgys` VALUES ('709', '654', 'Libo o');
INSERT INTO `geo_brgys` VALUES ('710', '654', 'Maasin');
INSERT INTO `geo_brgys` VALUES ('711', '654', 'Magdungao');
INSERT INTO `geo_brgys` VALUES ('712', '654', 'Malag it Grande');
INSERT INTO `geo_brgys` VALUES ('713', '654', 'Malag it Pequeño');
INSERT INTO `geo_brgys` VALUES ('714', '654', 'Mambiranan Grande');
INSERT INTO `geo_brgys` VALUES ('715', '654', 'Mambiranan Pequeño');
INSERT INTO `geo_brgys` VALUES ('716', '654', 'Man it');
INSERT INTO `geo_brgys` VALUES ('717', '654', 'Mantulang');
INSERT INTO `geo_brgys` VALUES ('718', '654', 'Mulapula');
INSERT INTO `geo_brgys` VALUES ('719', '654', 'Nueva Union');
INSERT INTO `geo_brgys` VALUES ('720', '654', 'Pangi');
INSERT INTO `geo_brgys` VALUES ('721', '654', 'Pagaypay');
INSERT INTO `geo_brgys` VALUES ('722', '654', 'Poblacion Ilawod');
INSERT INTO `geo_brgys` VALUES ('723', '654', 'Poblacion Ilaya');
INSERT INTO `geo_brgys` VALUES ('724', '654', 'Punong');
INSERT INTO `geo_brgys` VALUES ('725', '654', 'Quinagaringan Grande');
INSERT INTO `geo_brgys` VALUES ('726', '654', 'Quinagaringan Pequeño');
INSERT INTO `geo_brgys` VALUES ('727', '654', 'Sablogon');
INSERT INTO `geo_brgys` VALUES ('728', '654', 'Salngan');
INSERT INTO `geo_brgys` VALUES ('729', '654', 'Santo Tomas');
INSERT INTO `geo_brgys` VALUES ('730', '654', 'Sarapan');
INSERT INTO `geo_brgys` VALUES ('731', '654', 'Tagubong');
INSERT INTO `geo_brgys` VALUES ('732', '654', 'Talongonan');
INSERT INTO `geo_brgys` VALUES ('733', '654', 'Tubod');
INSERT INTO `geo_brgys` VALUES ('734', '654', 'Tuburan');
INSERT INTO `geo_brgys` VALUES ('735', '686', 'Abangay');
INSERT INTO `geo_brgys` VALUES ('736', '686', 'Amamaros');
INSERT INTO `geo_brgys` VALUES ('737', '686', 'Bagacay');
INSERT INTO `geo_brgys` VALUES ('738', '686', 'Barasan');
INSERT INTO `geo_brgys` VALUES ('739', '686', 'Batuan');
INSERT INTO `geo_brgys` VALUES ('740', '686', 'Bongco');
INSERT INTO `geo_brgys` VALUES ('741', '686', 'Cahaguichican');
INSERT INTO `geo_brgys` VALUES ('742', '686', 'Callan');
INSERT INTO `geo_brgys` VALUES ('743', '686', 'Cansilayan');
INSERT INTO `geo_brgys` VALUES ('744', '686', 'Casalsagan');
INSERT INTO `geo_brgys` VALUES ('745', '686', 'Cato-ogan');
INSERT INTO `geo_brgys` VALUES ('746', '686', 'Cau-ayan');
INSERT INTO `geo_brgys` VALUES ('747', '686', 'Culob');
INSERT INTO `geo_brgys` VALUES ('748', '686', 'Danao');
INSERT INTO `geo_brgys` VALUES ('749', '686', 'Dapitan');
INSERT INTO `geo_brgys` VALUES ('750', '686', 'Dawis');
INSERT INTO `geo_brgys` VALUES ('751', '686', 'Dongsol');
INSERT INTO `geo_brgys` VALUES ('752', '686', 'Fundacion');
INSERT INTO `geo_brgys` VALUES ('753', '686', 'Guinacas');
INSERT INTO `geo_brgys` VALUES ('754', '686', 'Guibuangan');
INSERT INTO `geo_brgys` VALUES ('755', '686', 'Igang');
INSERT INTO `geo_brgys` VALUES ('756', '686', 'Intaluan');
INSERT INTO `geo_brgys` VALUES ('757', '686', 'Iwa Ilaud');
INSERT INTO `geo_brgys` VALUES ('758', '686', 'Iwa Ilaya');
INSERT INTO `geo_brgys` VALUES ('759', '686', 'Jamabalud');
INSERT INTO `geo_brgys` VALUES ('760', '686', 'Jebioc');
INSERT INTO `geo_brgys` VALUES ('761', '686', 'Lay Ahan');
INSERT INTO `geo_brgys` VALUES ('762', '686', 'Primitivo Ledesma Ward');
INSERT INTO `geo_brgys` VALUES ('763', '686', 'Lopez Jaena Ward');
INSERT INTO `geo_brgys` VALUES ('764', '686', 'Lumbo');
INSERT INTO `geo_brgys` VALUES ('765', '686', 'Macatol');
INSERT INTO `geo_brgys` VALUES ('766', '686', 'Malusgod');
INSERT INTO `geo_brgys` VALUES ('767', '686', 'Naslo');
INSERT INTO `geo_brgys` VALUES ('768', '686', 'Nabitasan');
INSERT INTO `geo_brgys` VALUES ('769', '686', 'Naga');
INSERT INTO `geo_brgys` VALUES ('770', '686', 'Nanga');
INSERT INTO `geo_brgys` VALUES ('771', '686', 'Pajo');
INSERT INTO `geo_brgys` VALUES ('772', '686', 'Palanguia');
INSERT INTO `geo_brgys` VALUES ('773', '686', 'Fernando Parcon Ward');
INSERT INTO `geo_brgys` VALUES ('774', '686', 'Pitogo');
INSERT INTO `geo_brgys` VALUES ('775', '686', 'Polot-an');
INSERT INTO `geo_brgys` VALUES ('776', '686', 'Purog');
INSERT INTO `geo_brgys` VALUES ('777', '686', 'Rumbang');
INSERT INTO `geo_brgys` VALUES ('778', '686', 'San Jose Ward');
INSERT INTO `geo_brgys` VALUES ('779', '686', 'Sinuagan');
INSERT INTO `geo_brgys` VALUES ('780', '686', 'Tuburan');
INSERT INTO `geo_brgys` VALUES ('781', '686', 'Tumcon Ilaya');
INSERT INTO `geo_brgys` VALUES ('782', '686', 'Tumcon Ilaud');
INSERT INTO `geo_brgys` VALUES ('783', '686', 'Ubang');
INSERT INTO `geo_brgys` VALUES ('784', '686', 'Zarrague');
INSERT INTO `geo_brgys` VALUES ('785', '165', 'As-Is');
INSERT INTO `geo_brgys` VALUES ('786', '165', 'Balakilong');
INSERT INTO `geo_brgys` VALUES ('787', '165', 'Berinayan');
INSERT INTO `geo_brgys` VALUES ('788', '165', 'Bugaan East');
INSERT INTO `geo_brgys` VALUES ('789', '165', 'Bugaan West');
INSERT INTO `geo_brgys` VALUES ('790', '165', 'Buso-buso');
INSERT INTO `geo_brgys` VALUES ('791', '165', 'Dayap Itaas');
INSERT INTO `geo_brgys` VALUES ('792', '165', 'Gulod');
INSERT INTO `geo_brgys` VALUES ('793', '165', 'J. Leviste');
INSERT INTO `geo_brgys` VALUES ('794', '165', 'Molinete');
INSERT INTO `geo_brgys` VALUES ('795', '165', 'Niyugan');
INSERT INTO `geo_brgys` VALUES ('796', '165', 'Paliparan');
INSERT INTO `geo_brgys` VALUES ('797', '165', 'Barangay 1 (Poblacion)');
INSERT INTO `geo_brgys` VALUES ('798', '165', 'Barangay 2 (Poblacion)');
INSERT INTO `geo_brgys` VALUES ('799', '165', 'Barangay 3 (Poblacion)');
INSERT INTO `geo_brgys` VALUES ('800', '165', 'Barangay 4 (Poblacion)');
INSERT INTO `geo_brgys` VALUES ('801', '165', 'Barangay 5 (Poblacion)');
INSERT INTO `geo_brgys` VALUES ('802', '165', 'San Gabriel');
INSERT INTO `geo_brgys` VALUES ('803', '165', 'San Gregorio');
INSERT INTO `geo_brgys` VALUES ('804', '165', 'Santa Maria');
INSERT INTO `geo_brgys` VALUES ('805', '165', 'Ticub');
INSERT INTO `geo_brgys` VALUES ('806', '183', 'Aya');
INSERT INTO `geo_brgys` VALUES ('807', '183', 'Balas');
INSERT INTO `geo_brgys` VALUES ('808', '183', 'Banga');
INSERT INTO `geo_brgys` VALUES ('809', '183', 'Buco');
INSERT INTO `geo_brgys` VALUES ('810', '183', 'Caloocan');
INSERT INTO `geo_brgys` VALUES ('811', '183', 'Leynes');
INSERT INTO `geo_brgys` VALUES ('812', '183', 'Miranda');
INSERT INTO `geo_brgys` VALUES ('813', '183', 'Poblacion 1');
INSERT INTO `geo_brgys` VALUES ('814', '183', 'Poblacion 2');
INSERT INTO `geo_brgys` VALUES ('815', '183', 'Poblacion 3');
INSERT INTO `geo_brgys` VALUES ('816', '183', 'Poblacion 4');
INSERT INTO `geo_brgys` VALUES ('817', '183', 'Poblacion 5');
INSERT INTO `geo_brgys` VALUES ('818', '183', 'Poblacion 6');
INSERT INTO `geo_brgys` VALUES ('819', '183', 'Poblacion 7');
INSERT INTO `geo_brgys` VALUES ('820', '183', 'Poblacion 8');
INSERT INTO `geo_brgys` VALUES ('821', '183', 'Quiling');
INSERT INTO `geo_brgys` VALUES ('822', '183', 'Sampaloc');
INSERT INTO `geo_brgys` VALUES ('823', '183', 'San Guillermo');
INSERT INTO `geo_brgys` VALUES ('824', '183', 'Santa Maria');
INSERT INTO `geo_brgys` VALUES ('825', '183', 'Tranca');
INSERT INTO `geo_brgys` VALUES ('826', '183', 'Tumaway');
INSERT INTO `geo_brgys` VALUES ('827', '1143', 'Baloc');
INSERT INTO `geo_brgys` VALUES ('828', '1143', 'Buasao');
INSERT INTO `geo_brgys` VALUES ('829', '1143', 'Burgos');
INSERT INTO `geo_brgys` VALUES ('830', '1143', 'Cabugao');
INSERT INTO `geo_brgys` VALUES ('831', '1143', 'Casulucan');
INSERT INTO `geo_brgys` VALUES ('832', '1143', 'Comitang');
INSERT INTO `geo_brgys` VALUES ('833', '1143', 'Concepcion');
INSERT INTO `geo_brgys` VALUES ('834', '1143', 'Dolores');
INSERT INTO `geo_brgys` VALUES ('835', '1143', 'General Luna');
INSERT INTO `geo_brgys` VALUES ('836', '1143', 'Hulo');
INSERT INTO `geo_brgys` VALUES ('837', '1143', 'Ilog Baliwag');
INSERT INTO `geo_brgys` VALUES ('838', '1143', 'Mabini');
INSERT INTO `geo_brgys` VALUES ('839', '1143', 'Malasin');
INSERT INTO `geo_brgys` VALUES ('840', '1143', 'Malayantoc');
INSERT INTO `geo_brgys` VALUES ('841', '1143', 'Mambarao');
INSERT INTO `geo_brgys` VALUES ('842', '1143', 'Poblacion');
INSERT INTO `geo_brgys` VALUES ('843', '1143', 'Pook Malaya');
INSERT INTO `geo_brgys` VALUES ('844', '1143', 'Pulong Buli');
INSERT INTO `geo_brgys` VALUES ('845', '1143', 'Sagaba');
INSERT INTO `geo_brgys` VALUES ('846', '1143', 'San Agustin');
INSERT INTO `geo_brgys` VALUES ('847', '1143', 'San Fabian');
INSERT INTO `geo_brgys` VALUES ('848', '1143', 'San Francisco');
INSERT INTO `geo_brgys` VALUES ('849', '1143', 'San Pascual');
INSERT INTO `geo_brgys` VALUES ('850', '1143', 'Sta. Rita');
INSERT INTO `geo_brgys` VALUES ('851', '1143', 'Sto. Rosario');
INSERT INTO `geo_brgys` VALUES ('852', '1146', 'Batitang');
INSERT INTO `geo_brgys` VALUES ('853', '1146', 'Carmen');
INSERT INTO `geo_brgys` VALUES ('854', '1146', 'Concepcion');
INSERT INTO `geo_brgys` VALUES ('855', '1146', 'Del Pilar');
INSERT INTO `geo_brgys` VALUES ('856', '1146', 'General Luna');
INSERT INTO `geo_brgys` VALUES ('857', '1146', 'H. Romero');
INSERT INTO `geo_brgys` VALUES ('858', '1146', 'Macarse');
INSERT INTO `geo_brgys` VALUES ('859', '1146', 'Manaul');
INSERT INTO `geo_brgys` VALUES ('860', '1146', 'Mayamot');
INSERT INTO `geo_brgys` VALUES ('861', '1146', 'Pantoc');
INSERT INTO `geo_brgys` VALUES ('862', '1146', 'San Vicente (Poblacion)');
INSERT INTO `geo_brgys` VALUES ('863', '1146', 'San Isidro');
INSERT INTO `geo_brgys` VALUES ('864', '1146', 'San Rafael');
INSERT INTO `geo_brgys` VALUES ('865', '1146', 'Sta. Cruz');
INSERT INTO `geo_brgys` VALUES ('866', '1146', 'Sta. Lucia Old');
INSERT INTO `geo_brgys` VALUES ('867', '1146', 'Sta. Lucia Young');
INSERT INTO `geo_brgys` VALUES ('868', '1146', 'Sto. Rosario Old');
INSERT INTO `geo_brgys` VALUES ('869', '1146', 'Sto. Rosario Young');
INSERT INTO `geo_brgys` VALUES ('870', '1146', 'Valeriana');
INSERT INTO `geo_brgys` VALUES ('871', '1528', 'Alfonso');
INSERT INTO `geo_brgys` VALUES ('872', '1528', 'Balutu');
INSERT INTO `geo_brgys` VALUES ('873', '1528', 'Cafe');
INSERT INTO `geo_brgys` VALUES ('874', '1528', 'Calius Gueco');
INSERT INTO `geo_brgys` VALUES ('875', '1528', 'Caluluan');
INSERT INTO `geo_brgys` VALUES ('876', '1528', 'Castillo');
INSERT INTO `geo_brgys` VALUES ('877', '1528', 'Corazon de Jesus');
INSERT INTO `geo_brgys` VALUES ('878', '1528', 'Culatingan');
INSERT INTO `geo_brgys` VALUES ('879', '1528', 'Dungan');
INSERT INTO `geo_brgys` VALUES ('880', '1528', 'Dutung-A-Matas');
INSERT INTO `geo_brgys` VALUES ('881', '1528', 'Lilibangan');
INSERT INTO `geo_brgys` VALUES ('882', '1528', 'Mabilog');
INSERT INTO `geo_brgys` VALUES ('883', '1528', 'Magao');
INSERT INTO `geo_brgys` VALUES ('884', '1528', 'Malupa');
INSERT INTO `geo_brgys` VALUES ('885', '1528', 'Minane');
INSERT INTO `geo_brgys` VALUES ('886', '1528', 'Panalicsican');
INSERT INTO `geo_brgys` VALUES ('887', '1528', 'Pando');
INSERT INTO `geo_brgys` VALUES ('888', '1528', 'Parang');
INSERT INTO `geo_brgys` VALUES ('889', '1528', 'Parulung');
INSERT INTO `geo_brgys` VALUES ('890', '1528', 'Pitabunan');
INSERT INTO `geo_brgys` VALUES ('891', '1528', 'San Agustin (Murcia)');
INSERT INTO `geo_brgys` VALUES ('892', '1528', 'San Antonio');
INSERT INTO `geo_brgys` VALUES ('893', '1528', 'San Bartolome');
INSERT INTO `geo_brgys` VALUES ('894', '1528', 'San Francisco');
INSERT INTO `geo_brgys` VALUES ('895', '1528', 'San Isidro (Almendras)');
INSERT INTO `geo_brgys` VALUES ('896', '1528', 'San Jose (Pob)');
INSERT INTO `geo_brgys` VALUES ('897', '1528', 'San Juan (Castro)');
INSERT INTO `geo_brgys` VALUES ('898', '1528', 'San Martin');
INSERT INTO `geo_brgys` VALUES ('899', '1528', 'San Nicolas (Pob)');
INSERT INTO `geo_brgys` VALUES ('900', '1528', 'San Nicolas Balas');
INSERT INTO `geo_brgys` VALUES ('901', '1528', 'Santo Niño');
INSERT INTO `geo_brgys` VALUES ('902', '1528', 'Santa Cruz');
INSERT INTO `geo_brgys` VALUES ('903', '1528', 'Santa Maria');
INSERT INTO `geo_brgys` VALUES ('904', '1528', 'Santa Monica');
INSERT INTO `geo_brgys` VALUES ('905', '1528', 'Santa Rita');
INSERT INTO `geo_brgys` VALUES ('906', '1528', 'Santa Rosa');
INSERT INTO `geo_brgys` VALUES ('907', '1528', 'Santiago');
INSERT INTO `geo_brgys` VALUES ('908', '1528', 'Santo Cristo');
INSERT INTO `geo_brgys` VALUES ('909', '1528', 'Santo Rosario (Magunting)');
INSERT INTO `geo_brgys` VALUES ('910', '1528', 'San Vicente (Calius/Cobra)');
INSERT INTO `geo_brgys` VALUES ('911', '1528', 'Talimundoc Marimla (Mapaco)');
INSERT INTO `geo_brgys` VALUES ('912', '1528', 'Talimundoc San Miguel');
INSERT INTO `geo_brgys` VALUES ('913', '1528', 'Telabanca');
INSERT INTO `geo_brgys` VALUES ('914', '1528', 'Tinang');

-- ----------------------------
-- Table structure for geo_cities
-- ----------------------------
DROP TABLE IF EXISTS `geo_cities`;
CREATE TABLE `geo_cities` (
  `id` int(7) NOT NULL AUTO_INCREMENT,
  `province_id` int(7) NOT NULL,
  `name` varchar(225) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `id` (`id`) USING BTREE,
  KEY `province_id` (`province_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1637 DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of geo_cities
-- ----------------------------
INSERT INTO `geo_cities` VALUES ('1', '1', 'Bangued');
INSERT INTO `geo_cities` VALUES ('2', '1', 'Boliney');
INSERT INTO `geo_cities` VALUES ('3', '1', 'Bucay');
INSERT INTO `geo_cities` VALUES ('4', '1', 'Bucloc');
INSERT INTO `geo_cities` VALUES ('5', '1', 'Daguioman');
INSERT INTO `geo_cities` VALUES ('6', '1', 'Danglas');
INSERT INTO `geo_cities` VALUES ('7', '1', 'Dolores');
INSERT INTO `geo_cities` VALUES ('8', '1', 'La Paz');
INSERT INTO `geo_cities` VALUES ('9', '1', 'Lacub');
INSERT INTO `geo_cities` VALUES ('10', '1', 'Lagangilang');
INSERT INTO `geo_cities` VALUES ('11', '1', 'Lagayan');
INSERT INTO `geo_cities` VALUES ('12', '1', 'Langiden');
INSERT INTO `geo_cities` VALUES ('13', '1', 'Licuan-Baay');
INSERT INTO `geo_cities` VALUES ('14', '1', 'Luba');
INSERT INTO `geo_cities` VALUES ('15', '1', 'Malibcong');
INSERT INTO `geo_cities` VALUES ('16', '1', 'Manabo');
INSERT INTO `geo_cities` VALUES ('17', '1', 'Peñarrubia');
INSERT INTO `geo_cities` VALUES ('18', '1', 'Pidigan');
INSERT INTO `geo_cities` VALUES ('19', '1', 'Pilar');
INSERT INTO `geo_cities` VALUES ('20', '1', 'Sallapadan');
INSERT INTO `geo_cities` VALUES ('21', '1', 'San Isidro');
INSERT INTO `geo_cities` VALUES ('22', '1', 'San Juan');
INSERT INTO `geo_cities` VALUES ('23', '1', 'San Quintin');
INSERT INTO `geo_cities` VALUES ('24', '1', 'Tayum');
INSERT INTO `geo_cities` VALUES ('25', '1', 'Tineg');
INSERT INTO `geo_cities` VALUES ('26', '1', 'Tubo');
INSERT INTO `geo_cities` VALUES ('27', '1', 'Villaviciosa');
INSERT INTO `geo_cities` VALUES ('28', '2', 'Butuan City');
INSERT INTO `geo_cities` VALUES ('29', '2', 'Buenavista');
INSERT INTO `geo_cities` VALUES ('30', '2', 'Cabadbaran');
INSERT INTO `geo_cities` VALUES ('31', '2', 'Carmen');
INSERT INTO `geo_cities` VALUES ('32', '2', 'Jabonga');
INSERT INTO `geo_cities` VALUES ('33', '2', 'Kitcharao');
INSERT INTO `geo_cities` VALUES ('34', '2', 'Las Nieves');
INSERT INTO `geo_cities` VALUES ('35', '2', 'Magallanes');
INSERT INTO `geo_cities` VALUES ('36', '2', 'Nasipit');
INSERT INTO `geo_cities` VALUES ('37', '2', 'Remedios T. Romualdez');
INSERT INTO `geo_cities` VALUES ('38', '2', 'Santiago');
INSERT INTO `geo_cities` VALUES ('39', '2', 'Tubay');
INSERT INTO `geo_cities` VALUES ('40', '3', 'Bayugan');
INSERT INTO `geo_cities` VALUES ('41', '3', 'Bunawan');
INSERT INTO `geo_cities` VALUES ('42', '3', 'Esperanza');
INSERT INTO `geo_cities` VALUES ('43', '3', 'La Paz');
INSERT INTO `geo_cities` VALUES ('44', '3', 'Loreto');
INSERT INTO `geo_cities` VALUES ('45', '3', 'Prosperidad');
INSERT INTO `geo_cities` VALUES ('46', '3', 'Rosario');
INSERT INTO `geo_cities` VALUES ('47', '3', 'San Francisco');
INSERT INTO `geo_cities` VALUES ('48', '3', 'San Luis');
INSERT INTO `geo_cities` VALUES ('49', '3', 'Santa Josefa');
INSERT INTO `geo_cities` VALUES ('50', '3', 'Sibagat');
INSERT INTO `geo_cities` VALUES ('51', '3', 'Talacogon');
INSERT INTO `geo_cities` VALUES ('52', '3', 'Trento');
INSERT INTO `geo_cities` VALUES ('53', '3', 'Veruela');
INSERT INTO `geo_cities` VALUES ('54', '4', 'Altavas');
INSERT INTO `geo_cities` VALUES ('55', '4', 'Balete');
INSERT INTO `geo_cities` VALUES ('56', '4', 'Banga');
INSERT INTO `geo_cities` VALUES ('57', '4', 'Batan');
INSERT INTO `geo_cities` VALUES ('58', '4', 'Buruanga');
INSERT INTO `geo_cities` VALUES ('59', '4', 'Ibajay');
INSERT INTO `geo_cities` VALUES ('60', '4', 'Kalibo');
INSERT INTO `geo_cities` VALUES ('61', '4', 'Lezo');
INSERT INTO `geo_cities` VALUES ('62', '4', 'Libacao');
INSERT INTO `geo_cities` VALUES ('63', '4', 'Madalag');
INSERT INTO `geo_cities` VALUES ('64', '4', 'Makato');
INSERT INTO `geo_cities` VALUES ('65', '4', 'Malay');
INSERT INTO `geo_cities` VALUES ('66', '4', 'Malinao');
INSERT INTO `geo_cities` VALUES ('67', '4', 'Nabas');
INSERT INTO `geo_cities` VALUES ('68', '4', 'New Washington');
INSERT INTO `geo_cities` VALUES ('69', '4', 'Numancia');
INSERT INTO `geo_cities` VALUES ('70', '4', 'Tangalan');
INSERT INTO `geo_cities` VALUES ('71', '5', 'Legazpi City');
INSERT INTO `geo_cities` VALUES ('72', '5', 'Ligao City');
INSERT INTO `geo_cities` VALUES ('73', '5', 'Tabaco City');
INSERT INTO `geo_cities` VALUES ('74', '5', 'Bacacay');
INSERT INTO `geo_cities` VALUES ('75', '5', 'Camalig');
INSERT INTO `geo_cities` VALUES ('76', '5', 'Daraga');
INSERT INTO `geo_cities` VALUES ('77', '5', 'Guinobatan');
INSERT INTO `geo_cities` VALUES ('78', '5', 'Jovellar');
INSERT INTO `geo_cities` VALUES ('79', '5', 'Libon');
INSERT INTO `geo_cities` VALUES ('80', '5', 'Malilipot');
INSERT INTO `geo_cities` VALUES ('81', '5', 'Malinao');
INSERT INTO `geo_cities` VALUES ('82', '5', 'Manito');
INSERT INTO `geo_cities` VALUES ('83', '5', 'Oas');
INSERT INTO `geo_cities` VALUES ('84', '5', 'Pio Duran');
INSERT INTO `geo_cities` VALUES ('85', '5', 'Polangui');
INSERT INTO `geo_cities` VALUES ('86', '5', 'Rapu-Rapu');
INSERT INTO `geo_cities` VALUES ('87', '5', 'Santo Domingo');
INSERT INTO `geo_cities` VALUES ('88', '5', 'Tiwi');
INSERT INTO `geo_cities` VALUES ('89', '6', 'Anini-y');
INSERT INTO `geo_cities` VALUES ('90', '6', 'Barbaza');
INSERT INTO `geo_cities` VALUES ('91', '6', 'Belison');
INSERT INTO `geo_cities` VALUES ('92', '6', 'Bugasong');
INSERT INTO `geo_cities` VALUES ('93', '6', 'Caluya');
INSERT INTO `geo_cities` VALUES ('94', '6', 'Culasi');
INSERT INTO `geo_cities` VALUES ('95', '6', 'Hamtic');
INSERT INTO `geo_cities` VALUES ('96', '6', 'Laua-an');
INSERT INTO `geo_cities` VALUES ('97', '6', 'Libertad');
INSERT INTO `geo_cities` VALUES ('98', '6', 'Pandan');
INSERT INTO `geo_cities` VALUES ('99', '6', 'Patnongon');
INSERT INTO `geo_cities` VALUES ('100', '6', 'San Jose');
INSERT INTO `geo_cities` VALUES ('101', '6', 'San Remigio');
INSERT INTO `geo_cities` VALUES ('102', '6', 'Sebaste');
INSERT INTO `geo_cities` VALUES ('103', '6', 'Sibalom');
INSERT INTO `geo_cities` VALUES ('104', '6', 'Tibiao');
INSERT INTO `geo_cities` VALUES ('105', '6', 'Tobias Fornier');
INSERT INTO `geo_cities` VALUES ('106', '6', 'Valderrama');
INSERT INTO `geo_cities` VALUES ('107', '7', 'Calanasan');
INSERT INTO `geo_cities` VALUES ('108', '7', 'Conner');
INSERT INTO `geo_cities` VALUES ('109', '7', 'Flora');
INSERT INTO `geo_cities` VALUES ('110', '7', 'Kabugao');
INSERT INTO `geo_cities` VALUES ('111', '7', 'Luna');
INSERT INTO `geo_cities` VALUES ('112', '7', 'Pudtol');
INSERT INTO `geo_cities` VALUES ('113', '7', 'Santa Marcela');
INSERT INTO `geo_cities` VALUES ('114', '8', 'Baler');
INSERT INTO `geo_cities` VALUES ('115', '8', 'Casiguran');
INSERT INTO `geo_cities` VALUES ('116', '8', 'Dilasag');
INSERT INTO `geo_cities` VALUES ('117', '8', 'Dinalungan');
INSERT INTO `geo_cities` VALUES ('118', '8', 'Dingalan');
INSERT INTO `geo_cities` VALUES ('119', '8', 'Dipaculao');
INSERT INTO `geo_cities` VALUES ('120', '8', 'Maria Aurora');
INSERT INTO `geo_cities` VALUES ('121', '8', 'San Luis');
INSERT INTO `geo_cities` VALUES ('122', '9', 'Isabela City');
INSERT INTO `geo_cities` VALUES ('123', '9', 'Akbar');
INSERT INTO `geo_cities` VALUES ('124', '9', 'Al-Barka');
INSERT INTO `geo_cities` VALUES ('125', '9', 'Hadji Mohammad Ajul');
INSERT INTO `geo_cities` VALUES ('126', '9', 'Hadji Muhtamad');
INSERT INTO `geo_cities` VALUES ('127', '9', 'Lamitan');
INSERT INTO `geo_cities` VALUES ('128', '9', 'Lantawan');
INSERT INTO `geo_cities` VALUES ('129', '9', 'Maluso');
INSERT INTO `geo_cities` VALUES ('130', '9', 'Sumisip');
INSERT INTO `geo_cities` VALUES ('131', '9', 'Tabuan-Lasa');
INSERT INTO `geo_cities` VALUES ('132', '9', 'Tipo-Tipo');
INSERT INTO `geo_cities` VALUES ('133', '9', 'Tuburan');
INSERT INTO `geo_cities` VALUES ('134', '9', 'Ungkaya Pukan');
INSERT INTO `geo_cities` VALUES ('135', '10', 'Balanga City');
INSERT INTO `geo_cities` VALUES ('136', '10', 'Abucay');
INSERT INTO `geo_cities` VALUES ('137', '10', 'Bagac');
INSERT INTO `geo_cities` VALUES ('138', '10', 'Dinalupihan');
INSERT INTO `geo_cities` VALUES ('139', '10', 'Hermosa');
INSERT INTO `geo_cities` VALUES ('140', '10', 'Limay');
INSERT INTO `geo_cities` VALUES ('141', '10', 'Mariveles');
INSERT INTO `geo_cities` VALUES ('142', '10', 'Morong');
INSERT INTO `geo_cities` VALUES ('143', '10', 'Orani');
INSERT INTO `geo_cities` VALUES ('144', '10', 'Orion');
INSERT INTO `geo_cities` VALUES ('145', '10', 'Pilar');
INSERT INTO `geo_cities` VALUES ('146', '10', 'Samal');
INSERT INTO `geo_cities` VALUES ('147', '11', 'Basco');
INSERT INTO `geo_cities` VALUES ('148', '11', 'Itbayat');
INSERT INTO `geo_cities` VALUES ('149', '11', 'Ivana');
INSERT INTO `geo_cities` VALUES ('150', '11', 'Mahatao');
INSERT INTO `geo_cities` VALUES ('151', '11', 'Sabtang');
INSERT INTO `geo_cities` VALUES ('152', '11', 'Uyugan');
INSERT INTO `geo_cities` VALUES ('153', '12', 'Batangas City');
INSERT INTO `geo_cities` VALUES ('154', '12', 'Lipa City');
INSERT INTO `geo_cities` VALUES ('155', '12', 'Tanauan City');
INSERT INTO `geo_cities` VALUES ('156', '12', 'Agoncillo');
INSERT INTO `geo_cities` VALUES ('157', '12', 'Alitagtag');
INSERT INTO `geo_cities` VALUES ('158', '12', 'Balayan');
INSERT INTO `geo_cities` VALUES ('159', '12', 'Balete');
INSERT INTO `geo_cities` VALUES ('160', '12', 'Bauan');
INSERT INTO `geo_cities` VALUES ('161', '12', 'Calaca');
INSERT INTO `geo_cities` VALUES ('162', '12', 'Calatagan');
INSERT INTO `geo_cities` VALUES ('163', '12', 'Cuenca');
INSERT INTO `geo_cities` VALUES ('164', '12', 'Ibaan');
INSERT INTO `geo_cities` VALUES ('165', '12', 'Laurel');
INSERT INTO `geo_cities` VALUES ('166', '12', 'Lemery');
INSERT INTO `geo_cities` VALUES ('167', '12', 'Lian');
INSERT INTO `geo_cities` VALUES ('168', '12', 'Lobo');
INSERT INTO `geo_cities` VALUES ('169', '12', 'Mabini');
INSERT INTO `geo_cities` VALUES ('170', '12', 'Malvar');
INSERT INTO `geo_cities` VALUES ('171', '12', 'Mataas na Kahoy');
INSERT INTO `geo_cities` VALUES ('172', '12', 'Nasugbu');
INSERT INTO `geo_cities` VALUES ('173', '12', 'Padre Garcia');
INSERT INTO `geo_cities` VALUES ('174', '12', 'Rosario');
INSERT INTO `geo_cities` VALUES ('175', '12', 'San Jose');
INSERT INTO `geo_cities` VALUES ('176', '12', 'San Juan');
INSERT INTO `geo_cities` VALUES ('177', '12', 'San Luis');
INSERT INTO `geo_cities` VALUES ('178', '12', 'San Nicolas');
INSERT INTO `geo_cities` VALUES ('179', '12', 'San Pascual');
INSERT INTO `geo_cities` VALUES ('180', '12', 'Santa Teresita');
INSERT INTO `geo_cities` VALUES ('181', '12', 'Santo Tomas');
INSERT INTO `geo_cities` VALUES ('182', '12', 'Taal');
INSERT INTO `geo_cities` VALUES ('183', '12', 'Talisay');
INSERT INTO `geo_cities` VALUES ('184', '12', 'Taysan');
INSERT INTO `geo_cities` VALUES ('185', '12', 'Tingloy');
INSERT INTO `geo_cities` VALUES ('186', '12', 'Tuy');
INSERT INTO `geo_cities` VALUES ('187', '13', 'Baguio City');
INSERT INTO `geo_cities` VALUES ('188', '13', 'Atok');
INSERT INTO `geo_cities` VALUES ('189', '13', 'Bakun');
INSERT INTO `geo_cities` VALUES ('190', '13', 'Bokod');
INSERT INTO `geo_cities` VALUES ('191', '13', 'Buguias');
INSERT INTO `geo_cities` VALUES ('192', '13', 'Itogon');
INSERT INTO `geo_cities` VALUES ('193', '13', 'Kabayan');
INSERT INTO `geo_cities` VALUES ('194', '13', 'Kapangan');
INSERT INTO `geo_cities` VALUES ('195', '13', 'Kibungan');
INSERT INTO `geo_cities` VALUES ('196', '13', 'La Trinidad');
INSERT INTO `geo_cities` VALUES ('197', '13', 'Mankayan');
INSERT INTO `geo_cities` VALUES ('198', '13', 'Sablan');
INSERT INTO `geo_cities` VALUES ('199', '13', 'Tuba');
INSERT INTO `geo_cities` VALUES ('200', '13', 'Tublay');
INSERT INTO `geo_cities` VALUES ('201', '14', 'Almeria');
INSERT INTO `geo_cities` VALUES ('202', '14', 'Biliran');
INSERT INTO `geo_cities` VALUES ('203', '14', 'Cabucgayan');
INSERT INTO `geo_cities` VALUES ('204', '14', 'Caibiran');
INSERT INTO `geo_cities` VALUES ('205', '14', 'Culaba');
INSERT INTO `geo_cities` VALUES ('206', '14', 'Kawayan');
INSERT INTO `geo_cities` VALUES ('207', '14', 'Maripipi');
INSERT INTO `geo_cities` VALUES ('208', '14', 'Naval');
INSERT INTO `geo_cities` VALUES ('209', '15', 'Tagbilaran City');
INSERT INTO `geo_cities` VALUES ('210', '15', 'Alburquerque');
INSERT INTO `geo_cities` VALUES ('211', '15', 'Alicia');
INSERT INTO `geo_cities` VALUES ('212', '15', 'Anda');
INSERT INTO `geo_cities` VALUES ('213', '15', 'Antequera');
INSERT INTO `geo_cities` VALUES ('214', '15', 'Baclayon');
INSERT INTO `geo_cities` VALUES ('215', '15', 'Balilihan');
INSERT INTO `geo_cities` VALUES ('216', '15', 'Batuan');
INSERT INTO `geo_cities` VALUES ('217', '15', 'Bien Unido');
INSERT INTO `geo_cities` VALUES ('218', '15', 'Bilar');
INSERT INTO `geo_cities` VALUES ('219', '15', 'Buenavista');
INSERT INTO `geo_cities` VALUES ('220', '15', 'Calape');
INSERT INTO `geo_cities` VALUES ('221', '15', 'Candijay');
INSERT INTO `geo_cities` VALUES ('222', '15', 'Carmen');
INSERT INTO `geo_cities` VALUES ('223', '15', 'Catigbian');
INSERT INTO `geo_cities` VALUES ('224', '15', 'Clarin');
INSERT INTO `geo_cities` VALUES ('225', '15', 'Corella');
INSERT INTO `geo_cities` VALUES ('226', '15', 'Cortes');
INSERT INTO `geo_cities` VALUES ('227', '15', 'Dagohoy');
INSERT INTO `geo_cities` VALUES ('228', '15', 'Danao');
INSERT INTO `geo_cities` VALUES ('229', '15', 'Dauis');
INSERT INTO `geo_cities` VALUES ('230', '15', 'Dimiao');
INSERT INTO `geo_cities` VALUES ('231', '15', 'Duero');
INSERT INTO `geo_cities` VALUES ('232', '15', 'Garcia Hernandez');
INSERT INTO `geo_cities` VALUES ('233', '15', 'Getafe');
INSERT INTO `geo_cities` VALUES ('234', '15', 'Guindulman');
INSERT INTO `geo_cities` VALUES ('235', '15', 'Inabanga');
INSERT INTO `geo_cities` VALUES ('236', '15', 'Jagna');
INSERT INTO `geo_cities` VALUES ('237', '15', 'Lila');
INSERT INTO `geo_cities` VALUES ('238', '15', 'Loay');
INSERT INTO `geo_cities` VALUES ('239', '15', 'Loboc');
INSERT INTO `geo_cities` VALUES ('240', '15', 'Loon');
INSERT INTO `geo_cities` VALUES ('241', '15', 'Mabini');
INSERT INTO `geo_cities` VALUES ('242', '15', 'Maribojoc');
INSERT INTO `geo_cities` VALUES ('243', '15', 'Panglao');
INSERT INTO `geo_cities` VALUES ('244', '15', 'Pilar');
INSERT INTO `geo_cities` VALUES ('245', '15', 'President Carlos P. Garcia');
INSERT INTO `geo_cities` VALUES ('246', '15', 'Sagbayan');
INSERT INTO `geo_cities` VALUES ('247', '15', 'San Isidro');
INSERT INTO `geo_cities` VALUES ('248', '15', 'San Miguel');
INSERT INTO `geo_cities` VALUES ('249', '15', 'Sevilla');
INSERT INTO `geo_cities` VALUES ('250', '15', 'Sierra Bullones');
INSERT INTO `geo_cities` VALUES ('251', '15', 'Sikatuna');
INSERT INTO `geo_cities` VALUES ('252', '15', 'Talibon');
INSERT INTO `geo_cities` VALUES ('253', '15', 'Trinidad');
INSERT INTO `geo_cities` VALUES ('254', '15', 'Tubigon');
INSERT INTO `geo_cities` VALUES ('255', '15', 'Ubay');
INSERT INTO `geo_cities` VALUES ('256', '15', 'Valencia');
INSERT INTO `geo_cities` VALUES ('257', '16', 'Malaybalay City');
INSERT INTO `geo_cities` VALUES ('258', '16', 'Valencia City');
INSERT INTO `geo_cities` VALUES ('259', '16', 'Baungon');
INSERT INTO `geo_cities` VALUES ('260', '16', 'Cabanglasan');
INSERT INTO `geo_cities` VALUES ('261', '16', 'Damulog');
INSERT INTO `geo_cities` VALUES ('262', '16', 'Dangcagan');
INSERT INTO `geo_cities` VALUES ('263', '16', 'Don Carlos');
INSERT INTO `geo_cities` VALUES ('264', '16', 'Impasug-ong');
INSERT INTO `geo_cities` VALUES ('265', '16', 'Kadingilan');
INSERT INTO `geo_cities` VALUES ('266', '16', 'Kalilangan');
INSERT INTO `geo_cities` VALUES ('267', '16', 'Kibawe');
INSERT INTO `geo_cities` VALUES ('268', '16', 'Kitaotao');
INSERT INTO `geo_cities` VALUES ('269', '16', 'Lantapan');
INSERT INTO `geo_cities` VALUES ('270', '16', 'Libona');
INSERT INTO `geo_cities` VALUES ('271', '16', 'Malitbog');
INSERT INTO `geo_cities` VALUES ('272', '16', 'Manolo Fortich');
INSERT INTO `geo_cities` VALUES ('273', '16', 'Maramag');
INSERT INTO `geo_cities` VALUES ('274', '16', 'Pangantucan');
INSERT INTO `geo_cities` VALUES ('275', '16', 'Quezon');
INSERT INTO `geo_cities` VALUES ('276', '16', 'San Fernando');
INSERT INTO `geo_cities` VALUES ('277', '16', 'Sumilao');
INSERT INTO `geo_cities` VALUES ('278', '16', 'Talakag');
INSERT INTO `geo_cities` VALUES ('279', '17', 'Malolos City');
INSERT INTO `geo_cities` VALUES ('280', '17', 'Meycauayan City');
INSERT INTO `geo_cities` VALUES ('281', '17', 'San Jose del Monte City');
INSERT INTO `geo_cities` VALUES ('282', '17', 'Angat');
INSERT INTO `geo_cities` VALUES ('283', '17', 'Balagtas');
INSERT INTO `geo_cities` VALUES ('284', '17', 'Baliuag');
INSERT INTO `geo_cities` VALUES ('285', '17', 'Bocaue');
INSERT INTO `geo_cities` VALUES ('286', '17', 'Bulakan');
INSERT INTO `geo_cities` VALUES ('287', '17', 'Bustos');
INSERT INTO `geo_cities` VALUES ('288', '17', 'Calumpit');
INSERT INTO `geo_cities` VALUES ('289', '17', 'Doña Remedios Trinidad');
INSERT INTO `geo_cities` VALUES ('290', '17', 'Guiguinto');
INSERT INTO `geo_cities` VALUES ('291', '17', 'Hagonoy');
INSERT INTO `geo_cities` VALUES ('292', '17', 'Marilao');
INSERT INTO `geo_cities` VALUES ('293', '17', 'Norzagaray');
INSERT INTO `geo_cities` VALUES ('294', '17', 'Obando');
INSERT INTO `geo_cities` VALUES ('295', '17', 'Pandi');
INSERT INTO `geo_cities` VALUES ('296', '17', 'Paombong');
INSERT INTO `geo_cities` VALUES ('297', '17', 'Plaridel');
INSERT INTO `geo_cities` VALUES ('298', '17', 'Pulilan');
INSERT INTO `geo_cities` VALUES ('299', '17', 'San Ildefonso');
INSERT INTO `geo_cities` VALUES ('300', '17', 'San Miguel');
INSERT INTO `geo_cities` VALUES ('301', '17', 'San Rafael');
INSERT INTO `geo_cities` VALUES ('302', '17', 'Santa Maria');
INSERT INTO `geo_cities` VALUES ('303', '18', 'Tuguegarao City');
INSERT INTO `geo_cities` VALUES ('304', '18', 'Abulug');
INSERT INTO `geo_cities` VALUES ('305', '18', 'Alcala');
INSERT INTO `geo_cities` VALUES ('306', '18', 'Allacapan');
INSERT INTO `geo_cities` VALUES ('307', '18', 'Amulung');
INSERT INTO `geo_cities` VALUES ('308', '18', 'Aparri');
INSERT INTO `geo_cities` VALUES ('309', '18', 'Baggao');
INSERT INTO `geo_cities` VALUES ('310', '18', 'Ballesteros');
INSERT INTO `geo_cities` VALUES ('311', '18', 'Buguey');
INSERT INTO `geo_cities` VALUES ('312', '18', 'Calayan');
INSERT INTO `geo_cities` VALUES ('313', '18', 'Camalaniugan');
INSERT INTO `geo_cities` VALUES ('314', '18', 'Claveria');
INSERT INTO `geo_cities` VALUES ('315', '18', 'Enrile');
INSERT INTO `geo_cities` VALUES ('316', '18', 'Gattaran');
INSERT INTO `geo_cities` VALUES ('317', '18', 'Gonzaga');
INSERT INTO `geo_cities` VALUES ('318', '18', 'Iguig');
INSERT INTO `geo_cities` VALUES ('319', '18', 'Lal-lo');
INSERT INTO `geo_cities` VALUES ('320', '18', 'Lasam');
INSERT INTO `geo_cities` VALUES ('321', '18', 'Pamplona');
INSERT INTO `geo_cities` VALUES ('322', '18', 'Peñablanca');
INSERT INTO `geo_cities` VALUES ('323', '18', 'Piat');
INSERT INTO `geo_cities` VALUES ('324', '18', 'Rizal');
INSERT INTO `geo_cities` VALUES ('325', '18', 'Sanchez-Mira');
INSERT INTO `geo_cities` VALUES ('326', '18', 'Santa Ana');
INSERT INTO `geo_cities` VALUES ('327', '18', 'Santa Praxedes');
INSERT INTO `geo_cities` VALUES ('328', '18', 'Santa Teresita');
INSERT INTO `geo_cities` VALUES ('329', '18', 'Santo Niño');
INSERT INTO `geo_cities` VALUES ('330', '18', 'Solana');
INSERT INTO `geo_cities` VALUES ('331', '18', 'Tuao');
INSERT INTO `geo_cities` VALUES ('332', '19', 'Basud');
INSERT INTO `geo_cities` VALUES ('333', '19', 'Capalonga');
INSERT INTO `geo_cities` VALUES ('334', '19', 'Daet');
INSERT INTO `geo_cities` VALUES ('335', '19', 'Jose Panganiban');
INSERT INTO `geo_cities` VALUES ('336', '19', 'Labo');
INSERT INTO `geo_cities` VALUES ('337', '19', 'Mercedes');
INSERT INTO `geo_cities` VALUES ('338', '19', 'Paracale');
INSERT INTO `geo_cities` VALUES ('339', '19', 'San Lorenzo Ruiz');
INSERT INTO `geo_cities` VALUES ('340', '19', 'San Vicente');
INSERT INTO `geo_cities` VALUES ('341', '19', 'Santa Elena');
INSERT INTO `geo_cities` VALUES ('342', '19', 'Talisay');
INSERT INTO `geo_cities` VALUES ('343', '19', 'Vinzons');
INSERT INTO `geo_cities` VALUES ('344', '20', 'Iriga City');
INSERT INTO `geo_cities` VALUES ('345', '20', 'Naga City');
INSERT INTO `geo_cities` VALUES ('346', '20', 'Baao');
INSERT INTO `geo_cities` VALUES ('347', '20', 'Balatan');
INSERT INTO `geo_cities` VALUES ('348', '20', 'Bato');
INSERT INTO `geo_cities` VALUES ('349', '20', 'Bombon');
INSERT INTO `geo_cities` VALUES ('350', '20', 'Buhi');
INSERT INTO `geo_cities` VALUES ('351', '20', 'Bula');
INSERT INTO `geo_cities` VALUES ('352', '20', 'Cabusao');
INSERT INTO `geo_cities` VALUES ('353', '20', 'Calabanga');
INSERT INTO `geo_cities` VALUES ('354', '20', 'Camaligan');
INSERT INTO `geo_cities` VALUES ('355', '20', 'Canaman');
INSERT INTO `geo_cities` VALUES ('356', '20', 'Caramoan');
INSERT INTO `geo_cities` VALUES ('357', '20', 'Del Gallego');
INSERT INTO `geo_cities` VALUES ('358', '20', 'Gainza');
INSERT INTO `geo_cities` VALUES ('359', '20', 'Garchitorena');
INSERT INTO `geo_cities` VALUES ('360', '20', 'Goa');
INSERT INTO `geo_cities` VALUES ('361', '20', 'Lagonoy');
INSERT INTO `geo_cities` VALUES ('362', '20', 'Libmanan');
INSERT INTO `geo_cities` VALUES ('363', '20', 'Lupi');
INSERT INTO `geo_cities` VALUES ('364', '20', 'Magarao');
INSERT INTO `geo_cities` VALUES ('365', '20', 'Milaor');
INSERT INTO `geo_cities` VALUES ('366', '20', 'Minalabac');
INSERT INTO `geo_cities` VALUES ('367', '20', 'Nabua');
INSERT INTO `geo_cities` VALUES ('368', '20', 'Ocampo');
INSERT INTO `geo_cities` VALUES ('369', '20', 'Pamplona');
INSERT INTO `geo_cities` VALUES ('370', '20', 'Pasacao');
INSERT INTO `geo_cities` VALUES ('371', '20', 'Pili');
INSERT INTO `geo_cities` VALUES ('372', '20', 'Presentacion');
INSERT INTO `geo_cities` VALUES ('373', '20', 'Ragay');
INSERT INTO `geo_cities` VALUES ('374', '20', 'Sagñay');
INSERT INTO `geo_cities` VALUES ('375', '20', 'San Fernando');
INSERT INTO `geo_cities` VALUES ('376', '20', 'San Jose');
INSERT INTO `geo_cities` VALUES ('377', '20', 'Sipocot');
INSERT INTO `geo_cities` VALUES ('378', '20', 'Siruma');
INSERT INTO `geo_cities` VALUES ('379', '20', 'Tigaon');
INSERT INTO `geo_cities` VALUES ('380', '20', 'Tinambac');
INSERT INTO `geo_cities` VALUES ('381', '21', 'Catarman');
INSERT INTO `geo_cities` VALUES ('382', '21', 'Guinsiliban');
INSERT INTO `geo_cities` VALUES ('383', '21', 'Mahinog');
INSERT INTO `geo_cities` VALUES ('384', '21', 'Mambajao');
INSERT INTO `geo_cities` VALUES ('385', '21', 'Sagay');
INSERT INTO `geo_cities` VALUES ('386', '22', 'Roxas City');
INSERT INTO `geo_cities` VALUES ('387', '22', 'Cuartero');
INSERT INTO `geo_cities` VALUES ('388', '22', 'Dao');
INSERT INTO `geo_cities` VALUES ('389', '22', 'Dumalag');
INSERT INTO `geo_cities` VALUES ('390', '22', 'Dumarao');
INSERT INTO `geo_cities` VALUES ('391', '22', 'Ivisan');
INSERT INTO `geo_cities` VALUES ('392', '22', 'Jamindan');
INSERT INTO `geo_cities` VALUES ('393', '22', 'Ma-ayon');
INSERT INTO `geo_cities` VALUES ('394', '22', 'Mambusao');
INSERT INTO `geo_cities` VALUES ('395', '22', 'Panay');
INSERT INTO `geo_cities` VALUES ('396', '22', 'Panitan');
INSERT INTO `geo_cities` VALUES ('397', '22', 'Pilar');
INSERT INTO `geo_cities` VALUES ('398', '22', 'Pontevedra');
INSERT INTO `geo_cities` VALUES ('399', '22', 'President Roxas');
INSERT INTO `geo_cities` VALUES ('400', '22', 'Sapi-an');
INSERT INTO `geo_cities` VALUES ('401', '22', 'Sigma');
INSERT INTO `geo_cities` VALUES ('402', '22', 'Tapaz');
INSERT INTO `geo_cities` VALUES ('403', '23', 'Bagamanoc');
INSERT INTO `geo_cities` VALUES ('404', '23', 'Baras');
INSERT INTO `geo_cities` VALUES ('405', '23', 'Bato');
INSERT INTO `geo_cities` VALUES ('406', '23', 'Caramoran');
INSERT INTO `geo_cities` VALUES ('407', '23', 'Gigmoto');
INSERT INTO `geo_cities` VALUES ('408', '23', 'Pandan');
INSERT INTO `geo_cities` VALUES ('409', '23', 'Panganiban');
INSERT INTO `geo_cities` VALUES ('410', '23', 'San Andres');
INSERT INTO `geo_cities` VALUES ('411', '23', 'San Miguel');
INSERT INTO `geo_cities` VALUES ('412', '23', 'Viga');
INSERT INTO `geo_cities` VALUES ('413', '23', 'Virac');
INSERT INTO `geo_cities` VALUES ('414', '24', 'Cavite City');
INSERT INTO `geo_cities` VALUES ('415', '24', 'Dasmariñas City');
INSERT INTO `geo_cities` VALUES ('416', '24', 'Tagaytay City');
INSERT INTO `geo_cities` VALUES ('417', '24', 'Trece Martires City');
INSERT INTO `geo_cities` VALUES ('418', '24', 'Alfonso');
INSERT INTO `geo_cities` VALUES ('419', '24', 'Amadeo');
INSERT INTO `geo_cities` VALUES ('420', '24', 'Bacoor');
INSERT INTO `geo_cities` VALUES ('421', '24', 'Carmona');
INSERT INTO `geo_cities` VALUES ('422', '24', 'General Mariano Alvarez');
INSERT INTO `geo_cities` VALUES ('423', '24', 'General Emilio Aguinaldo');
INSERT INTO `geo_cities` VALUES ('424', '24', 'General Trias');
INSERT INTO `geo_cities` VALUES ('425', '24', 'Imus');
INSERT INTO `geo_cities` VALUES ('426', '24', 'Indang');
INSERT INTO `geo_cities` VALUES ('427', '24', 'Kawit');
INSERT INTO `geo_cities` VALUES ('428', '24', 'Magallanes');
INSERT INTO `geo_cities` VALUES ('429', '24', 'Maragondon');
INSERT INTO `geo_cities` VALUES ('430', '24', 'Mendez');
INSERT INTO `geo_cities` VALUES ('431', '24', 'Naic');
INSERT INTO `geo_cities` VALUES ('432', '24', 'Noveleta');
INSERT INTO `geo_cities` VALUES ('433', '24', 'Rosario');
INSERT INTO `geo_cities` VALUES ('434', '24', 'Silang');
INSERT INTO `geo_cities` VALUES ('435', '24', 'Tanza');
INSERT INTO `geo_cities` VALUES ('436', '24', 'Ternate');
INSERT INTO `geo_cities` VALUES ('437', '25', 'Bogo City');
INSERT INTO `geo_cities` VALUES ('438', '25', 'Cebu City');
INSERT INTO `geo_cities` VALUES ('439', '25', 'Carcar City');
INSERT INTO `geo_cities` VALUES ('440', '25', 'Danao City');
INSERT INTO `geo_cities` VALUES ('441', '25', 'Lapu-Lapu City');
INSERT INTO `geo_cities` VALUES ('442', '25', 'Mandaue City');
INSERT INTO `geo_cities` VALUES ('443', '25', 'Naga City');
INSERT INTO `geo_cities` VALUES ('444', '25', 'Talisay City');
INSERT INTO `geo_cities` VALUES ('445', '25', 'Toledo City');
INSERT INTO `geo_cities` VALUES ('446', '25', 'Alcantara');
INSERT INTO `geo_cities` VALUES ('447', '25', 'Alcoy');
INSERT INTO `geo_cities` VALUES ('448', '25', 'Alegria');
INSERT INTO `geo_cities` VALUES ('449', '25', 'Aloguinsan');
INSERT INTO `geo_cities` VALUES ('450', '25', 'Argao');
INSERT INTO `geo_cities` VALUES ('451', '25', 'Asturias');
INSERT INTO `geo_cities` VALUES ('452', '25', 'Badian');
INSERT INTO `geo_cities` VALUES ('453', '25', 'Balamban');
INSERT INTO `geo_cities` VALUES ('454', '25', 'Bantayan');
INSERT INTO `geo_cities` VALUES ('455', '25', 'Barili');
INSERT INTO `geo_cities` VALUES ('456', '25', 'Boljoon');
INSERT INTO `geo_cities` VALUES ('457', '25', 'Borbon');
INSERT INTO `geo_cities` VALUES ('458', '25', 'Carmen');
INSERT INTO `geo_cities` VALUES ('459', '25', 'Catmon');
INSERT INTO `geo_cities` VALUES ('460', '25', 'Compostela');
INSERT INTO `geo_cities` VALUES ('461', '25', 'Consolacion');
INSERT INTO `geo_cities` VALUES ('462', '25', 'Cordoba');
INSERT INTO `geo_cities` VALUES ('463', '25', 'Daanbantayan');
INSERT INTO `geo_cities` VALUES ('464', '25', 'Dalaguete');
INSERT INTO `geo_cities` VALUES ('465', '25', 'Dumanjug');
INSERT INTO `geo_cities` VALUES ('466', '25', 'Ginatilan');
INSERT INTO `geo_cities` VALUES ('467', '25', 'Liloan');
INSERT INTO `geo_cities` VALUES ('468', '25', 'Madridejos');
INSERT INTO `geo_cities` VALUES ('469', '25', 'Malabuyoc');
INSERT INTO `geo_cities` VALUES ('470', '25', 'Medellin');
INSERT INTO `geo_cities` VALUES ('471', '25', 'Minglanilla');
INSERT INTO `geo_cities` VALUES ('472', '25', 'Moalboal');
INSERT INTO `geo_cities` VALUES ('473', '25', 'Oslob');
INSERT INTO `geo_cities` VALUES ('474', '25', 'Pilar');
INSERT INTO `geo_cities` VALUES ('475', '25', 'Pinamungahan');
INSERT INTO `geo_cities` VALUES ('476', '25', 'Poro');
INSERT INTO `geo_cities` VALUES ('477', '25', 'Ronda');
INSERT INTO `geo_cities` VALUES ('478', '25', 'Samboan');
INSERT INTO `geo_cities` VALUES ('479', '25', 'San Fernando');
INSERT INTO `geo_cities` VALUES ('480', '25', 'San Francisco');
INSERT INTO `geo_cities` VALUES ('481', '25', 'San Remigio');
INSERT INTO `geo_cities` VALUES ('482', '25', 'Santa Fe');
INSERT INTO `geo_cities` VALUES ('483', '25', 'Santander');
INSERT INTO `geo_cities` VALUES ('484', '25', 'Sibonga');
INSERT INTO `geo_cities` VALUES ('485', '25', 'Sogod');
INSERT INTO `geo_cities` VALUES ('486', '25', 'Tabogon');
INSERT INTO `geo_cities` VALUES ('487', '25', 'Tabuelan');
INSERT INTO `geo_cities` VALUES ('488', '25', 'Tuburan');
INSERT INTO `geo_cities` VALUES ('489', '25', 'Tudela');
INSERT INTO `geo_cities` VALUES ('490', '26', 'Compostela');
INSERT INTO `geo_cities` VALUES ('491', '26', 'Laak');
INSERT INTO `geo_cities` VALUES ('492', '26', 'Mabini');
INSERT INTO `geo_cities` VALUES ('493', '26', 'Maco');
INSERT INTO `geo_cities` VALUES ('494', '26', 'Maragusan');
INSERT INTO `geo_cities` VALUES ('495', '26', 'Mawab');
INSERT INTO `geo_cities` VALUES ('496', '26', 'Monkayo');
INSERT INTO `geo_cities` VALUES ('497', '26', 'Montevista');
INSERT INTO `geo_cities` VALUES ('498', '26', 'Nabunturan');
INSERT INTO `geo_cities` VALUES ('499', '26', 'New Bataan');
INSERT INTO `geo_cities` VALUES ('500', '26', 'Pantukan');
INSERT INTO `geo_cities` VALUES ('501', '27', 'Kidapawan City');
INSERT INTO `geo_cities` VALUES ('502', '27', 'Alamada');
INSERT INTO `geo_cities` VALUES ('503', '27', 'Aleosan');
INSERT INTO `geo_cities` VALUES ('504', '27', 'Antipas');
INSERT INTO `geo_cities` VALUES ('505', '27', 'Arakan');
INSERT INTO `geo_cities` VALUES ('506', '27', 'Banisilan');
INSERT INTO `geo_cities` VALUES ('507', '27', 'Carmen');
INSERT INTO `geo_cities` VALUES ('508', '27', 'Kabacan');
INSERT INTO `geo_cities` VALUES ('509', '27', 'Libungan');
INSERT INTO `geo_cities` VALUES ('510', '27', 'M\'lang');
INSERT INTO `geo_cities` VALUES ('511', '27', 'Magpet');
INSERT INTO `geo_cities` VALUES ('512', '27', 'Makilala');
INSERT INTO `geo_cities` VALUES ('513', '27', 'Matalam');
INSERT INTO `geo_cities` VALUES ('514', '27', 'Midsayap');
INSERT INTO `geo_cities` VALUES ('515', '27', 'Pigkawayan');
INSERT INTO `geo_cities` VALUES ('516', '27', 'Pikit');
INSERT INTO `geo_cities` VALUES ('517', '27', 'President Roxas');
INSERT INTO `geo_cities` VALUES ('518', '27', 'Tulunan');
INSERT INTO `geo_cities` VALUES ('519', '28', 'Panabo City');
INSERT INTO `geo_cities` VALUES ('520', '28', 'Island Garden City of Samal');
INSERT INTO `geo_cities` VALUES ('521', '28', 'Tagum City');
INSERT INTO `geo_cities` VALUES ('522', '28', 'Asuncion');
INSERT INTO `geo_cities` VALUES ('523', '28', 'Braulio E. Dujali');
INSERT INTO `geo_cities` VALUES ('524', '28', 'Carmen');
INSERT INTO `geo_cities` VALUES ('525', '28', 'Kapalong');
INSERT INTO `geo_cities` VALUES ('526', '28', 'New Corella');
INSERT INTO `geo_cities` VALUES ('527', '28', 'San Isidro');
INSERT INTO `geo_cities` VALUES ('528', '28', 'Santo Tomas');
INSERT INTO `geo_cities` VALUES ('529', '28', 'Talaingod');
INSERT INTO `geo_cities` VALUES ('530', '29', 'Davao City');
INSERT INTO `geo_cities` VALUES ('531', '29', 'Digos City');
INSERT INTO `geo_cities` VALUES ('532', '29', 'Bansalan');
INSERT INTO `geo_cities` VALUES ('533', '29', 'Don Marcelino');
INSERT INTO `geo_cities` VALUES ('534', '29', 'Hagonoy');
INSERT INTO `geo_cities` VALUES ('535', '29', 'Jose Abad Santos');
INSERT INTO `geo_cities` VALUES ('536', '29', 'Kiblawan');
INSERT INTO `geo_cities` VALUES ('537', '29', 'Magsaysay');
INSERT INTO `geo_cities` VALUES ('538', '29', 'Malalag');
INSERT INTO `geo_cities` VALUES ('539', '29', 'Malita');
INSERT INTO `geo_cities` VALUES ('540', '29', 'Matanao');
INSERT INTO `geo_cities` VALUES ('541', '29', 'Padada');
INSERT INTO `geo_cities` VALUES ('542', '29', 'Santa Cruz');
INSERT INTO `geo_cities` VALUES ('543', '29', 'Santa Maria');
INSERT INTO `geo_cities` VALUES ('544', '29', 'Sarangani');
INSERT INTO `geo_cities` VALUES ('545', '29', 'Sulop');
INSERT INTO `geo_cities` VALUES ('546', '30', 'Mati City');
INSERT INTO `geo_cities` VALUES ('547', '30', 'Baganga');
INSERT INTO `geo_cities` VALUES ('548', '30', 'Banaybanay');
INSERT INTO `geo_cities` VALUES ('549', '30', 'Boston');
INSERT INTO `geo_cities` VALUES ('550', '30', 'Caraga');
INSERT INTO `geo_cities` VALUES ('551', '30', 'Cateel');
INSERT INTO `geo_cities` VALUES ('552', '30', 'Governor Generoso');
INSERT INTO `geo_cities` VALUES ('553', '30', 'Lupon');
INSERT INTO `geo_cities` VALUES ('554', '30', 'Manay');
INSERT INTO `geo_cities` VALUES ('555', '30', 'San Isidro');
INSERT INTO `geo_cities` VALUES ('556', '30', 'Tarragona');
INSERT INTO `geo_cities` VALUES ('557', '31', 'Arteche');
INSERT INTO `geo_cities` VALUES ('558', '31', 'Balangiga');
INSERT INTO `geo_cities` VALUES ('559', '31', 'Balangkayan');
INSERT INTO `geo_cities` VALUES ('560', '31', 'Borongan');
INSERT INTO `geo_cities` VALUES ('561', '31', 'Can-avid');
INSERT INTO `geo_cities` VALUES ('562', '31', 'Dolores');
INSERT INTO `geo_cities` VALUES ('563', '31', 'General MacArthur');
INSERT INTO `geo_cities` VALUES ('564', '31', 'Giporlos');
INSERT INTO `geo_cities` VALUES ('565', '31', 'Guiuan');
INSERT INTO `geo_cities` VALUES ('566', '31', 'Hernani');
INSERT INTO `geo_cities` VALUES ('567', '31', 'Jipapad');
INSERT INTO `geo_cities` VALUES ('568', '31', 'Lawaan');
INSERT INTO `geo_cities` VALUES ('569', '31', 'Llorente');
INSERT INTO `geo_cities` VALUES ('570', '31', 'Maslog');
INSERT INTO `geo_cities` VALUES ('571', '31', 'Maydolong');
INSERT INTO `geo_cities` VALUES ('572', '31', 'Mercedes');
INSERT INTO `geo_cities` VALUES ('573', '31', 'Oras');
INSERT INTO `geo_cities` VALUES ('574', '31', 'Quinapondan');
INSERT INTO `geo_cities` VALUES ('575', '31', 'Salcedo');
INSERT INTO `geo_cities` VALUES ('576', '31', 'San Julian');
INSERT INTO `geo_cities` VALUES ('577', '31', 'San Policarpo');
INSERT INTO `geo_cities` VALUES ('578', '31', 'Sulat');
INSERT INTO `geo_cities` VALUES ('579', '31', 'Taft');
INSERT INTO `geo_cities` VALUES ('580', '32', 'Buenavista');
INSERT INTO `geo_cities` VALUES ('581', '32', 'Jordan');
INSERT INTO `geo_cities` VALUES ('582', '32', 'Nueva Valencia');
INSERT INTO `geo_cities` VALUES ('583', '32', 'San Lorenzo');
INSERT INTO `geo_cities` VALUES ('584', '32', 'Sibunag');
INSERT INTO `geo_cities` VALUES ('585', '33', 'Aguinaldo');
INSERT INTO `geo_cities` VALUES ('586', '33', 'Alfonso Lista');
INSERT INTO `geo_cities` VALUES ('587', '33', 'Asipulo');
INSERT INTO `geo_cities` VALUES ('588', '33', 'Banaue');
INSERT INTO `geo_cities` VALUES ('589', '33', 'Hingyon');
INSERT INTO `geo_cities` VALUES ('590', '33', 'Hungduan');
INSERT INTO `geo_cities` VALUES ('591', '33', 'Kiangan');
INSERT INTO `geo_cities` VALUES ('592', '33', 'Lagawe');
INSERT INTO `geo_cities` VALUES ('593', '33', 'Lamut');
INSERT INTO `geo_cities` VALUES ('594', '33', 'Mayoyao');
INSERT INTO `geo_cities` VALUES ('595', '33', 'Tinoc');
INSERT INTO `geo_cities` VALUES ('596', '34', 'Batac City');
INSERT INTO `geo_cities` VALUES ('597', '34', 'Laoag City');
INSERT INTO `geo_cities` VALUES ('598', '34', 'Adams');
INSERT INTO `geo_cities` VALUES ('599', '34', 'Bacarra');
INSERT INTO `geo_cities` VALUES ('600', '34', 'Badoc');
INSERT INTO `geo_cities` VALUES ('601', '34', 'Bangui');
INSERT INTO `geo_cities` VALUES ('602', '34', 'Banna');
INSERT INTO `geo_cities` VALUES ('603', '34', 'Burgos');
INSERT INTO `geo_cities` VALUES ('604', '34', 'Carasi');
INSERT INTO `geo_cities` VALUES ('605', '34', 'Currimao');
INSERT INTO `geo_cities` VALUES ('606', '34', 'Dingras');
INSERT INTO `geo_cities` VALUES ('607', '34', 'Dumalneg');
INSERT INTO `geo_cities` VALUES ('608', '34', 'Marcos');
INSERT INTO `geo_cities` VALUES ('609', '34', 'Nueva Era');
INSERT INTO `geo_cities` VALUES ('610', '34', 'Pagudpud');
INSERT INTO `geo_cities` VALUES ('611', '34', 'Paoay');
INSERT INTO `geo_cities` VALUES ('612', '34', 'Pasuquin');
INSERT INTO `geo_cities` VALUES ('613', '34', 'Piddig');
INSERT INTO `geo_cities` VALUES ('614', '34', 'Pinili');
INSERT INTO `geo_cities` VALUES ('615', '34', 'San Nicolas');
INSERT INTO `geo_cities` VALUES ('616', '34', 'Sarrat');
INSERT INTO `geo_cities` VALUES ('617', '34', 'Solsona');
INSERT INTO `geo_cities` VALUES ('618', '34', 'Vintar');
INSERT INTO `geo_cities` VALUES ('619', '35', 'Candon City');
INSERT INTO `geo_cities` VALUES ('620', '35', 'Vigan City');
INSERT INTO `geo_cities` VALUES ('621', '35', 'Alilem');
INSERT INTO `geo_cities` VALUES ('622', '35', 'Banayoyo');
INSERT INTO `geo_cities` VALUES ('623', '35', 'Bantay');
INSERT INTO `geo_cities` VALUES ('624', '35', 'Burgos');
INSERT INTO `geo_cities` VALUES ('625', '35', 'Cabugao');
INSERT INTO `geo_cities` VALUES ('626', '35', 'Caoayan');
INSERT INTO `geo_cities` VALUES ('627', '35', 'Cervantes');
INSERT INTO `geo_cities` VALUES ('628', '35', 'Galimuyod');
INSERT INTO `geo_cities` VALUES ('629', '35', 'Gregorio Del Pilar');
INSERT INTO `geo_cities` VALUES ('630', '35', 'Lidlidda');
INSERT INTO `geo_cities` VALUES ('631', '35', 'Magsingal');
INSERT INTO `geo_cities` VALUES ('632', '35', 'Nagbukel');
INSERT INTO `geo_cities` VALUES ('633', '35', 'Narvacan');
INSERT INTO `geo_cities` VALUES ('634', '35', 'Quirino');
INSERT INTO `geo_cities` VALUES ('635', '35', 'Salcedo');
INSERT INTO `geo_cities` VALUES ('636', '35', 'San Emilio');
INSERT INTO `geo_cities` VALUES ('637', '35', 'San Esteban');
INSERT INTO `geo_cities` VALUES ('638', '35', 'San Ildefonso');
INSERT INTO `geo_cities` VALUES ('639', '35', 'San Juan');
INSERT INTO `geo_cities` VALUES ('640', '35', 'San Vicente');
INSERT INTO `geo_cities` VALUES ('641', '35', 'Santa');
INSERT INTO `geo_cities` VALUES ('642', '35', 'Santa Catalina');
INSERT INTO `geo_cities` VALUES ('643', '35', 'Santa Cruz');
INSERT INTO `geo_cities` VALUES ('644', '35', 'Santa Lucia');
INSERT INTO `geo_cities` VALUES ('645', '35', 'Santa Maria');
INSERT INTO `geo_cities` VALUES ('646', '35', 'Santiago');
INSERT INTO `geo_cities` VALUES ('647', '35', 'Santo Domingo');
INSERT INTO `geo_cities` VALUES ('648', '35', 'Sigay');
INSERT INTO `geo_cities` VALUES ('649', '35', 'Sinait');
INSERT INTO `geo_cities` VALUES ('650', '35', 'Sugpon');
INSERT INTO `geo_cities` VALUES ('651', '35', 'Suyo');
INSERT INTO `geo_cities` VALUES ('652', '35', 'Tagudin');
INSERT INTO `geo_cities` VALUES ('653', '36', 'Iloilo City');
INSERT INTO `geo_cities` VALUES ('654', '36', 'Passi City');
INSERT INTO `geo_cities` VALUES ('655', '36', 'Ajuy');
INSERT INTO `geo_cities` VALUES ('656', '36', 'Alimodian');
INSERT INTO `geo_cities` VALUES ('657', '36', 'Anilao');
INSERT INTO `geo_cities` VALUES ('658', '36', 'Badiangan');
INSERT INTO `geo_cities` VALUES ('659', '36', 'Balasan');
INSERT INTO `geo_cities` VALUES ('660', '36', 'Banate');
INSERT INTO `geo_cities` VALUES ('661', '36', 'Barotac Nuevo');
INSERT INTO `geo_cities` VALUES ('662', '36', 'Barotac Viejo');
INSERT INTO `geo_cities` VALUES ('663', '36', 'Batad');
INSERT INTO `geo_cities` VALUES ('664', '36', 'Bingawan');
INSERT INTO `geo_cities` VALUES ('665', '36', 'Cabatuan');
INSERT INTO `geo_cities` VALUES ('666', '36', 'Calinog');
INSERT INTO `geo_cities` VALUES ('667', '36', 'Carles');
INSERT INTO `geo_cities` VALUES ('668', '36', 'Concepcion');
INSERT INTO `geo_cities` VALUES ('669', '36', 'Dingle');
INSERT INTO `geo_cities` VALUES ('670', '36', 'Dueñas');
INSERT INTO `geo_cities` VALUES ('671', '36', 'Dumangas');
INSERT INTO `geo_cities` VALUES ('672', '36', 'Estancia');
INSERT INTO `geo_cities` VALUES ('673', '36', 'Guimbal');
INSERT INTO `geo_cities` VALUES ('674', '36', 'Igbaras');
INSERT INTO `geo_cities` VALUES ('675', '36', 'Janiuay');
INSERT INTO `geo_cities` VALUES ('676', '36', 'Lambunao');
INSERT INTO `geo_cities` VALUES ('677', '36', 'Leganes');
INSERT INTO `geo_cities` VALUES ('678', '36', 'Lemery');
INSERT INTO `geo_cities` VALUES ('679', '36', 'Leon');
INSERT INTO `geo_cities` VALUES ('680', '36', 'Maasin');
INSERT INTO `geo_cities` VALUES ('681', '36', 'Miagao');
INSERT INTO `geo_cities` VALUES ('682', '36', 'Mina');
INSERT INTO `geo_cities` VALUES ('683', '36', 'New Lucena');
INSERT INTO `geo_cities` VALUES ('684', '36', 'Oton');
INSERT INTO `geo_cities` VALUES ('685', '36', 'Pavia');
INSERT INTO `geo_cities` VALUES ('686', '36', 'Pototan');
INSERT INTO `geo_cities` VALUES ('687', '36', 'San Dionisio');
INSERT INTO `geo_cities` VALUES ('688', '36', 'San Enrique');
INSERT INTO `geo_cities` VALUES ('689', '36', 'San Joaquin');
INSERT INTO `geo_cities` VALUES ('690', '36', 'San Miguel');
INSERT INTO `geo_cities` VALUES ('691', '36', 'San Rafael');
INSERT INTO `geo_cities` VALUES ('692', '36', 'Santa Barbara');
INSERT INTO `geo_cities` VALUES ('693', '36', 'Sara');
INSERT INTO `geo_cities` VALUES ('694', '36', 'Tigbauan');
INSERT INTO `geo_cities` VALUES ('695', '36', 'Tubungan');
INSERT INTO `geo_cities` VALUES ('696', '36', 'Zarraga');
INSERT INTO `geo_cities` VALUES ('697', '37', 'Cauayan City');
INSERT INTO `geo_cities` VALUES ('698', '37', 'Santiago City');
INSERT INTO `geo_cities` VALUES ('699', '37', 'Alicia');
INSERT INTO `geo_cities` VALUES ('700', '37', 'Angadanan');
INSERT INTO `geo_cities` VALUES ('701', '37', 'Aurora');
INSERT INTO `geo_cities` VALUES ('702', '37', 'Benito Soliven');
INSERT INTO `geo_cities` VALUES ('703', '37', 'Burgos');
INSERT INTO `geo_cities` VALUES ('704', '37', 'Cabagan');
INSERT INTO `geo_cities` VALUES ('705', '37', 'Cabatuan');
INSERT INTO `geo_cities` VALUES ('706', '37', 'Cordon');
INSERT INTO `geo_cities` VALUES ('707', '37', 'Delfin Albano');
INSERT INTO `geo_cities` VALUES ('708', '37', 'Dinapigue');
INSERT INTO `geo_cities` VALUES ('709', '37', 'Divilacan');
INSERT INTO `geo_cities` VALUES ('710', '37', 'Echague');
INSERT INTO `geo_cities` VALUES ('711', '37', 'Gamu');
INSERT INTO `geo_cities` VALUES ('712', '37', 'Ilagan');
INSERT INTO `geo_cities` VALUES ('713', '37', 'Jones');
INSERT INTO `geo_cities` VALUES ('714', '37', 'Luna');
INSERT INTO `geo_cities` VALUES ('715', '37', 'Maconacon');
INSERT INTO `geo_cities` VALUES ('716', '37', 'Mallig');
INSERT INTO `geo_cities` VALUES ('717', '37', 'Naguilian');
INSERT INTO `geo_cities` VALUES ('718', '37', 'Palanan');
INSERT INTO `geo_cities` VALUES ('719', '37', 'Quezon');
INSERT INTO `geo_cities` VALUES ('720', '37', 'Quirino');
INSERT INTO `geo_cities` VALUES ('721', '37', 'Ramon');
INSERT INTO `geo_cities` VALUES ('722', '37', 'Reina Mercedes');
INSERT INTO `geo_cities` VALUES ('723', '37', 'Roxas');
INSERT INTO `geo_cities` VALUES ('724', '37', 'San Agustin');
INSERT INTO `geo_cities` VALUES ('725', '37', 'San Guillermo');
INSERT INTO `geo_cities` VALUES ('726', '37', 'San Isidro');
INSERT INTO `geo_cities` VALUES ('727', '37', 'San Manuel');
INSERT INTO `geo_cities` VALUES ('728', '37', 'San Mariano');
INSERT INTO `geo_cities` VALUES ('729', '37', 'San Mateo');
INSERT INTO `geo_cities` VALUES ('730', '37', 'San Pablo');
INSERT INTO `geo_cities` VALUES ('731', '37', 'Santa Maria');
INSERT INTO `geo_cities` VALUES ('732', '37', 'Santo Tomas');
INSERT INTO `geo_cities` VALUES ('733', '37', 'Tumauini');
INSERT INTO `geo_cities` VALUES ('734', '38', 'Tabuk');
INSERT INTO `geo_cities` VALUES ('735', '38', 'Balbalan');
INSERT INTO `geo_cities` VALUES ('736', '38', 'Lubuagan');
INSERT INTO `geo_cities` VALUES ('737', '38', 'Pasil');
INSERT INTO `geo_cities` VALUES ('738', '38', 'Pinukpuk');
INSERT INTO `geo_cities` VALUES ('739', '38', 'Rizal');
INSERT INTO `geo_cities` VALUES ('740', '38', 'Tanudan');
INSERT INTO `geo_cities` VALUES ('741', '38', 'Tinglayan');
INSERT INTO `geo_cities` VALUES ('742', '39', 'San Fernando City');
INSERT INTO `geo_cities` VALUES ('743', '39', 'Agoo');
INSERT INTO `geo_cities` VALUES ('744', '39', 'Aringay');
INSERT INTO `geo_cities` VALUES ('745', '39', 'Bacnotan');
INSERT INTO `geo_cities` VALUES ('746', '39', 'Bagulin');
INSERT INTO `geo_cities` VALUES ('747', '39', 'Balaoan');
INSERT INTO `geo_cities` VALUES ('748', '39', 'Bangar');
INSERT INTO `geo_cities` VALUES ('749', '39', 'Bauang');
INSERT INTO `geo_cities` VALUES ('750', '39', 'Burgos');
INSERT INTO `geo_cities` VALUES ('751', '39', 'Caba');
INSERT INTO `geo_cities` VALUES ('752', '39', 'Luna');
INSERT INTO `geo_cities` VALUES ('753', '39', 'Naguilian');
INSERT INTO `geo_cities` VALUES ('754', '39', 'Pugo');
INSERT INTO `geo_cities` VALUES ('755', '39', 'Rosario');
INSERT INTO `geo_cities` VALUES ('756', '39', 'San Gabriel');
INSERT INTO `geo_cities` VALUES ('757', '39', 'San Juan');
INSERT INTO `geo_cities` VALUES ('758', '39', 'Santo Tomas');
INSERT INTO `geo_cities` VALUES ('759', '39', 'Santol');
INSERT INTO `geo_cities` VALUES ('760', '39', 'Sudipen');
INSERT INTO `geo_cities` VALUES ('761', '39', 'Tubao');
INSERT INTO `geo_cities` VALUES ('762', '40', 'Biñan City');
INSERT INTO `geo_cities` VALUES ('763', '40', 'Calamba City');
INSERT INTO `geo_cities` VALUES ('764', '40', 'San Pablo City');
INSERT INTO `geo_cities` VALUES ('765', '40', 'Santa Rosa City');
INSERT INTO `geo_cities` VALUES ('766', '40', 'Alaminos');
INSERT INTO `geo_cities` VALUES ('767', '40', 'Bay');
INSERT INTO `geo_cities` VALUES ('768', '40', 'Cabuyao');
INSERT INTO `geo_cities` VALUES ('769', '40', 'Calauan');
INSERT INTO `geo_cities` VALUES ('770', '40', 'Cavinti');
INSERT INTO `geo_cities` VALUES ('771', '40', 'Famy');
INSERT INTO `geo_cities` VALUES ('772', '40', 'Kalayaan');
INSERT INTO `geo_cities` VALUES ('773', '40', 'Liliw');
INSERT INTO `geo_cities` VALUES ('774', '40', 'Los Baños');
INSERT INTO `geo_cities` VALUES ('775', '40', 'Luisiana');
INSERT INTO `geo_cities` VALUES ('776', '40', 'Lumban');
INSERT INTO `geo_cities` VALUES ('777', '40', 'Mabitac');
INSERT INTO `geo_cities` VALUES ('778', '40', 'Magdalena');
INSERT INTO `geo_cities` VALUES ('779', '40', 'Majayjay');
INSERT INTO `geo_cities` VALUES ('780', '40', 'Nagcarlan');
INSERT INTO `geo_cities` VALUES ('781', '40', 'Paete');
INSERT INTO `geo_cities` VALUES ('782', '40', 'Pagsanjan');
INSERT INTO `geo_cities` VALUES ('783', '40', 'Pakil');
INSERT INTO `geo_cities` VALUES ('784', '40', 'Pangil');
INSERT INTO `geo_cities` VALUES ('785', '40', 'Pila');
INSERT INTO `geo_cities` VALUES ('786', '40', 'Rizal');
INSERT INTO `geo_cities` VALUES ('787', '40', 'San Pedro');
INSERT INTO `geo_cities` VALUES ('788', '40', 'Santa Cruz');
INSERT INTO `geo_cities` VALUES ('789', '40', 'Santa Maria');
INSERT INTO `geo_cities` VALUES ('790', '40', 'Siniloan');
INSERT INTO `geo_cities` VALUES ('791', '40', 'Victoria');
INSERT INTO `geo_cities` VALUES ('792', '41', 'Iligan City');
INSERT INTO `geo_cities` VALUES ('793', '41', 'Bacolod');
INSERT INTO `geo_cities` VALUES ('794', '41', 'Baloi');
INSERT INTO `geo_cities` VALUES ('795', '41', 'Baroy');
INSERT INTO `geo_cities` VALUES ('796', '41', 'Kapatagan');
INSERT INTO `geo_cities` VALUES ('797', '41', 'Kauswagan');
INSERT INTO `geo_cities` VALUES ('798', '41', 'Kolambugan');
INSERT INTO `geo_cities` VALUES ('799', '41', 'Lala');
INSERT INTO `geo_cities` VALUES ('800', '41', 'Linamon');
INSERT INTO `geo_cities` VALUES ('801', '41', 'Magsaysay');
INSERT INTO `geo_cities` VALUES ('802', '41', 'Maigo');
INSERT INTO `geo_cities` VALUES ('803', '41', 'Matungao');
INSERT INTO `geo_cities` VALUES ('804', '41', 'Munai');
INSERT INTO `geo_cities` VALUES ('805', '41', 'Nunungan');
INSERT INTO `geo_cities` VALUES ('806', '41', 'Pantao Ragat');
INSERT INTO `geo_cities` VALUES ('807', '41', 'Pantar');
INSERT INTO `geo_cities` VALUES ('808', '41', 'Poona Piagapo');
INSERT INTO `geo_cities` VALUES ('809', '41', 'Salvador');
INSERT INTO `geo_cities` VALUES ('810', '41', 'Sapad');
INSERT INTO `geo_cities` VALUES ('811', '41', 'Sultan Naga Dimaporo');
INSERT INTO `geo_cities` VALUES ('812', '41', 'Tagoloan');
INSERT INTO `geo_cities` VALUES ('813', '41', 'Tangcal');
INSERT INTO `geo_cities` VALUES ('814', '41', 'Tubod');
INSERT INTO `geo_cities` VALUES ('815', '42', 'Marawi City');
INSERT INTO `geo_cities` VALUES ('816', '42', 'Bacolod-Kalawi');
INSERT INTO `geo_cities` VALUES ('817', '42', 'Balabagan');
INSERT INTO `geo_cities` VALUES ('818', '42', 'Balindong');
INSERT INTO `geo_cities` VALUES ('819', '42', 'Bayang');
INSERT INTO `geo_cities` VALUES ('820', '42', 'Binidayan');
INSERT INTO `geo_cities` VALUES ('821', '42', 'Buadiposo-Buntong');
INSERT INTO `geo_cities` VALUES ('822', '42', 'Bubong');
INSERT INTO `geo_cities` VALUES ('823', '42', 'Bumbaran');
INSERT INTO `geo_cities` VALUES ('824', '42', 'Butig');
INSERT INTO `geo_cities` VALUES ('825', '42', 'Calanogas');
INSERT INTO `geo_cities` VALUES ('826', '42', 'Ditsaan-Ramain');
INSERT INTO `geo_cities` VALUES ('827', '42', 'Ganassi');
INSERT INTO `geo_cities` VALUES ('828', '42', 'Kapai');
INSERT INTO `geo_cities` VALUES ('829', '42', 'Kapatagan');
INSERT INTO `geo_cities` VALUES ('830', '42', 'Lumba-Bayabao');
INSERT INTO `geo_cities` VALUES ('831', '42', 'Lumbaca-Unayan');
INSERT INTO `geo_cities` VALUES ('832', '42', 'Lumbatan');
INSERT INTO `geo_cities` VALUES ('833', '42', 'Lumbayanague');
INSERT INTO `geo_cities` VALUES ('834', '42', 'Madalum');
INSERT INTO `geo_cities` VALUES ('835', '42', 'Madamba');
INSERT INTO `geo_cities` VALUES ('836', '42', 'Maguing');
INSERT INTO `geo_cities` VALUES ('837', '42', 'Malabang');
INSERT INTO `geo_cities` VALUES ('838', '42', 'Marantao');
INSERT INTO `geo_cities` VALUES ('839', '42', 'Marogong');
INSERT INTO `geo_cities` VALUES ('840', '42', 'Masiu');
INSERT INTO `geo_cities` VALUES ('841', '42', 'Mulondo');
INSERT INTO `geo_cities` VALUES ('842', '42', 'Pagayawan');
INSERT INTO `geo_cities` VALUES ('843', '42', 'Piagapo');
INSERT INTO `geo_cities` VALUES ('844', '42', 'Poona Bayabao');
INSERT INTO `geo_cities` VALUES ('845', '42', 'Pualas');
INSERT INTO `geo_cities` VALUES ('846', '42', 'Saguiaran');
INSERT INTO `geo_cities` VALUES ('847', '42', 'Sultan Dumalondong');
INSERT INTO `geo_cities` VALUES ('848', '42', 'Picong');
INSERT INTO `geo_cities` VALUES ('849', '42', 'Tagoloan II');
INSERT INTO `geo_cities` VALUES ('850', '42', 'Tamparan');
INSERT INTO `geo_cities` VALUES ('851', '42', 'Taraka');
INSERT INTO `geo_cities` VALUES ('852', '42', 'Tubaran');
INSERT INTO `geo_cities` VALUES ('853', '42', 'Tugaya');
INSERT INTO `geo_cities` VALUES ('854', '42', 'Wao');
INSERT INTO `geo_cities` VALUES ('855', '43', 'Ormoc City');
INSERT INTO `geo_cities` VALUES ('856', '43', 'Tacloban City');
INSERT INTO `geo_cities` VALUES ('857', '43', 'Abuyog');
INSERT INTO `geo_cities` VALUES ('858', '43', 'Alangalang');
INSERT INTO `geo_cities` VALUES ('859', '43', 'Albuera');
INSERT INTO `geo_cities` VALUES ('860', '43', 'Babatngon');
INSERT INTO `geo_cities` VALUES ('861', '43', 'Barugo');
INSERT INTO `geo_cities` VALUES ('862', '43', 'Bato');
INSERT INTO `geo_cities` VALUES ('863', '43', 'Baybay');
INSERT INTO `geo_cities` VALUES ('864', '43', 'Burauen');
INSERT INTO `geo_cities` VALUES ('865', '43', 'Calubian');
INSERT INTO `geo_cities` VALUES ('866', '43', 'Capoocan');
INSERT INTO `geo_cities` VALUES ('867', '43', 'Carigara');
INSERT INTO `geo_cities` VALUES ('868', '43', 'Dagami');
INSERT INTO `geo_cities` VALUES ('869', '43', 'Dulag');
INSERT INTO `geo_cities` VALUES ('870', '43', 'Hilongos');
INSERT INTO `geo_cities` VALUES ('871', '43', 'Hindang');
INSERT INTO `geo_cities` VALUES ('872', '43', 'Inopacan');
INSERT INTO `geo_cities` VALUES ('873', '43', 'Isabel');
INSERT INTO `geo_cities` VALUES ('874', '43', 'Jaro');
INSERT INTO `geo_cities` VALUES ('875', '43', 'Javier');
INSERT INTO `geo_cities` VALUES ('876', '43', 'Julita');
INSERT INTO `geo_cities` VALUES ('877', '43', 'Kananga');
INSERT INTO `geo_cities` VALUES ('878', '43', 'La Paz');
INSERT INTO `geo_cities` VALUES ('879', '43', 'Leyte');
INSERT INTO `geo_cities` VALUES ('880', '43', 'Liloan');
INSERT INTO `geo_cities` VALUES ('881', '43', 'MacArthur');
INSERT INTO `geo_cities` VALUES ('882', '43', 'Mahaplag');
INSERT INTO `geo_cities` VALUES ('883', '43', 'Matag-ob');
INSERT INTO `geo_cities` VALUES ('884', '43', 'Matalom');
INSERT INTO `geo_cities` VALUES ('885', '43', 'Mayorga');
INSERT INTO `geo_cities` VALUES ('886', '43', 'Merida');
INSERT INTO `geo_cities` VALUES ('887', '43', 'Palo');
INSERT INTO `geo_cities` VALUES ('888', '43', 'Palompon');
INSERT INTO `geo_cities` VALUES ('889', '43', 'Pastrana');
INSERT INTO `geo_cities` VALUES ('890', '43', 'San Isidro');
INSERT INTO `geo_cities` VALUES ('891', '43', 'San Miguel');
INSERT INTO `geo_cities` VALUES ('892', '43', 'Santa Fe');
INSERT INTO `geo_cities` VALUES ('893', '43', 'Sogod');
INSERT INTO `geo_cities` VALUES ('894', '43', 'Tabango');
INSERT INTO `geo_cities` VALUES ('895', '43', 'Tabontabon');
INSERT INTO `geo_cities` VALUES ('896', '43', 'Tanauan');
INSERT INTO `geo_cities` VALUES ('897', '43', 'Tolosa');
INSERT INTO `geo_cities` VALUES ('898', '43', 'Tunga');
INSERT INTO `geo_cities` VALUES ('899', '43', 'Villaba');
INSERT INTO `geo_cities` VALUES ('900', '44', 'Cotabato City');
INSERT INTO `geo_cities` VALUES ('901', '44', 'Ampatuan');
INSERT INTO `geo_cities` VALUES ('902', '44', 'Barira');
INSERT INTO `geo_cities` VALUES ('903', '44', 'Buldon');
INSERT INTO `geo_cities` VALUES ('904', '44', 'Buluan');
INSERT INTO `geo_cities` VALUES ('905', '44', 'Datu Abdullah Sangki');
INSERT INTO `geo_cities` VALUES ('906', '44', 'Datu Anggal Midtimbang');
INSERT INTO `geo_cities` VALUES ('907', '44', 'Datu Blah T. Sinsuat');
INSERT INTO `geo_cities` VALUES ('908', '44', 'Datu Hoffer Ampatuan');
INSERT INTO `geo_cities` VALUES ('909', '44', 'Datu Montawal');
INSERT INTO `geo_cities` VALUES ('910', '44', 'Datu Odin Sinsuat');
INSERT INTO `geo_cities` VALUES ('911', '44', 'Datu Paglas');
INSERT INTO `geo_cities` VALUES ('912', '44', 'Datu Piang');
INSERT INTO `geo_cities` VALUES ('913', '44', 'Datu Salibo');
INSERT INTO `geo_cities` VALUES ('914', '44', 'Datu Saudi-Ampatuan');
INSERT INTO `geo_cities` VALUES ('915', '44', 'Datu Unsay');
INSERT INTO `geo_cities` VALUES ('916', '44', 'General Salipada K. Pendatun');
INSERT INTO `geo_cities` VALUES ('917', '44', 'Guindulungan');
INSERT INTO `geo_cities` VALUES ('918', '44', 'Kabuntalan');
INSERT INTO `geo_cities` VALUES ('919', '44', 'Mamasapano');
INSERT INTO `geo_cities` VALUES ('920', '44', 'Mangudadatu');
INSERT INTO `geo_cities` VALUES ('921', '44', 'Matanog');
INSERT INTO `geo_cities` VALUES ('922', '44', 'Northern Kabuntalan');
INSERT INTO `geo_cities` VALUES ('923', '44', 'Pagalungan');
INSERT INTO `geo_cities` VALUES ('924', '44', 'Paglat');
INSERT INTO `geo_cities` VALUES ('925', '44', 'Pandag');
INSERT INTO `geo_cities` VALUES ('926', '44', 'Parang');
INSERT INTO `geo_cities` VALUES ('927', '44', 'Rajah Buayan');
INSERT INTO `geo_cities` VALUES ('928', '44', 'Shariff Aguak');
INSERT INTO `geo_cities` VALUES ('929', '44', 'Shariff Saydona Mustapha');
INSERT INTO `geo_cities` VALUES ('930', '44', 'South Upi');
INSERT INTO `geo_cities` VALUES ('931', '44', 'Sultan Kudarat');
INSERT INTO `geo_cities` VALUES ('932', '44', 'Sultan Mastura');
INSERT INTO `geo_cities` VALUES ('933', '44', 'Sultan sa Barongis');
INSERT INTO `geo_cities` VALUES ('934', '44', 'Talayan');
INSERT INTO `geo_cities` VALUES ('935', '44', 'Talitay');
INSERT INTO `geo_cities` VALUES ('936', '44', 'Upi');
INSERT INTO `geo_cities` VALUES ('937', '45', 'Boac');
INSERT INTO `geo_cities` VALUES ('938', '45', 'Buenavista');
INSERT INTO `geo_cities` VALUES ('939', '45', 'Gasan');
INSERT INTO `geo_cities` VALUES ('940', '45', 'Mogpog');
INSERT INTO `geo_cities` VALUES ('941', '45', 'Santa Cruz');
INSERT INTO `geo_cities` VALUES ('942', '45', 'Torrijos');
INSERT INTO `geo_cities` VALUES ('943', '46', 'Masbate City');
INSERT INTO `geo_cities` VALUES ('944', '46', 'Aroroy');
INSERT INTO `geo_cities` VALUES ('945', '46', 'Baleno');
INSERT INTO `geo_cities` VALUES ('946', '46', 'Balud');
INSERT INTO `geo_cities` VALUES ('947', '46', 'Batuan');
INSERT INTO `geo_cities` VALUES ('948', '46', 'Cataingan');
INSERT INTO `geo_cities` VALUES ('949', '46', 'Cawayan');
INSERT INTO `geo_cities` VALUES ('950', '46', 'Claveria');
INSERT INTO `geo_cities` VALUES ('951', '46', 'Dimasalang');
INSERT INTO `geo_cities` VALUES ('952', '46', 'Esperanza');
INSERT INTO `geo_cities` VALUES ('953', '46', 'Mandaon');
INSERT INTO `geo_cities` VALUES ('954', '46', 'Milagros');
INSERT INTO `geo_cities` VALUES ('955', '46', 'Mobo');
INSERT INTO `geo_cities` VALUES ('956', '46', 'Monreal');
INSERT INTO `geo_cities` VALUES ('957', '46', 'Palanas');
INSERT INTO `geo_cities` VALUES ('958', '46', 'Pio V. Corpuz');
INSERT INTO `geo_cities` VALUES ('959', '46', 'Placer');
INSERT INTO `geo_cities` VALUES ('960', '46', 'San Fernando');
INSERT INTO `geo_cities` VALUES ('961', '46', 'San Jacinto');
INSERT INTO `geo_cities` VALUES ('962', '46', 'San Pascual');
INSERT INTO `geo_cities` VALUES ('963', '46', 'Uson');
INSERT INTO `geo_cities` VALUES ('964', '47', 'Caloocan');
INSERT INTO `geo_cities` VALUES ('965', '47', 'Las Piñas');
INSERT INTO `geo_cities` VALUES ('966', '47', 'Makati');
INSERT INTO `geo_cities` VALUES ('967', '47', 'Malabon');
INSERT INTO `geo_cities` VALUES ('968', '47', 'Mandaluyong');
INSERT INTO `geo_cities` VALUES ('969', '47', 'Manila');
INSERT INTO `geo_cities` VALUES ('970', '47', 'Marikina');
INSERT INTO `geo_cities` VALUES ('971', '47', 'Muntinlupa');
INSERT INTO `geo_cities` VALUES ('972', '47', 'Navotas');
INSERT INTO `geo_cities` VALUES ('973', '47', 'Parañaque');
INSERT INTO `geo_cities` VALUES ('974', '47', 'Pasay');
INSERT INTO `geo_cities` VALUES ('975', '47', 'Pasig');
INSERT INTO `geo_cities` VALUES ('976', '47', 'Quezon City');
INSERT INTO `geo_cities` VALUES ('977', '47', 'San Juan City');
INSERT INTO `geo_cities` VALUES ('978', '47', 'Taguig');
INSERT INTO `geo_cities` VALUES ('979', '47', 'Valenzuela City');
INSERT INTO `geo_cities` VALUES ('980', '47', 'Pateros');
INSERT INTO `geo_cities` VALUES ('981', '48', 'Oroquieta City');
INSERT INTO `geo_cities` VALUES ('982', '48', 'Ozamiz City');
INSERT INTO `geo_cities` VALUES ('983', '48', 'Tangub City');
INSERT INTO `geo_cities` VALUES ('984', '48', 'Aloran');
INSERT INTO `geo_cities` VALUES ('985', '48', 'Baliangao');
INSERT INTO `geo_cities` VALUES ('986', '48', 'Bonifacio');
INSERT INTO `geo_cities` VALUES ('987', '48', 'Calamba');
INSERT INTO `geo_cities` VALUES ('988', '48', 'Clarin');
INSERT INTO `geo_cities` VALUES ('989', '48', 'Concepcion');
INSERT INTO `geo_cities` VALUES ('990', '48', 'Don Victoriano Chiongbian');
INSERT INTO `geo_cities` VALUES ('991', '48', 'Jimenez');
INSERT INTO `geo_cities` VALUES ('992', '48', 'Lopez Jaena');
INSERT INTO `geo_cities` VALUES ('993', '48', 'Panaon');
INSERT INTO `geo_cities` VALUES ('994', '48', 'Plaridel');
INSERT INTO `geo_cities` VALUES ('995', '48', 'Sapang Dalaga');
INSERT INTO `geo_cities` VALUES ('996', '48', 'Sinacaban');
INSERT INTO `geo_cities` VALUES ('997', '48', 'Tudela');
INSERT INTO `geo_cities` VALUES ('998', '49', 'Cagayan de Oro');
INSERT INTO `geo_cities` VALUES ('999', '49', 'Gingoog City');
INSERT INTO `geo_cities` VALUES ('1000', '49', 'Alubijid');
INSERT INTO `geo_cities` VALUES ('1001', '49', 'Balingasag');
INSERT INTO `geo_cities` VALUES ('1002', '49', 'Balingoan');
INSERT INTO `geo_cities` VALUES ('1003', '49', 'Binuangan');
INSERT INTO `geo_cities` VALUES ('1004', '49', 'Claveria');
INSERT INTO `geo_cities` VALUES ('1005', '49', 'El Salvador');
INSERT INTO `geo_cities` VALUES ('1006', '49', 'Gitagum');
INSERT INTO `geo_cities` VALUES ('1007', '49', 'Initao');
INSERT INTO `geo_cities` VALUES ('1008', '49', 'Jasaan');
INSERT INTO `geo_cities` VALUES ('1009', '49', 'Kinoguitan');
INSERT INTO `geo_cities` VALUES ('1010', '49', 'Lagonglong');
INSERT INTO `geo_cities` VALUES ('1011', '49', 'Laguindingan');
INSERT INTO `geo_cities` VALUES ('1012', '49', 'Libertad');
INSERT INTO `geo_cities` VALUES ('1013', '49', 'Lugait');
INSERT INTO `geo_cities` VALUES ('1014', '49', 'Magsaysay');
INSERT INTO `geo_cities` VALUES ('1015', '49', 'Manticao');
INSERT INTO `geo_cities` VALUES ('1016', '49', 'Medina');
INSERT INTO `geo_cities` VALUES ('1017', '49', 'Naawan');
INSERT INTO `geo_cities` VALUES ('1018', '49', 'Opol');
INSERT INTO `geo_cities` VALUES ('1019', '49', 'Salay');
INSERT INTO `geo_cities` VALUES ('1020', '49', 'Sugbongcogon');
INSERT INTO `geo_cities` VALUES ('1021', '49', 'Tagoloan');
INSERT INTO `geo_cities` VALUES ('1022', '49', 'Talisayan');
INSERT INTO `geo_cities` VALUES ('1023', '49', 'Villanueva');
INSERT INTO `geo_cities` VALUES ('1024', '50', 'Barlig');
INSERT INTO `geo_cities` VALUES ('1025', '50', 'Bauko');
INSERT INTO `geo_cities` VALUES ('1026', '50', 'Besao');
INSERT INTO `geo_cities` VALUES ('1027', '50', 'Bontoc');
INSERT INTO `geo_cities` VALUES ('1028', '50', 'Natonin');
INSERT INTO `geo_cities` VALUES ('1029', '50', 'Paracelis');
INSERT INTO `geo_cities` VALUES ('1030', '50', 'Sabangan');
INSERT INTO `geo_cities` VALUES ('1031', '50', 'Sadanga');
INSERT INTO `geo_cities` VALUES ('1032', '50', 'Sagada');
INSERT INTO `geo_cities` VALUES ('1033', '50', 'Tadian');
INSERT INTO `geo_cities` VALUES ('1034', '51', 'Bacolod City');
INSERT INTO `geo_cities` VALUES ('1035', '51', 'Bago City');
INSERT INTO `geo_cities` VALUES ('1036', '51', 'Cadiz City');
INSERT INTO `geo_cities` VALUES ('1037', '51', 'Escalante City');
INSERT INTO `geo_cities` VALUES ('1038', '51', 'Himamaylan City');
INSERT INTO `geo_cities` VALUES ('1039', '51', 'Kabankalan City');
INSERT INTO `geo_cities` VALUES ('1040', '51', 'La Carlota City');
INSERT INTO `geo_cities` VALUES ('1041', '51', 'Sagay City');
INSERT INTO `geo_cities` VALUES ('1042', '51', 'San Carlos City');
INSERT INTO `geo_cities` VALUES ('1043', '51', 'Silay City');
INSERT INTO `geo_cities` VALUES ('1044', '51', 'Sipalay City');
INSERT INTO `geo_cities` VALUES ('1045', '51', 'Talisay City');
INSERT INTO `geo_cities` VALUES ('1046', '51', 'Victorias City');
INSERT INTO `geo_cities` VALUES ('1047', '51', 'Binalbagan');
INSERT INTO `geo_cities` VALUES ('1048', '51', 'Calatrava');
INSERT INTO `geo_cities` VALUES ('1049', '51', 'Candoni');
INSERT INTO `geo_cities` VALUES ('1050', '51', 'Cauayan');
INSERT INTO `geo_cities` VALUES ('1051', '51', 'Enrique B. Magalona');
INSERT INTO `geo_cities` VALUES ('1052', '51', 'Hinigaran');
INSERT INTO `geo_cities` VALUES ('1053', '51', 'Hinoba-an');
INSERT INTO `geo_cities` VALUES ('1054', '51', 'Ilog');
INSERT INTO `geo_cities` VALUES ('1055', '51', 'Isabela');
INSERT INTO `geo_cities` VALUES ('1056', '51', 'La Castellana');
INSERT INTO `geo_cities` VALUES ('1057', '51', 'Manapla');
INSERT INTO `geo_cities` VALUES ('1058', '51', 'Moises Padilla');
INSERT INTO `geo_cities` VALUES ('1059', '51', 'Murcia');
INSERT INTO `geo_cities` VALUES ('1060', '51', 'Pontevedra');
INSERT INTO `geo_cities` VALUES ('1061', '51', 'Pulupandan');
INSERT INTO `geo_cities` VALUES ('1062', '51', 'Salvador Benedicto');
INSERT INTO `geo_cities` VALUES ('1063', '51', 'San Enrique');
INSERT INTO `geo_cities` VALUES ('1064', '51', 'Toboso');
INSERT INTO `geo_cities` VALUES ('1065', '51', 'Valladolid');
INSERT INTO `geo_cities` VALUES ('1066', '52', 'Bais City');
INSERT INTO `geo_cities` VALUES ('1067', '52', 'Bayawan City');
INSERT INTO `geo_cities` VALUES ('1068', '52', 'Canlaon City');
INSERT INTO `geo_cities` VALUES ('1069', '52', 'Guihulngan City');
INSERT INTO `geo_cities` VALUES ('1070', '52', 'Dumaguete City');
INSERT INTO `geo_cities` VALUES ('1071', '52', 'Tanjay City');
INSERT INTO `geo_cities` VALUES ('1072', '52', 'Amlan');
INSERT INTO `geo_cities` VALUES ('1073', '52', 'Ayungon');
INSERT INTO `geo_cities` VALUES ('1074', '52', 'Bacong');
INSERT INTO `geo_cities` VALUES ('1075', '52', 'Basay');
INSERT INTO `geo_cities` VALUES ('1076', '52', 'Bindoy');
INSERT INTO `geo_cities` VALUES ('1077', '52', 'Dauin');
INSERT INTO `geo_cities` VALUES ('1078', '52', 'Jimalalud');
INSERT INTO `geo_cities` VALUES ('1079', '52', 'La Libertad');
INSERT INTO `geo_cities` VALUES ('1080', '52', 'Mabinay');
INSERT INTO `geo_cities` VALUES ('1081', '52', 'Manjuyod');
INSERT INTO `geo_cities` VALUES ('1082', '52', 'Pamplona');
INSERT INTO `geo_cities` VALUES ('1083', '52', 'San Jose');
INSERT INTO `geo_cities` VALUES ('1084', '52', 'Santa Catalina');
INSERT INTO `geo_cities` VALUES ('1085', '52', 'Siaton');
INSERT INTO `geo_cities` VALUES ('1086', '52', 'Sibulan');
INSERT INTO `geo_cities` VALUES ('1087', '52', 'Tayasan');
INSERT INTO `geo_cities` VALUES ('1088', '52', 'Valencia');
INSERT INTO `geo_cities` VALUES ('1089', '52', 'Vallehermoso');
INSERT INTO `geo_cities` VALUES ('1090', '52', 'Zamboanguita');
INSERT INTO `geo_cities` VALUES ('1091', '53', 'Allen');
INSERT INTO `geo_cities` VALUES ('1092', '53', 'Biri');
INSERT INTO `geo_cities` VALUES ('1093', '53', 'Bobon');
INSERT INTO `geo_cities` VALUES ('1094', '53', 'Capul');
INSERT INTO `geo_cities` VALUES ('1095', '53', 'Catarman');
INSERT INTO `geo_cities` VALUES ('1096', '53', 'Catubig');
INSERT INTO `geo_cities` VALUES ('1097', '53', 'Gamay');
INSERT INTO `geo_cities` VALUES ('1098', '53', 'Laoang');
INSERT INTO `geo_cities` VALUES ('1099', '53', 'Lapinig');
INSERT INTO `geo_cities` VALUES ('1100', '53', 'Las Navas');
INSERT INTO `geo_cities` VALUES ('1101', '53', 'Lavezares');
INSERT INTO `geo_cities` VALUES ('1102', '53', 'Lope de Vega');
INSERT INTO `geo_cities` VALUES ('1103', '53', 'Mapanas');
INSERT INTO `geo_cities` VALUES ('1104', '53', 'Mondragon');
INSERT INTO `geo_cities` VALUES ('1105', '53', 'Palapag');
INSERT INTO `geo_cities` VALUES ('1106', '53', 'Pambujan');
INSERT INTO `geo_cities` VALUES ('1107', '53', 'Rosario');
INSERT INTO `geo_cities` VALUES ('1108', '53', 'San Antonio');
INSERT INTO `geo_cities` VALUES ('1109', '53', 'San Isidro');
INSERT INTO `geo_cities` VALUES ('1110', '53', 'San Jose');
INSERT INTO `geo_cities` VALUES ('1111', '53', 'San Roque');
INSERT INTO `geo_cities` VALUES ('1112', '53', 'San Vicente');
INSERT INTO `geo_cities` VALUES ('1113', '53', 'Silvino Lobos');
INSERT INTO `geo_cities` VALUES ('1114', '53', 'Victoria');
INSERT INTO `geo_cities` VALUES ('1115', '54', 'Cabanatuan City');
INSERT INTO `geo_cities` VALUES ('1116', '54', 'Gapan City');
INSERT INTO `geo_cities` VALUES ('1117', '54', 'Science City of Muñoz');
INSERT INTO `geo_cities` VALUES ('1118', '54', 'Palayan City');
INSERT INTO `geo_cities` VALUES ('1119', '54', 'San Jose City');
INSERT INTO `geo_cities` VALUES ('1120', '54', 'Aliaga');
INSERT INTO `geo_cities` VALUES ('1121', '54', 'Bongabon');
INSERT INTO `geo_cities` VALUES ('1122', '54', 'Cabiao');
INSERT INTO `geo_cities` VALUES ('1123', '54', 'Carranglan');
INSERT INTO `geo_cities` VALUES ('1124', '54', 'Cuyapo');
INSERT INTO `geo_cities` VALUES ('1125', '54', 'Gabaldon');
INSERT INTO `geo_cities` VALUES ('1126', '54', 'General Mamerto Natividad');
INSERT INTO `geo_cities` VALUES ('1127', '54', 'General Tinio');
INSERT INTO `geo_cities` VALUES ('1128', '54', 'Guimba');
INSERT INTO `geo_cities` VALUES ('1129', '54', 'Jaen');
INSERT INTO `geo_cities` VALUES ('1130', '54', 'Laur');
INSERT INTO `geo_cities` VALUES ('1131', '54', 'Licab');
INSERT INTO `geo_cities` VALUES ('1132', '54', 'Llanera');
INSERT INTO `geo_cities` VALUES ('1133', '54', 'Lupao');
INSERT INTO `geo_cities` VALUES ('1134', '54', 'Nampicuan');
INSERT INTO `geo_cities` VALUES ('1135', '54', 'Pantabangan');
INSERT INTO `geo_cities` VALUES ('1136', '54', 'Peñaranda');
INSERT INTO `geo_cities` VALUES ('1137', '54', 'Quezon');
INSERT INTO `geo_cities` VALUES ('1138', '54', 'Rizal');
INSERT INTO `geo_cities` VALUES ('1139', '54', 'San Antonio');
INSERT INTO `geo_cities` VALUES ('1140', '54', 'San Isidro');
INSERT INTO `geo_cities` VALUES ('1141', '54', 'San Leonardo');
INSERT INTO `geo_cities` VALUES ('1142', '54', 'Santa Rosa');
INSERT INTO `geo_cities` VALUES ('1143', '54', 'Santo Domingo');
INSERT INTO `geo_cities` VALUES ('1144', '54', 'Talavera');
INSERT INTO `geo_cities` VALUES ('1145', '54', 'Talugtug');
INSERT INTO `geo_cities` VALUES ('1146', '54', 'Zaragoza');
INSERT INTO `geo_cities` VALUES ('1147', '55', 'Alfonso Castañeda');
INSERT INTO `geo_cities` VALUES ('1148', '55', 'Ambaguio');
INSERT INTO `geo_cities` VALUES ('1149', '55', 'Aritao');
INSERT INTO `geo_cities` VALUES ('1150', '55', 'Bagabag');
INSERT INTO `geo_cities` VALUES ('1151', '55', 'Bambang');
INSERT INTO `geo_cities` VALUES ('1152', '55', 'Bayombong');
INSERT INTO `geo_cities` VALUES ('1153', '55', 'Diadi');
INSERT INTO `geo_cities` VALUES ('1154', '55', 'Dupax del Norte');
INSERT INTO `geo_cities` VALUES ('1155', '55', 'Dupax del Sur');
INSERT INTO `geo_cities` VALUES ('1156', '55', 'Kasibu');
INSERT INTO `geo_cities` VALUES ('1157', '55', 'Kayapa');
INSERT INTO `geo_cities` VALUES ('1158', '55', 'Quezon');
INSERT INTO `geo_cities` VALUES ('1159', '55', 'Santa Fe');
INSERT INTO `geo_cities` VALUES ('1160', '55', 'Solano');
INSERT INTO `geo_cities` VALUES ('1161', '55', 'Villaverde');
INSERT INTO `geo_cities` VALUES ('1162', '56', 'Abra de Ilog');
INSERT INTO `geo_cities` VALUES ('1163', '56', 'Calintaan');
INSERT INTO `geo_cities` VALUES ('1164', '56', 'Looc');
INSERT INTO `geo_cities` VALUES ('1165', '56', 'Lubang');
INSERT INTO `geo_cities` VALUES ('1166', '56', 'Magsaysay');
INSERT INTO `geo_cities` VALUES ('1167', '56', 'Mamburao');
INSERT INTO `geo_cities` VALUES ('1168', '56', 'Paluan');
INSERT INTO `geo_cities` VALUES ('1169', '56', 'Rizal');
INSERT INTO `geo_cities` VALUES ('1170', '56', 'Sablayan');
INSERT INTO `geo_cities` VALUES ('1171', '56', 'San Jose');
INSERT INTO `geo_cities` VALUES ('1172', '56', 'Santa Cruz');
INSERT INTO `geo_cities` VALUES ('1173', '57', 'Calapan City');
INSERT INTO `geo_cities` VALUES ('1174', '57', 'Baco');
INSERT INTO `geo_cities` VALUES ('1175', '57', 'Bansud');
INSERT INTO `geo_cities` VALUES ('1176', '57', 'Bongabong');
INSERT INTO `geo_cities` VALUES ('1177', '57', 'Bulalacao');
INSERT INTO `geo_cities` VALUES ('1178', '57', 'Gloria');
INSERT INTO `geo_cities` VALUES ('1179', '57', 'Mansalay');
INSERT INTO `geo_cities` VALUES ('1180', '57', 'Naujan');
INSERT INTO `geo_cities` VALUES ('1181', '57', 'Pinamalayan');
INSERT INTO `geo_cities` VALUES ('1182', '57', 'Pola');
INSERT INTO `geo_cities` VALUES ('1183', '57', 'Puerto Galera');
INSERT INTO `geo_cities` VALUES ('1184', '57', 'Roxas');
INSERT INTO `geo_cities` VALUES ('1185', '57', 'San Teodoro');
INSERT INTO `geo_cities` VALUES ('1186', '57', 'Socorro');
INSERT INTO `geo_cities` VALUES ('1187', '57', 'Victoria');
INSERT INTO `geo_cities` VALUES ('1188', '58', 'Puerto Princesa City');
INSERT INTO `geo_cities` VALUES ('1189', '58', 'Aborlan');
INSERT INTO `geo_cities` VALUES ('1190', '58', 'Agutaya');
INSERT INTO `geo_cities` VALUES ('1191', '58', 'Araceli');
INSERT INTO `geo_cities` VALUES ('1192', '58', 'Balabac');
INSERT INTO `geo_cities` VALUES ('1193', '58', 'Bataraza');
INSERT INTO `geo_cities` VALUES ('1194', '58', 'Brooke\'s Point');
INSERT INTO `geo_cities` VALUES ('1195', '58', 'Busuanga');
INSERT INTO `geo_cities` VALUES ('1196', '58', 'Cagayancillo');
INSERT INTO `geo_cities` VALUES ('1197', '58', 'Coron');
INSERT INTO `geo_cities` VALUES ('1198', '58', 'Culion');
INSERT INTO `geo_cities` VALUES ('1199', '58', 'Cuyo');
INSERT INTO `geo_cities` VALUES ('1200', '58', 'Dumaran');
INSERT INTO `geo_cities` VALUES ('1201', '58', 'El Nido');
INSERT INTO `geo_cities` VALUES ('1202', '58', 'Kalayaan');
INSERT INTO `geo_cities` VALUES ('1203', '58', 'Linapacan');
INSERT INTO `geo_cities` VALUES ('1204', '58', 'Magsaysay');
INSERT INTO `geo_cities` VALUES ('1205', '58', 'Narra');
INSERT INTO `geo_cities` VALUES ('1206', '58', 'Quezon');
INSERT INTO `geo_cities` VALUES ('1207', '58', 'Rizal');
INSERT INTO `geo_cities` VALUES ('1208', '58', 'Roxas');
INSERT INTO `geo_cities` VALUES ('1209', '58', 'San Vicente');
INSERT INTO `geo_cities` VALUES ('1210', '58', 'Sofronio Española');
INSERT INTO `geo_cities` VALUES ('1211', '58', 'Taytay');
INSERT INTO `geo_cities` VALUES ('1212', '59', 'Angeles City');
INSERT INTO `geo_cities` VALUES ('1213', '59', 'City of San Fernando');
INSERT INTO `geo_cities` VALUES ('1214', '59', 'Apalit');
INSERT INTO `geo_cities` VALUES ('1215', '59', 'Arayat');
INSERT INTO `geo_cities` VALUES ('1216', '59', 'Bacolor');
INSERT INTO `geo_cities` VALUES ('1217', '59', 'Candaba');
INSERT INTO `geo_cities` VALUES ('1218', '59', 'Floridablanca');
INSERT INTO `geo_cities` VALUES ('1219', '59', 'Guagua');
INSERT INTO `geo_cities` VALUES ('1220', '59', 'Lubao');
INSERT INTO `geo_cities` VALUES ('1221', '59', 'Mabalacat');
INSERT INTO `geo_cities` VALUES ('1222', '59', 'Macabebe');
INSERT INTO `geo_cities` VALUES ('1223', '59', 'Magalang');
INSERT INTO `geo_cities` VALUES ('1224', '59', 'Masantol');
INSERT INTO `geo_cities` VALUES ('1225', '59', 'Mexico');
INSERT INTO `geo_cities` VALUES ('1226', '59', 'Minalin');
INSERT INTO `geo_cities` VALUES ('1227', '59', 'Porac');
INSERT INTO `geo_cities` VALUES ('1228', '59', 'San Luis');
INSERT INTO `geo_cities` VALUES ('1229', '59', 'San Simon');
INSERT INTO `geo_cities` VALUES ('1230', '59', 'Santa Ana');
INSERT INTO `geo_cities` VALUES ('1231', '59', 'Santa Rita');
INSERT INTO `geo_cities` VALUES ('1232', '59', 'Santo Tomas');
INSERT INTO `geo_cities` VALUES ('1233', '59', 'Sasmuan');
INSERT INTO `geo_cities` VALUES ('1234', '60', 'Alaminos City');
INSERT INTO `geo_cities` VALUES ('1235', '60', 'Dagupan City');
INSERT INTO `geo_cities` VALUES ('1236', '60', 'San Carlos City');
INSERT INTO `geo_cities` VALUES ('1237', '60', 'Urdaneta City');
INSERT INTO `geo_cities` VALUES ('1238', '60', 'Agno');
INSERT INTO `geo_cities` VALUES ('1239', '60', 'Aguilar');
INSERT INTO `geo_cities` VALUES ('1240', '60', 'Alcala');
INSERT INTO `geo_cities` VALUES ('1241', '60', 'Anda');
INSERT INTO `geo_cities` VALUES ('1242', '60', 'Asingan');
INSERT INTO `geo_cities` VALUES ('1243', '60', 'Balungao');
INSERT INTO `geo_cities` VALUES ('1244', '60', 'Bani');
INSERT INTO `geo_cities` VALUES ('1245', '60', 'Basista');
INSERT INTO `geo_cities` VALUES ('1246', '60', 'Bautista');
INSERT INTO `geo_cities` VALUES ('1247', '60', 'Bayambang');
INSERT INTO `geo_cities` VALUES ('1248', '60', 'Binalonan');
INSERT INTO `geo_cities` VALUES ('1249', '60', 'Binmaley');
INSERT INTO `geo_cities` VALUES ('1250', '60', 'Bolinao');
INSERT INTO `geo_cities` VALUES ('1251', '60', 'Bugallon');
INSERT INTO `geo_cities` VALUES ('1252', '60', 'Burgos');
INSERT INTO `geo_cities` VALUES ('1253', '60', 'Calasiao');
INSERT INTO `geo_cities` VALUES ('1254', '60', 'Dasol');
INSERT INTO `geo_cities` VALUES ('1255', '60', 'Infanta');
INSERT INTO `geo_cities` VALUES ('1256', '60', 'Labrador');
INSERT INTO `geo_cities` VALUES ('1257', '60', 'Laoac');
INSERT INTO `geo_cities` VALUES ('1258', '60', 'Lingayen');
INSERT INTO `geo_cities` VALUES ('1259', '60', 'Mabini');
INSERT INTO `geo_cities` VALUES ('1260', '60', 'Malasiqui');
INSERT INTO `geo_cities` VALUES ('1261', '60', 'Manaoag');
INSERT INTO `geo_cities` VALUES ('1262', '60', 'Mangaldan');
INSERT INTO `geo_cities` VALUES ('1263', '60', 'Mangatarem');
INSERT INTO `geo_cities` VALUES ('1264', '60', 'Mapandan');
INSERT INTO `geo_cities` VALUES ('1265', '60', 'Natividad');
INSERT INTO `geo_cities` VALUES ('1266', '60', 'Pozzorubio');
INSERT INTO `geo_cities` VALUES ('1267', '60', 'Rosales');
INSERT INTO `geo_cities` VALUES ('1268', '60', 'San Fabian');
INSERT INTO `geo_cities` VALUES ('1269', '60', 'San Jacinto');
INSERT INTO `geo_cities` VALUES ('1270', '60', 'San Manuel');
INSERT INTO `geo_cities` VALUES ('1271', '60', 'San Nicolas');
INSERT INTO `geo_cities` VALUES ('1272', '60', 'San Quintin');
INSERT INTO `geo_cities` VALUES ('1273', '60', 'Santa Barbara');
INSERT INTO `geo_cities` VALUES ('1274', '60', 'Santa Maria');
INSERT INTO `geo_cities` VALUES ('1275', '60', 'Santo Tomas');
INSERT INTO `geo_cities` VALUES ('1276', '60', 'Sison');
INSERT INTO `geo_cities` VALUES ('1277', '60', 'Sual');
INSERT INTO `geo_cities` VALUES ('1278', '60', 'Tayug');
INSERT INTO `geo_cities` VALUES ('1279', '60', 'Umingan');
INSERT INTO `geo_cities` VALUES ('1280', '60', 'Urbiztondo');
INSERT INTO `geo_cities` VALUES ('1281', '60', 'Villasis');
INSERT INTO `geo_cities` VALUES ('1282', '61', 'Lucena City');
INSERT INTO `geo_cities` VALUES ('1283', '61', 'Tayabas City');
INSERT INTO `geo_cities` VALUES ('1284', '61', 'Agdangan');
INSERT INTO `geo_cities` VALUES ('1285', '61', 'Alabat');
INSERT INTO `geo_cities` VALUES ('1286', '61', 'Atimonan');
INSERT INTO `geo_cities` VALUES ('1287', '61', 'Buenavista');
INSERT INTO `geo_cities` VALUES ('1288', '61', 'Burdeos');
INSERT INTO `geo_cities` VALUES ('1289', '61', 'Calauag');
INSERT INTO `geo_cities` VALUES ('1290', '61', 'Candelaria');
INSERT INTO `geo_cities` VALUES ('1291', '61', 'Catanauan');
INSERT INTO `geo_cities` VALUES ('1292', '61', 'Dolores');
INSERT INTO `geo_cities` VALUES ('1293', '61', 'General Luna');
INSERT INTO `geo_cities` VALUES ('1294', '61', 'General Nakar');
INSERT INTO `geo_cities` VALUES ('1295', '61', 'Guinayangan');
INSERT INTO `geo_cities` VALUES ('1296', '61', 'Gumaca');
INSERT INTO `geo_cities` VALUES ('1297', '61', 'Infanta');
INSERT INTO `geo_cities` VALUES ('1298', '61', 'Jomalig');
INSERT INTO `geo_cities` VALUES ('1299', '61', 'Lopez');
INSERT INTO `geo_cities` VALUES ('1300', '61', 'Lucban');
INSERT INTO `geo_cities` VALUES ('1301', '61', 'Macalelon');
INSERT INTO `geo_cities` VALUES ('1302', '61', 'Mauban');
INSERT INTO `geo_cities` VALUES ('1303', '61', 'Mulanay');
INSERT INTO `geo_cities` VALUES ('1304', '61', 'Padre Burgos');
INSERT INTO `geo_cities` VALUES ('1305', '61', 'Pagbilao');
INSERT INTO `geo_cities` VALUES ('1306', '61', 'Panukulan');
INSERT INTO `geo_cities` VALUES ('1307', '61', 'Patnanungan');
INSERT INTO `geo_cities` VALUES ('1308', '61', 'Perez');
INSERT INTO `geo_cities` VALUES ('1309', '61', 'Pitogo');
INSERT INTO `geo_cities` VALUES ('1310', '61', 'Plaridel');
INSERT INTO `geo_cities` VALUES ('1311', '61', 'Polillo');
INSERT INTO `geo_cities` VALUES ('1312', '61', 'Quezon');
INSERT INTO `geo_cities` VALUES ('1313', '61', 'Real');
INSERT INTO `geo_cities` VALUES ('1314', '61', 'Sampaloc');
INSERT INTO `geo_cities` VALUES ('1315', '61', 'San Andres');
INSERT INTO `geo_cities` VALUES ('1316', '61', 'San Antonio');
INSERT INTO `geo_cities` VALUES ('1317', '61', 'San Francisco');
INSERT INTO `geo_cities` VALUES ('1318', '61', 'San Narciso');
INSERT INTO `geo_cities` VALUES ('1319', '61', 'Sariaya');
INSERT INTO `geo_cities` VALUES ('1320', '61', 'Tagkawayan');
INSERT INTO `geo_cities` VALUES ('1321', '61', 'Tiaong');
INSERT INTO `geo_cities` VALUES ('1322', '61', 'Unisan');
INSERT INTO `geo_cities` VALUES ('1323', '62', 'Aglipay');
INSERT INTO `geo_cities` VALUES ('1324', '62', 'Cabarroguis');
INSERT INTO `geo_cities` VALUES ('1325', '62', 'Diffun');
INSERT INTO `geo_cities` VALUES ('1326', '62', 'Maddela');
INSERT INTO `geo_cities` VALUES ('1327', '62', 'Nagtipunan');
INSERT INTO `geo_cities` VALUES ('1328', '62', 'Saguday');
INSERT INTO `geo_cities` VALUES ('1329', '63', 'Antipolo City');
INSERT INTO `geo_cities` VALUES ('1330', '63', 'Angono');
INSERT INTO `geo_cities` VALUES ('1331', '63', 'Baras');
INSERT INTO `geo_cities` VALUES ('1332', '63', 'Binangonan');
INSERT INTO `geo_cities` VALUES ('1333', '63', 'Cainta');
INSERT INTO `geo_cities` VALUES ('1334', '63', 'Cardona');
INSERT INTO `geo_cities` VALUES ('1335', '63', 'Jalajala');
INSERT INTO `geo_cities` VALUES ('1336', '63', 'Morong');
INSERT INTO `geo_cities` VALUES ('1337', '63', 'Pililla');
INSERT INTO `geo_cities` VALUES ('1338', '63', 'Rodriguez');
INSERT INTO `geo_cities` VALUES ('1339', '63', 'San Mateo');
INSERT INTO `geo_cities` VALUES ('1340', '63', 'Tanay');
INSERT INTO `geo_cities` VALUES ('1341', '63', 'Taytay');
INSERT INTO `geo_cities` VALUES ('1342', '63', 'Teresa');
INSERT INTO `geo_cities` VALUES ('1343', '64', 'Alcantara');
INSERT INTO `geo_cities` VALUES ('1344', '64', 'Banton');
INSERT INTO `geo_cities` VALUES ('1345', '64', 'Cajidiocan');
INSERT INTO `geo_cities` VALUES ('1346', '64', 'Calatrava');
INSERT INTO `geo_cities` VALUES ('1347', '64', 'Concepcion');
INSERT INTO `geo_cities` VALUES ('1348', '64', 'Corcuera');
INSERT INTO `geo_cities` VALUES ('1349', '64', 'Ferrol');
INSERT INTO `geo_cities` VALUES ('1350', '64', 'Looc');
INSERT INTO `geo_cities` VALUES ('1351', '64', 'Magdiwang');
INSERT INTO `geo_cities` VALUES ('1352', '64', 'Odiongan');
INSERT INTO `geo_cities` VALUES ('1353', '64', 'Romblon');
INSERT INTO `geo_cities` VALUES ('1354', '64', 'San Agustin');
INSERT INTO `geo_cities` VALUES ('1355', '64', 'San Andres');
INSERT INTO `geo_cities` VALUES ('1356', '64', 'San Fernando');
INSERT INTO `geo_cities` VALUES ('1357', '64', 'San Jose');
INSERT INTO `geo_cities` VALUES ('1358', '64', 'Santa Fe');
INSERT INTO `geo_cities` VALUES ('1359', '64', 'Santa Maria');
INSERT INTO `geo_cities` VALUES ('1360', '65', 'Calbayog City');
INSERT INTO `geo_cities` VALUES ('1361', '65', 'Catbalogan City');
INSERT INTO `geo_cities` VALUES ('1362', '65', 'Almagro');
INSERT INTO `geo_cities` VALUES ('1363', '65', 'Basey');
INSERT INTO `geo_cities` VALUES ('1364', '65', 'Calbiga');
INSERT INTO `geo_cities` VALUES ('1365', '65', 'Daram');
INSERT INTO `geo_cities` VALUES ('1366', '65', 'Gandara');
INSERT INTO `geo_cities` VALUES ('1367', '65', 'Hinabangan');
INSERT INTO `geo_cities` VALUES ('1368', '65', 'Jiabong');
INSERT INTO `geo_cities` VALUES ('1369', '65', 'Marabut');
INSERT INTO `geo_cities` VALUES ('1370', '65', 'Matuguinao');
INSERT INTO `geo_cities` VALUES ('1371', '65', 'Motiong');
INSERT INTO `geo_cities` VALUES ('1372', '65', 'Pagsanghan');
INSERT INTO `geo_cities` VALUES ('1373', '65', 'Paranas');
INSERT INTO `geo_cities` VALUES ('1374', '65', 'Pinabacdao');
INSERT INTO `geo_cities` VALUES ('1375', '65', 'San Jorge');
INSERT INTO `geo_cities` VALUES ('1376', '65', 'San Jose De Buan');
INSERT INTO `geo_cities` VALUES ('1377', '65', 'San Sebastian');
INSERT INTO `geo_cities` VALUES ('1378', '65', 'Santa Margarita');
INSERT INTO `geo_cities` VALUES ('1379', '65', 'Santa Rita');
INSERT INTO `geo_cities` VALUES ('1380', '65', 'Santo Niño');
INSERT INTO `geo_cities` VALUES ('1381', '65', 'Tagapul-an');
INSERT INTO `geo_cities` VALUES ('1382', '65', 'Talalora');
INSERT INTO `geo_cities` VALUES ('1383', '65', 'Tarangnan');
INSERT INTO `geo_cities` VALUES ('1384', '65', 'Villareal');
INSERT INTO `geo_cities` VALUES ('1385', '65', 'Zumarraga');
INSERT INTO `geo_cities` VALUES ('1386', '66', 'Alabel');
INSERT INTO `geo_cities` VALUES ('1387', '66', 'Glan');
INSERT INTO `geo_cities` VALUES ('1388', '66', 'Kiamba');
INSERT INTO `geo_cities` VALUES ('1389', '66', 'Maasim');
INSERT INTO `geo_cities` VALUES ('1390', '66', 'Maitum');
INSERT INTO `geo_cities` VALUES ('1391', '66', 'Malapatan');
INSERT INTO `geo_cities` VALUES ('1392', '66', 'Malungon');
INSERT INTO `geo_cities` VALUES ('1393', '67', 'Enrique Villanueva');
INSERT INTO `geo_cities` VALUES ('1394', '67', 'Larena');
INSERT INTO `geo_cities` VALUES ('1395', '67', 'Lazi');
INSERT INTO `geo_cities` VALUES ('1396', '67', 'Maria');
INSERT INTO `geo_cities` VALUES ('1397', '67', 'San Juan');
INSERT INTO `geo_cities` VALUES ('1398', '67', 'Siquijor');
INSERT INTO `geo_cities` VALUES ('1399', '68', 'Sorsogon City');
INSERT INTO `geo_cities` VALUES ('1400', '68', 'Barcelona');
INSERT INTO `geo_cities` VALUES ('1401', '68', 'Bulan');
INSERT INTO `geo_cities` VALUES ('1402', '68', 'Bulusan');
INSERT INTO `geo_cities` VALUES ('1403', '68', 'Casiguran');
INSERT INTO `geo_cities` VALUES ('1404', '68', 'Castilla');
INSERT INTO `geo_cities` VALUES ('1405', '68', 'Donsol');
INSERT INTO `geo_cities` VALUES ('1406', '68', 'Gubat');
INSERT INTO `geo_cities` VALUES ('1407', '68', 'Irosin');
INSERT INTO `geo_cities` VALUES ('1408', '68', 'Juban');
INSERT INTO `geo_cities` VALUES ('1409', '68', 'Magallanes');
INSERT INTO `geo_cities` VALUES ('1410', '68', 'Matnog');
INSERT INTO `geo_cities` VALUES ('1411', '68', 'Pilar');
INSERT INTO `geo_cities` VALUES ('1412', '68', 'Prieto Diaz');
INSERT INTO `geo_cities` VALUES ('1413', '68', 'Santa Magdalena');
INSERT INTO `geo_cities` VALUES ('1414', '69', 'General Santos City');
INSERT INTO `geo_cities` VALUES ('1415', '69', 'Koronadal City');
INSERT INTO `geo_cities` VALUES ('1416', '69', 'Banga');
INSERT INTO `geo_cities` VALUES ('1417', '69', 'Lake Sebu');
INSERT INTO `geo_cities` VALUES ('1418', '69', 'Norala');
INSERT INTO `geo_cities` VALUES ('1419', '69', 'Polomolok');
INSERT INTO `geo_cities` VALUES ('1420', '69', 'Santo Niño');
INSERT INTO `geo_cities` VALUES ('1421', '69', 'Surallah');
INSERT INTO `geo_cities` VALUES ('1422', '69', 'T\'boli');
INSERT INTO `geo_cities` VALUES ('1423', '69', 'Tampakan');
INSERT INTO `geo_cities` VALUES ('1424', '69', 'Tantangan');
INSERT INTO `geo_cities` VALUES ('1425', '69', 'Tupi');
INSERT INTO `geo_cities` VALUES ('1426', '70', 'Maasin City');
INSERT INTO `geo_cities` VALUES ('1427', '70', 'Anahawan');
INSERT INTO `geo_cities` VALUES ('1428', '70', 'Bontoc');
INSERT INTO `geo_cities` VALUES ('1429', '70', 'Hinunangan');
INSERT INTO `geo_cities` VALUES ('1430', '70', 'Hinundayan');
INSERT INTO `geo_cities` VALUES ('1431', '70', 'Libagon');
INSERT INTO `geo_cities` VALUES ('1432', '70', 'Liloan');
INSERT INTO `geo_cities` VALUES ('1433', '70', 'Limasawa');
INSERT INTO `geo_cities` VALUES ('1434', '70', 'Macrohon');
INSERT INTO `geo_cities` VALUES ('1435', '70', 'Malitbog');
INSERT INTO `geo_cities` VALUES ('1436', '70', 'Padre Burgos');
INSERT INTO `geo_cities` VALUES ('1437', '70', 'Pintuyan');
INSERT INTO `geo_cities` VALUES ('1438', '70', 'Saint Bernard');
INSERT INTO `geo_cities` VALUES ('1439', '70', 'San Francisco');
INSERT INTO `geo_cities` VALUES ('1440', '70', 'San Juan');
INSERT INTO `geo_cities` VALUES ('1441', '70', 'San Ricardo');
INSERT INTO `geo_cities` VALUES ('1442', '70', 'Silago');
INSERT INTO `geo_cities` VALUES ('1443', '70', 'Sogod');
INSERT INTO `geo_cities` VALUES ('1444', '70', 'Tomas Oppus');
INSERT INTO `geo_cities` VALUES ('1445', '71', 'Tacurong City');
INSERT INTO `geo_cities` VALUES ('1446', '71', 'Bagumbayan');
INSERT INTO `geo_cities` VALUES ('1447', '71', 'Columbio');
INSERT INTO `geo_cities` VALUES ('1448', '71', 'Esperanza');
INSERT INTO `geo_cities` VALUES ('1449', '71', 'Isulan');
INSERT INTO `geo_cities` VALUES ('1450', '71', 'Kalamansig');
INSERT INTO `geo_cities` VALUES ('1451', '71', 'Lambayong');
INSERT INTO `geo_cities` VALUES ('1452', '71', 'Lebak');
INSERT INTO `geo_cities` VALUES ('1453', '71', 'Lutayan');
INSERT INTO `geo_cities` VALUES ('1454', '71', 'Palimbang');
INSERT INTO `geo_cities` VALUES ('1455', '71', 'President Quirino');
INSERT INTO `geo_cities` VALUES ('1456', '71', 'Senator Ninoy Aquino');
INSERT INTO `geo_cities` VALUES ('1457', '72', 'Banguingui');
INSERT INTO `geo_cities` VALUES ('1458', '72', 'Hadji Panglima Tahil');
INSERT INTO `geo_cities` VALUES ('1459', '72', 'Indanan');
INSERT INTO `geo_cities` VALUES ('1460', '72', 'Jolo');
INSERT INTO `geo_cities` VALUES ('1461', '72', 'Kalingalan Caluang');
INSERT INTO `geo_cities` VALUES ('1462', '72', 'Lugus');
INSERT INTO `geo_cities` VALUES ('1463', '72', 'Luuk');
INSERT INTO `geo_cities` VALUES ('1464', '72', 'Maimbung');
INSERT INTO `geo_cities` VALUES ('1465', '72', 'Old Panamao');
INSERT INTO `geo_cities` VALUES ('1466', '72', 'Omar');
INSERT INTO `geo_cities` VALUES ('1467', '72', 'Pandami');
INSERT INTO `geo_cities` VALUES ('1468', '72', 'Panglima Estino');
INSERT INTO `geo_cities` VALUES ('1469', '72', 'Pangutaran');
INSERT INTO `geo_cities` VALUES ('1470', '72', 'Parang');
INSERT INTO `geo_cities` VALUES ('1471', '72', 'Pata');
INSERT INTO `geo_cities` VALUES ('1472', '72', 'Patikul');
INSERT INTO `geo_cities` VALUES ('1473', '72', 'Siasi');
INSERT INTO `geo_cities` VALUES ('1474', '72', 'Talipao');
INSERT INTO `geo_cities` VALUES ('1475', '72', 'Tapul');
INSERT INTO `geo_cities` VALUES ('1476', '73', 'Surigao City');
INSERT INTO `geo_cities` VALUES ('1477', '73', 'Alegria');
INSERT INTO `geo_cities` VALUES ('1478', '73', 'Bacuag');
INSERT INTO `geo_cities` VALUES ('1479', '73', 'Basilisa');
INSERT INTO `geo_cities` VALUES ('1480', '73', 'Burgos');
INSERT INTO `geo_cities` VALUES ('1481', '73', 'Cagdianao');
INSERT INTO `geo_cities` VALUES ('1482', '73', 'Claver');
INSERT INTO `geo_cities` VALUES ('1483', '73', 'Dapa');
INSERT INTO `geo_cities` VALUES ('1484', '73', 'Del Carmen');
INSERT INTO `geo_cities` VALUES ('1485', '73', 'Dinagat');
INSERT INTO `geo_cities` VALUES ('1486', '73', 'General Luna');
INSERT INTO `geo_cities` VALUES ('1487', '73', 'Gigaquit');
INSERT INTO `geo_cities` VALUES ('1488', '73', 'Libjo');
INSERT INTO `geo_cities` VALUES ('1489', '73', 'Loreto');
INSERT INTO `geo_cities` VALUES ('1490', '73', 'Mainit');
INSERT INTO `geo_cities` VALUES ('1491', '73', 'Malimono');
INSERT INTO `geo_cities` VALUES ('1492', '73', 'Pilar');
INSERT INTO `geo_cities` VALUES ('1493', '73', 'Placer');
INSERT INTO `geo_cities` VALUES ('1494', '73', 'San Benito');
INSERT INTO `geo_cities` VALUES ('1495', '73', 'San Francisco');
INSERT INTO `geo_cities` VALUES ('1496', '73', 'San Isidro');
INSERT INTO `geo_cities` VALUES ('1497', '73', 'San Jose');
INSERT INTO `geo_cities` VALUES ('1498', '73', 'Santa Monica');
INSERT INTO `geo_cities` VALUES ('1499', '73', 'Sison');
INSERT INTO `geo_cities` VALUES ('1500', '73', 'Socorro');
INSERT INTO `geo_cities` VALUES ('1501', '73', 'Tagana-an');
INSERT INTO `geo_cities` VALUES ('1502', '73', 'Tubajon');
INSERT INTO `geo_cities` VALUES ('1503', '73', 'Tubod');
INSERT INTO `geo_cities` VALUES ('1504', '74', 'Bislig City');
INSERT INTO `geo_cities` VALUES ('1505', '74', 'Tandag City');
INSERT INTO `geo_cities` VALUES ('1506', '74', 'Barobo');
INSERT INTO `geo_cities` VALUES ('1507', '74', 'Bayabas');
INSERT INTO `geo_cities` VALUES ('1508', '74', 'Cagwait');
INSERT INTO `geo_cities` VALUES ('1509', '74', 'Cantilan');
INSERT INTO `geo_cities` VALUES ('1510', '74', 'Carmen');
INSERT INTO `geo_cities` VALUES ('1511', '74', 'Carrascal');
INSERT INTO `geo_cities` VALUES ('1512', '74', 'Cortes');
INSERT INTO `geo_cities` VALUES ('1513', '74', 'Hinatuan');
INSERT INTO `geo_cities` VALUES ('1514', '74', 'Lanuza');
INSERT INTO `geo_cities` VALUES ('1515', '74', 'Lianga');
INSERT INTO `geo_cities` VALUES ('1516', '74', 'Lingig');
INSERT INTO `geo_cities` VALUES ('1517', '74', 'Madrid');
INSERT INTO `geo_cities` VALUES ('1518', '74', 'Marihatag');
INSERT INTO `geo_cities` VALUES ('1519', '74', 'San Agustin');
INSERT INTO `geo_cities` VALUES ('1520', '74', 'San Miguel');
INSERT INTO `geo_cities` VALUES ('1521', '74', 'Tagbina');
INSERT INTO `geo_cities` VALUES ('1522', '74', 'Tago');
INSERT INTO `geo_cities` VALUES ('1523', '75', 'Tarlac City');
INSERT INTO `geo_cities` VALUES ('1524', '75', 'Anao');
INSERT INTO `geo_cities` VALUES ('1525', '75', 'Bamban');
INSERT INTO `geo_cities` VALUES ('1526', '75', 'Camiling');
INSERT INTO `geo_cities` VALUES ('1527', '75', 'Capas');
INSERT INTO `geo_cities` VALUES ('1528', '75', 'Concepcion');
INSERT INTO `geo_cities` VALUES ('1529', '75', 'Gerona');
INSERT INTO `geo_cities` VALUES ('1530', '75', 'La Paz');
INSERT INTO `geo_cities` VALUES ('1531', '75', 'Mayantoc');
INSERT INTO `geo_cities` VALUES ('1532', '75', 'Moncada');
INSERT INTO `geo_cities` VALUES ('1533', '75', 'Paniqui');
INSERT INTO `geo_cities` VALUES ('1534', '75', 'Pura');
INSERT INTO `geo_cities` VALUES ('1535', '75', 'Ramos');
INSERT INTO `geo_cities` VALUES ('1536', '75', 'San Clemente');
INSERT INTO `geo_cities` VALUES ('1537', '75', 'San Jose');
INSERT INTO `geo_cities` VALUES ('1538', '75', 'San Manuel');
INSERT INTO `geo_cities` VALUES ('1539', '75', 'Santa Ignacia');
INSERT INTO `geo_cities` VALUES ('1540', '75', 'Victoria');
INSERT INTO `geo_cities` VALUES ('1541', '76', 'Bongao');
INSERT INTO `geo_cities` VALUES ('1542', '76', 'Languyan');
INSERT INTO `geo_cities` VALUES ('1543', '76', 'Mapun');
INSERT INTO `geo_cities` VALUES ('1544', '76', 'Panglima Sugala');
INSERT INTO `geo_cities` VALUES ('1545', '76', 'Sapa-Sapa');
INSERT INTO `geo_cities` VALUES ('1546', '76', 'Sibutu');
INSERT INTO `geo_cities` VALUES ('1547', '76', 'Simunul');
INSERT INTO `geo_cities` VALUES ('1548', '76', 'Sitangkai');
INSERT INTO `geo_cities` VALUES ('1549', '76', 'South Ubian');
INSERT INTO `geo_cities` VALUES ('1550', '76', 'Tandubas');
INSERT INTO `geo_cities` VALUES ('1551', '76', 'Turtle Islands');
INSERT INTO `geo_cities` VALUES ('1552', '77', 'Olongapo City');
INSERT INTO `geo_cities` VALUES ('1553', '77', 'Botolan');
INSERT INTO `geo_cities` VALUES ('1554', '77', 'Cabangan');
INSERT INTO `geo_cities` VALUES ('1555', '77', 'Candelaria');
INSERT INTO `geo_cities` VALUES ('1556', '77', 'Castillejos');
INSERT INTO `geo_cities` VALUES ('1557', '77', 'Iba');
INSERT INTO `geo_cities` VALUES ('1558', '77', 'Masinloc');
INSERT INTO `geo_cities` VALUES ('1559', '77', 'Palauig');
INSERT INTO `geo_cities` VALUES ('1560', '77', 'San Antonio');
INSERT INTO `geo_cities` VALUES ('1561', '77', 'San Felipe');
INSERT INTO `geo_cities` VALUES ('1562', '77', 'San Marcelino');
INSERT INTO `geo_cities` VALUES ('1563', '77', 'San Narciso');
INSERT INTO `geo_cities` VALUES ('1564', '77', 'Santa Cruz');
INSERT INTO `geo_cities` VALUES ('1565', '77', 'Subic');
INSERT INTO `geo_cities` VALUES ('1566', '78', 'Dapitan City');
INSERT INTO `geo_cities` VALUES ('1567', '78', 'Dipolog City');
INSERT INTO `geo_cities` VALUES ('1568', '78', 'Bacungan');
INSERT INTO `geo_cities` VALUES ('1569', '78', 'Baliguian');
INSERT INTO `geo_cities` VALUES ('1570', '78', 'Godod');
INSERT INTO `geo_cities` VALUES ('1571', '78', 'Gutalac');
INSERT INTO `geo_cities` VALUES ('1572', '78', 'Jose Dalman');
INSERT INTO `geo_cities` VALUES ('1573', '78', 'Kalawit');
INSERT INTO `geo_cities` VALUES ('1574', '78', 'Katipunan');
INSERT INTO `geo_cities` VALUES ('1575', '78', 'La Libertad');
INSERT INTO `geo_cities` VALUES ('1576', '78', 'Labason');
INSERT INTO `geo_cities` VALUES ('1577', '78', 'Liloy');
INSERT INTO `geo_cities` VALUES ('1578', '78', 'Manukan');
INSERT INTO `geo_cities` VALUES ('1579', '78', 'Mutia');
INSERT INTO `geo_cities` VALUES ('1580', '78', 'Piñan');
INSERT INTO `geo_cities` VALUES ('1581', '78', 'Polanco');
INSERT INTO `geo_cities` VALUES ('1582', '78', 'President Manuel A. Roxas');
INSERT INTO `geo_cities` VALUES ('1583', '78', 'Rizal');
INSERT INTO `geo_cities` VALUES ('1584', '78', 'Salug');
INSERT INTO `geo_cities` VALUES ('1585', '78', 'Sergio Osmeña Sr.');
INSERT INTO `geo_cities` VALUES ('1586', '78', 'Siayan');
INSERT INTO `geo_cities` VALUES ('1587', '78', 'Sibuco');
INSERT INTO `geo_cities` VALUES ('1588', '78', 'Sibutad');
INSERT INTO `geo_cities` VALUES ('1589', '78', 'Sindangan');
INSERT INTO `geo_cities` VALUES ('1590', '78', 'Siocon');
INSERT INTO `geo_cities` VALUES ('1591', '78', 'Sirawai');
INSERT INTO `geo_cities` VALUES ('1592', '78', 'Tampilisan');
INSERT INTO `geo_cities` VALUES ('1593', '79', 'Pagadian City');
INSERT INTO `geo_cities` VALUES ('1594', '79', 'Zamboanga City');
INSERT INTO `geo_cities` VALUES ('1595', '79', 'Aurora');
INSERT INTO `geo_cities` VALUES ('1596', '79', 'Bayog');
INSERT INTO `geo_cities` VALUES ('1597', '79', 'Dimataling');
INSERT INTO `geo_cities` VALUES ('1598', '79', 'Dinas');
INSERT INTO `geo_cities` VALUES ('1599', '79', 'Dumalinao');
INSERT INTO `geo_cities` VALUES ('1600', '79', 'Dumingag');
INSERT INTO `geo_cities` VALUES ('1601', '79', 'Guipos');
INSERT INTO `geo_cities` VALUES ('1602', '79', 'Josefina');
INSERT INTO `geo_cities` VALUES ('1603', '79', 'Kumalarang');
INSERT INTO `geo_cities` VALUES ('1604', '79', 'Labangan');
INSERT INTO `geo_cities` VALUES ('1605', '79', 'Lakewood');
INSERT INTO `geo_cities` VALUES ('1606', '79', 'Lapuyan');
INSERT INTO `geo_cities` VALUES ('1607', '79', 'Mahayag');
INSERT INTO `geo_cities` VALUES ('1608', '79', 'Margosatubig');
INSERT INTO `geo_cities` VALUES ('1609', '79', 'Midsalip');
INSERT INTO `geo_cities` VALUES ('1610', '79', 'Molave');
INSERT INTO `geo_cities` VALUES ('1611', '79', 'Pitogo');
INSERT INTO `geo_cities` VALUES ('1612', '79', 'Ramon Magsaysay');
INSERT INTO `geo_cities` VALUES ('1613', '79', 'San Miguel');
INSERT INTO `geo_cities` VALUES ('1614', '79', 'San Pablo');
INSERT INTO `geo_cities` VALUES ('1615', '79', 'Sominot');
INSERT INTO `geo_cities` VALUES ('1616', '79', 'Tabina');
INSERT INTO `geo_cities` VALUES ('1617', '79', 'Tambulig');
INSERT INTO `geo_cities` VALUES ('1618', '79', 'Tigbao');
INSERT INTO `geo_cities` VALUES ('1619', '79', 'Tukuran');
INSERT INTO `geo_cities` VALUES ('1620', '79', 'Vincenzo A. Sagun');
INSERT INTO `geo_cities` VALUES ('1621', '80', 'Alicia');
INSERT INTO `geo_cities` VALUES ('1622', '80', 'Buug');
INSERT INTO `geo_cities` VALUES ('1623', '80', 'Diplahan');
INSERT INTO `geo_cities` VALUES ('1624', '80', 'Imelda');
INSERT INTO `geo_cities` VALUES ('1625', '80', 'Ipil');
INSERT INTO `geo_cities` VALUES ('1626', '80', 'Kabasalan');
INSERT INTO `geo_cities` VALUES ('1627', '80', 'Mabuhay');
INSERT INTO `geo_cities` VALUES ('1628', '80', 'Malangas');
INSERT INTO `geo_cities` VALUES ('1629', '80', 'Naga');
INSERT INTO `geo_cities` VALUES ('1630', '80', 'Olutanga');
INSERT INTO `geo_cities` VALUES ('1631', '80', 'Payao');
INSERT INTO `geo_cities` VALUES ('1632', '80', 'Roseller Lim');
INSERT INTO `geo_cities` VALUES ('1633', '80', 'Siay');
INSERT INTO `geo_cities` VALUES ('1634', '80', 'Talusan');
INSERT INTO `geo_cities` VALUES ('1635', '80', 'Titay');
INSERT INTO `geo_cities` VALUES ('1636', '80', 'Tungawan');

-- ----------------------------
-- Table structure for geo_provinces
-- ----------------------------
DROP TABLE IF EXISTS `geo_provinces`;
CREATE TABLE `geo_provinces` (
  `id` int(7) NOT NULL AUTO_INCREMENT,
  `name` varchar(225) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of geo_provinces
-- ----------------------------
INSERT INTO `geo_provinces` VALUES ('1', 'Abra');
INSERT INTO `geo_provinces` VALUES ('2', 'Agusan del Norte');
INSERT INTO `geo_provinces` VALUES ('3', 'Agusan del Sur');
INSERT INTO `geo_provinces` VALUES ('4', 'Aklan');
INSERT INTO `geo_provinces` VALUES ('5', 'Albay');
INSERT INTO `geo_provinces` VALUES ('6', 'Antique');
INSERT INTO `geo_provinces` VALUES ('7', 'Apayao');
INSERT INTO `geo_provinces` VALUES ('8', 'Aurora');
INSERT INTO `geo_provinces` VALUES ('9', 'Basilan');
INSERT INTO `geo_provinces` VALUES ('10', 'Bataan');
INSERT INTO `geo_provinces` VALUES ('11', 'Batanes');
INSERT INTO `geo_provinces` VALUES ('12', 'Batangas');
INSERT INTO `geo_provinces` VALUES ('13', 'Benguet');
INSERT INTO `geo_provinces` VALUES ('14', 'Biliran');
INSERT INTO `geo_provinces` VALUES ('15', 'Bohol');
INSERT INTO `geo_provinces` VALUES ('16', 'Bukidnon');
INSERT INTO `geo_provinces` VALUES ('17', 'Bulacan');
INSERT INTO `geo_provinces` VALUES ('18', 'Cagayan');
INSERT INTO `geo_provinces` VALUES ('19', 'Camarines Norte');
INSERT INTO `geo_provinces` VALUES ('20', 'Camarines Sur');
INSERT INTO `geo_provinces` VALUES ('21', 'Camiguin');
INSERT INTO `geo_provinces` VALUES ('22', 'Capiz');
INSERT INTO `geo_provinces` VALUES ('23', 'Catanduanes');
INSERT INTO `geo_provinces` VALUES ('24', 'Cavite');
INSERT INTO `geo_provinces` VALUES ('25', 'Cebu');
INSERT INTO `geo_provinces` VALUES ('26', 'Compostela Valley');
INSERT INTO `geo_provinces` VALUES ('27', 'Cotabato');
INSERT INTO `geo_provinces` VALUES ('28', 'Davao del Norte');
INSERT INTO `geo_provinces` VALUES ('29', 'Davao del Sur');
INSERT INTO `geo_provinces` VALUES ('30', 'Davao Oriental');
INSERT INTO `geo_provinces` VALUES ('31', 'Eastern Samar');
INSERT INTO `geo_provinces` VALUES ('32', 'Guimaras');
INSERT INTO `geo_provinces` VALUES ('33', 'Ifugao');
INSERT INTO `geo_provinces` VALUES ('34', 'Ilocos Norte');
INSERT INTO `geo_provinces` VALUES ('35', 'Ilocos Sur');
INSERT INTO `geo_provinces` VALUES ('36', 'Iloilo');
INSERT INTO `geo_provinces` VALUES ('37', 'Isabela');
INSERT INTO `geo_provinces` VALUES ('38', 'Kalinga');
INSERT INTO `geo_provinces` VALUES ('39', 'La Union');
INSERT INTO `geo_provinces` VALUES ('40', 'Laguna');
INSERT INTO `geo_provinces` VALUES ('41', 'Lanao del Norte');
INSERT INTO `geo_provinces` VALUES ('42', 'Lanao del Sur');
INSERT INTO `geo_provinces` VALUES ('43', 'Leyte');
INSERT INTO `geo_provinces` VALUES ('44', 'Maguindanao');
INSERT INTO `geo_provinces` VALUES ('45', 'Marinduque');
INSERT INTO `geo_provinces` VALUES ('46', 'Masbate');
INSERT INTO `geo_provinces` VALUES ('47', 'Metro Manila');
INSERT INTO `geo_provinces` VALUES ('48', 'Misamis Occidental');
INSERT INTO `geo_provinces` VALUES ('49', 'Misamis Oriental');
INSERT INTO `geo_provinces` VALUES ('50', 'Mountain Province');
INSERT INTO `geo_provinces` VALUES ('51', 'Negros Occidental');
INSERT INTO `geo_provinces` VALUES ('52', 'Negros Oriental');
INSERT INTO `geo_provinces` VALUES ('53', 'Northern Samar');
INSERT INTO `geo_provinces` VALUES ('54', 'Nueva Ecija');
INSERT INTO `geo_provinces` VALUES ('55', 'Nueva Vizcaya');
INSERT INTO `geo_provinces` VALUES ('56', 'Occidental Mindoro');
INSERT INTO `geo_provinces` VALUES ('57', 'Oriental Mindoro');
INSERT INTO `geo_provinces` VALUES ('58', 'Palawan');
INSERT INTO `geo_provinces` VALUES ('59', 'Pampanga');
INSERT INTO `geo_provinces` VALUES ('60', 'Pangasinan');
INSERT INTO `geo_provinces` VALUES ('61', 'Quezon');
INSERT INTO `geo_provinces` VALUES ('62', 'Quirino');
INSERT INTO `geo_provinces` VALUES ('63', 'Rizal');
INSERT INTO `geo_provinces` VALUES ('64', 'Romblon');
INSERT INTO `geo_provinces` VALUES ('65', 'Samar');
INSERT INTO `geo_provinces` VALUES ('66', 'Sarangani');
INSERT INTO `geo_provinces` VALUES ('67', 'Siquijor');
INSERT INTO `geo_provinces` VALUES ('68', 'Sorsogon');
INSERT INTO `geo_provinces` VALUES ('69', 'South Cotabato');
INSERT INTO `geo_provinces` VALUES ('70', 'Southern Leyte');
INSERT INTO `geo_provinces` VALUES ('71', 'Sultan Kudarat');
INSERT INTO `geo_provinces` VALUES ('72', 'Sulu');
INSERT INTO `geo_provinces` VALUES ('73', 'Surigao del Norte');
INSERT INTO `geo_provinces` VALUES ('74', 'Surigao del Sur');
INSERT INTO `geo_provinces` VALUES ('75', 'Tarlac');
INSERT INTO `geo_provinces` VALUES ('76', 'Tawi-Tawi');
INSERT INTO `geo_provinces` VALUES ('77', 'Zambales');
INSERT INTO `geo_provinces` VALUES ('78', 'Zamboanga del Norte');
INSERT INTO `geo_provinces` VALUES ('79', 'Zamboanga del Sur');
INSERT INTO `geo_provinces` VALUES ('80', 'Zamboanga Sibugay');

-- ----------------------------
-- Table structure for migrations
-- ----------------------------
DROP TABLE IF EXISTS `migrations`;
CREATE TABLE `migrations` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `migration` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of migrations
-- ----------------------------

-- ----------------------------
-- Table structure for positions
-- ----------------------------
DROP TABLE IF EXISTS `positions`;
CREATE TABLE `positions` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `poscode` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `posdesc` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `added_by` int(11) DEFAULT NULL COMMENT 'user',
  `date_added` datetime DEFAULT NULL,
  `modified_by` int(11) DEFAULT NULL,
  `date_modified` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of positions
-- ----------------------------
INSERT INTO `positions` VALUES ('1', 'AD', 'IT Admin', '1', '2019-10-01 13:41:06', null, '2019-10-23 19:39:22');
INSERT INTO `positions` VALUES ('2', 'BM', 'Branch Manager', null, '2019-10-01 14:07:35', null, null);
INSERT INTO `positions` VALUES ('3', 'OS', 'Operations Supervisor', '1', '2019-10-01 13:42:24', null, null);
INSERT INTO `positions` VALUES ('4', 'PS', 'Plumbing Supervisor', null, '2019-10-01 14:04:09', null, null);
INSERT INTO `positions` VALUES ('5', 'OP', 'Pump Operator', null, '2019-10-01 14:04:31', null, '2019-10-01 14:32:11');
INSERT INTO `positions` VALUES ('6', 'PL', 'Plumber', null, '2019-10-01 14:03:50', null, '2019-10-23 19:39:38');
INSERT INTO `positions` VALUES ('7', 'MR', 'Meter Reader', null, '2019-10-01 14:03:35', null, '2019-10-01 14:32:24');
INSERT INTO `positions` VALUES ('8', 'AS', 'Admin Supervisor', null, '2019-10-01 14:06:05', null, null);

-- ----------------------------
-- Table structure for tbl_bacti_constant
-- ----------------------------
DROP TABLE IF EXISTS `tbl_bacti_constant`;
CREATE TABLE `tbl_bacti_constant` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'unique identifier',
  `test_analysis` varchar(255) DEFAULT NULL COMMENT 'test name',
  `uom` varchar(255) DEFAULT NULL COMMENT 'Unit of Measurement (Matrix, Units)',
  `pnsdw_limits` decimal(11,0) DEFAULT NULL COMMENT 'reference range',
  `added_by` int(11) DEFAULT NULL COMMENT 'user',
  `date_added` datetime DEFAULT NULL,
  `modified_by` int(11) DEFAULT NULL,
  `date_modified` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tbl_bacti_constant
-- ----------------------------
INSERT INTO `tbl_bacti_constant` VALUES ('1', 'APHA 9221B', 'Water, MPN/100ml', '1003', '5', '2019-09-27 16:06:52', null, null);
INSERT INTO `tbl_bacti_constant` VALUES ('2', 'APHA 9215B (Heterotrophic Plate Count)', 'Water, MPN/100 ml', '10', '5', '2019-10-01 12:57:59', null, null);
INSERT INTO `tbl_bacti_constant` VALUES ('3', 'APHA 9221E (Fecal Coliforms)', 'Water, CFU/ml', '0', '5', '2019-10-01 12:58:22', null, null);
INSERT INTO `tbl_bacti_constant` VALUES ('11', 'asd', 'asd', '123', '13', '2020-07-10 06:50:12', null, null);

-- ----------------------------
-- Table structure for tbl_bacti_details
-- ----------------------------
DROP TABLE IF EXISTS `tbl_bacti_details`;
CREATE TABLE `tbl_bacti_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'unique identifier',
  `header_id` int(11) DEFAULT NULL COMMENT 'ID from tbl_bacti_header',
  `analyses_id` int(11) DEFAULT NULL COMMENT 'ID from tbl_bacti_constant',
  `test_result` int(11) DEFAULT NULL COMMENT 'value test result',
  `findings` varchar(225) DEFAULT NULL COMMENT 'passed/failed based on limit (reference range)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tbl_bacti_details
-- ----------------------------
INSERT INTO `tbl_bacti_details` VALUES ('1', '1003', '1', '1', 'Failed');
INSERT INTO `tbl_bacti_details` VALUES ('4', '6003', '2003', '501', 'Passed');

-- ----------------------------
-- Table structure for tbl_bacti_header
-- ----------------------------
DROP TABLE IF EXISTS `tbl_bacti_header`;
CREATE TABLE `tbl_bacti_header` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'unique identifier',
  `pump_station_id` int(11) DEFAULT NULL COMMENT 'id of pump station',
  `date_sampled` date DEFAULT NULL COMMENT 'sampling date',
  `date_received` date DEFAULT NULL COMMENT 'received date by testing center',
  `date_analysed` date DEFAULT NULL COMMENT 'date analyze',
  `overall_findings` int(11) DEFAULT NULL COMMENT 'passed/failed based on the detail results',
  `added_by` int(11) DEFAULT NULL COMMENT 'user',
  `date_added` timestamp NULL DEFAULT NULL,
  `modified_by` int(11) DEFAULT NULL,
  `date_modified` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tbl_bacti_header
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_bacti_test_result_details
-- ----------------------------
DROP TABLE IF EXISTS `tbl_bacti_test_result_details`;
CREATE TABLE `tbl_bacti_test_result_details` (
  `id` int(11) NOT NULL,
  `header_id` int(11) DEFAULT NULL COMMENT 'header id from header table',
  `analysis_id` int(11) DEFAULT NULL COMMENT 'id from constant table',
  `analysis_result` varchar(255) DEFAULT NULL COMMENT 'Either numeric value or alphanumeric',
  `analysis_findings` varchar(255) DEFAULT NULL COMMENT 'Passed/Failed',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_bacti_test_result_details
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_bacti_test_result_header
-- ----------------------------
DROP TABLE IF EXISTS `tbl_bacti_test_result_header`;
CREATE TABLE `tbl_bacti_test_result_header` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `station_id` int(11) DEFAULT NULL COMMENT 'Pump Station ID (Pumphouse)',
  `ref_no` varchar(255) DEFAULT NULL COMMENT 'Lab No, Any identifier from hard copy',
  `lab_name` varchar(255) DEFAULT NULL,
  `date_sampled` date DEFAULT NULL,
  `date_recieved` date DEFAULT NULL,
  `date_analized` date DEFAULT NULL,
  `final_result` varchar(255) DEFAULT NULL COMMENT 'Passed or Failed, if one analyses fails, automatic result is Failed',
  `test_remarks` varchar(255) DEFAULT NULL COMMENT 'Optional remarks',
  `added_by` int(11) DEFAULT NULL,
  `date_added` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `date_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_bacti_test_result_header
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_chlorinator
-- ----------------------------
DROP TABLE IF EXISTS `tbl_chlorinator`;
CREATE TABLE `tbl_chlorinator` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Unique Identifier',
  `code` varchar(255) DEFAULT '',
  `brand_name` varchar(100) DEFAULT NULL,
  `model_no` varchar(20) DEFAULT NULL,
  `serial_no` varchar(20) DEFAULT NULL,
  `stroke_per_minute` double(11,0) DEFAULT 0,
  `active_status` tinyint(1) DEFAULT 0,
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

-- ----------------------------
-- Table structure for tbl_control_panel
-- ----------------------------
DROP TABLE IF EXISTS `tbl_control_panel`;
CREATE TABLE `tbl_control_panel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cp_code` varchar(8) DEFAULT NULL COMMENT 'name/description/identifier',
  `cp_type` enum('single','triple') DEFAULT NULL COMMENT 'single/triple',
  `brand_name` varchar(255) DEFAULT NULL,
  `tor_hp` int(11) DEFAULT NULL COMMENT 'thermal overload relay horse power',
  `tor_volts` int(11) DEFAULT NULL,
  `tor_amps_from` int(11) DEFAULT NULL,
  `tor_amps_to` int(11) DEFAULT NULL,
  `ouvr_volts` int(11) DEFAULT NULL COMMENT 'over/under voltage relay (volts)',
  `ouvr_phase` enum('single','double','triple') DEFAULT NULL COMMENT 'over/under voltage relay (single/double/triple)',
  `vmr_volts` int(11) DEFAULT NULL COMMENT 'voltage monitor relay (volts)',
  `vmr_phase` enum('single','double','triple') DEFAULT NULL COMMENT 'voltage monitor relay (single/double/triple)',
  `autocoil_volts` int(11) DEFAULT NULL,
  `autocoil_hp` int(11) DEFAULT NULL,
  `cb_amps` int(11) DEFAULT NULL COMMENT 'circuit breaker',
  `cb_volts` int(11) DEFAULT NULL,
  `cb_phase` enum('single','double','triple') DEFAULT NULL,
  `mc_hp` int(11) DEFAULT NULL COMMENT 'magnetic contractor',
  `mc_volts` int(11) DEFAULT NULL,
  `mc_phase` enum('single','double','triple') DEFAULT NULL,
  `oc_floatless_relay` int(11) DEFAULT NULL COMMENT 'other components / volts',
  `oc_timer_relay` int(11) DEFAULT NULL COMMENT 'volts',
  `oc_surge_suppressor` enum('single','double','triple') DEFAULT NULL COMMENT 'phase',
  `oc_voltmeter` int(11) DEFAULT NULL COMMENT 'volts',
  `oc_ammeter` int(11) DEFAULT NULL COMMENT 'amps',
  `oc_pilot_light` int(11) DEFAULT NULL COMMENT 'volts',
  `oc_selector_switch` int(11) DEFAULT NULL,
  `mts_type` enum('single','double','triple') DEFAULT NULL COMMENT 'manual transfer switch',
  `mts_breaker_fuse_rating` int(11) DEFAULT NULL COMMENT 'amps',
  `mts_no_of_poles` int(11) DEFAULT NULL,
  `transformer_date_installed` date DEFAULT NULL COMMENT 'transformer',
  `transformer_kva_size` int(11) DEFAULT NULL,
  `transformer_condition` int(11) DEFAULT NULL,
  `transformer_number` int(11) DEFAULT NULL,
  `transformer_tap_charger` int(11) DEFAULT NULL,
  `transformer_connection_type` int(11) DEFAULT NULL,
  `installation_date` date DEFAULT NULL,
  `added_by` int(11) DEFAULT NULL COMMENT 'user',
  `date_added` datetime DEFAULT NULL,
  `modified_by` int(11) DEFAULT NULL COMMENT 'user',
  `date_modified` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tbl_control_panel
-- ----------------------------
INSERT INTO `tbl_control_panel` VALUES ('1', 'CP4156G1', 'triple', 'brand', '2', '220', '500', '600', '220', 'triple', '220', 'double', '220', '2', '300', '220', 'single', '2', '220', 'double', '300', '400', 'single', '220', '560', '800', '200', 'single', '670', '5', '2020-05-03', '200', '100', '220', '600', '200', '2020-01-01', '12', '2020-06-23 15:24:09', '12', '2020-08-06 08:47:52');

-- ----------------------------
-- Table structure for tbl_electric
-- ----------------------------
DROP TABLE IF EXISTS `tbl_electric`;
CREATE TABLE `tbl_electric` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pump_no` int(11) DEFAULT NULL,
  `pump_location` varchar(255) DEFAULT NULL,
  `elec_from` date DEFAULT NULL,
  `elec_to` date DEFAULT NULL,
  `elec_bil_days` int(11) DEFAULT NULL,
  `elec_total_prod` int(11) DEFAULT NULL,
  `elec_total_kwh` int(11) DEFAULT NULL COMMENT 'kwh',
  `elec_bill_amount` int(11) DEFAULT NULL COMMENT 'php',
  `elec_cost_kwh` int(11) DEFAULT NULL COMMENT 'php',
  `elec_cost_per_cum` int(11) DEFAULT NULL COMMENT 'php',
  `elec_kwh_per_cum` int(11) DEFAULT NULL COMMENT 'kwh',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tbl_electric
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_fittings
-- ----------------------------
DROP TABLE IF EXISTS `tbl_fittings`;
CREATE TABLE `tbl_fittings` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'unique identifier',
  `gate_valve` int(11) DEFAULT NULL,
  `strainer` int(11) DEFAULT NULL,
  `stc_gbault` int(11) DEFAULT NULL,
  `check_valve` int(11) DEFAULT NULL,
  `other_settings` text DEFAULT NULL,
  `date_installed` date DEFAULT NULL,
  `added_by` int(11) DEFAULT NULL COMMENT 'user',
  `date_added` datetime DEFAULT NULL,
  `modified_by` int(11) DEFAULT NULL,
  `date_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tbl_fittings
-- ----------------------------
INSERT INTO `tbl_fittings` VALUES ('1', '200', '25', '15', '20', null, '2019-08-08', '1', '2019-10-07 14:37:15', '12', '2020-08-06 05:05:55');
INSERT INTO `tbl_fittings` VALUES ('2', '200', '25', '15', '20', null, '2019-06-06', '5', '2019-09-12 00:00:00', null, null);
INSERT INTO `tbl_fittings` VALUES ('3', '200', '25', '15', '20', null, '2019-08-08', '5', '2019-09-18 08:37:42', null, null);
INSERT INTO `tbl_fittings` VALUES ('4', '200', '25', '15', '20', null, '2019-08-08', '5', '2019-09-25 08:37:55', null, null);
INSERT INTO `tbl_fittings` VALUES ('5', '200', '25', '15', '20', null, '2019-10-01', '5', '2019-10-01 09:54:56', null, null);
INSERT INTO `tbl_fittings` VALUES ('6', '200', '25', '15', '20', null, '2020-06-23', '12', '2020-08-05 14:46:48', null, null);
INSERT INTO `tbl_fittings` VALUES ('7', '200', '25', '15', '20', null, '2020-08-06', '12', '2020-08-06 02:08:28', '12', '2020-08-06 02:10:39');

-- ----------------------------
-- Table structure for tbl_flow_meter
-- ----------------------------
DROP TABLE IF EXISTS `tbl_flow_meter`;
CREATE TABLE `tbl_flow_meter` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Unique Identifier',
  `fm_code` varchar(255) DEFAULT NULL,
  `brand_name` varchar(255) DEFAULT NULL,
  `fm_meter_no` int(11) DEFAULT NULL,
  `fm_size` int(11) DEFAULT NULL COMMENT 'size in inches',
  `fm_type` varchar(255) DEFAULT NULL,
  `fm_initial_reading` int(11) DEFAULT NULL,
  `flow_meter_status` varchar(255) DEFAULT NULL COMMENT '0-stock/1-installed/2-deffective',
  `fm_date_installed` date DEFAULT NULL COMMENT 'installation date',
  `is_calibrated` varchar(6) DEFAULT NULL COMMENT 'yes/no',
  `installation_reading` int(11) DEFAULT NULL,
  `last_reading` int(11) DEFAULT NULL,
  `fittings_id` int(11) DEFAULT NULL,
  `added_by` int(11) DEFAULT NULL COMMENT 'user',
  `date_added` datetime DEFAULT NULL,
  `modified_by` int(11) DEFAULT NULL,
  `date_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tbl_flow_meter
-- ----------------------------
INSERT INTO `tbl_flow_meter` VALUES ('1', 'FM-01-0001', 'Tokico', '89776', '20', 'turbine', '1', 'installed', '2020-08-14', 'no', '9999', '60', '1', '1', '2019-10-01 14:46:06', '12', '2020-08-06 05:05:55');
INSERT INTO `tbl_flow_meter` VALUES ('2', 'FM-01-0002', 'Tokico', '75644', '20', 'Turbine', '2', 'Installed', '2019-05-05', 'No', '9', '9', '2', '1', '2019-10-01 14:46:19', null, null);
INSERT INTO `tbl_flow_meter` VALUES ('3', 'FM-01-0003', 'E-jet', '57982', '20', 'Turbine', '0', 'Installed', '2019-09-09', 'No', '333', '9', '3', '1', '2019-10-01 14:46:29', null, null);
INSERT INTO `tbl_flow_meter` VALUES ('4', 'FM-01-0004', 'E-jet', '23109', '25', 'Vortex', '0', 'Installed', '2019-05-05', 'No', '5555', '6666', '4', '1', '2019-10-01 14:46:42', null, null);
INSERT INTO `tbl_flow_meter` VALUES ('5', 'FM-01-0005', 'Tokico', '33419', '25', 'Vortex', '1', 'Installed', '2019-10-01', 'No', '1', '1', '5', '5', '2019-10-01 10:16:57', null, null);
INSERT INTO `tbl_flow_meter` VALUES ('7', 'FM-01-0006', 'E-jet', '45882', '25', 'vortex', '2', 'available', '2020-06-23', 'yes', '2', null, '6', '12', '2020-08-05 14:46:48', null, null);
INSERT INTO `tbl_flow_meter` VALUES ('8', 'FM-01-0007', 'E-jet', '88764', '20', 'electromagnetic', '1', 'defective', '2020-08-06', 'yes', '1', null, '7', '12', '2020-08-10 17:23:32', '12', '2020-08-06 02:10:39');
INSERT INTO `tbl_flow_meter` VALUES ('9', 'FM-01-0008', 'Tokico', '15790', '20', 'Vortex', '0', null, null, null, null, null, null, '12', '2020-08-10 17:23:52', null, null);

-- ----------------------------
-- Table structure for tbl_genset
-- ----------------------------
DROP TABLE IF EXISTS `tbl_genset`;
CREATE TABLE `tbl_genset` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `brand_name` varchar(255) DEFAULT NULL,
  `serial_no` varchar(255) DEFAULT NULL,
  `ratings` int(11) DEFAULT NULL COMMENT 'KVA',
  `no_of_cylinders` int(11) DEFAULT NULL,
  `active_status` tinyint(1) DEFAULT 0,
  `date_acquired` date DEFAULT NULL,
  `added_by` int(11) DEFAULT NULL COMMENT 'user',
  `date_added` datetime DEFAULT NULL,
  `modified_by` int(11) DEFAULT NULL,
  `date_modified` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tbl_genset
-- ----------------------------
INSERT INTO `tbl_genset` VALUES ('5', 'Genset 3013', 'BTS', '20015', '100', '2', '0', '2019-09-01', '13', '2020-07-17 00:21:54', null, '2020-07-17 00:21:54');
INSERT INTO `tbl_genset` VALUES ('10', 'Genset 103', 'BRAND', '125', '98', '13', '0', '2019-09-03', '5', '2019-10-01 12:45:42', null, null);
INSERT INTO `tbl_genset` VALUES ('14', 'Genset 125', 'eee', '1111', '1', '1111', '0', '2019-09-04', '1', '2019-10-01 14:11:20', null, '2019-10-01 15:11:20');
INSERT INTO `tbl_genset` VALUES ('26', 'Genset 1003', 'Jet', '1203', '3', '1', '0', null, '1', '2019-10-01 14:07:41', null, '2019-10-01 15:07:41');
INSERT INTO `tbl_genset` VALUES ('33', 'Genset 1002', 'ASIAM101', 'ask456', '1', '1', '0', null, '1', '2019-10-01 14:04:27', null, '2019-10-01 15:04:27');
INSERT INTO `tbl_genset` VALUES ('35', 'Genset 5001', 'ford', '8520', '3', '3', '0', null, '1', '2019-10-01 14:04:44', null, '2019-10-01 15:04:43');
INSERT INTO `tbl_genset` VALUES ('36', 'Genset 0002', 'FGH123', '1112', '2223', '3', '0', null, '1', '2019-10-01 14:04:34', null, '2019-10-01 15:04:34');
INSERT INTO `tbl_genset` VALUES ('37', 'Genset 101', 'ACEJET GEN', '5002', '1', '3', '0', null, '1', '2019-10-07 13:16:19', null, null);
INSERT INTO `tbl_genset` VALUES ('38', 'Genset009', 'ACEJET', 'SDF123', '12', '123456789', '0', null, '1', '2019-10-16 08:59:39', null, null);

-- ----------------------------
-- Table structure for tbl_jo_details
-- ----------------------------
DROP TABLE IF EXISTS `tbl_jo_details`;
CREATE TABLE `tbl_jo_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jo_id` int(11) DEFAULT NULL,
  `jo_number` varchar(25) DEFAULT NULL,
  `jo_status` varchar(25) DEFAULT NULL,
  `jo_created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tbl_jo_details
-- ----------------------------
INSERT INTO `tbl_jo_details` VALUES ('1', '2294', 'JO15-0820-0047', 'dispatched', '2020-08-16 23:25:50');
INSERT INTO `tbl_jo_details` VALUES ('2', '2295', 'JO15-0820-0048', 'dispatched', '2020-08-16 23:26:15');
INSERT INTO `tbl_jo_details` VALUES ('3', '2296', 'JO15-0820-0049', 'dispatched', '2020-08-16 23:26:45');

-- ----------------------------
-- Table structure for tbl_pchem_constant
-- ----------------------------
DROP TABLE IF EXISTS `tbl_pchem_constant`;
CREATE TABLE `tbl_pchem_constant` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'unique identifier',
  `test_analysis` varchar(255) DEFAULT NULL COMMENT 'name/description/identifier',
  `uom` varchar(255) DEFAULT NULL,
  `mdl_idl_rl` varchar(255) DEFAULT NULL,
  `pnsdw_limits` int(11) DEFAULT NULL,
  `added_by` int(11) DEFAULT NULL COMMENT 'user',
  `date_added` datetime DEFAULT NULL,
  `modified_by` int(11) DEFAULT NULL,
  `date_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tbl_pchem_constant
-- ----------------------------
INSERT INTO `tbl_pchem_constant` VALUES ('1', 'Glass Electrode (ph**, Laboratory @ 25.0 C)', 'water', '3', '5', '1', '2019-10-02 07:39:13', null, null);
INSERT INTO `tbl_pchem_constant` VALUES ('2', 'Visual Comparison (Color**)', 'Water, NTU', '0.013', '30', '1', '2019-10-03 12:53:11', null, null);
INSERT INTO `tbl_pchem_constant` VALUES ('3', 'Gravimetry(TDS**)', 'Water, NTU', '0.05', '6', '1', '2019-10-02 07:18:12', null, null);

-- ----------------------------
-- Table structure for tbl_pchem_test_result_details
-- ----------------------------
DROP TABLE IF EXISTS `tbl_pchem_test_result_details`;
CREATE TABLE `tbl_pchem_test_result_details` (
  `id` int(11) NOT NULL,
  `header_id` int(11) DEFAULT NULL COMMENT 'header id from header table',
  `analysis_id` int(11) DEFAULT NULL COMMENT 'id from constant table',
  `analysis_result` varchar(255) DEFAULT NULL COMMENT 'Either numeric value or alphanumeric',
  `analysis_findings` varchar(255) DEFAULT NULL COMMENT 'Passed/Failed',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_pchem_test_result_details
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_pchem_test_result_header
-- ----------------------------
DROP TABLE IF EXISTS `tbl_pchem_test_result_header`;
CREATE TABLE `tbl_pchem_test_result_header` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `station_id` int(11) DEFAULT NULL COMMENT 'Pump Station ID (Pumphouse)',
  `ref_no` varchar(255) DEFAULT NULL COMMENT 'Lab No, Any identifier from hard copy',
  `lab_name` varchar(255) DEFAULT NULL,
  `date_sampled` date DEFAULT NULL,
  `date_recieved` date DEFAULT NULL,
  `date_analized` date DEFAULT NULL,
  `final_result` varchar(255) DEFAULT NULL COMMENT 'Passed or Failed, if one analyses fails, automatic result is Failed',
  `test_remarks` varchar(255) DEFAULT NULL COMMENT 'Optional remarks',
  `added_by` int(11) DEFAULT NULL,
  `date_added` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `date_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_pchem_test_result_header
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_pressure
-- ----------------------------
DROP TABLE IF EXISTS `tbl_pressure`;
CREATE TABLE `tbl_pressure` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pump_id` int(11) DEFAULT NULL,
  `p_point_address` varchar(255) DEFAULT '',
  `p_point_no` int(11) DEFAULT NULL,
  `date_installed` date DEFAULT NULL,
  `gauge_no` int(11) DEFAULT NULL,
  `initial_reading` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `date_acquired` date DEFAULT NULL,
  `added_by` int(11) DEFAULT NULL COMMENT 'user',
  `date_added` datetime DEFAULT NULL,
  `modified_by` int(11) DEFAULT NULL,
  `date_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tbl_pressure
-- ----------------------------
INSERT INTO `tbl_pressure` VALUES ('1', '2', 'Lot 5 Blk 11 Sampaguita  St.', '100019', '2020-06-01', '40', '1', 'Good.', '2020-05-10', '12', '2020-06-15 06:47:47', '12', '2020-08-12 16:37:55');
INSERT INTO `tbl_pressure` VALUES ('2', '1', '#632 Blk 2 Narra St.', '100021', '2020-08-11', '51', '0', 'Good', null, '12', '2020-08-10 19:36:02', '12', '2020-08-12 16:37:24');

-- ----------------------------
-- Table structure for tbl_pressure_gauge
-- ----------------------------
DROP TABLE IF EXISTS `tbl_pressure_gauge`;
CREATE TABLE `tbl_pressure_gauge` (
  `id` int(11) NOT NULL,
  `gauge_no` varchar(25) DEFAULT NULL,
  `brand_name` varchar(25) DEFAULT NULL,
  `serial_no` varchar(25) DEFAULT NULL,
  `min_psi` int(11) DEFAULT NULL,
  `max_psi` int(11) DEFAULT NULL,
  `added_by` int(11) DEFAULT NULL,
  `date_added` datetime DEFAULT NULL,
  `modified_by` int(11) DEFAULT NULL,
  `date_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tbl_pressure_gauge
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_pumps
-- ----------------------------
DROP TABLE IF EXISTS `tbl_pumps`;
CREATE TABLE `tbl_pumps` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'unique identifier',
  `pump_code` varchar(255) DEFAULT NULL COMMENT 'name/description/identifier',
  `pump_type` int(11) DEFAULT NULL,
  `brand_name` varchar(255) DEFAULT NULL,
  `serial_no` varchar(255) DEFAULT NULL,
  `model_no` varchar(255) DEFAULT NULL,
  `stages` int(11) DEFAULT NULL,
  `pump_status` tinyint(1) DEFAULT 0,
  `date_installed` date DEFAULT NULL,
  `added_by` int(11) DEFAULT NULL COMMENT 'user',
  `date_added` timestamp NULL DEFAULT NULL,
  `modified_by` int(11) DEFAULT NULL,
  `date_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tbl_pumps
-- ----------------------------
INSERT INTO `tbl_pumps` VALUES ('1', 'Code 01', '1', 'Brand 1', 'Serial - 0110', 'Model 1', '200', '1', '2019-09-17', '1', '2019-09-17 00:00:00', '1', '2019-09-17 15:50:56');
INSERT INTO `tbl_pumps` VALUES ('2', 'Code 02', '2', 'Brand 2', 'Serial - 002', 'Model 2', '200', '2', '2019-09-17', '1', '2019-09-17 00:00:00', '1', '2019-09-17 15:49:40');
INSERT INTO `tbl_pumps` VALUES ('3', 'Code 03', '2', 'Brand 3', 'Serial - 002', 'Model 2', '300', '2', '2019-09-17', '1', '2019-09-17 00:00:00', '1', '2019-09-17 15:50:02');
INSERT INTO `tbl_pumps` VALUES ('4', 'Code 04', '2', 'Brand 4', 'Serial - 002', 'Model 2', '400', '2', '2019-09-17', '1', '2019-09-17 06:58:55', '1', '2019-09-17 15:50:22');
INSERT INTO `tbl_pumps` VALUES ('5', 'Code 05', '3', 'Brand 5', 'Serial - 00031', 'Model - 01', '500', '2', '2019-09-18', '1', '2019-09-17 15:14:49', '1', '2019-09-17 15:50:32');

-- ----------------------------
-- Table structure for tbl_pump_station
-- ----------------------------
DROP TABLE IF EXISTS `tbl_pump_station`;
CREATE TABLE `tbl_pump_station` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `branch_id` int(11) DEFAULT NULL COMMENT 'From Branch Table',
  `station_code` varchar(255) DEFAULT '' COMMENT 'name/description/identifier',
  `lot_size` int(11) DEFAULT NULL COMMENT 'Lot size in sq/m',
  `pump_location` text DEFAULT NULL COMMENT 'Pump Station complete address',
  `acquisition_type` enum('lease','renting','owned') DEFAULT NULL COMMENT 'owned/renting/lease',
  `date_established` datetime DEFAULT NULL,
  `date_constructed` datetime DEFAULT NULL,
  `date_installed` datetime DEFAULT NULL,
  `remarks` text DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL COMMENT 'name/description/identifier',
  `well_id` int(11) DEFAULT NULL COMMENT 'From Wells Table',
  `cp_id` int(11) DEFAULT NULL COMMENT 'From Control Panel Table',
  `motor_id` int(11) DEFAULT NULL COMMENT 'From Submersible Motor Table',
  `pump_id` int(11) DEFAULT NULL COMMENT 'From Submersible Pump Table',
  `cable_size` int(11) DEFAULT NULL COMMENT 'diameter of cable in inches',
  `cable_length` int(11) DEFAULT NULL,
  `add_length` int(11) DEFAULT NULL COMMENT 'additional cable length in meter',
  `total_length` int(11) DEFAULT NULL COMMENT 'total cable length in meter',
  `riser_pipe_size` int(11) DEFAULT NULL,
  `riser_pipe_settings` int(11) DEFAULT NULL,
  `riser_pipe_o_capacity` int(11) DEFAULT NULL,
  `fm_id` int(11) DEFAULT NULL COMMENT 'From Flow Meter Table',
  `fittings_id` int(11) DEFAULT NULL COMMENT 'From Fittings Table',
  `chlorinator_id` int(11) DEFAULT NULL COMMENT 'From Chlorintor Table',
  `genset_id` int(11) DEFAULT NULL COMMENT 'Genset Table',
  `added_by` int(11) DEFAULT NULL COMMENT 'From User Table (logged user)',
  `date_added` datetime DEFAULT NULL COMMENT 'Date Created',
  `modified_by` int(11) DEFAULT NULL,
  `date_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tbl_pump_station
-- ----------------------------
INSERT INTO `tbl_pump_station` VALUES ('1', null, '', '450', '#3 Blk 8', 'owned', '2020-05-01 00:00:00', null, null, null, 'PH-202001-03', '2', '1', '1', '1', '300', '500', '20', '520', null, null, null, '2', '2', '3', '10', '12', '2020-06-24 09:04:36', '12', '2020-08-04 14:23:25');
INSERT INTO `tbl_pump_station` VALUES ('2', null, '', '350', '#1 Blk 9', 'renting', '2020-06-01 00:00:00', null, null, null, 'PH-202005-02', '3', '1', '1', '2', '150', '400', '100', '500', null, null, null, '4', '5', '13', '36', '12', '2020-06-24 09:07:34', null, null);
INSERT INTO `tbl_pump_station` VALUES ('3', null, '', '450', '#4 Blk 7', 'owned', '2020-05-01 00:00:00', null, null, null, 'PH-202001-01', '2', '1', '1', '3', '300', '200', '10', '210', null, null, null, '2', '2', '3', '10', '12', '2020-06-27 02:20:13', null, null);
INSERT INTO `tbl_pump_station` VALUES ('6', null, 'code123', '123', null, 'renting', '2020-01-01 00:00:00', null, null, null, 'code123', '3', '1', '2', '4', '123', '321', '213', '534', null, null, null, '3', null, '7', '35', '12', '2020-08-13 11:12:02', null, null);
INSERT INTO `tbl_pump_station` VALUES ('7', null, 'code123', '123', null, 'renting', '2020-01-01 00:00:00', null, null, null, 'code123', '3', '1', '2', '4', '123', '321', '213', '534', null, null, null, '3', null, '7', '35', '12', '2020-08-13 11:12:02', null, null);
INSERT INTO `tbl_pump_station` VALUES ('8', null, 'code', '200', ', Angeles City', 'renting', '2020-08-14 00:00:00', null, null, null, 'code', '3', '1', '1', '5', '123', '123', '123', '246', null, null, null, '1', null, '4', '10', '12', '2020-08-13 23:04:19', null, null);

-- ----------------------------
-- Table structure for tbl_riser_pipe
-- ----------------------------
DROP TABLE IF EXISTS `tbl_riser_pipe`;
CREATE TABLE `tbl_riser_pipe` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Unique Identifier',
  `code` varchar(255) DEFAULT NULL COMMENT 'name/description/identifier',
  `size` int(11) DEFAULT NULL COMMENT 'inches',
  `settings` int(11) DEFAULT NULL COMMENT '"ft',
  `output_capacity` int(11) DEFAULT NULL COMMENT 'GPM',
  `lps` decimal(11,2) DEFAULT NULL COMMENT 'liter/sec',
  `active_status` tinyint(1) DEFAULT 0,
  `added_by` int(11) DEFAULT NULL COMMENT 'user',
  `date_added` datetime DEFAULT NULL,
  `modified_by` int(11) DEFAULT NULL,
  `date_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tbl_riser_pipe
-- ----------------------------
INSERT INTO `tbl_riser_pipe` VALUES ('1', 'RP-0001-02', '30', '200', '400', '25.24', '0', '12', '2020-05-23 01:46:06', '13', '2020-07-17 00:21:10');
INSERT INTO `tbl_riser_pipe` VALUES ('2', 'RP-0001-03', '25', '250', '300', '18.93', '0', '12', '2020-05-23 01:46:45', '12', '2020-06-13 00:17:18');
INSERT INTO `tbl_riser_pipe` VALUES ('3', 'RP-0001-04', '20', '200', '500', '150.00', '0', '12', '2020-05-23 01:47:22', '12', '2020-05-28 03:01:58');
INSERT INTO `tbl_riser_pipe` VALUES ('4', 'RP-0001-05', '20', '200', '500', '150.00', '0', '12', '2020-05-23 01:47:56', '12', '2020-06-05 01:26:53');
INSERT INTO `tbl_riser_pipe` VALUES ('5', 'RP-0001-06', '25', '150', '500', '150.00', '2', '12', '2020-05-23 01:51:39', null, null);
INSERT INTO `tbl_riser_pipe` VALUES ('6', 'RP-0001-07', '25', '200', '500', '150.00', '1', '12', '2020-05-23 01:52:04', null, null);
INSERT INTO `tbl_riser_pipe` VALUES ('7', 'RP-0001-08', '20', '200', '500', '150.00', '2', '12', '2020-05-23 01:52:27', null, null);
INSERT INTO `tbl_riser_pipe` VALUES ('8', 'RP-0001-09', '25', '200', '500', '150.00', '1', '12', '2020-05-23 01:52:47', null, null);
INSERT INTO `tbl_riser_pipe` VALUES ('9', 'RP-0001-10', '20', '200', '500', '150.00', '1', '12', '2020-05-23 01:53:07', null, null);
INSERT INTO `tbl_riser_pipe` VALUES ('10', 'RP-0001-11', '25', '200', '500', '150.00', '1', '12', '2020-05-23 01:53:34', null, null);
INSERT INTO `tbl_riser_pipe` VALUES ('11', 'RP-0001-12', '20', '200', '500', '150.00', '1', '12', '2020-05-25 09:38:33', null, null);
INSERT INTO `tbl_riser_pipe` VALUES ('12', 'RP-0001-13', '20', '150', '500', '150.00', '1', '12', '2020-05-25 10:11:28', '12', '2020-05-26 04:01:03');
INSERT INTO `tbl_riser_pipe` VALUES ('13', 'RP-0001-14', '20', '200', '500', '150.00', '1', '12', '2020-05-26 04:03:04', null, null);
INSERT INTO `tbl_riser_pipe` VALUES ('14', 'RP-0001-16', '20', '200', '500', '150.00', '1', '12', '2020-05-26 04:05:18', '12', '2020-05-26 04:06:47');
INSERT INTO `tbl_riser_pipe` VALUES ('15', 'RP-0001-17', '20', '150', '500', '200.00', '1', '12', '2020-05-26 04:17:29', null, null);
INSERT INTO `tbl_riser_pipe` VALUES ('16', 'RP-0001-18', '20', '150', '500', '150.00', '1', '12', '2020-05-26 05:08:11', null, null);
INSERT INTO `tbl_riser_pipe` VALUES ('17', 'RP-0001-19', '20', '200', '500', '150.00', '1', '12', '2020-05-26 05:09:28', null, null);
INSERT INTO `tbl_riser_pipe` VALUES ('18', 'RP-0001-20', '20', '200', '500', '120.00', '1', '12', '2020-05-26 05:10:08', null, null);
INSERT INTO `tbl_riser_pipe` VALUES ('19', 'RP-0001-21', '20', '150', '500', '130.00', '1', '12', '2020-05-26 05:14:28', '12', '2020-05-26 05:15:47');
INSERT INTO `tbl_riser_pipe` VALUES ('20', 'RP-0001-22', '20', '150', '500', '130.00', '1', '12', '2020-05-26 05:16:37', null, null);
INSERT INTO `tbl_riser_pipe` VALUES ('21', 'asdf', '200', '125', '600', '37.85', '0', '12', '2020-06-06 15:09:34', '12', '2020-08-06 05:04:48');
INSERT INTO `tbl_riser_pipe` VALUES ('22', 'RP-0001-23', '300', '100', '600', '37.85', '0', '12', '2020-06-11 01:32:09', null, null);

-- ----------------------------
-- Table structure for tbl_sub_cable
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sub_cable`;
CREATE TABLE `tbl_sub_cable` (
  `id` double NOT NULL COMMENT 'Unique Identifier',
  `date_installed` date DEFAULT NULL,
  `cable_size` int(11) DEFAULT NULL COMMENT 'diameter of cable in inches',
  `calbe_length` int(11) DEFAULT NULL COMMENT 'length of cable in meter',
  `add_length` int(11) DEFAULT NULL COMMENT 'additional cable length in meter',
  `total_length` int(11) DEFAULT NULL COMMENT 'total cable length in meter',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tbl_sub_cable
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_sub_motor
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sub_motor`;
CREATE TABLE `tbl_sub_motor` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'unique identifier',
  `motor_code` varchar(255) DEFAULT NULL COMMENT 'name/description/identifier',
  `power_wattages` int(11) DEFAULT NULL,
  `brand_name` varchar(255) DEFAULT NULL,
  `serial_no` varchar(255) DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL COMMENT 'horsepower (HP)',
  `op_voltage` int(11) DEFAULT NULL COMMENT 'voltage (v)',
  `phase` varchar(255) DEFAULT NULL COMMENT 'voltage (v)',
  `min_current` int(11) DEFAULT NULL COMMENT 'amp',
  `max_current` int(11) DEFAULT NULL COMMENT 'amp',
  `motor_status` tinyint(4) DEFAULT NULL COMMENT '0-stock/1-installed/2-deffective',
  `installation_date` date DEFAULT NULL,
  `added_by` int(11) DEFAULT NULL COMMENT 'user',
  `date_added` datetime DEFAULT NULL,
  `modified_by` int(11) DEFAULT NULL,
  `date_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tbl_sub_motor
-- ----------------------------
INSERT INTO `tbl_sub_motor` VALUES ('1', 'Test Code', '600', 'Test Brand 123', '00001abc', '400', '600', '300', '700', '10000', '1', '2019-09-16', '1', '2019-09-16 00:00:00', '1', '2019-09-17 00:00:00');
INSERT INTO `tbl_sub_motor` VALUES ('2', '123ef0gh', '800', 'Test Brand 23', '0987771-tp', '500', '500', '300', '700', '10000', '1', '2001-03-01', '1', '2019-09-16 00:00:00', '1', '2019-09-17 00:00:00');

-- ----------------------------
-- Table structure for tbl_wells
-- ----------------------------
DROP TABLE IF EXISTS `tbl_wells`;
CREATE TABLE `tbl_wells` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Unique Identifier',
  `well_code` varchar(255) DEFAULT NULL COMMENT 'name/description/identifier',
  `well_location` text DEFAULT NULL,
  `address` text DEFAULT NULL,
  `date_constructed` date DEFAULT NULL,
  `casing_size` int(11) DEFAULT NULL,
  `well_depth` int(11) DEFAULT NULL,
  `static_water_level` int(11) DEFAULT NULL,
  `pumping_water_level` int(11) DEFAULT NULL,
  `draw_down` int(11) DEFAULT NULL,
  `quality` text DEFAULT NULL,
  `active_status` tinyint(1) DEFAULT 0,
  `added_by` int(11) DEFAULT NULL COMMENT 'user',
  `date_added` datetime DEFAULT NULL,
  `modified_by` int(11) DEFAULT NULL,
  `date_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tbl_wells
-- ----------------------------
INSERT INTO `tbl_wells` VALUES ('1', 'Well Code 1', 'Mabalacat City', '123 st. blk 8', '2019-08-01', '10', '350', '200', '200', '100', 'Good Quality.', '0', '1', '2019-09-12 00:00:00', '13', '2020-07-17 00:20:06');
INSERT INTO `tbl_wells` VALUES ('2', 'Well Code 2', 'Angeles City', null, '2003-01-05', '5', '500', '250', '100', '200', 'Good', '0', '1', '2019-09-12 00:00:00', '1', '2019-09-17 00:00:00');
INSERT INTO `tbl_wells` VALUES ('3', 'well code 3', 'Angeles City', null, '2001-01-08', '10', '380', '200', '120', '100', 'Fair', '0', '1', '2019-09-12 00:00:00', null, null);

-- ----------------------------
-- Table structure for tran_prod_chlorine_details
-- ----------------------------
DROP TABLE IF EXISTS `tran_prod_chlorine_details`;
CREATE TABLE `tran_prod_chlorine_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Auto increment index field',
  `header_id` int(11) DEFAULT NULL COMMENT 'inertgetid upon uploading',
  `time_replenished` time DEFAULT NULL COMMENT 'Time of Replenishment',
  `chlorine_type` varchar(255) DEFAULT NULL COMMENT 'Type of Chlorine (Granules/Powder/Liquid)',
  `vol_chlorine` double DEFAULT NULL COMMENT 'Weight of Chlorine replenished in Kgs',
  `chlorine_remarks` varchar(255) DEFAULT NULL COMMENT 'Chlorine replenishment remarks',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tran_prod_chlorine_details
-- ----------------------------

-- ----------------------------
-- Table structure for tran_prod_fm_rdg_details
-- ----------------------------
DROP TABLE IF EXISTS `tran_prod_fm_rdg_details`;
CREATE TABLE `tran_prod_fm_rdg_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Auto increment index field',
  `header_id` int(11) DEFAULT NULL COMMENT 'inertgetid upon uploading',
  `time_rdg` time DEFAULT NULL COMMENT 'Time Reading',
  `prev_rdg` double DEFAULT NULL COMMENT 'Previous Reading',
  `pres_rdg` double DEFAULT NULL COMMENT 'Present Reading',
  `total_cum` double(11,0) DEFAULT 0 COMMENT 'Total production in CuM',
  `fm_rdg_remarks` varchar(255) DEFAULT NULL COMMENT 'FM Reading remarks',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tran_prod_fm_rdg_details
-- ----------------------------

-- ----------------------------
-- Table structure for tran_prod_header
-- ----------------------------
DROP TABLE IF EXISTS `tran_prod_header`;
CREATE TABLE `tran_prod_header` (
  `id` int(11) NOT NULL,
  `ref_id` int(11) DEFAULT NULL COMMENT 'header_id from android',
  `ref_code` varchar(255) DEFAULT NULL COMMENT 'pump and date for referrence',
  `station_id` int(11) DEFAULT NULL COMMENT 'pump house id from android',
  `tran_date` date DEFAULT NULL COMMENT 'transaction date from android',
  `total_op_hrs` decimal(11,2) DEFAULT NULL COMMENT 'Total operating hours',
  `total_production` double DEFAULT NULL COMMENT 'Total production',
  `total_drain_duration` double DEFAULT NULL COMMENT 'Total drain hours in mins',
  `total_drain_cum` double DEFAULT NULL COMMENT 'Total drain in CuM',
  `max_psi_rdg` varchar(255) DEFAULT NULL,
  `min_psi_rdg` varchar(255) DEFAULT NULL,
  `ave_psi_rdg` varchar(255) DEFAULT NULL,
  `time_chlorine_replenish` time DEFAULT NULL COMMENT 'Time of replenishment Chlorine',
  `chlorine_type` varchar(255) DEFAULT NULL COMMENT 'Type of chlorine',
  `total_volume_chlorine` double(11,0) DEFAULT NULL COMMENT 'Total volume of chlorine in Kgs',
  `operator_id1` int(11) DEFAULT NULL COMMENT 'Day Shift operator',
  `operator_id2` int(11) DEFAULT NULL COMMENT 'Night Shift operator',
  `added_by` int(11) DEFAULT NULL COMMENT 'from android',
  `date_added` datetime DEFAULT NULL,
  `modified_by` int(11) DEFAULT NULL COMMENT 'from android',
  `date_modified` datetime DEFAULT NULL COMMENT 'from android',
  `uploaded_by` int(11) DEFAULT NULL COMMENT 'from android',
  `date_uploaded` timestamp NULL DEFAULT NULL COMMENT 'Date and time of uploading',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tran_prod_header
-- ----------------------------

-- ----------------------------
-- Table structure for tran_prod_on_off_details
-- ----------------------------
DROP TABLE IF EXISTS `tran_prod_on_off_details`;
CREATE TABLE `tran_prod_on_off_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `header_id` int(11) DEFAULT NULL COMMENT 'inertid upon uploading',
  `time_on` time DEFAULT NULL COMMENT 'Time On',
  `time_off` time DEFAULT NULL COMMENT 'Time Off',
  `power_source_id` enum('1=Electricity','0=Genset') DEFAULT '1=Electricity' COMMENT 'Power Source from android',
  `total_op_hrs` decimal(11,2) DEFAULT 0.00 COMMENT 'Total operating hours',
  `total_drain_time` double(11,0) DEFAULT 0 COMMENT 'Total Drain in mins',
  `total_drain_cum` double(11,0) DEFAULT 0 COMMENT 'Total Drain CuM',
  `on_off_remarks` varchar(255) DEFAULT NULL COMMENT 'Time on/off remarks',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tran_prod_on_off_details
-- ----------------------------

-- ----------------------------
-- Table structure for tran_prod_psi_rdg_details
-- ----------------------------
DROP TABLE IF EXISTS `tran_prod_psi_rdg_details`;
CREATE TABLE `tran_prod_psi_rdg_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Auto increment index field',
  `header_id` int(11) DEFAULT NULL COMMENT 'inertgetid upon uploading',
  `time_rdg` time DEFAULT NULL COMMENT 'Time Reading',
  `psi_rdg` double DEFAULT NULL COMMENT 'PSI Reading',
  `fm_rdg_remarks` varchar(255) DEFAULT NULL COMMENT 'PSI Reading remarks',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tran_prod_psi_rdg_details
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `remember_token` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT 1,
  `added_by` int(11) DEFAULT NULL COMMENT 'user',
  `date_added` datetime DEFAULT NULL,
  `modified_by` int(11) DEFAULT NULL,
  `date_modified` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'admin', '$2y$10$kY7fziD8pe9FjmZXVdoX9O9EoKKkqkfRqjVR/2jNQT5.gyJRdxxfG', '9G7WtkIOMTKGwKmaQpIzJkgE89zZWJ9SzigBMK567leaJzcQZXQ61K1aMMMC', '1', null, '2019-10-01 13:59:23', null, '2019-10-23 15:53:34');
INSERT INTO `users` VALUES ('12', 'cbit-erick', '$2y$10$wZky0rCXMZ7u/s5AaRTIyOdxaEBszxSJhbsWTMjni8G8cupweqLJW', '1rCrbyX9D53F3Ce0XhanWkiDf6VVZcIwtdZlcG0pGSZx7D1mKudU2Xs8A4Pb', '1', null, '2019-10-24 11:32:41', null, '2020-08-14 07:08:14');
INSERT INTO `users` VALUES ('13', 'cbit-yumi', '$2y$10$N6/TbaSX.rO3vi49fAuNOuu6QTac.17FGwuStdaMtvkemQd4d5BX.', null, '1', null, '2019-10-24 11:34:02', null, null);
INSERT INTO `users` VALUES ('14', 'cbit-joko', '$2y$10$kpTDE7Lsjp97rD.1z6E8ZORGkU0T80teidtjQnVW87GWgRxUvCy6u', null, '1', null, '2019-10-24 11:35:42', null, null);
INSERT INTO `users` VALUES ('15', 'cbit-jay', '$2y$10$PQQQMMbM5UsRjdEavigIo.z3p.Ww2hYmLIm47osnjvwGC5PgET7PC', null, '0', null, '2019-10-24 11:41:09', null, '2020-06-18 10:18:47');
INSERT INTO `users` VALUES ('16', 'cbit-randy', '$2y$10$FgI1pQsjgRQWHa1NiAOiZuIeaS2/.i.QVVbWWdIZlr6.0yZVjCmXK', null, '0', null, '2019-10-28 14:51:09', null, '2020-06-18 10:18:50');
INSERT INTO `users` VALUES ('17', 'cbit-kevin', '$2y$10$Dlk/svRd3ooPLBAXg3wzsuvqo68.saXYinlVUgB8uNJB4YPmEs65q', null, '0', null, '2019-10-28 15:44:47', null, '2020-06-18 10:18:53');
INSERT INTO `users` VALUES ('18', 'asdf', '$2y$10$Zmc8PorxPJQswXFn3gKg5.Xufkss.iHW7/ObW3VNGA2sKe8yMIk9m', null, '1', '12', '2020-06-19 06:09:34', null, null);
INSERT INTO `users` VALUES ('19', 'john', '$2y$10$eCldZU/GNxhvHC4FI4NYvuhQJQl.BgNq0EBqd8SDLUpplzoQWzKB.', null, '1', '18', '2020-06-19 06:47:47', null, null);
INSERT INTO `users` VALUES ('20', 'charm', '$2y$10$uFrba1RG9LBbONAdIIFe9.ruA5/ITXYtaiIk04oatCJU8Bj9168ka', null, '1', '18', '2020-06-19 06:49:57', null, null);
INSERT INTO `users` VALUES ('21', 'jeffrey', '$2y$10$yG1.kKhaSLyt4BsCapVgpO7qdUalvB6gscLgNWTe1mFuJlQLKaw6G', null, '1', '18', '2020-06-19 06:52:15', null, null);

-- ----------------------------
-- Table structure for user_positions
-- ----------------------------
DROP TABLE IF EXISTS `user_positions`;
CREATE TABLE `user_positions` (
  `user_id` int(11) NOT NULL,
  `pos_id` int(11) NOT NULL,
  `is_primary` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of user_positions
-- ----------------------------
INSERT INTO `user_positions` VALUES ('1', '1', '1');
INSERT INTO `user_positions` VALUES ('12', '1', '1');
INSERT INTO `user_positions` VALUES ('12', '7', '0');
INSERT INTO `user_positions` VALUES ('13', '1', '1');
INSERT INTO `user_positions` VALUES ('13', '4', '0');
INSERT INTO `user_positions` VALUES ('14', '1', '1');
INSERT INTO `user_positions` VALUES ('14', '6', '0');
INSERT INTO `user_positions` VALUES ('15', '2', '1');
INSERT INTO `user_positions` VALUES ('15', '3', '0');
INSERT INTO `user_positions` VALUES ('16', '1', '1');
INSERT INTO `user_positions` VALUES ('16', '2', '0');
INSERT INTO `user_positions` VALUES ('16', '3', '0');
INSERT INTO `user_positions` VALUES ('16', '4', '0');
INSERT INTO `user_positions` VALUES ('17', '2', '1');
INSERT INTO `user_positions` VALUES ('17', '8', '0');
INSERT INTO `user_positions` VALUES ('18', '1', '1');
INSERT INTO `user_positions` VALUES ('18', '2', '0');
INSERT INTO `user_positions` VALUES ('19', '1', '1');
INSERT INTO `user_positions` VALUES ('19', '2', '0');
INSERT INTO `user_positions` VALUES ('19', '3', '0');
INSERT INTO `user_positions` VALUES ('20', '2', '1');
INSERT INTO `user_positions` VALUES ('20', '3', '0');
INSERT INTO `user_positions` VALUES ('20', '4', '0');
INSERT INTO `user_positions` VALUES ('21', '8', '1');
INSERT INTO `user_positions` VALUES ('21', '2', '0');
INSERT INTO `user_positions` VALUES ('21', '3', '0');
