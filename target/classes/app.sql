/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80300
Source Host           : localhost:3306
Source Database       : app

Target Server Type    : MYSQL
Target Server Version : 80300
File Encoding         : 65001

Date: 2025-11-25 11:42:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bill
-- ----------------------------
DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '唯一ID',
  `userId` int NOT NULL COMMENT '用户ID',
  `test` varchar(500) DEFAULT '' COMMENT '买码原始数据',
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下单日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='下单表';

-- ----------------------------
-- Records of bill
-- ----------------------------

-- ----------------------------
-- Table structure for lottery
-- ----------------------------
DROP TABLE IF EXISTS `lottery`;
CREATE TABLE `lottery` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '彩票唯一ID',
  `code` varchar(20) NOT NULL COMMENT '彩票期号',
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开奖日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='彩票表';

-- ----------------------------
-- Records of lottery
-- ----------------------------

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '订单唯一ID',
  `userId` int NOT NULL COMMENT '用户ID',
  `lotteryId` int NOT NULL COMMENT '彩票ID',
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作日期',
  PRIMARY KEY (`id`),
  KEY `idx_userId` (`userId`),
  KEY `idx_lotteryId` (`lotteryId`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单表';

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for ordercode
-- ----------------------------
DROP TABLE IF EXISTS `ordercode`;
CREATE TABLE `ordercode` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '唯一ID',
  `code` varchar(20) NOT NULL COMMENT '号码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='生效排序表';

-- ----------------------------
-- Records of ordercode
-- ----------------------------
INSERT INTO `ordercode` VALUES ('1', '01');
INSERT INTO `ordercode` VALUES ('2', '13');
INSERT INTO `ordercode` VALUES ('3', '25');
INSERT INTO `ordercode` VALUES ('4', '37');
INSERT INTO `ordercode` VALUES ('5', '49');
INSERT INTO `ordercode` VALUES ('6', '02');
INSERT INTO `ordercode` VALUES ('7', '14');
INSERT INTO `ordercode` VALUES ('8', '26');
INSERT INTO `ordercode` VALUES ('9', '38');
INSERT INTO `ordercode` VALUES ('10', '03');
INSERT INTO `ordercode` VALUES ('11', '15');
INSERT INTO `ordercode` VALUES ('12', '27');
INSERT INTO `ordercode` VALUES ('13', '39');
INSERT INTO `ordercode` VALUES ('14', '04');
INSERT INTO `ordercode` VALUES ('15', '16');
INSERT INTO `ordercode` VALUES ('16', '28');
INSERT INTO `ordercode` VALUES ('17', '40');
INSERT INTO `ordercode` VALUES ('18', '05');
INSERT INTO `ordercode` VALUES ('19', '17');
INSERT INTO `ordercode` VALUES ('20', '29');
INSERT INTO `ordercode` VALUES ('21', '41');
INSERT INTO `ordercode` VALUES ('22', '06');
INSERT INTO `ordercode` VALUES ('23', '18');
INSERT INTO `ordercode` VALUES ('24', '30');
INSERT INTO `ordercode` VALUES ('25', '42');
INSERT INTO `ordercode` VALUES ('26', '07');
INSERT INTO `ordercode` VALUES ('27', '19');
INSERT INTO `ordercode` VALUES ('28', '31');
INSERT INTO `ordercode` VALUES ('29', '43');
INSERT INTO `ordercode` VALUES ('30', '08');
INSERT INTO `ordercode` VALUES ('31', '20');
INSERT INTO `ordercode` VALUES ('32', '32');
INSERT INTO `ordercode` VALUES ('33', '44');
INSERT INTO `ordercode` VALUES ('34', '09');
INSERT INTO `ordercode` VALUES ('35', '21');
INSERT INTO `ordercode` VALUES ('36', '33');
INSERT INTO `ordercode` VALUES ('37', '45');
INSERT INTO `ordercode` VALUES ('38', '10');
INSERT INTO `ordercode` VALUES ('39', '22');
INSERT INTO `ordercode` VALUES ('40', '34');
INSERT INTO `ordercode` VALUES ('41', '46');
INSERT INTO `ordercode` VALUES ('42', '11');
INSERT INTO `ordercode` VALUES ('43', '23');
INSERT INTO `ordercode` VALUES ('44', '35');
INSERT INTO `ordercode` VALUES ('45', '47');
INSERT INTO `ordercode` VALUES ('46', '12');
INSERT INTO `ordercode` VALUES ('47', '24');
INSERT INTO `ordercode` VALUES ('48', '36');
INSERT INTO `ordercode` VALUES ('49', '48');

-- ----------------------------
-- Table structure for orderdetil
-- ----------------------------
DROP TABLE IF EXISTS `orderdetil`;
CREATE TABLE `orderdetil` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '详情唯一ID',
  `orderId` int NOT NULL COMMENT '订单ID',
  `code` varchar(20) NOT NULL COMMENT '投注号码',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '投注金额',
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作日期',
  PRIMARY KEY (`id`),
  KEY `idx_orderId` (`orderId`)
) ENGINE=InnoDB AUTO_INCREMENT=293 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单详情表';

-- ----------------------------
-- Records of orderdetil
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户唯一ID',
  `name` varchar(50) DEFAULT '' COMMENT '姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '四妹');
INSERT INTO `user` VALUES ('2', '阿二');
INSERT INTO `user` VALUES ('3', '阿二家婆');
INSERT INTO `user` VALUES ('4', '方业兄弟');
INSERT INTO `user` VALUES ('5', '四妹家婆');
