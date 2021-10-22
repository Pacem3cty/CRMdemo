/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80026
Source Host           : localhost:3306
Source Database       : crm

Target Server Type    : MYSQL
Target Server Version : 80026
File Encoding         : 65001

Date: 2021-10-22 16:48:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_cus_dev_plan
-- ----------------------------
DROP TABLE IF EXISTS `t_cus_dev_plan`;
CREATE TABLE `t_cus_dev_plan` (
  `id` int DEFAULT NULL,
  `sale_chance_id` int DEFAULT NULL,
  `plan_item` varchar(255) DEFAULT NULL,
  `plan_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `exe_affect` varchar(255) DEFAULT NULL COMMENT '执行效果',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `is_valid` int DEFAULT NULL,
  KEY `sale_chance_id` (`sale_chance_id`),
  CONSTRAINT `t_cus_dev_plan_ibfk_1` FOREIGN KEY (`sale_chance_id`) REFERENCES `t_sale_chance` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_cus_dev_plan
-- ----------------------------

-- ----------------------------
-- Table structure for t_module
-- ----------------------------
DROP TABLE IF EXISTS `t_module`;
CREATE TABLE `t_module` (
  `id` int NOT NULL,
  `module_name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `parent_id` int DEFAULT NULL,
  `parent_opt_value` varchar(255) DEFAULT NULL,
  `grade` int DEFAULT NULL,
  `opt_value` varchar(255) DEFAULT NULL,
  `orders` int DEFAULT NULL,
  `is_valid` int DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_module
-- ----------------------------

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` int NOT NULL,
  `role_id` int DEFAULT NULL,
  `module_id` int DEFAULT NULL,
  `acl_value` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `t_permission_ibfk_1` (`role_id`),
  KEY `t_permission_ibfk_2` (`module_id`),
  CONSTRAINT `t_permission_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_permission_ibfk_2` FOREIGN KEY (`module_id`) REFERENCES `t_module` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_permission
-- ----------------------------

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int NOT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `role_remark` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT NULL,
  `is_valid` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_role
-- ----------------------------

-- ----------------------------
-- Table structure for t_sale_chance
-- ----------------------------
DROP TABLE IF EXISTS `t_sale_chance`;
CREATE TABLE `t_sale_chance` (
  `id` int NOT NULL AUTO_INCREMENT,
  `change_source` varchar(255) DEFAULT NULL COMMENT '机会来源',
  `customer_name` varchar(255) DEFAULT NULL COMMENT '客户名称',
  `probability` int DEFAULT NULL COMMENT '成功几率',
  `overview` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '概要',
  `contract_person` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `contract_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '描述',
  `create_person` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `assign_person` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '分配人',
  `assign_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '分配时间',
  `state` int DEFAULT NULL COMMENT '分配状态 0 未分配 1 已分配',
  `dev_result` int DEFAULT NULL COMMENT '开发结果',
  `is_valid` int DEFAULT NULL,
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_sale_chance
-- ----------------------------
INSERT INTO `t_sale_chance` VALUES ('1', '官网', '锕锂芸计算有限公司 ', '60', '全球领先的云计算及人工智能科技公司', '马耘', '15008976432', '测试机会', '吴芮', '2021-10-21 11:24:55', '吴芮', '2021-10-21 11:25:22', '1', '1', '0', '2021-10-21 14:43:41');
INSERT INTO `t_sale_chance` VALUES ('2', '展会', '驣殉计算机系统有限公司 ', '80', '最大的互联网综合服务提供商之一', '马砉螣', '18934879265', '测试机会', '吴㕙', '2021-03-18 16:41:41', '', '2021-10-21 16:41:41', '0', '1', '0', '2021-10-21 16:41:41');
INSERT INTO `t_sale_chance` VALUES ('3', '展会', '魍弌网络有限公司', '75', '中国领先的互联网技术公司', '丁攂', '17398438954', '测试机会', '吴㕙', '2021-10-22 13:48:57', null, '2020-11-19 14:02:13', '0', '0', '0', '2021-10-22 13:48:57');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int NOT NULL,
  `user_name` varchar(20) DEFAULT NULL,
  `user_pwd` varchar(100) DEFAULT NULL,
  `true_name` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `is_valid` int DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'Pacem3cty', 'e35cf7b66449df565f93c607d5a81d09', '王睿', '1556250364@qq.com', '19868586764', '西八老马', '0', '2021-10-09 16:15:20', '2021-10-22 15:06:11');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` int NOT NULL,
  `user_id` int DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `t_user_role_ibfk_1` (`user_id`),
  KEY `t_user_role_ibfk_2` (`role_id`),
  CONSTRAINT `t_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
