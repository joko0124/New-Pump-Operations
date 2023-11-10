/*
Navicat SQLite Data Transfer

Source Server         : PumpOPTest
Source Server Version : 30714
Source Host           : :0

Target Server Type    : SQLite
Target Server Version : 30714
File Encoding         : 65001

Date: 2023-11-10 23:07:38
*/

PRAGMA foreign_keys = OFF;

-- ----------------------------
-- Table structure for tblUsers
-- ----------------------------
DROP TABLE IF EXISTS "main"."tblUsers";
CREATE TABLE "tblUsers" (
"UserID"  TEXT,
"BranchID"  INTEGER,
"LastName"  TEXT,
"FirstName"  TEXT,
"MiddleName"  TEXT,
"Suffixed"  TEXT,
"EmpName"  TEXT,
"UserName"  TEXT,
"UserPassword"  TEXT,
"IsMultiPos"  INTEGER,
"IsDefault"  INTEGER DEFAULT 0,
"TokenType"  TEXT,
"Expiration"  INTEGER,
"UserToken"  TEXT,
"RefreshToken"  TEXT
);
