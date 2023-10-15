/*
Navicat MySQL Data Transfer

Source Server         : My Connection
Source Server Version : 50505
Source Host           : 127.0.0.1:3306
Source Database       : erick-pump-operations

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2020-10-01 17:06:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_jo_details
-- ----------------------------
DROP TABLE IF EXISTS `tbl_jo_details`;
CREATE TABLE `tbl_jo_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jo_id` int(11) DEFAULT NULL,
  `jo_number` varchar(25) DEFAULT NULL,
  `jo_cat` varchar(20) DEFAULT NULL,
  `jo_reason_id` int(11) DEFAULT NULL,
  `jo_reason` varchar(255) DEFAULT NULL,
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
  `plumber_note` varchar(255) DEFAULT NULL,
  `jo_status` tinyint(1) DEFAULT 0 COMMENT '0 - Dispatched from Elite\r\n1 - Assisgned P-Operations\r\n2 - Accomplished P-Operations\r\n3 - Uploaded P-Operations\r\n4 - Cancelled P-Operations',
  `jo_created_at` datetime DEFAULT NULL COMMENT 'Date and Time Created (From Elite)',
  `dispatched_at` datetime DEFAULT NULL COMMENT 'Date and Time Dispatched (From Elite)',
  `dispatched_to` int(125) DEFAULT NULL COMMENT 'Dispatched To (From Elite)',
  `assigned_at` datetime DEFAULT NULL COMMENT 'Date and Time Dispatched (From P-Op)',
  `assigned_to` varchar(125) DEFAULT NULL COMMENT 'Dispatched To (From P-Op)',
  `date_started` datetime DEFAULT NULL COMMENT 'Date and Time Started (From App)',
  `date_finished` datetime DEFAULT NULL COMMENT 'Date and Time Finished (From App)',
  `date_returned` datetime DEFAULT NULL COMMENT 'Save to Elite Date returned (Actual Date Time Uploading)',
  `date_uploaded` datetime DEFAULT NULL COMMENT 'Date and Time Uploaded (From P-Op)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tbl_jo_details
-- ----------------------------
INSERT INTO `tbl_jo_details` VALUES ('1', '1515', 'JO25-0920-0018', 'SAS', null, null, '339', 'AP25-0920-0005', 'dantes, marian rivera', '123 DALIG, BALAGTAS, BULACAN', 'RES', 'A', null, null, null, null, null, null, '09265146296', null, '0', '2020-09-12 00:00:00', '2020-09-18 09:26:00', '7', null, null, null, null, null, null);
INSERT INTO `tbl_jo_details` VALUES ('2', '1517', 'JO25-0920-0020', 'SAS', null, null, '341', 'AP25-0920-0007', 'fernandez, joko pogi', '4342535 BOROL 1ST, BALAGTAS, BULACAN', 'RES', 'A', null, null, null, null, null, null, '09265146296', null, '0', '2020-09-17 00:00:00', '2020-09-18 09:27:00', '12', null, null, null, null, null, null);
INSERT INTO `tbl_jo_details` VALUES ('3', '1518', 'JO25-0920-0021', 'SAS', null, null, '342', 'AP25-0920-0008', 'salangsang, yumi juco', '434343 BOROL 1ST, BALAGTAS, BULACAN', 'RES', 'A', null, null, null, null, null, null, '4353545', null, '0', '2020-09-17 00:00:00', '2020-09-18 09:27:00', '8', null, null, null, null, null, null);
INSERT INTO `tbl_jo_details` VALUES ('4', '1519', 'JO25-0920-0022', 'NC', null, null, '339', 'AP25-0920-0005', 'dantes, marian rivera', '123 DALIG, BALAGTAS, BULACAN', 'RES', 'A', null, null, null, null, null, null, '09265146296', null, '0', '2020-09-18 10:00:06', '2020-09-22 09:37:00', '12', null, null, null, null, null, null);
INSERT INTO `tbl_jo_details` VALUES ('5', '1513', 'JO25-0920-0016', 'MC', '33', 'Leak repair', '1584', 'AC25-0810-0006', 'CUNDANGAN, MILAGROS  (F)', 'celia st. Panginay, Balagtas, Bulacan', 'RES', 'A', '11', '1831', '1294', null, null, null, null, null, '0', '2020-09-03 00:00:00', '2020-09-28 11:15:00', '7', null, null, null, null, null, null);
INSERT INTO `tbl_jo_details` VALUES ('6', '1480', 'JO25-0820-0137', 'MC', '32', 'Relocation', '1073', 'AC25-0808-1043', 'IRA, MIGUELITO', '007 a & v subdivision Panginay, Balagtas, Bulacan', 'RES', 'A', '13', '632', '14', null, null, null, null, null, '0', '2020-08-25 00:00:00', '2020-09-28 11:25:00', '12', null, null, null, null, null, null);
INSERT INTO `tbl_jo_details` VALUES ('7', '1483', 'JO25-0820-0140', 'MC', '17', 'Meter test', '178', 'AC25-0808-0148', 'PAGUIA, JULITA  (2)', 'galvez st. Panginay, Balagtas, Bulacan', 'RES', 'A', '2', '1295', '485', null, null, null, null, null, '0', '2020-08-25 00:00:00', '2020-09-28 11:25:00', '12', null, null, null, null, null, null);
INSERT INTO `tbl_jo_details` VALUES ('8', '510', 'JO25-0120-0163', 'DC-DA', null, null, '3609', 'AC25-1214-0008', 'ORTEGA, FELOMINA', '53 p. atienza st. (pnr) Longos, Balagtas, Bulacan', 'RES', 'B', '25', '3994', '576', null, null, null, null, '5 mos delinquent', '0', '2020-01-15 00:00:00', '2020-09-28 14:03:00', '5', null, null, null, null, null, null);
INSERT INTO `tbl_jo_details` VALUES ('10', '198', 'JO25-1219-0076', 'DC-CR', null, null, '3829', 'AC25-0516-0028', 'DELI-QUEEN GOODY CORP', 'mcarthur hway Wawa, Balagtas, Bulacan', 'COM', 'B', '18', '4012', '62', null, null, null, null, null, '0', '2019-12-09 00:00:00', '2020-09-28 14:22:00', '8', null, null, null, null, null, null);
INSERT INTO `tbl_jo_details` VALUES ('11', '194', 'JO25-1219-0072', 'RC', null, null, '3220', 'AC25-0612-0014', 'ROQUE, NOLLIE', 'c. galvez st. Longos, Balagtas, Bulacan', 'RES', 'B', '25', '3914', '831', null, null, null, null, null, '0', '2019-12-09 00:00:00', '2020-09-28 14:30:00', '9', null, null, null, null, null, null);
INSERT INTO `tbl_jo_details` VALUES ('12', '1520', 'JO25-0920-0023', 'IC', null, null, '3230', 'AC25-0612-0026', 'ABCEDE, DAISY', '0151 p. atienza Longos, Balagtas, Bulacan', 'RES', 'B', '24', '3729', '566', null, '0', '16', null, 'Ask to Pay', '0', '2020-09-28 00:00:00', '2020-09-29 12:58:00', '6', null, null, null, null, null, null);
INSERT INTO `tbl_jo_details` VALUES ('13', '1522', 'JO25-0920-0025', 'DC-CR', null, null, '4264', '25-0120-0075', 'hernandez, celso a', 'tiwala st Borol 1st, Balagtas, Bulacan', 'RES', 'A', '28', '5269', '1062', null, null, null, null, null, '0', '2020-09-29 00:00:00', '2020-09-29 13:13:00', '12', null, null, null, null, null, null);
INSERT INTO `tbl_jo_details` VALUES ('14', '1397', 'JO25-0820-0054', 'CM', null, null, '1632', 'AC25-0311-0002', 'FORDHAM, RODA', 'a & v subdivision Panginay, Balagtas, Bulacan', 'RES', 'A', '13', '5564', '0', null, null, null, null, null, '0', '2020-08-07 00:00:00', '2020-09-29 17:12:00', '5', null, null, null, null, null, null);
INSERT INTO `tbl_jo_details` VALUES ('15', '1512', 'JO25-0920-0015', 'SL', '7', 'Low pressure', '785', 'AC25-0808-0755', 'DIONISIO, NORIE', 'provincial road Panginay, Balagtas, Bulacan', 'RES', 'A', '7', '2165', '1759', null, null, null, null, 'mike vicente - 0955611130', '0', '2020-09-02 00:00:00', '2020-10-01 23:50:00', '12', null, null, null, null, null, null);
INSERT INTO `tbl_jo_details` VALUES ('16', '1523', 'JO25-1020-0001', 'SV', '34', 'Check account classification', '2152', 'AC25-0314-0017', 'ADO, ROBERTO', 'provincial road Panginay, Balagtas, Bulacan', 'RES', 'A', '7', '901', '1840', null, null, null, null, null, '0', '2020-10-01 00:00:00', '2020-10-01 16:10:00', '12', null, null, null, null, null, null);
INSERT INTO `tbl_jo_details` VALUES ('17', '1524', 'JO25-1020-0002', 'OT', '99', 'For Testing', '4412', 'AC25-0620-0027', 'abdala, emmalinda lofranco', 'huseng batute st. Panginay, Balagtas, Bulacan', 'RES', 'A', '5', '5469', '15', null, null, null, '09653009484', null, '0', '2020-10-01 00:00:00', '2020-10-01 16:57:00', '12', null, null, null, null, null, null);
