/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80026
Source Host           : localhost:3306
Source Database       : crm

Target Server Type    : MYSQL
Target Server Version : 80026
File Encoding         : 65001

Date: 2022-02-16 10:09:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_cus
-- ----------------------------
DROP TABLE IF EXISTS `t_cus`;
CREATE TABLE `t_cus` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '客户编号',
  `cus_num` varchar(20) DEFAULT NULL,
  `cus_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '客户名称',
  `area` varchar(20) DEFAULT NULL COMMENT '所属地区',
  `cus_manager` varchar(20) DEFAULT NULL COMMENT '客户经理',
  `level` varchar(30) DEFAULT NULL COMMENT '客户级别',
  `csr` varchar(30) DEFAULT NULL COMMENT '客户满意度',
  `ccr` varchar(30) DEFAULT NULL COMMENT '客户信用度',
  `cus_address` varchar(255) DEFAULT NULL COMMENT '客户地址',
  `postal_code` varchar(50) DEFAULT NULL COMMENT '邮编',
  `cus_phone` varchar(20) DEFAULT NULL COMMENT '客户电话',
  `cus_fax` varchar(20) DEFAULT NULL COMMENT '传真',
  `website` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '客户网站',
  `social_credit_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '统一社会信用代码',
  `artificial_person` varchar(20) DEFAULT NULL COMMENT '法人代表',
  `registered_capital` varchar(20) DEFAULT NULL COMMENT '注册资金',
  `annual_turnover` varchar(20) DEFAULT NULL COMMENT '年营业额',
  `account_bank` varchar(50) DEFAULT NULL COMMENT '开户银行',
  `account_num` varchar(50) DEFAULT NULL COMMENT '银行账号',
  `loss_status` int DEFAULT NULL COMMENT '流失状态',
  `is_valid` int DEFAULT NULL COMMENT '有效状态 0 有效 1 无效',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_cus
-- ----------------------------
INSERT INTO `t_cus` VALUES ('1', 'C202111240000001', '驣殉计算机系统有限公司', '深圳', '测试', '战略合作伙伴', '满意', '良好', '广东深圳', null, '18934879265', null, null, '91440300708461136T', '马砉螣', null, null, null, null, '0', '0', '2021-11-24 20:44:35', null);
INSERT INTO `t_cus` VALUES ('2', 'C202111240000002', '锕锂芸计算有限公司 ', '杭州', '测试', '重要客户', '一般', '良好', '浙江杭州', null, '15008976432', null, null, '91330100799655058B', '马耘', null, null, null, null, '0', '0', '2021-11-24 00:00:00', '2021-11-27 23:45:18');
INSERT INTO `t_cus` VALUES ('3', 'C202111270000003', '魍弌网络有限公司', '', '测试', '', '', null, '', '', '18934879265', '', null, '91330000788831167A', '丁攂', '', '', '', '', '1', '0', '2021-04-27 21:20:31', null);
INSERT INTO `t_cus` VALUES ('4', 'C202111270000004', '枔誏网技术有限公司', null, '测试', null, null, null, null, null, '18823846863', null, null, '911101087975504981', '曹椁葳', null, null, null, null, '1', '0', '2021-01-26 08:56:40', null);

-- ----------------------------
-- Table structure for t_cus_contact
-- ----------------------------
DROP TABLE IF EXISTS `t_cus_contact`;
CREATE TABLE `t_cus_contact` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '客户交往记录编号',
  `cus_id` int DEFAULT NULL COMMENT '客户编号',
  `contact_time` datetime DEFAULT NULL COMMENT '交往时间',
  `contact_address` varchar(255) DEFAULT NULL COMMENT '交往地址',
  `overview` varchar(255) DEFAULT NULL COMMENT '交往概述',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime DEFAULT NULL COMMENT '修改日期',
  `is_valid` int DEFAULT NULL COMMENT '有效状态 0 有效 1 无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_cus_contact
-- ----------------------------
INSERT INTO `t_cus_contact` VALUES ('1', '1', '2021-11-28 00:00:00', '广东广州', '测试概述', '2021-11-28 00:00:00', '2021-11-29 15:41:31', '0');
INSERT INTO `t_cus_contact` VALUES ('2', '1', '2021-11-15 00:00:00', '广东深圳', '测试交往概述', '2021-11-29 15:26:58', null, '0');
INSERT INTO `t_cus_contact` VALUES ('3', '1', '2021-11-03 00:00:00', '测试地址', '测试概述', '2021-11-29 15:41:54', null, '0');
INSERT INTO `t_cus_contact` VALUES ('4', '1', '2021-11-03 00:00:00', '测试地址', '测试概述', '2021-11-29 15:45:18', null, '0');

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
-- Table structure for t_cus_linkman
-- ----------------------------
DROP TABLE IF EXISTS `t_cus_linkman`;
CREATE TABLE `t_cus_linkman` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '客户联系人编号',
  `cus_id` int DEFAULT NULL COMMENT '客户编号',
  `linkman_name` varchar(20) DEFAULT NULL COMMENT '联系人名称',
  `sex` int DEFAULT NULL COMMENT '性别 0 男 | 1 女',
  `position` varchar(50) DEFAULT NULL COMMENT '职位',
  `office_phone` varchar(50) DEFAULT NULL COMMENT '办公电话',
  `phone` varchar(50) DEFAULT NULL COMMENT '手机号码',
  `is_valid` int DEFAULT NULL COMMENT '有效状态',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_cus_linkman
-- ----------------------------
INSERT INTO `t_cus_linkman` VALUES ('1', '1', '测试姓名', '1', '测试性别', '18000000000', null, '0', '2021-11-29 00:00:00', '2021-11-29 14:01:33');
INSERT INTO `t_cus_linkman` VALUES ('8', '1', '测试姓名', '0', '测试职位', '18000000000', null, '0', '2021-11-29 15:48:56', null);

-- ----------------------------
-- Table structure for t_cus_loss
-- ----------------------------
DROP TABLE IF EXISTS `t_cus_loss`;
CREATE TABLE `t_cus_loss` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '客户流失记录编号',
  `cus_num` varchar(20) DEFAULT NULL COMMENT '客户编号',
  `cus_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `cus_manager` varchar(20) DEFAULT NULL COMMENT '客户经理',
  `last_order_time` datetime DEFAULT NULL COMMENT '最后下单时间',
  `confirm_loss_time` datetime DEFAULT NULL COMMENT '确认流失时间',
  `state` int DEFAULT NULL COMMENT '流失状态',
  `loss_reason` varchar(255) DEFAULT NULL COMMENT '流失原因',
  `is_valid` int DEFAULT NULL COMMENT '有效状态',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`),
  KEY `cus_id` (`cus_num`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_cus_loss
-- ----------------------------
INSERT INTO `t_cus_loss` VALUES ('14', 'C202111270000003', '魍弌网络有限公司', '测试', '2018-12-06 00:00:00', null, '0', null, '0', '2021-12-03 16:47:23', '2021-12-03 16:47:23');
INSERT INTO `t_cus_loss` VALUES ('15', 'C202111270000004', '枔誏网技术有限公司', '测试', '2013-08-13 00:00:00', null, '0', null, '0', '2021-12-03 16:47:23', '2021-12-03 16:47:23');

-- ----------------------------
-- Table structure for t_cus_order
-- ----------------------------
DROP TABLE IF EXISTS `t_cus_order`;
CREATE TABLE `t_cus_order` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `cus_id` int DEFAULT NULL COMMENT '客户编号',
  `order_num` varchar(40) DEFAULT NULL COMMENT '订单编号',
  `order_date` datetime DEFAULT NULL COMMENT '下单时间',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `order_total` decimal(10,2) DEFAULT NULL COMMENT '订单总额',
  `state` int DEFAULT NULL COMMENT '状态',
  `is_valid` int DEFAULT NULL COMMENT '有效状态 0 有效 1 无效',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_cus_order
-- ----------------------------
INSERT INTO `t_cus_order` VALUES ('1', '1', 'O202111300000001', '2021-11-30 00:00:00', '广东深圳', '4696.49', '1', '0', '2021-11-30 00:00:00', '2021-12-01 10:15:37');
INSERT INTO `t_cus_order` VALUES ('3', '1', 'O202111300000002', '2021-11-04 00:00:00', '广东广州', null, '0', '0', '2021-11-30 10:18:22', '2021-11-30 00:00:00');
INSERT INTO `t_cus_order` VALUES ('6', '2', 'O202112010000006', '2021-09-08 00:00:00', '浙江杭州', '1800.00', '1', '0', '2021-12-01 12:10:23', '2021-12-01 12:21:04');
INSERT INTO `t_cus_order` VALUES ('7', '3', 'O202112030000007', '2018-12-06 00:00:00', '广东广州', null, '0', '0', '2021-12-03 12:27:24', null);
INSERT INTO `t_cus_order` VALUES ('8', '3', 'O202112030000008', '2015-12-02 00:00:00', '广州深圳', null, '0', '0', '2021-12-03 12:51:03', null);
INSERT INTO `t_cus_order` VALUES ('9', '4', 'O202112030000009', '2013-08-13 00:00:00', '广东深圳', null, '0', '0', '2021-12-03 15:27:58', null);

-- ----------------------------
-- Table structure for t_cus_reprieve
-- ----------------------------
DROP TABLE IF EXISTS `t_cus_reprieve`;
CREATE TABLE `t_cus_reprieve` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '暂缓记录编号',
  `loss_id` int DEFAULT NULL COMMENT '流失编号',
  `measure` varchar(255) DEFAULT NULL COMMENT '暂缓措施',
  `is_valid` int DEFAULT NULL COMMENT '有效状态',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_cus_reprieve
-- ----------------------------

-- ----------------------------
-- Table structure for t_cus_service
-- ----------------------------
DROP TABLE IF EXISTS `t_cus_service`;
CREATE TABLE `t_cus_service` (
  `id` int NOT NULL COMMENT '客户服务编号',
  `service_type` varchar(30) DEFAULT NULL COMMENT '服务类型',
  `overview` varchar(255) DEFAULT NULL COMMENT '概述',
  `customer` varchar(30) DEFAULT NULL COMMENT '客户',
  `state` varchar(20) DEFAULT NULL COMMENT '服务状态',
  `service_request` varchar(255) DEFAULT NULL COMMENT '服务请求',
  `create_person` varchar(100) DEFAULT NULL COMMENT '创建人',
  `assign_person` varchar(100) DEFAULT NULL COMMENT '指派人',
  `assign_date` datetime DEFAULT NULL COMMENT '分配日期',
  `service_process` varchar(255) DEFAULT NULL COMMENT '服务处理',
  `process_person` varchar(20) DEFAULT NULL COMMENT '处理人',
  `process_date` datetime DEFAULT NULL COMMENT '处理时间',
  `process_result` varchar(255) DEFAULT NULL COMMENT '处理结果',
  `csr` varchar(50) DEFAULT NULL COMMENT '客户满意度',
  `is_valid` int DEFAULT NULL COMMENT '有效状态',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_cus_service
-- ----------------------------

-- ----------------------------
-- Table structure for t_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_dict`;
CREATE TABLE `t_dict` (
  `id` int NOT NULL COMMENT '数据字典主键id',
  `data_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据类型',
  `data_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据名称',
  `data_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据值',
  `is_valid` int DEFAULT NULL COMMENT '有效状态 0 有效 | 1 无效',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_dict
-- ----------------------------
INSERT INTO `t_dict` VALUES ('1', '测试数据类型', '测试数据名称', '测试值', '0', '2022-01-07 00:00:00', '2022-01-07 14:48:52');
INSERT INTO `t_dict` VALUES ('2', '测试数据类型', '测试名称', '测试数据值', '0', '2022-01-07 14:25:29', null);

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
  `parent_opt_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
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
INSERT INTO `t_module` VALUES ('1', '营销管理', null, '#', '-1', '', null, '010', null, '0', '2021-11-05 15:51:07', null);
INSERT INTO `t_module` VALUES ('2', '营销机会管理', null, '/sales/*', '1', '010', null, '0101', null, '0', '2021-11-05 15:53:35', '2021-11-22 11:21:36');
INSERT INTO `t_module` VALUES ('3', '营销机会信息查询', null, '/sales/queryAll', '2', '0101', null, '01011', null, '0', '2021-11-05 15:56:23', null);
INSERT INTO `t_module` VALUES ('4', '营销机会信息新增', null, '/sales/add', '2', '0101', null, '01012', null, '0', '2021-11-11 11:22:08', '2021-11-22 11:18:37');
INSERT INTO `t_module` VALUES ('5', '营销机会信息修改', null, '/sales/update', '2', '0101', null, '01013', null, '0', '2021-11-11 11:23:44', null);
INSERT INTO `t_module` VALUES ('6', '营销机会信息删除', null, '/sales/delete', '2', '0101', null, '01014', null, '0', '2021-11-11 11:24:01', null);
INSERT INTO `t_module` VALUES ('7', '客户开发计划', null, '/sales/tCusDevPlan', '1', '010', null, '0102', null, '0', '2021-11-12 14:31:14', null);
INSERT INTO `t_module` VALUES ('8', '计划项数据查询', null, '/sales/tCusDevPlan/queryAll', '7', '0102', null, '01021', null, '0', '2021-11-12 14:35:32', null);
INSERT INTO `t_module` VALUES ('9', '计划项数据新增', null, '/sales/tCusDevPlan/add', '7', '0102', null, '01022', null, '0', '2021-11-22 15:39:17', null);
INSERT INTO `t_module` VALUES ('10', '计划项数据修改', null, '/sales/tCusDevPlan/update', '7', '0102', null, '01023', null, '0', '2021-11-12 14:37:47', '2021-11-22 15:37:37');
INSERT INTO `t_module` VALUES ('11', '计划项数据删除', null, '/sales/tCusDevPlan/delete', '7', '0102', null, '01024', null, '0', '2021-11-12 14:37:27', null);
INSERT INTO `t_module` VALUES ('12', '客户管理', null, '#', '-1', '', null, '020', null, '0', '2021-11-12 14:39:57', null);
INSERT INTO `t_module` VALUES ('13', '客户信息管理', null, '/cus/*', '12', '020', null, '0201', null, '0', '2021-11-15 08:53:32', null);
INSERT INTO `t_module` VALUES ('14', '客户信息查询', null, '/cus/queryAll', '13', '0201', null, '02011', null, '0', '2021-11-15 08:54:01', null);
INSERT INTO `t_module` VALUES ('15', '客户信息新增', null, '/cus/add', '13', '0201', null, '02012', null, '0', '2021-11-15 08:55:00', null);
INSERT INTO `t_module` VALUES ('16', '客户信息修改', null, '/cus/update', '13', '0201', null, '02013', null, '0', '2021-11-15 08:55:19', null);
INSERT INTO `t_module` VALUES ('17', '客户信息删除', null, '/cus/delete', '13', '0201', null, '02014', null, '0', '2021-11-15 08:57:21', null);
INSERT INTO `t_module` VALUES ('18', '客户交往记录查询', null, '/cusContact/queryAll', '13', '0201', null, '0201C1', null, '0', '2022-01-06 09:47:19', null);
INSERT INTO `t_module` VALUES ('19', '客户交往记录新增', null, '/cusContact/add', '13', '0201', null, '0201C2', null, '0', '2022-01-06 09:53:33', null);
INSERT INTO `t_module` VALUES ('20', '客户交往记录修改', null, '/cusContact/update', '13', '0201', null, '0201C3', null, '0', '2022-01-06 09:54:48', null);
INSERT INTO `t_module` VALUES ('21', '客户交往记录删除', null, '/cusContact/delete', '13', '0201', null, '0201C4', null, '0', '2022-01-06 09:55:32', null);
INSERT INTO `t_module` VALUES ('22', '客户联系人信息查询', null, '/cusLinkman/queryAll', '13', '0201', null, '0201L1', null, '0', '2022-01-06 10:04:19', null);
INSERT INTO `t_module` VALUES ('23', '客户联系人信息新增', null, '/cusLinkman/add', '13', '0201', null, '0201L2', null, '0', '2022-01-06 10:07:51', null);
INSERT INTO `t_module` VALUES ('24', '客户联系人信息修改', null, '/cusLinkman/update', '13', '0201', null, '0201L3', null, '0', '2022-01-06 10:09:11', null);
INSERT INTO `t_module` VALUES ('25', '客户联系人信息删除', null, '/cusLinkman/delete', '13', '0201', null, '0201L4', null, '0', '2022-01-06 10:10:13', null);
INSERT INTO `t_module` VALUES ('26', '客户订单信息查询', null, '/cusOrder/queryAll', '13', '0201', null, '0201O1', null, '0', '2022-01-06 10:16:46', null);
INSERT INTO `t_module` VALUES ('27', '客户订单信息新增', null, '/cusOrder/add', '13', '0201', null, '0201O2', null, '0', '2022-01-06 10:17:42', null);
INSERT INTO `t_module` VALUES ('28', '客户订单信息修改', null, '/cusOrder/update', '13', '0201', null, '0201O3', null, '0', '2022-01-06 10:20:27', null);
INSERT INTO `t_module` VALUES ('29', '客户订单信息删除', null, '/cusOrder/delete', '13', '0201', null, '0201O4', null, '0', '2022-01-06 10:18:29', null);
INSERT INTO `t_module` VALUES ('30', '客户订单详情查询', null, '/cusOrderDetails/queryAll', '13', '0201', null, '0201OD1', null, '0', '2022-01-06 10:41:54', null);
INSERT INTO `t_module` VALUES ('31', '客户订单详情新增', null, '/cusOrderDetails/add', '13', '0201', null, '0201OD2', null, '0', '2022-01-06 10:47:07', null);
INSERT INTO `t_module` VALUES ('32', '客户订单详情修改', null, '/cusOrderDetails/update', '13', '0201', null, '0201OD3', null, '0', '2022-01-06 10:47:41', null);
INSERT INTO `t_module` VALUES ('33', '客户订单详情删除', null, '/cusOrderDetails/delete', '13', '0201', null, '0201OD4', null, '0', '2022-01-06 10:48:13', null);
INSERT INTO `t_module` VALUES ('34', '客户流失管理', null, '/cusLoss/*', '12', '020', null, '0202', null, '0', '2021-11-15 08:58:00', null);
INSERT INTO `t_module` VALUES ('35', '客户流失信息查询', null, '/cusLoss/queryAll', '34', '0202', null, '02021', null, '0', '2021-11-15 08:58:35', null);
INSERT INTO `t_module` VALUES ('36', '客户流失信息修改', null, '/cusLoss/update', '34', '0202', null, '02022', null, '0', '2021-11-15 08:59:22', null);
INSERT INTO `t_module` VALUES ('37', '客户暂缓流失信息查询', null, '/cusReprieve/queryAll', '34', '0202', null, '0202R1', null, '0', '2022-01-06 11:18:37', null);
INSERT INTO `t_module` VALUES ('38', '客户暂缓流失信息新增', null, '/cusReprieve/add', '34', '0202', null, '0202R2', null, '0', '2022-01-06 12:06:13', null);
INSERT INTO `t_module` VALUES ('39', '客户暂缓流失信息修改', null, '/cusReprieve/update', '34', '0202', null, '0202R3', null, '0', '2022-01-06 12:09:50', null);
INSERT INTO `t_module` VALUES ('40', '客户暂缓流失信息删除', null, '/cusReprieve/delete', '34', '0202', null, '0202R4', null, '0', '2022-01-06 12:10:44', null);
INSERT INTO `t_module` VALUES ('42', '服务管理', null, '#', '-1', null, null, '030', null, '0', '2022-01-06 13:58:37', null);
INSERT INTO `t_module` VALUES ('43', '服务信息查询', null, '/service/queryAll', '42', '030', null, '0301', null, '0', '2022-01-06 14:00:36', null);
INSERT INTO `t_module` VALUES ('44', '服务信息新增', null, '/service/add', '42', '030', null, '0302', null, '0', '2022-01-06 14:00:57', null);
INSERT INTO `t_module` VALUES ('45', '服务信息修改', null, '/service/update', '42', '030', null, '0303', null, '0', '2022-01-06 14:01:21', null);
INSERT INTO `t_module` VALUES ('46', '服务信息删除', null, '/service/delete', '42', '030', null, '0304', null, '0', '2022-01-06 14:01:50', null);
INSERT INTO `t_module` VALUES ('47', '统计分析', null, '#', '-1', null, null, '040', null, '0', '2022-01-06 14:06:03', null);
INSERT INTO `t_module` VALUES ('48', '客户贡献分析查询', null, '/stats/listCusCTRB', '47', '040', null, '0401', null, '0', '2022-01-06 14:23:18', null);
INSERT INTO `t_module` VALUES ('49', '客户构成分析查询', null, '/stats/listConsistStats', '47', '040', null, '0402', null, '0', '2022-01-06 14:32:56', null);
INSERT INTO `t_module` VALUES ('50', '客户服务分析查询', null, '/stats/listServiceTypeStats', '47', '040', null, '0403', null, '0', '2022-01-06 14:33:24', null);
INSERT INTO `t_module` VALUES ('51', '客户流失分析查询', null, null, '47', '040', null, null, null, null, '2022-01-06 14:52:45', null);
INSERT INTO `t_module` VALUES ('52', '系统设置', null, '#', '-1', null, null, '050', null, '0', '2022-01-06 14:37:26', null);
INSERT INTO `t_module` VALUES ('53', '字典管理', null, '/dict/*', '52', '050', null, '0501', null, '0', '2022-01-06 14:43:04', null);
INSERT INTO `t_module` VALUES ('54', '字典信息查询', null, '/dict/queryAll', '53', '0501', null, '05011', null, '0', '2022-01-06 14:47:21', null);
INSERT INTO `t_module` VALUES ('55', '字典信息新增', null, '/dict/add', '53', '0501', null, '05012', null, '0', '2022-01-06 14:47:37', null);
INSERT INTO `t_module` VALUES ('56', '字典信息修改', null, '/dict/update', '53', '0501', null, '05013', null, '0', '2022-01-06 14:49:10', null);
INSERT INTO `t_module` VALUES ('57', '字典信息删除', null, '/dict/delete', '53', '0501', null, '05014', null, '0', '2022-01-06 14:49:42', null);
INSERT INTO `t_module` VALUES ('58', '用户管理', null, '/user/*', '52', '050', null, '0502', null, '0', '2022-01-06 14:51:34', null);
INSERT INTO `t_module` VALUES ('59', '用户信息查询', null, '/user/queryAll', '58', '0502', null, '05021', null, '0', '2022-01-06 14:54:09', null);
INSERT INTO `t_module` VALUES ('60', '用户信息新增', null, '/user/add', '58', '0502', null, '05022', null, '0', '2022-01-06 15:02:31', null);
INSERT INTO `t_module` VALUES ('61', '用户信息修改', null, '/user/update', '58', '0502', null, '05023', null, '0', '2022-01-06 15:04:03', null);
INSERT INTO `t_module` VALUES ('62', '用户信息删除', null, '/user/delete', '58', '0502', null, '05024', null, '0', '2022-01-06 15:04:30', null);
INSERT INTO `t_module` VALUES ('63', '用户-角色信息查询', null, '/userRole/queryRoleId', '58', '0502', null, '0502R1', null, '0', '2022-01-06 15:07:02', null);
INSERT INTO `t_module` VALUES ('64', '用户-角色信息新增', null, '/userRole/add', '58', '0502', null, '0502R2', null, '0', '2022-01-06 15:09:40', null);
INSERT INTO `t_module` VALUES ('65', '用户-角色信息修改', null, '/userRole/update', '58', '0502', null, '0502R3', null, '0', '2022-01-06 15:13:35', null);
INSERT INTO `t_module` VALUES ('66', '角色管理', null, '/role/*', '52', '050', null, '0503', null, '0', '2022-01-06 15:16:47', null);
INSERT INTO `t_module` VALUES ('67', '角色信息查询', null, '/role/queryAll', '66', '0503', null, '05031', null, '0', '2022-01-06 15:19:40', null);
INSERT INTO `t_module` VALUES ('68', '角色信息新增', null, '/role/add', '66', '0503', null, '05032', null, '0', '2022-01-06 15:19:52', null);
INSERT INTO `t_module` VALUES ('69', '角色信息修改', null, '/role/update', '66', '0503', null, '05033', null, '0', '2022-01-06 15:20:26', null);
INSERT INTO `t_module` VALUES ('70', '角色信息删除', null, '/role/delete', '66', '0503', null, '05034', null, '0', '2022-01-06 15:20:53', null);
INSERT INTO `t_module` VALUES ('71', '角色-资源信息查询', null, '/permission/loadData', '66', '0503', null, '0503P1', null, '0', '2022-01-06 15:47:58', null);
INSERT INTO `t_module` VALUES ('72', '角色资源授权', null, '/permission/authModules', '66', '0503', null, '0503P2', null, '0', '2022-01-06 15:48:34', null);
INSERT INTO `t_module` VALUES ('73', '资源管理', null, '/module/*', '52', '050', null, '0504', null, '0', '2022-01-06 15:53:11', null);
INSERT INTO `t_module` VALUES ('74', '资源信息查询', null, '/module/queryAll', '73', '0504', null, '05041', null, '0', '2022-01-06 15:53:24', null);
INSERT INTO `t_module` VALUES ('75', '资源信息新增', null, '/module/add', '73', '0504', null, '05042', null, '0', '2022-01-06 15:53:57', null);
INSERT INTO `t_module` VALUES ('76', '资源信息修改', null, '/module/update', '73', '0504', null, '05043', null, '0', '2022-01-06 15:56:02', null);
INSERT INTO `t_module` VALUES ('77', '资源信息删除', null, '/module/delete', '73', '0504', null, '05044', null, '0', '2022-01-06 15:56:11', null);

-- ----------------------------
-- Table structure for t_order_details
-- ----------------------------
DROP TABLE IF EXISTS `t_order_details`;
CREATE TABLE `t_order_details` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '详情编号',
  `order_id` int DEFAULT NULL COMMENT '订单编号',
  `goods_name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `goods_num` int DEFAULT NULL COMMENT '商品数量',
  `unit` varchar(20) DEFAULT NULL COMMENT '商品单位',
  `price` decimal(10,2) DEFAULT NULL COMMENT '单价',
  `total` decimal(10,2) DEFAULT NULL COMMENT '总金额',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `is_valid` int DEFAULT NULL COMMENT '有效状态',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_order_details
-- ----------------------------
INSERT INTO `t_order_details` VALUES ('1', '1', '测试商品1', '1', '盒', '299.99', '299.99', null, '0', '2021-11-30 00:00:00', '2021-12-01 09:34:55');
INSERT INTO `t_order_details` VALUES ('2', '1', '测试商品2', '4', '箱', '499.50', '1998.00', null, '0', '2021-11-30 15:46:45', null);
INSERT INTO `t_order_details` VALUES ('3', '1', '测试商品3', '2', '箱', '600.00', '1200.00', null, '0', '2021-11-30 00:00:00', '2021-11-30 16:13:01');
INSERT INTO `t_order_details` VALUES ('4', '1', '测试商品4', '3', '瓶', '399.50', '1198.50', null, '0', '2021-11-30 00:00:00', '2021-11-30 16:17:03');
INSERT INTO `t_order_details` VALUES ('5', '6', '测试商品1', '3', '箱', '600.00', '1800.00', null, '0', '2021-12-01 12:21:01', null);

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
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
) ENGINE=InnoDB AUTO_INCREMENT=329 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('110', '2', '1', '010', '2022-01-06 16:06:40', '2022-01-06 16:06:40');
INSERT INTO `t_permission` VALUES ('111', '2', '2', '0101', '2022-01-06 16:06:40', '2022-01-06 16:06:40');
INSERT INTO `t_permission` VALUES ('112', '2', '3', '01011', '2022-01-06 16:06:40', '2022-01-06 16:06:40');
INSERT INTO `t_permission` VALUES ('113', '2', '4', '01012', '2022-01-06 16:06:40', '2022-01-06 16:06:40');
INSERT INTO `t_permission` VALUES ('114', '2', '5', '01013', '2022-01-06 16:06:40', '2022-01-06 16:06:40');
INSERT INTO `t_permission` VALUES ('115', '2', '6', '01014', '2022-01-06 16:06:40', '2022-01-06 16:06:40');
INSERT INTO `t_permission` VALUES ('116', '2', '7', '0102', '2022-01-06 16:06:40', '2022-01-06 16:06:40');
INSERT INTO `t_permission` VALUES ('117', '2', '8', '01021', '2022-01-06 16:06:40', '2022-01-06 16:06:40');
INSERT INTO `t_permission` VALUES ('118', '2', '9', '01022', '2022-01-06 16:06:40', '2022-01-06 16:06:40');
INSERT INTO `t_permission` VALUES ('119', '2', '10', '01023', '2022-01-06 16:06:40', '2022-01-06 16:06:40');
INSERT INTO `t_permission` VALUES ('120', '2', '11', '01024', '2022-01-06 16:06:40', '2022-01-06 16:06:40');
INSERT INTO `t_permission` VALUES ('121', '5', '58', '0502', '2022-01-06 16:09:58', '2022-01-06 16:09:58');
INSERT INTO `t_permission` VALUES ('122', '5', '59', '05021', '2022-01-06 16:09:58', '2022-01-06 16:09:58');
INSERT INTO `t_permission` VALUES ('123', '5', '60', '05022', '2022-01-06 16:09:58', '2022-01-06 16:09:58');
INSERT INTO `t_permission` VALUES ('124', '5', '61', '05023', '2022-01-06 16:09:58', '2022-01-06 16:09:58');
INSERT INTO `t_permission` VALUES ('125', '5', '62', '05024', '2022-01-06 16:09:58', '2022-01-06 16:09:58');
INSERT INTO `t_permission` VALUES ('126', '5', '63', '0502R1', '2022-01-06 16:09:58', '2022-01-06 16:09:58');
INSERT INTO `t_permission` VALUES ('127', '5', '64', '0502R2', '2022-01-06 16:09:58', '2022-01-06 16:09:58');
INSERT INTO `t_permission` VALUES ('128', '5', '65', '0502R3', '2022-01-06 16:09:58', '2022-01-06 16:09:58');
INSERT INTO `t_permission` VALUES ('129', '5', '66', '0503', '2022-01-06 16:09:58', '2022-01-06 16:09:58');
INSERT INTO `t_permission` VALUES ('130', '5', '67', '05031', '2022-01-06 16:09:58', '2022-01-06 16:09:58');
INSERT INTO `t_permission` VALUES ('131', '5', '68', '05032', '2022-01-06 16:09:58', '2022-01-06 16:09:58');
INSERT INTO `t_permission` VALUES ('132', '5', '69', '05033', '2022-01-06 16:09:58', '2022-01-06 16:09:58');
INSERT INTO `t_permission` VALUES ('133', '5', '70', '05034', '2022-01-06 16:09:58', '2022-01-06 16:09:58');
INSERT INTO `t_permission` VALUES ('134', '5', '71', '0503P1', '2022-01-06 16:09:58', '2022-01-06 16:09:58');
INSERT INTO `t_permission` VALUES ('135', '5', '72', '0503P2', '2022-01-06 16:09:58', '2022-01-06 16:09:58');
INSERT INTO `t_permission` VALUES ('136', '3', '12', '020', '2022-01-06 16:11:15', '2022-01-06 16:11:15');
INSERT INTO `t_permission` VALUES ('137', '3', '13', '0201', '2022-01-06 16:11:15', '2022-01-06 16:11:15');
INSERT INTO `t_permission` VALUES ('138', '3', '14', '02011', '2022-01-06 16:11:15', '2022-01-06 16:11:15');
INSERT INTO `t_permission` VALUES ('139', '3', '15', '02012', '2022-01-06 16:11:15', '2022-01-06 16:11:15');
INSERT INTO `t_permission` VALUES ('140', '3', '16', '02013', '2022-01-06 16:11:15', '2022-01-06 16:11:15');
INSERT INTO `t_permission` VALUES ('141', '3', '17', '02014', '2022-01-06 16:11:15', '2022-01-06 16:11:15');
INSERT INTO `t_permission` VALUES ('142', '3', '18', '0201C1', '2022-01-06 16:11:15', '2022-01-06 16:11:15');
INSERT INTO `t_permission` VALUES ('143', '3', '19', '0201C2', '2022-01-06 16:11:15', '2022-01-06 16:11:15');
INSERT INTO `t_permission` VALUES ('144', '3', '20', '0201C3', '2022-01-06 16:11:15', '2022-01-06 16:11:15');
INSERT INTO `t_permission` VALUES ('145', '3', '21', '0201C4', '2022-01-06 16:11:15', '2022-01-06 16:11:15');
INSERT INTO `t_permission` VALUES ('146', '3', '22', '0201L1', '2022-01-06 16:11:15', '2022-01-06 16:11:15');
INSERT INTO `t_permission` VALUES ('147', '3', '23', '0201L2', '2022-01-06 16:11:15', '2022-01-06 16:11:15');
INSERT INTO `t_permission` VALUES ('148', '3', '24', '0201L3', '2022-01-06 16:11:15', '2022-01-06 16:11:15');
INSERT INTO `t_permission` VALUES ('149', '3', '25', '0201L4', '2022-01-06 16:11:15', '2022-01-06 16:11:15');
INSERT INTO `t_permission` VALUES ('150', '3', '26', '0201O1', '2022-01-06 16:11:15', '2022-01-06 16:11:15');
INSERT INTO `t_permission` VALUES ('151', '3', '27', '0201O2', '2022-01-06 16:11:15', '2022-01-06 16:11:15');
INSERT INTO `t_permission` VALUES ('152', '3', '28', '0201O3', '2022-01-06 16:11:15', '2022-01-06 16:11:15');
INSERT INTO `t_permission` VALUES ('153', '3', '29', '0201O4', '2022-01-06 16:11:15', '2022-01-06 16:11:15');
INSERT INTO `t_permission` VALUES ('154', '3', '30', '0201OD1', '2022-01-06 16:11:15', '2022-01-06 16:11:15');
INSERT INTO `t_permission` VALUES ('155', '3', '31', '0201OD2', '2022-01-06 16:11:15', '2022-01-06 16:11:15');
INSERT INTO `t_permission` VALUES ('156', '3', '32', '0201OD3', '2022-01-06 16:11:15', '2022-01-06 16:11:15');
INSERT INTO `t_permission` VALUES ('157', '3', '33', '0201OD4', '2022-01-06 16:11:15', '2022-01-06 16:11:15');
INSERT INTO `t_permission` VALUES ('158', '3', '34', '0202', '2022-01-06 16:11:15', '2022-01-06 16:11:15');
INSERT INTO `t_permission` VALUES ('159', '3', '35', '02021', '2022-01-06 16:11:15', '2022-01-06 16:11:15');
INSERT INTO `t_permission` VALUES ('160', '3', '36', '02022', '2022-01-06 16:11:15', '2022-01-06 16:11:15');
INSERT INTO `t_permission` VALUES ('161', '3', '37', '0202R1', '2022-01-06 16:11:15', '2022-01-06 16:11:15');
INSERT INTO `t_permission` VALUES ('162', '3', '38', '0202R2', '2022-01-06 16:11:15', '2022-01-06 16:11:15');
INSERT INTO `t_permission` VALUES ('163', '3', '39', '0202R3', '2022-01-06 16:11:15', '2022-01-06 16:11:15');
INSERT INTO `t_permission` VALUES ('164', '3', '40', '0202R4', '2022-01-06 16:11:15', '2022-01-06 16:11:15');
INSERT INTO `t_permission` VALUES ('165', '3', '42', '030', '2022-01-06 16:11:15', '2022-01-06 16:11:15');
INSERT INTO `t_permission` VALUES ('166', '3', '43', '0301', '2022-01-06 16:11:15', '2022-01-06 16:11:15');
INSERT INTO `t_permission` VALUES ('167', '3', '44', '0302', '2022-01-06 16:11:15', '2022-01-06 16:11:15');
INSERT INTO `t_permission` VALUES ('168', '3', '45', '0303', '2022-01-06 16:11:15', '2022-01-06 16:11:15');
INSERT INTO `t_permission` VALUES ('169', '3', '46', '0304', '2022-01-06 16:11:15', '2022-01-06 16:11:15');
INSERT INTO `t_permission` VALUES ('170', '3', '47', '040', '2022-01-06 16:11:15', '2022-01-06 16:11:15');
INSERT INTO `t_permission` VALUES ('171', '3', '48', '0401', '2022-01-06 16:11:15', '2022-01-06 16:11:15');
INSERT INTO `t_permission` VALUES ('244', '4', '53', '0501', '2022-01-07 11:08:27', '2022-01-07 11:08:27');
INSERT INTO `t_permission` VALUES ('245', '4', '54', '05011', '2022-01-07 11:08:27', '2022-01-07 11:08:27');
INSERT INTO `t_permission` VALUES ('246', '4', '55', '05012', '2022-01-07 11:08:27', '2022-01-07 11:08:27');
INSERT INTO `t_permission` VALUES ('247', '4', '56', '05013', '2022-01-07 11:08:27', '2022-01-07 11:08:27');
INSERT INTO `t_permission` VALUES ('248', '4', '57', '05014', '2022-01-07 11:08:27', '2022-01-07 11:08:27');
INSERT INTO `t_permission` VALUES ('249', '4', '73', '0504', '2022-01-07 11:08:27', '2022-01-07 11:08:27');
INSERT INTO `t_permission` VALUES ('250', '4', '74', '05041', '2022-01-07 11:08:27', '2022-01-07 11:08:27');
INSERT INTO `t_permission` VALUES ('251', '4', '75', '05042', '2022-01-07 11:08:27', '2022-01-07 11:08:27');
INSERT INTO `t_permission` VALUES ('252', '4', '76', '05043', '2022-01-07 11:08:27', '2022-01-07 11:08:27');
INSERT INTO `t_permission` VALUES ('253', '4', '77', '05044', '2022-01-07 11:08:27', '2022-01-07 11:08:27');
INSERT INTO `t_permission` VALUES ('254', '1', '1', '010', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('255', '1', '2', '0101', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('256', '1', '3', '01011', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('257', '1', '4', '01012', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('258', '1', '5', '01013', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('259', '1', '6', '01014', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('260', '1', '7', '0102', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('261', '1', '8', '01021', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('262', '1', '9', '01022', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('263', '1', '10', '01023', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('264', '1', '11', '01024', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('265', '1', '12', '020', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('266', '1', '13', '0201', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('267', '1', '14', '02011', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('268', '1', '15', '02012', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('269', '1', '16', '02013', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('270', '1', '17', '02014', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('271', '1', '18', '0201C1', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('272', '1', '19', '0201C2', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('273', '1', '20', '0201C3', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('274', '1', '21', '0201C4', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('275', '1', '22', '0201L1', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('276', '1', '23', '0201L2', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('277', '1', '24', '0201L3', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('278', '1', '25', '0201L4', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('279', '1', '26', '0201O1', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('280', '1', '27', '0201O2', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('281', '1', '28', '0201O3', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('282', '1', '29', '0201O4', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('283', '1', '30', '0201OD1', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('284', '1', '31', '0201OD2', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('285', '1', '32', '0201OD3', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('286', '1', '33', '0201OD4', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('287', '1', '34', '0202', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('288', '1', '35', '02021', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('289', '1', '36', '02022', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('290', '1', '37', '0202R1', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('291', '1', '38', '0202R2', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('292', '1', '39', '0202R3', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('293', '1', '40', '0202R4', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('294', '1', '42', '030', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('295', '1', '43', '0301', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('296', '1', '44', '0302', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('297', '1', '45', '0303', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('298', '1', '46', '0304', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('299', '1', '47', '040', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('300', '1', '48', '0401', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('301', '1', '49', '0402', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('302', '1', '50', '0403', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('303', '1', '52', '050', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('304', '1', '53', '0501', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('305', '1', '54', '05011', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('306', '1', '55', '05012', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('307', '1', '56', '05013', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('308', '1', '57', '05014', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('309', '1', '58', '0502', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('310', '1', '59', '05021', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('311', '1', '60', '05022', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('312', '1', '61', '05023', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('313', '1', '62', '05024', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('314', '1', '63', '0502R1', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('315', '1', '64', '0502R2', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('316', '1', '65', '0502R3', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('317', '1', '66', '0503', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('318', '1', '67', '05031', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('319', '1', '68', '05032', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('320', '1', '69', '05033', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('321', '1', '70', '05034', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('322', '1', '71', '0503P1', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('323', '1', '72', '0503P2', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('324', '1', '73', '0504', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('325', '1', '74', '05041', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('326', '1', '75', '05042', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('327', '1', '76', '05043', '2022-02-16 09:50:12', '2022-02-16 09:50:12');
INSERT INTO `t_permission` VALUES ('328', '1', '77', '05044', '2022-02-16 09:50:12', '2022-02-16 09:50:12');

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
INSERT INTO `t_role` VALUES ('1', '系统管理员', '所有模块权限', '2021-11-03 00:00:00', '2021-11-22 15:47:52', '0');
INSERT INTO `t_role` VALUES ('2', '销售', '仅营销管理模块权限', '2021-11-03 00:00:00', '2022-01-06 16:07:33', '0');
INSERT INTO `t_role` VALUES ('3', '客户经理', '仅客户、服务管理及统计报表模块权限', '2021-11-03 00:00:00', '2022-01-06 16:12:41', '0');
INSERT INTO `t_role` VALUES ('4', '技术经理', '仅资源管理及字典管理权限', '2021-11-03 00:00:00', '2022-01-06 16:10:23', '0');
INSERT INTO `t_role` VALUES ('5', '人力资源经理', '仅用户管理及角色管理权限', '2021-11-05 00:00:00', '2022-01-06 16:09:32', '0');

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
  `contact_person` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `contact_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
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
INSERT INTO `t_user` VALUES ('3', '测试', 'e35cf7b66449df565f93c607d5a81d09', '测试', '', '18000000000', '测试备注', '0', '2021-11-03 00:00:00', '2021-12-03 16:38:24');

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
INSERT INTO `t_user_role` VALUES ('3', '3', '3', '2021-11-03 00:00:00', '2021-12-03 16:38:24');
