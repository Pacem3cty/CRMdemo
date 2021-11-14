/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80026
Source Host           : localhost:3306
Source Database       : crm

Target Server Type    : MYSQL
Target Server Version : 80026
File Encoding         : 65001

Date: 2021-11-12 16:50:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_cus_dev_plan
-- ----------------------------
DROP TABLE IF EXISTS `t_cus_dev_plan`;
CREATE TABLE `t_cus_dev_plan` (
  `id` int NOT NULL,
  `sale_chance_id` int DEFAULT NULL,
  `plan_item` varchar(255) DEFAULT NULL,
  `plan_date` datetime DEFAULT NULL,
  `exe_affect` varchar(255) DEFAULT NULL COMMENT '执行效果',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT NULL,
  `is_valid` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sale_chance_id` (`sale_chance_id`),
  CONSTRAINT `t_cus_dev_plan_ibfk_1` FOREIGN KEY (`sale_chance_id`) REFERENCES `t_sale_chance` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_cus_dev_plan
-- ----------------------------
INSERT INTO `t_cus_dev_plan` VALUES ('1', '1', '测试计划项1', '2021-10-27 09:02:05', '测试计划效果1', '2021-10-27 09:02:21', null, '0');
INSERT INTO `t_cus_dev_plan` VALUES ('2', '2', '测试计划项2', '2021-10-27 14:49:43', '测试计划效果2', '2021-10-27 14:49:36', null, '0');
INSERT INTO `t_cus_dev_plan` VALUES ('3', '3', '测试计划项3', '2021-10-27 00:00:00', '测试计划效果3', '2021-10-27 00:00:00', '2021-11-04 15:33:57', '0');
INSERT INTO `t_cus_dev_plan` VALUES ('4', '3', '测试计划项4', '2021-10-28 00:00:00', '测试执行效果4', '2021-10-28 00:00:00', '2021-10-28 16:08:30', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('5', '2', '测试计划项3', '2021-11-03 00:00:00', '测试执行效果3', '2021-11-03 00:00:00', '2021-11-03 10:11:05', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('6', '3', '测试计划项6', '2021-11-04 00:00:00', '测试执行效果6', '2021-11-04 15:36:32', null, '0');
INSERT INTO `t_cus_dev_plan` VALUES ('7', '4', '测试计划项7', '2021-11-04 00:00:00', '测试执行效果7', '2021-11-04 15:42:25', null, '0');

-- ----------------------------
-- Table structure for t_module
-- ----------------------------
DROP TABLE IF EXISTS `t_module`;
CREATE TABLE `t_module` (
  `id` int NOT NULL,
  `module_name` varchar(255) DEFAULT NULL,
  `module_style` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `parent_id` int DEFAULT NULL,
  `parent_opt_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `grade` int DEFAULT NULL,
  `opt_value` varchar(255) DEFAULT NULL,
  `orders` int NOT NULL,
  `is_valid` int DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_module
-- ----------------------------
INSERT INTO `t_module` VALUES ('1', '营销管理', null, '#', '-1', '0', null, null, '1', '0', '2021-11-05 15:51:07', null);
INSERT INTO `t_module` VALUES ('2', '营销机会管理', null, '/SaleChance', '1', '0', null, null, '2', '0', '2021-11-05 15:53:35', null);
INSERT INTO `t_module` VALUES ('3', '营销机会管理查询', null, '#', '2', '0', null, null, '3', '0', '2021-11-05 15:56:23', null);
INSERT INTO `t_module` VALUES ('4', '营销机会管理添加', null, null, '2', '0', null, null, '4', '0', '2021-11-11 11:22:08', null);
INSERT INTO `t_module` VALUES ('5', '营销机会管理修改', null, null, '2', '0', null, null, '5', '0', '2021-11-11 11:23:44', null);
INSERT INTO `t_module` VALUES ('6', '营销机会管理删除', null, null, '2', '0', null, null, '6', '0', '2021-11-11 11:24:01', null);
INSERT INTO `t_module` VALUES ('7', '客户开发计划', null, null, '1', '0', null, null, '7', '0', '2021-11-12 14:31:14', null);
INSERT INTO `t_module` VALUES ('8', '开发计划查询', null, null, '7', '0', null, null, '8', '0', '2021-11-12 14:35:32', null);
INSERT INTO `t_module` VALUES ('9', '计划项详情查看', null, null, '7', '0', null, null, '9', '0', '2021-11-12 14:37:27', null);
INSERT INTO `t_module` VALUES ('10', '计划项开发', null, null, '7', '0', null, null, '10', '0', '2021-11-12 14:37:47', null);
INSERT INTO `t_module` VALUES ('11', '客户管理', null, null, '-1', '0', null, null, '11', '0', '2021-11-12 14:39:57', null);

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` int NOT NULL,
  `role_id` int NOT NULL,
  `module_id` int NOT NULL,
  `acl_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
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
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_remark` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT NULL,
  `is_valid` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '系统管理员', '所有权限', '2021-11-03 10:41:06', null, '0');
INSERT INTO `t_role` VALUES ('2', '销售', '无系统设置模块权限（除修改资料）', '2021-11-03 10:41:52', null, '0');
INSERT INTO `t_role` VALUES ('3', '客户经理', '无系统设置模块权限（除修改资料）', '2021-11-03 10:42:01', null, '0');
INSERT INTO `t_role` VALUES ('4', '技术经理', '无系统设置模块权限（除修改资料）', '2021-11-03 10:42:27', null, '0');
INSERT INTO `t_role` VALUES ('5', '人力资源经理', '无系统设置模块权限（除修改资料）', '2021-11-05 00:00:00', '2021-11-05 11:31:28', '0');

-- ----------------------------
-- Table structure for t_sale_chance
-- ----------------------------
DROP TABLE IF EXISTS `t_sale_chance`;
CREATE TABLE `t_sale_chance` (
  `id` int NOT NULL AUTO_INCREMENT,
  `chance_source` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '机会来源',
  `customer_name` varchar(255) DEFAULT NULL COMMENT '客户名称',
  `probability` int DEFAULT NULL COMMENT '成功几率',
  `overview` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '概要',
  `contract_person` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `contract_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '描述',
  `create_person` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `assign_person` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '分配人',
  `assign_date` datetime DEFAULT NULL COMMENT '分配时间',
  `state` int DEFAULT NULL COMMENT '分配状态 0 未分配 1 已分配',
  `dev_result` int DEFAULT NULL COMMENT '开发结果',
  `is_valid` int DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_sale_chance
-- ----------------------------
INSERT INTO `t_sale_chance` VALUES ('1', '官网', '锕锂芸计算有限公司 ', '60', '全球领先的云计算及人工智能科技公司', '马耘', '15008976432', '测试机会描述', '吴芮', '2021-10-21 00:00:00', '吴芮', '2021-11-04 00:00:00', '1', '2', '0', '2021-11-04 15:25:29');
INSERT INTO `t_sale_chance` VALUES ('2', '展会', '驣殉计算机系统有限公司 ', '80', '最大的互联网综合服务提供商之一', '马砉螣', '18934879265', '测试机会描述', '吴㕙', '2021-03-18 00:00:00', '吴芮', '2021-11-04 00:00:00', '1', '1', '0', '2021-11-04 15:25:24');
INSERT INTO `t_sale_chance` VALUES ('3', '展会', '魍弌网络有限公司', '75', '中国领先的互联网技术公司', '丁攂', '17398438954', '测试机会描述', '吴㕙', '2021-10-22 00:00:00', '吴㕙', '2021-11-04 00:00:00', '1', '0', '0', '2021-11-04 11:12:12');
INSERT INTO `t_sale_chance` VALUES ('4', '官网', '枔誏网技术有限公司', '80', '一家服务于中国及全球华人社群的网络媒体公司', '曹椁葳', '18975638638', '测试机会描述', '吴芮', '2021-10-25 00:00:00', '', null, '1', '0', '0', '2021-10-25 16:45:03');
INSERT INTO `t_sale_chance` VALUES ('15', '测试', '测试', '80', '测试概述', '测试', '18000000000', '', '吴芮', '2021-11-04 00:00:00', '', null, '0', '0', '0', '2021-11-05 11:29:48');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int NOT NULL,
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_pwd` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
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
INSERT INTO `t_user` VALUES ('1', 'Pacem3cty', 'e10adc3949ba59abbe56e057f20f883e', '吴芮', '1556250364@qq.com', '19868586764', '一定要出重拳', '0', '2021-10-09 16:15:20', '2021-11-03 10:03:46');
INSERT INTO `t_user` VALUES ('2', 'Pacem', 'e35cf7b66449df565f93c607d5a81d09', '吴㕙', '1556251364@qq.com', '19868586762', '牙医shakeit', '0', '2021-10-29 00:00:00', '2021-11-03 09:22:21');
INSERT INTO `t_user` VALUES ('3', '测试', 'e35cf7b66449df565f93c607d5a81d09', '测试', '', '18000000000', '测试备注', '0', '2021-11-03 00:00:00', '2021-11-11 09:59:44');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `role_id` int DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `t_user_role_ibfk_1` (`user_id`),
  KEY `t_user_role_ibfk_2` (`role_id`),
  CONSTRAINT `t_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '1', '1', '2021-11-12 10:48:16', null);
INSERT INTO `t_user_role` VALUES ('2', '2', '2', '2021-11-12 11:17:53', null);
INSERT INTO `t_user_role` VALUES ('3', '3', '3', '2021-11-03 00:00:00', '2021-11-11 09:59:44');
