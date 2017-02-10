/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : subway

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2017-02-10 20:44:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sub_line`
-- ----------------------------
DROP TABLE IF EXISTS `sub_line`;
CREATE TABLE `sub_line` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `line_name` char(32) NOT NULL,
  `full_name` tinytext NOT NULL,
  `stop` text NOT NULL,
  `stop_back` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sub_line
-- ----------------------------
INSERT INTO `sub_line` VALUES ('2', '1', '1号线', '中国药科大学,南京交院,江苏经贸学院,龙眠大道,天印大道,竹山路,小龙湾,百家湖,胜太路,河定桥,双龙大道,南京南站,花神庙,软件大道,天隆寺,安德门,中华门,三山街,张府园,新街口,珠江路,鼓楼,玄武门,新模范马路,南京站,红山动物园,迈皋桥', '迈皋桥,红山动物园,南京站,新模范马路,玄武门,鼓楼,珠江路,新街口,张府园,三山街,中华门,安德门,天隆寺,软件大道,花神庙,南京南站,双龙大道,河定桥,胜太路,百家湖,小龙湾,竹山路,天印大道,龙眠大道,江苏经贸学院,南京交院,中国药科大学');
INSERT INTO `sub_line` VALUES ('3', '2', '2号线', '油坊桥,雨润大街,元通,奥体东,兴隆大街,集庆门大街,云锦路,莫愁湖,汉中门,上海路,新街口,大行宫,西安门,明故宫,苜蓿园,下马坊,孝陵卫,钟灵街,马群,金马路,仙鹤门,学则路,仙林中心,羊山公园,南大仙林校区,经天路', '经天路,南大仙林校区,羊山公园,仙林中心,学则路,仙鹤门,金马路,马群,钟灵街,孝陵卫,下马坊,苜蓿园,明故宫,西安门,大行宫,新街口,上海路,汉中门,莫愁湖,云锦路,集庆门大街,兴隆大街,奥体东,元通,雨润大街,油坊桥');
INSERT INTO `sub_line` VALUES ('4', '3', '3号线', '秣周东路,东大九龙湖校区,诚信大道,九龙湖,天元西路,胜太西路,宏运大道,南京南站,明发广场,大明路,卡子门,雨花门,武定门,夫子庙,常府街,大行宫,浮桥,鸡鸣寺,南京林业大学·新庄,南京站,小市,五塘广场,上元门,柳洲东路,天润城,泰冯路,东大成贤学院,星火路,林场', '林场,星火路,东大成贤学院,泰冯路,天润城,柳洲东路,上元门,五塘广场,小市,南京站,南京林业大学·新庄,鸡鸣寺,浮桥,大行宫,常府街,夫子庙,武定门,雨花门,卡子门,大明路,明发广场,南京南站,宏运大道,胜太西路,天元西路,九龙湖,诚信大道,东大九龙湖校区,秣周东路');
INSERT INTO `sub_line` VALUES ('5', '10', '10号线', '安德门,小行,中胜,元通,奥体中心,梦都大街,绿博园,江心洲,临江,浦口万汇城,南京工业大学,龙华路,文德路,雨山路', '雨山路,文德路,龙华路,南京工业大学,浦口万汇城,临江,江心洲,绿博园,梦都大街,奥体中心,元通,中胜,小行,安德门');
INSERT INTO `sub_line` VALUES ('6', 's1', 's1机场线', '禄口机场,翔宇路南,翔宇路北,正方中路,吉印大道,佛成西路,翠屏山,南京南站', '南京南站,翠屏山,佛成西路,吉印大道,正方中路,翔宇路北,翔宇路南,禄口机场');

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
  `re_price` int(2) DEFAULT '0',
  `create_at` datetime NOT NULL,
  `state` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sub_order
-- ----------------------------
INSERT INTO `sub_order` VALUES ('1', '0000000001', '1', '龙眠大道', '软件大道', '4', '0', '2017-02-09 19:03:34', '待取票');
INSERT INTO `sub_order` VALUES ('2', '0000000002', '2', '软件大道', '龙眠大道', '4', '0', '2017-02-08 19:05:00', '已取票');
INSERT INTO `sub_order` VALUES ('3', '0000000003', '2', '龙眠大道', '新街口', '4', '0', '2017-02-07 19:05:57', '已乘坐');
INSERT INTO `sub_order` VALUES ('4', '0000000004', '1', '龙眠大道', '迈皋桥', '4', '0', '2017-02-02 19:06:53', '已退票');
INSERT INTO `sub_order` VALUES ('16', 'wuzeq5q7n1', '1', '龙眠大道', '迈皋桥', '4', '0', '2017-02-02 19:06:53', '已取票');
INSERT INTO `sub_order` VALUES ('15', 'gygkf91wod', '1', '龙眠大道', '新街口', '4', '0', '2017-02-07 19:05:57', '已乘坐');
INSERT INTO `sub_order` VALUES ('14', 'dnn088epi6', '1', '龙眠大道', '软件大道', '4', '0', '2017-02-09 19:03:34', '待取票');
INSERT INTO `sub_order` VALUES ('17', 'dsdb4t43ax', '12', '红山动物园', '苜蓿园', '3', '0', '2017-02-10 20:08:58', '待取票');
INSERT INTO `sub_order` VALUES ('18', 'd91v9axueo', '12', '红山动物园', '常府街', '2', '0', '2017-02-10 20:16:06', '待取票');
INSERT INTO `sub_order` VALUES ('19', 'd91v9axueo', '12', '红山动物园', '常府街', '2', '0', '2017-02-10 20:24:40', '待取票');
INSERT INTO `sub_order` VALUES ('20', 'r71r3l0ifk', '12', '鸡鸣寺', '武定门', '2', '0', '2017-02-10 20:27:08', '已退票');
INSERT INTO `sub_order` VALUES ('21', 'igx18eh4b0', '1', '龙眠大道', '南京站', '5', '0', '2017-02-10 20:31:36', '待取票');

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
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sub_user
-- ----------------------------
INSERT INTO `sub_user` VALUES ('1', 'huangbo', 'huangbo', 'xiaopang@qq.com', '15100009999');
INSERT INTO `sub_user` VALUES ('2', 'test', 'test', 'test@qq.com', null);
INSERT INTO `sub_user` VALUES ('9', 'voidking', 'voidking', 'voidking@qq.com', null);
INSERT INTO `sub_user` VALUES ('12', 'haojin', 'haojin', 'voidking@qq.com', '15100000000');
INSERT INTO `sub_user` VALUES ('13', 'voidking00', 'voidking', null, null);
