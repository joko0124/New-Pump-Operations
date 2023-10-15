/*
Navicat MySQL Data Transfer

Source Server         : My Connection
Source Server Version : 50505
Source Host           : 127.0.0.1:3306
Source Database       : erick-pump-operations

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2020-09-22 09:09:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for constant__authorized_cons
-- ----------------------------
DROP TABLE IF EXISTS `constant__authorized_cons`;
CREATE TABLE `constant__authorized_cons` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ac_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

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
  `ac_class_desc` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of constant__authorized_cons_class
-- ----------------------------
INSERT INTO `constant__authorized_cons_class` VALUES ('1', '1', 'Billed Metered Consumption');
INSERT INTO `constant__authorized_cons_class` VALUES ('2', '1', 'Billed Unmetered Concumption');
INSERT INTO `constant__authorized_cons_class` VALUES ('3', '2', 'Unbilled Metered Consumption');
INSERT INTO `constant__authorized_cons_class` VALUES ('4', '2', 'Unbilled Unmetered Concumption');

-- ----------------------------
-- Table structure for constant__authorized_cons_type
-- ----------------------------
DROP TABLE IF EXISTS `constant__authorized_cons_type`;
CREATE TABLE `constant__authorized_cons_type` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) DEFAULT NULL,
  `ac_type_desc` varchar(255) DEFAULT '',
  `is_mainline` tinyint(1) DEFAULT 0,
  `is_service_connection` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

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
-- Table structure for constant_jo_categories
-- ----------------------------
DROP TABLE IF EXISTS `constant_jo_categories`;
CREATE TABLE `constant_jo_categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `elite_id` int(11) DEFAULT NULL,
  `jo_code` varchar(10) DEFAULT '',
  `jo_desc` varchar(50) DEFAULT '',
  `with_type` int(1) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of constant_jo_categories
-- ----------------------------
INSERT INTO `constant_jo_categories` VALUES ('1', '9', 'SAS', 'Service Application Survey', '0');
INSERT INTO `constant_jo_categories` VALUES ('2', '1', 'NC', 'New Connection', '0');
INSERT INTO `constant_jo_categories` VALUES ('3', '4', 'RC', 'Reconnection', '0');
INSERT INTO `constant_jo_categories` VALUES ('4', '6', 'MC', 'Metering Concerns', '1');
INSERT INTO `constant_jo_categories` VALUES ('5', '5', 'CM', 'Change Meter', '0');
INSERT INTO `constant_jo_categories` VALUES ('6', '7', 'SL', 'Service Level', '1');
INSERT INTO `constant_jo_categories` VALUES ('7', '10', 'SV\r\n', 'Survey\r\n', '1');
INSERT INTO `constant_jo_categories` VALUES ('8', '13', 'CAC', 'Change Account Classification', '0');
INSERT INTO `constant_jo_categories` VALUES ('9', '2', 'DC-DA\r\n', 'Disconnection - Delinquent Account', '0');
INSERT INTO `constant_jo_categories` VALUES ('10', '3', 'DC-CR\r\n', 'Disconnection - Customer Request\r\n', '0');
INSERT INTO `constant_jo_categories` VALUES ('11', '8', 'RM', 'Repairs and Maintenance\r\n', '1');
INSERT INTO `constant_jo_categories` VALUES ('12', '11', 'IC\r\n', 'Illegal Connection\r\n', '0');
INSERT INTO `constant_jo_categories` VALUES ('13', '12', 'OT', 'Others\r\n', '0');

-- ----------------------------
-- Table structure for constant_jo_type
-- ----------------------------
DROP TABLE IF EXISTS `constant_jo_type`;
CREATE TABLE `constant_jo_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `elite_id` int(11) DEFAULT NULL,
  `cat_id` int(11) DEFAULT NULL,
  `type_code` varchar(10) DEFAULT '',
  `type_desc` varchar(30) DEFAULT '',
  `is_cust_related` int(1) DEFAULT 0,
  `has_water_losses` int(1) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of constant_jo_type
-- ----------------------------

-- ----------------------------
-- Table structure for constant_water_losses
-- ----------------------------
DROP TABLE IF EXISTS `constant_water_losses`;
CREATE TABLE `constant_water_losses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `wl_desc` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of constant_water_losses
-- ----------------------------
INSERT INTO `constant_water_losses` VALUES ('1', 'Commercial/Apparent Losses');
INSERT INTO `constant_water_losses` VALUES ('2', 'Real/Physical Losses');

-- ----------------------------
-- Table structure for constant_water_losses_class
-- ----------------------------
DROP TABLE IF EXISTS `constant_water_losses_class`;
CREATE TABLE `constant_water_losses_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `water_losses_id` int(11) DEFAULT NULL,
  `wl_class_desc` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of constant_water_losses_class
-- ----------------------------
INSERT INTO `constant_water_losses_class` VALUES ('1', '1', 'Unauthorized Consumption');
INSERT INTO `constant_water_losses_class` VALUES ('2', '1', 'Metering Inaccuracies');
INSERT INTO `constant_water_losses_class` VALUES ('3', '2', 'Accounted Leakage');
INSERT INTO `constant_water_losses_class` VALUES ('4', '2', 'Unaccounted Leakage');

-- ----------------------------
-- Table structure for constant_water_losses_type
-- ----------------------------
DROP TABLE IF EXISTS `constant_water_losses_type`;
CREATE TABLE `constant_water_losses_type` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) DEFAULT NULL,
  `wl_type_desc` varchar(255) DEFAULT '',
  `is_mainline` tinyint(1) DEFAULT 0,
  `is_service_connection` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of constant_water_losses_type
-- ----------------------------
INSERT INTO `constant_water_losses_type` VALUES ('1', '1', 'Illegal Connection', '1', '1');
INSERT INTO `constant_water_losses_type` VALUES ('2', '2', 'Meter Test', '0', '1');
INSERT INTO `constant_water_losses_type` VALUES ('3', '3', 'Busted Service Pipes', '0', '1');
INSERT INTO `constant_water_losses_type` VALUES ('4', '3', 'Busted Mainlines', '1', '0');
INSERT INTO `constant_water_losses_type` VALUES ('5', '3', 'Busted Meter Stand', '1', '1');
INSERT INTO `constant_water_losses_type` VALUES ('6', '3', 'Tail Piece Leak', '1', '1');
INSERT INTO `constant_water_losses_type` VALUES ('7', '3', 'Gate Valve Leak', '1', '1');
INSERT INTO `constant_water_losses_type` VALUES ('8', '3', 'Disconnect Mainline', '1', '0');
INSERT INTO `constant_water_losses_type` VALUES ('9', '3', 'Disconnect Meter Stand', '1', '0');
INSERT INTO `constant_water_losses_type` VALUES ('10', '3', 'Installation of Appurtenances', '1', '0');
INSERT INTO `constant_water_losses_type` VALUES ('11', '3', 'Interconnection of Mainline', '1', '0');
INSERT INTO `constant_water_losses_type` VALUES ('12', '4', 'Other Leakages', '1', '0');

-- ----------------------------
-- Table structure for constant_water_supplies
-- ----------------------------
DROP TABLE IF EXISTS `constant_water_supplies`;
CREATE TABLE `constant_water_supplies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `supply_type` tinyint(1) DEFAULT NULL,
  `supply_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of constant_water_supplies
-- ----------------------------
INSERT INTO `constant_water_supplies` VALUES ('1', '0', 'Drain');
INSERT INTO `constant_water_supplies` VALUES ('2', '1', 'Production');
INSERT INTO `constant_water_supplies` VALUES ('3', '2', 'Bulk');
INSERT INTO `constant_water_supplies` VALUES ('4', '3', 'Lorry');

-- ----------------------------
-- Table structure for tbl_jo_details
-- ----------------------------
DROP TABLE IF EXISTS `tbl_jo_details`;
CREATE TABLE `tbl_jo_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jo_id` int(11) DEFAULT NULL,
  `jo_number` varchar(25) DEFAULT NULL,
  `jo_cat` varchar(20) DEFAULT NULL,
  `ref_id` int(11) DEFAULT NULL,
  `ref_num` varchar(255) DEFAULT NULL,
  `cust_name` varchar(255) DEFAULT '',
  `cust_address` varchar(255) DEFAULT '',
  `acct_class` varchar(10) DEFAULT '0',
  `acct_subclass` varchar(10) DEFAULT '',
  `book_id` int(11) DEFAULT NULL,
  `meter_id` int(11) DEFAULT NULL,
  `prev_rdg` int(11) DEFAULT NULL,
  `final_rdg` int(11) DEFAULT NULL,
  `lowest_cum` int(11) DEFAULT NULL,
  `highest_cum` int(11) DEFAULT NULL,
  `contact_no` varchar(255) DEFAULT NULL,
  `jo_status` tinyint(1) DEFAULT 0 COMMENT '0',
  `jo_created_at` datetime DEFAULT NULL COMMENT 'Date and Time Created (From Elite)',
  `dispatched_at` datetime DEFAULT NULL COMMENT 'Date and Time Dispatched (From Elite)',
  `dispatched_to` varchar(125) DEFAULT NULL COMMENT 'Dispatched To (From Elite)',
  `assigned_at` datetime DEFAULT NULL COMMENT 'Date and Time Dispatched (From P-Op)',
  `assigned_to` varchar(125) DEFAULT NULL COMMENT 'Dispatched To (From P-Op)',
  `date_started` datetime DEFAULT NULL COMMENT 'Date and Time Started (From App)',
  `date_finished` datetime DEFAULT NULL COMMENT 'Date and Time Finished (From App)',
  `date_returned` datetime DEFAULT NULL COMMENT 'Save to Elite Date returned (Actual Date Time Uploading)',
  `date_uploaded` datetime DEFAULT NULL COMMENT 'Date and Time Uploaded (From P-Op)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tbl_jo_details
-- ----------------------------
INSERT INTO `tbl_jo_details` VALUES ('1', '1515', 'JO25-0920-0018', 'SAS', '339', 'AP25-0920-0005', 'dantes, marian rivera', '123 DALIG, BALAGTAS, BULACAN', 'RES', 'A', null, null, null, null, null, null, '09265146296', '0', '2020-09-12 00:00:00', '2020-09-18 09:26:00', '7', null, null, null, null, null, null);
INSERT INTO `tbl_jo_details` VALUES ('2', '1517', 'JO25-0920-0020', 'SAS', '341', 'AP25-0920-0007', 'fernandez, joko pogi', '4342535 BOROL 1ST, BALAGTAS, BULACAN', 'RES', 'A', null, null, null, null, null, null, '09265146296', '0', '2020-09-17 00:00:00', '2020-09-18 09:27:00', '12', null, null, null, null, null, null);
INSERT INTO `tbl_jo_details` VALUES ('3', '1518', 'JO25-0920-0021', 'SAS', '342', 'AP25-0920-0008', 'juco, yumi salangsang', '434343 BOROL 1ST, BALAGTAS, BULACAN', 'RES', 'A', null, null, null, null, null, null, '4353545', '0', '2020-09-17 00:00:00', '2020-09-18 09:27:00', '8', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for tbl_leak_volume_constant
-- ----------------------------
DROP TABLE IF EXISTS `tbl_leak_volume_constant`;
CREATE TABLE `tbl_leak_volume_constant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `PSI` int(255) DEFAULT 0,
  `PipeSize` varchar(255) DEFAULT NULL,
  `PVC_ACP_PE` double(12,9) DEFAULT NULL,
  `CI_GI` double(12,9) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=829 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tbl_leak_volume_constant
-- ----------------------------
INSERT INTO `tbl_leak_volume_constant` VALUES ('1', '1', '1/2', '0.025497261', '0.022947535');
INSERT INTO `tbl_leak_volume_constant` VALUES ('2', '2', '1/2', '0.026772124', '0.024094911');
INSERT INTO `tbl_leak_volume_constant` VALUES ('3', '3', '1/2', '0.034421302', '0.030979172');
INSERT INTO `tbl_leak_volume_constant` VALUES ('4', '4', '1/2', '0.045895069', '0.041305562');
INSERT INTO `tbl_leak_volume_constant` VALUES ('5', '5', '1/2', '0.057368837', '0.051631953');
INSERT INTO `tbl_leak_volume_constant` VALUES ('6', '6', '1/2', '0.061193426', '0.055074083');
INSERT INTO `tbl_leak_volume_constant` VALUES ('7', '7', '1/2', '0.062468289', '0.056221460');
INSERT INTO `tbl_leak_volume_constant` VALUES ('8', '8', '1/2', '0.071392330', '0.064253097');
INSERT INTO `tbl_leak_volume_constant` VALUES ('9', '9', '1/2', '0.080316371', '0.072284734');
INSERT INTO `tbl_leak_volume_constant` VALUES ('10', '10', '1/2', '0.089240412', '0.080316371');
INSERT INTO `tbl_leak_volume_constant` VALUES ('11', '11', '1/2', '0.098164454', '0.088348008');
INSERT INTO `tbl_leak_volume_constant` VALUES ('12', '12', '1/2', '0.107088495', '0.096379645');
INSERT INTO `tbl_leak_volume_constant` VALUES ('13', '13', '1/2', '0.116012536', '0.104411283');
INSERT INTO `tbl_leak_volume_constant` VALUES ('14', '14', '1/2', '0.124936577', '0.112442920');
INSERT INTO `tbl_leak_volume_constant` VALUES ('15', '15', '1/2', '0.133860619', '0.120474557');
INSERT INTO `tbl_leak_volume_constant` VALUES ('16', '16', '1/2', '0.142784660', '0.128506194');
INSERT INTO `tbl_leak_volume_constant` VALUES ('17', '17', '1/2', '0.151708701', '0.136537831');
INSERT INTO `tbl_leak_volume_constant` VALUES ('18', '18', '1/2', '0.160632742', '0.144569468');
INSERT INTO `tbl_leak_volume_constant` VALUES ('19', '19', '1/2', '0.169556784', '0.152601105');
INSERT INTO `tbl_leak_volume_constant` VALUES ('20', '20', '1/2', '0.178480825', '0.160632742');
INSERT INTO `tbl_leak_volume_constant` VALUES ('21', '21', '1/2', '0.187404866', '0.168664380');
INSERT INTO `tbl_leak_volume_constant` VALUES ('22', '22', '1/2', '0.196328907', '0.176696017');
INSERT INTO `tbl_leak_volume_constant` VALUES ('23', '23', '1/2', '0.205252949', '0.184727654');
INSERT INTO `tbl_leak_volume_constant` VALUES ('24', '24', '1/2', '0.214176990', '0.192759291');
INSERT INTO `tbl_leak_volume_constant` VALUES ('25', '25', '1/2', '0.223101031', '0.200790928');
INSERT INTO `tbl_leak_volume_constant` VALUES ('26', '26', '1/2', '0.232025072', '0.208822565');
INSERT INTO `tbl_leak_volume_constant` VALUES ('27', '27', '1/2', '0.240949114', '0.216854202');
INSERT INTO `tbl_leak_volume_constant` VALUES ('28', '28', '1/2', '0.249873155', '0.224885839');
INSERT INTO `tbl_leak_volume_constant` VALUES ('29', '29', '1/2', '0.258797196', '0.232917477');
INSERT INTO `tbl_leak_volume_constant` VALUES ('30', '30', '1/2', '0.267721237', '0.240949114');
INSERT INTO `tbl_leak_volume_constant` VALUES ('31', '31', '1/2', '0.276645279', '0.248980751');
INSERT INTO `tbl_leak_volume_constant` VALUES ('32', '32', '1/2', '0.285569320', '0.257012388');
INSERT INTO `tbl_leak_volume_constant` VALUES ('33', '33', '1/2', '0.294493361', '0.265044025');
INSERT INTO `tbl_leak_volume_constant` VALUES ('34', '34', '1/2', '0.303417402', '0.273075662');
INSERT INTO `tbl_leak_volume_constant` VALUES ('35', '35', '1/2', '0.312341444', '0.281107299');
INSERT INTO `tbl_leak_volume_constant` VALUES ('36', '36', '1/2', '0.321265485', '0.289138936');
INSERT INTO `tbl_leak_volume_constant` VALUES ('37', '37', '1/2', '0.330189526', '0.297170574');
INSERT INTO `tbl_leak_volume_constant` VALUES ('38', '38', '1/2', '0.339113567', '0.305202211');
INSERT INTO `tbl_leak_volume_constant` VALUES ('39', '39', '1/2', '0.348037609', '0.313233848');
INSERT INTO `tbl_leak_volume_constant` VALUES ('40', '40', '1/2', '0.356961650', '0.321265485');
INSERT INTO `tbl_leak_volume_constant` VALUES ('41', '41', '1/2', '0.365885691', '0.329297122');
INSERT INTO `tbl_leak_volume_constant` VALUES ('42', '42', '1/2', '0.374809732', '0.337328759');
INSERT INTO `tbl_leak_volume_constant` VALUES ('43', '43', '1/2', '0.383733774', '0.345360396');
INSERT INTO `tbl_leak_volume_constant` VALUES ('44', '44', '1/2', '0.392657815', '0.353392033');
INSERT INTO `tbl_leak_volume_constant` VALUES ('45', '45', '1/2', '0.401581856', '0.361423671');
INSERT INTO `tbl_leak_volume_constant` VALUES ('46', '46', '1/2', '0.410505897', '0.369455308');
INSERT INTO `tbl_leak_volume_constant` VALUES ('47', '47', '1/2', '0.419429939', '0.377486945');
INSERT INTO `tbl_leak_volume_constant` VALUES ('48', '48', '1/2', '0.428353980', '0.385518582');
INSERT INTO `tbl_leak_volume_constant` VALUES ('49', '49', '1/2', '0.437278021', '0.393550219');
INSERT INTO `tbl_leak_volume_constant` VALUES ('50', '50', '1/2', '0.446202062', '0.401581856');
INSERT INTO `tbl_leak_volume_constant` VALUES ('51', '1', '3/4', '0.034927754', '0.031434979');
INSERT INTO `tbl_leak_volume_constant` VALUES ('52', '2', '3/4', '0.036674142', '0.033006728');
INSERT INTO `tbl_leak_volume_constant` VALUES ('53', '3', '3/4', '0.047152468', '0.042437222');
INSERT INTO `tbl_leak_volume_constant` VALUES ('54', '4', '3/4', '0.062869958', '0.056582962');
INSERT INTO `tbl_leak_volume_constant` VALUES ('55', '5', '3/4', '0.078587447', '0.070728703');
INSERT INTO `tbl_leak_volume_constant` VALUES ('56', '6', '3/4', '0.083826611', '0.075443949');
INSERT INTO `tbl_leak_volume_constant` VALUES ('57', '7', '3/4', '0.085572998', '0.077015698');
INSERT INTO `tbl_leak_volume_constant` VALUES ('58', '8', '3/4', '0.097797712', '0.088017941');
INSERT INTO `tbl_leak_volume_constant` VALUES ('59', '9', '3/4', '0.110022426', '0.099020184');
INSERT INTO `tbl_leak_volume_constant` VALUES ('60', '10', '3/4', '0.122247140', '0.110022426');
INSERT INTO `tbl_leak_volume_constant` VALUES ('61', '11', '3/4', '0.134471854', '0.121024669');
INSERT INTO `tbl_leak_volume_constant` VALUES ('62', '12', '3/4', '0.146696568', '0.132026912');
INSERT INTO `tbl_leak_volume_constant` VALUES ('63', '13', '3/4', '0.158921282', '0.143029154');
INSERT INTO `tbl_leak_volume_constant` VALUES ('64', '14', '3/4', '0.171145997', '0.154031397');
INSERT INTO `tbl_leak_volume_constant` VALUES ('65', '15', '3/4', '0.183370711', '0.165033640');
INSERT INTO `tbl_leak_volume_constant` VALUES ('66', '16', '3/4', '0.195595425', '0.176035882');
INSERT INTO `tbl_leak_volume_constant` VALUES ('67', '17', '3/4', '0.207820139', '0.187038125');
INSERT INTO `tbl_leak_volume_constant` VALUES ('68', '18', '3/4', '0.220044853', '0.198040367');
INSERT INTO `tbl_leak_volume_constant` VALUES ('69', '19', '3/4', '0.232269567', '0.209042610');
INSERT INTO `tbl_leak_volume_constant` VALUES ('70', '20', '3/4', '0.244494281', '0.220044853');
INSERT INTO `tbl_leak_volume_constant` VALUES ('71', '21', '3/4', '0.256718995', '0.231047095');
INSERT INTO `tbl_leak_volume_constant` VALUES ('72', '22', '3/4', '0.268943709', '0.242049338');
INSERT INTO `tbl_leak_volume_constant` VALUES ('73', '23', '3/4', '0.281168423', '0.253051581');
INSERT INTO `tbl_leak_volume_constant` VALUES ('74', '24', '3/4', '0.293393137', '0.264053823');
INSERT INTO `tbl_leak_volume_constant` VALUES ('75', '25', '3/4', '0.305617851', '0.275056066');
INSERT INTO `tbl_leak_volume_constant` VALUES ('76', '26', '3/4', '0.317842565', '0.286058308');
INSERT INTO `tbl_leak_volume_constant` VALUES ('77', '27', '3/4', '0.330067279', '0.297060551');
INSERT INTO `tbl_leak_volume_constant` VALUES ('78', '28', '3/4', '0.342291993', '0.308062794');
INSERT INTO `tbl_leak_volume_constant` VALUES ('79', '29', '3/4', '0.354516707', '0.319065036');
INSERT INTO `tbl_leak_volume_constant` VALUES ('80', '30', '3/4', '0.366741421', '0.330067279');
INSERT INTO `tbl_leak_volume_constant` VALUES ('81', '31', '3/4', '0.378966135', '0.341069522');
INSERT INTO `tbl_leak_volume_constant` VALUES ('82', '32', '3/4', '0.391190849', '0.352071764');
INSERT INTO `tbl_leak_volume_constant` VALUES ('83', '33', '3/4', '0.403415563', '0.363074007');
INSERT INTO `tbl_leak_volume_constant` VALUES ('84', '34', '3/4', '0.415640277', '0.374076250');
INSERT INTO `tbl_leak_volume_constant` VALUES ('85', '35', '3/4', '0.427864991', '0.385078492');
INSERT INTO `tbl_leak_volume_constant` VALUES ('86', '36', '3/4', '0.440089705', '0.396080735');
INSERT INTO `tbl_leak_volume_constant` VALUES ('87', '37', '3/4', '0.452314419', '0.407082977');
INSERT INTO `tbl_leak_volume_constant` VALUES ('88', '38', '3/4', '0.464539133', '0.418085220');
INSERT INTO `tbl_leak_volume_constant` VALUES ('89', '39', '3/4', '0.476763848', '0.429087463');
INSERT INTO `tbl_leak_volume_constant` VALUES ('90', '40', '3/4', '0.488988562', '0.440089705');
INSERT INTO `tbl_leak_volume_constant` VALUES ('91', '41', '3/4', '0.501213276', '0.451091948');
INSERT INTO `tbl_leak_volume_constant` VALUES ('92', '42', '3/4', '0.513437990', '0.462094191');
INSERT INTO `tbl_leak_volume_constant` VALUES ('93', '43', '3/4', '0.525662704', '0.473096433');
INSERT INTO `tbl_leak_volume_constant` VALUES ('94', '44', '3/4', '0.537887418', '0.484098676');
INSERT INTO `tbl_leak_volume_constant` VALUES ('95', '45', '3/4', '0.550112132', '0.495100919');
INSERT INTO `tbl_leak_volume_constant` VALUES ('96', '46', '3/4', '0.562336846', '0.506103161');
INSERT INTO `tbl_leak_volume_constant` VALUES ('97', '47', '3/4', '0.574561560', '0.517105404');
INSERT INTO `tbl_leak_volume_constant` VALUES ('98', '48', '3/4', '0.586786274', '0.528107646');
INSERT INTO `tbl_leak_volume_constant` VALUES ('99', '49', '3/4', '0.599010988', '0.539109889');
INSERT INTO `tbl_leak_volume_constant` VALUES ('100', '50', '3/4', '0.611235702', '0.550112132');
INSERT INTO `tbl_leak_volume_constant` VALUES ('101', '1', '1', '0.057225633', '0.051503070');
INSERT INTO `tbl_leak_volume_constant` VALUES ('102', '2', '1', '0.065809478', '0.059228530');
INSERT INTO `tbl_leak_volume_constant` VALUES ('103', '3', '1', '0.072962682', '0.065666414');
INSERT INTO `tbl_leak_volume_constant` VALUES ('104', '4', '1', '0.097283576', '0.087555218');
INSERT INTO `tbl_leak_volume_constant` VALUES ('105', '5', '1', '0.121604470', '0.109444023');
INSERT INTO `tbl_leak_volume_constant` VALUES ('106', '6', '1', '0.128757674', '0.115881906');
INSERT INTO `tbl_leak_volume_constant` VALUES ('107', '7', '1', '0.150217286', '0.135195557');
INSERT INTO `tbl_leak_volume_constant` VALUES ('108', '8', '1', '0.171676898', '0.154509209');
INSERT INTO `tbl_leak_volume_constant` VALUES ('109', '9', '1', '0.193136511', '0.173822860');
INSERT INTO `tbl_leak_volume_constant` VALUES ('110', '10', '1', '0.214596123', '0.193136511');
INSERT INTO `tbl_leak_volume_constant` VALUES ('111', '11', '1', '0.220318686', '0.198286818');
INSERT INTO `tbl_leak_volume_constant` VALUES ('112', '12', '1', '0.240347658', '0.216312892');
INSERT INTO `tbl_leak_volume_constant` VALUES ('113', '13', '1', '0.260376629', '0.234338966');
INSERT INTO `tbl_leak_volume_constant` VALUES ('114', '14', '1', '0.280405601', '0.252365041');
INSERT INTO `tbl_leak_volume_constant` VALUES ('115', '15', '1', '0.300434572', '0.270391115');
INSERT INTO `tbl_leak_volume_constant` VALUES ('116', '16', '1', '0.320463544', '0.288417189');
INSERT INTO `tbl_leak_volume_constant` VALUES ('117', '17', '1', '0.340492515', '0.306443264');
INSERT INTO `tbl_leak_volume_constant` VALUES ('118', '18', '1', '0.360521487', '0.324469338');
INSERT INTO `tbl_leak_volume_constant` VALUES ('119', '19', '1', '0.380550458', '0.342495412');
INSERT INTO `tbl_leak_volume_constant` VALUES ('120', '20', '1', '0.400579430', '0.360521487');
INSERT INTO `tbl_leak_volume_constant` VALUES ('121', '21', '1', '0.420608401', '0.378547561');
INSERT INTO `tbl_leak_volume_constant` VALUES ('122', '22', '1', '0.440637373', '0.396573635');
INSERT INTO `tbl_leak_volume_constant` VALUES ('123', '23', '1', '0.460666344', '0.414599710');
INSERT INTO `tbl_leak_volume_constant` VALUES ('124', '24', '1', '0.480695316', '0.432625784');
INSERT INTO `tbl_leak_volume_constant` VALUES ('125', '25', '1', '0.500724287', '0.450651858');
INSERT INTO `tbl_leak_volume_constant` VALUES ('126', '26', '1', '0.520753258', '0.468677933');
INSERT INTO `tbl_leak_volume_constant` VALUES ('127', '27', '1', '0.540782230', '0.486704007');
INSERT INTO `tbl_leak_volume_constant` VALUES ('128', '28', '1', '0.560811201', '0.504730081');
INSERT INTO `tbl_leak_volume_constant` VALUES ('129', '29', '1', '0.580840173', '0.522756156');
INSERT INTO `tbl_leak_volume_constant` VALUES ('130', '30', '1', '0.600869144', '0.540782230');
INSERT INTO `tbl_leak_volume_constant` VALUES ('131', '31', '1', '0.620898116', '0.558808304');
INSERT INTO `tbl_leak_volume_constant` VALUES ('132', '32', '1', '0.640927087', '0.576834379');
INSERT INTO `tbl_leak_volume_constant` VALUES ('133', '33', '1', '0.660956059', '0.594860453');
INSERT INTO `tbl_leak_volume_constant` VALUES ('134', '34', '1', '0.680985030', '0.612886527');
INSERT INTO `tbl_leak_volume_constant` VALUES ('135', '35', '1', '0.701014002', '0.630912602');
INSERT INTO `tbl_leak_volume_constant` VALUES ('136', '36', '1', '0.721042973', '0.648938676');
INSERT INTO `tbl_leak_volume_constant` VALUES ('137', '37', '1', '0.741071945', '0.666964750');
INSERT INTO `tbl_leak_volume_constant` VALUES ('138', '38', '1', '0.761100916', '0.684990825');
INSERT INTO `tbl_leak_volume_constant` VALUES ('139', '39', '1', '0.781129888', '0.703016899');
INSERT INTO `tbl_leak_volume_constant` VALUES ('140', '40', '1', '0.801158859', '0.721042973');
INSERT INTO `tbl_leak_volume_constant` VALUES ('141', '41', '1', '0.821187831', '0.739069048');
INSERT INTO `tbl_leak_volume_constant` VALUES ('142', '42', '1', '0.841216802', '0.757095122');
INSERT INTO `tbl_leak_volume_constant` VALUES ('143', '43', '1', '0.861245774', '0.775121196');
INSERT INTO `tbl_leak_volume_constant` VALUES ('144', '44', '1', '0.881274745', '0.793147271');
INSERT INTO `tbl_leak_volume_constant` VALUES ('145', '45', '1', '0.901303717', '0.811173345');
INSERT INTO `tbl_leak_volume_constant` VALUES ('146', '46', '1', '0.921332688', '0.829199419');
INSERT INTO `tbl_leak_volume_constant` VALUES ('147', '47', '1', '0.941361660', '0.847225494');
INSERT INTO `tbl_leak_volume_constant` VALUES ('148', '48', '1', '0.961390631', '0.865251568');
INSERT INTO `tbl_leak_volume_constant` VALUES ('149', '49', '1', '0.981419603', '0.883277642');
INSERT INTO `tbl_leak_volume_constant` VALUES ('150', '50', '1', '1.001448574', '0.901303717');
INSERT INTO `tbl_leak_volume_constant` VALUES ('151', '1', '1.5', '0.065529083', '0.058976175');
INSERT INTO `tbl_leak_volume_constant` VALUES ('152', '2', '1.5', '0.074180345', '0.066762311');
INSERT INTO `tbl_leak_volume_constant` VALUES ('153', '3', '1.5', '0.081389731', '0.073250758');
INSERT INTO `tbl_leak_volume_constant` VALUES ('154', '4', '1.5', '0.105901641', '0.095311477');
INSERT INTO `tbl_leak_volume_constant` VALUES ('155', '5', '1.5', '0.130413551', '0.117372196');
INSERT INTO `tbl_leak_volume_constant` VALUES ('156', '6', '1.5', '0.137622937', '0.123860643');
INSERT INTO `tbl_leak_volume_constant` VALUES ('157', '7', '1.5', '0.159251093', '0.143325983');
INSERT INTO `tbl_leak_volume_constant` VALUES ('158', '8', '1.5', '0.180879249', '0.162791324');
INSERT INTO `tbl_leak_volume_constant` VALUES ('159', '9', '1.5', '0.202507405', '0.182256664');
INSERT INTO `tbl_leak_volume_constant` VALUES ('160', '10', '1.5', '0.224135561', '0.201722005');
INSERT INTO `tbl_leak_volume_constant` VALUES ('161', '11', '1.5', '0.229903069', '0.206912762');
INSERT INTO `tbl_leak_volume_constant` VALUES ('162', '12', '1.5', '0.250089348', '0.225080413');
INSERT INTO `tbl_leak_volume_constant` VALUES ('163', '13', '1.5', '0.270275627', '0.243248065');
INSERT INTO `tbl_leak_volume_constant` VALUES ('164', '14', '1.5', '0.290461906', '0.261415716');
INSERT INTO `tbl_leak_volume_constant` VALUES ('165', '15', '1.5', '0.310648185', '0.279583367');
INSERT INTO `tbl_leak_volume_constant` VALUES ('166', '16', '1.5', '0.330834464', '0.297751018');
INSERT INTO `tbl_leak_volume_constant` VALUES ('167', '17', '1.5', '0.351020743', '0.315918669');
INSERT INTO `tbl_leak_volume_constant` VALUES ('168', '18', '1.5', '0.371207022', '0.334086320');
INSERT INTO `tbl_leak_volume_constant` VALUES ('169', '19', '1.5', '0.391393301', '0.352253971');
INSERT INTO `tbl_leak_volume_constant` VALUES ('170', '20', '1.5', '0.411579580', '0.370421622');
INSERT INTO `tbl_leak_volume_constant` VALUES ('171', '21', '1.5', '0.431765859', '0.388589274');
INSERT INTO `tbl_leak_volume_constant` VALUES ('172', '22', '1.5', '0.451952138', '0.406756925');
INSERT INTO `tbl_leak_volume_constant` VALUES ('173', '23', '1.5', '0.472138418', '0.424924576');
INSERT INTO `tbl_leak_volume_constant` VALUES ('174', '24', '1.5', '0.492324697', '0.443092227');
INSERT INTO `tbl_leak_volume_constant` VALUES ('175', '25', '1.5', '0.512510976', '0.461259878');
INSERT INTO `tbl_leak_volume_constant` VALUES ('176', '26', '1.5', '0.532697255', '0.479427529');
INSERT INTO `tbl_leak_volume_constant` VALUES ('177', '27', '1.5', '0.552883534', '0.497595180');
INSERT INTO `tbl_leak_volume_constant` VALUES ('178', '28', '1.5', '0.573069813', '0.515762831');
INSERT INTO `tbl_leak_volume_constant` VALUES ('179', '29', '1.5', '0.593256092', '0.533930482');
INSERT INTO `tbl_leak_volume_constant` VALUES ('180', '30', '1.5', '0.613442371', '0.552098134');
INSERT INTO `tbl_leak_volume_constant` VALUES ('181', '31', '1.5', '0.633628650', '0.570265785');
INSERT INTO `tbl_leak_volume_constant` VALUES ('182', '32', '1.5', '0.653814929', '0.588433436');
INSERT INTO `tbl_leak_volume_constant` VALUES ('183', '33', '1.5', '0.674001208', '0.606601087');
INSERT INTO `tbl_leak_volume_constant` VALUES ('184', '34', '1.5', '0.694187487', '0.624768738');
INSERT INTO `tbl_leak_volume_constant` VALUES ('185', '35', '1.5', '0.714373766', '0.642936389');
INSERT INTO `tbl_leak_volume_constant` VALUES ('186', '36', '1.5', '0.734560045', '0.661104040');
INSERT INTO `tbl_leak_volume_constant` VALUES ('187', '37', '1.5', '0.754746324', '0.679271691');
INSERT INTO `tbl_leak_volume_constant` VALUES ('188', '38', '1.5', '0.774932603', '0.697439343');
INSERT INTO `tbl_leak_volume_constant` VALUES ('189', '39', '1.5', '0.795118882', '0.715606994');
INSERT INTO `tbl_leak_volume_constant` VALUES ('190', '40', '1.5', '0.815305161', '0.733774645');
INSERT INTO `tbl_leak_volume_constant` VALUES ('191', '41', '1.5', '0.835491440', '0.751942296');
INSERT INTO `tbl_leak_volume_constant` VALUES ('192', '42', '1.5', '0.855677719', '0.770109947');
INSERT INTO `tbl_leak_volume_constant` VALUES ('193', '43', '1.5', '0.875863998', '0.788277598');
INSERT INTO `tbl_leak_volume_constant` VALUES ('194', '44', '1.5', '0.896050277', '0.806445249');
INSERT INTO `tbl_leak_volume_constant` VALUES ('195', '45', '1.5', '0.916236556', '0.824612900');
INSERT INTO `tbl_leak_volume_constant` VALUES ('196', '46', '1.5', '0.936422835', '0.842780552');
INSERT INTO `tbl_leak_volume_constant` VALUES ('197', '47', '1.5', '0.956609114', '0.860948203');
INSERT INTO `tbl_leak_volume_constant` VALUES ('198', '48', '1.5', '0.976795393', '0.879115854');
INSERT INTO `tbl_leak_volume_constant` VALUES ('199', '49', '1.5', '0.996981672', '0.897283505');
INSERT INTO `tbl_leak_volume_constant` VALUES ('200', '50', '1.5', '1.017167951', '0.915451156');
INSERT INTO `tbl_leak_volume_constant` VALUES ('201', '1', '2', '0.078815176', '0.070933659');
INSERT INTO `tbl_leak_volume_constant` VALUES ('202', '2', '2', '0.087574311', '0.078816880');
INSERT INTO `tbl_leak_volume_constant` VALUES ('203', '3', '2', '0.094873591', '0.085386231');
INSERT INTO `tbl_leak_volume_constant` VALUES ('204', '4', '2', '0.119691140', '0.107722026');
INSERT INTO `tbl_leak_volume_constant` VALUES ('205', '5', '2', '0.144508690', '0.130057821');
INSERT INTO `tbl_leak_volume_constant` VALUES ('206', '6', '2', '0.151807969', '0.136627172');
INSERT INTO `tbl_leak_volume_constant` VALUES ('207', '7', '2', '0.173705807', '0.156335226');
INSERT INTO `tbl_leak_volume_constant` VALUES ('208', '8', '2', '0.195603644', '0.176043280');
INSERT INTO `tbl_leak_volume_constant` VALUES ('209', '9', '2', '0.217501482', '0.195751334');
INSERT INTO `tbl_leak_volume_constant` VALUES ('210', '10', '2', '0.239399320', '0.215459388');
INSERT INTO `tbl_leak_volume_constant` VALUES ('211', '11', '2', '0.245238743', '0.220714869');
INSERT INTO `tbl_leak_volume_constant` VALUES ('212', '12', '2', '0.265676725', '0.239109053');
INSERT INTO `tbl_leak_volume_constant` VALUES ('213', '13', '2', '0.286114707', '0.257503237');
INSERT INTO `tbl_leak_volume_constant` VALUES ('214', '14', '2', '0.306552689', '0.275897420');
INSERT INTO `tbl_leak_volume_constant` VALUES ('215', '15', '2', '0.326990671', '0.294291604');
INSERT INTO `tbl_leak_volume_constant` VALUES ('216', '16', '2', '0.347428653', '0.312685788');
INSERT INTO `tbl_leak_volume_constant` VALUES ('217', '17', '2', '0.367866635', '0.331079972');
INSERT INTO `tbl_leak_volume_constant` VALUES ('218', '18', '2', '0.388304617', '0.349474155');
INSERT INTO `tbl_leak_volume_constant` VALUES ('219', '19', '2', '0.408742599', '0.367868339');
INSERT INTO `tbl_leak_volume_constant` VALUES ('220', '20', '2', '0.429180581', '0.386262523');
INSERT INTO `tbl_leak_volume_constant` VALUES ('221', '21', '2', '0.449618563', '0.404656707');
INSERT INTO `tbl_leak_volume_constant` VALUES ('222', '22', '2', '0.470056545', '0.423050890');
INSERT INTO `tbl_leak_volume_constant` VALUES ('223', '23', '2', '0.490494527', '0.441445074');
INSERT INTO `tbl_leak_volume_constant` VALUES ('224', '24', '2', '0.510932509', '0.459839258');
INSERT INTO `tbl_leak_volume_constant` VALUES ('225', '25', '2', '0.531370491', '0.478233442');
INSERT INTO `tbl_leak_volume_constant` VALUES ('226', '26', '2', '0.551808473', '0.496627625');
INSERT INTO `tbl_leak_volume_constant` VALUES ('227', '27', '2', '0.572246455', '0.515021809');
INSERT INTO `tbl_leak_volume_constant` VALUES ('228', '28', '2', '0.592684436', '0.533415993');
INSERT INTO `tbl_leak_volume_constant` VALUES ('229', '29', '2', '0.613122418', '0.551810177');
INSERT INTO `tbl_leak_volume_constant` VALUES ('230', '30', '2', '0.633560400', '0.570204360');
INSERT INTO `tbl_leak_volume_constant` VALUES ('231', '31', '2', '0.653998382', '0.588598544');
INSERT INTO `tbl_leak_volume_constant` VALUES ('232', '32', '2', '0.674436364', '0.606992728');
INSERT INTO `tbl_leak_volume_constant` VALUES ('233', '33', '2', '0.694874346', '0.625386912');
INSERT INTO `tbl_leak_volume_constant` VALUES ('234', '34', '2', '0.715312328', '0.643781095');
INSERT INTO `tbl_leak_volume_constant` VALUES ('235', '35', '2', '0.735750310', '0.662175279');
INSERT INTO `tbl_leak_volume_constant` VALUES ('236', '36', '2', '0.756188292', '0.680569463');
INSERT INTO `tbl_leak_volume_constant` VALUES ('237', '37', '2', '0.776626274', '0.698963647');
INSERT INTO `tbl_leak_volume_constant` VALUES ('238', '38', '2', '0.797064256', '0.717357830');
INSERT INTO `tbl_leak_volume_constant` VALUES ('239', '39', '2', '0.817502238', '0.735752014');
INSERT INTO `tbl_leak_volume_constant` VALUES ('240', '40', '2', '0.837940220', '0.754146198');
INSERT INTO `tbl_leak_volume_constant` VALUES ('241', '41', '2', '0.858378202', '0.772540382');
INSERT INTO `tbl_leak_volume_constant` VALUES ('242', '42', '2', '0.878816184', '0.790934565');
INSERT INTO `tbl_leak_volume_constant` VALUES ('243', '43', '2', '0.899254166', '0.809328749');
INSERT INTO `tbl_leak_volume_constant` VALUES ('244', '44', '2', '0.919692148', '0.827722933');
INSERT INTO `tbl_leak_volume_constant` VALUES ('245', '45', '2', '0.940130130', '0.846117117');
INSERT INTO `tbl_leak_volume_constant` VALUES ('246', '46', '2', '0.960568112', '0.864511300');
INSERT INTO `tbl_leak_volume_constant` VALUES ('247', '47', '2', '0.981006093', '0.882905484');
INSERT INTO `tbl_leak_volume_constant` VALUES ('248', '48', '2', '1.001444075', '0.901299668');
INSERT INTO `tbl_leak_volume_constant` VALUES ('249', '49', '2', '1.021882057', '0.919693852');
INSERT INTO `tbl_leak_volume_constant` VALUES ('250', '50', '2', '1.042320039', '0.938088035');
INSERT INTO `tbl_leak_volume_constant` VALUES ('251', '1', '3', '0.097879459', '0.088091513');
INSERT INTO `tbl_leak_volume_constant` VALUES ('252', '2', '3', '0.106793381', '0.096114043');
INSERT INTO `tbl_leak_volume_constant` VALUES ('253', '3', '3', '0.114221649', '0.102799484');
INSERT INTO `tbl_leak_volume_constant` VALUES ('254', '4', '3', '0.139477762', '0.125529986');
INSERT INTO `tbl_leak_volume_constant` VALUES ('255', '5', '3', '0.164733875', '0.148260487');
INSERT INTO `tbl_leak_volume_constant` VALUES ('256', '6', '3', '0.172162143', '0.154945929');
INSERT INTO `tbl_leak_volume_constant` VALUES ('257', '7', '3', '0.194446949', '0.175002254');
INSERT INTO `tbl_leak_volume_constant` VALUES ('258', '8', '3', '0.216731754', '0.195058579');
INSERT INTO `tbl_leak_volume_constant` VALUES ('259', '9', '3', '0.239016560', '0.215114904');
INSERT INTO `tbl_leak_volume_constant` VALUES ('260', '10', '3', '0.261301365', '0.235171229');
INSERT INTO `tbl_leak_volume_constant` VALUES ('261', '11', '3', '0.267243980', '0.240519582');
INSERT INTO `tbl_leak_volume_constant` VALUES ('262', '12', '3', '0.288043132', '0.259238818');
INSERT INTO `tbl_leak_volume_constant` VALUES ('263', '13', '3', '0.308842283', '0.277958055');
INSERT INTO `tbl_leak_volume_constant` VALUES ('264', '14', '3', '0.329641435', '0.296677292');
INSERT INTO `tbl_leak_volume_constant` VALUES ('265', '15', '3', '0.350440587', '0.315396528');
INSERT INTO `tbl_leak_volume_constant` VALUES ('266', '16', '3', '0.371239739', '0.334115765');
INSERT INTO `tbl_leak_volume_constant` VALUES ('267', '17', '3', '0.392038890', '0.352835001');
INSERT INTO `tbl_leak_volume_constant` VALUES ('268', '18', '3', '0.412838042', '0.371554238');
INSERT INTO `tbl_leak_volume_constant` VALUES ('269', '19', '3', '0.433637194', '0.390273474');
INSERT INTO `tbl_leak_volume_constant` VALUES ('270', '20', '3', '0.454436346', '0.408992711');
INSERT INTO `tbl_leak_volume_constant` VALUES ('271', '21', '3', '0.475235497', '0.427711948');
INSERT INTO `tbl_leak_volume_constant` VALUES ('272', '22', '3', '0.496034649', '0.446431184');
INSERT INTO `tbl_leak_volume_constant` VALUES ('273', '23', '3', '0.516833801', '0.465150421');
INSERT INTO `tbl_leak_volume_constant` VALUES ('274', '24', '3', '0.537632953', '0.483869657');
INSERT INTO `tbl_leak_volume_constant` VALUES ('275', '25', '3', '0.558432104', '0.502588894');
INSERT INTO `tbl_leak_volume_constant` VALUES ('276', '26', '3', '0.579231256', '0.521308130');
INSERT INTO `tbl_leak_volume_constant` VALUES ('277', '27', '3', '0.600030408', '0.540027367');
INSERT INTO `tbl_leak_volume_constant` VALUES ('278', '28', '3', '0.620829560', '0.558746604');
INSERT INTO `tbl_leak_volume_constant` VALUES ('279', '29', '3', '0.641628711', '0.577465840');
INSERT INTO `tbl_leak_volume_constant` VALUES ('280', '30', '3', '0.662427863', '0.596185077');
INSERT INTO `tbl_leak_volume_constant` VALUES ('281', '1', '4', '0.132370440', '0.119133396');
INSERT INTO `tbl_leak_volume_constant` VALUES ('282', '2', '4', '0.141564402', '0.127407961');
INSERT INTO `tbl_leak_volume_constant` VALUES ('283', '3', '4', '0.149226037', '0.134303433');
INSERT INTO `tbl_leak_volume_constant` VALUES ('284', '4', '4', '0.175275595', '0.157748036');
INSERT INTO `tbl_leak_volume_constant` VALUES ('285', '5', '4', '0.201325154', '0.181192639');
INSERT INTO `tbl_leak_volume_constant` VALUES ('286', '6', '4', '0.208986789', '0.188088110');
INSERT INTO `tbl_leak_volume_constant` VALUES ('287', '7', '4', '0.231971694', '0.208774525');
INSERT INTO `tbl_leak_volume_constant` VALUES ('288', '8', '4', '0.254956599', '0.229460939');
INSERT INTO `tbl_leak_volume_constant` VALUES ('289', '9', '4', '0.277941504', '0.250147353');
INSERT INTO `tbl_leak_volume_constant` VALUES ('290', '10', '4', '0.300926409', '0.270833768');
INSERT INTO `tbl_leak_volume_constant` VALUES ('291', '11', '4', '0.307055717', '0.276350145');
INSERT INTO `tbl_leak_volume_constant` VALUES ('292', '12', '4', '0.328508295', '0.295657465');
INSERT INTO `tbl_leak_volume_constant` VALUES ('293', '13', '4', '0.349960873', '0.314964785');
INSERT INTO `tbl_leak_volume_constant` VALUES ('294', '14', '4', '0.371413450', '0.334272105');
INSERT INTO `tbl_leak_volume_constant` VALUES ('295', '15', '4', '0.392866028', '0.353579425');
INSERT INTO `tbl_leak_volume_constant` VALUES ('296', '16', '4', '0.414318606', '0.372886746');
INSERT INTO `tbl_leak_volume_constant` VALUES ('297', '17', '4', '0.435771184', '0.392194066');
INSERT INTO `tbl_leak_volume_constant` VALUES ('298', '18', '4', '0.457223762', '0.411501386');
INSERT INTO `tbl_leak_volume_constant` VALUES ('299', '19', '4', '0.478676340', '0.430808706');
INSERT INTO `tbl_leak_volume_constant` VALUES ('300', '20', '4', '0.500128918', '0.450116026');
INSERT INTO `tbl_leak_volume_constant` VALUES ('301', '21', '4', '0.521581496', '0.469423346');
INSERT INTO `tbl_leak_volume_constant` VALUES ('302', '22', '4', '0.543034074', '0.488730666');
INSERT INTO `tbl_leak_volume_constant` VALUES ('303', '23', '4', '0.564486651', '0.508037986');
INSERT INTO `tbl_leak_volume_constant` VALUES ('304', '24', '4', '0.585939229', '0.527345306');
INSERT INTO `tbl_leak_volume_constant` VALUES ('305', '25', '4', '0.607391807', '0.546652627');
INSERT INTO `tbl_leak_volume_constant` VALUES ('306', '26', '4', '0.628844385', '0.565959947');
INSERT INTO `tbl_leak_volume_constant` VALUES ('307', '27', '4', '0.650296963', '0.585267267');
INSERT INTO `tbl_leak_volume_constant` VALUES ('308', '28', '4', '0.671749541', '0.604574587');
INSERT INTO `tbl_leak_volume_constant` VALUES ('309', '29', '4', '0.693202119', '0.623881907');
INSERT INTO `tbl_leak_volume_constant` VALUES ('310', '30', '4', '0.714654697', '0.643189227');
INSERT INTO `tbl_leak_volume_constant` VALUES ('311', '1', '5', '0.187955674', '0.169160106');
INSERT INTO `tbl_leak_volume_constant` VALUES ('312', '2', '5', '0.197600944', '0.177840850');
INSERT INTO `tbl_leak_volume_constant` VALUES ('313', '3', '5', '0.205638670', '0.185074803');
INSERT INTO `tbl_leak_volume_constant` VALUES ('314', '4', '5', '0.232966936', '0.209670243');
INSERT INTO `tbl_leak_volume_constant` VALUES ('315', '5', '5', '0.260295203', '0.234265682');
INSERT INTO `tbl_leak_volume_constant` VALUES ('316', '6', '5', '0.268332928', '0.241499635');
INSERT INTO `tbl_leak_volume_constant` VALUES ('317', '7', '5', '0.292446105', '0.263201494');
INSERT INTO `tbl_leak_volume_constant` VALUES ('318', '8', '5', '0.316559281', '0.284903353');
INSERT INTO `tbl_leak_volume_constant` VALUES ('319', '9', '5', '0.340672457', '0.306605212');
INSERT INTO `tbl_leak_volume_constant` VALUES ('320', '10', '5', '0.364785634', '0.328307070');
INSERT INTO `tbl_leak_volume_constant` VALUES ('321', '11', '5', '0.371215814', '0.334094233');
INSERT INTO `tbl_leak_volume_constant` VALUES ('322', '12', '5', '0.393721446', '0.354349301');
INSERT INTO `tbl_leak_volume_constant` VALUES ('323', '13', '5', '0.416227077', '0.374604369');
INSERT INTO `tbl_leak_volume_constant` VALUES ('324', '14', '5', '0.438732708', '0.394859437');
INSERT INTO `tbl_leak_volume_constant` VALUES ('325', '15', '5', '0.461238339', '0.415114506');
INSERT INTO `tbl_leak_volume_constant` VALUES ('326', '16', '5', '0.483743971', '0.435369574');
INSERT INTO `tbl_leak_volume_constant` VALUES ('327', '17', '5', '0.506249602', '0.455624642');
INSERT INTO `tbl_leak_volume_constant` VALUES ('328', '18', '5', '0.528755233', '0.475879710');
INSERT INTO `tbl_leak_volume_constant` VALUES ('329', '19', '5', '0.551260865', '0.496134778');
INSERT INTO `tbl_leak_volume_constant` VALUES ('330', '20', '5', '0.573766496', '0.516389846');
INSERT INTO `tbl_leak_volume_constant` VALUES ('331', '21', '5', '0.596272127', '0.536644915');
INSERT INTO `tbl_leak_volume_constant` VALUES ('332', '22', '5', '0.618777759', '0.556899983');
INSERT INTO `tbl_leak_volume_constant` VALUES ('333', '23', '5', '0.641283390', '0.577155051');
INSERT INTO `tbl_leak_volume_constant` VALUES ('334', '24', '5', '0.663789021', '0.597410119');
INSERT INTO `tbl_leak_volume_constant` VALUES ('335', '25', '5', '0.686294653', '0.617665187');
INSERT INTO `tbl_leak_volume_constant` VALUES ('336', '26', '5', '0.708800284', '0.637920256');
INSERT INTO `tbl_leak_volume_constant` VALUES ('337', '27', '5', '0.731305915', '0.658175324');
INSERT INTO `tbl_leak_volume_constant` VALUES ('338', '28', '5', '0.753811547', '0.678430392');
INSERT INTO `tbl_leak_volume_constant` VALUES ('339', '29', '5', '0.776317178', '0.698685460');
INSERT INTO `tbl_leak_volume_constant` VALUES ('340', '30', '5', '0.798822809', '0.718940528');
INSERT INTO `tbl_leak_volume_constant` VALUES ('341', '1', '6', '0.271927508', '0.244734757');
INSERT INTO `tbl_leak_volume_constant` VALUES ('342', '2', '6', '0.282254564', '0.254029108');
INSERT INTO `tbl_leak_volume_constant` VALUES ('343', '3', '6', '0.290860445', '0.261774400');
INSERT INTO `tbl_leak_volume_constant` VALUES ('344', '4', '6', '0.320120437', '0.288108393');
INSERT INTO `tbl_leak_volume_constant` VALUES ('345', '5', '6', '0.349380429', '0.314442387');
INSERT INTO `tbl_leak_volume_constant` VALUES ('346', '6', '6', '0.357986310', '0.322187679');
INSERT INTO `tbl_leak_volume_constant` VALUES ('347', '7', '6', '0.383803950', '0.345423555');
INSERT INTO `tbl_leak_volume_constant` VALUES ('348', '8', '6', '0.409621590', '0.368659431');
INSERT INTO `tbl_leak_volume_constant` VALUES ('349', '9', '6', '0.435439231', '0.391895308');
INSERT INTO `tbl_leak_volume_constant` VALUES ('350', '10', '6', '0.461256871', '0.415131184');
INSERT INTO `tbl_leak_volume_constant` VALUES ('351', '11', '6', '0.468141575', '0.421327418');
INSERT INTO `tbl_leak_volume_constant` VALUES ('352', '12', '6', '0.492238040', '0.443014236');
INSERT INTO `tbl_leak_volume_constant` VALUES ('353', '13', '6', '0.516334504', '0.464701054');
INSERT INTO `tbl_leak_volume_constant` VALUES ('354', '14', '6', '0.540430968', '0.486387872');
INSERT INTO `tbl_leak_volume_constant` VALUES ('355', '15', '6', '0.564527433', '0.508074689');
INSERT INTO `tbl_leak_volume_constant` VALUES ('356', '16', '6', '0.588623897', '0.529761507');
INSERT INTO `tbl_leak_volume_constant` VALUES ('357', '17', '6', '0.612720361', '0.551448325');
INSERT INTO `tbl_leak_volume_constant` VALUES ('358', '18', '6', '0.636816826', '0.573135143');
INSERT INTO `tbl_leak_volume_constant` VALUES ('359', '19', '6', '0.660913290', '0.594821961');
INSERT INTO `tbl_leak_volume_constant` VALUES ('360', '20', '6', '0.685009755', '0.616508779');
INSERT INTO `tbl_leak_volume_constant` VALUES ('361', '21', '6', '0.709106219', '0.638195597');
INSERT INTO `tbl_leak_volume_constant` VALUES ('362', '22', '6', '0.733202683', '0.659882415');
INSERT INTO `tbl_leak_volume_constant` VALUES ('363', '23', '6', '0.757299148', '0.681569233');
INSERT INTO `tbl_leak_volume_constant` VALUES ('364', '24', '6', '0.781395612', '0.703256051');
INSERT INTO `tbl_leak_volume_constant` VALUES ('365', '25', '6', '0.805492076', '0.724942869');
INSERT INTO `tbl_leak_volume_constant` VALUES ('366', '26', '6', '0.829588541', '0.746629687');
INSERT INTO `tbl_leak_volume_constant` VALUES ('367', '27', '6', '0.853685005', '0.768316505');
INSERT INTO `tbl_leak_volume_constant` VALUES ('368', '28', '6', '0.877781470', '0.790003323');
INSERT INTO `tbl_leak_volume_constant` VALUES ('369', '29', '6', '0.901877934', '0.811690140');
INSERT INTO `tbl_leak_volume_constant` VALUES ('370', '30', '6', '0.925974398', '0.833376958');
