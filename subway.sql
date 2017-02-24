/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : subway

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2017-02-24 13:58:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sub_admin`
-- ----------------------------
DROP TABLE IF EXISTS `sub_admin`;
CREATE TABLE `sub_admin` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sub_admin
-- ----------------------------
INSERT INTO `sub_admin` VALUES ('1', 'haojin', 'haojin');

-- ----------------------------
-- Table structure for `sub_order`
-- ----------------------------
DROP TABLE IF EXISTS `sub_order`;
CREATE TABLE `sub_order` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `order_number` char(10) NOT NULL,
  `user_id` int(8) NOT NULL,
  `one_site` varchar(32) NOT NULL,
  `two_site` varchar(32) NOT NULL,
  `price` int(2) DEFAULT NULL,
  `re_price` double(2,1) DEFAULT '0.0',
  `create_at` datetime NOT NULL,
  `state` varchar(32) NOT NULL,
  `deleted` int(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sub_order
-- ----------------------------
INSERT INTO `sub_order` VALUES ('1', '0000000001', '1', '龙眠大道', '软件大道', '4', '0.0', '2017-02-03 19:03:34', '待取票', '0');
INSERT INTO `sub_order` VALUES ('2', '0000000002', '2', '软件大道', '龙眠大道', '4', '0.0', '2017-02-03 19:05:00', '已取票', '1');
INSERT INTO `sub_order` VALUES ('3', '0000000003', '2', '龙眠大道', '新街口', '4', '0.0', '2017-02-07 19:05:57', '已乘坐', '1');
INSERT INTO `sub_order` VALUES ('4', '0000000004', '9', '龙眠大道', '迈皋桥', '4', '4.0', '2017-02-07 19:06:53', '已退票', '0');
INSERT INTO `sub_order` VALUES ('16', 'wuzeq5q7n1', '9', '龙眠大道', '迈皋桥', '4', '0.0', '2017-02-07 19:06:53', '已取票', '0');
INSERT INTO `sub_order` VALUES ('15', 'gygkf91wod', '1', '龙眠大道', '新街口', '4', '0.0', '2017-02-07 19:05:57', '已乘坐', '0');
INSERT INTO `sub_order` VALUES ('14', 'dnn088epi6', '1', '龙眠大道', '软件大道', '4', '0.0', '2017-02-09 19:03:34', '待取票', '0');
INSERT INTO `sub_order` VALUES ('17', 'dsdb4t43ax', '12', '红山动物园', '苜蓿园', '3', '0.0', '2017-02-10 20:08:58', '待取票', '0');
INSERT INTO `sub_order` VALUES ('18', 'd91v9axueo', '12', '红山动物园', '常府街', '2', '0.0', '2017-02-10 20:16:06', '已取票', '0');
INSERT INTO `sub_order` VALUES ('19', 'd91v9axueo', '12', '红山动物园', '常府街', '2', '2.0', '2017-02-10 20:24:40', '已退票', '0');
INSERT INTO `sub_order` VALUES ('20', 'r71r3l0ifk', '12', '鸡鸣寺', '武定门', '2', '1.6', '2017-02-10 20:27:08', '已退票', '1');
INSERT INTO `sub_order` VALUES ('21', 'igx18eh4b0', '1', '龙眠大道', '南京站', '5', '4.0', '2017-02-10 20:31:36', '已退票', '0');

-- ----------------------------
-- Table structure for `sub_user`
-- ----------------------------
DROP TABLE IF EXISTS `sub_user`;
CREATE TABLE `sub_user` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `email` varchar(32) DEFAULT NULL,
  `tel` char(11) DEFAULT NULL,
  `deleted` int(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sub_user
-- ----------------------------
INSERT INTO `sub_user` VALUES ('1', 'huangbo', 'huangbo', 'xiaopang@qq.com', '15100009999', '0');
INSERT INTO `sub_user` VALUES ('2', 'test', 'test', 'test@qq.com', null, '1');
INSERT INTO `sub_user` VALUES ('9', 'voidking', 'voidking', 'voidking@qq.com', null, '0');
INSERT INTO `sub_user` VALUES ('12', 'haojin', 'haojin', 'voidking@qq.com', '15100000000', '0');
